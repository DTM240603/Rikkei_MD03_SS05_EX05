package re.edu.hw.ss05.ex05.dto.InstructorDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.hw.ss05.ex05.entity.Instructor;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InstructorCreateRequest {
    private String name;
    private String email;

    public InstructorCreateRequest(Instructor i) {
        this.name = i.getName();
        this.email = i.getEmail();
    }
}
