package com.opet.hasgarddetouro_app.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.opet.hasgarddetouro_app.R;
import com.opet.hasgarddetouro_app.objects.Matress;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListHomeAdapter extends ArrayAdapter<Matress> {

    private static final String TAG = ListHomeAdapter.class.getSimpleName();
    private ArrayList<Matress> matresses;
    private Context mContext;
    ImageView imageView;
    TextView descMatress;
    TextView cond_matress;

    public ListHomeAdapter(@NonNull Context context, ArrayList<Matress> matresses) {
        super(context, R.layout.list_home);
        this.mContext = context;
        this.matresses = matresses;
    }

    @Override
    public int getCount() {
        return matresses.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_home, parent, false);

        imageView = convertView.findViewById(R.id.imageMatress);
        descMatress = convertView.findViewById(R.id.descMatress);
        cond_matress = convertView.findViewById(R.id.cond_matress);

        getImageStorage(position);
        descMatress.setText(matresses.get(position).getDescription());
        cond_matress.setText(matresses.get(position).getCashPrice().toString());

        return convertView;
    }

    private void getImageStorage(int position) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReferenceFromUrl(matresses.get(position).getImages().get(0));
        storageReference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imageView);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Falha ao realizar download da imagem");
                        imageView.setImageResource(R.mipmap.ic_bed_splash_foreground);
                    }
                });
    }
}
