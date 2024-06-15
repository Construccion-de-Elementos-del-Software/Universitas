package co.edu.poli.ces.universitas.dao;

public class Task {
    private String document;
    private String nameTask;

    public Task() {
    }

    public Task(String document, String nameTask) {
        this.document = document;
        this.nameTask = nameTask;
    }

    public Task(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}
