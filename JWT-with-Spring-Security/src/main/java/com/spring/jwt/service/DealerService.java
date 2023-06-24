package com.spring.jwt.service;

import com.spring.jwt.dto.RegisterDto;
import com.spring.jwt.utils.BaseResponseDTO;

public interface DealerService {
    BaseResponseDTO updateDealer(Integer userId, RegisterDto registerDto);
}