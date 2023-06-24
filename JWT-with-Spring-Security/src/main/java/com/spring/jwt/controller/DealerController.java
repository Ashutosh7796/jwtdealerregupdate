package com.spring.jwt.controller;

import com.spring.jwt.dto.RegisterDto;
import com.spring.jwt.service.DealerService;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dealer")
@RequiredArgsConstructor
public class DealerController {
    private final DealerService dealerService;
    @PutMapping("/updateDealer/{userId}")
    public ResponseEntity<BaseResponseDTO> updateDealer(@PathVariable("userId") Integer userId, @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(dealerService.updateDealer(userId, registerDto));
    }
}
