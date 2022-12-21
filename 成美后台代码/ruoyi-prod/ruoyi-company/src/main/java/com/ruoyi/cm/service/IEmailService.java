package com.ruoyi.cm.service;

import java.io.File;

/**
 * 邮件服务
 */
public interface IEmailService {
    /**
     * 纯文本内容的邮件服务
     * @param to 接收者
     * @param subject 主题
     * @param text  内容
     */
    void sendMail(String to,String subject,String text);

    /**
     * 带图片和多附件的邮件服务
     * @param to 接收者
     * @param subject 主题
     * @param text 文字内容
     * @param imgFile 图片文件
     * @param imgAddr 点击图片的地址
     * @param appendixs 多个附件
     */
    void sendMail(String to, String subject, String text, File imgFile, String imgAddr,File[] appendixs);
}
