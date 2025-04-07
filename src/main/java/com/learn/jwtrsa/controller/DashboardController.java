package com.learn.jwtrsa.controller;

import com.learn.jwtrsa.dto.WebResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DashboardController {

    @GetMapping(
            path = "/home",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<Void>home() {
        return WebResponse.<Void>builder()
                .message("this is dashboard")
                .build();
    }

}
