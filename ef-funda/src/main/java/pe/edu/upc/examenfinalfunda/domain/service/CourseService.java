package pe.edu.upc.examenfinalfunda.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pe.edu.upc.examenfinalfunda.domain.model.Course;

public interface CourseService {
    ResponseEntity<?> deleteCourse(Long courseId);

    Course updateCourse(Long courseId, Course courseRequest);

    Course createCourse(Course course);

    Course getCourseById(Long courseId);

    Page<Course> getAllCourses(Pageable pageable);

}
