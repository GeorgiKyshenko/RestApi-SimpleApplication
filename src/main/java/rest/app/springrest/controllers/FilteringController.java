package rest.app.springrest.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.app.springrest.filtering.model.TestBean;

import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        TestBean testBean = new TestBean("username", "password", "description");
        //dynamic filtering of properties
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(testBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("username", "description");
        FilterProvider filters = new SimpleFilterProvider().addFilter("TestBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {

        List<TestBean> testBeans = List.of(new TestBean("username", "password", "description"),
                new TestBean("username1", "password2", "description2"));

        //dynamic filtering of properties
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(testBeans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("username");
        FilterProvider filters = new SimpleFilterProvider().addFilter("TestBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
