package RoomPackage;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import RoomPackage.dao.GroupsDao;
import RoomPackage.dao.UsersDao;
import RoomPackage.entity.Groups;
import RoomPackage.entity.Users;


@Database(entities = {Users.class, Groups.class} ,version = 1)

public abstract class DBHelper extends RoomDatabase {

    private static DBHelper GroupsInstance;
    private static DBHelper UsersInstance;

    public abstract UsersDao usersDao();
    public abstract GroupsDao groupsDao();


    public static DBHelper getGroupsDatabase(Context context) {
        if (GroupsInstance == null) {
            GroupsInstance = Room.databaseBuilder(context.getApplicationContext(),
                    DBHelper.class,
                    "groups")
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return GroupsInstance;
    }
    public static DBHelper getUsersDatabase(Context context) {
        if (UsersInstance == null) {
            UsersInstance = Room.databaseBuilder(context.getApplicationContext(),
                    DBHelper.class,
                    "users")
                    .allowMainThreadQueries()
                    .build();
        }
        return UsersInstance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(GroupsInstance).execute();
            new PopulateDbAsyncTask(UsersInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private GroupsDao groupsDao;
        private UsersDao usersDao;


        private PopulateDbAsyncTask(DBHelper db) {
            groupsDao = db.groupsDao();
            usersDao = db.usersDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            groupsDao.insert(new Groups(1,4,true, "Description 2", "asdasd"));

            groupsDao.insert(new Groups(2,5,true, "Description 2", "asdasd"));

            groupsDao.insert(new Groups(3,6,true, "Description 2", "asdasd"));

            return null;
        }
    }

}
