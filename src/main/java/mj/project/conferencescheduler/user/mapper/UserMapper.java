package mj.project.conferencescheduler.user.mapper;

import mj.project.conferencescheduler.lecture.model.LectureEntity;
import mj.project.conferencescheduler.user.model.UserDto;
import mj.project.conferencescheduler.user.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .lectureIds(userEntity.getLectures().stream()
                        .map(LectureEntity::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public UserEntity toEntity(UserDto userDto) {
        return UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .lectures(Collections.emptySet())
                .build();
    }
}
