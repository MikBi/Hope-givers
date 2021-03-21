package pl.coderslab.charity.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "category", uniqueConstraints = {
@UniqueConstraint(columnNames = "name")
})
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "[A-Z][A-z]+")
    private String name;
}
