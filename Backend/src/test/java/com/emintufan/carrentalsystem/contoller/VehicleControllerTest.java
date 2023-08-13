package com.emintufan.carrentalsystem.contoller;

import com.emintufan.carrentalsystem.dto.request.CreateVehicleRequest;
import com.emintufan.carrentalsystem.dto.response.CreateVehicleResponse;
import com.emintufan.carrentalsystem.exceptions.EntityExceptions;
import com.emintufan.carrentalsystem.services.abstracts.VehicleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VehicleService vehicleService;

    @Test
    public void testGetVehicleList_Success() throws Exception {
        List<CreateVehicleResponse> response = new ArrayList<>();

        response.add(new CreateVehicleResponse());
        response.add(new CreateVehicleResponse());
        response.add(new CreateVehicleResponse());
        response.add(new CreateVehicleResponse());
        response.add(new CreateVehicleResponse());
        response.add(new CreateVehicleResponse());
        response.add(new CreateVehicleResponse());

        String jsonInput = new ObjectMapper().writeValueAsString(response);

        when(vehicleService.getVehicleList()).thenReturn(response);
        MvcResult result = mockMvc.perform(get("/vehicle/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andReturn();
        String jsonOutPut = result.getResponse().getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutPut);
        List<CreateVehicleResponse> actualResponse = new ObjectMapper().readValue(jsonOutPut, new TypeReference<>() {});
        assertTrue(actualResponse.size() == response.size());
    }

    @Test
    public void testGetVehicleList_Failure() throws Exception {

        when(vehicleService.getVehicleList()).thenReturn(null);
        MvcResult result = mockMvc.perform(get("/vehicle/"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    public void testGetVehicleList_ThrowException() throws Exception {
        when(vehicleService.getVehicleList()).thenThrow(new EntityExceptions("Vehicles Not Found!"));
        mockMvc.perform(get("/vehicle/"))
                .andExpect(status -> assertTrue(status.getResolvedException() instanceof EntityExceptions))
                .andExpect(status().isNotFound())
                .andExpect(status -> assertEquals("Vehicles Not Found!",status.getResolvedException().getMessage()))
                .andReturn();
        verify(vehicleService,times(1)).getVehicleList();
    }
    @Test
    public void testAddVehicle_Success() throws Exception {
        CreateVehicleRequest request = new CreateVehicleRequest();
        request.setName("test");
        request.setAbs(true);
        request.setAirbag(true);
        request.setCapacity("4");
        request.setCruiseControl(true);
        request.setDoors("4");
        request.setCategories("SUV");
        request.setCarClass("C");
        request.setEngineCapacity("4");
        request.setFuelType("Dizel");

        String jsonInput = new ObjectMapper().writeValueAsString(request);

        when(vehicleService.addVehicle(any(CreateVehicleRequest.class))).thenReturn(request);
        MvcResult result = mockMvc.perform(post("/vehicle/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput))
                .andExpect(status().isCreated())
                .andExpect(status -> assertEquals(status.getResponse().getContentAsString(),"Vehicle Added !"))
                .andReturn();
        verify(vehicleService,times(1)).addVehicle(any(CreateVehicleRequest.class));
    }
    @Test
    public void testAddVehicle_Failure() throws Exception {
            CreateVehicleRequest request = new CreateVehicleRequest();
            request.setName("test");
            request.setAbs(true);
            request.setAirbag(true);
            request.setCapacity("4");
            request.setCruiseControl(true);
            request.setDoors("4");
            request.setCategories("SUV");
            request.setCarClass("C");
            request.setEngineCapacity("4");
            request.setFuelType("Dizel");

            String jsonInput = new ObjectMapper().writeValueAsString(request);

            when(vehicleService.addVehicle(any(CreateVehicleRequest.class))).thenReturn(null);
            mockMvc.perform(post("/vehicle/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonInput))
                    .andExpect(status().isBadRequest())
                    .andExpect(status -> assertEquals(status.getResponse().getContentAsString(),"Bad Request!"))
                    .andReturn();
            verify(vehicleService,times(1)).addVehicle(any(CreateVehicleRequest.class));
    }
    @Test
    public void testAddVehicle_ThrowException() throws Exception {
        CreateVehicleRequest request = new CreateVehicleRequest();
        request.setName("test");
        request.setAbs(true);
        request.setAirbag(true);
        request.setCapacity("4");
        request.setCruiseControl(true);
        request.setDoors("4");
        request.setCategories("SUV");
        request.setCarClass("C");
        request.setEngineCapacity("4");
        request.setFuelType("Dizel");

        String jsonInput = new ObjectMapper().writeValueAsString(request);

        when(vehicleService.addVehicle(any(CreateVehicleRequest.class))).thenThrow(new EntityExceptions("Vehicle Was Not Added!"));
        mockMvc.perform(post("/vehicle/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isNotFound())
                .andExpect(status -> assertEquals(status.getResolvedException().getMessage(),"Vehicle Was Not Added!"))
                .andExpect(status -> assertTrue(status.getResolvedException() instanceof  EntityExceptions))
                .andReturn();
        verify(vehicleService,times(1)).addVehicle(any(CreateVehicleRequest.class));
    }
    @Test
    public void testDeleteVehicle_Success() throws Exception {
        int vehicleId = 1;
        when(vehicleService.deleteVehicle(vehicleId)).thenReturn(true);
        mockMvc.perform(delete("/vehicle/delete/{id}",vehicleId))
                .andExpect(status().isOk())
                .andReturn();
        verify(vehicleService,times(1)).deleteVehicle(vehicleId);

    }
    @Test
    public void testDeleteVehicle_Failure() throws Exception {
        int vehicleId = 1;
        when(vehicleService.deleteVehicle(vehicleId)).thenReturn(false);
        mockMvc.perform(delete("/vehicle/delete/{id}",vehicleId))
                .andExpect(status().isNotFound())
                .andReturn();
        verify(vehicleService,times(1)).deleteVehicle(vehicleId);

    }
    @Test
    public void testDeleteVehicle_ThrowException ()throws Exception {
        int vehicleId = 1;
        when(vehicleService.deleteVehicle(vehicleId)).thenThrow(new EntityExceptions("Vehicle Was Not Deleted!"));
        mockMvc.perform(delete("/vehicle/delete/{id}",vehicleId))
                .andExpect(status().isNotFound())
                .andExpect(status -> assertTrue(status.getResolvedException() instanceof  EntityExceptions))
                .andExpect(status -> assertEquals(status.getResolvedException().getMessage(),new EntityExceptions("Vehicle Was Not Deleted!").getMessage()))
                .andReturn();
        verify(vehicleService,times(1)).deleteVehicle(vehicleId);
    }
}
