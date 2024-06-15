package co.edu.poli.ces.universitas.repositories;

import co.edu.poli.ces.universitas.dao.Task;
import co.edu.poli.ces.universitas.dao.User;
import co.edu.poli.ces.universitas.database.ConexionFirebase;
import co.edu.poli.ces.universitas.database.Crud;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class TaskRepository extends ConexionFirebase implements Crud<Task,String> {


    @Override
    public void insert(Task task) {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference documentTask = db.collection("tasks").document();

        Map<String, Object> data = new HashMap<>();

        data.put("nameTask",task.getNameTask());
        data.put("createdAt", new Date());

        ApiFuture<WriteResult> result = documentTask.set(data);

        try {
            System.out.println("El momento en el que se actualizo user fue " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No se pudo insertar...");
        }

        System.out.println("Conexion to Firebase");

    }

    @Override
    public boolean update(Task user) {
        return false;
    }

    @Override
    public List<Task> get() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionTasks = db.collection("tasks");
        ApiFuture<QuerySnapshot> querySnapchot = collectionTasks.get();

        List<QueryDocumentSnapshot> documents = querySnapchot.get().getDocuments();

        for (QueryDocumentSnapshot document: documents){
            tasks.add(new Task(document.getId(),(String) document.getData().get("nameTask")));
        }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error");
        }
        return null;
    }

    @Override
    public Task getOne(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("tasks").document(String.valueOf(id));
        return null;
    }

    public Task getOneJ(String document) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("tasks").document(document);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot task = future.get();
            if (task.exists()){
                return new Task(task.getId(), (String) task.getData().get("nameTask"));
            } else{
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Task lookTask = new TaskRepository().getOneJ("PI3ZbwoSEEoQMKanrrvP");
        System.out.println(lookTask.getNameTask());
    }
}
