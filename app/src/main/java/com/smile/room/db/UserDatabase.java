package com.smile.room.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.smile.room.dao.UserDao;
import com.smile.room.entity.User;
import com.smile.room.saferoom.SafeHelperFactory;

/**
 * @Company： 天之骄子
 * @ProjectName: JatpackRoom
 * @Package: com.smile.room
 * @ClassName: UserDatabase
 * @Description: java类作用描述
 * @Author: smile
 * @CreateDate: 2021/7/14 5:37 下午
 * @UpdateUser: 更新者：
 * @UpdateDate: 2021/7/14 5:37 下午
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
//exportSchema = false 是不会导出 schemas json 结构
@Database(entities = {User.class}, version = 2, exportSchema = true)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "android_room_dev.db";

    public abstract UserDao getUserDao();

    private static volatile UserDatabase sInstance;

    public static UserDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (UserDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static UserDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, UserDatabase.class, DATABASE_NAME)
                .openHelperFactory(new SafeHelperFactory("123456".toCharArray()))
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Log.e("AppDatabase", "buildDatabase-onCreate");
                    }
                })
                .addMigrations(migration)
                .allowMainThreadQueries()
                .build();
    }

    static final Migration migration = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user ADD COLUMN type INTEGER NOT NULL DEFAULT 1");
            //sqlLet没有删除字段语句，只能创建新的数据库定义需要的字段，将原有数据库数据复制过去，删除旧数据库后再将新数据库重命名
//            database.execSQL("CREATE TABLE user_temp (id INTEGER PRIMARY KEY NOT NULL , user_name TEXT," +
//                    "user_gender TEXT)");
//            database.execSQL("INSERT INTO user_temp (id, user_name, user_gender) " +
//                    "SELECT id, user_name, user_gender FROM user");
//            database.execSQL("DROP TABLE user");
//            database.execSQL("ALTER TABLE user_temp RENAME to user");

        }
    };
}
