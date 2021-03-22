package pl.coderslab.charity.entities;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "archived_donation")
@Data
public class ArchivedDonations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private String street;
    private String city;
    private String phoneNumber;
    private String zipCode;
    private java.time.LocalDate pickUpDate;
    private java.time.LocalTime pickUpTime;
    private LocalDateTime date;
    private String pickUpComment;
    @ManyToMany
    private List<Category> category;
    @ManyToOne
    private Institution institution;
    @OneToOne
    private User user;

}
