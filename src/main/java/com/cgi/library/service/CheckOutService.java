package com.cgi.library.service;

import com.cgi.library.entity.CheckOut;
import com.cgi.library.model.CheckOutDTO;
import com.cgi.library.repository.CheckOutRepository;
import com.cgi.library.util.ModelMapperFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckOutService {

    @Autowired
    private CheckOutRepository checkOutRepository;
    private static final int PAGE_SIZE = 2;

    public Page<CheckOutDTO> getCheckOuts(int page, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        ModelMapper modelMapper = ModelMapperFactory.getMapper();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);
        return checkOutRepository.findAll(pageable).map(checkOut -> modelMapper.map(checkOut, CheckOutDTO.class));
    }

    public CheckOutDTO getCheckOut(UUID checkOutId) {
        CheckOut checkOut = checkOutRepository.getOne(checkOutId);
        return ModelMapperFactory.getMapper().map(checkOut, CheckOutDTO.class);
    }

    public void saveCheckOut(CheckOutDTO checkOutDTO) {
        checkOutRepository.save(ModelMapperFactory.getMapper().map(checkOutDTO, CheckOut.class));
    }

    public void deleteCheckOut(UUID checkOutId) {
        checkOutRepository.deleteById(checkOutId);
    }
}
