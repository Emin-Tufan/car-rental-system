package com.emintufan.carrentalsystem.contoller;

import com.emintufan.carrentalsystem.dto.request.CreateReservationRequest;
import static org.assertj.core.api.Assertions.assertThat;
import com.emintufan.carrentalsystem.dto.response.CreateReservationResponse;
import com.emintufan.carrentalsystem.services.abstracts.ReservationService;
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

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReservationService reservationService;

    @Test
    public void testGetUserReservations_Success() throws Exception {
        List<CreateReservationResponse> list = new ArrayList<>();
        list.add(new CreateReservationResponse());
        list.add(new CreateReservationResponse());
        list.add(new CreateReservationResponse());
        list.add(new CreateReservationResponse());
        list.add(new CreateReservationResponse());

        when(reservationService.getReservations()).thenReturn(list);

        String jsonInput = new ObjectMapper().writeValueAsString(list);

        MvcResult result = mockMvc.perform(get("/reservation/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isOk())
                .andReturn();
        String jsonOutPut = result.getResponse().getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutPut);
        verify(reservationService,times(1)).getReservations();
    }
    @Test
    public void testGetUserReservations_Failure() throws Exception {
        when(reservationService.getReservations()).thenReturn(null);

        mockMvc.perform(get("/reservation/"))
                        .andExpect(status().isBadRequest())
                                        .andReturn();
        verify(reservationService, times(1)).getReservations();
    }

    @Test
    public void testAddReservation_Success() throws Exception {
        int vehicleId = 1;
        int userId = 2;
        CreateReservationRequest request = new CreateReservationRequest();

        when(reservationService.addReservation(vehicleId, userId, request)).thenReturn(true);

        mockMvc.perform(post("/reservation/add/{vehicleId}/{userId}", vehicleId, userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Reservation added!"));

        verify(reservationService, times(1)).addReservation(vehicleId, userId, request);
    }

    @Test
    public void testAddReservation_Failure() throws Exception {
        int vehicleId = 1;
        int userId = 2;
        CreateReservationRequest request = new CreateReservationRequest();

        when(reservationService.addReservation(vehicleId, userId, request)).thenReturn(false);

        mockMvc.perform(post("/reservation/add/{vehicleId}/{userId}", vehicleId, userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));

        verify(reservationService, times(1)).addReservation(vehicleId, userId, request);
    }

    @Test
    public void testDeleteReservation_Success() throws Exception {
        int reservationId = 1;

        when(reservationService.deleteReservation(reservationId)).thenReturn(true);

        mockMvc.perform(delete("/reservation/delete/{id}", reservationId))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(reservationService, times(1)).deleteReservation(reservationId);
    }

    @Test
    public void testDeleteReservation_Failure() throws Exception {
        int reservationId = 1;

        when(reservationService.deleteReservation(reservationId)).thenReturn(false);

        mockMvc.perform(delete("/reservation/delete/{id}", reservationId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("false"));

        verify(reservationService, times(1)).deleteReservation(reservationId);
    }
}
