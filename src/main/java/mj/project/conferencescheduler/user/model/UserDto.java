package mj.project.conferencescheduler.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
public class UserDto {
    private String username;
    private String email;
    private Set<Long> lectureIds;
}
