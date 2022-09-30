package com.tweetapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TweetEntity {

    @Id
    @GeneratedValue
    private long id;
    private String tweetMassage;
    private Date date;
    private long likes;


    @OneToMany(targetEntity = ReplyEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "tweet_id",referencedColumnName = "id")
    private List<ReplyEntity> replays;

    public TweetEntity(long id, String tweetMassage, Date date) {
    }
    public TweetEntity(long id, String tweetMassage, Date date,long like) {
    }
}
