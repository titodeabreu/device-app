package com.device.controller.v1;

import com.device.controller.v1.dto.DeviceInputDTO;
import com.device.model.Device;
import com.device.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceControllerV1 {

    private final DeviceService deviceService;

    public DeviceControllerV1(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody DeviceInputDTO deviceInputDTO) {

        Device device = Device
                .builder()
                .name(deviceInputDTO.getName())
                .brand(deviceInputDTO.getBrand())
                .creationTime(Instant.now())
                .build();

        return ResponseEntity.ok(deviceService.save(device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> fullUpdateOnDevice(@PathVariable Long id, @RequestBody DeviceInputDTO deviceInputDTO) {
        Device device = deviceService.getDeviceById(id);

        device.setName(deviceInputDTO.getName());
        device.setBrand(deviceInputDTO.getBrand());

        return ResponseEntity.ok(deviceService.save(device));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Device> partialUpdateOnDevice(@PathVariable Long id, @RequestBody DeviceInputDTO deviceInputDTO) {
        Device device = deviceService.getDeviceById(id);

        if (deviceInputDTO.getName() != null)
            device.setName(deviceInputDTO.getName());

        if (deviceInputDTO.getBrand() != null)
            device.setBrand(deviceInputDTO.getBrand());

        return ResponseEntity.ok(deviceService.save(device));
    }

}
