package com.callor.library.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.library.R;
import com.callor.library.model.NaverBookDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * RecyclerView에 데이터를 보여주기위한 Helper Class
 */
public class BookViewAdapter extends RecyclerView.Adapter {

    // RecyclerView에 표현할 데이터
    private List<NaverBookDTO> bookList;

    /**
     * RecyclerView에서 사용할 데이터(bookList)를 왜부에서 Adapter에 보내기위해
     * 생성자, sett()를 사용한다
     *
     * RecyclerView 의 Adapter를 객체로 생성할때
     * 화면에 보여줄 데이터를 전달(주입)
     */

    public BookViewAdapter(List<NaverBookDTO> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    /**
     * onCreateViewHolder
     * item을 그리는 item.xml 파일을 읽어서 사용할 준비를 하기
     * item.xml 파일을 view로 생성하고 데이터와 연결할 준비
     */
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // *.xml 파일에 설정된 view 정보를 읽어서 Java Code에서 사용하기 위한 객체로 생성하는것
        // inflate(확장)
        // item.xml 파일을 읽어와서 Holder로 만들기 위한 도구
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate( R.layout.book_item_view, parent, false );

        // 생성된 view 객체를 BookViewHolder 클래스의 생성자에 주입하면서 ViewHolder 객체를 생성하기

        BookItemHolder viewHolder = new BookItemHolder(view);

        // 생성된 viewHolder 객체를 RecyclerView에게 return
        return viewHolder;
    }

    @Override
    /**
     * 생성된 Holder와 실제 데이터(한개의 데이터)를 연결하는 작업
     * 한개의 데이터를 연결하는 작업을 수행하지만
     * RecyclerView에 의해 데이터 개수만큼 반복된다
     *
     *
     */
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       // 매개변수로 받은 holder 를 우리가 선언한 BookItemHolder로 형변환후 코드진행
       BookItemHolder bookHolder = (BookItemHolder) holder;

       // 전체데이터에서 한개의 데이터를 추출한다
       NaverBookDTO bookDTO = bookList.get(position);

       String item_title = bookDTO.getTitle();
       Spanned sp_title = Html.fromHtml(item_title,Html.FROM_HTML_MODE_LEGACY);
       bookHolder.item_title.setText(sp_title);

       String item_desc = bookDTO.getDescription();
       Spanned sp_desc = Html.fromHtml(item_desc, Html.FROM_HTML_MODE_LEGACY);
       bookHolder.item_desc.setText(sp_desc);

        /**
         * naver API를 통해 전달받은 image 정보 표시
         * naver API에서는 이미지주소(link)를 문자열로 보낸다
         *
         * HTML에서는 직접 호출이 가능했으나
         *
         * 안드로이드에서는 Picasso, Glide같은 서드파티 라이브러리를 이용하여
         * 화면에 이미지를 구현한다
         *
         *          picasso         Glide
         *   속도    다소느림        다소빠름
         *  메모리   적게 소모       많이 소모
         *   기능     단순           다양함
         */

        if (!bookDTO.getImage().isEmpty()){
            Picasso.get().load(bookDTO.getImage()).into(bookHolder.item_image);
        }


    }

    @Override
    /**
     * onCreateViewHolder에서 생성된 holder를 사용하여
     * onBindViewHolder가 데이터 한개 그리기를 수행하게 되면
     * RecyclerView에게 데이터가 몇개인지 알려주고 데이터 개수만큼 반복시키는 method
     */
    public int getItemCount() {

        return bookList == null ? 0 : bookList.size();
    }

    /**
     * onCreateViewHolder() method가 사용하는 클래스
     * 실제 item.xml에 작성된 각각의 view componet를
     * 실제적으로 사용할 수 있도록 view객체를 생성하기위한 보조 class
     *
     * 초기에는 선택사항이었으나 현재는 반드시 있어야하는 필수 클래스이다
     *
     * item.xml에 설정된 view Component 개별 요소를 추출하여 각각 객체로 만드는 일
     */
    public static class BookItemHolder extends RecyclerView.ViewHolder{

        public TextView item_title;
        public ImageView item_image;
        public TextView item_desc;

        public BookItemHolder(@NonNull View itemView) {
            super(itemView);
            item_title = itemView.findViewById(R.id.book_item_title);
            item_desc = itemView.findViewById(R.id.book_item_desc);
            item_image = itemView.findViewById(R.id.book_item_image);
        }
    }
}
