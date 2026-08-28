package re.edu.hw.ss05.ex05.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private List<StudentEnrollmentResponse> studentEnrollments;
}
