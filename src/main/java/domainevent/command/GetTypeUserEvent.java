package domainevent.command;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domainevent.command.handler.BaseHandler;
import domainevent.command.handler.EventTypeUserHandler;
import msa.commons.event.EventId;
import msa.commons.event.EventResponse;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;
import msa.commons.microservices.user.commandevent.CreateUserCommand;

@Stateless
@GetTypeQualifierV2
@Local(EventTypeUserHandler.class)
public class GetTypeUserEvent extends BaseHandler {
    @Override
    public void handle(EventResponse eventResponse) {
        long idTypeUser = this.typeUserServices.getIdTypeUser(this.gson.fromJson(eventResponse.getData().toString(), CreateUserCommand.class));
        if (idTypeUser == 0) 
            this.jmsEventPublisher.publish(EventId.CREATE_USER, EventResponse.success(idTypeUser));
        else
            this.jmsEventPublisher.publish(EventId.FAILED_USER, EventResponse.error("Tipo de usuario no encontrado"));
    }    
}
