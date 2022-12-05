package com.kevinnoon.projectmanagement.repository;

import com.kevinnoon.projectmanagement.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount,Long> {
@Override
    public List<UserAccount> findAll();
}
