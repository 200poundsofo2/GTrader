package com.example.gtraderprototype.views;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gtraderprototype.R;
import android.view.KeyEvent;
import android.widget.Toast;
import com.example.gtraderprototype.entity.PirateGame.Game;

public class PirateGameActivity extends AppCompatActivity  {
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate_game);
        setContentView(new Game(this));
    }
    public boolean onKeyDown(int keyCode,KeyEvent event) { //返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            long t=System.currentTimeMillis();//获取系统时间
            if(t-time<=500){
                exit(); //如果500毫秒内按下两次返回键则退出游戏
            }else{
                time=t;
                Toast.makeText(getApplicationContext(),"再按一次退出游戏",Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return false;
    }
    public void exit(){
        PirateGameActivity.this.finish();
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
                System.exit(0);
            }
        }).start();
    }


}
