package com.example.lifechoices;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifechoices.Adapter.GuideAdapter;
import com.example.lifechoices.MyProgress.Adapter;
import com.example.lifechoices.MyProgress.Item;
import com.example.lifechoices.finishall.FinishAllAdapter;
import com.example.lifechoices.roomb.AppDatabase;
import com.example.lifechoices.roomb.AppDatabaseDrawer;
import com.example.lifechoices.roomb.AppDatabasefensh;
import com.example.lifechoices.roomb.DrawerItem;
import com.example.lifechoices.roomb.DrawerItemDao;
import com.example.lifechoices.roomb.ItemDao;
import com.example.lifechoices.roomb.User;
import com.example.lifechoices.roomb.UserDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    List<DrawerItem> drawerlist0;
    RecyclerView drawerrecyclerView0;
    RecyclerView progressRecyclerview;
    List<Item> progressList;
    CircleImageView headImg;
    AlertDialog dialog;
    Dialog inputDialog;
    View dialogView;
    EditText editText;
    DrawerItemDao drawerItemDao;
    GuideAdapter adapter;
    FloatingActionButton floatingActionButton;
    ItemDao itemDao;
    ImageButton imageButton_dialog;
    EditText editText_dialog;
    View view;//inputdialog加载的布局
    RecyclerView recyclerViewMian;
    List<com.example.lifechoices.roomb.Item> mainlist;//主页recyclerview的list
    EditText editText_meaning;
    UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setstatusbartranste();
        intiDate();
        iniView();
        initDialog();
        //点击drawerlayout里的头像
        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


            }
        });
        //点击悬浮按钮
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDialog.show();
                showSoftInput();
            }
        });
        //点击保存按钮
        imageButton_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fenShu = 0;
                String content = editText_dialog.getText().toString();
                try {
                    fenShu=  Integer.parseInt(editText_meaning.getText().toString());

                    itemDao.insertItem(new com.example.lifechoices.roomb.Item(0, content, fenShu, Item.position));
                    mainlist = itemDao.loadAllItemsByPosition(Item.position);
                    recyclerViewMian.setAdapter(new FinishAllAdapter(mainlist, MainActivity.this));
                }catch ( Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_LONG).show();

                }


                inputDialog.dismiss();
            }
        });

    }

    public void updateMainrecy(List<com.example.lifechoices.roomb.Item> list) {
        recyclerViewMian.setAdapter(new FinishAllAdapter(list, this));
    }

    //初始化dialog
    protected void initDialog() {
        //初始化AlertDialog
        dialog = new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.drawable.present)//设置标题的图片
                .setTitle("添加新奖励")//设置对话框的标题
                .setView(dialogView)
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

                        drawerItemDao.insertItem(new DrawerItem(R.drawable.present,
                                content,
                                0
                        ));
                        drawerlist0 = drawerItemDao.loadAllItems();
                        adapter = new GuideAdapter(drawerlist0, MainActivity.this);
                        drawerrecyclerView0.setAdapter(adapter);
                        dialog.dismiss();
                    }
                }).create();

        //设置inputDialog的大小样式动画

        inputDialog = new Dialog(this, R.style.DialogTheme);
        inputDialog.setContentView(view);
        Window window = inputDialog.getWindow();
        window.setBackgroundDrawable(null);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.my_style);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    //设置标题栏为透明。。。//实现状态栏图标和文字颜色为暗色
    public void setstatusbartranste() {
        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

    }

    public void iniView() {
        drawerrecyclerView0 = (RecyclerView) findViewById(R.id.recy_drawer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        drawerrecyclerView0.setLayoutManager(linearLayoutManager);
        adapter = new GuideAdapter(drawerItemDao.loadAllItems(), this);
        drawerrecyclerView0.setAdapter(adapter);
        drawerrecyclerView0.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));

        headImg = findViewById(R.id.icon_image);

        dialogView = getLayoutInflater().inflate(R.layout.layou_dialog_view, null);
        editText = (EditText) dialogView.findViewById(R.id.dialog_edit);

        progressRecyclerview = findViewById(R.id.recy);
        progressRecyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        Adapter progressAdapter = new Adapter(progressList, this);
        progressRecyclerview.setAdapter(progressAdapter);

        floatingActionButton = findViewById(R.id.float_add);
//加载inputDialog布局
        view = View.inflate(this, R.layout.dialog_edit, null);
        imageButton_dialog = view.findViewById(R.id.btn_dialog);
        editText_dialog = view.findViewById(R.id.edit_dialog);

        recyclerViewMian = findViewById(R.id.recy_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMian.setLayoutManager(layoutManager);


        FinishAllAdapter finishAllAdapter = new FinishAllAdapter(mainlist, this);
        recyclerViewMian.setAdapter(finishAllAdapter);

        editText_meaning = (EditText) view.findViewById(R.id.edit_meaning);




    }

    public void intiDate() {
        if (drawerlist0 == null)
            drawerlist0 = new ArrayList<>();
        drawerItemDao = AppDatabaseDrawer.Companion.getDatabase(this).DrawerItemDao();
        drawerlist0 = drawerItemDao.loadAllItems();
        progressList = new ArrayList<>();


        mainlist = new ArrayList<>();

        itemDao = AppDatabase.Companion.getDatabase(this).itemDao();
        mainlist = itemDao.loadAllItemsByPosition(Item.position);

        userDao = AppDatabasefensh.Companion.getDatabase(this).itemDao();
        int fenshu;
        if (userDao.loadbyPosition(Item.position).size() > 0) {
            fenshu = userDao.loadbyPosition(Item.position).get(0).getFenshu();
            for (int i = 0; i <= fenshu / 20; i++) {
                progressList.add(new Item(R.drawable.present));
            }
        } else {
            userDao.insertUser(new User(0, Item.position));
        }
    }

    //弹出软键盘
    private void showSoftInput() {
        Timer timer = new Timer();
        //TODO("activity 获取一次，这里又find 一次，似乎把原先的对象给夺过来了，所以，showdialog方法后，editdialog全是null")

        //设置可获得焦点
//        if (!editText_dialog.isFocusable()) {
//            editText_dialog.setFocusable(true);
//        }
//        if (!editText_dialog.isFocusableInTouchMode()) {
//            editText_dialog.setFocusableInTouchMode(true);
//        }

        //只有这个有用
        editText_dialog.requestFocus();
        //Edittext绘制完毕才能显示软件盘，所以这里设置了个延时
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

            }
        }, 300);

    }

   public void updateProgress(){
        progressList.clear();
       int fenshu =  userDao.loadbyPosition(Item.position).get(0).getFenshu();
       for (int i = 0; i <= fenshu / 20; i++) {
           progressList.add(new Item(R.drawable.present));
       }
   progressRecyclerview.setAdapter(new Adapter(progressList,this));
   }
}