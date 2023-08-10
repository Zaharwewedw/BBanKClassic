package com.bank.profile.repo;

import com.bank.profile.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepo extends JpaRepository<Passport, Long> {
}
