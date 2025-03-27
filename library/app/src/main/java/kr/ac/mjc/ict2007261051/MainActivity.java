package kr.ac.mjc.ict2007261051;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView booklistRv;
    EditText keywordEt;
    Button searchBtn;

    ArrayList<Book> mBookList=new ArrayList<>();

    BookAdapter bookAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        keywordEt=findViewById(R.id.keyword_et);
        searchBtn=findViewById(R.id.search_btn);
        booklistRv=findViewById(R.id.booklist_rv);
        
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setTitle("안드로이드 정석");
                book.setAuthor("진용화");
                book.setPub("명지출판사");
                book.setThumbUrl("https://image.aladin.co.kr/product/22629/25/cover/k342636936_1.jpg");
//                book.setThumbUrl("http://image.aladin.co.kr/product/2060/87/cover/8993827516_1.jpg");

                mBookList.add(book);
                bookAdapter.notifyDataSetChanged();
            }
        });

        /*
        for(int i=0;i<100000;i++){
            Book book=new Book();
            book.setTitle("안드로이드 정석"+i);
            book.setAuthor("진용화"+i);
            book.setPub("명지출판사"+i);
            mBookList.add(book);
        }
        */

        bookAdapter=new BookAdapter(mBookList);
        booklistRv.setAdapter(bookAdapter);
        booklistRv.setLayoutManager(new LinearLayoutManager(this));

    }
}