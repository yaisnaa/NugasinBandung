package com.nugasinbandung.backend.service;

import com.nugasinbandung.backend.entity.Coffeeshop;
import com.nugasinbandung.backend.entity.User;
import com.nugasinbandung.backend.repository.CoffeeshopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeshopService {

    private final CoffeeshopRepository coffeeshopRepository;
    private final UserService userService;

    public List<Coffeeshop> findAll() {
        return coffeeshopRepository.findAll();
    }

    public Coffeeshop findById(Long id) {
        return coffeeshopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coffeeshop tidak ditemukan"));
    }

    public Coffeeshop submit(Coffeeshop coffeeshop, String email) {
        User user = userService.findByEmail(email);
        coffeeshop.setSubmittedBy(user.getId());
        return coffeeshopRepository.save(coffeeshop);
    }
}