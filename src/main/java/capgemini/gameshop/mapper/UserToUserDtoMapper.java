package capgemini.gameshop.mapper;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.entity.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Maps entities of user to DTO type
 *
 *  First method takes in one entity and returns one DTO
 *
 *  Second method takes in a List of entities and returns a List of DTO's
 */
public class UserToUserDtoMapper {
    public static UserDto map(User entity){
        return new UserDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword()
        );
    }

    public static List<UserDto> map(List<User> entities){
        return entities
                .stream()
                .map(UserToUserDtoMapper::map)
                .collect(Collectors.toList());
    }
}
