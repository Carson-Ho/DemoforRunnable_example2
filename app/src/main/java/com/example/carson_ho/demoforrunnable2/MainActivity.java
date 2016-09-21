package com.example.carson_ho.demoforrunnable2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //主布局中定义了一个按钮用以启动线程
    Button button;

    //步骤1:创建线程类，实现Runnable接口
    //由于需要实现两个不同的操作:卖票速度1s/张和3s/张
    //所以需要创建两个线程类并实现Runnable接口

    //第一个线程类:实现卖票速度1s/张操作
    private class MyThread1 implements Runnable{

        private int ticket = 100;//一个窗口有100张票

        //在run方法里复写需要进行的操作:卖票速度1s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);

                try {
                    Thread.sleep(1000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //第二个线程类:实现卖票速度1s/张操作
    private class MyThread2 implements Runnable{

        private int ticket = 100;//一个窗口有100张票

        //在run方法里复写需要进行的操作:卖票速度3s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);

                try {
                    Thread.sleep(3000);//卖票速度是3s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button按下时会开启一个新线程执行卖票
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //步骤2:创建线程类的实例
                //分别实例化两个线程子类
                MyThread1 mt1 = new MyThread1();
                MyThread2 mt2 = new MyThread2();

                //创建二个线程，模拟二个窗口卖票
                Thread mt11 = new Thread(mt1, "窗口1");//卖票速度1s/张
                Thread mt22 = new Thread(mt2, "窗口2");//卖票速度3s/张

                //步骤3：调用start()方法开启线程
                //启动二个线程，也即是窗口，开始卖票
                mt11.start();
                mt22.start();

            }
        });





    }
}
