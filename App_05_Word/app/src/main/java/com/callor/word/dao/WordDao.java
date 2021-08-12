package com.callor.word.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.callor.word.model.WordDTO;

@Dao
public interface WordDao {

    /**
     * 데이터 selectAll method return type을 다음과 같이 지정한다
     * LiveData<데이터리스트>
     *
     */
    @Query("SELECT * FROM tbl_word ORDER BY word")
    public LiveData<WordDTO> selectAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(WordDTO wordDTO);

    @Query("DELETE FROM tbl_word")
    public void deleteAll(); // 전체삭제(초기화)
}
