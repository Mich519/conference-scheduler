package mj.project.conferencescheduler.user.model;

import lombok.*;
import mj.project.conferencescheduler.lecture.model.LectureEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "reservations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id"))
    private Set<LectureEntity> lectures;
}
