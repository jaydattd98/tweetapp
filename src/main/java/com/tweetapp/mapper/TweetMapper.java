package com.tweetapp.mapper;

import com.tweetapp.entity.TweetEntity;
import com.tweetapp.model.response.TweetResponse;
import org.springframework.stereotype.Component;

@Component
public class TweetMapper {

    public static TweetResponse toTweetResponse(String username, TweetEntity tweet) {
          return new TweetResponse(tweet.getId(),tweet.getTweetMassage(),username,tweet.getDate(),tweet.getLikes(),tweet.getReplays().stream().count());
    }
}
