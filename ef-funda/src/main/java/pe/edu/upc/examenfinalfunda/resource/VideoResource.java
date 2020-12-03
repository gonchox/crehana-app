package pe.edu.upc.examenfinalfunda.resource;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.examenfinalfunda.domain.model.AuditModel;

@Getter
@Setter
@Data
public class VideoResource extends AuditModel {
    private Long id;
    private Long courseId;
    private String title;
    private String description;
    private Integer duration;
}
