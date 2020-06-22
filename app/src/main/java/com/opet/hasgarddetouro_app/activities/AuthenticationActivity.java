package com.opet.hasgarddetouro_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.opet.hasgarddetouro_app.HomeActivity;
import com.opet.hasgarddetouro_app.R;

public class AuthenticationActivity extends AppCompatActivity {

    private static final String TAG = AuthenticationActivity.class.getSimpleName();
    private EditText editLogin, editPass;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        editLogin = findViewById(R.id.editLogin);
        editPass = findViewById(R.id.editPass);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    public void login(View view) {
        String login = editLogin.getText().toString();
        String pass = editPass.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(login, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: Success");
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            updateUI(firebaseUser);
                        } else {
                            Log.wtf(TAG, "Falha ao autenticar usuário");
                            Toast.makeText(AuthenticationActivity.this, "Falha ao autenticar", Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser firebaseUser) {
        if (firebaseUser != null){
            Toast.makeText(AuthenticationActivity.this, "Sucesso ao autenticar", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AuthenticationActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void loginGoogle(View view) {
    }

    public void criarUsuario(View view) {
        Intent paginaCriar = new Intent(AuthenticationActivity.this, CreateUserActivity.class);
        startActivity(paginaCriar);
        finish();
    }
}
