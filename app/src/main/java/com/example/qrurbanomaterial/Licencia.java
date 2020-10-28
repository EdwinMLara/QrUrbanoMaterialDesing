package com.example.qrurbanomaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;


public class Licencia extends AppCompatActivity {

    private static final String TAG = Licencia.class.getSimpleName();

    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar toolbar;
    private LicenciaModel li = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licencia);

        bottomNavigationView = findViewById(R.id.button_navigation);
        toolbar = findViewById(R.id.main_toolbar);

        Intent intenGetStringJsonLicencia = getIntent();
        if(intenGetStringJsonLicencia.hasExtra("jsonStringLicencia")){
            String datos = intenGetStringJsonLicencia.getStringExtra("jsonStringLicencia");
            Log.v(TAG,datos);
            Gson gson = new Gson();
            li = gson.fromJson(datos,LicenciaModel.class);
            Fragment incialFragment = fragmentLoadTransaction(new SolicitanteFragment());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder,incialFragment).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.page1:
                        selectedFragment = fragmentLoadTransaction(new SolicitanteFragment());
                        break;
                    case R.id.page2:
                        selectedFragment = fragmentLoadTransaction(new PropieatarioFragment());
                        break;
                    case R.id.page3:
                        selectedFragment = fragmentLoadTransaction(new ObraFragment());
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder,selectedFragment).commit();
                return true;
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.buscar:
                        Toast.makeText(Licencia.this,"buscar",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Intent intentLogout  = new Intent(Licencia.this,MainActivity.class);
                        intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentLogout);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }

                Log.v(TAG,"entrando al evento");
                return true;
            }
        });
    }

    public Fragment fragmentLoadTransaction(Fragment fragment){
        Bundle inicialBundle = new Bundle();
        inicialBundle.putParcelable("datos",li);
        fragment.setArguments(inicialBundle);
        return fragment;
    }
}
