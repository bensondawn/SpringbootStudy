package com.jiaxun.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserModel {

    @NotNull(message = "用户ID不能为空")
    private Long userID;

    @NotNull(message = "收货人地址id不能为空")
    private Long addressID;

    @NotBlank(message = "备注不为空")
    private String comment;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
