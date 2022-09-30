package com.example.lifechoices.Adapter;

import android.app.Service;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lifechoices.MainActivity;
import com.example.lifechoices.MyProgress.Item;
import com.example.lifechoices.R;
import com.example.lifechoices.roomb.AppDatabase;
import com.example.lifechoices.roomb.AppDatabaseDrawer;
import com.example.lifechoices.roomb.DrawerItem;
import com.example.lifechoices.roomb.DrawerItemDao;
import com.example.lifechoices.roomb.ItemDao;

import java.util.List;


public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {
    private List<DrawerItem> list;
    private MainActivity context;

    public GuideAdapter(List<DrawerItem> list, MainActivity context) {

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
        holder.count.setText(count + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item.position = holder.getAbsoluteAdapterPosition();
                ItemDao itemDao = AppDatabase.Companion.getDatabase(context).itemDao();
                List<com.example.lifechoices.roomb.Item>  listmain=itemDao.loadAllItemsByPosition(Item.position);
                context.updateMainrecy(listmain);
                context.updateProgress();
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DrawerItem drawerItem = list.get(holder.getAbsoluteAdapterPosition());
               DrawerItemDao drawerItemDao = AppDatabaseDrawer.Companion.getDatabase(context).DrawerItemDao();
               drawerItemDao.deleteItem(drawerItem);
               list = drawerItemDao.loadAllItems();
                Vibrator vib = (Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);
                vib.vibrate(100);//只震动一秒，一次


               notifyDataSetChanged();
              return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
