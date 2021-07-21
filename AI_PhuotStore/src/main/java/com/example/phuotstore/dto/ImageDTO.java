package com.example.phuotstore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ImageDTO {

    @NotNull(message = "Image Name must not be null")
    private String imgName;

    @NotNull(message = "Image URL must not be null")
    private String  imgURL;

    @Min(value = 1,message = "Please chose a status")
    private int status;

    private int productID;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
