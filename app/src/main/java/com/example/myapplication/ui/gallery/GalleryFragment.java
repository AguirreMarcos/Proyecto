package com.example.myapplication.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.controller.MuseoController;
import com.example.myapplication.model.Museo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class GalleryFragment extends Fragment {

    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;

    private GalleryViewModel galleryViewModel;
    MuseoController museoController = new MuseoController();
    List<Museo> listaMuseos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        TextView textoMuseo = root.findViewById(R.id.textView2);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        museoController.listar();

        listaMuseos = museoController.getListObjects();

        for(Museo museo: listaMuseos){
            textoMuseo.setText(museo.getDescripcion());
        }

        return root;
    }
    public void inicializarFirebase() {

        FirebaseApp.initializeApp(GalleryFragment.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}