package com.bank.profile.service.profile;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;

import java.util.List;

public interface ProfileService {

    List<Profile> getAll();
    Profile getProfileById(Long id);

    Profile add(Profile profile);

    Profile update(Long id, Profile profile);

    Profile deleteById(Long id);

}
