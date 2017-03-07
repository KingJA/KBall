package com.kingja.kball.model.entiy;

/**
 * Description：TODO
 * Create Time：2017/3/6 17:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Question {

    /**
     * name : 乔丹
     * avatar : /upload/avatar/head_8.jpg
     * title : 这是一个新的问题
     * content : 具体问题请看标题
     * imgUrls :
     * solved : 0
     * answerCount : 0
     * collectCount : 0
     * tagId : 1
     * createTime : 2016-11-29 09:38:54
     */

    private String name;
    private String avatar;
    private String title;
    private String content;
    private String imgUrls;
    private int solved;
    private int answerCount;
    private int collectCount;
    private int tagId;
    private String createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}