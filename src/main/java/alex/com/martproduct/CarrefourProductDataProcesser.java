package alex.com.martproduct;

import alex.com.controller.TestController;
import alex.com.entity.Category;
import alex.com.entity.ProductInfo;
import alex.com.martproduct.mapper.CategoryDao;
import alex.com.martproduct.service.CategoryService;
import alex.com.martproduct.service.ipml.CategoryServiceIpm;
import alex.com.util.ImagesDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarrefourProductDataProcesser implements PageProcessor {

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(4000);

    @Autowired
    private static CategoryService cds;

    private CategoryDao csbean;

    private String pagestr = "?&qsort=relevance&pg=";

    static List<Category> list;

    String imgpath = "E:\\carrimage";

    public CarrefourProductDataProcesser() {
        this.csbean = ThreadBean.getBean(CategoryDao.class);
    }

    /**
     * 爬取数据及图片
     * @param page
     */
    @Override
    public void process(Page page) {
        String pas = page.getUrl().get();
        String s1 = pas.substring(pas.indexOf("/mafegy"));
        Integer index = s1.indexOf("?&");
        if (index > 0){
            s1 = s1.substring(0, index);
        }
        Integer pid = csbean.getcidbyhrefstr(s1);
        //https://www.carrefouregypt.com/mafegy/en/Fresh-Food/Fruits-VegetablesVegetables/c/FEGY1660500
        if(index < 0){
            //判断有没有页数 /html/body/main/div/div[1]/div/div[2]/nav[1]/ul/li[1]/a
            List<String> pages = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/nav[1]/ul/li/a/text()").all();
            if (pages != null && pages.size() > 0){
                //有分页 ,去掉头尾的 previous 和 next
                pages.remove(0);
                pages.remove(pages.size() - 1);
                Integer lastpage = Integer.parseInt(pages.get(pages.size() - 1));
                List<String> urls = new ArrayList<>();
                String sc = pas + pagestr;
                for (int i = 1;i < lastpage;i++){
                    urls.add(sc+i);
                }
                page.addTargetRequests(urls);

            }
            //爬取本页数据
            List<String> nametit = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div/div/div/div[1]/div/a/div/p[1]/text()").all();
            List<String> price = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div/div/div/div[1]/div/a/div/p[3]/strong/text()").all();
            //List<String> bi = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div/div/div/div[1]/div/a/div/p[3]/strong/span[1]/text()").all();
            List<String> imgurl = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div/div/div/div[1]/div/a/figure/img/@src").all();
            for (int j=0;j<nametit.size()-1;j++){
                ProductInfo p = new ProductInfo();
                p.setAlex_cid(pid); //cid
                p.setAlex_pname_ar(nametit.get(j).trim());
                String imgname = ImagesDownloadUtil.gettimemillion();
                String suffix = imgurl.get(j).substring(imgurl.get(j).lastIndexOf("."));
                //1571387313447.jpg_200Wx200H
                suffix = suffix.substring(0,suffix.lastIndexOf("_"));
                p.setAlex_imgname(imgname+suffix);
                p.setAlex_imgurl("/carrimage/"+imgname+suffix);
                String kg = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div["+ (j+1) +"]/div/div/div[1]/div/a/div/p[3]/strong/span[2]/text()").get();
                if (kg ==null){
                    kg = "";
                }
                String pricess = price.get(j) + "جنيه " + kg.trim();
                p.setAlex_price(pricess);
                p.setPicurl(imgurl.get(j));
                //下载图片
                String picpath = imgpath + "/" + imgname+suffix;
                try {
                    ImagesDownloadUtil.downloadPicture(imgurl.get(j), picpath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //存数据库
                csbean.addproductinfo(p);
            }

        }else {
            //爬取本页数据
            List<String> nametit = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div/div/div/div[1]/div/a/div/p[1]/text()").all();
            List<String> price = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div/div/div/div[1]/div/a/div/p[3]/strong/text()").all();
            //List<String> bi = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div/div/div/div[1]/div/a/div/p[3]/strong/span[1]/text()").all();
            List<String> imgurl = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div/div/div/div[1]/div/a/figure/img/@src").all();
            for (int j=0;j<nametit.size()-1;j++){
                ProductInfo p = new ProductInfo();
                p.setAlex_cid(pid); //cid
                p.setAlex_pname_ar(nametit.get(j).trim());
                String imgname = ImagesDownloadUtil.gettimemillion();
                String suffix = imgurl.get(j).substring(imgurl.get(j).lastIndexOf("."));
                //1571387313447.jpg_200Wx200H
                suffix = suffix.substring(0,suffix.lastIndexOf("_"));
                p.setAlex_imgname(imgname+suffix);
                p.setAlex_imgurl("/carrimage/"+imgname+suffix);
                String kg = page.getHtml().xpath("/html/body/main/div/div[1]/div/div[2]/section/div["+ (j+1) +"]/div/div/div[1]/div/a/div/p[3]/strong/span[2]/text()").get();
                if (kg ==null){
                    kg = "";
                }
                String pricess = price.get(j) + "جنيه " + kg.trim();
                p.setAlex_price(pricess);
                p.setPicurl(imgurl.get(j));
                //下载图片
                String picpath = imgpath + "/" + imgname+suffix;
                try {
                    ImagesDownloadUtil.downloadPicture(imgurl.get(j), picpath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //存数据库
                csbean.addproductinfo(p);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Category c =new Category();
        c.setAlex_cname_ar("Vegetables");
        c.setAlex_cpid(2);
        c.setHrefstr("/mafegy/en/Fresh-Food/Fruits-VegetablesVegetables/c/FEGY1660500");
        String url = "https://www.carrefouregypt.com" + c.getHrefstr(); //https://www.carrefouregypt.com/mafegy/en/Fresh-Food/Fruits-VegetablesVegetables/c/FEGY1660500
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CarrefourProductDataProcesser())
                .addUrl(url)
                .thread(5).run();

    }
}
