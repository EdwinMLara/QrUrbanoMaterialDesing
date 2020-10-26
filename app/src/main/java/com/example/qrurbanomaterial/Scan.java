package com.example.qrurbanomaterial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.android.material.appbar.MaterialToolbar;

public class Scan extends AppCompatActivity {
    private static final String TAG = Scan.class.getSimpleName();
    private Button scan;
    private MaterialToolbar toolbar;

    private static final int REQUEST_CODE_QR_SCAN = 101;
    public static final int REQUEST_CODE_CAMERA_PERMISSION = 10;

    private String token = null;
    private Boolean isLogged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Intent intentGetToken = getIntent();
        if(intentGetToken.hasExtra("token")) {

            isLogged = intentGetToken.getBooleanExtra("isLogged",false);
            token = intentGetToken.getStringExtra("token");

            scan = (Button) findViewById(R.id.buttonScan);
            toolbar = findViewById(R.id.main_toolbar_scan);

            scan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Scan.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA_PERMISSION);
                    } else {
                        Intent intent = new Intent(Scan.this, QrCodeActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_QR_SCAN);
                    }
                }
            });
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.buscar:
                        Toast.makeText(Scan.this,"buscar",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Intent intentLogout  = new Intent(Scan.this,MainActivity.class);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_CAMERA_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Scan.this, QrCodeActivity.class);
                startActivityForResult(intent, REQUEST_CODE_QR_SCAN);
            }else{
                Toast.makeText(this,"Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            if (data == null)
                return;

            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(Scan.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;
        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data == null)
                return;
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.v(TAG, result);
            getDataLicenced(result);
        }
    }

    private void getDataLicenced(String id_hash){

        RequestLicencia requestLicencia = new RequestLicencia(id_hash);
        ApiUsuario apiUsuario = new ApiUsuario("contructionData",requestLicencia);
        Uriangatoservice service = (Uriangatoservice) UriangatoClassService.getLicenciaByToken(this.token);

        retrofit2.Call<JsonObject> LicenciaCall = service.getLicenciaByToken(apiUsuario);

        LicenciaCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject responseJson = response.body();

                if(responseJson.has("error")){
                    JsonObject errorJson = responseJson.getAsJsonObject("error");
                    Log.v(TAG,errorJson.toString());
                    AlertDialog alertDialog = new AlertDialog.Builder(Scan.this).create();
                    alertDialog.setTitle("status");
                    alertDialog.setMessage(errorJson.get("message").getAsString());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                if(responseJson.has("response")){
                    JsonObject responseServer = responseJson.getAsJsonObject("response");
                    Log.v(TAG,responseServer.toString());
                    int status = responseServer.get("status").getAsInt();
                    if (status == 200) {

                        JsonObject jsonResult = responseServer.getAsJsonObject("result");
                        if(jsonResult != null){
                            JsonObject jsonLicencia = jsonResult.getAsJsonObject("licencia");
                            Intent intentDatosLicencia = new Intent(Scan.this,Licencia.class);
                            intentDatosLicencia.putExtra("jsonStringLicencia",jsonLicencia.toString());
                            startActivity(intentDatosLicencia);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(Scan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
