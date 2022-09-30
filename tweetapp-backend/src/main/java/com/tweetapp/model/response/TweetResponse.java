package com.tweetapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
/**
 * Class TweetResponse
 *
 * This DTA model used to give proper UI response to User
 * And Avoid populating sensitive details to user like "password,DOB .etc" in response
 *
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TweetResponse {
    private long id;
    private String tweetMassage;
    private String username;
    private Date date;
    private long likes;
    private long comments;
}
