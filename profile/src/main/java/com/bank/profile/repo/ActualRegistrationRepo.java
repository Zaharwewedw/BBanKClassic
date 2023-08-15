package com.bank.profile.repo;

import com.bank.profile.entity.ActualRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualRegistrationRepo extends JpaRepository<ActualRegistration, Long> {
}
