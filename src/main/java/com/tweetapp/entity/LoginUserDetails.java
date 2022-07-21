package com.tweetapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This table will contains the username for logged in user
 * if username if present in table means he is logged in
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name="LOGIN_USER_DETAILS")
public class LoginUserDetails {
    @Id
    private String username;
}
