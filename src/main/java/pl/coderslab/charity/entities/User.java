package pl.coderslab.charity.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(min = 3, max = 15, message = "Twoja nazwa użytkownika jest za krótka bądź za długa. Minimalna ilość znaków to trzy, natomiast maksymalna to 15")
    @Pattern(regexp = "[A-z]{3}[A-z0-9.\\-_]{0,12}" ,message = "Twoja nazwa użytkownika jest nieprawidłowa. Powinna się składać z minimum trzech liter oraz maksymalnie z dodatkowych 12 liter/cyfr bądź znaków takich jak kropka,myślnik bądź znaku podkreślenia")
    private String username;
    @NotBlank
    @Pattern(regexp = "[AaĄąBbCcĆćDdEeĘęFfGgHhIiJjKkLlŁłMmNnŃńOoÓóPpRrSsŚśTtUuWwYyZzŹźŻż]{3,13}", message = "Wpisz imię ponownie")
    private String name;
    @NotBlank
    @Pattern(regexp = "[AaĄąBbCcĆćDdEeĘęFfGgHhIiJjKkLlŁłMmNnŃńOoÓóPpRrSsŚśTtUuWwYyZzŹźŻż]{3,27}", message = "Wpisz nazwisko ponownie")
    private String surname;
    @NotBlank
    @Email(message = "Wpisz email ponownie")
    private String email;
//    @NotBlank
//    @Size(min = 6, max = 30, message = "Twoje hasło jest za krótkie bądź za długie. Minimalna ilość znaków to sześć, natomiast maksymalna to trzydzieści")
//    @Pattern(regexp = ".{6,30}")
    private String password;
    private Integer adminId;
    @ManyToOne
    private Role role;
}
