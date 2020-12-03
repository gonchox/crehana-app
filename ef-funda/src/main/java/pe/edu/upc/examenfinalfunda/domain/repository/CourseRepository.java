package pe.edu.upc.examenfinalfunda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinalfunda.domain.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
