package com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLoginLogDTO {

    private Long id;
    private String email;
    private LocalDateTime fecha;
    private Boolean success;
}
