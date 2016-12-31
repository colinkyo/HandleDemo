package com.example.a7yan.handledemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LooperThread extends AppCompatActivity {
    private Button btn;
    private TextView tv;
    private Handler handler=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_thread);
        tv = (TextView) findViewById(R.id.tv);
        //启动轮询
        new LpThread().start();
    }
    //发送消息
    public void sendMsg(View view){
        Message message=new Message();
        message.obj="我在Handle发送的...";
        handler.sendMessage(message);
    }
    //自定义一个Looper线程, 不停的处理主线程发来的消息
    class LpThread extends Thread{
        @Override
        public void run() {
            //准备成为Looper线程
            Looper.prepare();

            handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    String values= (String) msg.obj;
                    Log.i("7Yan","从主线程接收的信息"+values);
                }
            };
            //开心轮询
            Looper.loop();
        }
    }
}
