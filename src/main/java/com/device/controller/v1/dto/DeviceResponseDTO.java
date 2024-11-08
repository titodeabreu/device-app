package com.device.controller.v1.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class DeviceResponseDTO {
    private Long id;
    private String name;
    private String brand;
    private Instant creationTime;
}
