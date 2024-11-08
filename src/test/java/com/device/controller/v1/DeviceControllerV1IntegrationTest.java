package com.device.controller.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeviceControllerV1IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateNewDevice() throws Exception {
        //GIVEN
        String bodyRequest = "{\n" +
                "  \"name\": \"Device1\",\n" +
                "  \"brand\": \"TOSHIBA\"\n" +
                "}";
        String expectedName = "Device1";
        String expectedBrand = "TOSHIBA";
        //WHEN
        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequest))
                // THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedName))
                .andExpect(jsonPath("$.brand").value(expectedBrand))
                .andExpect(jsonPath("$.creationTime").exists())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void shouldGetDeviceById() throws Exception {
        //GIVEN
        String bodyRequest = "{\n" +
                "  \"name\": \"Device1\",\n" +
                "  \"brand\": \"TOSHIBA\"\n" +
                "}";

        String expectedName = "Device1";
        String expectedBrand = "TOSHIBA";

        mockMvc.perform(post("/api/v1/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest));

        //WHEN
        mockMvc.perform(get("/api/v1/devices/1"))
                // THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedName))
                .andExpect(jsonPath("$.brand").value(expectedBrand))
                .andExpect(jsonPath("$.creationTime").exists())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void shouldThrowDeviceNotFoundExceptionWhenGetDeviceByInvalidId() throws Exception {
        //WHEN
        mockMvc.perform(get("/api/v1/devices/12345689"))
                // THEN
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldGetAllDevices() throws Exception {
        //GIVEN
        String bodyRequestForDevice1 = "{\n" +
                "  \"name\": \"Device1\",\n" +
                "  \"brand\": \"TOSHIBA\"\n" +
                "}";

        String bodyRequestForDevice2 = "{\n" +
                "  \"name\": \"Device2\",\n" +
                "  \"brand\": \"TOSHIBA\"\n" +
                "}";


        // device 1
        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequestForDevice1))
                .andExpect(status().isOk());

        // device 2
        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequestForDevice2))
                .andExpect(status().isOk());


        //WHEN
        mockMvc.perform(get("/api/v1/devices"))
                // THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void shouldGetAllDevicesWithBrandParam() throws Exception {
        //GIVEN
        String bodyRequestForDevice1 = "{\n" +
                "  \"name\": \"Device1\",\n" +
                "  \"brand\": \"TOSHIBA\"\n" +
                "}";

        String bodyRequestForDevice2 = "{\n" +
                "  \"name\": \"Device2\",\n" +
                "  \"brand\": \"PHILLIPS\"\n" +
                "}";


        // device 1
        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequestForDevice1))
                .andExpect(status().isOk());

        // device 2
        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequestForDevice2))
                .andExpect(status().isOk());


        //WHEN
        mockMvc.perform(get("/api/v1/devices?brand=PHILLIPS"))
                // THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void shouldPatchDeviceById() throws Exception {
        //GIVEN
        String bodyRequestForDevice = "{\n" +
                "  \"name\": \"Device1\",\n" +
                "  \"brand\": \"TOSHIBA\"\n" +
                "}";

        // device 1
        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequestForDevice))
                .andExpect(status().isOk());


        String bodyRequestForDevicePatch = "{\n" +
                "  \"name\": \"Device Patched\",\n" +
                "  \"brand\": null \n" +
                "}";

        String expectedName = "Device Patched";
        String expectedBrand = "TOSHIBA";

        //WHEN
        mockMvc.perform(patch("/api/v1/devices/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequestForDevicePatch))
                // THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedName))
                .andExpect(jsonPath("$.brand").value(expectedBrand))
                .andExpect(jsonPath("$.creationTime").exists())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void shouldPutDeviceById() throws Exception {
        //GIVEN
        String bodyRequestForDevice = "{\n" +
                "  \"name\": \"Device1\",\n" +
                "  \"brand\": \"TOSHIBA\"\n" +
                "}";

        // device 1
        mockMvc.perform(post("/api/v1/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequestForDevice))
                .andExpect(status().isOk());


        String bodyRequestForDevicePatch = "{\n" +
                "  \"name\": \"Device Patched\",\n" +
                "  \"brand\": null \n" +
                "}";

        String expectedName = "Device Patched";

        //WHEN
        mockMvc.perform(put("/api/v1/devices/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyRequestForDevicePatch))
                // THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(expectedName))
                .andExpect(jsonPath("$.brand").isEmpty())
                .andExpect(jsonPath("$.creationTime").exists())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void shouldDeleteDeviceById() throws Exception {
        //GIVEN
        String bodyRequest = "{\n" +
                "  \"name\": \"Device1\",\n" +
                "  \"brand\": \"TOSHIBA\"\n" +
                "}";

        mockMvc.perform(post("/api/v1/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyRequest));

        //WHEN
        mockMvc.perform(delete("/api/v1/devices/1"))
                // THEN
                .andExpect(status().isNoContent());
    }

}
