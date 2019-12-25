package com.example.demoweb.model;

import java.security.PolicySpi;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Integer likes = 0;
    private Date creationDate;


    public Post() {
    }

    public Post(Long id, String text, Date creationDate){
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
    }

    public String getText(){
        return this.text;
    }

    public Integer getLikes(){
        return this.likes;
    }

    public void setLikes(Integer likes){
        this.likes = likes;
    }

    public Date getCreationDate(){
        return this.creationDate;
    }

    public Long getId(){
        return this.id;
    }
}
