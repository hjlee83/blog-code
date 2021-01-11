package com.example.code.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@SpringBootApplication
public class ValidationApplication {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    public static void main(String[] args) {
        SpringApplication.run(ValidationApplication.class, args);
    }

    @GetMapping("/hello")
    public String helloName(@Valid HelloRequest helloRequest) {

        return helloRequest.getMessage();
    }

    public static class HelloRequest {

        @NotEmpty(message = "이름은 빈값일 수 없습니다.")
        private List<String> name;

        public String getMessage() {
            return String.format("hello %s", name);
        }
    }
}
