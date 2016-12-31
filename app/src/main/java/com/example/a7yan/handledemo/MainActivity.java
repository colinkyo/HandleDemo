package com.example.a7yan.handledemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv;
    private  int count=0;
    private Handler handler=new Handler(){
        //响应处理方法
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case 1://根据标记响应
                    int value=msg.arg1;
                    //settext只能是string
                    tv.setText("当前的值是="+value);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
    }
    //发送消息
    public void sendMsg(View view)
    {
        new Thread(){
            @Override
            public void run() {
                while (count<1000){
                    count++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //利用Handle对象发送消息
                    //Message message=new Message();
                    Message message=Message.obtain();
                    //标记，用于响应时区分
                    message.what=1;
                    message.arg1=count;
                    message.obj="这是传递字符串用的";
                    handler.sendMessage(message);
                }
                //super.run();
            }
        }.start();
    }
    public void jump(View view){
        Intent intent=new Intent(this,LooperThread.class);
        startActivity(intent);
    }
}
