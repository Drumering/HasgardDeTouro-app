package com.opet.hasgarddetouro_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.opet.hasgarddetouro_app.activities.AuthenticationActivity;
import com.opet.hasgarddetouro_app.adapters.ListHomeAdapter;
import com.opet.hasgarddetouro_app.objects.Mattress;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ArrayList<Mattress> mattresses = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.list_home);

        mattresses.add(new Mattress("Name 01", "Overview 1", (float) 1200.00, "12x R$ 100,00"));
        mattresses.add(new Mattress("Name 02", "Overview 2", (float) 2400.00, "12x R$ 200,00"));
        mattresses.add(new Mattress("Name 03", "Overview 3", (float) 3600.00, "12x R$ 300,00"));
        mattresses.add(new Mattress("Name 04", "Overview 4", (float) 1600.00, "12x R$ 133,34"));
        mattresses.add(new Mattress("Name 05", "Overview 5", (float) 1520.00, "12x R$ 126,67"));

        ListHomeAdapter listHomeAdapter = new ListHomeAdapter(HomeActivity.this, mattresses);
        listView.setAdapter(listHomeAdapter);
    }

    public void goToAuth(View view) {
        Intent intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }

    public void addToChart(View view) {

    }
}
