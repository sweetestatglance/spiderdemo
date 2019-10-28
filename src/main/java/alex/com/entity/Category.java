package alex.com.entity;

import java.io.Serializable;

/**
 * Created by Tiffany on 2019-10-15.
 */
public class Category implements Serializable {

    private Integer alex_cid; //分类id

    private String alex_cname_ar; //分类名字-阿语

    private String alex_cname_zh; //分类名字-中文ps：辅助字段

    private String hrefstr; //href地址

    private Integer alex_cpid; //分类的pid

    public Category() {
    }

    public Category(Integer alex_cid, String alex_cname_ar, String alex_cname_zh, String hrefstr, Integer alex_cpid) {
        this.alex_cid = alex_cid;
        this.alex_cname_ar = alex_cname_ar;
        this.alex_cname_zh = alex_cname_zh;
        this.hrefstr = hrefstr;
        this.alex_cpid = alex_cpid;
    }

    public Integer getAlex_cid() {
        return alex_cid;
    }

    public void setAlex_cid(Integer alex_cid) {
        this.alex_cid = alex_cid;
    }

    public String getAlex_cname_ar() {
        return alex_cname_ar;
    }

    public void setAlex_cname_ar(String alex_cname_ar) {
        this.alex_cname_ar = alex_cname_ar;
    }

    public String getAlex_cname_zh() {
        return alex_cname_zh;
    }

    public void setAlex_cname_zh(String alex_cname_zh) {
        this.alex_cname_zh = alex_cname_zh;
    }

    public String getHrefstr() {
        return hrefstr;
    }

    public void setHrefstr(String hrefstr) {
        this.hrefstr = hrefstr;
    }

    public Integer getAlex_cpid() {
        return alex_cpid;
    }

    public void setAlex_cpid(Integer alex_cpid) {
        this.alex_cpid = alex_cpid;
    }

    @Override
    public String toString() {
        return "Category{" +
                "alex_cid=" + alex_cid +
                ", alex_cname_ar='" + alex_cname_ar + '\'' +
                ", alex_cname_zh='" + alex_cname_zh + '\'' +
                ", hrefstr='" + hrefstr + '\'' +
                ", alex_cpid=" + alex_cpid +
                '}';
    }
}
