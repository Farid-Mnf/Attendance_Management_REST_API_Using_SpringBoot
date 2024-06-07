package com.farid.attendancesystem.controller.requestdtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
