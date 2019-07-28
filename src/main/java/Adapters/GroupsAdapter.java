package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.EditActivity;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

import RoomPackage.entity.Groups;
import RoomPackage.entity.Users;
import ViewModels.UsersViewModel;

import static com.example.test.GroupsActivity.edit_groups_request;


public class GroupsAdapter extends ListAdapter<Groups, GroupsAdapter.ViewHolder> {
    private List<Groups> groups = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;
    private UsersViewModel usersViewModel;
    private Activity activity;
    private LifecycleOwner owner;

    public GroupsAdapter(Context context, Activity activity, LifecycleOwner owner) {

        super(DIFF_CALLBACK);
        this.activity = activity;
        this.context = context;
        this.owner = owner;
    }

    private static final DiffUtil.ItemCallback<Groups> DIFF_CALLBACK = new DiffUtil.ItemCallback<Groups>() {
        @Override
        public boolean areItemsTheSame(Groups oldItem, Groups newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Groups oldItem, Groups newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getType().equals(newItem.getType()) &&
                    oldItem.getSynced().equals(newItem.getSynced());
        }
    };

    @NonNull
    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.groups_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsAdapter.ViewHolder holder, int position) {
        Groups grp = groups.get(position);

        holder.textViewDescription.setText(grp.getType());
        holder.textViewPriority.setText(grp.getName());
        holder.textViewTitle.setText(grp.getId());
    }

    public Groups getGroupAt(int pos) {
        return getItem(pos);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recycler_user;
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public ViewHolder(View itemView) {
            super(itemView);
            recycler_user = itemView.findViewById(R.id.usersList);
            recycler_user.setLayoutManager(new LinearLayoutManager(context));

            final UsersAdaper adapter = new UsersAdaper();


            recycler_user.setAdapter(adapter);
            usersViewModel.getAllUsers().observe(owner, new Observer<List<Users>>() {
                @Override
                public void onChanged(@Nullable List<Users> users) {
                    adapter.setUsers(users);
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
                    usersViewModel.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                }
            }).attachToRecyclerView(recycler_user);


            adapter.setOnItemClickListener(new UsersAdaper.OnItemClickListener() {
                @Override
                public void onItemClick(Users usr) {
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra(EditActivity.getExtra_id(), usr.getId());
                    intent.putExtra(EditActivity.getExtra_type(), usr.getType());
                    intent.putExtra(EditActivity.getExtra_group(), usr.getName());
                    activity.startActivityForResult(intent, edit_groups_request);
                }
            });


            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Groups groups);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}


