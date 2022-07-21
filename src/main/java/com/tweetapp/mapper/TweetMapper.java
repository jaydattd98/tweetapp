package com.tweetapp.mapper;

import com.tweetapp.entity.TweetEntity;
import com.tweetapp.model.response.TweetResponse;
import org.springframework.stereotype.Component;

@Component
public class TweetMapper {

    public static TweetResponse toTweetResponse(TweetEntity tweet) {
          return new TweetResponse(tweet.getId(),tweet.getTweetMassage(),tweet.getDate(),tweet.getLikes());
    }
}
