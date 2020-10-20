package com.example.qrurbanomaterial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class Scan extends AppCompatActivity {
    private static final String TAG = Scan.class.getSimpleName();
    private Button scan;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scan = (Button)findViewById(R.id.buttonScan);
        toolbar = findViewById(R.id.main_toolbar_scan);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenQrScanActivity = new Intent(Scan.this,Licencia.class);
                startActivity(intenQrScanActivity);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.buscar:
                        Toast.makeText(Scan.this,"buscar",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toast.makeText(Scan.this,"Logout",Toast.LENGTH_SHORT).show();
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
