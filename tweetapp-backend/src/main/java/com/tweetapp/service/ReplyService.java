package com.tweetapp.service;

import com.tweetapp.entity.ReplyEntity;
import com.tweetapp.entity.TweetEntity;
import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.ErrorCode;
import com.tweetapp.exception.TweetAppServiceException;
import com.tweetapp.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserService userService;
    private final TweetService tweetService;

    public ReplyEntity createReply(ReplyEntity reply) {
        return replyRepository.save(reply);
    }

    public List<ReplyEntity> getReplies() throws TweetAppServiceException {
        List<ReplyEntity> replies = replyRepository.findAll();
        if (replies.isEmpty()) {
            throw new TweetAppServiceException(ErrorCode.REPLY_NOT_FOUND);
        }
        return replies;
    }

    public ReplyEntity getReply(long Id) throws TweetAppServiceException {
        return replyRepository.findById(Id)
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.REPLY_NOT_FOUND));
    }

    public List<ReplyEntity> getReplies(String username, Long id) throws TweetAppServiceException {
        userService.getUserEntityByUserName(username);
        List<ReplyEntity> replies = tweetService.getTweetEntityById(id).getReplays();
        if (replies.isEmpty()) {
            throw new TweetAppServiceException(ErrorCode.REPLY_NOT_FOUND);
        }
        return replies;
    }

    public ReplyEntity addReply(String username, Long id, String replyMassage) throws TweetAppServiceException {
        UserEntity user = userService.getUserEntityByUserName(username);
        TweetEntity tweet = tweetService.getTweetEntityById(id);
        ReplyEntity reply = createReply(setReply(replyMassage, user));
        tweet.getReplays().add(reply);
        tweetService.createTweet(tweet);
        return reply;
    }

    private ReplyEntity setReply(String replyMassage, UserEntity user) {
        ReplyEntity reply = new ReplyEntity();
        reply.setReply(replyMassage);
        reply.setDate(new Date());
        reply.setUserName(user.getLoginId());
        return reply;
    }
}
