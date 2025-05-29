package com.microservice.course.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {


    private String name;
    private String lastName;
    private String email;
    private Long courseId;
}
