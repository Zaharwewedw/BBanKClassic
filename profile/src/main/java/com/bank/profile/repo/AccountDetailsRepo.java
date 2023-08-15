package com.bank.profile.repo;

import com.bank.profile.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailsRepo extends JpaRepository<AccountDetails, Long> {
}
