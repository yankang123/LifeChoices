package com.example.lifechoices;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lifechoices.Adapter.Guide;
import com.example.lifechoices.Adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Guide> drawerlist0;
    List<Guide> drawerlist1;
    RecyclerView drawerrecyclerView0;
    RecyclerView drawerrecyclerView1;
    CircleImageView headImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setstatusbartranste();
        intiDate();
        iniView();
//       ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }



        View dialogview = getLayoutInflater().inflate(R.layout.layou_dialog_view, null);
        final EditText editText = (EditText) dialogview.findViewById(R.id.dialog_edit);
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.drawable.present)//设置标题的图片
                .setTitle("半自定义对话框")//设置对话框的标题
                .setView(dialogview)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editText.getText().toString();
                        Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
headImg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        dialog.show();

    }
});






    }
    //设置标题栏为透明。。。//实现状态栏图标和文字颜色为暗色
    public void setstatusbartranste(){
        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

    }
    public void iniView(){
        drawerrecyclerView0 = (RecyclerView) findViewById(R.id.recy_drawer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        drawerrecyclerView0.setLayoutManager(linearLayoutManager);
        GuideAdapter adapter = new GuideAdapter(drawerlist0,this);
        drawerrecyclerView0.setAdapter(adapter);
//        drawerrecyclerView1 = (RecyclerView) findViewById(R.id.recy_drawer0);
//        drawerrecyclerView1.setLayoutManager(new LinearLayoutManager(this));
//        drawerrecyclerView1.setAdapter(new GuideAdapter(drawerlist1,this));
        headImg = findViewById(R.id.icon_image);


    }
    public void intiDate(){
        drawerlist0 = new ArrayList<>();
        drawerlist0 = Arrays.asList(new Guide(R.drawable.myself,"我爱你",2),
                new Guide(R.drawable.myself,"我爱你",2),
                new Guide(R.drawable.myself,"我爱你",2),
                new Guide(R.drawable.myself,"我爱你",2)
        );
//        drawerlist1 = new ArrayList<>();
//        drawerlist1.add(new Guide(R.drawable.myself,"全部",3));
//        drawerlist1.add(new Guide(R.drawable.myself,"奖励",3));
    }
}