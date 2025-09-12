package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/v1/home")
    public String home() {
        return """
                <h1>HRM System - тестирование API</h1>
                <ul>
                  <li><a href="/api/v1/positions">Все должности</a></li>
                  <li><a href="/api/v1/departments">Все департаменты</a></li>
                  <li><a href="/api/v1/employees">Все сотрудники</a></li>
                  <li><a href="/api/v1/users">Все пользователи</a></li>
                  <li><a href="/swagger-ui/index.html">Swagger UI</a></li>
                </ul>
                """;
    }
}
