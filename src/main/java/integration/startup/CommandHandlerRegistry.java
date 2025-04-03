package integration.startup;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import business.command.handler.CommandHandler;
import business.command.qualifier.GetTypeQualifier;
import msa.commons.event.EventId;

@Singleton
@Startup
public class CommandHandlerRegistry {
    
    @EJB
    @GetTypeQualifier
    private CommandHandler getTypeUserCommand;
    private Map<EventId, CommandHandler> handlers = new EnumMap<>(EventId.class);

    @PostConstruct
    public void init(){
        this.handlers.put(EventId.GET_TYPE_USER, getTypeUserCommand);
    }

    public CommandHandler getCommandHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

}
