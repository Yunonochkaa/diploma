package yunona.cloud_storage_backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import yunona.cloud_storage_backend.dto.UserDto;
import yunona.cloud_storage_backend.entity.UserEntity;
import yunona.cloud_storage_backend.enums.Role;
import yunona.cloud_storage_backend.exception.IncorrectDataEntry;
import yunona.cloud_storage_backend.exception.UserNotFoundException;
import yunona.cloud_storage_backend.repository.UserRepository;
import yunona.cloud_storage_backend.utils.MapperUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final MapperUtils mapperUtils;
    private final PasswordEncoder passwordEncoder;

    public UserDto registerUser(UserDto userDto) {
        UserEntity userEntity = mapperUtils.toUserEntity(userDto);

        log.info("Проверка свободности логина: {}", userEntity);
        userRepository.findUserByLogin(userEntity.getLogin()).ifPresent(s -> {
            log.info("Пользователь с таким логином: {} уже существует", userEntity.getLogin());
            throw new IncorrectDataEntry("Пользователь с таким логином: { " + userEntity.getLogin() +
                    " } уже зарегистрирован", userEntity.getId());
        });
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Collections.singleton(Role.ROLE_USER));
        userEntity.setRole(Role.ROLE_USER.getAuthority());
        log.info("Новый пользователь зарегистрирован: {}", userEntity);
        return mapperUtils.toUserDto(userRepository.save(userEntity));
    }

    public UserDto getUser(Long id) {
        log.info("Поиск пользователя в базе данных по Id: {}", id);
        UserEntity userFound = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь не найден", id));
        log.info("Пользователь в базе данных найден. ID: {}, логин: {}", id, userFound);
        return mapperUtils.toUserDto(userFound);
    }

    public void deleteUser(Long id) {
        log.info("Поиск пользователя в базе данных по Id: {}", id);
        UserEntity userFound = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь не найден", id));
        log.info("Пользователь в базе данных найден. ID: {}, логин: {}", id, userFound);
        log.info("Пользователь удален. ID: {}, логин: {}", id, userFound);
        userRepository.deleteById(id);
    }
}