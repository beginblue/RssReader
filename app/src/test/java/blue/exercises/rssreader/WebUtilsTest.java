package blue.exercises.rssreader;

import org.junit.Test;

import InternetIssues.WebUtils;

/**
 * Created by getbl on 2016/6/2.
 */
public class WebUtilsTest {
    @Test
    public void TestWebUtils() throws Exception {
        String res="nothing";
        WebUtils webUtils = new WebUtils("http://www.write.org.cn/");
        webUtils.execute();
        if(webUtils.checkFinish()) res = webUtils.getResult();
        else {
            Thread.sleep(3000);
            res=webUtils.getResult();
        }
        System.out.println(res);
//        try {
//            res=webUtils.getResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Thread.sleep(2000);
//            res=webUtils.getResult();
//        }
//        finally {
//            System.out.println(res);
//        }
    }

}
