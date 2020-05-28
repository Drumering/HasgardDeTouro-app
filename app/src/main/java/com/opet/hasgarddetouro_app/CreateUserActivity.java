package com.opet.hasgarddetouro_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateUserActivity extends AppCompatActivity {

    private EditText editLogin, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        editLogin = findViewById(R.id.newLogin);
        editPass = findViewById(R.id.newPass);
    }

    public void criarUsuario(View view) {
    }

    public void voltar(View view) {
        Intent home = new Intent(CreateUserActivity.this, MainActivity.class);
        startActivity(home);
        finish();
    }
}
