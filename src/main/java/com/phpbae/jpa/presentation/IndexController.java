package com.phpbae.jpa.presentation;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class IndexController {


    /**
     * N+1 문제가 발생.
     *
     * @return ResponseBody(JSON)
     */
    @GetMapping(value = "problem", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity problem() {
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
