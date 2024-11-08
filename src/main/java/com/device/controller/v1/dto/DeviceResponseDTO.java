package com.device.controller.v1.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class DeviceResponseDTO {
    private Long id;
    private String name;
    private String brand;
    private Instant creationTime;
}
