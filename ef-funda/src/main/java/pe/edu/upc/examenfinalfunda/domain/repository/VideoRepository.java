package pe.edu.upc.examenfinalfunda.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.examenfinalfunda.domain.model.Course;
import pe.edu.upc.examenfinalfunda.domain.model.Video;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,Long> {
    Page<Video> findByCourseId(Long courseId, Pageable pageable);

    Optional<Video> findByIdAndCourseId(Long videoId, Long courseId);
}
