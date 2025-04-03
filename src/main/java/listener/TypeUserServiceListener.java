package listener;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.transaction.Transactional;

import com.google.gson.Gson;

import business.command.handler.CommandHandler;
import integration.startup.CommandHandlerRegistry;
import msa.commons.event.Event;


@MessageDriven(mappedName = "jms/typeUserServiceQueue")
public class TypeUserServiceListener implements MessageListener {
    
    private Gson gson;
    private CommandHandlerRegistry commandHandlerRegistry;

    @Override
    @Transactional
    public void onMessage(Message msg) {
        try {
            if(msg instanceof TextMessage m) {
                Event event = this.gson.fromJson(m.getText(), Event.class);
                CommandHandler commandHandler = this.commandHandlerRegistry.getCommandHandler(event.getEventId());
                if(commandHandler != null)
                    commandHandler.handleCommand(event.getData());
            }
        } catch (Exception e) {
            System.out.println("Error al recibir el mensaje: " + e.getMessage());
        }
    }

    @Inject
    public void setGson(Gson gson) { this.gson = gson; }
    @EJB
    public void setCommandHandlerRegistry(CommandHandlerRegistry commandHandlerRegistry) { this.commandHandlerRegistry = commandHandlerRegistry; }
}