package business.services;

import business.typeuser.TypeUserDTO;
import msa.commons.commands.user.CreateUserCommand;

public interface TypeUserServices {
    long getIdTypeUser(CreateUserCommand createUserCommand);
    TypeUserDTO getTypeUserById(Long id);
}
