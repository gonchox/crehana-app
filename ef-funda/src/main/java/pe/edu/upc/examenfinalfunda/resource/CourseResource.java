package pe.edu.upc.examenfinalfunda.resource;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CourseResource {
    private Long id;
    private String name;
    private String hours;
    private String description;
    private String content;
}
