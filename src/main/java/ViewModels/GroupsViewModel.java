package ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import Reposytorys.GroupsReposytorys;
import RoomPackage.entity.Groups;

public class GroupsViewModel extends AndroidViewModel {
    private static GroupsReposytorys repository;
    private LiveData<List<Groups>> allGroups;


    public GroupsViewModel(@NonNull Application application) {
        super(application);
        repository = new GroupsReposytorys(application);
        allGroups = repository.getAllGroups();
    }
    public void insert(Groups groups) {
        repository.insert(groups);
    }

    public static void update(Groups groups) {
        repository.update(groups);
    }

    public void delete(Groups groups) {
        repository.delete(groups);
    }

    public void deleteAllGroups() {
        repository.deleteAllGroups();
    }

    public LiveData<List<Groups>> getAllGroups() {
        return allGroups;
    }
}
