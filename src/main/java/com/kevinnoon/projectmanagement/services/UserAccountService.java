package com.kevinnoon.projectmanagement.services;

import com.kevinnoon.projectmanagement.entities.UserAccount;
import com.kevinnoon.projectmanagement.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {
    @Autowired
    UserAccountRepository securityRepository;
    public UserAccount save(UserAccount user){
        return securityRepository.save(user);
    }
    public List<UserAccount> findAll(){
        return securityRepository.findAll();
    }
}
