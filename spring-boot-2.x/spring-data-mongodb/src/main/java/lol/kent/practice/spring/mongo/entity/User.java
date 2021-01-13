package lol.kent.practice.spring.mongo.entity;

import java.util.Date;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年08月25日 14:13
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */

@Document("user")
public class User {

    public static final String WORD = "酱油";

    @Id
    private String id;

    private String name;

    private Integer age;

    private String mail;

    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    private Date updatedTime;

    @CreatedBy
    private String createdUserId;

    @LastModifiedBy
    private String upatedUserId;

    public User() {
    }

    public User(String id, String name, Integer age, String mail) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public String getUpatedUserId() {
        return upatedUserId;
    }

    public void setUpatedUserId(String upatedUserId) {
        this.upatedUserId = upatedUserId;
    }
}
