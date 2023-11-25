package com.example.xyzempresa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void registro(View view) {
        EditText emailoruser = findViewById(R.id.etuserreg);
        EditText pass = findViewById(R.id.etpassreg);
        EditText vpass = findViewById(R.id.etpassv);

        String email = emailoruser.getText().toString();
        String password = pass.getText().toString();
        String vpassword = vpass.getText().toString();

        if (!password.equals(vpassword)) {
            Toast.makeText(Register.this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registrar usuario
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registro exitoso
                        Log.d("Registro", "createUserWithEmail:success");
                        Toast.makeText(Register.this, "Usuario creado con éxito!", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        // Redirigir al usuario a la actividad principal (página de inicio de sesión)
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Manejo de errores específicos
                        if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                            Toast.makeText(Register.this, "La contraseña es demasiado débil. Por favor, elige una más fuerte.", Toast.LENGTH_SHORT).show();
                        } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(Register.this, "El formato del correo electrónico es inválido.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Otros errores de autenticación
                            Log.w("Registro", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Autenticación fallida.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}