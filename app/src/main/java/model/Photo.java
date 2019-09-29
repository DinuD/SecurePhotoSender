package model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "photo")
public class Photo {

    @PrimaryKey @NonNull
    private String code;
    private String link;
    private Boolean sent;

    public Photo(String code, String link, Boolean sent) {
        this.code = code;
        this.link = link;
        this.sent = sent;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean isSent() {
        return sent;
    }

    public String getCode() {
        return code;
    }

    public String getLink() {
        return link;
    }
}

