package njt.myproject.dax.dto;

import njt.myproject.dax.dto.form.AddUserForm;
import njt.myproject.dax.dto.form.EditUserForm;
import njt.myproject.dax.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEditMapper {

    UserEditMapper INSTANCE = Mappers.getMapper(UserEditMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "active", target = "active")
    })
    User editUserFormToUser(EditUserForm editUserForm);
}
