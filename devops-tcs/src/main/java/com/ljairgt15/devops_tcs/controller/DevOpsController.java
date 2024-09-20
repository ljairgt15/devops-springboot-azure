package com.ljairgt15.devops_tcs.controller;

import com.ljairgt15.devops_tcs.models.dto.DevOpsRequestDTO;
import com.ljairgt15.devops_tcs.models.dto.DevOpsResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DevOps")
public class DevOpsController {

    @PostMapping
    public DevOpsResponseDTO handlePostRequest(@RequestBody DevOpsRequestDTO requestDTO) {

        String to = requestDTO.getTo();
        return new DevOpsResponseDTO("Hello " + to + " your message will be sent");
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public String handleOtherMethods() {
        return "ERROR";
    }
}
