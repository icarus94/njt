package njt.myproject.dax.dto;

import njt.myproject.dax.dto.form.AddUserForm;
import njt.myproject.dax.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAddMapper {

    UserAddMapper INSTANCE = Mappers.getMapper(UserAddMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surname", target = "surname"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "role", target = "role"),
    })
    User addUserFormToUser(AddUserForm addUserForm);
}
