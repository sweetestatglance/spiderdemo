package alex.com.martproduct.service.ipml;

import alex.com.entity.Category;
import alex.com.entity.ProductInfo;
import alex.com.martproduct.mapper.CategoryDao;
import alex.com.martproduct.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tiffany on 2019-10-17.
 */
@Service
public class CategoryServiceIpm implements CategoryService {

    @Autowired
    private CategoryDao cdbean;

    @Override
    public Integer addcategory(Category ca) {
        return cdbean.addcategory(ca);
    }

    @Override
    public Integer getmaxid() {
        return cdbean.getmaxid();
    }

    @Override
    public Integer getcidbycname(String cname) {
        return cdbean.getcidbycname(cname);
    }

    @Override
    public List<Category> getlevelt(Integer page) {
        return cdbean.getlevelt(page);
    }

    @Override
    public List<Category> getleveltx() {
        return cdbean.getleveltx();
    }

    @Override
    public Category getbycid(Integer cid) {
        return cdbean.getbycid(cid);
    }

    @Override
    public List<ProductInfo> getpcurlbyname(String imgname) {
        return cdbean.getpcurlbyname(imgname);
    }

    @Override
    public Integer updatep(ProductInfo p) {
        return cdbean.updatep(p);
    }
}
