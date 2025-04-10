package kr.ac.mjc.ict2007261051;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.mjc.ict2007261051.dto.BookLocationDto;
import kr.ac.mjc.ict2007261051.dto.ResponseDto2;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookActivity extends AppCompatActivity {

    ImageView thumbIv;
    TextView titleTv;
    RecyclerView locationRv;
    List<BookLocationDto> mBookLocationList=new ArrayList<>();
    BookLocationAdapter bookLocationAdapter=new BookLocationAdapter(mBookLocationList);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        thumbIv=findViewById(R.id.thumb_iv);
        titleTv=findViewById(R.id.title_tv);
        locationRv=findViewById(R.id.location_rv);
        locationRv.setAdapter(bookLocationAdapter);
        locationRv.setLayoutManager(new LinearLayoutManager(this));

        Book book= (Book) getIntent().getSerializableExtra("book");
        titleTv.setText(book.getTitle());
        Glide.with(this).load(book.getThumbUrl()).into(thumbIv);

        loadBookLocation(book);
    }

    public void loadBookLocation(Book book){
        String url="https://lib.mjc.ac.kr/pyxis-api/1/biblios/"+book.getId()+"/items";
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String json=response.body().string();
                Log.d("json",json);
                // JSON String -> ResponstDto2 클래스로 변환
                ResponseDto2 responseDto=new Gson().fromJson(json,ResponseDto2.class);
                List<BookLocationDto> bookLocationList=responseDto.getData().get("1");
                mBookLocationList.clear();
                mBookLocationList.addAll(bookLocationList);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bookLocationAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }
}
