package com.bank.profile.service.reg;


import java.util.List;

public interface RegistrationService<T> {

    List<T> getAll();
    T getRegistrationById(Long id);

    T add(T registration);

    T update(Long id, T registration);

    T deleteById(Long id);

}
