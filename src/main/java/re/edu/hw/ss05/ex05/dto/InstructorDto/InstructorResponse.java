package re.edu.hw.ss05.ex05.dto.InstructorDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorResponse {
    private Long id;
    private String name;
    private String email;
    private List<InstructorCourseResponse> courses;
}
