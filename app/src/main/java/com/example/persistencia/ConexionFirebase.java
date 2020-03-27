/*package com.example.persistencia;

/*import com.example.proyecto1.logica.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*public class ConexionFirebase {
    public static ConexionFirebase instancia = null;
    public static DatabaseReference mDatabase;

    public static ConexionFirebase getInstance() {
        if (instancia == null){
            instancia = new ConexionFirebase();
        }
        return instancia;
    }

    public void enviar(String coleccion, Usuario usuario){
        ConexionFirebase.mDatabase = FirebaseDatabase.getInstance().getReference();
        ConexionFirebase.mDatabase.child(coleccion).child(String.valueOf(usuario.getId())).setValue(usuario);
    }
}
