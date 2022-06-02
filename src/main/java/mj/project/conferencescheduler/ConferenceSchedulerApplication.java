package mj.project.conferencescheduler;

import mj.project.conferencescheduler.lecture.model.LectureDto;
import mj.project.conferencescheduler.lecture.service.LectureService;
import mj.project.conferencescheduler.user.model.UserDto;
import mj.project.conferencescheduler.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@SpringBootApplication
public class ConferenceSchedulerApplication implements CommandLineRunner {

    private final UserService userService;
    private final LectureService lectureService;

    public ConferenceSchedulerApplication(UserService userService, LectureService lectureService) {
        this.userService = userService;
        this.lectureService = lectureService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConferenceSchedulerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        lectureService.addLecture(LectureDto.builder()
                        .topic("Design")
                        .startTime(LocalDateTime.of(2022, 6, 1, 10, 0).toString())
                        .endTime(LocalDateTime.of(2022, 6, 1, 11, 45).toString())
                .build());

        lectureService.addLecture(LectureDto.builder()
                .topic("Design")
                .startTime(LocalDateTime.of(2022, 6, 1, 12, 00).toString())
                .endTime(LocalDateTime.of(2022, 6, 1, 13, 45).toString())
                .build());

        lectureService.addLecture(LectureDto.builder()
                .topic("Design")
                .startTime(LocalDateTime.of(2022, 6, 1, 14, 00).toString())
                .endTime(LocalDateTime.of(2022, 6, 1, 15, 45).toString())
                .build());

        lectureService.addLecture(LectureDto.builder()
                .topic("Cybersecurity")
                .startTime(LocalDateTime.of(2022, 6, 1, 10, 0).toString())
                .endTime(LocalDateTime.of(2022, 6, 1, 11, 45).toString())
                .build());

        lectureService.addLecture(LectureDto.builder()
                .topic("Cybersecurity")
                .startTime(LocalDateTime.of(2022, 6, 1, 12, 00).toString())
                .endTime(LocalDateTime.of(2022, 6, 1, 13, 45).toString())
                .build());

        lectureService.addLecture(LectureDto.builder()
                .topic("Cybersecurity")
                .startTime(LocalDateTime.of(2022, 6, 1, 14, 00).toString())
                .endTime(LocalDateTime.of(2022, 6, 1, 15, 45).toString())
                .build());

        lectureService.addLecture(LectureDto.builder()
                .topic("Management")
                .startTime(LocalDateTime.of(2022, 6, 1, 10, 0).toString())
                .endTime(LocalDateTime.of(2022, 6, 1, 11, 45).toString())
                .build());

        lectureService.addLecture(LectureDto.builder()
                .topic("Management")
                .startTime(LocalDateTime.of(2022, 6, 1, 12, 00).toString())
                .endTime(LocalDateTime.of(2022, 6, 1, 13, 45).toString())
                .build());

        lectureService.addLecture(LectureDto.builder()
                .topic("Management")
                .startTime(LocalDateTime.of(2022, 6, 1, 14, 00).toString())
                .endTime(LocalDateTime.of(2022, 6, 1, 15, 45).toString())
                .build());

        userService.addUser(UserDto.builder()
                .username("john")
                .email("john@john")
                .build()
        );

        userService.updateUser("john", UserDto.builder()
                .username("john")
                .email("john@john")
                .lectureIds(Set.of(1L, 2L))
                .build());

        userService.addUser(UserDto.builder()
                .username("mark")
                .email("mark@mark")
                .build()
        );

        userService.updateUser("mark", UserDto.builder()
                .username("mark")
                .email("mark@mark")
                .lectureIds(Set.of(4L, 5L, 6L))
                .build());

        userService.addUser(UserDto.builder()
                .username("anna")
                .email("anna@anna")
                .build()
        );

        userService.updateUser("anna", UserDto.builder()
                .username("anna")
                .email("anna@anna")
                .lectureIds(Set.of(7L, 8L, 9L))
                .build());

        userService.addUser(UserDto.builder()
                .username("dell")
                .email("dell@dell")
                .build()
        );

        userService.updateUser("dell", UserDto.builder()
                .username("dell")
                .email("dell@dell")
                .lectureIds(Set.of(1L, 2L, 3L))
                .build());
    }
}
