package pe.edu.upc.examenfinalfunda.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
