package com.sethabrams.travelapp.repositories;

import com.sethabrams.travelapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
