import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by huangning on 2018/1/5.
 */
public class SendMail {
    public void sendMail(String to,String content) throws MessagingException, GeneralSecurityException {
        Properties props = new Properties();

// 开启debug调试
        props.setProperty("mail.debug", "true");
// 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
// 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
// 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

//邮件内容部分
        Message msg = new MimeMessage(session);
        msg.setSubject("搬瓦工vps购买提醒");
//        StringBuilder builder = new StringBuilder();
//        builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
//        builder.append("页面爬虫错误");
//        builder.append("\n data " + TimeTool.getCurrentTime());
        msg.setText(content);
//邮件发送者
        msg.setFrom(new InternetAddress("377075700@qq.com"));

//发送邮件
        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "377075700@qq.com", "heivruwfdvrrbjeh");

        transport.sendMessage(msg, new Address[]{new InternetAddress(to)});
        transport.close();
    }


    public static void main(String[] args) throws MessagingException, GeneralSecurityException {

        SendMail mail = new SendMail();
        String to = "377075700@qq.com";//目标邮箱1
        String to2 = "597941116@qq.com";//目标邮箱2
        String content = "购买url:" + "<a href=\"http://9.111.221.116:8080/CTAP/app/#/case\" target=\"_blank\">http://9.111<wbr>.221.116:808<wbr>0/CTAP/app/#<wbr>/case</a>";
        mail.sendMail(to,content);

    }
}
//<a href="http://9.111.221.116:8080/CTAP/app/#/case" target="_blank">http://9.111<wbr>.221.116:808<wbr>0/CTAP/app/#<wbr>/case</a>
//账号 18069866520@163.com
//账号 377075700@qq.com
//密码 Hn377075700
// heivruwfdvrrbjeh
