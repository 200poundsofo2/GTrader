package com.example.gtraderprototype.views;

import com.example.gtraderprototype.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import java.util.Vector;

class my {
    public static int js=0;//击杀数
    public static int w,h;//屏幕的宽高
    public static float bili;//比例，用于适应不同屏幕
    public static Vector<plane> list=new Vector<plane>();//所有飞行物的集合,添加进这个集合才能被画出来
    public static Vector<plane> drlist=new Vector<plane>();//敌人飞机的集合，添加进这个集合才能被子弹打中
    //我集合学的挺烂的哈 为什么用Vector呢？因为他线程是安全的。。。
    public static Bitmap player, pirate, background, bullet;//图片：我的灰机 敌人灰机 背景 我的子弹
    public static myhj my;//我的灰机
    public static bj b;//背景

}

public class game extends View{//画
    private Paint p=new Paint();//画笔
    private float x,y;//按下屏幕时的坐标
    private float myx,myy;//按下屏幕时玩家飞机的坐标

    public game(Context context) {
        super(context);
        //添加事件控制玩家飞机
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                if(e.getAction()==MotionEvent.ACTION_DOWN){
                    x=e.getX();
                    y=e.getY();
                    myx=my.my.r.left;
                    myy=my.my.r.top;
                }
                float xx=myx+e.getX()-x;
                float yy=myy+e.getY()-y;
                //我的飞机不能飞出屏幕
                xx=xx<my.w-my.my.w/2?xx:my.w-my.my.w/2;
                xx=xx>-my.my.w/2?xx:-my.my.w/2;
                yy=yy<my.h-my.my.h/2?yy:my.h-my.my.h/2;
                yy=yy>-my.my.h/2?yy:-my.my.h/2;
                my.my.setX(xx);
                my.my.setY(yy);
                return true;
            }
        });

        //setBackgroundColor(Color.BLACK);//设背景颜色为黑色

        my.player = BitmapFactory.decodeResource(getResources(),R.drawable.plane);//加载图片
        my.pirate =BitmapFactory.decodeResource(getResources(),R.drawable.pirate);
        my.bullet =BitmapFactory.decodeResource(getResources(),R.drawable.bullet);
        my.background =BitmapFactory.decodeResource(getResources(), R.drawable.bg);

        new Thread(new re()).start();//新建一个线程 让画布自动重绘
        new Thread(new loaddr()).start();//新建一个 加载敌人的线程
    }
    @Override
    protected void onDraw(Canvas g) {//这个相当于swing的paint方法吧 用于绘制屏幕上的所有物体
        super.onDraw(g);
        //g.drawBitmap(my.b.img,null,my.b.r,p);//画背景 我没有把背景添加到list里

        for(int i=0;i<my.list.size();i++){//我们把所有的飞行物都添加到了my.list这个集合里
            plane h=my.list.get(i);           //然后在这里用一个for循环画出来
            g.drawBitmap(h.img,null,h.r,p);
        }
        g.drawText("kill："+my.js,0,my.h-50,p);

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//这个方法用来获取屏幕宽高的
        super.onSizeChanged(w, h, oldw, oldh);
        my.w=w;//获取宽
        my.h=h;//高

        //获取手机（应该不是手机的吧 是这控件的吧）分辨率和1920*1080的比例
        //然后飞机的宽高乘上这个分辨率就能在不同大小的屏幕正常显示了
        //为什么用1920*1080呢 因为我手机就是这个分辨率。。。
        my.bili= (float) (Math.sqrt(my.w * my.h)/ Math.sqrt(1920 * 1080));
        p.setTextSize(50*my.bili);//设置字体大小，“击杀”的大小
        p.setColor(Color.WHITE);//设为白色
        //好了 到这里游戏开始了
        my.b=new bj();//初始化背景
        my.my=new myhj();//初始化 我的灰机
    }
    private class re implements Runnable {
        @Override
        public void run() {
            //每10ms刷新一次界面
            while(true){
                try { Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
                postInvalidate();//刷新画布
            }
        }
    }
    private class loaddr implements Runnable{
        @Override
        public void run() {
            while(true){
                //每300ms刷一个敌人
                try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                try {
                    new drhj();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class plane {//游戏内所有物体的父类
    public RectF r=new RectF();//这个是用来确定位置的
    public int hp;//生命
    public float w,h;//宽高
    public Bitmap img;//图片


    //这里的画图方法和swing的不太一样
    //设两个方法来设置x,y的坐标
    public void setX(float x){
        r.left=x;
        r.right=x+w;
    }
    public void setY(float y){
        r.top=y;
        r.bottom=y+h;
    }

    public boolean pengzhuang(plane obj, float px) {//判断碰撞 判断时忽略px个像素
        px*=my.bili;//凡是涉及到像素的 都乘一下分辨率比例my.bili
        if (r.left+px - obj.r.left <= obj.w && obj.r.left - this.r.left+px <= this.w-px-px)
            if (r.top+px - obj.r.top <= obj.h && obj.r.top - r.top+px <= h-px-px) {
                return true;
            }
        return false;

    }
}
class bj extends plane implements  Runnable{//背景
    public bj(){
        w=my.w;
        h=my.h*2;//背景的高是 屏幕高的两倍
        img=my.background;
        setX(0);
        setY(-my.h);
        new Thread(this).start();
    }
    @Override
    public void run() {
        //这里控制背景一直向下移
        while(true){
            try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
            if(r.top+2<=0){
                setY(r.top+2);
            }else{
                setY(-my.h);
            }
        }
    }
}

class drhj extends plane implements Runnable{//敌人灰机
    private long sd0=(long) (Math.random()*10)+10;//生成一个[10,20)的随机数 用来控制敌人速度 敌人速度是不一样的

    public drhj(){
//        w=my.w/5.4f;
//        h=my.h/9.6f;
        w=h=200*my.bili;
        //敌人刷出来的位置
        setX((float)( Math.random()*(my.w-w)));//x是随机的
        setY(-h);//在屏幕外 刚好看不到的位置
        img=my.pirate;
        hp=12;//生命=12
        my.list.add(this);//添加到集合里 这样才能被画出来
        my.drlist.add(this);//添加到敌人的集合 添加进这个集合子弹才打得到
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(hp>0){//如果生命>0 没有死 就继续向前飞，死了还飞什么？
            try {Thread.sleep(sd0);} catch (InterruptedException e) {e.printStackTrace();}
            setY(r.top+2*my.bili);
            if(r.top>=my.h)break;//敌人飞出屏幕 跳出循环
        }
        //从集合删除
        my.list.remove(this);
        my.drlist.remove(this);
    }
}

class myhj extends plane implements Runnable{//我的灰机

    public myhj(){
        w=h=200*my.bili;//凡是涉及到像素的 都乘一下分辨率比例my.bili
        //设置初始位置
        setX(my.w/2-w/2);
        setY(my.h*0.7f-h/2);
        img=my.player;//初始化图片
        my.list.add(this);//添加到集合里 这样才能被画出来
        new Thread(this).start();//发射子弹的线程
    }

    @Override
    public void run() {
        while(true){
            //90毫秒发射一发子弹
            try {Thread.sleep(90);} catch (InterruptedException e) {e.printStackTrace();}
            new myzd(this);
        }
    }
}
class myzd extends plane implements Runnable{//我的子弹
    private int dps;
    private float sd0;

    public myzd(plane hj){
        w=h=90*my.bili;//凡是涉及到像素的 都乘一下分辨率比例my.bili
        img=my.bullet;//图片
        sd0=6*my.bili;//速度=6
        dps=6;//伤害=6
        //设在玩家中心的偏上一点
        setX(hj.r.left+hj.w/2-w/2);
        setY(hj.r.top-h/2);
        my.list.add(this);//添加到集合里 这样才能被画出来
        new Thread(this).start();//新建一个子弹向上移动的线程
    }

    @Override
    public void run() {
        boolean flag = false;//一个标记 用来跳出嵌套循环
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setY(r.top - sd0);//向上移sd0个像素，sd0=6

            try {//try一下 怕出错
                //这里判断有没有和集合里的敌人发生碰撞
                for (int i = 0; i < my.drlist.size(); i++) {
                    plane h = my.drlist.get(i);
                    if (pengzhuang(h, 30)) {//判断碰撞
                        h.hp -= dps;//敌人生命-子弹伤害
                        flag = true;//一个标记 用来跳出嵌套循环
                        my.js++;//击杀+1
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            if (flag || r.top + h <= 0) break;//如果子弹击中过敌人 或者超出屏幕范围 跳出循环
        }
        my.list.remove(this);//从集合删除
    }
}
