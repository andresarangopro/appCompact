package com.example.felipearango.appcompact.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.felipearango.appcompact.Fragments.FragmentChat;
import com.example.felipearango.appcompact.Fragments.FragmentChooseChallenge;
import com.example.felipearango.appcompact.Fragments.FragmentCreateClassroom;
import com.example.felipearango.appcompact.Fragments.FragmentMyClassroom;
import com.example.felipearango.appcompact.Fragments.FragmentPerfil;
import com.example.felipearango.appcompact.Fragments.FragmentRetos;
import com.example.felipearango.appcompact.R;
import com.example.felipearango.appcompact.util.ManejoUser;

public class Activity_Principal extends AppCompatActivity   implements  NavigationView.OnNavigationItemSelectedListener{

    Button btnPublicarReto, btnVisualizarReto, btnChat;
    ManejoUser mn = new ManejoUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mn.inicializatedFireBase();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        FrameLayout fl = (FrameLayout) findViewById(R.id.FrFragments);
        fl.removeAllViews();
        manager.popBackStack();
        getFragmentManager().popBackStack();
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_perfil:{
                fragment = new FragmentPerfil();
                break;
            }
            case R.id.nav_publicar_retos:{
                fragment = new FragmentRetos();
                break;
            }
            case R.id.nav_mis_retos:{
                fragment = new FragmentChooseChallenge();
                break;
            }
            case R.id.nav_chat:{
                fragment = new FragmentChat();
                break;
            }
            case R.id.nav_aulas:{
                fragment = new FragmentMyClassroom();
                break;
            }
            case  R.id.nav_share:{
                fragment = new FragmentCreateClassroom();
                break;
            }
            case R.id.nav_send:{
                cerrarSesion();
                break;
            }
        }

        if (fragment != null) {
            transaction.replace(R.id.FrFragments, fragment);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    public void cerrarSesion(){
       mn.firebaseAuth.signOut();
       startActivity(new Intent(this, Activity_Login.class));
        finish();
    }

}
