package com.bank.publicinfo.service.branchService;

import com.bank.publicinfo.entity.branchEntity.Atm;
import com.bank.publicinfo.entity.branchEntity.Branch;
import com.bank.publicinfo.exception.branchException.BranchNotFoundException;
import com.bank.publicinfo.repository.branchRepository.BranchRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchServiceImpl implements BranchService {
    final BranchRepository branchRepository;

    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch getById(Long id) throws BranchNotFoundException {
        return branchRepository.findById(id)
                .orElseThrow(BranchNotFoundException::new);
    }

    @Override
    @Transactional
    public Branch save(Branch branch) {
        List<Atm> atmList = branch.getAtmList();
        if (atmList != null) {
            atmList.forEach(atm -> atm.setBranchId(branch));
        }
        return branchRepository.save(branch);
    }

    @Override
    @Transactional
    public Branch update(Long id, Branch newBranch) {
        Branch branch = getById(id);
        if (newBranch.getAddress() != null) branch.setAddress(newBranch.getAddress());
        if (newBranch.getPhoneNumber() != null) branch.setPhoneNumber(newBranch.getPhoneNumber());
        if (newBranch.getCity() != null) branch.setCity(newBranch.getCity());
        if (newBranch.getStartOfWork() != null) branch.setStartOfWork(newBranch.getStartOfWork());
        if (newBranch.getEndOfWork() != null) branch.setEndOfWork(newBranch.getEndOfWork());
        List<Atm> newAtmList = newBranch.getAtmList();
        if (newAtmList != null) {
            newAtmList.forEach(atm -> atm.setBranchId(branch));
            if (branch.getAtmList() == null) branch.setAtmList(new ArrayList<>());
            branch.getAtmList().addAll(newAtmList);
        }
        return branchRepository.save(branch);
    }

    @Override
    @Transactional
    public Branch delete(Long id) {
        Branch branch = getById(id);
        branchRepository.deleteById(id);
        return branch;
    }
}
