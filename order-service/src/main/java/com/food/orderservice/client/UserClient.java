package com.food.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service") // this must match the spring.application.name of the user service
public interface UserClient {
    @GetMapping("/api/users/email")
    String getNameByEmail(@RequestParam String email);
}
