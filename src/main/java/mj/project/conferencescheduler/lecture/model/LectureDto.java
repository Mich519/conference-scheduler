package mj.project.conferencescheduler.lecture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import mj.project.conferencescheduler.user.model.UserEntity;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
public class LectureDto {
    private long id;
    private String topic;
    private String startTime;
    private String endTime;
    private Set<String> userIds;
}
