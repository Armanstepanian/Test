package ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Reposytorys.UsersReposytory;
import RoomPackage.entity.Users;

public class UsersViewModel extends AndroidViewModel {
    UsersReposytory usersReposytory;
    LiveData<List<Users>> allUsers;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        usersReposytory = new UsersReposytory(application);
        allUsers = usersReposytory.getAllGroups();
    }

    public void insert(Users users) {
        usersReposytory.insert(users);
    }

    public void delete(Users users) {
        usersReposytory.delete(users);
    }

    public void update(Users users) {
        usersReposytory.update(users);
    }

    public void delteAll() {
        usersReposytory.deleteAllGroups();
    }
    public LiveData<List<Users>> getAllUsers(){
        return allUsers;
    }
}
