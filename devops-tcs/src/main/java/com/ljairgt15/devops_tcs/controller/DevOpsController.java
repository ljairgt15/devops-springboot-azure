package com.ljairgt15.devops_tcs.controller;
import com.ljairgt15.devops_tcs.models.dto.DevOpsRequestDTO;
import com.ljairgt15.devops_tcs.models.dto.DevOpsResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/DevOps")
public class DevOpsController {

    @PostMapping
    public DevOpsResponseDTO handlePostRequest(@RequestHeader("X-Parse-REST-API-Key") String apiKey,
                                               @RequestBody DevOpsRequestDTO requestDTO,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {
        String validApiKey = "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c";
        if (!validApiKey.equals(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new DevOpsResponseDTO("Unauthorized");
        }

        // Construir la respuesta usando el DTO
        String to = requestDTO.getTo();
        return new DevOpsResponseDTO("Hello " + to + " your message will be send");
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
    public String handleOtherMethods() {
        return "ERROR";
    }
}
