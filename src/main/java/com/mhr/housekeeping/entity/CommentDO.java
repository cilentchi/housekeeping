package com.mhr.housekeeping.entity;

import java.io.Serializable;

/**
 * <br/>
 * Created by min on 2019/05/07
 */
public class CommentDO implements Serializable {
    private static final long serialVersionUID = -7865298991941202491L;

    private Integer id;

    private Integer orderId;

    private String comment;

    /**
     * 几星
     */
    private Integer rate;

    private String photo;

    private Long createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
