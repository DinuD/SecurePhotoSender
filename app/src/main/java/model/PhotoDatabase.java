package model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Photo.class}, exportSchema = false, version = 1)
public abstract class PhotoDatabase extends RoomDatabase {

    private static PhotoDatabase INSTANCE;
    public abstract PhotoDao photoDao();

    public static PhotoDatabase getPhotoDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PhotoDatabase.class, "photo-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
