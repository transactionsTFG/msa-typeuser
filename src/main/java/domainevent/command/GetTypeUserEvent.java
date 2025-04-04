package domainevent.command;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;

import business.services.TypeUserServices;
import domainevent.command.handler.EventTypeUserHandler;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;
import msa.commons.microservices.user.commandevent.CreateUserCommand;
import msa.commons.parser.ObjectUtils;

@Stateless
@GetTypeQualifierV2
public class GetTypeUserEvent implements EventTypeUserHandler {

    private TypeUserServices typeUserServices;
    private Gson gson;

    @Override
    public void handle(Object event) {
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
