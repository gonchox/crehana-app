package pe.edu.upc.examenfinalfunda.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinalfunda.domain.model.Course;
import pe.edu.upc.examenfinalfunda.domain.service.CourseService;
import pe.edu.upc.examenfinalfunda.resource.CourseResource;
import pe.edu.upc.examenfinalfunda.resource.SaveCourseResource;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "courses", description = "Courses API")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class CourseController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CourseService courseService;

    @Operation(summary = "Get Courses", description = "Get All Courses by Pages", tags = { "courses" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Courses returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/courses")
    public Page<CourseResource> getAllCourses(Pageable pageable) {
        Page<Course> coursesPage = courseService.getAllCourses(pageable);
        List<CourseResource> resources = coursesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get Course by Id", description = "Get a Course by specifying Id", tags = { "courses" })
    @GetMapping("/courses/{id}")
    public CourseResource getCourseById(
            @Parameter(description="Course Id")
            @PathVariable(name = "id") Long courseId) {
        return convertToResource(courseService.getCourseById(courseId));
    }

    //@Operation(security={ @SecurityRequirement(name="Authorization") })
    @PostMapping("/courses")
    public CourseResource createUser(@Valid @RequestBody SaveCourseResource resource)  {
       Course course = convertToEntity(resource);
        return convertToResource(courseService.createCourse(course));
    }


    @PutMapping("/courses/{id}")
    public CourseResource updateCourse(@PathVariable(name = "id") Long courseId, @Valid @RequestBody SaveCourseResource resource) {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.updateCourse(courseId, course));
    }


    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long courseId) {
        return courseService.deleteCourse(courseId);
    }
    // Auto Mapper
    private Course convertToEntity(SaveCourseResource resource) {
        return mapper.map(resource, Course.class);
    }

    private CourseResource convertToResource(Course entity) {
        return mapper.map(entity, CourseResource.class);
    }
}
