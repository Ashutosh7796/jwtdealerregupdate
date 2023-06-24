package com.spring.jwt.service.impl;

import com.spring.jwt.dto.RegisterDto;
import com.spring.jwt.entity.Dealer;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.DealerRepository;
import com.spring.jwt.repository.RoleRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.DealerService;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class DealerServiceImpl implements DealerService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DealerRepository dealerRepository;
    @Override
    public BaseResponseDTO updateDealer(Integer userId, RegisterDto registerDto) {
        BaseResponseDTO response = new BaseResponseDTO();
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getRoles().stream().anyMatch(role -> role.getName().equals("DEALER"))) {
                Dealer dealer = user.getDealers();
                if (dealer != null) {
                    updateDealerDetails(dealer, registerDto);
                    dealerRepository.save(dealer);
                    response.setCode(String.valueOf(HttpStatus.OK.value()));
                    response.setMessage("Dealer details updated successfully");
                } else {
                    response.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
                    response.setMessage("Dealer details not found");
                }
            } else {
                response.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
                response.setMessage("User is not a dealer");
            }
        } else {
            response.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
            response.setMessage("User not found");
        }

        return response;
    }

    private void updateDealerDetails(Dealer dealer, RegisterDto registerDto) {
        dealer.setAddress(registerDto.getAddress());
        dealer.setAdharShopact(registerDto.getAdharShopact());
        dealer.setArea(registerDto.getArea());
        dealer.setCity(registerDto.getCity());
        dealer.setFristname(registerDto.getFirstName());
        dealer.setLastName(registerDto.getLastName());
        dealer.setMobileNo(registerDto.getMobileNo());
        dealer.setShopName(registerDto.getShopName());
        dealer.setEmail(registerDto.getEmail());
        User user = dealer.getUser();
        user.setEmail(registerDto.getEmail()); // Update email in User table as well
        userRepository.save(user); // Save the updated User entity
    }
    }
