package com.opet.hasgarddetouro_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.opet.hasgarddetouro_app.R;
import com.opet.hasgarddetouro_app.objects.Matress;

import java.util.ArrayList;

public class ListHomeAdapter extends ArrayAdapter<Matress> {

    private ArrayList<Matress> matresses;
    private Context mContext;

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

        ImageView imageView = convertView.findViewById(R.id.imageMatress);
        TextView descMatress = convertView.findViewById(R.id.descMatress);
        TextView cond_matress = convertView.findViewById(R.id.cond_matress);

        imageView.setImageResource(R.mipmap.ic_bed_splash_foreground);
        descMatress.setText(matresses.get(position).getDescription());
        cond_matress.setText(matresses.get(position).getConditions());

        return convertView;
    }
}
