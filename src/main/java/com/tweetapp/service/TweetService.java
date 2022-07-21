package com.tweetapp.service;

import com.tweetapp.entity.TweetEntity;
import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.ErrorCode;
import com.tweetapp.exception.TweetAppServiceException;
import com.tweetapp.mapper.TweetMapper;
import com.tweetapp.model.response.TweetResponse;
import com.tweetapp.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.tweetapp.mapper.TweetMapper.toTweetResponse;

@Service
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserService userService;

    public TweetEntity createTweet(TweetEntity tweet) {
        return tweetRepository.save(tweet);
    }

    public TweetEntity updateTweetByIdAndMsg(long id, String tweetMassage) throws TweetAppServiceException {
        TweetEntity tweet = getTweetEntityById(id);
        tweet.setTweetMassage(tweetMassage);
        return tweetRepository.save(tweet);
    }

    private TweetEntity updateTweet(TweetEntity tweet) {
        return tweetRepository.save(tweet);
    }

    public List<TweetResponse> getTweets() throws TweetAppServiceException {
        return getAllTweetEntities()
                .stream()
                .map(TweetMapper::toTweetResponse)
                .collect(Collectors.toList());
    }

    public TweetEntity getTweetByIdAndUserName(long id, String username) throws TweetAppServiceException {
        return userService
                .getUserEntityByUserName(username)
                .getTweets()
                .stream()
                .filter(tweet -> id == tweet.getId())
                .findFirst()
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.TWEET_NOT_FOUND));
    }

    public TweetEntity getTweetEntityById(long tweetId) throws TweetAppServiceException {
        return tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.TWEET_NOT_FOUND));
    }

    public String removeTweet(long tweetId) throws TweetAppServiceException {
        tweetRepository.delete(getTweetEntityById(tweetId));
        return "TweetEntity has deleted Successfully";
    }

    private List<TweetEntity> getAllTweetEntities() throws TweetAppServiceException {
        List<TweetEntity> tweets = tweetRepository.findAll();
        if (tweets.isEmpty()) {
            throw new TweetAppServiceException(ErrorCode.TWEET_NOT_FOUND);
        }
        return tweets;
    }

    public List<TweetResponse> getTweetsByUserName(String username) throws TweetAppServiceException {
        return userService
                .getUserEntityByUserName(username)
                .getTweets()
                .stream()
                .map(TweetMapper::toTweetResponse)
                .collect(Collectors.toList());
    }

    public TweetEntity addLikeForTweet(long id) throws TweetAppServiceException {
        TweetEntity tweet = getTweetEntityById(id);
        tweet.setLikes(tweet.getLikes() + 1);
        return updateTweet(tweet);
    }


    public TweetResponse createNewTweet(String username, String tweetMassage) throws TweetAppServiceException {
        UserEntity user = userService.getUserEntityByUserName(username);
        TweetEntity tweet = createTweet(getTweetFromRequest(tweetMassage));
        user.getTweets().add(tweet);
        userService.updateUserEntity(user);
        return toTweetResponse(tweet);
    }


    private TweetEntity getTweetFromRequest(String tweetMassage) {
        TweetEntity tweet = new TweetEntity();
        tweet.setTweetMassage(tweetMassage);
        tweet.setDate(new Date());
        return tweet;
    }
}
