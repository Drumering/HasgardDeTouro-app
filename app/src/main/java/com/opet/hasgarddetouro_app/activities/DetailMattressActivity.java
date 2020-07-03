package com.opet.hasgarddetouro_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.opet.hasgarddetouro_app.R;
import com.opet.hasgarddetouro_app.objects.Matress;
import com.squareup.picasso.Picasso;

public class DetailMattressActivity extends AppCompatActivity {

    private Matress matress;
    private ImageView imageMatress;
    private TextView conditions, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mattress);

        matress = (Matress) getIntent().getSerializableExtra("obj");

        imageMatress = findViewById(R.id.mattressImage);
        conditions = findViewById(R.id.textConditions);
        description = findViewById(R.id.textDescription);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Picasso.get().load(matress.getImages().get(0)).into(imageMatress);
        conditions.setText("R$ " + matress.getCashPrice().toString() + " à vista");
        description.setText(matress.getDescription());
    }
}