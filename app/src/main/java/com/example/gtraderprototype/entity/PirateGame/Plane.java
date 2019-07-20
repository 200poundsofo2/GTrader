package com.example.gtraderprototype.entity.PirateGame;

import android.graphics.Bitmap;
import android.graphics.RectF;

public class Plane { //all the items that can fly: player, pirate and bullet
    public RectF position = new RectF();
    public int life;
    public float width,height;
    public Bitmap image;
    public GameVariables GV;

    public Plane(GameVariables GV){
        this.GV=GV;
    }

    public void setX(float x){
        position.left=x;
        position.right=x+width;
    }

    public void setY(float y){
        position.top=y;
        position.bottom=y+height;
    }
    public boolean collide(Plane obj, float px){
        px*=GV.ratio;
        if (position.left+px - obj.position.left <= obj.width && obj.position.left - this.position.left+px <= this.width-px-px)
            if (position.top+px - obj.position.top <= obj.height && obj.position.top - position.top+px <= this.height-px-px) {
                return true;
            }
        return false;
    }
}

class piratePlane extends Plane implements Runnable{
    private long speed =(long) (Math.random()*10)+10;//[10,20) speed of the pirates
    public piratePlane(GameVariables GV){
        super(GV);
        width=height=200*GV.ratio;
        //set initial position
        setX((float) Math.random()*(GV.width-width));
        setY(-height);
        image=GV.pirate;
        life=12;
        GV.objects.add(this);
        GV.enemy.add(this);
        new Thread(this).start();
    }

    @Override
    public void run(){
        while(life>0){ //alive
            try{
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
            }

            if(!GV.alive || GV.kill >= GV.goal){
                break;
            }
            setY(position.top+2*GV.ratio);
            if(position.top >= GV.height){//out of the screen
                GV.alive=false;
                break;
            }
        }
        //died, remove from the game
        GV.objects.remove(this);
        GV.enemy.remove(this);

    }
}
class playerPlane extends Plane implements Runnable{
    public playerPlane(GameVariables GV){
        super(GV);
        width=height=200*GV.ratio;
        setX(GV.width/2-width/2);
        setY(GV.height*0.7f-height/2);
        image=GV.player;
        GV.objects.add(this);
        new Thread(this).start();//thread that shoot the bullet

    }
    @Override
    public void run(){
        while(true){
            if(!GV.alive || GV.kill >= GV.goal){
                break;
            }
            for(int i=0;i<GV.enemy.size();i++){
                Plane curr=GV.enemy.get(i);
                if(collide(curr,30)){
                    GV.alive=false;
                    break;
                }
            }
            try{
                Thread.sleep(200);
            } catch (InterruptedException ex){
            }
            new bullet(this.GV);
        }

    }
}
class bullet extends Plane implements Runnable{
    private int power;
    private float speed;
    private Plane plane;

    public bullet(GameVariables GV){
        super(GV);
        plane=GV.playerPlane;
        width=height=90*plane.GV.ratio;
        image=plane.GV.bullet;
        speed=6*plane.GV.ratio;
        power=6;

        setX(plane.position.left+plane.width/2-width/2);
        setY(plane.position.top-height/2);
        plane.GV.objects.add(this);
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
            if(!GV.alive || GV.kill >= GV.goal){
                break;
            }

            setY(position.top-speed);

            try{
                for(int i=0; i<plane.GV.enemy.size(); i++){
                    Plane curr = GV.enemy.get(i);
                    if(collide(curr,30)){
                        curr.life-=this.power;
                        GV.kill++;
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
        GV.objects.remove(this);

    }
}
