package com.oru.oruerakproject.auth.service;

import com.oru.oruerakproject.common.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class duplicateService {
    @Autowired
    private CommonRepository commonRepository;

    public boolean isPhoneDuplicate(String phoneNumber) {
        return commonRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean isNicknameDuplicate(String userId) {
        return commonRepository.existsByUserId(userId);
    }

}
