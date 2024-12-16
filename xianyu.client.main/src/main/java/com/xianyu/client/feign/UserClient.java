package com.xianyu.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "xianyu-server",url = "192.168.163.1:8080")
public interface UserClient {

   @PostMapping({"/login"})
   void login();

   @GetMapping({"/logout"})
   void logout();
}