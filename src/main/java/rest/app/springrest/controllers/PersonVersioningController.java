package rest.app.springrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.app.springrest.versioning.model.Name;
import rest.app.springrest.versioning.model.PersonV1;
import rest.app.springrest.versioning.model.PersonV2;

@RestController
public class PersonVersioningController {

    /*URI versioning*/
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Georgi Tangardzhiev");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Georgi", "Tangardzhiev"));
    }

    /*RequestParam versioning*/

    @GetMapping(path = "person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRqParam() {
        return new PersonV1("Georgi Tangardzhiev");
    }

    @GetMapping(path = "person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRqParam() {
        return new PersonV2(new Name("Georgi", "Tangardzhiev"));
    }

    /*Header versioning. This feature with headers is presented in Google Chrome extension called Talend API Tester*/

    @GetMapping(path = "person", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeader() {
        return new PersonV1("Georgi Tangardzhiev");
    }

    @GetMapping(path = "person", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeader() {
        return new PersonV2(new Name("Georgi", "Tangardzhiev"));
    }
}
