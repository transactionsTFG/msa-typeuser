package integration.startup;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import business.command.handler.EventTypeUserHandler;
import msa.commons.event.EventId;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;

@Singleton
@Startup
public class EventHandlerRegistry {
    
    @EJB
    @GetTypeQualifierV2
    private EventTypeUserHandler getTypeHandler;
    private Map<EventId, EventTypeUserHandler> handlers = new EnumMap<>(EventId.class);

    @PostConstruct
    public void init(){
        this.handlers.put(EventId.GET_TYPE_USER, getTypeHandler);
    }

    public EventTypeUserHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

}
