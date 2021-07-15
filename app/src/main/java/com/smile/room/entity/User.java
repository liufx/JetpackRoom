package com.smile.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @Company： 天之骄子
 * @ProjectName: JatpackRoom
 * @Package: com.smile.room.entity
 * @ClassName: User
 * @Description: java类作用描述
 * @Author: smile
 * @CreateDate: 2021/7/14 5:02 下午
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/7/14 5:02 下午
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@Entity(tableName = "User" ,indices = {@Index(value = {"user_name"}, unique = true)})//数据库实体
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "user_name")
    private String name;
    @ColumnInfo(name = "user_gender")
    private String gender;

    private int age;

    private int type;

    public User() {
    }

    public User(long id, String name, String gender, int age, int type) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.type = type;
    }

    public User(String name, String gender, int age, int type) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
