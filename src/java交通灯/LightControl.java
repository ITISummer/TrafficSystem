package java交通灯;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**对灯组进行智能化控制的类*/
@SuppressWarnings("serial")
public class LightControl extends Canvas implements Runnable {
//**********************************类(共享)变量区***********************************************
	 /**控制灯的线程运行的boolean变量*/
	 public static boolean RUN=true;
	 /**单个灯的宽度*/
	 private static final int light_width=50;
	 /**单个灯的高度*/
	 private static final int light_height=50;
	 /**对计时牌的重画限制时间*/
	 private static final int repaint_time=500;
//**********************************类(共享)变量区***********************************************
	
//**********************************成员变量区***************************************************
	/**每一组灯组的时间*/
	 private int time;
	 /**对灯组的选择*/
	 private String Lampgroup_choice;
	 /**灯的选择,比如中间的灯或者左边的灯*/
	 private char light_choice;
	 /**灯-左，灯-中，灯-右*/
	 private Light  light_left, light_middle,light_right;
 //**********************************成员变量区***************************************************
	 
	 /**@1、传入一组灯，按左、中、右顺序，对每组灯进行相应的控制
	  * @2、给定灯的时间
	  * @3、对灯组的选择（南北、东西）*/
public LightControl(Light light_left,Light light_middle,Light light_right,String Lampgroup_choice,char light_choice,int time) {
	this.light_left=light_left;
	this.light_middle=light_middle;
	this.light_right=light_right;
	this.light_choice=light_choice;
	this.Lampgroup_choice=Lampgroup_choice;
	this.time=time;	
}//Light_control()结束
	/**@画南北灯*/
public void paint_NS_Light_control(Graphics2D G) {
	G.drawImage(getToolkit().getImage(light_left.getphoto()), light_left.x,light_left.y,light_width,light_height,this);
    G.drawImage(getToolkit().getImage(light_middle.getphoto()), light_middle.x,light_middle.y,light_width,light_height,this);
    G.drawImage(getToolkit().getImage(light_right.getphoto()), light_right.x,light_right.y,light_width,light_height,this);
    G.setColor(Color.BLACK);
}//画南北灯的方法结束
	/**@画东西灯*/
public void paint_WE_Light_control(Graphics2D G) {
	G.drawImage(getToolkit().getImage(light_left.getphoto()), light_left.x,light_left.y,light_width,light_height,this);
    G.drawImage(getToolkit().getImage(light_middle.getphoto()), light_middle.x,light_middle.y,light_width,light_height,this);
    G.drawImage(getToolkit().getImage(light_right.getphoto()), light_right.x,light_right.y,light_width,light_height,this);
    G.setColor(Color.BLACK);
}//画东西灯的方法结束
	/**重写paint()方法*/
public void paint(Graphics g) {
	Graphics2D G=(Graphics2D)g;
	switch(this.light_choice) {
case 'N':
		paint_NS_Light_control(G);//调用画南北灯的的方法
		//画北计时板
	    G.fillRect(light_left.x+52, light_left.y, light_width, light_height);
	    G.setColor(Color.RED);
	    G.setFont(new Font("宋体",Font.BOLD,40));
	    G.drawString(time+" ", light_left.x+52,light_left.y+40 );
	break;
case 'S':
		paint_NS_Light_control(G);//调用画南北灯的的方法
		//画南计时板
	    G.fillRect(light_left.x-52, light_left.y, light_width, light_height);
	    G.setColor(Color.RED);
	    G.setFont(new Font("宋体",Font.BOLD,40));
	    G.drawString(time+" ", light_left.x-52,light_left.y+40 );
	break;
case 'E':
		paint_WE_Light_control(G);//调用画东西灯的的方法	 
		//画东计时板
		G.fillRect(light_left.x, light_left.y+52, light_width, light_height);
	    G.setColor(Color.RED);
	    G.setFont(new Font("宋体",Font.BOLD,40));
	    G.drawString(time+" ", light_left.x,light_left.y+90 );
	break;
case 'W':
		paint_WE_Light_control(G);//调用画东西灯的的方法
		//画西计时板
	    G.fillRect(light_left.x, light_left.y-52, light_width, light_height);
	    G.setColor(Color.RED);
	    G.setFont(new Font("宋体",Font.BOLD,40));
	    G.drawString(time+" ", light_left.x,light_left.y-10 );
	break;
	
	}
}//paint()结束
	/**@对每组灯进行相应控制的具体操作
	 * @这里使用嵌套switch*/
@Override
public void run() {
	String light_choice1="light_middle";//对灯的选择，左中右三种
	String light_choice2="stop";
	while(RUN) {
		switch(Lampgroup_choice) {//由实例化时给定的参数决定
		case "NS":
			switch(light_choice1) {//先让北和南部中间灯显示绿灯
			case "light_middle":
			{
				try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
				repaint();
				this.time--;
				if(this.time>3) {
				light_left.status=Light.light_red;//红
				light_middle.status=Light.light_green;//绿
				
				}
				if(this.time<=3&&this.time>0) {
					light_left.status=Light.light_yellow;//黄
					light_middle.status=Light.light_yellow;//黄
				}
				if(time==0) {
					this.time=10;
					light_left.status=Light.light_green;//绿
					light_middle.status=Light.light_red;//红
					light_choice1="light_left";
				}
			}
				break;
			case "light_left"://然后北和南部左边灯显示绿灯
			{
				try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
				repaint();
				this.time--;
				if(time>3) {
				light_left.status=Light.light_green;//绿
				light_middle.status=Light.light_red;//红
				
				}
				if(time>0&&time<=3) {
					light_left.status=Light.light_yellow;//黄
					light_middle.status=Light.light_red;//继续为红////////////////////////已改
				}
				if(time==0) {
					this.time=20;
					light_left.status=Light.light_red;//红
					light_middle.status=Light.light_red;//红
					light_choice1="stop";
				}
			}
				break;
			case "stop":
			{   
				try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
				repaint();
				this.time--;
				if(time>0&&time<=3) {
					light_left.status=Light.light_red;//继续为红////////////////////////已改
					light_middle.status=Light.light_yellow;//黄
				}
				if(this.time==0) {
					time=10;
					light_left.status=Light.light_red;//红
					light_middle.status=Light.light_green;//绿
					light_choice1="light_middle";
					
				}
			}
			break;
			}//light switch语句结束
			break;
		case "WE":
				switch(light_choice2) {
				case "light_middle":
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(this.time>3) {
					light_left.status=Light.light_red;//红
					light_middle.status=Light.light_green;//绿
					
					}
					if(this.time<=3&&this.time>0) {
						light_left.status=Light.light_yellow;//黄
						light_middle.status=Light.light_yellow;//黄
					}
					if(this.time==0) {
						this.time=10;
						light_left.status=Light.light_green;//绿
						light_middle.status=Light.light_red;//红
						light_choice2="light_left";
					}
				}
					break;
				case "light_left":
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(time>3) {
					light_left.status=Light.light_green;//绿
					light_middle.status=Light.light_red;//红
					
					}
					if(time>0&&time<=3) {
						light_left.status=Light.light_yellow;//黄
						light_middle.status=Light.light_red;//继续为红////////////////////////已改
					}
					if(time==0) {
						this.time=20;
						light_left.status=Light.light_red;//红
						light_middle.status=Light.light_red;//红
						light_choice2="stop";
					}
				}
					break;
				case "stop":
				{   
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(this.time>3) {
						light_left.status=Light.light_red;//红
						light_middle.status=Light.light_red;//红
						
					}
					if(time>0&&time<=3) {
						light_left.status=Light.light_red;//继续为红////////////////////已改
						light_middle.status=Light.light_yellow;//黄
					}
					if(this.time==0) {
						time=10;
						light_left.status=Light.light_red;//红
						light_middle.status=Light.light_green;//绿
						light_choice2="light_middle";
						
					}
				}
				break;
				}//light switch语句结束
				break;
		}//Light_control switch语句结束
		}//while循环结束
		
	}//run()方法结束	
}


