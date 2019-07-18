package com.example.gtraderprototype.entity.PirateGame;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import com.example.gtraderprototype.R;
import com.example.gtraderprototype.views.GameOverActivity;
import com.example.gtraderprototype.views.PirateGameActivity;
import com.example.gtraderprototype.views.WinPirateActivity;


import java.util.List;
import java.util.LinkedList;

public class Game extends View{
    private Paint paint = new Paint();
    private float x;
    private float y;
    private float planeX;
    private float planeY;
    public static GameVariables GV;

    public Game(Context context){
        super(context);
        setOnTouchListener(new OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent e){ //drag to move
                if(e.getAction()==MotionEvent.ACTION_DOWN){
                    x=e.getX();
                    y=e.getY();
                    planeX=GameVariables.playerPlane.position.left;
                    planeY=GameVariables.playerPlane.position.top;
                }
                float xx=planeX+e.getX()-x;
                float yy=planeY+e.getY()-y;
                xx=xx<GameVariables.width-GameVariables.playerPlane.width/2?xx:GameVariables.width-GameVariables.playerPlane.width/2;
                xx=xx>-GameVariables.playerPlane.width/2?xx:-GameVariables.playerPlane.width/2;
                yy=yy<GameVariables.height-GameVariables.playerPlane.height/2?yy:GameVariables.height-GameVariables.playerPlane.height/2;
                yy=yy>-GameVariables.playerPlane.height/2?yy:-GameVariables.playerPlane.height/2;
                GameVariables.playerPlane.setX(xx);
                GameVariables.playerPlane.setY(yy);
                return true;
            }
        });

        setBackgroundResource(R.drawable.bg);
        GameVariables.player = BitmapFactory.decodeResource(getResources(),R.drawable.plane);//load images
        GameVariables.pirate =BitmapFactory.decodeResource(getResources(),R.drawable.pirate);
        GameVariables.bullet =BitmapFactory.decodeResource(getResources(),R.drawable.bullet);

        new Thread(new refresh()).start();//repaint
        new Thread(new loadEnemy()).start();
    }
    @Override
    protected void onDraw(Canvas canvas) { //draw all the objects on the screen
        super.onDraw(canvas);
        if(!GameVariables.alive){
            Activity activity=(Activity) getContext();
            activity.startActivity(new Intent(activity, WinPirateActivity.class));
        }
        if( GameVariables.kill >= GameVariables.goal) {
            Activity activity=(Activity) getContext();
            activity.finish();
        }
        for(int i=0;i<GameVariables.objects.size();i++){
            Plane h=GameVariables.objects.get(i);
            canvas.drawBitmap(h.image,null,h.position,paint);
        }
        canvas.drawText("kill："+GameVariables.kill,0,GameVariables.height-50,paint);

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//get the height and width of the screen
        super.onSizeChanged(w, h, oldw, oldh);
        GameVariables.width=w;
        GameVariables.height=h;

        GameVariables.ratio= (float) (Math.sqrt(GameVariables.width * GameVariables.height)/ Math.sqrt(1920 * 1080));
        paint.setTextSize(50*GameVariables.ratio);//set front
        paint.setColor(Color.WHITE);//set the color to be white
        //start game
        GameVariables.playerPlane=new playerPlane();//initialize my plane
    }

    private class refresh implements Runnable{
        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(10);
                } catch (InterruptedException ex){

                }
                postInvalidate();
            }
        }
    }
    private class loadEnemy implements Runnable{
        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(300);
                } catch (InterruptedException ex){
                }
                new piratePlane();
            }
        }
    }
}



