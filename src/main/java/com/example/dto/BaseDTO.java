package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RegisterForReflection
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    @JsonIgnore
    private Long id;
    private String extraInfo;
    private String info;

}