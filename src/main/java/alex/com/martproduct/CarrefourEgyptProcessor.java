package alex.com.martproduct;

import alex.com.entity.Category;
import alex.com.martproduct.mapper.CategoryDao;
import alex.com.martproduct.service.CategoryService;
import alex.com.martproduct.service.ipml.CategoryServiceIpm;
import alex.com.testdemo.CsdnBlogProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by Tiffany on 2019-10-17.
 */
public class CarrefourEgyptProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(4000);

    //@Autowired
    private CategoryDao csbean;

    public CarrefourEgyptProcessor() {
        this.csbean = ThreadBean.getBean(CategoryDao.class);
    }

    /**家乐福埃及：
     * 1、第一步先把分类搞出来，分类有三级，只取最后一级的链接地址
     * 2、再把地址读出来循环爬数据
     * @param page
     */
    @Override
    public void process(Page page) {
        //1、获取所有1及目录
        List<String> level1 = page.getHtml().xpath("/html/body/header/div[3]/nav/div/div/div/div/ul[2]/li[@class='hidden-xs hidden-sm hidden-md']/a/span/text()").all();
        System.out.println(level1);
        for (int i = 0;i < level1.size();i++){
            System.out.println("level1: "+level1.get(i));
            //存level1
            Category category = new Category();
            category.setAlex_cname_ar(level1.get(i));
            csbean.addcategory(category);
            Integer cid1 = csbean.getcidbycname(category.getAlex_cname_ar());
            //存该1级的2级子目录
            String xpath2 = "/html/body/header/div[3]/nav/div/div/div/div/ul[2]/li[" + (i + 1) + "]/ul/li/a/text()";
            List<String> level2 = page.getHtml().xpath(xpath2).all();
            System.out.println(level2);
            level2.remove(0);
            level2.remove(0);
            System.out.println("level2: " + level2);
            for (int j = 0;j < level2.size();j++){
                System.out.println(level2.get(j));
                Category cate2 = new Category();
                cate2.setAlex_cname_ar(level2.get(j));
                cate2.setAlex_cpid(cid1);
                csbean.addcategory(cate2);
                Integer cid2 = csbean.getcidbycname(cate2.getAlex_cname_ar());
                //存该级的子目录，也就是第三级目录，并存链接
                String xpathtitle = "/html/body/header/div[3]/nav/div/div/div/div/ul[2]/li[" + (i + 1) + "]/ul/li[" + (j + 3) + "]/ul/li/a/text()";
                String xpathhref = "/html/body/header/div[3]/nav/div/div/div/div/ul[2]/li[" + (i + 1) + "]/ul/li[" + (j + 3) + "]/ul/li/a/@href";
                List<String> level3 = page.getHtml().xpath(xpathtitle).all();
                List<String> hrefstr = page.getHtml().xpath(xpathhref).all();
                System.out.println(level3);
                System.out.println(hrefstr);
                level3.remove(0);
                level3.remove(0);
                level3.remove(0);
                hrefstr.remove(0);
                hrefstr.remove(0);
                hrefstr.remove(0);
                for (int n = 0;n < level3.size();n++){
                    System.out.println("level3: "+level3.get(n) + "---" +"href: " + hrefstr.get(n));
                    Category cate3 = new Category();
                    cate3.setAlex_cname_ar(level3.get(n));
                    cate3.setHrefstr(hrefstr.get(n));
                    cate3.setAlex_cpid(cid2);
                    csbean.addcategory(cate3);
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CarrefourEgyptProcessor())
                .addUrl("https://www.carrefouregypt.com/mafegy/ar/language/en")
                .thread(5).run();
    }

}
