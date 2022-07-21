package com.tweetapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReplyEntity {
    @Id
    @GeneratedValue
    private long id;
    private String reply;
    private Date date;
    private String userName;
}
