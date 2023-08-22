package com.bank.publicinfo.repository.bankDetailsRepository;

import com.bank.publicinfo.entity.bankDetailsEntity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {

}
