package com.doit.theread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 사용할 변수 선언
    Button btn_start, btn_stop;
    Thread thread;
    boolean isThread = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //버튼 가져온뒤 클릭이벤트 메서드 호출
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) { // 버튼 클릭시 isThread를 true 바꿔주고 새로운 thread객체 생성
                isThread = true;
                thread = new Thread() {

                    public void run() { // 스레드 시작 메서드
                        while (isThread) {  // 스레드가 실행하는동안
                            try {
                                sleep(10000); //3000은 3초 1000 == 1초 동안 잠자기
                            } catch (InterruptedException e) { // 예외처리
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(100); //handleMessage로 메세지 전달
                        }
                    }
                };
                thread.start();  //스레드 시작메서드
            }
        });

        // 버튼 가져오기
        // 버튼클릭시 스레드 종료
        btn_stop = findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThread = false;
            }
        });

    }

    // 핸들러 생성
    // 스레드간의 통신
    private Handler handler = new Handler() { // 핸들러 객체 생성
        @Override
        public void handleMessage(@NonNull Message msg) {    // 핸들메세지 오버라이드후 토스트 출력
            Toast.makeText(MainActivity.this, "출력출력출력출력출력출력출력", Toast.LENGTH_SHORT).show();

        }
    };
}