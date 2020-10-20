package com.example.qrurbanomaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Licencia extends AppCompatActivity {

    private static final String TAG = Licencia.class.getSimpleName();

    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licencia);

        bottomNavigationView = findViewById(R.id.button_navigation);
        toolbar = findViewById(R.id.main_toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.page1:
                        selectedFragment = new SolicitanteFragment();
                        break;
                    case R.id.page2:
                        selectedFragment = new PropieatarioFragment();
                        break;
                    case R.id.page3:
                        selectedFragment = new ObraFragment();
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
                        Toast.makeText(Licencia.this,"Logout",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }

                Log.v(TAG,"entrando al evento");
                return true;
            }
        });

    }
}
