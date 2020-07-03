package com.opet.hasgarddetouro_app.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.opet.hasgarddetouro_app.R;

public class AuthenticationActivity extends AppCompatActivity {

    private static final String TAG = AuthenticationActivity.class.getSimpleName();
    private EditText editLogin, editPass;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private GoogleSignInClient googleSignInClient;

    private static final int RC_SIGN_IN = 9001;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        editLogin = findViewById(R.id.editLogin);
        editPass = findViewById(R.id.editPass);

        firebaseAuth = FirebaseAuth.getInstance();

        // [START]=================== Google Auth ===================
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END]=================== Google Auth ===================
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = firebaseAuth.getCurrentUser();
    }

    public void login(View view) {
        String login = editLogin.getText().toString();
        String passwd = editPass.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(login, passwd)
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
            /**
             * Organizar logica para logar e retornar para página que chamou o login
             */
            Toast.makeText(AuthenticationActivity.this, "Sucesso ao autenticar", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void loginGoogle(View view) {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> googleSignInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(googleSignInAccountTask);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> googleSignInAccountTask) {
        try {
            GoogleSignInAccount account = googleSignInAccountTask.getResult(ApiException.class);
            assert account != null;
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            Log.wtf(TAG, "SignIn result => handleSignInResult: " + e.getStatusCode());
            updateUI(null);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle: " + account.getId());

        showProgressDialog();

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "signInWithCredential: Success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w(TAG, "signInWithCredential: Failure", task.getException());
                    updateUI(null);
                }
            }
        });
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);

            // Disable the back button
            DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            };
            progressDialog.setOnKeyListener(keyListener);
        }
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();
    }

    public void criarUsuario(View view) {
        Intent paginaCriar = new Intent(AuthenticationActivity.this, CreateUserActivity.class);
        startActivity(paginaCriar);
        finish();
    }
}
