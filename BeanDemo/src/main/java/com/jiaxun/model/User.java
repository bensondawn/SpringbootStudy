package com.jiaxun.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("user")
public class User {

    @XStreamAsAttribute
    @XStreamAlias("userID")
    private String userID;

    @XStreamAsAttribute
    @XStreamAlias("deptID")
    private int deptID;

    @XStreamAsAttribute
    @XStreamAlias("pos")
    private int pos;

    @XStreamAsAttribute
    @XStreamAlias("channelid")
    private String channelid;

    @XStreamAsAttribute
    @XStreamAlias("channelName")
    private String channelName;

    @XStreamAsAttribute
    @XStreamAlias("callNum")
    private String callNum;

    @XStreamAsAttribute
    @XStreamAlias("cooperateNum")
    @XStreamOmitField
    private int cooperateNum;

    @XStreamAsAttribute
    @XStreamAlias("ptzLevel")
    private int ptzLevel;

    @XStreamAsAttribute
    @XStreamAlias("talkLevel")
    private int talkLevel;

    @XStreamAsAttribute
    @XStreamAlias("restart")
    private int restart;

    @XStreamAsAttribute
    @XStreamAlias("modify")
    private int modify;

    @XStreamAsAttribute
    @XStreamAlias("preset")
    private int preset;

    @XStreamAlias("status")
    @XStreamAsAttribute
    private String status;

}
