package com.smile.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.smile.room.entity.User;

import java.util.List;

/**
 * @Company： 天之骄子
 * @ProjectName: JatpackRoom
 * @Package: com.smile.room.dao
 * @ClassName: UserDao
 * @Description: java类作用描述
 * @Author: smile
 * @CreateDate: 2021/7/14 5:06 下午
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/7/14 5:06 下午
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
@Dao  //Database access object  数据库访问接口 所有增删改查等操作都在此声明
public interface UserDao {

    // long 表示插入数据后返回的id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... user);

    // int 影响的行数
    @Update
    int updateUser(User... user);

    @Delete
    int deleteUser(User... user);

    @Query("DELETE FROM USER")
    void deleteUser();

    @Query("SELECT * FROM USER ORDER BY ID DESC")
    List<User> getAllUser();

    @Query("SELECT * FROM USER ORDER BY ID DESC")
    LiveData<List<User>> getAllUserLive();
}
