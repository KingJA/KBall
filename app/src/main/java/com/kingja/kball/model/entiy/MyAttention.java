package com.kingja.kball.model.entiy;


import java.util.Date;

/**
 * Description：TODO
 * Create Time：2016/11/29 16:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyAttention {
    private long attentionId;
    private long otherAccountId;
    private String name;
    private String des;
    private int experience;
    private String avatar;
    private RankInfo rankInfo;

    public RankInfo getRankInfo() {
        return new RankInfo(experience);
    }

    public long getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(long attentionId) {
        this.attentionId = attentionId;
    }

    public long getOtherAccountId() {
        return otherAccountId;
    }

    public void setOtherAccountId(long otherAccountId) {
        this.otherAccountId = otherAccountId;
    }

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

    public void setRankInfo(RankInfo rankInfo) {
        this.rankInfo = rankInfo;
    }
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
