package com.example.qrurbanomaterial;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class ObraFragment extends Fragment {
    private static final String TAG = ObraFragment.class.getSimpleName();
    private LicenciaModel li = null;
    private TextInputEditText textCuentaPredialObra,textUbicaionObra,textDestinoObra,textSuperficieObra;

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
        // Inflate the layout for this fragment
        View obraView = inflater.inflate(R.layout.obra_fragment, container, false);
        textCuentaPredialObra = (TextInputEditText)  obraView.findViewById(R.id.textCuentaPredialObra);
        textUbicaionObra = (TextInputEditText) obraView.findViewById(R.id.textUbicacionObra);
        textDestinoObra = (TextInputEditText) obraView.findViewById(R.id.textDestinoObra);
        textSuperficieObra = (TextInputEditText) obraView.findViewById(R.id.textSuperficieObra);

        return  obraView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textCuentaPredialObra.setText(String.valueOf(li.getPredial_obra()));
        textUbicaionObra.setText(li.getUbicaion_obra());
        textDestinoObra.setText(li.getDestino_obra());
        textSuperficieObra.setText(String.valueOf(li.getSuperficie_obra()) + "M");
    }
}
