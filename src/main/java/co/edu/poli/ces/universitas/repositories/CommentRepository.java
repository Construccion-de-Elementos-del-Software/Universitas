package co.edu.poli.ces.universitas.repositories;

import co.edu.poli.ces.universitas.dao.Comment;
import co.edu.poli.ces.universitas.dao.Reply;
import co.edu.poli.ces.universitas.dao.Task;
import co.edu.poli.ces.universitas.dao.TypesComment;
import co.edu.poli.ces.universitas.database.ConexionFirebase;
import co.edu.poli.ces.universitas.database.Crud;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CommentRepository extends ConexionFirebase implements Crud<Comment,String> {

    @Override
    public void insert(Comment comment) {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference documentComment = db.collection("comments").document();

        Map<String, Object> data = new HashMap<>();

        data.put("mail",comment.getMail());
        data.put("fullName", comment.getFullName());
        data.put("type",comment.getType());
        data.put("createdAt", new Date());
        data.put("comment_text",comment.getComment_text());
        data.put("replies",null);

        ApiFuture<WriteResult> result = documentComment.set(data);

        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No se pudo insertar...");
        }
    }

    @Override
    public boolean update(Comment comment) {
        return false;
    }

    @Override
    public List<Comment> get() {
        return null;
    }

    @Override
    public Comment getOne(String comment) {
        return null;
    }

    public boolean insertReply (String documentComment,Reply reply) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documnetRef = db.collection("comments").document(documentComment);

        ApiFuture<WriteResult>  future = documnetRef.update("replies", FieldValue.arrayUnion(reply));

        try {
            future.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            return false;
        }
    }
}
