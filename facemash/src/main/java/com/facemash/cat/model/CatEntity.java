package com.facemash.cat.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represent cat picture model class.
 */
@Entity
@Table(name = "Cat")
public class CatEntity implements Serializable {

    private static final long serialVersionUID = 6398676189025643407L;
    /**
     * Image cat id.
     */
    @Id
    private String id;

    /**
     * Image cat url.
     */
    @Column
    private String url;

    @Column
    private boolean voted;


    @Column
    private Integer score = 0;
    /**
     * Image cat id getter.
     */
    public String getId() {
        return id;
    }

    /**
     * Image cat id setter.
     */
    public CatEntity setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Image url id getter.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Image url id setter.
     */
    public CatEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public boolean isVoted() {
        return voted;
    }

    public CatEntity setVoted(boolean voted) {
        this.voted = voted;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public CatEntity setScore(Integer score) {
        this.score = score;
        return this;
    }
}
