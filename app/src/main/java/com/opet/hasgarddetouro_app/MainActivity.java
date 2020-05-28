package com.opet.hasgarddetouro_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editLogin, editPass;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        String email = editLogin.getText().toString();
        String passwd = editPass.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: Success");
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            updateUI(firebaseUser);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser firebaseUser) {
        if (firebaseUser != null){
            // To do
        }
    }

    public void loginGoogle(View view) {
    }

    public void criarUsuario(View view) {
        Intent paginaCriar = new Intent(MainActivity.this, CreateUserActivity.class);
        startActivity(paginaCriar);
        finish();
    }
}
