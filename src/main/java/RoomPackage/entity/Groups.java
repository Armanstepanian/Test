package RoomPackage.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "groups")
//@Getter
//@Setter
public class Groups {
    @PrimaryKey
    private int id;

    private int firebaseId;
    private Boolean isSynced;
    private String type;
    private String name;

    public Groups(int id, int firebaseId, Boolean isSynced, String type, String name) {
        this.id = id;
        this.firebaseId = firebaseId;
        this.isSynced = isSynced;
        this.type = type;
        this.name = name;
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

    public Boolean getSynced() {
        return isSynced;
    }

    public void setSynced(Boolean synced) {
        isSynced = synced;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
