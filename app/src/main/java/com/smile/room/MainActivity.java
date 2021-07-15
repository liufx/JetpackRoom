package com.smile.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smile.room.dao.UserDao;
import com.smile.room.db.UserDatabase;
import com.smile.room.entity.User;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvAdd;
    TextView tvUpdate;
    TextView tvDelete;
    TextView tvQuery;
    TextView tvQueryAll;
    TextView tvContent;
    UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDatabase = UserDatabase.getInstance(this);
        setContentView(R.layout.activity_main);
        tvAdd = findViewById(R.id.tv_add);
        tvUpdate = findViewById(R.id.tv_update);
        tvDelete = findViewById(R.id.tv_delete);
        tvQuery = findViewById(R.id.tv_query);
        tvQueryAll = findViewById(R.id.tv_queryAll);
        tvContent = findViewById(R.id.tv_content);
        UserDao userDao = userDatabase.getUserDao();
        LiveData<List<User>> listLiveData = userDao.getAllUserLive();
        listLiveData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                String s = "";
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    s += "姓名：" + user.getName() + "性别：" + user.getGender() + "年龄：" + user.getAge() + "类型：" + user.getType() +"\n";
                }
                tvContent.setText(s);
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setName("张三" + new Random().nextInt(100));
                user.setAge(new Random().nextInt(100));
                user.setGender(new Random().nextInt(1) + "");
                user.setType(new Random().nextInt(9));
                userDao.insertUser(user);
            }
        });

        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<User> users = userDao.getAllUser();
                User user = users.get(0);
                user.setName("玛丽哈利");
                userDao.updateUser(user);
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = userDao.getAllUser();
                if(users!=null && users.size()>0){
                    User user = users.get(0);
                    userDao.deleteUser(user);
                }

            }
        });
        tvQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = userDao.getAllUser();
                String s = "";
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    s += "姓名：" + user.getName() + "性别：" + user.getGender() + "年龄：" + user.getAge() + "类型：" + user.getType() +"\n";
                }
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
        });
        tvQueryAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}