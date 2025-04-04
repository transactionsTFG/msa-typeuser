package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import domainevent.command.handler.EventTypeUserHandler;
import msa.commons.event.EventId;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;

@Singleton
@Startup
public class EventHandlerRegistry {
    private Map<EventId, EventTypeUserHandler> handlers = new EnumMap<>(EventId.class);
    private EventTypeUserHandler getTypeHandler;

    @PostConstruct
    public void init(){
        this.handlers.put(EventId.GET_TYPE_USER, getTypeHandler);
    }

    public EventTypeUserHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @EJB
    @GetTypeQualifierV2
    public void setGetTypeHandler(EventTypeUserHandler getTypeHandler) {
        this.getTypeHandler = getTypeHandler;
    }
}
