package com.travelone.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

public class SendEmail extends Thread {
    static Properties pro;
    private String from;
    private String title;
    private String content;

    public SendEmail(String from, String title, String content){
        this.from = from;
        this.title = title;
        this.content = content;
    }

    @Override
    public void run() {
        pro = new Properties();
        InputStream input = SendEmail.class.getClassLoader().getResourceAsStream("email.properties");
        try {
            pro.load(input);
            pro.setProperty("mail.host", pro.getProperty("mail.smtp.host"));
            pro.setProperty("mail.transport.protocol", "smtp");
            pro.setProperty("mail.smtp.auth", "true");

            Session session = Session.getInstance(pro);
            Transport ts = session.getTransport();
            ts.connect(pro.getProperty("mail.smtp.host"), pro.getProperty("mail.send.user"), pro.getProperty("mail.send.password"));

            MimeMessage message = createSimpleMail(session);
            ts.sendMessage(message,message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public MimeMessage createSimpleMail(Session session)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(pro.getProperty("mail.send.user")));

        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(from));
        //邮件的标题
        message.setSubject(title);
        //邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }

}
