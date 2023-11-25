package com.example.xyzempresa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Producto extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    FloatingActionButton floatingActionButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("productos");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options = new FirebaseRecyclerOptions.Builder<MainModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Titulo"),MainModel.class)
                .build();

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(), Agregar.class));
            }
        });


        //Configuración boton de añadir producto
        Button btnAdd = findViewById(R.id.btnañadir);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProducto();
            }
        });
    }

    private void addProducto() {
        EditText etNombre = findViewById(R.id.etnameprod);
        EditText etPrecio = findViewById(R.id.etpproducto);

        String nombre = etNombre.getText().toString();
        String precioTexto = etPrecio.getText().toString();
        double precio = 0;

        // Validación de los datos
        if (nombre.isEmpty() || precioTexto.isEmpty()) {
            Toast.makeText(Producto.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            Toast.makeText(Producto.this, "El precio ingresado no es válido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (precio <= 0) {
            Toast.makeText(Producto.this, "El precio ingresado no es válido", Toast.LENGTH_SHORT).show();
            return;
        }
        MainModel nuevoProducto = new MainModel(nombre, null, null, precio);

        myRef.push().setValue(nuevoProducto).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Operación exitosa
                Toast.makeText(Producto.this, "Producto añadido con éxito", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Error al añadir producto
                Toast.makeText(Producto.this, "Error al añadir producto: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void verProducto(View view) {
        Intent intent = new Intent(this, Agregar.class);
        startActivity(intent);
    }

}