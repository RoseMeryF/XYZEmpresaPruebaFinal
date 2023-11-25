package com.example.xyzempresa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    private EditText edmail, edpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.btnlogin);
        edmail = findViewById(R.id.etuserreg);
        edpass = findViewById(R.id.etpassreg);

    }
    public void EntrarLogin(View view) {
        String email = edmail.getText().toString();
        String password = edpass.getText().toString();

        // Verifica que los campos no estén vacíos
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Los campos de correo y contraseña no" +
                    " pueden estar vacíos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar que el formato del correo electrónico sea válido
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, introduce un correo" +
                    " electrónico válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Autenticación con Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()) {
                        // Ingreso exitoso
                        Toast.makeText(MainActivity.this, "Autenticación exitosa.", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(MainActivity.this, Producto.class);
                        startActivity(intent);
                    } else {
                        // Error de autenticación
                        Toast.makeText(MainActivity.this, "Falló la autenticación. Verifica tus credenciales.", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void registrarse(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}