package business.command;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.command.handler.EventTypeUserHandler;
import business.services.TypeUserServices;
import msa.commons.microservices.typeuser.qualifier.GetTypeQualifierV2;
import msa.commons.microservices.user.commandevent.CreateUserCommand;
import msa.commons.parser.ObjectUtils;

@Stateless
@GetTypeQualifierV2
public class GetTypeUserEvent implements EventTypeUserHandler {

    private TypeUserServices typeUserServices;
    
    @Override
    public void handle(Object event) {
        if(ObjectUtils.isInstanceOf(event, CreateUserCommand.class))
            this.typeUserServices.getTypeUser(ObjectUtils.safeCast(event, CreateUserCommand.class));
    }

    @EJB
    public void setTypeUserServices(TypeUserServices typeUserServices) {
        this.typeUserServices = typeUserServices;
    }
}
