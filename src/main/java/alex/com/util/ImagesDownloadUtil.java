package alex.com.util;

import alex.com.entity.ProductInfo;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ImagesDownloadUtil {

    static String usrl = "https://cdnprod.mafretailproxy.com/cdn-cgi/image/format=auto,onerror=redirect/sys-master-prod/hae/h2d/9342453088286/414810_main.jpg_200Wx200H";
    static String imgpath = "E:\\carrimage/123456.jpg";
    static String useragent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)";
    /**
     *
     * @param fromurl
     * @param topath
     */
    public static void downloadPicture(String fromurl, String topath) throws IOException {
        CloseableHttpClient client = null;
        try {
            client = HttpConnectionManager.getHttpClient(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpGet get = new HttpGet(URI.create(fromurl));
        get.addHeader("Accept","*/*");
        String paths = fromurl.substring(fromurl.indexOf("/"));
        get.addHeader("path",paths);
        get.addHeader("authority","cdnprod.mafretailproxy.com");
        get.addHeader("scheme","https");
        get.addHeader("User-Agent",useragent);
        CloseableHttpResponse resp = null;
        File outputAudio = new File(topath);
        try {
            // 执行请求
            resp = client.execute(get);
            // 判断返回状态是否为200
            if (resp.getStatusLine().getStatusCode() == 200) {
                //内容写入文件
                HttpEntity entity = resp.getEntity();
                if (entity.getContent() != null) {
                    FileOutputStream fos = new FileOutputStream(outputAudio);
                    entity.writeTo(fos);
                    fos.flush();
                    fos.close();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (resp.getEntity() != null){
                resp.getEntity().getContent().close();
            }
            if (resp != null) {
                resp.close();
            }
        }
    }

    public static String gettimemillion(){
        return System.currentTimeMillis() + "";
    }

    public static void main(String[] args) throws IOException {

        downloadPicture(usrl, imgpath);
        System.out.println("下载成功!");
    }
}
