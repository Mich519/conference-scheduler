package mj.project.conferencescheduler.lecture.controller;

import mj.project.conferencescheduler.lecture.model.LectureDto;
import mj.project.conferencescheduler.lecture.model.LectureReportDto;
import mj.project.conferencescheduler.lecture.model.TopicReportDto;
import mj.project.conferencescheduler.lecture.service.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class LectureController {

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/lectures")
    public ResponseEntity<Collection<LectureDto>> getAllLectures() {
        return ResponseEntity.ok(
                lectureService.getAllLectures()
        );
    }

    @GetMapping("/lectures/{id}")
    public ResponseEntity<LectureDto> getLecture(@PathVariable long id) {
        return ResponseEntity.ok(
                lectureService.getLecture(id)
        );
    }

    @PostMapping("/lectures")
    public ResponseEntity<LectureDto> addLecture(@RequestBody LectureDto lectureDto) {
        return ResponseEntity.ok(
                lectureService.addLecture(lectureDto)
        );
    }

    @GetMapping("/lectures/attendance-report")
    public ResponseEntity<Collection<LectureReportDto>> getLectureAttendanceReport() {
        return ResponseEntity.ok(
                lectureService.getLectureAttendanceReport()
        );
    }

    @GetMapping("/lectures/topic-attendance-report")
    public ResponseEntity<Collection<TopicReportDto>> getTopicAttendanceReport() {
        return ResponseEntity.ok(
                lectureService.getTopicAttendanceReport()
        );
    }
}
