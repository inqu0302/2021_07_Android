package com.callor.word.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.callor.word.dao.WordDao;
import com.callor.word.model.WordDTO;

import java.util.List;

/**
 * WordDao와 WordDTO를 사용하여 DataBase 핸들링을 하는 중각 Service클래스
 */
public class WordRepository {

    protected WordDao wordDao;
    protected LiveData<List<WordDTO>> wordList;

    /**
     * Application Context를 매개변수로 갖는 생성자
     */
    public WordRepository(Application application) {

    }

    /**
     * 외부에서 repository.selectAll() method를 호출하면
     * 내부에서 자동으로 Dao를 통해 Select르 수행하고 그 결과를 return 한다
     */
    public LiveData<List<WordDTO>> selectAll() {
        return wordList;
    }

    public void insert (WordDTO wordDTO){
        wordDao.insert(wordDTO);
    }

    public void deletAll(){
        wordDao.deleteAll();
    }
}
