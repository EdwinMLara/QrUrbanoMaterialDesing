package com.example.qrurbanomaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Scan extends AppCompatActivity {

    private Button scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scan = (Button)findViewById(R.id.buttonScan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenQrScanActivity = new Intent(Scan.this,Licencia.class);
                startActivity(intenQrScanActivity);
            }
        });
    }
}
