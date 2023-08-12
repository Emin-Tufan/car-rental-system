package com.emintufan.carrentalsystem.contoller;

import com.emintufan.carrentalsystem.dto.request.CreateOfficeRequest;
import com.emintufan.carrentalsystem.dto.response.CreateOfficeResponse;
import com.emintufan.carrentalsystem.exceptions.EntityExceptions;
import com.emintufan.carrentalsystem.services.abstracts.OfficeService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfficeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OfficeService officeService;

    @Test
    public void testGetOfficeList_Success() throws Exception {
        List<CreateOfficeResponse> response = new ArrayList<>();

        response.add(new CreateOfficeResponse());
        response.add(new CreateOfficeResponse());
        response.add(new CreateOfficeResponse());
        response.add(new CreateOfficeResponse());
        response.add(new CreateOfficeResponse());
        response.add(new CreateOfficeResponse());
        response.add(new CreateOfficeResponse());

        String jsonInput = new ObjectMapper().writeValueAsString(response);
        when(officeService.getOfficeList()).thenReturn(response);

        MvcResult result = mockMvc.perform(get("/office/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andReturn();
        String jsonOutPut = result.getResponse().getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutPut);
        List<CreateOfficeResponse> actualResponse = new ObjectMapper().readValue(jsonOutPut, new TypeReference<>() {
        });
        assertTrue(actualResponse.size() == response.size());
    }

    @Test
    public void testGetOfficeList_Failure() throws Exception {

        when(officeService.getOfficeList()).thenReturn(null);
        mockMvc.perform(get("/office/"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testGetOfficeList_ThrowException() throws Exception {
        when(officeService.getOfficeList()).thenThrow(new EntityExceptions("Office Not Found!"));
        mockMvc.perform(get("/office/"))
                .andExpect(status -> assertTrue(status.getResolvedException() instanceof EntityExceptions))
                .andExpect(status().isNotFound())
                .andExpect(status -> assertEquals("Office Not Found!", status.getResolvedException().getMessage()))
                .andReturn();
        verify(officeService, times(1)).getOfficeList();
    }

    @Test
    public void testAddOffice_Success() throws Exception {
        CreateOfficeRequest request = new CreateOfficeRequest();
        request.setName("test");
        request.setAddress("testAddress");
        request.setEmail("test@gmail.com");

        String jsonInput = new ObjectMapper().writeValueAsString(request);

        when(officeService.addOffice(any(CreateOfficeRequest.class))).thenReturn(request);
        mockMvc.perform(post("/office/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isCreated())
                .andExpect(status -> assertEquals(status.getResponse().getContentAsString(), "Office Added !"))
                .andReturn();
        verify(officeService, times(1)).addOffice(any(CreateOfficeRequest.class));
    }

    @Test
    public void testAddOffice_Failure() throws Exception {
        CreateOfficeRequest request = new CreateOfficeRequest();
        request.setName("test");
        request.setAddress("testAddress");
        request.setEmail("test@gmail.com");

        String jsonInput = new ObjectMapper().writeValueAsString(request);

        when(officeService.addOffice(any(CreateOfficeRequest.class))).thenReturn(null);
        mockMvc.perform(post("/office/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isBadRequest())
                .andReturn();
        verify(officeService, times(1)).addOffice(any(CreateOfficeRequest.class));
    }

    @Test
    public void testAddOffice_ThrowException() throws Exception {
        CreateOfficeRequest request = new CreateOfficeRequest();
        request.setName("test");
        request.setAddress("testAddress");
        request.setEmail("test@gmail.com");

        String jsonInput = new ObjectMapper().writeValueAsString(request);

        when(officeService.addOffice(any(CreateOfficeRequest.class))).thenThrow(new EntityExceptions("Vehicle Was Not Added!"));
        mockMvc.perform(post("/office/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isNotFound())
                .andExpect(status -> assertEquals(status.getResolvedException().getMessage(), "Office Was Not Added !"))
                .andExpect(status -> assertTrue(status.getResolvedException() instanceof EntityExceptions))
                .andReturn();
        verify(officeService, times(1)).addOffice(any(CreateOfficeRequest.class));
    }
    @Test
    public void testDeleteOffice_Success() throws Exception {
        int vehicleId = 1;
        when(officeService.deleteOffice(vehicleId)).thenReturn(true);
        mockMvc.perform(delete("/office/delete/{id}",vehicleId))
                .andExpect(status().isOk())
                .andReturn();
        verify(officeService,times(1)).deleteOffice(vehicleId);

    }
    public void testDeleteOffice_Failure() throws Exception {
        int vehicleId = 1;
        when(officeService.deleteOffice(vehicleId)).thenReturn(false);
        mockMvc.perform(delete("/office/delete/{id}",vehicleId))
                .andExpect(status().isBadRequest())
                .andReturn();
        verify(officeService,times(1)).deleteOffice(vehicleId);

    }
    public void testDeleteOffice_ThrowException ()throws Exception {
        int vehicleId = 1;
        when(officeService.deleteOffice(vehicleId)).thenThrow(new EntityExceptions("Office Was Not Deleted!"));
        mockMvc.perform(delete("/office/delete/{id}",vehicleId))
                .andExpect(status().isNotFound())
                .andExpect(status -> assertTrue(status.getResolvedException() instanceof  EntityExceptions))
                .andExpect(status -> assertEquals(status.getResolvedException().getMessage(),new EntityExceptions("Office Was Not Deleted!")))
                .andReturn();
        verify(officeService,times(1)).deleteOffice(vehicleId);
    }
}