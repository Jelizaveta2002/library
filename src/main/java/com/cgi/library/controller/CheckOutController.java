package com.cgi.library.controller;

import com.cgi.library.model.CheckOutDTO;
import com.cgi.library.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {

    @Autowired
    private CheckOutService checkOutService;

    @GetMapping(value = "getCheckouts")
    public ResponseEntity<Page<CheckOutDTO>> getCheckOuts(int page, String sortBy) {
        return ResponseEntity.ok(checkOutService.getCheckOuts(page, sortBy));
    }

    @GetMapping(value = "getClosedCheckouts")
    public ResponseEntity<List<CheckOutDTO>> getClosedCheckOuts(int page) {
        return ResponseEntity.ok(checkOutService.getCheckOutsWhereReturnNotNull(page));
    }

    @GetMapping(value = "getOpenedCheckouts")
    public ResponseEntity<List<CheckOutDTO>> getOpenedCheckOuts(int page) {
        return ResponseEntity.ok(checkOutService.getCheckOutsWhereReturnNull(page));
    }

    @GetMapping(value = "getCheckout")
    public ResponseEntity<CheckOutDTO> getCheckOut(@RequestParam(value = "checkOutId") UUID checkOutId) {
        return ResponseEntity.ok(checkOutService.getCheckOut(checkOutId));
    }

    @GetMapping(value = "setDate")
    public void setReturnedDate(@RequestParam(value = "checkOutId") UUID checkOutId) {
        checkOutService.setReturnedDate(checkOutId);
    }

    @PostMapping(value = "checkout")
    public ResponseEntity<String> saveCheckOut(@RequestBody CheckOutDTO checkOutDTO) {
        checkOutService.saveCheckOut(checkOutDTO);
        return ResponseEntity.ok("");
    }

    @DeleteMapping(value = "checkout")
    public ResponseEntity<String> deleteCheckOut(@RequestParam(value = "checkOutId") UUID checkOutId) {
        checkOutService.deleteCheckOut(checkOutId);
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "getLateCheckouts")
    public ResponseEntity<List<CheckOutDTO>> getLateCheckOuts(int page) {
        return ResponseEntity.ok(checkOutService.getLateCheckOuts(page));
    }
}
