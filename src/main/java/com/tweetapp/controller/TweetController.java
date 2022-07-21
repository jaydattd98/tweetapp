package com.tweetapp.controller;

import com.tweetapp.entity.ReplyEntity;
import com.tweetapp.entity.TweetEntity;
import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.ErrorCode;
import com.tweetapp.exception.TweetAppServiceException;
import com.tweetapp.model.response.TweetResponse;
import com.tweetapp.service.LoginService;
import com.tweetapp.service.ReplyService;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.tweetapp.mapper.TweetMapper.toTweetResponse;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/tweets")
public class TweetController {

    private final TweetService tweetService;
    private final UserService userService;
    private final ReplyService replyService;
    private final LoginService loginService;


    @GetMapping("/all")
    public ResponseEntity<List<TweetResponse>> getTweet() throws TweetAppServiceException {
        return new ResponseEntity<>(tweetService.getTweets(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<TweetResponse>> getAllTweetsOfUser(@PathVariable String username) throws TweetAppServiceException {
        return new ResponseEntity<>(tweetService.getTweetsByUserName(username), HttpStatus.OK);
    }

    @PostMapping("/{username}/add")
    public ResponseEntity<TweetResponse> postNewTweet(@RequestBody String tweetMassage, @PathVariable String username) throws TweetAppServiceException {
        checkIsUserLoggedIn(username);
        validateTweetRequestInput(tweetMassage);
        return new ResponseEntity<>(tweetService.createNewTweet(username,tweetMassage), HttpStatus.OK);
    }

    @PostMapping("/{username}/update/{id}")
    public ResponseEntity<TweetResponse> updateTweet(@RequestBody String tweetMassage, @PathVariable String username, @PathVariable Long id) throws TweetAppServiceException {
        checkIsUserLoggedIn(username);
        validateTweetRequestInput(tweetMassage);
        return new ResponseEntity<>(toTweetResponse(tweetService.updateTweetByIdAndMsg(id, tweetMassage)), HttpStatus.OK);
    }

    @GetMapping("/{username}/get/{id}")
    public ResponseEntity<TweetEntity> getTweet(@PathVariable long id, @PathVariable String username) throws TweetAppServiceException {
        validateTweetId(id);
        return new ResponseEntity<>(tweetService.getTweetByIdAndUserName(id, username), HttpStatus.OK);
    }

    @DeleteMapping("/{username}/delete/{id}")
    public ResponseEntity<String> removeTweet(@PathVariable String username, @PathVariable long id) throws TweetAppServiceException {
        checkIsUserLoggedIn(username);
        validateTweetId(id);
        return new ResponseEntity<>(tweetService.removeTweet(id), HttpStatus.OK);
    }

    @PutMapping("/{username}/like/{id}")
    public ResponseEntity<TweetResponse> likeTweet(@PathVariable String username, @PathVariable long id) throws TweetAppServiceException {
        checkIsUserLoggedIn(username);
        validateTweetId(id);
        return new ResponseEntity<>(toTweetResponse(tweetService.addLikeForTweet(id)), HttpStatus.OK);
    }

    @PostMapping("/{username}/reply/{id}")
    public ResponseEntity<ReplyEntity> addReply(@PathVariable String username, @PathVariable Long id, @RequestBody String replyMassage) throws TweetAppServiceException {
        checkIsUserLoggedIn(username);
        validateTweetRequestInput(replyMassage);
        return new ResponseEntity<>(replyService.addReply(username, id, replyMassage), HttpStatus.OK);
    }

    @GetMapping("/{username}/replies/{id}")
    public ResponseEntity<List<ReplyEntity>> getRepliesForTweet(@PathVariable String username, @PathVariable Long id) throws TweetAppServiceException {
        checkIsUserLoggedIn(username);
        return new ResponseEntity<>(replyService.getReplies(username, id), HttpStatus.OK);
    }


    private void checkIsUserLoggedIn(String username) throws TweetAppServiceException {
        loginService.isUserLoggedIn(username);
    }

    private void validateTweetId(Long tweetId) throws TweetAppServiceException {
        if (tweetId == null || tweetId == 0) {
            throw new TweetAppServiceException(ErrorCode.INVALID_INPUT);
        }
    }

    private void validateTweetRequestInput(String tweetMassage) throws TweetAppServiceException {
        if (isEmpty(tweetMassage)) {
            throw new TweetAppServiceException(ErrorCode.INVALID_INPUT);
        }
    }
}
