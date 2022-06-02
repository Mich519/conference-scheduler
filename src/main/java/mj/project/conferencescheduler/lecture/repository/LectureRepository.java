package mj.project.conferencescheduler.lecture.repository;

import mj.project.conferencescheduler.lecture.model.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
}