package com.opet.hasgarddetouro_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.opet.hasgarddetouro_app.R;
import com.opet.hasgarddetouro_app.objects.Matress;

public class DetailMattressActivity extends AppCompatActivity {

    private Matress matress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mattress);

        matress = (Matress) getIntent().getSerializableExtra("obj");
        Log.d("OBJETO", matress.getName());
    }
}