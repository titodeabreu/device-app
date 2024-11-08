package com.device.service;

import com.device.exception.DeviceNotFoundException;
import com.device.model.Device;
import com.device.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Transactional
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }

    public Device getDeviceByBrand(String brand) {
        Optional<Device> device = deviceRepository.findByBrand(brand);

        if (device.isEmpty())
            throw new DeviceNotFoundException("No device found with brand=" + brand);

        return device.get();
    }

    public Device getDeviceById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);

        if (device.isEmpty())
            throw new DeviceNotFoundException("No device found with id=" + id);

        return device.get();
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

}
