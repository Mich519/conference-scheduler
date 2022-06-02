package mj.project.conferencescheduler.user.service;

import mj.project.conferencescheduler.lecture.mapper.LectureMapper;
import mj.project.conferencescheduler.lecture.model.LectureDto;
import mj.project.conferencescheduler.lecture.model.LectureEntity;
import mj.project.conferencescheduler.lecture.service.LectureService;
import mj.project.conferencescheduler.user.exception.*;
import mj.project.conferencescheduler.user.mapper.UserMapper;
import mj.project.conferencescheduler.user.model.UserDto;
import mj.project.conferencescheduler.user.model.UserEntity;
import mj.project.conferencescheduler.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LectureService lectureService;
    private final UserMapper userMapper;

    private final LectureMapper lectureMapper;

    @Autowired
    public UserService(UserRepository userRepository, LectureService lectureService, UserMapper userMapper, LectureMapper lectureMapper) {
        this.userRepository = userRepository;
        this.lectureService = lectureService;
        this.userMapper = userMapper;
        this.lectureMapper = lectureMapper;
    }

    public UserDto findUserByUsername(String username) {
        return userRepository
                .findById(username)
                .map(userMapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }

    public Collection<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toSet());
    }

    public UserDto addUser(UserDto userDto) {
        userRepository.findById(userDto.getUsername())
                .ifPresent(userEntity1 -> {
                    throw new UsernameAlreadyInUseException(userDto.getUsername());
                });
        UserEntity result = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(result);
    }

    public UserDto updateUser(String username, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        userEntity.getLectures().clear();

        Set<LectureEntity> lectures = userDto.getLectureIds().stream()
                .map(lectureService::getLectureEntity)
                .filter(lectureEntity -> lectureEntity.getUsers().size() < LectureEntity.MAX_USERS_PER_LECTURE)
                .collect(Collectors.toSet());

        lectures.forEach(lecture -> lectures.forEach(other -> {
            if (lecture != other && areLecturesDatesCollide(lecture, other))
                throw new UserLecturesDatesOverlapException();
        }));

        lectures.forEach(lectureEntity -> lectureEntity.getUsers().add(userEntity));
        userEntity.setLectures(lectures);
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        sendResponseEmail(userDto.getEmail());
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    public UserDto deleteLectureFromUser(String username, long lectureId) {
        UserEntity userEntity = userRepository.findById(username).orElseThrow(() -> {
            throw new UserNotFoundException(username);
        });
        LectureEntity lecture = lectureService.getLectureEntity(lectureId);
        lecture.getUsers().remove(userEntity);
        userEntity.getLectures().remove(lecture);
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    private boolean areLecturesDatesCollide(LectureEntity lectureEntity1, LectureEntity lectureEntity2) {
        LocalDateTime startDate1 = lectureEntity1.getStartTime();
        LocalDateTime startDate2 = lectureEntity2.getStartTime();
        LocalDateTime endDate1 = lectureEntity1.getEndTime();
        LocalDateTime endDate2 = lectureEntity2.getEndTime();

        return startDate1.equals(startDate2) ||
                (startDate1.isAfter(startDate2) && startDate1.isBefore(endDate2)) ||
                (startDate2.isAfter(startDate1) && startDate2.isBefore(endDate1));
    }

    private void sendResponseEmail(String email) {
        try {

            PrintWriter writer = new PrintWriter(new FileOutputStream("notifications.txt"), true);
            writer.println(email);
            writer.println(LocalDateTime.now());
            writer.println("Your reservation is confirmed");
        } catch (IOException e) {
            throw new EmailResponseFileCreationEexception();
        }

    }

    public Collection<LectureDto> getLecturesByUsername(String username) {
        UserEntity userEntity = userRepository.findById(username).orElseThrow(() -> {
            throw new UserNotFoundException(username);
        });

        return userEntity.getLectures().stream()
                .map(lectureMapper::toDto)
                .collect(Collectors.toSet());
    }

    public UserDto patchUser(String username, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(username).orElseThrow(() -> {
            throw new UserNotFoundException(username);
        });
        userRepository.findUserEntityByEmail(userDto.getEmail()).ifPresent(userEntity1 -> {
            throw new EmailAlreadyInUseException(userDto.getEmail());
        });
        userEntity.setEmail(userDto.getEmail());
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }
}

