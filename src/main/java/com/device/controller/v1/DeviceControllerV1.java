package com.device.controller.v1;

import com.device.controller.v1.dto.DeviceInputDTO;
import com.device.controller.v1.dto.DeviceResponseDTO;
import com.device.controller.v1.mapper.DeviceResponseMapper;
import com.device.model.Device;
import com.device.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(DeviceControllerV1.class);
    private final DeviceService deviceService;

    public DeviceControllerV1(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> getAllDevices() {
        logger.info("GET [/api/v1/devices] get all devices");
        List<Device> allDevices = deviceService.getAllDevices();

        List<DeviceResponseDTO> allDevicesResponse = allDevices
                .stream()
                .map(DeviceResponseMapper.INSTANCE::deviceToDeviceResponseDTO)
                .toList();

        return ResponseEntity.ok(allDevicesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> getDeviceById(@PathVariable Long id) {
        logger.info("GET [/api/v1/devices/{}] get device by id", id);
        Device device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(DeviceResponseMapper.INSTANCE.deviceToDeviceResponseDTO(device));
    }

    @PostMapping
    public ResponseEntity<DeviceResponseDTO> createDevice(@RequestBody DeviceInputDTO deviceInputDTO) {
        logger.info("POST [/api/v1/devices] create new device with input={}", deviceInputDTO);
        Device newDevice = Device
                .builder()
                .name(deviceInputDTO.getName())
                .brand(deviceInputDTO.getBrand())
                .creationTime(Instant.now())
                .build();

        Device device = deviceService.save(newDevice);
        return ResponseEntity.ok(DeviceResponseMapper.INSTANCE.deviceToDeviceResponseDTO(device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("DELETE [/api/v1/devices/{}] delete device by id", id);
        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> fullUpdateOnDevice(@PathVariable Long id, @RequestBody DeviceInputDTO deviceInputDTO) {
        logger.info("PUT [/api/v1/devices/{}] full update on device by id with input={}", id, deviceInputDTO);
        Device device = deviceService.getDeviceById(id);

        device.setName(deviceInputDTO.getName());
        device.setBrand(deviceInputDTO.getBrand());
        device = deviceService.save(device);

        return ResponseEntity.ok(DeviceResponseMapper.INSTANCE.deviceToDeviceResponseDTO(device));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> partialUpdateOnDevice(@PathVariable Long id, @RequestBody DeviceInputDTO deviceInputDTO) {
        logger.info("PATCH [/api/v1/devices/{}] partial update on device by id with input={}", id, deviceInputDTO);
        Device device = deviceService.getDeviceById(id);

        if (deviceInputDTO.getName() != null)
            device.setName(deviceInputDTO.getName());

        if (deviceInputDTO.getBrand() != null)
            device.setBrand(deviceInputDTO.getBrand());

        device = deviceService.save(device);

        return ResponseEntity.ok(DeviceResponseMapper.INSTANCE.deviceToDeviceResponseDTO(device));
    }

}
