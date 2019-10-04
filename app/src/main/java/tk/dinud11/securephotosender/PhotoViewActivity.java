package tk.dinud11.securephotosender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import model.Photo;

public class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_photo_view);

        ImageView content = findViewById(R.id.imageview);

        Intent intent = getIntent();
        String link = intent.getStringExtra("photo_link");
        Picasso.get().load(link).into(content);
    }
}
