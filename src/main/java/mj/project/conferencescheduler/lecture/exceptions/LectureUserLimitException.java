package mj.project.conferencescheduler.lecture.exceptions;

public class LectureUserLimitException extends RuntimeException {
    public LectureUserLimitException() {
        super("Max number of users per lecture reached");
    }
}
