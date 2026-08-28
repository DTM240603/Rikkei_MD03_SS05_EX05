package re.edu.hw.ss05.ex05.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.hw.ss05.ex05.dto.CourseDto.CourseResponseV2;
import re.edu.hw.ss05.ex05.entity.Course;
import re.edu.hw.ss05.ex05.entity.CourseStatus;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT c FROM Course c WHERE :status IS NULL OR c.status = :status")
    Page<Course> findAllByStatus(@Param("status") CourseStatus status, Pageable pageable);

    @Query(value = """
        SELECT new re.edu.hw.ss05.ex05.dto.CourseDto.CourseResponseV2(
            c.id,
            c.title,
            c.status)
        from Course c
        WHERE :status IS NULL OR c.status = :status
    """)
    Page<CourseResponseV2> findAllByStatusV2(@Param("status") CourseStatus status, Pageable pageable);
}
