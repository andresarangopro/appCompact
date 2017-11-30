package com.example.felipearango.appcompact.models;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Felipe Arango on 29/11/2017.
 */

public class ManejoUser {

    /////////////////////
    //Variables
    ////////////////////

    public DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

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

    /**
     * Metodo que selecciona un dato referenciado el cua se pasa por parametro
     * @param context
     * @param usChildString
     */
    public void eventSelect(final Context context, String usChildString){
        databaseReference.child(usChildString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot obj : dataSnapshot.getChildren() ) {
                  //  Log.e("DAT",obj.getValue(Estudiante.class).getId()+"");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
