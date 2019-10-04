package tk.dinud11.securephotosender;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import model.Photo;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveFragment extends Fragment {

    private TextInputEditText editText;
    private MaterialButton submit;


    public ReceiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receive, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editText = view.findViewById(R.id.code_input);
        submit = view.findViewById(R.id.code_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(editText.getText().toString().length() == 8) {
                        final String code = editText.getText().toString();
                        StorageReference ref = FirebaseStorage.getInstance().getReference().child(code);
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Photo photo = new Photo(code, uri.toString(), false);
                                if(MainActivity.addPhoto(photo) == null)
                                    Toast.makeText(getContext(), "Photo has been already added to history",
                                            Toast.LENGTH_SHORT).show();
                                MainActivity.adapter.notifyDataSetChanged();
                                Intent intent = new Intent(getContext(), PhotoViewActivity.class);
                                intent.putExtra("photo_link", photo.getLink());
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Could not find code", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (NullPointerException e) {
                    Toast.makeText(getContext(), "Input cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
