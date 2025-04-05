package domainevent.command.handler;

import javax.ejb.EJB;
import javax.inject.Inject;

import com.google.gson.Gson;

import business.services.TypeUserServices;
import domainevent.publisher.IJMSEventPublisher;

public abstract class BaseHandler implements EventTypeUserHandler{
    protected TypeUserServices typeUserServices;
    protected IJMSEventPublisher jmsEventPublisher;
    protected Gson gson;

    @EJB
    public void setTypeUserServices(TypeUserServices typeUserServices) {
        this.typeUserServices = typeUserServices;
    }
    
    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }
    @Inject
    public void setJmsEventPublisher(IJMSEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }
}
