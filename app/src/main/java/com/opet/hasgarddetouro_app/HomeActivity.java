package com.opet.hasgarddetouro_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.opet.hasgarddetouro_app.adapters.ListHomeAdapter;
import com.opet.hasgarddetouro_app.objects.Matress;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NavigableMap;

public class HomeActivity extends AppCompatActivity {

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

    private void initAdapter() {
        ListHomeAdapter listHomeAdapter = new ListHomeAdapter(HomeActivity.this, matresses);
        listView.setAdapter(listHomeAdapter);
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
                            initAdapter();
                        }
                    }
                });
    }

    public void goToAuth(View view) {
        Intent intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
        startActivity(intent);
    }
}
