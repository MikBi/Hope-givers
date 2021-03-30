package pl.coderslab.charity.entities;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "donation")
@Data
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    @NotBlank
    private String street;
    @Pattern(regexp = "([A-Z]|[ĆŁŃÓŚŻŹ])[AaĄąBbCcĆćDdEeĘęFfGgHhIiJjKkLlŁłMmNnŃńOoÓóPpRrSsŚśTtUuWwYyZzŹźŻż]+")
    private String city;
    @NotBlank
    @Pattern(regexp = "[2-9]\\d{2}-\\d{3}-\\d{3}")
    private String phoneNumber;
    @NotBlank
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.time.LocalDate pickUpDate;
    private java.time.LocalTime pickUpTime;
    private LocalDateTime date;
    private String pickUpComment;
    private Status status;
    private boolean archived;
    @ManyToMany
    private List<Category> categories;
    @ManyToOne
    private Institution institution;
    @OneToOne
    private User user;

}
