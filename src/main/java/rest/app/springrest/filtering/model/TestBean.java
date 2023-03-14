package rest.app.springrest.filtering.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//static filtering
//@JsonIgnoreProperties({"description", "password"})

@JsonFilter("TestBeanFilter") // this is for dynamic filtering which is happening in the FilteringController
public class TestBean {
    private String username;

    //static filtering @JsonIgnore
    //@JsonIgnore
    private String password;
    private String description;

    //static filtering annotations are used only in the class/Bean, nothing in Controller
}
