package pe.edu.upc.examenfinalfunda.resource;

import lombok.Data;
import pe.edu.upc.examenfinalfunda.domain.model.AuditModel;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveVideoResource extends AuditModel {

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Integer duration;
}
