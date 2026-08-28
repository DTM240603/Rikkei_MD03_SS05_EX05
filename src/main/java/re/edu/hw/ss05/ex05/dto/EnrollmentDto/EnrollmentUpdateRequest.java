package re.edu.hw.ss05.ex05.dto.EnrollmentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EnrollmentUpdateRequest {
    private Long studentId;
    private Long courseId;
}
