package alex.com.entity;

import java.io.Serializable;

/**
 * Created by Tiffany on 2019-10-15.
 */
public class ProductInfo implements Serializable {

    private Integer alex_prid; //pid

    private String alex_pname_ar; //商品名字-阿语

    private String alex_pname_zh; //商品名字-中文ps：辅助字段

    private String alex_description; //商品描述

    private String alex_price; //商品价格

    private String alex_weight; //商品重量

    private String alex_brand; //商品品牌

    private String alex_imgname; //商品图片名字

    private String alex_imgurl; //商品图片的url

    private Integer alex_cid; //分类cid

    private String picurl; //pic 原url

    public ProductInfo() {
    }

    public ProductInfo(Integer alex_prid, String alex_pname_ar, String alex_pname_zh, String alex_description, String alex_price, String alex_weight, String alex_brand, String alex_imgname, String alex_imgurl, Integer alex_cid, String picurl) {
        this.alex_prid = alex_prid;
        this.alex_pname_ar = alex_pname_ar;
        this.alex_pname_zh = alex_pname_zh;
        this.alex_description = alex_description;
        this.alex_price = alex_price;
        this.alex_weight = alex_weight;
        this.alex_brand = alex_brand;
        this.alex_imgname = alex_imgname;
        this.alex_imgurl = alex_imgurl;
        this.alex_cid = alex_cid;
        this.picurl = picurl;
    }

    public Integer getAlex_prid() {
        return alex_prid;
    }

    public void setAlex_prid(Integer alex_prid) {
        this.alex_prid = alex_prid;
    }

    public String getAlex_pname_ar() {
        return alex_pname_ar;
    }

    public void setAlex_pname_ar(String alex_pname_ar) {
        this.alex_pname_ar = alex_pname_ar;
    }

    public String getAlex_pname_zh() {
        return alex_pname_zh;
    }

    public void setAlex_pname_zh(String alex_pname_zh) {
        this.alex_pname_zh = alex_pname_zh;
    }

    public String getAlex_description() {
        return alex_description;
    }

    public void setAlex_description(String alex_description) {
        this.alex_description = alex_description;
    }

    public String getAlex_price() {
        return alex_price;
    }

    public void setAlex_price(String alex_price) {
        this.alex_price = alex_price;
    }

    public String getAlex_weight() {
        return alex_weight;
    }

    public void setAlex_weight(String alex_weight) {
        this.alex_weight = alex_weight;
    }

    public String getAlex_brand() {
        return alex_brand;
    }

    public void setAlex_brand(String alex_brand) {
        this.alex_brand = alex_brand;
    }

    public String getAlex_imgname() {
        return alex_imgname;
    }

    public void setAlex_imgname(String alex_imgname) {
        this.alex_imgname = alex_imgname;
    }

    public String getAlex_imgurl() {
        return alex_imgurl;
    }

    public void setAlex_imgurl(String alex_imgurl) {
        this.alex_imgurl = alex_imgurl;
    }

    public Integer getAlex_cid() {
        return alex_cid;
    }

    public void setAlex_cid(Integer alex_cid) {
        this.alex_cid = alex_cid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "alex_prid=" + alex_prid +
                ", alex_pname_ar='" + alex_pname_ar + '\'' +
                ", alex_pname_zh='" + alex_pname_zh + '\'' +
                ", alex_description='" + alex_description + '\'' +
                ", alex_price='" + alex_price + '\'' +
                ", alex_weight='" + alex_weight + '\'' +
                ", alex_brand='" + alex_brand + '\'' +
                ", alex_imgname='" + alex_imgname + '\'' +
                ", alex_imgurl='" + alex_imgurl + '\'' +
                ", alex_cid=" + alex_cid +
                ", picurl='" + picurl + '\'' +
                '}';
    }
}
