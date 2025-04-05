package domainevent.publisher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import integration.consts.JMSQueueNames;
import msa.commons.event.EventId;
import msa.commons.event.Event;

@Stateless
public class JMSEventPublisher implements IJMSEventPublisher {

    private ConnectionFactory connectionFactory;
    private Queue orchestratorQueue;
    private Gson gson;
    private static final Logger LOGGER = LogManager.getLogger(JMSEventPublisher.class);

    public JMSEventPublisher() {}

    @Inject
    public JMSEventPublisher(ConnectionFactory connectionFactory, Queue orchestratorQueue, Gson gson) {
        this.connectionFactory = connectionFactory;
        this.orchestratorQueue = orchestratorQueue;
        this.gson = gson;
    }

    @Override
    public void publish(EventId eventId, Object data){
        try(JMSContext jmsContext = connectionFactory.createContext()) {
            Event sendMsg = new Event(eventId, data);
            final String msg = this.gson.toJson(sendMsg);
            LOGGER.info("Publicando en Cola {}, Evento Id: {}, Mensaje: {}", JMSQueueNames.ORCHESTATOR_QUEUE, eventId, msg);
            jmsContext.createProducer().send(this.orchestratorQueue, msg);
        }
    }

}
