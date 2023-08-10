package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.service.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    @Autowired
    public ProfileController(ProfileService profileService, ProfileMapper profileMapper) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> getAllProfile() {
        return ResponseEntity.ok(
                profileMapper.listToDTO(
                        profileService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                profileMapper.profileToDTO(
                        profileService.getProfileById(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody ProfileDTO profileDTO) {
        Profile addedProfile = profileService.add(profileMapper.DTOToProfile(profileDTO));
        return ResponseEntity.ok(
                profileMapper.profileToDTO(addedProfile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id,
                                                    @RequestBody ProfileDTO profileDTO) {
        Profile updatedProfile = profileService.update(id, profileMapper.DTOToProfile(profileDTO));
        return ResponseEntity.ok(
                profileMapper.profileToDTO(updatedProfile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfileDTO> deleteProfile(@PathVariable Long id) {
        Profile deletedProfile = profileService.deleteById(id);
        return ResponseEntity.ok(
                profileMapper.profileToDTO(deletedProfile));
    }
}
