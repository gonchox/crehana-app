package pe.edu.upc.examenfinalfunda.resource;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveCourseResource {

    @NotNull
    @Size(max = 15)
    @NaturalId
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    private String hours;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String content;
}
