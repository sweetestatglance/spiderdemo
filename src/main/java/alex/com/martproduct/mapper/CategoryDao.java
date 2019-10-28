package alex.com.martproduct.mapper;

import alex.com.entity.Category;
import alex.com.entity.ProductInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tiffany on 2019-10-17.
 */
@Repository
public interface CategoryDao {

    @Insert("insert into alexcategory(alex_cid, alex_cname_ar, alex_cname_zh, hrefstr, alex_cpid)" +
            "values(#{alex_cid}, #{alex_cname_ar}, #{alex_cname_zh}, #{hrefstr}, #{alex_cpid})")
    @ResultType(Integer.class)
    Integer addcategory(Category ca);

    @Select("select max(alex_cid) from alexcategory")
    @ResultType(Integer.class)
    Integer getmaxid();

    @Select("select alex_cid from alexcategory where alex_cname_ar = #{cname} and hrefstr is null")
    @ResultType(Integer.class)
    Integer getcidbycname(@Param("cname")String cname);

    //每页是个数据
    @Select("select * from alexcategory where hrefstr is not null limit #{page}, 10")
    @ResultType(Category.class)
    List<Category> getlevelt(@Param("page") Integer page);

    @Select("select * from alexcategory where hrefstr is not null")
    @ResultType(Category.class)
    List<Category> getleveltx();

    @Insert("insert into alexproduct(alex_prid,alex_pname_ar,alex_pname_zh,alex_description,alex_price,alex_weight,alex_brand,alex_imgname,alex_imgurl,alex_cid,picurl)" +
            "values(#{alex_prid},#{alex_pname_ar},#{alex_pname_zh},#{alex_description},#{alex_price},#{alex_weight},#{alex_brand},#{alex_imgname},#{alex_imgurl},#{alex_cid},#{picurl})")
    @ResultType(Integer.class)
    Integer addproductinfo(ProductInfo p);

    @Select("select alex_prid from alexproduct where alex_imgname = #{alex_imgname}")
    @ResultType(Integer.class)
    Integer getproid(@Param("picname") String picname);

    @Select("select alex_cid from alexcategory where hrefstr = #{str}")
    @ResultType(Integer.class)
    Integer getcidbyhrefstr(@Param("str") String str);

    @Update("update alexproduct set picurl = #{picurl} where alex_prid = #{proid}")
    Integer updatepicurl(@Param("picurl") String picurl,@Param("proid") Integer proid);

    @Select("select * from alexcategory where alex_cid = #{cid}")
    Category getbycid(@Param("cid") Integer cid);

    @Select("select * from alexproduct where alex_imgname = #{imgname}")
    @ResultType(ProductInfo.class)
    List<ProductInfo> getpcurlbyname(@Param("imgname") String imgname);

    @Update("update alexproduct set alex_imgname = #{alex_imgname}, alex_imgurl = #{alex_imgurl} where alex_prid = #{alex_prid}")
    Integer updatep(ProductInfo p);
}
