package kr.ac.mjc.ict2007261051;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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

    }
}
