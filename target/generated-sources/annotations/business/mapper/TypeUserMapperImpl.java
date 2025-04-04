package business.mapper;

import business.typeuser.TypeUser;
import business.typeuser.TypeUserDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-04T16:09:26+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.13 (Oracle Corporation)"
)
public class TypeUserMapperImpl implements TypeUserMapper {

    @Override
    public TypeUserDTO entityToDTO(TypeUser typeUser) {
        if ( typeUser == null ) {
            return null;
        }

        TypeUserDTO typeUserDTO = new TypeUserDTO();

        typeUserDTO.setId( typeUser.getId() );
        typeUserDTO.setName( typeUser.getName() );
        typeUserDTO.setIsActive( typeUser.getIsActive() );

        return typeUserDTO;
    }
}
