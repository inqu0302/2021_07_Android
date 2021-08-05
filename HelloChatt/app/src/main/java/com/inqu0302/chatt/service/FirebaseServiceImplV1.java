package com.inqu0302.chatt.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.inqu0302.chatt.Adapter.ChattAdapter;
import com.inqu0302.chatt.model.Chatt;

public class FirebaseServiceImplV1 implements ChildEventListener {

    private ChattAdapter adapter;

    public FirebaseServiceImplV1(ChattAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        // 데이터 파싱하는 방식과 비슷함
        Chatt chattVO = snapshot.getValue(Chatt.class);
        adapter.addChatList(chattVO);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
