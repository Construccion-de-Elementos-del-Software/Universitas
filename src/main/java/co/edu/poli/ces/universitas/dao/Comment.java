package co.edu.poli.ces.universitas.dao;

import java.util.ArrayList;
import java.util.Date;

public class Comment {
    private String document;
    private String mail;
    private String fullName;
    private Date createdAt;
    private TypesComment type;
    private String comment_text;
    private ArrayList<Reply> replies;

    public Comment(String mail, String fullName, Date createdAt, TypesComment type, String comment_text) {
        this.mail = mail;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.type = type;
        this.comment_text = comment_text;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public TypesComment getType() {
        return type;
    }

    public void setType(TypesComment type) {
        this.type = type;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Reply> replies) {
        this.replies = replies;
    }
}
