package com.jiaxun.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("dept")
public class DeptSecond {

    @XStreamAlias("deptid")
    @XStreamAsAttribute
    private String deptid;

    @XStreamAlias("deptName")
    @XStreamAsAttribute
    private String deptName;

    @XStreamAlias("superiorDept")
    @XStreamAsAttribute
    private String superiorDept;

    @XStreamAlias("pos")
    @XStreamAsAttribute
    private String pos;

    @XStreamImplicit(itemFieldName="user")
    private List<User> users;
}
