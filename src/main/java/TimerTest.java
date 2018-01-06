import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huangning on 2018/1/6.
 */
public class TimerTest {
    static class MyTask extends TimerTask {

        public void run() {

            CheckBWH check = new CheckBWH();
            String resultString = check.check();

            SendMail mail = new SendMail();
            String to = "377075700@qq.com";//目标邮箱1
            String to2 = "597941116@qq.com";//目标邮箱2
            String content = "购买url:" + CheckBWH.url;

            //如果发现匹配到的字段不是no stock,就发送邮件
            if(!resultString.equals(CheckBWH.NOSTOCK)){
                //发送邮件重试次数
                int retryCount = 10;
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
//            System.out.println("start program...");
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 1000, 1000 * 60);
    }


}
