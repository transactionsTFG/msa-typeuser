package business.mapper;

import business.typeuser.TypeUser;
import business.typeuser.TypeUserDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2025-04-04T12:13:45+0200",
=======
    date = "2025-04-04T10:38:31+0200",
>>>>>>> 995e802cf05af5597eec36a4e2794da8b4246ee7
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
public class TypeUserMapperImpl implements TypeUserMapper {

    @Override
    public TypeUserDTO entityToDTO(TypeUser typeUser) {
        if ( typeUser == null ) {
            return null;
        }

        TypeUserDTO typeUserDTO = new TypeUserDTO();

        typeUserDTO.setId( typeUser.getId() );
        typeUserDTO.setIsActive( typeUser.getIsActive() );
        typeUserDTO.setName( typeUser.getName() );

        return typeUserDTO;
    }
}
