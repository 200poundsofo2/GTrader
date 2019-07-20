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

public class Game extends View{
    private Paint paint = new Paint();
    private float x;
    private float y;
    private float planeX;
    private float planeY;
    public  GameVariables GV;
    public Thread refresh;
    public Thread loadEnemy;

    public Game(Context context){
        super(context);
        GV=new GameVariables();
        setOnTouchListener(new OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent e){ //drag to move
                if(e.getAction()==MotionEvent.ACTION_DOWN){
                    x=e.getX();
                    y=e.getY();
                    planeX=GV.playerPlane.position.left;
                    planeY=GV.playerPlane.position.top;
                }
                float xx=planeX+e.getX()-x;
                float yy=planeY+e.getY()-y;
                xx=xx<GV.width-GV.playerPlane.width/2?xx:GV.width-GV.playerPlane.width/2;
                xx=xx>-GV.playerPlane.width/2?xx:-GV.playerPlane.width/2;
                yy=yy<GV.height-GV.playerPlane.height/2?yy:GV.height-GV.playerPlane.height/2;
                yy=yy>-GV.playerPlane.height/2?yy:-GV.playerPlane.height/2;
                GV.playerPlane.setX(xx);
                GV.playerPlane.setY(yy);
                return true;
            }
        });

        setBackgroundResource(R.drawable.bg);
        GV.player = BitmapFactory.decodeResource(getResources(),R.drawable.plane);//load images
        GV.pirate =BitmapFactory.decodeResource(getResources(),R.drawable.pirate);
        GV.bullet =BitmapFactory.decodeResource(getResources(),R.drawable.bullet);

        refresh=new Thread(new Refresh());
        refresh.start();//repaint
        loadEnemy=new Thread(new LoadEnemy());
        loadEnemy.start();
    }
    @Override
    protected void onDraw(Canvas canvas) { //draw all the objects on the screen
        super.onDraw(canvas);
        for(int i=0;i<GV.objects.size();i++){
            Plane h=GV.objects.get(i);
            canvas.drawBitmap(h.image,null,h.position,paint);
        }
        canvas.drawText("kill："+GV.kill,0,GV.height-50,paint);

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//get the height and width of the screen
        super.onSizeChanged(w, h, oldw, oldh);
        GV.width=w;
        GV.height=h;

        GV.ratio= (float) (Math.sqrt(GV.width * GV.height)/ Math.sqrt(1920 * 1080));
        paint.setTextSize(50*GV.ratio);//set front
        paint.setColor(Color.WHITE);//set the color to be white
        //start game
        GV.playerPlane=new playerPlane(GV);//initialize my plane
    }

    private class Refresh implements Runnable{
        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(10);
                } catch (InterruptedException ex){

                }
                if(!GV.alive){
                    Activity activity=(Activity) getContext();
                    activity.startActivity(new Intent(activity, GameOverActivity.class));
                    break;
                } else if( GV.kill >= GV.goal) {
                    Activity activity=(Activity) getContext();
                    activity.finish();
                    break;
                } else{
                    postInvalidate();
                }
            }
        }
    }
    private class LoadEnemy implements Runnable{
        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(300);
                } catch (InterruptedException ex){
                }
                if(!GV.alive || GV.kill >= GV.goal){
                    break;
                }
                new piratePlane(GV);
            }
        }
    }
}



