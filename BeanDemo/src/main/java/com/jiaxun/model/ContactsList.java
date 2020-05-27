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
@XStreamAlias("contactsList")
public class ContactsList {

    @XStreamAlias("contactsID")
    @XStreamAsAttribute
    private String contactsID;

    @XStreamAlias("contactsName")
    @XStreamAsAttribute
    private String contactsName;

    @XStreamImplicit(itemFieldName="dept")
    private List<DeptFirst> deptFirsts;
}
