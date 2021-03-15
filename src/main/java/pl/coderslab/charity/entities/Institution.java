package pl.coderslab.charity.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "institution", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Data
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
}
