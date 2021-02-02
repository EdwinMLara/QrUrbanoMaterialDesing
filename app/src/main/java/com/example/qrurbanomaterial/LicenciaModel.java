package com.example.qrurbanomaterial;

import android.os.Parcel;
import android.os.Parcelable;

public class LicenciaModel implements Parcelable {
    private String vigencia1 = "";
    private String vigencia2 = "";
    private String nombre_solicitante = "";
    private String domicilio_solicitante = "";
    private String ciudad_solicitante = "";
    private String telefono_solicitante = "";
    private String predial_obra = "";
    private String ubicacion_obra = "";
    private String destino_obra = "";
    private String superficie_obra ="";
    private String documentos_obra = "";
    private String fecha= "";
    private String status= "";
    private String nombre_suscriptor = "";
    private String numero_perito;
    private String domicilio_suscriptor = "";
    private String observaciones = "";
    private String numero_licencia = "";
    private String image = "";
    private String pdf = "";
    private String numero_recibo = "";

    public LicenciaModel(){}

    protected LicenciaModel(Parcel in){
        vigencia1 = in.readString();
        vigencia2 = in.readString();
        nombre_solicitante = in.readString();
        domicilio_solicitante = in.readString();
        ciudad_solicitante = in.readString();
        telefono_solicitante = in.readString();
        predial_obra = in.readString();
        ubicacion_obra = in.readString();
        destino_obra = in.readString();
        superficie_obra = in.readString();
        documentos_obra = in.readString();
        fecha = in.readString();
        status = in.readString();
        nombre_suscriptor = in.readString();
        numero_perito = in.readString();
        domicilio_suscriptor = in.readString();
        observaciones = in.readString();
        numero_licencia = in.readString();
        image = in.readString();
        pdf = in.readString();
        numero_recibo = in.readString();

    }
    public static final Parcelable.Creator<LicenciaModel > CREATOR = new Parcelable.Creator<LicenciaModel >() {
        @Override
        public LicenciaModel  createFromParcel(Parcel in) {
            return new LicenciaModel(in);
        }

        @Override
        public LicenciaModel [] newArray(int size) {
            return new LicenciaModel [size];
        }
    };

    public void setNumero_recibo(String numero_recibo) {
        this.numero_recibo = numero_recibo;
    }

    public String getNumero_recibo() {
        return numero_recibo;
    }

    public String getVegencia1() {
        return vigencia1;
    }

    public void setVegencia1(String vegencia1) {
        this.vigencia1 = vegencia1;
    }

    public String getVigencia2() {
        return vigencia2;
    }

    public void setVigencia2(String vigencia2) {
        this.vigencia2 = vigencia2;
    }

    public void setNombre_solicitante(String nombre_solicitante) {
        this.nombre_solicitante = nombre_solicitante;
    }

    public String getNombre_solicitante() {
        return this.nombre_solicitante;
    }

    public String getDomicilio_solicitante() {
        return this.domicilio_solicitante;
    }

    public void setDomicilio_solicitante(String domicilio_solicitante) {
        this.domicilio_solicitante = domicilio_solicitante;
    }

    public String getCiudad_solicitante() {
        return this.ciudad_solicitante;
    }

    public void setCiudad_solicitante(String ciudad_solicitante) {
        this.ciudad_solicitante = ciudad_solicitante;
    }

    public String getTelefono_solicitante() {
        return this.telefono_solicitante;
    }

    public void setTelefono_solicitante(String telefono_solicitante) {
        this.telefono_solicitante = telefono_solicitante;
    }

    public String getPredial_obra() {
        return this.predial_obra;
    }

    public void setPredial_obra(String predial_obra) {
        this.predial_obra = predial_obra;
    }

    public String getUbicaion_obra() {
        return this.ubicacion_obra;
    }

    public void setUbicaion_obra(String ubicaion_obra) {
        this.ubicacion_obra = ubicaion_obra;
    }

    public void setDestino_obra(String destino_obra){this.destino_obra = destino_obra;}

    public String getDestino_obra(){return this.destino_obra;}

    public String getSuperficie_obra() {
        return this.superficie_obra;
    }

    public void setSuperficie_obra(String superficie_obra) {
        this.superficie_obra = superficie_obra;
    }

    public String getDocumentos_obra() {
        return this.documentos_obra;
    }

    public void setDocumentos_obra(String documentos_obra) {
        this.documentos_obra = documentos_obra;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNombre_suscriptor() {
        return this.nombre_suscriptor;
    }

    public void setNombre_suscriptor(String nombre_suscriptor) {
        this.nombre_suscriptor = nombre_suscriptor;
    }

    public String getNumero_perio() {
        return this.numero_perito;
    }

    public void setNumero_perio(String numero_perio) {
        this.numero_perito = numero_perio;
    }

    public String getDomicilio_suscritor() {
        return this.domicilio_suscriptor;
    }

    public void setDomicilio_suscritor(String domicilio_suscritor) {
        this.domicilio_suscriptor = domicilio_suscritor;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNumero_licencia() {
        return this.numero_licencia;
    }

    public void setNumero_licencia(String numero_licencia) {
        this.numero_licencia = numero_licencia;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdf() {
        return this.pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vigencia1);
        dest.writeString(vigencia2);
        dest.writeString(nombre_solicitante);
        dest.writeString(domicilio_solicitante);
        dest.writeString(ciudad_solicitante);
        dest.writeString(telefono_solicitante);
        dest.writeString(predial_obra);
        dest.writeString(ubicacion_obra);
        dest.writeString(destino_obra);
        dest.writeString(superficie_obra);
        dest.writeString(documentos_obra);
        dest.writeString(fecha);
        dest.writeString(status);
        dest.writeString(nombre_suscriptor);
        dest.writeString(numero_perito);
        dest.writeString(domicilio_suscriptor);
        dest.writeString(observaciones);
        dest.writeString(numero_licencia);
        dest.writeString(image);
        dest.writeString(pdf);
        dest.writeString(numero_recibo);
    }
}
