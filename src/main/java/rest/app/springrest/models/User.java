package rest.app.springrest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
@Getter@Setter
@AllArgsConstructor
@ToString
public class User {
    private long id;
    @Size(min = 2, message = "Size should be min 2 symbols")
    @NotBlank
    @JsonProperty("user_name")
    private String name;
    @Past(message = "Type present date")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
}
