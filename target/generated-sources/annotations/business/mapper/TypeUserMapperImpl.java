package business.mapper;

import business.typeuser.TypeUser;
import business.typeuser.TypeUserDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-03T22:27:01+0200",
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
        typeUserDTO.setName( typeUser.getName() );

        return typeUserDTO;
    }
}
