package com.bank.publicinfo.service.branchService;

import com.bank.publicinfo.entity.branchEntity.Atm;
import com.bank.publicinfo.entity.branchEntity.Branch;
import com.bank.publicinfo.exception.branchException.AtmNotFoundException;
import com.bank.publicinfo.repository.branchRepository.AtmRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AtmServiceImpl implements AtmService {
    final BranchService branchService;
    final AtmRepository atmRepository;


    @Override
    public List<Atm> getAll(Long id) {
        return branchService.getById(id).getAtmList();
    }

    @Override
    public Atm getById(Long idAtm) throws AtmNotFoundException {
        return atmRepository.findById(idAtm).orElseThrow(AtmNotFoundException::new);
    }

    @Override
    @Transactional
    public Atm save(Long id, Atm atm) {
        Branch branch = branchService.getById(id);
        atm.setBranchId(branch);
        branch.getAtmList().add(atm);
        return atmRepository.save(atm);
    }

    @Override
    @Transactional
    public Atm update(Long idAtm, Atm newAtm) {
        Atm atm = getById(idAtm);
        if (newAtm.getAddress() != null) {
            atm.setAddress(newAtm.getAddress());
        }
        if (newAtm.getAllHours()) {
            atm.setAllHours(true);
            atm.setStartOfWork(null);
            atm.setEndOfWork(null);
        } else {
            atm.setStartOfWork(newAtm.getStartOfWork());
            atm.setEndOfWork(newAtm.getEndOfWork());
            atm.setAllHours(false);
        }
        return atmRepository.save(atm);
    }

    @Override
    @Transactional
    public Atm delete(Long idAtm) {
        Atm atm = getById(idAtm);
        atmRepository.deleteById(idAtm);
        return atm;
    }
}
