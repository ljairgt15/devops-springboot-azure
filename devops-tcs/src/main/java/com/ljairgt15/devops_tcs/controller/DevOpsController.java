package com.ljairgt15.devops_tcs.controller;

import com.ljairgt15.devops_tcs.models.dto.DevOpsRequestDTO;
import com.ljairgt15.devops_tcs.models.dto.DevOpsResponseDTO;
import com.ljairgt15.devops_tcs.util.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/DevOps")
public class DevOpsController {
    @Autowired
    private JWTGenerator jwtGenerator;


    @PostMapping
    public DevOpsResponseDTO handlePostRequest(@RequestBody DevOpsRequestDTO requestDTO) {

        String to = requestDTO.getTo();
        return new DevOpsResponseDTO("Hello " + to + " your message will be sent");
    }
    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generateJwt() {
        String token = jwtGenerator.generateToken();
        return ResponseEntity.ok(Map.of("jwt", token));
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public String handleOtherMethods() {
        return "ERROR";
    }
}
