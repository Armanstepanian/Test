package RoomPackage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import RoomPackage.entity.Groups;


@Dao
public interface GroupsDao {
    @Query("SELECT * FROM groups")
    LiveData<List<Groups>> getAll();

    @Query("SELECT * FROM groups WHERE isSynced = :isSynced")
    Groups getAllSynched(Boolean isSynced);

    @Insert
    void insert(Groups groups);

    @Update
    void update(Groups groups);

    @Query("DELETE FROM groups")
    void delete();

    @Query("DELETE FROM groups WHERE id = :id")
    void delete(int id);

    @Delete()
    void delete(Groups groups);


}
