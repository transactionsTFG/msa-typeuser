package integration.startup;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import business.command.handler.EventHandler;
import business.command.qualifier.GetTypeQualifier;
import msa.commons.event.EventId;

@Singleton
@Startup
public class EventHandlerRegistry {
    
    @EJB
    @GetTypeQualifier
    private EventHandler getTypeHandler;
    private Map<EventId, EventHandler> handlers = new EnumMap<>(EventId.class);

    @PostConstruct
    public void init(){
        this.handlers.put(EventId.GET_TYPE_USER, getTypeHandler);
    }

    public EventHandler getCommandHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

}
