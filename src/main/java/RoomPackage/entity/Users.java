package RoomPackage.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")

public class Users {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public  int firebaseId;

    public String name;
    public String type;
    public Boolean isSynched;

    public Users(int id, int firebaseId, String name, String type, Boolean isSynched) {
        this.id = id;
        this.firebaseId = firebaseId;
        this.name = name;
        this.type = type;
        this.isSynched = isSynched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(int firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSynched() {
        return isSynched;
    }

    public void setSynched(Boolean synched) {
        isSynched = synched;
    }
}
