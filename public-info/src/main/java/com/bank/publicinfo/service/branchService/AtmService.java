package com.bank.publicinfo.service.branchService;

import com.bank.publicinfo.entity.branchEntity.Atm;

import java.util.List;

public interface AtmService {
    List<Atm> getAll(Long id);

    Atm getById(Long idAtm);

    Atm save(Long id, Atm atm);

    Atm update(Long idAtm, Atm atm);

    Atm delete(Long idAtm);
}
