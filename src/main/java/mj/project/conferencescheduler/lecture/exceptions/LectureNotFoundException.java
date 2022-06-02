package mj.project.conferencescheduler.lecture.exceptions;

public class LectureNotFoundException extends RuntimeException{
    public LectureNotFoundException(long id) {
        super("Lecture with id: " + id + " not found");
    }
}
