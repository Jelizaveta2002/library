package com.cgi.library.service;

import com.cgi.library.entity.Book;
import com.cgi.library.entity.CheckOut;
import com.cgi.library.model.BookDTO;
import com.cgi.library.model.CheckOutDTO;
import com.cgi.library.repository.CheckOutRepository;
import com.cgi.library.util.ModelMapperFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
        checkOutDTO.setId(UUID.randomUUID());
        checkOutDTO.setCheckedOutDate(LocalDate.now());
        LocalDate startDate = LocalDate.of(2023, 4, 1); // Start date
        LocalDate endDate = LocalDate.of(2023, 7, 31); // End date

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        long randomDay = ThreadLocalRandom.current().nextLong(days + 1);

        LocalDate randomDate = startDate.plusDays(randomDay);
        checkOutDTO.setDueDate(randomDate);
        checkOutRepository.save(ModelMapperFactory.getMapper().map(checkOutDTO, CheckOut.class));
    }

    public void deleteCheckOut(UUID checkOutId) {
        checkOutRepository.deleteById(checkOutId);
    }

    public void setReturnedDate(UUID checkOutId) {
        CheckOut checkOut = checkOutRepository.getOne(checkOutId);
        checkOut.setReturnedDate(LocalDate.now());
        checkOutRepository.save(checkOut);
    }

    public List<CheckOutDTO> getLateCheckOuts(int page) {
        List<CheckOutDTO> checkOutOverDueDTO = new ArrayList<>();
        ModelMapper modelMapper = ModelMapperFactory.getMapper();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        List<CheckOut> checkOutList = checkOutRepository.findOverdueBooks(LocalDate.now(), pageable);
        for (CheckOut checkOut: checkOutList) {
            checkOutOverDueDTO.add(modelMapper.map(checkOut, CheckOutDTO.class));
        }
        return checkOutOverDueDTO;
    }

    public List<CheckOutDTO> getCheckOutsWhereReturnNull(int page) {
        List<CheckOutDTO> checkOutDTOList = new ArrayList<>();
        ModelMapper modelMapper = ModelMapperFactory.getMapper();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        List<CheckOut> checkOutList = checkOutRepository.findAllByReturnedDateIsNull(pageable);
        for (CheckOut checkOut: checkOutList) {
            checkOutDTOList.add(modelMapper.map(checkOut, CheckOutDTO.class));
        }
        return checkOutDTOList;
    }

    public List<CheckOutDTO> getCheckOutsWhereReturnNotNull(int page) {
        List<CheckOutDTO> checkOutDTOList = new ArrayList<>();
        ModelMapper modelMapper = ModelMapperFactory.getMapper();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        List<CheckOut> checkOutList = checkOutRepository.findAllByReturnedDateIsNotNull(pageable);
        for (CheckOut checkOut: checkOutList) {
            checkOutDTOList.add(modelMapper.map(checkOut, CheckOutDTO.class));
        }
        return checkOutDTOList;
    }
}
