package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test.R;
import java.util.ArrayList;
import java.util.List;

import RoomPackage.entity.Groups;
import RoomPackage.entity.Users;


public class UsersAdaper extends ListAdapter<Users,UsersAdaper.Holder>  {
    private List<Users> users = new ArrayList<>();
    private OnItemClickListener listener;

    protected UsersAdaper() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Users> DIFF_CALLBACK = new DiffUtil.ItemCallback<Users>() {
        @Override
        public boolean areItemsTheSame(Users oldItem, Users newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Users oldItem, Users newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getType().equals(newItem.getType()) &&
                    oldItem.getSynched().equals(newItem.getSynched());

        }
    };


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.groups_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Users usr = users.get(position);

        holder.textViewDescription.setText(usr.getType());
        holder.textViewPriority.setText(usr.getName());
        holder.textViewTitle.setText(usr.getId());

    }
    public Users getUserAt(int pos){
        return getItem(pos);
    }
    public void setUsers(List<Users> users) {
        this.users = users;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public Holder(@NonNull View itemView) {
            super(itemView);
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
        void onItemClick(Users users);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}



