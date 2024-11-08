package com.device.controller.v1.mapper;

import com.device.controller.v1.dto.DeviceResponseDTO;
import com.device.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceResponseMapper {
    DeviceResponseMapper INSTANCE = Mappers.getMapper(DeviceResponseMapper.class);

    DeviceResponseDTO deviceToDeviceResponseDTO(Device device);
}
