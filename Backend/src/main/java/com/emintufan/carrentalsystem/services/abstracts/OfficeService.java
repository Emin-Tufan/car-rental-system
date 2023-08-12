package com.emintufan.carrentalsystem.services.abstracts;


import com.emintufan.carrentalsystem.dto.request.CreateOfficeRequest;
import com.emintufan.carrentalsystem.dto.response.CreateOfficeResponse;
import com.emintufan.carrentalsystem.entities.Office;

import java.util.List;

public interface OfficeService {

    CreateOfficeRequest addOffice(CreateOfficeRequest createOfficeRequest);
    List<CreateOfficeResponse> getOfficeList();

    boolean deleteOffice(int id);
}
