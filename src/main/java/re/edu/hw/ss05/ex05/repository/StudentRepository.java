package re.edu.hw.ss05.ex05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.hw.ss05.ex05.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
