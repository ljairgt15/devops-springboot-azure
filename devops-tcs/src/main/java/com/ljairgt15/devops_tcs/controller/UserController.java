package com.ljairgt15.devops_tcs.controller;

import com.ljairgt15.devops_tcs.models.User;
import com.ljairgt15.devops_tcs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    @PostMapping("/buscarCorreo")
    public ResponseEntity<List<User>> getCorreo(@RequestParam String correo) {
        List<User> users = userRepository.findUsuariosByCorreo(correo);
        return new ResponseEntity<>(users, OK);
    }



}
