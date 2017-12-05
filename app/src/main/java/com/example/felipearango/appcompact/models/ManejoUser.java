package com.example.felipearango.appcompact.models;

import android.content.Context;
import android.content.Intent;

import com.example.felipearango.appcompact.activitys.Activity_Principal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.felipearango.appcompact.activitys.Activity_Login.calledAlready;


/**
 * Created by Felipe Arango on 29/11/2017.
 */

public class ManejoUser {

    /////////////////////
    //Variables
    ////////////////////

    public DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;



    ///////////////////
    //Constructor
    //////////////////

    public ManejoUser() {
    }

    /////////////////////
    //Metodos
    ////////////////////

    /**
     * Metodo que inserta cualquier objeto en la base de datos en el nodo que se pasa como
     * parameto
     *
     * @param childDatabaseR direccion del nodo donde se guardará el objeto
     * @param key            llave primaria del objeto
     * @param object         objeto a guardar
     */
    public void insertar(String childDatabaseR, String key, Object object) {
        databaseReference.child(childDatabaseR).child(key).setValue(object);
    }

   public void inicializatedFireBase(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        if (!calledAlready) {
            firebaseDatabase.setPersistenceEnabled(true);
            calledAlready = true;
        }
        databaseReference = firebaseDatabase.getReference();
    }

    public void account(final Context context){
      // if(TIPO_USUARIO == usuario_empresa)
        context.startActivity(new Intent(context, Activity_Principal.class));
       // Toast.makeText(getApplicationContext(), "Sesion iniciada con email: " + user.getEmail(), Toast.LENGTH_LONG).show();
    }

    /**
     * Metodo que selecciona un dato referenciado el cua se pasa por parametro
     * @param context
     * @param usChildString
     */
    public void eventSelect(final Context context, String usChildString){
        databaseReference.child(usChildString);
    }
}
