package ren.jiemei.timedtask;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button task;
    private Timer timer;
    private TimerTask timetask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        task = (Button) findViewById(R.id.task);

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ThreadTask();
                handler.postDelayed(myrunable,10000);
//                TimeTask();
            }
        });
    }

    //用线程Thread休眠的方式实行延时
    private void ThreadTask(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("tag", "onClick: "+i );
        }
        tv.setText("wo shi yi ge hao ren .....");//要十秒之后显示这个
    }

    //用Handler实现定时延时
    Handler handler = new Handler();

    Runnable myrunable = new Runnable() {
        @Override
        public void run() {
            tv.setText("wo shi yi ge hao ren .....");//要十秒之后显示这个

        }
    };

    //使用Timer.schedule方式延时，可能会不准确
    private void TimeTask(){
        timer = new Timer();
        //要十秒之后显示这个
        timetask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("wo shi yi ge hao ren .....");//要十秒之后显示这个

                    }
                });
            }
        };
        timer.schedule(timetask,10000);
    }
}
