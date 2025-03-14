package com.example.btt7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btt7.model.Category;
import com.example.btt7.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Category> array;

    public CategoryAdapter(Context context, List<Category> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, null);
        MyViewholder myViewholder = new MyViewholder(view);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Category category = array.get(position);
        MyViewholder myViewholder = (MyViewholder) holder;
        myViewholder.tenSP.setText(category.getName());
        // load anh voi Glide
        Glide.with(context)
                .load(category.getImage())
                .into(((MyViewholder) holder).images);
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 :array.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSP;
        public MyViewholder(@NonNull View itemView){
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.image_cate);
            tenSP = (TextView) itemView.findViewById(R.id.tvNameCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Bạn chọn" + tenSP.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}