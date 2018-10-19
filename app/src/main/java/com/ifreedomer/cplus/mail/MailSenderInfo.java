
package com.ifreedomer.cplus.mail;

import java.util.Properties;

public class MailSenderInfo {

    // 发??邮件的服务器的IP和端???
    private String mailServerHost;
    private String mailServerPort = "25";
    // 邮件发??者的地址
    private String fromAddress;
    // 邮件接收者的地址
    private String[] toAddressList = new String[]{"3383813446@qq.com"};
    // 登陆邮件发??服务器的用户名和密码
    private String userName;
    private String password;
    // 是否?????身份验证
    private boolean validate = false;
    // 邮件主题
    private String subject;
    // 邮件的文本内???
    private String content;
    // 邮件附件的文件名
    private String[] attachFileNames;
    /**
     * protocol 邮件发送协议 imap 或者 smtp
     */
    private String protocol = "smtp";

    /**
     * 获得邮件会话属??
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.transport.protocol", this.protocol);
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] fileNames) {
        this.attachFileNames = fileNames;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getToAddressList() {
        return toAddressList;
    }

    public void setToAddressList(String[] toAddress) {
        this.toAddressList = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String textContent) {
        this.content = textContent;
    }

    public String getProtocol() {
        return protocol;
    }

    /**
     * 设置邮件发送协议 “smtp”或者“imap”
     * 
     * @param protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

}
