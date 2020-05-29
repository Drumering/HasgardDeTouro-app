package com.opet.hasgarddetouro_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.opet.hasgarddetouro_app.HomeActivity;
import com.opet.hasgarddetouro_app.R;

public class CreateUserActivity extends AppCompatActivity {

    private EditText editLogin, editPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        editLogin = findViewById(R.id.newLogin);
        editPass = findViewById(R.id.newPass);

        mAuth = FirebaseAuth.getInstance();
    }

    public void criarUsuario(View view) {
        String login = editLogin.getText().toString();
        String pass = editPass.getText().toString();

        if (!emailValido(login)) {
            Toast.makeText(CreateUserActivity.this, "Email Invalido",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(login, pass).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                atualizarUI(user, view);
            } else {
                Toast.makeText(CreateUserActivity.this, "Falha em Criar Novo Usuario",
                        Toast.LENGTH_SHORT).show();
                atualizarUI(null, view);
            }
        });
    }

    boolean emailValido(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void atualizarUI(FirebaseUser user, View view) {
        if (user != null) {
            voltar(view);
        }
    }

    public void voltar(View view) {
        Intent home = new Intent(CreateUserActivity.this, HomeActivity.class);
        startActivity(home);
        finish();
    }
}
