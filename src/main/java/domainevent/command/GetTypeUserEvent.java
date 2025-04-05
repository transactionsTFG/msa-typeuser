package domainevent.command;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventTypeUserHandler;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;
import msa.commons.microservices.user.commandevent.CreateUserCommand;

@Stateless
@GetTypeQualifierV2
@Local(EventTypeUserHandler.class)
public class GetTypeUserEvent extends BaseHandler {
    @Override
    public void handle(Object event) {
        this.typeUserServices.getTypeUser(this.gson.fromJson(event.toString(), CreateUserCommand.class));
    }    
}
