package co.edu.poli.ces.universitas.dao;

import java.util.Date;

public class Reply {
    private String mail;
    private String fullName;
    private Date createdAt;
    private TypesComment type;
    private String comment_text;
    private int student_id;

    public Reply(String mail, String fullName, Date createdAt, TypesComment type, String comment_text, int student_id) {
        this.mail = mail;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.type = type;
        this.comment_text = comment_text;
        this.student_id = student_id;
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

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
