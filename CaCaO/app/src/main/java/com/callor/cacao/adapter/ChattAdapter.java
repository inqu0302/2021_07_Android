package com.callor.cacao.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.cacao.R;
import com.callor.cacao.model.Chatt;

import java.util.List;

public class ChattAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Chatt> chattList;
    private String name;

    public void addChatList(Chatt chatt){

        chattList.add(chatt);

        notifyItemInserted(chattList.size()-1);
    }

    public ChattAdapter(List<Chatt> chattList){
//      this.chattList = chattList;
        this(chattList,"익명");
    }

    public ChattAdapter(List<Chatt> chattList, String name){
        this.chattList = chattList;
        this.name = name;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chatt_item, parent, false);

        ChattViewHolder viewHolder = new ChattViewHolder(item_layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Chatt chatt = chattList.get(position);

        ChattViewHolder chattViewHolder = (ChattViewHolder) holder;

        chattViewHolder.item_name.setText(chatt.getName());
        chattViewHolder.item_msg.setText(chatt.getMsg());

        /**
         * 현재 App에서 메시지를 DB에서 가져올때(Fetch) 저장된 이름이 같으면 작동
         * this.name 변수에는 App에서 설정한 nickname이 담겨있고
         * firebase에서 가져온 데이터의 이름과 같으면 오른쪽 정렬시킨다
         */
        if(this.name.equals(chatt.getName())){

            chattViewHolder.item_name.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            chattViewHolder.item_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            chattViewHolder.msgLinear.setGravity(Gravity.RIGHT);
            chattViewHolder.item_msg.setBackgroundColor(Color.parseColor("#44aa99"));
        }
    }

    @Override
    public int getItemCount() {

        return chattList == null ? 0 : chattList.size();
    }

    public static class ChattViewHolder extends RecyclerView.ViewHolder{

        public TextView item_name;
        public TextView item_msg;

        public LinearLayout msgLinear;

        public ChattViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_msg = itemView.findViewById(R.id.item_msg);

            /**
             * item_name 과 item_msg를 감싸고 있는 layout(LinearLayout) 에 접근하기 위하여
             * 객체로 생성
             */

            msgLinear = itemView.findViewById(R.id.msg_linear);
        }
    }
}
