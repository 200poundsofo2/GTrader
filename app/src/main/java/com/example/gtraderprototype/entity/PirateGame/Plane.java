package com.example.gtraderprototype.entity.PirateGame;

import android.graphics.Bitmap;
import android.graphics.RectF;

import com.example.gtraderprototype.views.GameOverActivity;

public class Plane { //all the items that can fly: player, pirate and bullet
    public RectF position = new RectF();
    public int life;
    public float width,height;
    public Bitmap image;

    public void setX(float x){
        position.left=x;
        position.right=x+width;
    }

    public void setY(float y){
        position.top=y;
        position.bottom=y+height;
    }
    public boolean collide(Plane obj, float px){
        px*=GameVariables.ratio;
        if (position.left+px - obj.position.left <= obj.width && obj.position.left - this.position.left+px <= this.width-px-px)
            if (position.top+px - obj.position.top <= obj.height && obj.position.top - position.top+px <= this.height-px-px) {
                return true;
            }
        return false;
    }
}

class piratePlane extends Plane implements Runnable{
    private long speed =(long) (Math.random()*10)+10;//[10,20) speed of the pirates
    public piratePlane(){
        width=height=200*GameVariables.ratio;
        //set initial position
        setX((float) Math.random()*(GameVariables.width-width));
        setY(-height);
        image=GameVariables.pirate;
        life=12;
        GameVariables.objects.add(this);
        GameVariables.enemy.add(this);
        new Thread(this).start();
    }

    @Override
    public void run(){
        while(life>0){ //alive
            try{
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
            }
            setY(position.top+2*GameVariables.ratio);
            if(position.top >= GameVariables.height){ //out of the screen
                break;
            }
        }
        //died, remove from the game
        GameVariables.objects.remove(this);
        GameVariables.enemy.remove(this);

    }
}
class playerPlane extends Plane implements Runnable{
    public playerPlane(){
        width=height=200*GameVariables.ratio;
        setX(GameVariables.width/2-width/2);
        setY(GameVariables.height*0.7f-height/2);
        image=GameVariables.player;
        GameVariables.objects.add(this);
        new Thread(this).start();//thread that shoot the bullet

    }
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(200);
            } catch (InterruptedException ex){
            }
            new bullet(this);
        }



    }
}
class bullet extends Plane implements Runnable{
    private int power;
    private float speed;

    public bullet(Plane plane){
        width=height=90*GameVariables.ratio;
        image=GameVariables.bullet;
        speed=6*GameVariables.ratio;
        power=6;

        setX(plane.position.left+plane.width/2-width/2);
        setY(plane.position.top-height/2);
        GameVariables.objects.add(this);
        new Thread(this).start(); //move the bullet upward
    }
    @Override
    public void run(){
        boolean flag = false;

        while(true){
            try{
                Thread.sleep(30);
            } catch (InterruptedException ex){
            }
            setY(position.top-speed);

            try{
                for(int i=0; i<GameVariables.enemy.size(); i++){
                    Plane curr = GameVariables.enemy.get(i);
                    if(collide(curr,30)){
                        curr.life-=this.power;
                        GameVariables.kill++;
                        flag=true;
                        break;
                    }
                }
            }catch (Exception ex){
                break;
            }
            if(flag || position.top+height<=0){
                break;
            }
        }
        GameVariables.objects.remove(this);

    }
}
