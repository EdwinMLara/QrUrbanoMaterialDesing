package com.example.qrurbanomaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_CODE_INTERNET_PERMISSION = 20;

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText editTextUsername;
    private TextInputEditText editTextPassword;
    private Button buttonLogin;
    private String username,password,token;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = (Button)findViewById(R.id.buttonLogin);

        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        editTextUsername = findViewById(R.id.username);


        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        editTextPassword = findViewById(R.id.password);

        progressBar = findViewById(R.id.progressBar);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                attempLogin();
            }
        });
    }

    private void attempLogin(){
        editTextUsername.setError(null);
        editTextPassword.setError(null);

        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        Log.v(TAG,"Datos Login" + username + password);

        boolean cancel = false;

        if(TextUtils.isEmpty(username)){
            textInputLayoutUsername.setError(getString(R.string.error_empty_username));
            cancel = true;
        }

        if(TextUtils.isEmpty(password)){
            textInputLayoutPassword.setError(getString(R.string.error_empty_password));
            cancel = true;
        }

        if(cancel){
            textInputLayoutUsername.setErrorEnabled(true);
            textInputLayoutPassword.setErrorEnabled(true);
            progressBar.setVisibility(View.GONE);
        }else{
            if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.INTERNET},REQUEST_CODE_INTERNET_PERMISSION);
            }else{
                makeRequest();
            }
        }
    }

    public void makeRequest(){
        Usuario usuario = new Usuario(username,password);
        ApiUsuario apiUsuario = new ApiUsuario("generateToken",usuario);
        Uriangatoservice service = UriangatoClassService.postCreateUserService();

        retrofit2.Call<JsonObject> loginCall = service.getLogin(apiUsuario);

        loginCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject respondentsJson = response.body();
                if(respondentsJson.has("response")) {
                    JsonObject responseServer = respondentsJson.getAsJsonObject("response");
                    Log.v(TAG, responseServer.toString());
                    int status = responseServer.get("status").getAsInt();
                    if (status == 200) {
                        JsonObject result = responseServer.getAsJsonObject("result");
                        token = result.get("token").getAsString();

                        Intent tokenLoginIntent = new Intent(MainActivity.this, Scan.class);
                        tokenLoginIntent.putExtra("token", token);
                        tokenLoginIntent.putExtra("isLogged", true);
                        progressBar.setVisibility(View.GONE);
                        startActivity(tokenLoginIntent);
                    }else{
                        progressBar.setVisibility(View.GONE);
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("ServerError " + responseServer.get("status").getAsString() );
                        alertDialog.setMessage(responseServer.get("result").getAsString());
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_INTERNET_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makeRequest();
            }else{
                Toast.makeText(this,"Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
