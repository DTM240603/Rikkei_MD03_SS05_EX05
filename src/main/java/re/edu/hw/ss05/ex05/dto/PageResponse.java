package re.edu.hw.ss05.ex05.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PageResponse<T> {
    private final List<T> items;
    private final int page;
    private final int size;
    private final int totalItems;
    private final int totalPages;
    private final boolean last;
}
