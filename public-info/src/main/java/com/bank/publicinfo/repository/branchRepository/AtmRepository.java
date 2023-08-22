package com.bank.publicinfo.repository.branchRepository;

import com.bank.publicinfo.entity.branchEntity.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {
}
