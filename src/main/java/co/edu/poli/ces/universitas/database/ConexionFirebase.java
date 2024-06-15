package co.edu.poli.ces.universitas.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class ConexionFirebase {
    public ConexionFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Santiago Torres\\Desktop\\CES3\\Clase1\\universitas\\src\\main\\resources\\serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            System.out.println("Conexion to Firebase");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
