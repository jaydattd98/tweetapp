package com.tweetapp.repository;

import com.tweetapp.entity.LoginUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUserDetails, Long> {
    Optional<LoginUserDetails> findByUserName(String username);

    List<LoginUserDetails> findAllByUserName(String loginId);
}
