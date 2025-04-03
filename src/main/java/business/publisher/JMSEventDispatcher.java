package business.publisher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.google.gson.Gson;

import msa.commons.event.EventId;
import msa.commons.event.Event;

@Stateless
public class JMSEventDispatcher implements IJMSEventDispatcher {

    private ConnectionFactory connectionFactory;
    private Queue orchestratorQueue;
    private Gson gson;

    public JMSEventDispatcher() {}

    @Inject
    public JMSEventDispatcher(ConnectionFactory connectionFactory, Queue orchestratorQueue, Gson gson) {
        this.connectionFactory = connectionFactory;
        this.orchestratorQueue = orchestratorQueue;
        this.gson = gson;
    }

    @Override
    public void publish(EventId eventId, Object data){
        try(JMSContext jmsContext = connectionFactory.createContext()) {
            Event sendMsg = new Event(eventId, data);
            jmsContext.createProducer().send(this.orchestratorQueue, this.gson.toJson(sendMsg));
        }
    }

}
