package ar.com.psychic_chainsaw.app.model.user.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class User {

    @Id
    private String id;
    private String emailAddress;
    private String password;
    private Date creationTS;

    public User() {
        this.creationTS = new Date();
    }

    public String getID() {
        return id;
    }

    public User setID(String id) {
        this.id = id;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public User setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getCreationTS() {
        return creationTS;
    }

}
