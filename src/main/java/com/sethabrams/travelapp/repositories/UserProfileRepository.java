package com.sethabrams.travelapp.repositories;

import com.sethabrams.travelapp.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}