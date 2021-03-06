package com.mhr.housekeeping.entity;

import java.math.BigDecimal;

import java.io.Serializable;

/**
 * <br/>
 * Created by min on 2019/05/06
 */
public class OrdersDO implements Serializable {
    private static final long serialVersionUID = -4671264481966220250L;

    private Integer id;

    private Integer serviceId;

    private Integer employeeId;

    /**
     * 预留上门时间
     */
    private Long reverseTime;

    private String address;

    private String phone;

    private String tip;

    private Integer state;

    private Integer orderPrice;

    private Integer employerId;

    private Long createTime;

    /**
     * 订单完成时间
     */
    private Long endTime;

    /**
     * 评分
     */
    private BigDecimal rate;

    private String prov;

    private String city;

    /**
     * 超时取消费
     */
    private Integer pay;

    /**
     * 退款的原因
     */
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Long getReverseTime() {
        return reverseTime;
    }

    public void setReverseTime(Long reverseTime) {
        this.reverseTime = reverseTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }
}
