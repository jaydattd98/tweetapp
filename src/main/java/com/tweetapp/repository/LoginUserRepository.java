package com.tweetapp.repository;

import com.tweetapp.entity.LoginUserDetails;
import com.tweetapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUserDetails, String> {
}
