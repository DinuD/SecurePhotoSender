package model;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tk.dinud11.securephotosender.MainActivity;
import tk.dinud11.securephotosender.R;
import tk.dinud11.securephotosender.SendFragment;

public class PhotoAdapter extends ArrayAdapter<Photo> {

    private static class ViewHolder {
        TextView code;
        ImageView preview;
        MaterialButton delete;
    }

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        super(context, 0, photos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Photo photo = getItem(position);
        final ViewHolder viewHolder;
        if(convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_photo, parent, false);
            viewHolder.code = convertView.findViewById(R.id.code);
            viewHolder.preview = convertView.findViewById(R.id.photo_preview);
            viewHolder.delete = convertView.findViewById(R.id.delete_btn);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            viewHolder.code.setText(photo.getCode());
            Picasso.get().load(photo.getLink()).resize(0, 350).onlyScaleDown().into(viewHolder.preview);
            if(!photo.isSent())
                viewHolder.delete.setVisibility(View.GONE);


            viewHolder.code.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipData clip = ClipData.newPlainText("photo code", viewHolder.code.getText());
                    MainActivity.clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NullPointerException e) {
            Log.d("null", "help");
        }
        return convertView;
    }
}
