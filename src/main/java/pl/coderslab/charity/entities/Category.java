package pl.coderslab.charity.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "category", uniqueConstraints = {
@UniqueConstraint(columnNames = "name")
})
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
