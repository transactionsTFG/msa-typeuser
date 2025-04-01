import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
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
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/TestConnectionFactory");
            Queue queue = (Queue) ctx.lookup("jms/testq");

            // Crear un JMSContext (auto-close con try-with-resources)
            try (JMSContext jmsContext = connectionFactory.createContext("weblogic", "password$1")) {

                // Enviar
                jmsContext.createProducer().send(queue, "Mensaje JMS 2.0 desde Java");

                // Recibir
                String msgRecibido = jmsContext.createConsumer(queue).receiveBody(String.class, 5000);
                System.out.println("Mensaje recibido: " + msgRecibido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}