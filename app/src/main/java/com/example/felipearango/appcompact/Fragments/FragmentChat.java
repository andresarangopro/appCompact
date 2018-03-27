package com.example.felipearango.appcompact.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.activitys.Activity_Principal;
import com.example.felipearango.appcompact.clases.AdapterMensajes;
import com.example.felipearango.appcompact.clases.MensajeEnviar;
import com.example.felipearango.appcompact.clases.MensajeRecibir;
import com.example.felipearango.appcompact.util.ManejoUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class FragmentChat extends Fragment implements View.OnClickListener {

    //Variables
    private CircleImageView fotoPerfil;
    private TextView nombre;
    private RecyclerView rvMensajes;
    private EditText txtMensaje;
    private Button btnEnviar;
    private ImageButton btnEnviarFoto;
    private AdapterMensajes adapter;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ManejoUser mn = new ManejoUser();
    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;
    private String fotoPerfilCadena;
    private ArrayList<MensajeRecibir> listMn = new ArrayList<>();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_chat, container, false);
        Activity_Principal.toolbar.setTitle("Chat");
        initComponents(view);
        adapter = new AdapterMensajes(view.getContext(), listMn);
        LinearLayoutManager l = new LinearLayoutManager(view.getContext());
        rvMensajes.setLayoutManager(l);
        rvMensajes.setAdapter(adapter);
        mn.inicializatedFireBase();
        btnEnviar.setOnClickListener(this);
        btnEnviarFoto.setOnClickListener(this);
        fotoPerfil.setOnClickListener(this);
        listener();
        return view;
    }

    private void listener() {
        mn.databaseReference.child("salaChat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MensajeRecibir m = dataSnapshot.getValue(MensajeRecibir.class);
                    Log.e("ERR", m.getMensaje()+"1");
                    adapter.addMensaje(m);
                    adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        setScrollBar();
                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEnviar:
                MensajeEnviar m = new MensajeEnviar(txtMensaje.getText().toString(), nombre.getText().toString(),fotoPerfilCadena, "1", ServerValue.TIMESTAMP);
                mn.databaseReference.child("salaChat").push().setValue(m);
                txtMensaje.setText("");
                break;

            case R.id.btnEnviarFoto:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Selecciona una foto"), PHOTO_SEND);
                break;

            case R.id.fotoPerfil:
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),PHOTO_PERFIL);
                break;
        }
    }

    private void setScrollBar(){
        rvMensajes.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("imagenes_chat"); //Carpeta donde se guardaran las imagenes del chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());

            fotoReferencia.putFile(u).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    MensajeEnviar m = new MensajeEnviar(nombre.getText().toString() + " te ha enviado una foto", u.toString(), nombre.getText().toString(), fotoPerfilCadena, "2", ServerValue.TIMESTAMP);
                    mn.databaseReference.child("salaChat").push().setValue(m);
                }
            });
        } else if (requestCode == PHOTO_PERFIL && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("foto_perfil");//imagenes_chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    fotoPerfilCadena = u.toString();
                    Glide.with(FragmentChat.this).load(u.toString()).into(fotoPerfil);
                }
            });
        }
    }



    private void initComponents(View view){
        fotoPerfil = (CircleImageView) view.findViewById(R.id.fotoPerfil);
        nombre = (TextView) view.findViewById(R.id.tvNombre);
        rvMensajes = (RecyclerView) view.findViewById(R.id.rvMensajes);
        txtMensaje = (EditText) view.findViewById(R.id.txtMensaje);
        btnEnviar = (Button) view.findViewById(R.id.btnEnviar);
        btnEnviarFoto = (ImageButton) view.findViewById(R.id.btnEnviarFoto);
        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        nombre.setText(user.getEmail());
        fotoPerfilCadena = "";
    }
}
