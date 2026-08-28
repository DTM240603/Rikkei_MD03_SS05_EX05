package re.edu.hw.ss05.ex05.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentCreateRequest {
    private String name;
    private String email;
}
