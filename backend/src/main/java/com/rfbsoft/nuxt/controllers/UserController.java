package com.rfbsoft.nuxt.controllers;

import com.rfbsoft.nuxt.dto.UserDataDTO;
import com.rfbsoft.nuxt.dto.UserLoginRequest;
import com.rfbsoft.nuxt.dto.UserResponseDTO;
import com.rfbsoft.nuxt.dto.responses.SuccesResponse;
import com.rfbsoft.nuxt.entities.User;
import com.rfbsoft.nuxt.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/signin")
  public ResponseEntity login(//
                              @RequestBody UserLoginRequest request) {
    System.out.println(request);
    return ResponseEntity.ok(new SuccesResponse(userService.signin(request.username, request.password)));
  }

  @PostMapping("/signup")

  public ResponseEntity signup(@RequestBody UserDataDTO user) {
    return ResponseEntity.ok(new SuccesResponse(userService.signup(modelMapper.map(user, User.class))));
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String delete( @PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserResponseDTO search(@PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

}
