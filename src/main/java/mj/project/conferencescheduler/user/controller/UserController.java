package mj.project.conferencescheduler.user.controller;

import mj.project.conferencescheduler.lecture.model.LectureDto;
import mj.project.conferencescheduler.user.model.UserDto;
import mj.project.conferencescheduler.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<UserDto>> getUsers() {
        return ResponseEntity.ok(
                userService.findAllUsers()
        );
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(
                userService.findUserByUsername(username)
        );
    }

    @GetMapping("/users/{username}/lectures")
    public ResponseEntity<Collection<LectureDto>> getUserReservations(@PathVariable String username) {
        return ResponseEntity.ok(
                userService.getLecturesByUsername(username)
        );
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(
                userService.addUser(userDto)
        );
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(
                userService.updateUser(username, userDto)
        );
    }

    @PatchMapping("/users/{username}")
    public ResponseEntity<UserDto> patchUser(@PathVariable String username,
                                             @RequestBody UserDto userDto) {
        return ResponseEntity.ok(
                userService.patchUser(username, userDto)
        );
    }

    @DeleteMapping("/users/{username}/lectures/{lectureId}")
    public ResponseEntity<UserDto> deleteLectureFromUser(@PathVariable(value = "username") String username,
                                                   @PathVariable(value = "lectureId") long lectureId) {
        return ResponseEntity.ok(
                userService.deleteLectureFromUser(username, lectureId)
        );
    }
}
