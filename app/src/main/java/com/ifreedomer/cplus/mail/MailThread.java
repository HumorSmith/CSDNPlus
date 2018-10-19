
package com.ifreedomer.cplus.mail;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;

/**
 * 该类用于发送邮件给指定的邮箱
 *
 * @author wanghexin
 * @creation 2015年8月26日
 */
public class MailThread extends Thread {
    private MailStateListener mailStateListener;
    private final Context ctx;
    private final String content;
    private final String tag;

    public MailStateListener getMailStateListener() {
        return mailStateListener;
    }

    public void setMailStateListener(MailStateListener mailStateListener) {
        this.mailStateListener = mailStateListener;
    }

    private final String sender;
    private final String[] toAddressSet = new String[]{
            "3383813446@qq.com"
    };

    /**
     * 发送邮件线程
     *
     * @param ctx
     * @param content 邮件主题内容
     */
    public MailThread(Context ctx, String TAG, String content, String sender) {
        this.ctx = ctx;
        this.tag = TAG;
        this.content = content;
        this.sender = sender;
    }

    private void sendEmail() {
        try {
            MailSenderInfo mailInfo = new MailSenderInfo();
            mailInfo.setMailServerHost("smtp.163.com");
            mailInfo.setMailServerPort("25");
            mailInfo.setValidate(true);
            mailInfo.setUserName("moonvsky888@163.com");
            mailInfo.setPassword("wd613923");
            mailInfo.setFromAddress("moonvsky888@163.com");
            mailInfo.setToAddressList(toAddressSet);
            mailInfo.setSubject(tag);
            try {
                mailInfo.setContent("title=>"+tag+"\n\ncontent=>"+content+"\n\nsender="+sender+"\n\ndevice_model:"
                        + Build.MODEL
                        + "\nversion_release:"
                        + Build.VERSION.RELEASE
                        + "\nUserID:"
                        + "\nVersion:"
                        + ctx.getPackageManager().getPackageInfo(
                        ctx.getPackageName(), 0).versionName
                        + "\nPackageName:" + ctx.getPackageName()
                        + "\nLogInfo:" + content);
            } catch (NameNotFoundException e) {
                mailInfo.setContent("device_model:" + Build.MODEL
                        + "\nversion_release:" + Build.VERSION.RELEASE
                        + "\nVersion:unKnown" + "\nPackageName:"
                        + ctx.getPackageName() + "\nLogInfo:" + content);
            }
            SimpleMailSender sms = new SimpleMailSender();
            MailcapCommandMap mc = (MailcapCommandMap) CommandMap
                    .getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);
            sms.sendTextMail(mailInfo);
            if (mailStateListener!=null){
                mailStateListener.onSuccess();
            }
        } catch (Exception e) {
            if (mailStateListener!=null){
                mailStateListener.onError();
            }
            e.getStackTrace();
        }

    }

    @Override
    public void run() {
        sendEmail();
        super.run();
    }
}
