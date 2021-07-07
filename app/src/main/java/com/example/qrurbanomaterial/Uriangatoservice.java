package com.example.qrurbanomaterial;

import com.google.gson.JsonObject;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Uriangatoservice {
    public String BASE_URL= "http://intranet.uriangato.gob.mx/urbano2/api/";
    public String HASH_KEY = "encrypted";

    @GET("construcion-api.php")
    Call<Licencia> getLicencia();

    @Headers("Content-Type: application/json")
    @POST("logApiJwt.php")
    Call<JsonObject> getLogin(@Body ApiUsuario apiusuario);

    @Headers("Content-Type: application/json")
    @POST("logApiJwt.php")
    Call<JsonObject> getLicenciaByToken(@Body ApiUsuario apiusuario);
}
