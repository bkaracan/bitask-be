package com.ilkayburak.bitask.repository;

import com.ilkayburak.bitask.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {

    Optional<UserStatus> findByName(String name);

}
