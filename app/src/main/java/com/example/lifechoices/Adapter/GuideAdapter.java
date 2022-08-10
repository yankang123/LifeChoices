package com.example.lifechoices.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.lifechoices.MyApplication;
import com.example.lifechoices.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {
private List<Guide> list ;
private Context context;
    public GuideAdapter(List<Guide> list,Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_guide,
                parent,
                false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         int id = list.get(position).getImgId();
         String content = list.get(position).getContent();
         int count = list.get(position).getCount();
       Glide.with(context).load(id).into(holder.imageView);
         holder.content.setText(content);
         holder.count.setText(count+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
       private ImageView imageView;
       private TextView content;
       private TextView count;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           imageView = itemView.findViewById(R.id.img_item_drawer_recy);
           content = itemView.findViewById(R.id.text_item_drawer_recy_content);
           count = itemView.findViewById(R.id.text_item_drawer_recy_count);
       }
   }

}
