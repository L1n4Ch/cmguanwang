package com.ruoyi.cm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 留言管理对象 cm_msg_manage
 *
 * @author ruoyi
 * @date 2021-05-24
 */
public class CmMsgManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long msgId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String msgUse;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String msgPhone;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String msgEmail;

    /** 留言内容 */
    @Excel(name = "留言内容")
    private String msgDetail;

    /** 翻译后的留言内容 */
    @Excel(name = "翻译留言")
    private String fyMsgDetail;

    /** 留言时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "留言时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date msgTime;

    /** 回复 */
    @Excel(name = "回复")
    private String msgAnswer;

    /** 翻译后的回复 */
    @Excel(name = "翻译回复")
    private String fyMsgAnswer;

    /** 回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date msgAnswerTime;

    public void setMsgId(Long msgId)
    {
        this.msgId = msgId;
    }

    public Long getMsgId()
    {
        return msgId;
    }
    public void setMsgUse(String msgUse)
    {
        this.msgUse = msgUse;
    }

    public String getMsgUse()
    {
        return msgUse;
    }
    public void setMsgPhone(String msgPhone)
    {
        this.msgPhone = msgPhone;
    }

    public String getMsgPhone()
    {
        return msgPhone;
    }
    public void setMsgEmail(String msgEmail)
    {
        this.msgEmail = msgEmail;
    }

    public String getMsgEmail()
    {
        return msgEmail;
    }
    public void setMsgDetail(String msgDetail)
    {
        this.msgDetail = msgDetail;
    }

    public String getMsgDetail()
    {
        return msgDetail;
    }

    public void setFyMsgDetail(String fyMsgDetail)
    {
        this.fyMsgDetail = fyMsgDetail;
    }
    public String getFyMsgDetail()
    {
        return fyMsgDetail;
    }

    public void setMsgTime(Date msgTime)
    {
        this.msgTime = msgTime;
    }

    public Date getMsgTime()
    {
        return msgTime;
    }
    public void setMsgAnswer(String msgAnswer)
    {
        this.msgAnswer = msgAnswer;
    }

    public String getMsgAnswer()
    {
        return msgAnswer;
    }
    public void setMsgAnswerTime(Date msgAnswerTime)
    {
        this.msgAnswerTime = msgAnswerTime;
    }

    public String getFyMsgAnswer()
    {
        return fyMsgAnswer;
    }
    public void setFyMsgAnswer(String fyMsgAnswer)
    {
        this.fyMsgAnswer = fyMsgAnswer;
    }

    public Date getMsgAnswerTime()
    {
        return msgAnswerTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("msgId", getMsgId())
            .append("msgUse", getMsgUse())
            .append("msgPhone", getMsgPhone())
            .append("msgEmail", getMsgEmail())
            .append("msgDetail", getMsgDetail())
            .append("fyMsgDetail", getFyMsgDetail())
            .append("msgTime", getMsgTime())
            .append("msgAnswer", getMsgAnswer())
            .append("fyMsgAnswer", getFyMsgAnswer())
            .append("msgAnswerTime", getMsgAnswerTime())
            .toString();
    }
}
