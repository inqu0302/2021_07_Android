package com.callor.word.config;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.callor.word.dao.WordDao;
import com.callor.word.model.WordDTO;

import java.util.concurrent.ExecutorService;

/**
 * RoomDatabase를 상속받아 DB Connection, Session을 만드는 클래스
 * 이 클래스는 반드시 abstract 키워드를 추가하여
 * 추상클래스로 선언을 해야한다
 *
 * 일부는 직접코드를 구현하는  method를 포함하고
 * 일부는 interface처럼 코드가 구현되지 않은 method를 함께 포함하는 클래스
 */

// 만약 DB table이 없으면 WordDTO 클래스를 참조하여 table을 만들어라 /entities 항목
// 사용중 table에 변경사항이 발생하면 WordDTO 클래스에 칼럼을 변경하고
// version 넘버를 변경하며 table을 재구성한다 /version 항목 작동내용
@Database(entities = {WordDTO.class}, version = 1)
public abstract class WordDataBase extends RoomDatabase {

    static WordDataBase dbConn;

    // 데이터 관련 코드에서 사용항 DB Connection(Session) 객체를 return하는 method
    public static WordDataBase getDataBase(final Context context) {

        if (dbConn == null) {
            dbConn = Room.databaseBuilder(
                    context.getApplicationContext(),
                    WordDataBase.class,
                    "word_database"
            ).addCallback(null).build();
        }

        return dbConn;
    }

    // 시스템이 생성하여 dao를 만드는 코드로 재 작성될 것이다
    abstract WordDao wordDao();
}
