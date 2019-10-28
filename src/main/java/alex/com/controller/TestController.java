package alex.com.controller;

import alex.com.entity.Category;
import alex.com.martproduct.CarrefourEgyptProcessor;
import alex.com.martproduct.CarrefourProductDataProcesser;
import alex.com.martproduct.mapper.CategoryDao;
import alex.com.martproduct.service.CategoryService;
import alex.com.martproduct.service.ipml.CategoryServiceIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * Created by Tiffany on 2019-10-17.
 */
@RestController
@RequestMapping("/dtod")
public class TestController {
    @Autowired
    private CategoryService cs;

    @Autowired
    private CategoryDao vv;

    @RequestMapping("/menu")
    public String getmenu(){
        Spider.create(new CarrefourEgyptProcessor())
                .addUrl("https://www.carrefouregypt.com/mafegy/en/language/en")
                .thread(5).run();
        return "ok";
    }

    @RequestMapping("/test")
    public String test(){
        Category c = new Category();
        c.setAlex_cname_ar("test");
        Integer we = cs.addcategory(c);
        return we+"";
    }

    @RequestMapping("/level3")
    public List<Category> level3(){
        List<Category> list = cs.getlevelt(0);
        for (Category c : list){
            String url = "https://www.carrefouregypt.com" + c.getHrefstr(); //https://www.carrefouregypt.com/mafegy/en/Fresh-Food/Fruits-VegetablesVegetables/c/FEGY1660500
            // 从用户博客首页开始抓，开启5个线程，启动爬虫
            Spider.create(new CarrefourProductDataProcesser())
                    .addUrl(url)
                    .thread(5).run();
        }
        return list;
    }

}
