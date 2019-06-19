package com.mock.project1.DemoMock;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExposingAPI {

    @GetMapping(value = "api/v1/{tenantID}")
    public String exposeGetAPI(@PathVariable(value = "tenantID") final String id) {
        return "result here is : " + id;
    }

    // postman call would look like: http://localhost:8080/api/v1?count=12&size=2
    @GetMapping(value = "api/v1")
    public String exposeGetAPI2(@RequestParam(value = "count") final String number, @RequestParam(value = "size") final String length) {
        return "result here is : " + number + length;
    }

    // this takes json as body in postman and then maps what all json fields and values are available as instance variables in User class.
    // if consumes=application/xml, then to map input with a User type class, then we need to add dependency for jackson-dataformat-xml in pom
    @PostMapping(path = "api/v1", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public String exposePostAPI(@RequestBody User user)
    {
        String uname = user.username;
        String pwd = user.password;
        return uname + pwd;
    }
    /*
     * this also takes json and returns the same json
     * 
     * @PostMapping(path = "api/v1", consumes = "application/json") public String exposePostAPI(@RequestBody String input) { return input; }
     */

    @PutMapping(path = "api/v1/{tenantID}", consumes = "application/json")
    public String exposePutAPI(@PathVariable(value = "tenantID") String id, @RequestBody User user) {
        String uname = user.username;
        String pwd = user.password;
        return uname + pwd + id;
    }

    @DeleteMapping(value = "/api/v1/{tenantID}")
    public String exposeDeleteAPI(@PathVariable(value = "tenantID") String id) {
        return id;
    }

}
