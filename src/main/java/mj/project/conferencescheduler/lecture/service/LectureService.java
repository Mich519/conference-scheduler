package mj.project.conferencescheduler.lecture.service;

import mj.project.conferencescheduler.lecture.mapper.LectureMapper;
import mj.project.conferencescheduler.lecture.model.LectureDto;
import mj.project.conferencescheduler.lecture.model.LectureReportDto;
import mj.project.conferencescheduler.lecture.exceptions.LectureNotFoundException;
import mj.project.conferencescheduler.lecture.model.LectureEntity;
import mj.project.conferencescheduler.lecture.model.TopicReportDto;
import mj.project.conferencescheduler.lecture.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    private final LectureMapper lectureMapper;

    @Autowired
    public LectureService(LectureRepository lectureRepository, LectureMapper lectureMapper) {
        this.lectureRepository = lectureRepository;
        this.lectureMapper = lectureMapper;
    }

    public LectureDto getLecture(long id) throws LectureNotFoundException {
        return lectureRepository.findById(id)
                .map(lectureMapper::toDto)
                .orElseThrow(() -> new LectureNotFoundException(id));
    }

    public LectureEntity getLectureEntity(long id) {
        return lectureRepository.findById(id)
                .orElseThrow(() -> new LectureNotFoundException(id));
    }

    public Collection<LectureDto> getAllLectures() {
        return lectureRepository.findAll().stream()
                .map(lectureMapper::toDto)
                .collect(Collectors.toSet());
    }
    public LectureDto addLecture(LectureDto lectureDto) {
        LectureEntity lectureEntity = lectureRepository.save(lectureMapper.toEntity(lectureDto));
        return lectureMapper.toDto(lectureEntity);
    }

    public Set<LectureReportDto> getLectureAttendanceReport() {
        return lectureRepository.findAll().stream()
                .map(lectureEntity -> LectureReportDto.builder()
                        .lectureId(lectureEntity.getId())
                        .attendancePercentage(lectureEntity.getUsers().size() * 100 / LectureEntity.MAX_USERS_PER_LECTURE)
                        .build())
                .collect(Collectors.toSet());
    }

    public Set<TopicReportDto> getTopicAttendanceReport() {
        Map<String, Integer> topicByNumOfUsers = new HashMap<>();
        Map<String, Integer> topicByMaxUsers = new HashMap<>();
        // calculate number of users that signed up for a topic and max possible number of users per topic
        lectureRepository.findAll()
                .forEach(lectureEntity -> {
                    int numOfUsers = lectureEntity.getUsers().size();
                    topicByNumOfUsers.putIfAbsent(lectureEntity.getTopic(), 0);
                    topicByNumOfUsers.computeIfPresent(lectureEntity.getTopic(), (a, prev) -> prev + numOfUsers);
                    topicByMaxUsers.putIfAbsent(lectureEntity.getTopic(), 0);
                    topicByMaxUsers.computeIfPresent(lectureEntity.getTopic(), (a, prev) -> prev + LectureEntity.MAX_USERS_PER_LECTURE);
                });

        return topicByNumOfUsers.entrySet().stream()
                .map(entry -> TopicReportDto.builder()
                        .topic(entry.getKey())
                        .attendancePercentage(entry.getValue() * 100 / topicByMaxUsers.get(entry.getKey()))
                        .build())
                .collect(Collectors.toSet());
    }
}
