package alex.com;

import alex.com.entity.Category;
import alex.com.entity.ProductInfo;
import alex.com.martproduct.CarrefourEgyptProcessor;
import alex.com.martproduct.CarrefourProductDataProcesser;
import alex.com.martproduct.service.CategoryService;
import alex.com.martproduct.service.ipml.CategoryServiceIpm;
import alex.com.util.ImagesDownloadUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductspiderApplicationTests {

	@Autowired
	private CategoryService cs;

	@Test
	public void contextLoads() {
		// 从用户博客首页开始抓，开启5个线程，启动爬虫
		Spider.create(new CarrefourEgyptProcessor())
				.addUrl("https://www.carrefouregypt.com/mafegy/ar/language/ar")
				.thread(5).run();
	}
	@Test
	public void fun1() {
		Category c =new Category();
		c.setAlex_cname_ar("الأجبان واللبنة");
		c.setAlex_cpid(15);
		c.setAlex_cid(19);
		c.setHrefstr("/mafegy/ar/Fresh-Food/Dairy-EggsCheese-Labneh/c/FEGY1630200");
		String url = "https://www.carrefouregypt.com" + c.getHrefstr(); //https://www.carrefouregypt.com/mafegy/en/Fresh-Food/Fruits-VegetablesVegetables/c/FEGY1660500
		// 从用户博客首页开始抓，开启5个线程，启动爬虫
		Spider.create(new CarrefourProductDataProcesser())
				.addUrl(url)
				.thread(5).run();
	}


	/**
	 * 443条
	 * pagesize = 10 ，共44页
	 *现在页数 1
	 */
	@Test
	public void fun2() {
		Integer page = 1;
		Integer pagestart = (page - 1) * 10;
		//List<Category> list = cs.getleveltx();
		List<Category> list = cs.getlevelt(pagestart);
		for (Category c : list){
			String url = "https://www.carrefouregypt.com" + c.getHrefstr(); //https://www.carrefouregypt.com/mafegy/en/Fresh-Food/Fruits-VegetablesVegetables/c/FEGY1660500
			// 从用户博客首页开始抓，开启5个线程，启动爬虫
			Spider.create(new CarrefourProductDataProcesser())
					.addUrl(url)
					.thread(5).run();
		}
	}

	@Test
	public void funx() {
		List<Category> list = cs.getleveltx();
		for (Category c : list){
			String url = "https://www.carrefouregypt.com" + c.getHrefstr(); //https://www.carrefouregypt.com/mafegy/en/Fresh-Food/Fruits-VegetablesVegetables/c/FEGY1660500
			// 从用户博客首页开始抓，开启5个线程，启动爬虫
			Spider.create(new CarrefourProductDataProcesser())
					.addUrl(url)
					.thread(5).run();
		}
	}

	@Test
	public void fun3() {
		Category c = cs.getbycid(6);
		String url = "https://www.carrefouregypt.com" + c.getHrefstr(); //https://www.carrefouregypt.com/mafegy/en/Fresh-Food/Fruits-VegetablesVegetables/c/FEGY1660500
		// 从用户博客首页开始抓，开启5个线程，启动爬虫
		Spider.create(new CarrefourProductDataProcesser())
				.addUrl(url)
				.thread(5).run();
	}
	@Test
	public void fun4() {
		String str = " qwe wer 123 ";
		System.out.println(str.trim());
	}

	@Test
	public void fun5c() throws IOException {
		String imgname = "1571584790481.jpg";
        List<ProductInfo> infos = cs.getpcurlbyname(imgname);
        if (infos.size() > 1){
            for (ProductInfo p : infos){
                String str = "E:\\carrimage/" + p.getAlex_imgname();
                File f = new File(str);
                if (f.exists()){
                    f.delete();
                }
                String suf = p.getAlex_imgname().substring(p.getAlex_imgname().lastIndexOf("."));
                String imgna = ImagesDownloadUtil.gettimemillion() + suf;
                p.setAlex_imgname(imgna);
                String topath = "E:\\carrimage/" + imgna;
                p.setAlex_imgurl("/carrimage/" + imgna);
                cs.updatep(p);
                ImagesDownloadUtil.downloadPicture(p.getPicurl(), topath);
            }
        }
        if (infos.size() == 1){
            ProductInfo info = infos.get(0);
            String originfile = "E:\\carrimage/" + info.getAlex_imgname();
            File file = new File(originfile);
            if (file.exists()){
                file.delete();
            }
            ImagesDownloadUtil.downloadPicture(info.getPicurl(),originfile);
        }

	}

}
