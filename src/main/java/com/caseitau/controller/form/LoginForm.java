package com.caseitau.controller.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginForm {

     private String username;
     private String pass;

     public UsernamePasswordAuthenticationToken converter() {
          return new UsernamePasswordAuthenticationToken(username, pass);
     }
}
