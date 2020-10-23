package com.example.qrurbanomaterial;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class PropieatarioFragment extends Fragment {
    private static final String TAG = PropieatarioFragment.class.getSimpleName();
    private LicenciaModel li = null;
    private TextInputEditText textPropietarioName,textPropietarioDomicilio,textPropietarioNumeroRegistro;

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
        View propietarioView = inflater.inflate(R.layout.propietario_fragment, container, false);
        textPropietarioName = (TextInputEditText) propietarioView.findViewById(R.id.textPropietarioName);
        textPropietarioDomicilio = (TextInputEditText) propietarioView.findViewById(R.id.textPropietarioDomicilio);
        textPropietarioNumeroRegistro = (TextInputEditText) propietarioView.findViewById(R.id.textPropietarioNumeroRegistro);
        return propietarioView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textPropietarioName.setText(li.getNombre_suscriptor());
        textPropietarioDomicilio.setText(li.getDomicilio_suscritor());
        textPropietarioNumeroRegistro.setText(String.valueOf(li.getNumero_recibo()));
    }
}
