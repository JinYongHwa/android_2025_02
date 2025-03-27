package kr.ac.mjc.layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button joinBtn=findViewById(R.id.join_btn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText idEt=findViewById(R.id.id_et);
                String id=idEt.getText().toString();
                if(id.equals("")){
                    Toast.makeText(MainActivity.this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText password1Et=findViewById(R.id.password1_et);
                EditText password2Et=findViewById(R.id.password2_et);
                
                //사용자가 입력한 패스워드 값
                String password1=password1Et.getText().toString();
                String password2=password2Et.getText().toString();
                
                //비밀번호가 4자리 이하일경우
                if(password1.length()<4){
                    Toast.makeText(MainActivity.this, "비밀번호는 4자리 이상 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                //비밀번호를 서로 다르게입력할경우
                if( !password1.equals(password2) ){
                    Toast.makeText(MainActivity.this, "비밀번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                    password2Et.getText().clear();
                    password2Et.requestFocus();
                    return;
                }
                
                //여기까지왔으면 모든 입력값 검증완료
                Toast.makeText(MainActivity.this, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}