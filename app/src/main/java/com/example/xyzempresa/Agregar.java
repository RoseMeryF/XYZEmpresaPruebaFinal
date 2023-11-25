package com.example.xyzempresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Agregar extends AppCompatActivity {

    EditText nombre, detalle, precio, imgUrl;
    Button btnAdd, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        nombre = findViewById(R.id.etnombreprod);
        detalle = findViewById(R.id.etdetalleprod);
        precio = findViewById(R.id.etpproduct);
        imgUrl = findViewById(R.id.etimgprod);

        btnBack = findViewById(R.id.btnvolver);
        btnAdd = findViewById(R.id.btnagregar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertarDatos() {
        Map<String, Object> map = new HashMap();
        map.put("Nombre: ",nombre.getText().toString());
        map.put("Detalles: ",detalle.getText().toString());
        map.put("Precio: ",precio.getText().toString());
        map.put("imgUrl: ",imgUrl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Nulo").push()
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Agregar.this, "Ingresado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e){
                        Toast.makeText(Agregar.this, "Error al ingresar datos", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}