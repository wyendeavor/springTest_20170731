package com.wy.domain.jd;

import java.util.Date;

public class CashierTemplatePayMethodRelation {
    private Long id;

    private String templateNo;

    private String paymentMethod1;

    private String paymentMethod2;

    private String paymentMethod3;

    private Integer priority;

    private Byte availableStatus;

    private Date createdDate;

    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo == null ? null : templateNo.trim();
    }

    public String getPaymentMethod1() {
        return paymentMethod1;
    }

    public void setPaymentMethod1(String paymentMethod1) {
        this.paymentMethod1 = paymentMethod1 == null ? null : paymentMethod1.trim();
    }

    public String getPaymentMethod2() {
        return paymentMethod2;
    }

    public void setPaymentMethod2(String paymentMethod2) {
        this.paymentMethod2 = paymentMethod2 == null ? null : paymentMethod2.trim();
    }

    public String getPaymentMethod3() {
        return paymentMethod3;
    }

    public void setPaymentMethod3(String paymentMethod3) {
        this.paymentMethod3 = paymentMethod3 == null ? null : paymentMethod3.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Byte getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(Byte availableStatus) {
        this.availableStatus = availableStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}