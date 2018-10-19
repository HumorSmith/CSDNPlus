
package com.ifreedomer.cplus.mail;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMailSender {

    /**
     * 发送文本邮件
     *
     * @param mailInfo 待发送的邮件的内容
     */
    public boolean sendTextMail(final MailSenderInfo mailInfo) {
        if (mailInfo == null) {
            return false;
        }
        // 创建邮件发送会话，并且构建认证
        Session sendMailSession = Session.getDefaultInstance(mailInfo.getProperties(),
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailInfo.getUserName(), mailInfo
                                .getPassword());
                    }
                });
        try {
            // 将收件人地址转换成Addres 数组
            Address[] toAddressSet = null;
            if (mailInfo.getToAddressList() != null && mailInfo.getToAddressList().length > 0) {
                toAddressSet = new Address[mailInfo.getToAddressList().length];
                for (int i = 0; i < mailInfo.getToAddressList().length; i++) {
                    Address toAddress = new InternetAddress(mailInfo.getToAddressList()[i]);
                    toAddressSet[i] = toAddress;
                }

            } else
                return false;

            // 封装邮件消息主体
            Message message = new MimeMessage(sendMailSession);
            message.setFrom(new InternetAddress(mailInfo.getFromAddress()));
            message.setRecipients(Message.RecipientType.TO, toAddressSet);
            message.setSubject(mailInfo.getSubject());
            message.setText(mailInfo.getContent());

            // 开始发送邮件
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
