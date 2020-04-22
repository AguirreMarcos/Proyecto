package com.example.myapplication.controller;

import androidx.annotation.NonNull;

import com.example.myapplication.MainActivity;
import com.example.myapplication.model.Museo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MuseoController implements IAccesoDatos {


    List<Museo> listObjects = new ArrayList<>();

    public List<Museo> getListObjects() {
        return listObjects;
    }

    public void setListObjects(List<Museo> listObjects) {
        this.listObjects = listObjects;
    }

    @Override
    public void listar() {
        MainActivity.databaseReference.child("Museo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listObjects.clear();
                for(DataSnapshot objSnapShot: dataSnapshot.getChildren()){
                    Museo museo = objSnapShot.getValue(Museo.class);
                    listObjects.add(museo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
