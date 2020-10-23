package com.example.qrurbanomaterial;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class SolicitanteFragment extends Fragment {
    private static final String TAG = SolicitanteFragment.class.getSimpleName();
    private LicenciaModel li = null;
    private  TextInputEditText textVig1,textVig2,textNombreSolicitante,textDomicilioSolicitante,textTel,textPredial;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        if (getArguments() != null){
            li = bundle.getParcelable("datos");
        }else {
            Log.d(TAG, "no hay Datos");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View solicitanteView = inflater.inflate(R.layout.solicitante_fragment, container, false);
        textVig1 = (TextInputEditText) solicitanteView.findViewById(R.id.textVig1);
        textVig2 = (TextInputEditText) solicitanteView.findViewById(R.id.textVig2);
        textNombreSolicitante = (TextInputEditText) solicitanteView.findViewById(R.id.textNameSolicitante);
        textDomicilioSolicitante = (TextInputEditText) solicitanteView.findViewById(R.id.textDomicilioSolicitante);
        textTel = (TextInputEditText) solicitanteView.findViewById(R.id.textTel);
        textPredial = (TextInputEditText) solicitanteView.findViewById(R.id.textPredial);
        return solicitanteView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textVig1.setText(li.getVegencia1());
        textVig2.setText(li.getVigencia2());
        textNombreSolicitante.setText(li.getNombre_solicitante());
        textDomicilioSolicitante.setText(li.getDomicilio_solicitante());
        textTel.setText(li.getTelefono_solicitante());
        textPredial.setText(String.valueOf(li.getPredial_obra()));
    }
}
