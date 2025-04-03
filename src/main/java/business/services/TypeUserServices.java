package business.services;

import msa.commons.microservices.user.commandevent.CreateUserCommand;

public interface TypeUserServices {
    void getTypeUser(CreateUserCommand createUserCommand);
}
