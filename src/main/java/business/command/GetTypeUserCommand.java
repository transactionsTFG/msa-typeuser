package business.command;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import business.command.handler.CommandHandler;
import business.command.qualifier.GetTypeQualifier;
import business.services.TypeUserServices;
import msa.commons.parser.NumberParser;

@Stateless
@GetTypeQualifier
public class GetTypeUserCommand implements CommandHandler {

    private TypeUserServices typeUserServices;
    
    @Override
    public void handleCommand(Object event) {
        long idTypeUser = NumberParser.toLong(event);
        this.typeUserServices.getTypeUser(idTypeUser);
    } 

    @EJB
    public void setTypeUserServices(TypeUserServices typeUserServices) {
        this.typeUserServices = typeUserServices;
    }
}
