package com.bank.publicinfo.repository.branchRepository;

import com.bank.publicinfo.entity.branchEntity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
}
