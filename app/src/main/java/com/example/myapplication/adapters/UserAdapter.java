package com.example.myapplication.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.RecylerViewActivity;
import com.example.myapplication.databinding.LayoutUserCellBinding;
import com.example.myapplication.models.UserDetail;

import java.util.ArrayList;

public class UserAdapter  extends
        RecyclerView.Adapter<UserAdapter.UserHolder>{
    Context context;
    ArrayList<UserDetail> detailArrayList;

    public UserAdapter(Context context,
                       ArrayList<UserDetail> detailArrayList) {
        this.context = context;
        this.detailArrayList = detailArrayList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        LayoutUserCellBinding binding = LayoutUserCellBinding
                .inflate(LayoutInflater.from(context),parent,
                        false);
        return new UserHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
            final  int p = position;
            UserDetail obj = detailArrayList.get(position);
            holder.binding.textViewName.setText(obj.userName);
            holder.binding.textViewDetails.setText(obj.userPassword);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,
                            "Tapped item view "+p,
                            Toast.LENGTH_SHORT).show();
                }
            });
            holder.binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RecylerViewActivity objR =
                            (RecylerViewActivity) context;
                    objR.deleteUser(obj);
                }
            });
            holder.binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,
                            obj.userName,
                            Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return detailArrayList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        LayoutUserCellBinding binding;

        public UserHolder(@NonNull LayoutUserCellBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
