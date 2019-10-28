package alex.com.testdemo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by Tiffany on 2019-10-15.
 */
public class CsdnBlogProcessor implements PageProcessor {

    private static String username = "yixiao1874";// 设置csdn用户名
    private static int size = 0;// 共抓取到的文章数量

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);
    private int x;
    @Override
    public void process(Page page) {
        String s = page.getUrl().get();
        if (!page.getUrl().regex("http://blog.csdn.net/" + username + "/article/details/\\d+").match()) {
            //获取当前页码
            x +=1;
            String number = page.getHtml().xpath("//*[@class='ui-paging-container']/ul/li[@class='focus']/text()").toString();
            if (number == null){
                number = "1";
            }
            //匹配当前页码+1的页码也就是下一页，加入爬取列表中
            String targetUrls = page.getHtml().links()
                    .regex("http://blog.csdn.net/"+username+"/article/list/"+(Integer.parseInt(number)+1))
                    .get();
            if (x != 2){
                page.addTargetRequest(targetUrls);
            }

            List<String> detailUrls = page.getHtml().xpath("//div[@class='article-item-box csdn-tracking-statistics']//h4//a/@href").all();
            for(String list :detailUrls){
                System.out.println(list);
            }
            page.addTargetRequests(detailUrls);
        }else {
            size++;// 文章数量加1
            CsdnBlog csdnBlog = new CsdnBlog();
            String path = page.getUrl().get();
            int id = Integer.parseInt(path.substring(path.lastIndexOf("/")+1));
            String title = page.getHtml().xpath("//h1[@class='csdn_top']/text()").get();
            String date = page.getHtml().xpath("//div[@class='artical_tag']//span[@class='time']/text()").get();
            String copyright = page.getHtml().xpath("//div[@class='artical_tag']//span[@class='original']/text()").get();
            int view = Integer.parseInt(page.getHtml().xpath("//button[@class='btn-noborder']//span[@class='txt']/text()").get());
            csdnBlog.id(id).title(title).date(date).copyright(copyright).view(view);
            System.out.println(csdnBlog);
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // 从用户博客首页开始抓，开启5个线程，启动爬虫
        Spider.create(new CsdnBlogProcessor())
                .addUrl("http://blog.csdn.net/" + username)
                .thread(5).run();
        System.out.println("文章总数为"+size);
    }
}
