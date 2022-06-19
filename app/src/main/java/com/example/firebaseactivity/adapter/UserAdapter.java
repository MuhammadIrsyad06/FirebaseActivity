package com.example.firebaseactivity.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseactivity.R;
import com.example.firebaseactivity.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context context;
    private List<User> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog){
        this.dialog=dialog;
    }
    public UserAdapter(Context context,List<User>list){
        this.context=context;
        this.list=list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        holder.namePeru.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.posisi.setText(list.get(position).getPosisi());
        holder.gaji.setText(list.get(position).getGaji());
        holder.syarat.setText(list.get(position).getSyarat());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView namePeru, email,posisi,gaji,syarat;

        public MyViewHolder(View itemView){
            super(itemView);
            namePeru=itemView.findViewById(R.id.namePeru);
            email=itemView.findViewById(R.id.email);
            posisi=itemView.findViewById(R.id.posisi);
            gaji=itemView.findViewById(R.id.gaji);
            syarat=itemView.findViewById(R.id.syarat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}

