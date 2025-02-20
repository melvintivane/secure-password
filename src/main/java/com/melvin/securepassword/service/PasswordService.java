package com.melvin.securepassword.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordService {

   public List<String> validatePassword(String password) {
      List<String> failures = new ArrayList<>();

      checkPasswordNotNull(password, failures);

      if (password != null) {
         validateLength(password, failures);
         validateLowercase(password, failures);
         validateUppercase(password, failures);
         validateNumber(password, failures);
         validateSpecialCharacter(password, failures);
      }

      return failures;
   }

   private void checkPasswordNotNull(String password, List<String> failures) {
      if (password == null) {
         failures.add("A senha não pode ser vazia.");
      }
   }

   private void validateLength(String password, List<String> failures) {
      if (password.length() < 8) {
         failures.add("A senha deve conter pelo menos 08 caracteres.");
      }
   }

   private void validateLowercase(String password, List<String> failures) {
      if (!Pattern.matches(".*[a-z].*", password)) {
         failures.add("A senha deve conter pelo menos uma letra minúscula.");
      }
   }

   private void validateUppercase(String password, List<String> failures) {
      if (!Pattern.matches(".*[A-Z].*", password)) {
         failures.add("A senha deve conter pelo menos uma letra maiúscula.");
      }
   }

   private void validateNumber(String password, List<String> failures) {
      if (!Pattern.matches(".*[0-9].*", password)) {
         failures.add("A senha deve conter pelo menos um dígito numérico.");
      }
   }

   private void validateSpecialCharacter(String password, List<String> failures) {
      if (!Pattern.matches(".*\\W.*", password)) {
         failures.add("A senha deve conter pelo menos um caractere especial (e.g, !@#$%).");
      }
   }
}
