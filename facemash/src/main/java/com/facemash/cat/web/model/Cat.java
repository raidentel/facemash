package com.facemash.cat.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Represents cat image dto model.
 */
@ApiModel("Represents cat image dto model.")
public class Cat {

    /**
     * Image cat id.
     */
    private String id;

    /**
     * Image cat url.
     */
    private String url;

    /**
     *  Score.
     */
    private Integer score;

    /**
     * Voted.
     */
    private boolean voted;

    /**
     * Image cat id getter.
     */
    @ApiModelProperty("Image cat id.")
    public String getId() {
        return id;
    }

    /**
     * Image cat id setter.
     */
    public Cat setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Image url id getter.
     */
    @ApiModelProperty("Image cat url.")
    public String getUrl() {
        return url;
    }

    /**
     * Image url id setter.
     */
    public Cat setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Image cat score getter.
     */
    @ApiModelProperty("Score.")
    public Integer getScore() {
        return score;
    }

    /**
     * Image cat score setter.
     */
    public Cat setScore(Integer score) {
        this.score = score;
        return this;
    }

    /**
     * Voted getter.
     */
    @ApiModelProperty("Voted")
    public boolean isVoted() {
        return voted;
    }

    /**s
     * Voted setter.
     */
    public Cat setVoted(boolean voted) {
        this.voted = voted;
        return this;
    }
}
