package Reposytorys;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import RoomPackage.DBHelper;
import RoomPackage.dao.GroupsDao;
import RoomPackage.entity.Groups;

public class GroupsReposytorys {
    GroupsDao groupsDao;
    LiveData<List<Groups>> allGroups;
    public GroupsReposytorys(Application application){
        DBHelper groupsDB = DBHelper.getGroupsDatabase(application);
        groupsDao =groupsDB.groupsDao();
        allGroups = groupsDao.getAll();

    }

    public void insert(Groups groups) {
        new InsertNoteAsyncTask(groupsDao).execute(groups);
    }

    public void update(Groups groups) {
        new UpdateNoteAsyncTask(groupsDao).execute(groups);
    }

    public void delete(Groups groups) {
        new DeleteNoteAsyncTask(groupsDao).execute(groups);
    }

    public void deleteAllGroups() {
        new DeleteAllNotesAsyncTask(groupsDao).execute();
    }

    public LiveData<List<Groups>> getAllGroups() {
        return allGroups;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Groups, Void, Void> {
        private GroupsDao groupsDao;

        private InsertNoteAsyncTask(GroupsDao groupsDao) {
            this.groupsDao = groupsDao;
        }

        @Override
        protected Void doInBackground(Groups... groups) {
            groupsDao.insert(groups[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Groups, Void, Void> {
        private GroupsDao groupsDao;

        private UpdateNoteAsyncTask(GroupsDao groupsDao) {
            this.groupsDao = groupsDao;
        }

        @Override
        protected Void doInBackground(Groups... groups) {
            groupsDao.update(groups[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Groups, Void, Void> {
        private GroupsDao groupsDao;

        private DeleteNoteAsyncTask(GroupsDao groupsDao) {
            this.groupsDao = groupsDao;
        }

        @Override
        protected Void doInBackground(Groups... groups) {
            groupsDao.delete(groups[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private GroupsDao groupDao;

        private DeleteAllNotesAsyncTask(GroupsDao groupDao) {
            this.groupDao = groupDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            groupDao.delete();
            return null;
        }
    }
}
