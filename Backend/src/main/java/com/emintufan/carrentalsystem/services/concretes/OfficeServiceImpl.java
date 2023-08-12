package com.emintufan.carrentalsystem.services.concretes;

import com.emintufan.carrentalsystem.dao.OfficeDao;
import com.emintufan.carrentalsystem.dto.request.CreateOfficeRequest;
import com.emintufan.carrentalsystem.dto.response.CreateOfficeResponse;
import com.emintufan.carrentalsystem.entities.Office;
import com.emintufan.carrentalsystem.mapper.ModelMapperManager;
import com.emintufan.carrentalsystem.services.abstracts.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private OfficeDao officeDao;
    private ModelMapperManager modelMapperManager;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, ModelMapperManager modelMapperManager) {
        this.officeDao = officeDao;
        this.modelMapperManager = modelMapperManager;
    }

    @Override
    public CreateOfficeRequest addOffice(CreateOfficeRequest createOfficeRequest) {

        if (createOfficeRequest == null)
            return (null);
        else {
            Office office = modelMapperManager.forRequest().map(createOfficeRequest, Office.class);
            officeDao.save(office);
            return (createOfficeRequest);
        }
    }

    @Override
    public List<CreateOfficeResponse> getOfficeList() {

        List<Office> getOffice = officeDao.findAll();
        if (getOffice.isEmpty())
            return null;
        List<CreateOfficeResponse> list = getOffice.stream()
                .map(x -> modelMapperManager.forResponse()
                        .map(x, CreateOfficeResponse.class)).collect(Collectors.toList());
        return list;
    }
    @Override
    public boolean deleteOffice(int id) {

        Optional<Office> office = officeDao.findById(id);
        if (office.isPresent()) {
            officeDao.delete(office.get());
            return (true);
        }
        return (false);
    }
}
