package com.learn.jwtrsa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebResponse <T> {
    private String message;
    private T data;
    private List<String> errors;
}
