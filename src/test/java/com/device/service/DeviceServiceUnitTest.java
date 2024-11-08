package com.device.service;

import com.device.exception.DeviceNotFoundException;
import com.device.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceUnitTest {

    @InjectMocks
    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    @Test
    public void shouldThrowDeviceNotFoundExceptionWhenGetDeviceWithInvalidBrand() {
        // GIVEN
        String brand = "invalid";
        when(deviceRepository.findByBrand(brand)).thenReturn(Optional.empty());

        // WHEN
        DeviceNotFoundException exception = assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceByBrand(brand);
        });

        // THEN
        assertEquals("No device found with brand=" + brand, exception.getMessage());
    }

    @Test
    public void shouldThrowDeviceNotFoundExceptionWhenGetDeviceWithInvalidId() {
        // GIVEN
        Long id = 999L;
        when(deviceRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        DeviceNotFoundException exception = assertThrows(DeviceNotFoundException.class, () -> {
            deviceService.getDeviceById(id);
        });

        // THEN
        assertEquals("No device found with id=" + id, exception.getMessage());
    }

}
