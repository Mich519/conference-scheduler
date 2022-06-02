package mj.project.conferencescheduler.lecture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LectureReportDto {
    long lectureId;
    int attendancePercentage;
}
