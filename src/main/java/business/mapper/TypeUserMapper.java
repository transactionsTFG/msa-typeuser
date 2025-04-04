package business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import business.typeuser.TypeUser;
import business.typeuser.TypeUserDTO;

@Mapper
public interface TypeUserMapper {
    TypeUserMapper INSTANCE = Mappers.getMapper(TypeUserMapper.class);
    TypeUserDTO entityToDTO(TypeUser typeUser);
}
