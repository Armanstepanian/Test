package RoomPackage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


import RoomPackage.entity.Users;

@Dao
public interface UsersDao {

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAll();

    @Query("SELECT * FROM users WHERE isSynched = :isSynched")
    Users getAllSynched(Boolean isSynched);

    @Insert
    void insert(Users users);

    @Update
    void update(Users users);

    @Query("DELETE FROM users")
    void delete();

    @Delete()
    void delete(Users users);


}
