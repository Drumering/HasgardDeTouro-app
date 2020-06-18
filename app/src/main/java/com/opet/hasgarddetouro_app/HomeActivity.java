package com.opet.hasgarddetouro_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.opet.hasgarddetouro_app.activities.AuthenticationActivity;
import com.opet.hasgarddetouro_app.adapters.ListHomeAdapter;
import com.opet.hasgarddetouro_app.objects.Matress;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ArrayList<Matress> matresses = new ArrayList<Matress>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.list_home);

        matresses.add(new Matress("name", "brief overview", (float) 1200.00, "12x R$ 100"));
        matresses.add(new Matress("name", "brief overview", (float) 2400.00, "12x R$ 200"));
        matresses.add(new Matress("name", "brief overview", (float) 3600.00, "12x R$ 300"));

        ListHomeAdapter listHomeAdapter = new ListHomeAdapter(HomeActivity.this, matresses);
        listView.setAdapter(listHomeAdapter);
    }

    public void goToAuth(View view) {
        Intent intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
        startActivity(intent);
    }
}
