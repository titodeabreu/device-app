package com.device.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class DeviceResponseDTO {
    private Long id;
    private String name;
    private String brand;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Instant creationTime;
}
