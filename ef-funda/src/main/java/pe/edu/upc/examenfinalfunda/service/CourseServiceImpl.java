package pe.edu.upc.examenfinalfunda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinalfunda.domain.model.Course;
import pe.edu.upc.examenfinalfunda.domain.repository.CourseRepository;
import pe.edu.upc.examenfinalfunda.domain.service.CourseService;
import pe.edu.upc.examenfinalfunda.exception.ResourceNotFoundException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public ResponseEntity<?> deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        courseRepository.delete(course);
        return ResponseEntity.ok().build();
    }

    @Override
    public Course updateCourse(Long courseId, Course courseRequest) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        course.setContent(courseRequest.getContent());
        course.setHours(courseRequest.getHours());
        return courseRepository.save(course);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
}
