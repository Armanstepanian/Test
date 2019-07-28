package com.example.test;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;
import java.util.List;

import Adapters.GroupsAdapter;
import RoomPackage.entity.Groups;
import RoomPackage.entity.Users;
import ViewModels.GroupsViewModel;

public class GroupsActivity extends AppCompatActivity {

    public static final int add_groups_request = 1;
    public static final int delete_groups_request = 2;
    public static final int edit_groups_request = 3;

    private RecyclerView recycler;
    private Button editGroups;
    private Button deleteButton;
    private Button addGroups;

    private GroupsViewModel groupsViewModel;
    private ArrayList<Groups> arr = new ArrayList<>();

    private GroupsAdapter adapter;

    setContext set_Context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        set_Context = new setContext();

        deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO

                Intent intent = new Intent(GroupsActivity.this, EditActivity.class);
                startActivityForResult(intent, delete_groups_request);
            }
        });
        editGroups = (Button) findViewById(R.id.edit_groups);
        editGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupsActivity.this, EditActivity.class);
                startActivityForResult(intent, edit_groups_request);
            }
        });
        addGroups = (Button) findViewById(R.id.add_groups);
        addGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupsActivity.this, EditActivity.class);
                startActivityForResult(intent, add_groups_request);
            }
        });


        recycler = (RecyclerView) findViewById(R.id.groupsList);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

         adapter = new GroupsAdapter(getApplicationContext(), this, this);

        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);

        groupsViewModel = ViewModelProviders.of(this).get(GroupsViewModel.class);
        groupsViewModel.getAllGroups().observe(this, new Observer<List<Groups>>() {
            @Override
            public void onChanged(@Nullable List<Groups> groups) {
                adapter.setGroups(groups);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                groupsViewModel.delete(adapter.getGroupAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recycler);


        adapter.setOnItemClickListener(new GroupsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Groups grp) {
                Intent intent = new Intent(GroupsActivity.this, EditActivity.class);
                intent.putExtra(EditActivity.getExtra_id(), grp.getId());
                intent.putExtra(EditActivity.getExtra_type(), grp.getType());
                intent.putExtra(EditActivity.getExtra_group(), grp.getName());
                startActivityForResult(intent, edit_groups_request);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == add_groups_request && resultCode == RESULT_OK) {
            String group = data.getStringExtra(EditActivity.getExtra_group());
            String id = data.getStringExtra(EditActivity.getExtra_id());
            String type = data.getStringExtra(EditActivity.getExtra_type());
            Groups groups = new Groups(Integer.parseInt(id), 5, false, type, group);

        } else if (requestCode == edit_groups_request && resultCode == RESULT_OK) {
            String id = data.getStringExtra("id");

            String group = data.getStringExtra(EditActivity.getExtra_group());
            String extra_id = data.getStringExtra(EditActivity.getExtra_id());
            String type = data.getStringExtra(EditActivity.getExtra_type());

            Groups groups = new Groups(Integer.parseInt(id), 5, false, type, group);
            groups.setId(Integer.parseInt(extra_id));

            GroupsViewModel.update(groups);

        } else if (requestCode == delete_groups_request && resultCode == RESULT_OK) {
            groupsViewModel.deleteAllGroups();
            Toast.makeText(getApplicationContext(), "all groups are deleted ", Toast.LENGTH_LONG).show();
        }
    }
    private void getDataFromFirebase(){
        for(int i = 0 ; i < 10; i ++){
            addOnRecyclerView(i,i,true,"type","name");
        }
    }
    private void addOnRecyclerView(int id,int firebaseId,boolean isSynched,String type,String name){
        Groups grp = new Groups(id,firebaseId,isSynched,type,name);
        arr.add(grp);
        adapter.notifyDataSetChanged();
    }
    class setContext extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //TODO
            getDataFromFirebase();
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }

    }
}
