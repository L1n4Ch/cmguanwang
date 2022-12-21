package com.ruoyi.cm.service.impl;

import com.ruoyi.cm.service.IEmailService;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
public class CmEmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public void sendMail(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();// 新建邮
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);// 编辑发送内容
        try {
            Properties javaMailProperties = mailSender.getJavaMailProperties();
            if(StringUtils.isEmpty(to)){
                to = javaMailProperties.getProperty("to");
            }
            messageHelper.setFrom(javaMailProperties.getProperty("from"));
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text);
            log.info("邮件发送成功！");
            mailSender.send(message);// 发送
        } catch (MessagingException e1) {
            log.error("邮件发送失败",e1.getMessage());
        }catch (Exception e2){
            log.error("邮件发送失败",e2.getMessage());
        }

    }

    /**
     * 创建图片
     * @param imgFile
     * @return
     * @throws Exception
     */
    private MimeBodyPart getImage(File imgFile) throws Exception{
        // 创建图片"节点"
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource(imgFile));
        // 将图片数据添加到"节点"
        image.setDataHandler(dh);
        // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）
        image.setContentID("mailImgId");
        return image;
    }

    /**
     * 得到文本+ 图片的内容
     * @param text
     * @param image
     * @return
     */
    private MimeBodyPart getTxtImage(String text, MimeBodyPart image,String imgAddr)throws Exception {
        // 创建文本"节点"
        MimeBodyPart mp = new MimeBodyPart();

        //设置主体内容： 可优化
        mp.setContent(text+"<br/><a href='"+imgAddr +"'><img src='cid:mailImgId'/></a>", "text/html;charset=UTF-8");


        // （文本+图片）设置 文本 和 图片"节点"的关系（将 文本 和 图片"节点"合成一个混合"节点"）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(mp);
        mm_text_image.addBodyPart(image);
        mm_text_image.setSubType("related");    // 关联关系
        // 将 文本+图片 的混合"节点"封装成一个普通"节点"
        // 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        // 上面的 mailImgId 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);
        return text_image;
    }

    /**
     * 封装附件
     * @param appendixs
     * @return
     */
    private List<MimeBodyPart> getAppendixs(File[] appendixs)throws Exception {
        if(appendixs == null || appendixs.length == 0){
            return null;
        }
        List<MimeBodyPart> list = new ArrayList<MimeBodyPart>();
        for(File f : appendixs){
            // 创建附件"节点"
            MimeBodyPart attachment = new MimeBodyPart();
            // 读取本地文件
            DataHandler dh2 = new DataHandler(new FileDataSource(f));
            // 将附件数据添加到"节点"
            attachment.setDataHandler(dh2);
            // 设置附件的文件名（需要编码）
            attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
            list.add(attachment);
        }
        return list;
    }
    @Override
    public void sendMail(String to, String subject, String text, File imgFile, String imgAddr, File[] appendixs) {
        MimeMessage message = mailSender.createMimeMessage();// 新建邮件
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);// 编辑发送内容
        try {
            Properties javaMailProperties = mailSender.getJavaMailProperties();
            if(StringUtils.isEmpty(to)){
                to = javaMailProperties.getProperty("to");
            }
            messageHelper.setFrom(javaMailProperties.getProperty("from"));
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            //得到图片对象
            MimeBodyPart image =  getImage(imgFile);
            //得到文本+ 图片的内容
            MimeBodyPart text_img = getTxtImage(text,image,imgAddr);

            //得到附件
            List<MimeBodyPart> attachmentList = getAppendixs(appendixs);

            // 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
            MimeMultipart mm = new MimeMultipart();
            mm.addBodyPart(text_img);
            if(attachmentList !=null){
                for(MimeBodyPart attachment: attachmentList){
                    mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
                }
            }
            // 混合关系
            mm.setSubType("mixed");
            // 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
            message.setContent(mm);

            log.info("发送成功");
            mailSender.send(message);// 发送
        } catch (MessagingException e) {
            log.error("邮件发送失败",e.getMessage());
        }catch (Exception e){
            log.error("邮件发送失败",e.getMessage());
        }
    }
}
