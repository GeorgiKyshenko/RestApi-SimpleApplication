package rest.app.springrest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "test_user_jpa")
public class User2JPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2, message = "Size should be min 2 symbols")
    @NotBlank
    @JsonProperty("user_name")
    private String name;
    @Past(message = "Type present date")
    @JsonProperty("birth_date") // if this stays like this, in Postman or wherever we POST the body should contain this property name !!! birth_date and not birthDate
    private LocalDate birthDate;
}
