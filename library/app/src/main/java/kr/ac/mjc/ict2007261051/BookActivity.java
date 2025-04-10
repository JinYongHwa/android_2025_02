package kr.ac.mjc.ict2007261051;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookActivity extends AppCompatActivity {

    ImageView thumbIv;
    TextView titleTv;
    RecyclerView locationRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        thumbIv=findViewById(R.id.thumb_iv);
        titleTv=findViewById(R.id.title_tv);
        locationRv=findViewById(R.id.location_rv);
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
                
            }
        });


    }
}
