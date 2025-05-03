package business.services;

import msa.commons.commands.user.CreateUserCommand;

public interface TypeUserServices {
    long getIdTypeUser(CreateUserCommand createUserCommand);
}
