package business.command;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.command.handler.EventHandler;
import business.command.qualifier.GetTypeQualifier;
import business.services.TypeUserServices;
import msa.commons.parser.NumberParser;

@Stateless
@GetTypeQualifier
public class GetTypeUserEvent implements EventHandler {

    private TypeUserServices typeUserServices;
    
    @Override
    public void handle(Object event) {
        long idTypeUser = NumberParser.toLong(event);
        this.typeUserServices.getTypeUser(idTypeUser);
    } 

    @EJB
    public void setTypeUserServices(TypeUserServices typeUserServices) {
        this.typeUserServices = typeUserServices;
    }
}
