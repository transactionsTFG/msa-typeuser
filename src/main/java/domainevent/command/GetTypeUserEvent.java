package domainevent.command;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import business.services.TypeUserServices;
import domainevent.command.handler.EventTypeUserHandler;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;
import msa.commons.microservices.user.commandevent.CreateUserCommand;

@Stateless
@GetTypeQualifierV2
public class GetTypeUserEvent implements EventTypeUserHandler {

    private static final Logger LOGGER = LogManager.getLogger(GetTypeUserEvent.class);
    private TypeUserServices typeUserServices;
    private Gson gson;

    @Override
    public void handle(Object event) {
        LOGGER.info("GetTypeUserEvent.handle() called with event: {}", event);
        this.typeUserServices.getTypeUser(this.gson.fromJson(event.toString(), CreateUserCommand.class));
    }

    @EJB
    public void setTypeUserServices(TypeUserServices typeUserServices) {
        this.typeUserServices = typeUserServices;
    }

    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
