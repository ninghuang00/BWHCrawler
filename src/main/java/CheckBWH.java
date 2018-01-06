import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangning on 2018/1/5.
 */
public class CheckBWH {
    //bwh地址
    public final static String url = "https://bwh1.net/vps-hosting.php";
    //想要访问的元素路径
    private String xpath = "//*[@id='hostingplans']/div/div[2]/ul[2]/li";
    //没有存货
    public static String NOSTOCK = "KVM: no stock";

    public String check(){
        int retryCount = 10;

        WebDriver driver = new HtmlUnitDriver(BrowserVersion.getDefault());

        while (retryCount > 0) {
            try {
                driver.get(url);
                //隐式等待
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                //通过xpath获取元素对象
                WebElement content = driver.findElement(By.xpath(xpath));
                //得到元素对象包含的文本
                String result = content.getText();

                System.out.println(driver.getTitle());
                System.out.println(content.getText());

                driver.quit();
                retryCount = 0;
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("retry count is " + retryCount);
                retryCount--;
            }
        }
        System.out.println("out of while in check");
        return CheckBWH.NOSTOCK;
    }

    /*public static void main(String[] args) {
        CheckBWH check = new CheckBWH();
        String resultString = check.check();

        SendMail mail = new SendMail();
        String to = "377075700@qq.com";//目标邮箱1
        String to2 = "597941116@qq.com";//目标邮箱2
        String content = "购买url:" + url;


        if(!resultString.equals(CheckBWH.NOSTOCK)){
            int retryCount = 3;
            System.out.println(" send email ...");
            while (retryCount > 0) {
                try {
                    mail.sendMail(to,content);
                    mail.sendMail(to2,content);
                    retryCount = 0;
                } catch (MessagingException e) {
                    e.printStackTrace();
                    retryCount--;

                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                    retryCount--;
                }
            }

        }

    }*/

}

