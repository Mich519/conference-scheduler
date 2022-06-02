package mj.project.conferencescheduler.lecture.mapper;

import mj.project.conferencescheduler.lecture.model.LectureDto;
import mj.project.conferencescheduler.lecture.model.LectureEntity;
import mj.project.conferencescheduler.user.model.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class LectureMapper {

    public LectureDto toDto(LectureEntity lectureEntity) {
        return LectureDto.builder()
                .id(lectureEntity.getId())
                .startTime(lectureEntity.getStartTime().toString())
                .endTime(lectureEntity.getEndTime().toString())
                .topic(lectureEntity.getTopic())
                .userIds(lectureEntity.getUsers().stream()
                        .map(UserEntity::getUsername)
                        .collect(Collectors.toSet()))
                .build();
    }

    public LectureEntity toEntity(LectureDto lectureDto) {
        return LectureEntity.builder()
                //.id(lectureDto.getId())
                .startTime(LocalDateTime.parse(lectureDto.getStartTime()))
                .endTime(LocalDateTime.parse(lectureDto.getEndTime()))
                .topic(lectureDto.getTopic())
                .users(Collections.emptySet())
                .build();
    }
}
