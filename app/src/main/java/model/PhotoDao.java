package model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PhotoDao {
    @Query("SELECT * FROM photo")
    List<Photo> getAll();

    @Query("SELECT * FROM photo where code LIKE :mCode")
    Photo findByCode(String mCode);

    @Query("SELECT COUNT(*) from photo")
    int countPhotos();

    @Insert
    void insertAll(Photo... photos);

    @Delete
    void delete(Photo photo);
}
