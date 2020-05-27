package com.opet.hasgarddetouro_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editLogin, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = findViewById(R.id.editLogin);
        editPass = findViewById(R.id.editPass);
    }

    public void login(View view) {
    }

    public void loginGoogle(View view) {
    }

    public void criarUsuario(View view) {
        Intent paginaCriar = new Intent(MainActivity.this, CreateUserActivity.class);
        startActivity(paginaCriar);
        finish();
    }
}
