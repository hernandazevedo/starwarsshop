package br.com.devhernand.starwarsstore.model.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

/**
 * Created by Nando on 31/05/2017.
 */
@Generated("org.jsonschema2pojo")
public class Product implements Serializable{

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("price")
    @Expose
    public Long price;

    @SerializedName("zipcode")
    @Expose
    public String zipcode;

    @SerializedName("seller")
    @Expose
    public String seller;

    @SerializedName("thumbnailHd")
    @Expose
    public String thumbnailHd;


    @SerializedName("date")
    @Expose
    public String date;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getThumbnailHd() {
        return thumbnailHd;
    }

    public void setThumbnailHd(String thumbnailHd) {
        this.thumbnailHd = thumbnailHd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}