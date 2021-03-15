package pl.coderslab.charity.entities;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "donation")
@Data
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Min(value = 1)
    @Max(value = 200)
    @NotNull
    private Integer quantity;
    @NotBlank
    private String street;
    @NotBlank
    @Pattern(regexp = "([A-Z]|[ĆŁŃÓŚŻŹ])[AaĄąBbCcĆćDdEeĘęFfGgHhIiJjKkLlŁłMmNnŃńOoÓóPpRrSsŚśTtUuWwYyZzŹźŻż]+")
    private String city;
    @NotBlank
    @Pattern(regexp = "[2-9]\\d{2}-\\d{3}-\\d{3}")
    private String phoneNumber;
    @NotBlank
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String zipCode;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.time.LocalDate pickUpDate;
    @NotNull
    private java.time.LocalTime pickUpTime;
    private String pickUpComment;
    @ManyToMany
    private List<Category> category;
    @ManyToOne
    private Institution institution;
}
