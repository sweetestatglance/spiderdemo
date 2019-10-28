package alex.com.martproduct.service;

import alex.com.entity.Category;
import alex.com.entity.ProductInfo;

import java.util.List;

/**
 * Created by Tiffany on 2019-10-17.
 */
public interface CategoryService {

    Integer addcategory(Category ca);

    Integer getmaxid();

    Integer getcidbycname(String cname);

    List<Category> getlevelt(Integer page);

    List<Category> getleveltx();

    Category getbycid(Integer cid);

    List<ProductInfo> getpcurlbyname(String imgname);

    Integer updatep(ProductInfo p);
}
