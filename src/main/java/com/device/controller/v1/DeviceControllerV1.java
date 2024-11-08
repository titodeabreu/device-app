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
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    @PostMapping
    public Device createDevice(@RequestBody DeviceInputDTO deviceInputDTO) {

        Device device = Device
                .builder()
                .name(deviceInputDTO.getName())
                .brand(deviceInputDTO.getBrand())
                .creationTime(Instant.now())
                .build();

        return deviceService.save(device);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // missing patch and put

}
