package com.example.qrurbanomaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Licencia extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licencia);

        bottomNavigationView = findViewById(R.id.button_navigation);

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
    }
}
