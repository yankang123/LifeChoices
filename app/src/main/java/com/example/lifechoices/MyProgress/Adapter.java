package com.example.lifechoices.MyProgress;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifechoices.R;
import com.example.lifechoices.roomb.AppDatabasefensh;
import com.example.lifechoices.roomb.UserDao;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Item> list;
    private Context context;

    public Adapter(List<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserDao userDao = AppDatabasefensh.Companion.getDatabase(context).itemDao();
        double sum = (double) userDao.loadbyPosition(Item.position).get(0).getFenshu();
        int imgId = list.get(position).getImgId();
//         Glide.with(context).load(imgId).into(holder.imageView);
        double count = Math.floor(((double) sum / 20));
        if (position + 1 <= count) {
            holder.myProgressView.setPrgress(20);
            presentAnimate(holder.imageView);
        }
        else if (position == count) {
            holder.myProgressView.setPrgress(sum % 20);
        } else {
            holder.myProgressView.setPrgress(0);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MyProgressView myProgressView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myProgressView = itemView.findViewById(R.id.myView);
            imageView = itemView.findViewById(R.id.img);
        }
    }
//伸缩和旋转动画
    public void presentAnimate(ImageView imageView) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 35f, -35f, 0f, -35);
        objectAnimator.setDuration(1500);
        objectAnimator.start();
        ObjectAnimator objectAnimatortwo = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 2f, 1f, 1.2f);
        objectAnimatortwo.setDuration(1500);
        objectAnimatortwo.start();
    }
}
