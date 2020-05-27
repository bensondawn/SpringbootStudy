package com.jiaxun.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("contacts")
public class Contacts {

    @XStreamAlias("version")
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("xmlns")
    @XStreamAsAttribute
    @XStreamOmitField
    private String xmlns;

    @XStreamImplicit(itemFieldName="contactsList")
    private List<ContactsList> contactsLists;
}
