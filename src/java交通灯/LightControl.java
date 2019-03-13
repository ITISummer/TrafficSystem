package java��ͨ��;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**�Ե���������ܻ����Ƶ���*/
@SuppressWarnings("serial")
public class LightControl extends Canvas implements Runnable {
//**********************************��(����)������***********************************************
	 /**���ƵƵ��߳����е�boolean����*/
	 public static boolean RUN=true;
	 /**�����ƵĿ��*/
	 private static final int light_width=50;
	 /**�����Ƶĸ߶�*/
	 private static final int light_height=50;
	 /**�Լ�ʱ�Ƶ��ػ�����ʱ��*/
	 private static final int repaint_time=500;
//**********************************��(����)������***********************************************
	
//**********************************��Ա������***************************************************
	/**ÿһ������ʱ��*/
	 private int time;
	 /**�Ե����ѡ��*/
	 private String Lampgroup_choice;
	 /**�Ƶ�ѡ��,�����м�ĵƻ�����ߵĵ�*/
	 private char light_choice;
	 /**��-�󣬵�-�У���-��*/
	 private Light  light_left, light_middle,light_right;
 //**********************************��Ա������***************************************************
	 
	 /**@1������һ��ƣ������С���˳�򣬶�ÿ��ƽ�����Ӧ�Ŀ���
	  * @2�������Ƶ�ʱ��
	  * @3���Ե����ѡ���ϱ���������*/
public LightControl(Light light_left,Light light_middle,Light light_right,String Lampgroup_choice,char light_choice,int time) {
	this.light_left=light_left;
	this.light_middle=light_middle;
	this.light_right=light_right;
	this.light_choice=light_choice;
	this.Lampgroup_choice=Lampgroup_choice;
	this.time=time;	
}//Light_control()����
	/**@���ϱ���*/
public void paint_NS_Light_control(Graphics2D G) {
	G.drawImage(getToolkit().getImage(light_left.getphoto()), light_left.x,light_left.y,light_width,light_height,this);
    G.drawImage(getToolkit().getImage(light_middle.getphoto()), light_middle.x,light_middle.y,light_width,light_height,this);
    G.drawImage(getToolkit().getImage(light_right.getphoto()), light_right.x,light_right.y,light_width,light_height,this);
    G.setColor(Color.BLACK);
}//���ϱ��Ƶķ�������
	/**@��������*/
public void paint_WE_Light_control(Graphics2D G) {
	G.drawImage(getToolkit().getImage(light_left.getphoto()), light_left.x,light_left.y,light_width,light_height,this);
    G.drawImage(getToolkit().getImage(light_middle.getphoto()), light_middle.x,light_middle.y,light_width,light_height,this);
    G.drawImage(getToolkit().getImage(light_right.getphoto()), light_right.x,light_right.y,light_width,light_height,this);
    G.setColor(Color.BLACK);
}//�������Ƶķ�������
	/**��дpaint()����*/
public void paint(Graphics g) {
	Graphics2D G=(Graphics2D)g;
	switch(this.light_choice) {
case 'N':
		paint_NS_Light_control(G);//���û��ϱ��Ƶĵķ���
		//������ʱ��
	    G.fillRect(light_left.x+52, light_left.y, light_width, light_height);
	    G.setColor(Color.RED);
	    G.setFont(new Font("����",Font.BOLD,40));
	    G.drawString(time+" ", light_left.x+52,light_left.y+40 );
	break;
case 'S':
		paint_NS_Light_control(G);//���û��ϱ��Ƶĵķ���
		//���ϼ�ʱ��
	    G.fillRect(light_left.x-52, light_left.y, light_width, light_height);
	    G.setColor(Color.RED);
	    G.setFont(new Font("����",Font.BOLD,40));
	    G.drawString(time+" ", light_left.x-52,light_left.y+40 );
	break;
case 'E':
		paint_WE_Light_control(G);//���û������Ƶĵķ���	 
		//������ʱ��
		G.fillRect(light_left.x, light_left.y+52, light_width, light_height);
	    G.setColor(Color.RED);
	    G.setFont(new Font("����",Font.BOLD,40));
	    G.drawString(time+" ", light_left.x,light_left.y+90 );
	break;
case 'W':
		paint_WE_Light_control(G);//���û������Ƶĵķ���
		//������ʱ��
	    G.fillRect(light_left.x, light_left.y-52, light_width, light_height);
	    G.setColor(Color.RED);
	    G.setFont(new Font("����",Font.BOLD,40));
	    G.drawString(time+" ", light_left.x,light_left.y-10 );
	break;
	
	}
}//paint()����
	/**@��ÿ��ƽ�����Ӧ���Ƶľ������
	 * @����ʹ��Ƕ��switch*/
@Override
public void run() {
	String light_choice1="light_middle";//�ԵƵ�ѡ������������
	String light_choice2="stop";
	while(RUN) {
		switch(Lampgroup_choice) {//��ʵ����ʱ�����Ĳ�������
		case "NS":
			switch(light_choice1) {//���ñ����ϲ��м����ʾ�̵�
			case "light_middle":
			{
				try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
				repaint();
				this.time--;
				if(this.time>3) {
				light_left.status=Light.light_red;//��
				light_middle.status=Light.light_green;//��
				
				}
				if(this.time<=3&&this.time>0) {
					light_left.status=Light.light_yellow;//��
					light_middle.status=Light.light_yellow;//��
				}
				if(time==0) {
					this.time=10;
					light_left.status=Light.light_green;//��
					light_middle.status=Light.light_red;//��
					light_choice1="light_left";
				}
			}
				break;
			case "light_left"://Ȼ�󱱺��ϲ���ߵ���ʾ�̵�
			{
				try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
				repaint();
				this.time--;
				if(time>3) {
				light_left.status=Light.light_green;//��
				light_middle.status=Light.light_red;//��
				
				}
				if(time>0&&time<=3) {
					light_left.status=Light.light_yellow;//��
					light_middle.status=Light.light_red;//����Ϊ��////////////////////////�Ѹ�
				}
				if(time==0) {
					this.time=20;
					light_left.status=Light.light_red;//��
					light_middle.status=Light.light_red;//��
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
					light_left.status=Light.light_red;//����Ϊ��////////////////////////�Ѹ�
					light_middle.status=Light.light_yellow;//��
				}
				if(this.time==0) {
					time=10;
					light_left.status=Light.light_red;//��
					light_middle.status=Light.light_green;//��
					light_choice1="light_middle";
					
				}
			}
			break;
			}//light switch������
			break;
		case "WE":
				switch(light_choice2) {
				case "light_middle":
				{
					try {Thread.sleep(repaint_time);}catch(InterruptedException e) {e.printStackTrace();}
					repaint();
					this.time--;
					if(this.time>3) {
					light_left.status=Light.light_red;//��
					light_middle.status=Light.light_green;//��
					
					}
					if(this.time<=3&&this.time>0) {
						light_left.status=Light.light_yellow;//��
						light_middle.status=Light.light_yellow;//��
					}
					if(this.time==0) {
						this.time=10;
						light_left.status=Light.light_green;//��
						light_middle.status=Light.light_red;//��
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
					light_left.status=Light.light_green;//��
					light_middle.status=Light.light_red;//��
					
					}
					if(time>0&&time<=3) {
						light_left.status=Light.light_yellow;//��
						light_middle.status=Light.light_red;//����Ϊ��////////////////////////�Ѹ�
					}
					if(time==0) {
						this.time=20;
						light_left.status=Light.light_red;//��
						light_middle.status=Light.light_red;//��
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
						light_left.status=Light.light_red;//��
						light_middle.status=Light.light_red;//��
						
					}
					if(time>0&&time<=3) {
						light_left.status=Light.light_red;//����Ϊ��////////////////////�Ѹ�
						light_middle.status=Light.light_yellow;//��
					}
					if(this.time==0) {
						time=10;
						light_left.status=Light.light_red;//��
						light_middle.status=Light.light_green;//��
						light_choice2="light_middle";
						
					}
				}
				break;
				}//light switch������
				break;
		}//Light_control switch������
		}//whileѭ������
		
	}//run()��������	
}


