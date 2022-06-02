package mj.project.conferencescheduler.lecture.model;

import lombok.*;
import mj.project.conferencescheduler.user.model.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LectureEntity {

    @Transient
    public static int MAX_USERS_PER_LECTURE = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @ManyToMany
    private Set<UserEntity> users;
}
