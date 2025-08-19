package yunona.cloud_storage_backend.utils;

import yunona.cloud_storage_backend.dto.UserDto;
import yunona.cloud_storage_backend.entity.UserEntity;

public interface MapperUtils {

    UserEntity toUserEntity(UserDto userDto);

    UserDto toUserDto(UserEntity userEntity);

}
