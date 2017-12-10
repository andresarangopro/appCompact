package com.example.felipearango.appcompact.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.felipearango.appcompact.activitys.Activity_Principal;
import com.example.felipearango.appcompact.activitys.FragmentCreateClassroom;
import com.example.felipearango.appcompact.clases.Aula;
import com.example.felipearango.appcompact.clases.Entregable;
import com.example.felipearango.appcompact.clases.Usuario_estudiante;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.felipearango.appcompact.activitys.Activity_Login.TIPO_USUARIO;
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
    public ProgressDialog progressDialog;
    public FirebaseUser firebaseUser;


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
     *  @param childDatabaseR direccion del nodo donde se guardará el objeto
     * @param key            llave primaria del objeto
     * @param object         objeto a guardar
     */
    public Task<Void> insertar(String childDatabaseR, String key, Object object) {
        return databaseReference.child(childDatabaseR).child(key).setValue(object);
    }

    public ArrayList<Entregable> insertarEntregable(final String childDatabaseR, final String key, Object object, final ArrayList<Entregable> listEntre) {

       databaseReference.child(childDatabaseR).child(key).setValue(object).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               Log.e("Tam", listEntre.size()+"");
               for (Entregable entregabl   :   listEntre) {
                   databaseReference.child(childDatabaseR).child(key).child("entregable").child(entregabl.getId()).setValue(entregabl);
               }

           }
       });
        ArrayList<Entregable> po = new ArrayList<>();
       return po;

    }

    public void ingresar(String email, String password, final Context context) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    account(context);
                    Toast.makeText(context, "Correcto", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(context, "Por favor verifique su usuario y contraseña", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

   public void inicializatedFireBase(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (!calledAlready) {
            firebaseDatabase.setPersistenceEnabled(true);
            calledAlready = true;
        }
        databaseReference = firebaseDatabase.getReference();
    }

    public void account(final Context context){
        eventSelect("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                int tipo_usuario = Integer.parseInt(dataSnapshot.child(user.getUid()).child("tipoUser").getValue().toString());
                TIPO_USUARIO = tipo_usuario;
                context.startActivity(new Intent(context, Activity_Principal.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /**
     * Metodo que selecciona un dato referenciado el cua se pasa por parametro
     * @param usChildString
     */
    public DatabaseReference eventSelect( String usChildString){
       return databaseReference.child(usChildString);
    }

    public void registrarUser(final String email, final String password, final Context context, final int usuario_nuevo){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Registrando usuario, por favor espera");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ingresarNuevo(email, password, context, usuario_nuevo);
                }else{
                    progressDialog.dismiss();
                }

            }
        });
    }

    private void ingresarNuevo(String email, String password, final Context context, final int usuario_nuevo) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Usuario_estudiante uE = new Usuario_estudiante(usuario_nuevo, user.getEmail(),user.getUid());
                    insertar("Users",user.getUid(),uE).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                TIPO_USUARIO = usuario_nuevo;
                                context.startActivity(new Intent(context, Activity_Principal.class));
                                progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                            }

                        }
                    });
                }else{

                }
            }
        });
    }

    public void registrarAula(String nombre, String descripcion, ArrayList<String> lstIntegrantes, String key, ArrayList<String> lstEstudiantes){
        Aula aula = new Aula(firebaseUser.getEmail(), nombre, descripcion, lstIntegrantes, FragmentCreateClassroom.key_aula);
        insertar("Aulas", key, aula);

        //Agregar aula a profesor
        databaseReference.child("Users").child(firebaseUser.getUid()).child("Aulas").push().setValue(key);

        //Agregar aula a estudiantes
        for (String estudiante:
             lstEstudiantes) {
            databaseReference.child("Users").child(estudiante).child("Aulas").push().setValue(key);
        }
    }


}
