package re.edu.hw.ss05.ex05.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse <T>{
    private int status;
    private String message;
    private T data;
}
