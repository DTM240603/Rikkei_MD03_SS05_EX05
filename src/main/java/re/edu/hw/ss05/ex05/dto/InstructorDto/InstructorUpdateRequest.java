package re.edu.hw.ss05.ex05.dto.InstructorDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InstructorUpdateRequest {
    private String name;
    private String email;
}
