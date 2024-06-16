package co.edu.poli.ces.universitas.dao;

public class Enrollment {
    private int id;
    private String nameCourse;
    private String semester;
    private String status;
    private int id_student;

    public Enrollment(int id, String nameCourse, String semester, String status, int id_student) {
        this.id = id;
        this.nameCourse = nameCourse;
        this.semester = semester;
        this.status = status;
        this.id_student = id_student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }
}
