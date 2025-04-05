package domainevent.command.handler;

import javax.ejb.EJB;
import javax.inject.Inject;

import com.google.gson.Gson;

import business.services.TypeUserServices;

public abstract class BaseHandler implements EventTypeUserHandler{
    protected TypeUserServices typeUserServices;
    protected Gson gson;

    @EJB
    public void setTypeUserServices(TypeUserServices typeUserServices) {
        this.typeUserServices = typeUserServices;
    }
    
    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
