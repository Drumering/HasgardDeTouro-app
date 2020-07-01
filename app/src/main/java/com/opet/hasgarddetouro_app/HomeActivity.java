package com.opet.hasgarddetouro_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.opet.hasgarddetouro_app.activities.AuthenticationActivity;
import com.opet.hasgarddetouro_app.activities.DetailMattressActivity;
import com.opet.hasgarddetouro_app.adapters.ListHomeAdapter;
import com.opet.hasgarddetouro_app.objects.Matress;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NavigableMap;

public class HomeActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private ArrayList<Matress> matresses = new ArrayList<Matress>();
    private ListView listView;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.list_home);

//        matresses.add(new Matress("name", "brief overview", (float) 1200.00, "12x R$ 100"));
//        matresses.add(new Matress("name", "brief overview", (float) 2400.00, "12x R$ 200"));
//        matresses.add(new Matress("name", "brief overview", (float) 3600.00, "12x R$ 300"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
        loadMatresses();
    }

    private void initAdapter(ArrayList<Matress> matresses) {
        ListHomeAdapter listHomeAdapter = new ListHomeAdapter(HomeActivity.this, matresses);
//        ArrayAdapter<Matress> test = new ArrayAdapter<>(getApplicationContext(), listHomeAdapter, matresses);
        listView.setAdapter( listHomeAdapter);
        listView.setOnItemClickListener(this);
    }

    private void loadMatresses() {
        CollectionReference ref = db.collection("matress");
        ref.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                matresses.add(documentSnapshot.toObject(Matress.class));
                            }
                            for (Matress m : matresses){
                                Log.d(TAG, m.getName() + " " + m.getImages());
                            }
                            initAdapter(matresses);
                        }
                    }
                });
        matresses.clear();
    }

    public void goToAuth(View view) {
        Intent intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Matress matress = (Matress) parent.getItemAtPosition(position);
        Intent intent = new Intent(HomeActivity.this, DetailMattressActivity.class);

        intent.putExtra("ID", matress.getName());
        startActivity(intent);
    }
}
