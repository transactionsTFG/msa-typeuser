package domainevent.command;

import javax.ejb.Local;
import javax.ejb.Stateless;

import business.qualifier.GetTypeQualifierV2;
import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventTypeUserHandler;
import msa.commons.commands.user.CreateUserCommand;
import msa.commons.event.EventId;

@Stateless
@GetTypeQualifierV2
@Local(EventTypeUserHandler.class)
public class GetTypeUserEvent extends BaseHandler {
    @Override
    public void handle(Object data) {
        CreateUserCommand command = this.gson.fromJson(data.toString(), CreateUserCommand.class);
        long idTypeUser = this.typeUserServices.getIdTypeUser(command);
        if (idTypeUser == 0) 
            this.jmsEventPublisher.publish(EventId.FAILED_USER, command.getIdUser());
        else
            this.jmsEventPublisher.publish(EventId.CREATE_USER, command.getIdUser());
    }    
}
