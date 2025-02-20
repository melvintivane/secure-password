package com.melvin.securepassword.controller;

import com.melvin.securepassword.controller.dto.BodyRequest;
import com.melvin.securepassword.controller.dto.FailureResponse;
import com.melvin.securepassword.service.PasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class ApiController {

   private final PasswordService passwordService;

   public ApiController(PasswordService passwordService) {
      this.passwordService = passwordService;
   }

   @PostMapping(value = "/validate-password")
   public ResponseEntity<FailureResponse> validatePassword(@RequestBody BodyRequest request) {
      List<String> failures = passwordService.validatePassword(request.password());

      if (failures.isEmpty()) {
         return ResponseEntity.noContent().build();
      }

      return ResponseEntity.badRequest().body(new FailureResponse(failures));
   }
}
