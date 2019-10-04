package tk.dinud11.securephotosender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import model.Photo;
import model.PhotoAdapter;
import model.PhotoDatabase;

public class MainActivity extends AppCompatActivity {

    public static PhotoDatabase db;
    public static ClipboardManager clipboard;
    public static PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);
        ViewPager pager = findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(pager);

        db = PhotoDatabase.getPhotoDatabase(this);
        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    public static Photo addPhoto(Photo photo) {
        if(db.photoDao().findByCode(photo.getCode()) == null)
            db.photoDao().insertAll(photo);
        else
            return null;
        return photo;
    }

    private void setupUI() {

    }
}
