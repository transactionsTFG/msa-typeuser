package business.services;

import msa.commons.microservices.user.commandevent.CreateUserCommand;

public interface TypeUserServices {
    long getIdTypeUser(CreateUserCommand createUserCommand);
}
