package Reposytorys;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import RoomPackage.DBHelper;
import RoomPackage.dao.UsersDao;
import RoomPackage.entity.Users;

public class UsersReposytory {

    UsersDao usersDao;
    LiveData<List<Users>> allUsers;
    public UsersReposytory(Application application){
        DBHelper usersDB = DBHelper.getUsersDatabase(application);
        usersDao =usersDB.usersDao();
        allUsers = usersDao.getAll();
    }

    public void insert(Users users) {
        new InsertNoteAsyncTask(usersDao).execute(users);
    }

    public void update(Users users) {
        new UpdateNoteAsyncTask(usersDao).execute(users);
    }

    public void delete(Users users) {
        new DeleteNoteAsyncTask(usersDao).execute(users);
    }

    public void deleteAllGroups() {
        new DeleteAllNotesAsyncTask(usersDao).execute();
    }

    public LiveData<List<Users>> getAllGroups() {
        return allUsers;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Users, Void, Void> {
        private UsersDao usersDao;

        private InsertNoteAsyncTask(UsersDao usersDao) {
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            usersDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Users, Void, Void> {
        private UsersDao usersDao;

        private UpdateNoteAsyncTask(UsersDao usersDao) {
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            usersDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Users, Void, Void> {
        private UsersDao usersDao;

        private DeleteNoteAsyncTask(UsersDao usersDao) {
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            usersDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private UsersDao usersDao;

        private DeleteAllNotesAsyncTask(UsersDao usersDao) {
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            usersDao.delete();
            return null;
        }
    }
}
