import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.nimbusds.jose.shaded.gson.Gson;

import msa.commons.event.Event;
import msa.commons.event.EventId;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            // Propiedades JNDI
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            env.put(Context.PROVIDER_URL, "t3://localhost:9001");
            env.put(Context.SECURITY_PRINCIPAL, "weblogic");
            env.put(Context.SECURITY_CREDENTIALS, "password$1");

            Context ctx = new InitialContext(env);

            // Lookup
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/connectionFactory");
            Queue queue = (Queue) ctx.lookup("jms/typeUserServiceQueue");

            // Crear un JMSContext (auto-close con try-with-resources)
            try (JMSContext jmsContext = connectionFactory.createContext("weblogic", "password$1")) {

                // Enviar
                //jmsContext.createProducer().send(queue, "CreateUserCommand");
                jmsContext.createProducer().send(queue, new Gson().toJson(new Event(EventId.GET_TYPE_USER, 1L)));

                // Recibir
                //String msgRecibido = jmsContext.createConsumer(queue).receiveBody(String.class, 5000);
                //System.out.println("Mensaje recibido: " + msgRecibido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}