package mj.project.conferencescheduler.lecture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TopicReportDto {
    String topic;
    int attendancePercentage;
}
