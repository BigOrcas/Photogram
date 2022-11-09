package com.ward.photogram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

public interface UserRepository extends JpaRepository<User,Integer> {
}
