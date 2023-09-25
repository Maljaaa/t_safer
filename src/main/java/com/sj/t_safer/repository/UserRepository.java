package com.sj.t_safer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sj.t_safer.domain.sign.Users;

@Repository
public interface UserRepository extends JpaRepository <Users, Long> {
    Users findByEmail(String email);
}