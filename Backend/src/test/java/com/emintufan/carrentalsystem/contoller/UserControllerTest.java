package com.emintufan.carrentalsystem.contoller;

import com.emintufan.carrentalsystem.dto.request.CreateLoginRequest;
import com.emintufan.carrentalsystem.dto.request.CreateUserRequest;
import com.emintufan.carrentalsystem.dto.response.AuthenticationResponse;
import static org.assertj.core.api.Assertions.assertThat;
import com.emintufan.carrentalsystem.dto.response.CreateLast5RegisteredUserResponse;
import com.emintufan.carrentalsystem.dto.response.CreateUserResponse;
import com.emintufan.carrentalsystem.exceptions.EntityExceptions;
import com.emintufan.carrentalsystem.services.abstracts.EmailService;
import com.emintufan.carrentalsystem.services.abstracts.UserService;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private EmailService emailService;

    @Test
    public void testCustomerRegister_Success() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Emin");
        request.setSurName("Tufan");
        request.setIdentityNo("1234567890");
        request.setPhone("5555555555");
        request.setEmail("test@gmail.com");
        request.setAddress("Test Address");
        request.setPassword("testpassword");
        request.setGender("Male");


        String mockToken = UUID.randomUUID().toString();
        AuthenticationResponse response = AuthenticationResponse.builder().token(mockToken).build();

        when(userService.registerCustomer(any(CreateUserRequest.class))).thenReturn(response);

        MvcResult result = mockMvc.perform(post("/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        AuthenticationResponse actualResponse = new ObjectMapper().readValue(responseContent, AuthenticationResponse.class);
        assertEquals(mockToken, actualResponse.getToken());
        verify(userService, times(1)).registerCustomer(any(CreateUserRequest.class));
    }

    @Test
    public void testCustomerRegister_UserAlreadyExists() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Emin");
        request.setSurName("Tufan");
        request.setIdentityNo("1234567890");
        request.setPhone("5555555555");
        request.setEmail("test@gmail.com");
        request.setAddress("Test Address");
        request.setPassword("testpassword");
        request.setGender("Male");


        when(userService.registerCustomer(any(CreateUserRequest.class))).thenReturn(null);

        mockMvc.perform(post("/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityExceptions))
                .andExpect(result -> assertEquals("User Already Exists!", result.getResolvedException().getMessage()));
        verify(userService, times(1)).registerCustomer(any(CreateUserRequest.class));
    }
    @Test
    public void testCustomerLogin_Success() throws Exception {
        CreateLoginRequest loginRequest = new CreateLoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("testpassword");
        loginRequest.setAuthorities("ADMIN");

        String mockToken = UUID.randomUUID().toString();
        AuthenticationResponse response = AuthenticationResponse.builder().token(mockToken).build();
        when(userService.authenticationResponse(any(CreateLoginRequest.class))).thenReturn(response);

        MvcResult result = mockMvc.perform(post("/customer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        AuthenticationResponse actualResponse = new ObjectMapper().readValue(responseContent,AuthenticationResponse.class);
        assertEquals(mockToken,actualResponse.getToken());

        verify(userService,times(1)).authenticationResponse(any(CreateLoginRequest.class));

    }
    @Test
    public void testCustomerLogin_Fail() throws Exception {
        CreateLoginRequest loginRequest = new CreateLoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("testpassword");
        loginRequest.setAuthorities("ADMIN");

        when(userService.authenticationResponse(any(CreateLoginRequest.class))).thenThrow(new EntityExceptions("Invalid Username Or Password!"));
        mockMvc.perform(post("/customer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status -> assertEquals(status.getResolvedException().getMessage(),"Invalid Username or Password"));
        verify(userService,times(1)).authenticationResponse(any(CreateLoginRequest.class));
    }
    @Test
    public void testLoginAdmin_Success() throws Exception {
        CreateLoginRequest loginRequest = new CreateLoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("testpassword");
        loginRequest.setAuthorities("ADMIN");

        String mockToken = UUID.randomUUID().toString();
        AuthenticationResponse authToken = AuthenticationResponse.builder().token(mockToken).build();
        when(userService.authenticationResponseForAdmin(any(CreateLoginRequest.class))).thenReturn(authToken);

        MvcResult result = mockMvc.perform(post("/customer/login-admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        AuthenticationResponse response = new ObjectMapper().readValue(responseContent,AuthenticationResponse.class);

        assertEquals(response.getToken(),mockToken);
        verify(userService,times(1)).authenticationResponseForAdmin(any(CreateLoginRequest.class));

    }
    @Test
    public void testLoginAdmin_Failure() throws Exception {
        CreateLoginRequest loginRequest = new CreateLoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("testpassword");
        loginRequest.setAuthorities("ADMIN");

        when(userService.authenticationResponseForAdmin(any(CreateLoginRequest.class))).thenReturn(null);
         mockMvc.perform(post("/customer/login-admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andReturn();
        verify(userService,times(1)).authenticationResponseForAdmin(any(CreateLoginRequest.class));
    }
    @Test
    public void testCustomerUpdate_Success() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Emin");
        request.setSurName("Tufan");
        request.setIdentityNo("1234567890");
        request.setPhone("5555555555");
        request.setEmail("test@gmail.com");
        request.setAddress("Test Address");
        request.setPassword("testpassword");
        request.setGender("Male");

        when(userService.updateCustomerProfile(anyInt(), any(CreateUserRequest.class))).thenReturn(true);

        mockMvc.perform(put("/customer/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(status -> assertEquals(status.getResponse().getContentAsString(),"Customer profile successfully updated."))
                .andReturn();
        verify(userService,times(1)).updateCustomerProfile(anyInt(),any(CreateUserRequest.class));
    }
    @Test
    public void testCustomerUpdate_Failure() throws Exception {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Emin");
        request.setSurName("Tufan");
        request.setIdentityNo("1234567890");
        request.setPhone("5555555555");
        request.setEmail("test@gmail.com");
        request.setAddress("Test Address");
        request.setPassword("testpassword");
        request.setGender("Male");

        when(userService.updateCustomerProfile(anyInt(),any(CreateUserRequest.class))).thenReturn(false);

         mockMvc.perform(put("/customer/update/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(status -> assertEquals(status.getResponse().getContentAsString(),"Customer not found with ID: 1"))
                .andReturn();

        verify(userService,times(1)).updateCustomerProfile(anyInt(),any(CreateUserRequest.class));

    }
    @Test
    public void testGetUser_Success() throws Exception {
        CreateUserResponse user = new CreateUserResponse();
        user.setName("Emin");
        user.setSurName("Tufan");
        user.setIdentityNo("1234567890");
        user.setPhone("5555555555");
        user.setEmail("test@gmail.com");
        user.setAddress("Test Address");
        user.setGender("Male");
        user.setId(1);
        user.setType("ADMIN");

        String jsonInput = new ObjectMapper().writeValueAsString(user);

        when(userService.findUserById(1)).thenReturn(user);

        MvcResult result = mockMvc.perform(get("/customer/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInput))
                .andExpect(status().isOk())
                .andReturn();

        CreateUserResponse actualResponse = new ObjectMapper().readValue(result.getResponse()
                .getContentAsString(),CreateUserResponse.class);
        String jsonOutPut = result.getResponse().getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutPut);
        assertEquals(actualResponse,user);
        verify(userService,times(1)).findUserById(anyInt());
    }

    @Test
    public void testGetUser_Failure() throws Exception {

        when(userService.findUserById(anyInt())).thenReturn(null);
         mockMvc.perform(get("/customer/{id}",1))
                .andExpect(status().isNotFound())
                .andReturn();

        verify(userService,times(1)).findUserById(anyInt());
    }
    @Test
    public void testGetUserList_Success() throws Exception {
        List<CreateUserResponse> list = new ArrayList<>();
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());

        when(userService.getAllUsers()).thenReturn(list);

        MvcResult result = mockMvc.perform(get("/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(list)))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        List<CreateUserResponse> responseList = new ObjectMapper().readValue(responseContent, new TypeReference<>() {});

        assertEquals(list.size(), responseList.size());
        assertEquals(list, responseList);
        verify(userService,times(1)).getAllUsers();
    }
    @Test
    public void testGetUserList_Failure() throws Exception {
        List<CreateUserResponse> list = new ArrayList<>();
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());

        when(userService.getAllUsers()).thenReturn(null);

         mockMvc.perform(get("/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(list)))
                .andExpect(status().isNotFound())
                .andReturn();
        verify(userService,times(1)).getAllUsers();
    }
    @Test
    public void testUserCount_Success() throws Exception {
        List<CreateUserResponse> list = new ArrayList<>();
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());
        list.add(new CreateUserResponse());

        when(userService.count()).thenReturn((long) list.size());
        MvcResult result = mockMvc.perform(get("/customer/users-count")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(list)))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        Long listCount = new ObjectMapper().readValue(responseContent,  Long.class);
        assertEquals(list.size(), listCount);
        verify(userService,times(1)).count();
    }
    @Test
    public void testUsersCount_Failure() throws Exception {
        when(userService.count()).thenThrow(new EntityExceptions("Users Not Found !"));

        mockMvc.perform(get("/customer/users-count")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityExceptions))
                .andExpect(result -> assertEquals("Users Not Found !", result.getResolvedException().getMessage()));

        verify(userService, times(1)).count();
    }
    @Test
    public void testGetLast5User_Success() throws Exception {
        List<CreateLast5RegisteredUserResponse> list = new ArrayList();

        list.add(CreateLast5RegisteredUserResponse.builder().name("test1").build());
        list.add(CreateLast5RegisteredUserResponse.builder().name("test2").build());
        list.add(CreateLast5RegisteredUserResponse.builder().name("test3").build());
        list.add(CreateLast5RegisteredUserResponse.builder().name("test4").build());
        list.add(CreateLast5RegisteredUserResponse.builder().name("test5").build());

        when(userService.getLast5Users()).thenReturn(list);

        MvcResult result = mockMvc.perform(get("/customer/users-last")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(list)))
                .andExpect(status().isOk())
                .andReturn();
        String contentResponse = result.getResponse().getContentAsString();
        List<CreateLast5RegisteredUserResponse> actualList = new ObjectMapper().readValue(contentResponse, new TypeReference<>() {});
        assertAll(
                () -> assertEquals("test1", actualList.get(0).getName()),
                () -> assertEquals("test2", actualList.get(1).getName()),
                () -> assertEquals("test3", actualList.get(2).getName()),
                () -> assertEquals("test4", actualList.get(3).getName()),
                () -> assertEquals("test5", actualList.get(4).getName())
        );

        verify(userService,times(1)).getLast5Users();
    }
    @Test
    public void testGetLast5User_Failure() throws Exception
    {
        when(userService.getLast5Users()).thenReturn(null);

        mockMvc.perform(get("/customer/users-last")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                        .andReturn();
        verify(userService, times(1)).getLast5Users();
    }
    @Test
    public void testForgotPassword_Success() throws Exception {
        String email = "test@example.com";
        String expectedResponse = "Password reset link sent successfully to " + email;

        when(emailService.forgotPassword(email)).thenReturn(expectedResponse);

        mockMvc.perform(post("/customer/forgot-password")
                        .param("email", email))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
        verify(emailService,times(1)).forgotPassword(anyString());
    }

    @Test
    public void testForgotPassword_Failure() throws Exception {
        String email = "invalid@example.com";
        String errorMessage = "User with email " + email + " not found";

        when(emailService.forgotPassword(email)).thenThrow(new EntityExceptions(errorMessage));

        mockMvc.perform(post("/customer/forgot-password")
                        .param("email", email))
                .andExpect(status().isNotFound())
                .andExpect(status -> assertTrue(status.getResolvedException() instanceof EntityExceptions))
                .andReturn();
        verify(emailService,times(1)).forgotPassword(anyString());
    }

    @Test
    public void testDeleteUser_Success() throws Exception {

        when(userService.deleteUser(1)).thenReturn(true);
        MvcResult result = mockMvc.perform(delete("/customer/delete/{id}",1))
                .andExpect(status().isOk())
                .andReturn();
        verify(userService,times(1)).deleteUser(anyInt());
    }
    @Test
    public void testDeleteUser_Failure() throws Exception {

        when(userService.deleteUser(1)).thenReturn(false);
        mockMvc.perform(delete("/customer/delete/{id}",1))
                .andExpect(status().isBadRequest())
                .andReturn();
        verify(userService,times(1)).deleteUser(anyInt());
    }
}

