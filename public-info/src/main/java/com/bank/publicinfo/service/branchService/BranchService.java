package com.bank.publicinfo.service.branchService;

import com.bank.publicinfo.entity.branchEntity.Branch;

import java.util.List;

public interface BranchService {
    List<Branch> getAll();

    Branch getById(Long id);

    Branch save(Branch branch);

    Branch update(Long id, Branch branch);

    Branch delete(Long id);
}
