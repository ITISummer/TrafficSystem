package java交通灯;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @该类主要是对地图进行设计，包括启动灯组*/
	@SuppressWarnings("serial")
public class MyCanvas extends JPanel implements ActionListener,MouseListener,MouseMotionListener{
/**********************************成员变量区***************************************************/
		JButton start=new JButton("statrt");//启动灯组的按钮
		JButton stop=new JButton("stop");//暂停灯组的按钮
		//四组灯组，之所以能够将灯组实例化放在单独灯实例化前面是因为单独灯的修饰符是static的，在类实例化之前就有了分配的内存
	 	LightControl LightControl1=new LightControl(light_N_right,light_N_middle,light_N_left,"NS",'N',10);
	 	LightControl LightControl2=new LightControl(light_S_left,light_S_middle,light_S_right,"NS",'S',10);
	 	LightControl LightControl3=new LightControl(light_E_right,light_E_middle,light_E_left,"WE",'E',20);
	 	LightControl LightControl4=new LightControl(light_W_left,light_W_middle,light_W_right,"WE",'W',20);
	 	//线程对象
			Thread f1=new Thread(LightControl1);
		 	Thread f2=new Thread(LightControl2);
		 	Thread f3=new Thread(LightControl3);
		 	Thread f4=new Thread(LightControl4);
/**********************************成员变量区***************************************************/
		
/**********************************类(共享)变量区***************************************************/
		 //十二个灯
		 //北灯
	 	static Light light_N_left=new Light(0,0,Light.light_green);
	 	static Light light_N_middle=new Light(50,0,Light.light_green);
	 	static Light light_N_right=new Light(100,0,Light.light_red);
	 	//南灯
	 	static Light light_S_left=new Light(50,0,Light.light_red);
	 	static Light light_S_middle=new Light(100,0,Light.light_green);
	 	static Light light_S_right=new Light(150,0,Light.light_green);
	 	//东灯
	 	static Light light_E_left=new Light(0,0,Light.light_green);
	 	static Light light_E_middle=new Light(0,50,Light.light_red);
	 	static Light light_E_right=new Light(0,100,Light.light_red);
	 	//西灯
	 	static Light light_W_left=new Light(0,50,Light.light_red);
	 	static Light light_W_middle=new Light(0,100,Light.light_red);
	 	static Light light_W_right=new Light(0,150,Light.light_green);
/**********************************类(共享)变量区***************************************************/
			
	 	/**构造方法*/
MyCanvas(){
	
			//添加鼠标监听是因为方便查看这个窗体的每个点的坐标
		    addMouseListener(this);
		    addMouseMotionListener(this);
			//两个按钮
			start.addActionListener(this);
			start.setBounds(30, 30, 80, 30);
			this.add(start);
			stop.addActionListener(this);
			stop.setBounds(30, 70, 80, 30);
			this.add(stop);
			//设置灯组在面板的位置
	 	 	LightControl1.setBounds(405, 305, 200,50);//N
	 	 	LightControl2.setBounds(495, 545,200,50);//S
	 	 	LightControl3.setBounds(645, 305, 50,200);//E
	 	 	LightControl4.setBounds(405, 395, 50,200);//W
	 	 	//添加四个灯组
 	 	 	this.add(LightControl1);
 	 	 	this.add(LightControl2);
 	 	 	this.add(LightControl3);
 	 	 	this.add(LightControl4);
 	 	 	
		}
	public void paint(Graphics g) {
		Graphics2D G=(Graphics2D)g;
		//--------------------设置画笔大小-----------------------
		BasicStroke bs1=new BasicStroke(3.0f);
		BasicStroke bs2=new BasicStroke(5.0f);
		BasicStroke bs3 = new BasicStroke(5.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 2.0f, new float[] { 10, 10, }, 0f);
		BasicStroke bs4=new BasicStroke(5.0f);
		//中心矩形
		Rectangle2D Rectangle=new Rectangle2D.Double(400,300,300,300);
		G.setStroke(bs1);
		G.setColor(Color.WHITE);
		G.draw(Rectangle);
		//---------------------画绿色草坪-----------------------
		//前两个参数一定要是左上角位置
		G.setColor(new Color(14,84,12));
		G.fillRect(0, 0, 400, 300);
		G.fillRect(0, 600,400,300);
		G.fillRect(700,600,500,300);
		G.fillRect(700, 0, 500, 300);
		//---------------------画黄色实线------------------------
		//南黄线
		Line2D line_south1=new Line2D.Double(400, 600, 400, 900);
		Line2D line_south2=new Line2D.Double(550, 600, 550,900);
		Line2D line_south3=new Line2D.Double(700, 600, 700, 900);
		//东黄线
		Line2D line_east1=new Line2D.Double(700, 600, 1200, 600);
		Line2D line_east2=new Line2D.Double(700,450,1200,450);
		Line2D line_east3=new Line2D.Double(700, 300, 1200, 300);
		//北黄线
		Line2D line_north1=new Line2D.Double(700, 300, 700, 0);
		Line2D line_north2=new Line2D.Double(550, 300, 550, 0);
		Line2D line_north3=new Line2D.Double(400, 300, 400, 0);
		//西黄线
		Line2D line_west1=new Line2D.Double(400, 300, 0, 300);
		Line2D line_west2=new Line2D.Double(400, 450, 0, 450);
		Line2D line_west3=new Line2D.Double(400, 600, 0, 600);
		//-----------------设置画笔属性并添加黄色实现----------
		G.setStroke(bs2);
		G.setColor(Color.YELLOW);
		G.draw(line_west1);
		G.draw(line_north3);
		G.draw(line_north1);
		G.draw(line_east3);
		G.draw(line_west3);
		G.draw(line_south1);
		G.draw(line_south3);
		G.draw(line_east1);
		G.draw(line_north2);
		G.draw(line_east2);
		G.draw(line_south2);
		G.draw(line_west2);
		//------------------------画黄色虚线----------------
		Line2D dashed_line_north=new Line2D.Double(540, 300, 540, 230);
        G.setStroke(bs3);
        G.setColor(Color.yellow);
        G.draw(dashed_line_north);//北虚线
        Line2D line_north=new Line2D.Double(540, 220, 540, 0);
        G.setStroke(bs2);
        G.setColor(Color.yellow);
        G.draw(line_north);//北实线
        
		Line2D dashed_line_west=new Line2D.Double(400, 460, 330, 460);
        G.setStroke(bs3);
        G.setColor(Color.yellow);
        G.draw(dashed_line_west);//虚线2
        Line2D line_west=new Line2D.Double(320, 460, 0, 460);
        G.setStroke(bs2);
        G.setColor(Color.yellow);
        G.draw(line_west);//实线2
        
        Line2D dashed_line_south=new Line2D.Double(560, 600, 560, 670);
        G.setStroke(bs3);
        G.setColor(Color.yellow);
        G.draw(dashed_line_south);//虚线3
        Line2D line_south=new Line2D.Double(560, 680, 560, 900);
        G.setStroke(bs2);
        G.setColor(Color.yellow);
        G.draw(line_south);//实线3
        
        Line2D dashed_line_east=new Line2D.Double(700, 440, 770, 440);
        G.setStroke(bs3);
        G.setColor(Color.yellow);
        G.draw(dashed_line_east);//虚线4
        Line2D line_east=new Line2D.Double(780, 440, 1200, 440);
        G.setStroke(bs2);
        G.setColor(Color.yellow);
        G.draw(line_east);//实线4
        //-------------------------------画白线----------------
        //第一组,南白线
        Line2D line21=new Line2D.Double(450,600,450,900);
		Line2D line22=new Line2D.Double(500,600,500,900);
		Line2D line23=new Line2D.Double(600, 600, 600,900);
		Line2D line24=new Line2D.Double(650, 600, 650,900);
		G.setStroke(bs4);
		G.setColor(Color.WHITE);
		G.draw(line21);//白线1
		G.draw(line22);//白线2
		G.draw(line23);//白线3
		G.draw(line24);//白线4
		//第二组,东白线
		Line2D line25=new Line2D.Double(700,350,1200,350);
		Line2D line26=new Line2D.Double(700,400,1200,400);
		Line2D line27=new Line2D.Double(700,500,1200,500);
		Line2D line28=new Line2D.Double(700,550,1200,550);
		G.setStroke(bs4);
		G.setColor(Color.WHITE);
		G.draw(line25);//白线1
		G.draw(line26);//白线2
		G.draw(line27);//白线3
		G.draw(line28);//白线4
		//第三组，北白线
		Line2D line29=new Line2D.Double(450,300,450,0);
		Line2D line30=new Line2D.Double(500,300,500,0);
		Line2D line31=new Line2D.Double(600,300,600,0);
		Line2D line32=new Line2D.Double(650,300,650,0);
		G.setStroke(bs4);
		G.setColor(Color.WHITE);
		G.draw(line29);//白线1
		G.draw(line30);//白线2
		G.draw(line31);//白线3
		G.draw(line32);//白线4
		//第四组，西白线
		Line2D line33=new Line2D.Double(400,350,0,350);
		Line2D line34=new Line2D.Double(400,400,0,400);
		Line2D line35=new Line2D.Double(400,500,0,500);
		Line2D line36=new Line2D.Double(400,550,0,550);
		G.setStroke(bs4);
		G.setColor(Color.WHITE);
		G.draw(line33);//白线1
		G.draw(line34);//白线2
		G.draw(line35);//白线3
		G.draw(line36);//白线4
		//-------------------------写提示文字----------
		//设置字体
		Font font1=new Font("宋体",Font.BOLD,50);
		Font font2=new Font("宋体",Font.BOLD,20);
		G.setColor(Color.white);
		G.setFont(font1);
		G.drawString("十字路口", 700, 60);
		G.setFont(font2);
		G.drawString("1.左转：左转红灯停止，左转绿灯左转", 750, 100);
		G.drawString("2.直行：直行红灯停止，直行绿灯通行", 750, 140);
		G.drawString("3.右转：常绿", 750, 180);
		Toolkit toolkit=getToolkit();
	    //-------------------------第一组图片------------
	    Image img1=toolkit.getImage("src/图片/1.1.png");
	    G.drawImage(img1, 545, 625,70,130,this);
	    Image img2=toolkit.getImage("src/图片/1.2.png");
	    G.drawImage(img2, 590, 650,70,70,this);
	    Image img3=toolkit.getImage("src/图片/1.3.png");
	    G.drawImage(img3, 640, 625,70,130,this);
	    //--------------------------第二组图片-----------
	    Image img4=toolkit.getImage("src/图片/2.1.png");
	    G.drawImage(img4, 735, 385,130,70,this);
	    Image img5=toolkit.getImage("src/图片/2.2.png");
	    G.drawImage(img5, 760, 340,70,70,this);
	    Image img6=toolkit.getImage("src/图片/2.3.png");
	    G.drawImage(img6, 735, 290,130,70,this);
	    //--------------------------第三组图片-----------
	    Image img7=toolkit.getImage("src/图片/3.1.png");
	    G.drawImage(img7, 485, 140,70,130,this);
	    Image img8=toolkit.getImage("src/图片/3.2.png");
	    G.drawImage(img8, 440, 175,70,70,this);
	    Image img9=toolkit.getImage("src/图片/3.3.png");
	    G.drawImage(img9, 390, 140,70,130,this);
	    //---------------------------第四组图片----------
	    Image img10=toolkit.getImage("src/图片/4.1.png");
	    G.drawImage(img10, 240, 445,130,70,this);
	    Image img11=toolkit.getImage("src/图片/4.2.png");
	    G.drawImage(img11, 275, 490,70,70,this);
	    Image img12=toolkit.getImage("src/图片/4.3.png");
	    G.drawImage(img12, 240, 540,130,70,this);

//#####################################通过调用添加总车的集合list_all画车######################################################################
for (int j = 0; j <Main.list_all.size(); j++) { 
		if(Main.list_all.get(j).road=="S2W"||Main.list_all.get(j).road=="S2N"||
				Main.list_all.get(j).road=="S2E"||Main.list_all.get(j).road=="N2E"||
						Main.list_all.get(j).road=="N2S"||Main.list_all.get(j).road=="N2W") {
//##############################################判断南北是否左转################################################
					//这里的判断很有必要，注意boolean类型的变量的初始值是false
					if(Main.list_all.get(j).turn_right==false&&Main.list_all.get(j).turn_left==false){
						G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_width, Car.car_height, this);
					}else if(Main.list_all.get(j).turn_left==true) {
						G.rotate(Math.toRadians(-Main.list_all.get(j).rotation_angle),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
						G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_width, Car.car_height, this);
						G.rotate(Math.toRadians(Main.list_all.get(j).rotation_angle),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
					}
//##############################################判断南北是否左转################################################
					
//##############################################判断南北是否右转################################################
					//这里的判断很有必要，注意boolean类型的变量的初始值是false
					if(Main.list_all.get(j).turn_right==false&&Main.list_all.get(j).turn_left==false) {
						G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_width, Car.car_height, this);
					}else if(Main.list_all.get(j).turn_right==true) {
						G.rotate(Math.toRadians(90),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
						G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_width, Car.car_height, this);
						G.rotate(Math.toRadians(-90),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
					}
//##############################################判断南北是否右转################################################
					}//if判断结束

					else {
 //##############################################判断东西是否左转################################################
						    //这里的判断很有必要，注意boolean类型的变量的初始值是false
							if(Main.list_all.get(j).turn_right==false&&Main.list_all.get(j).turn_left==false){
								G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_height, Car.car_width, this);
							}else if(Main.list_all.get(j).turn_left==true) {
								G.rotate(Math.toRadians(-Main.list_all.get(j).rotation_angle),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
								G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_height, Car.car_width, this);
								G.rotate(Math.toRadians(Main.list_all.get(j).rotation_angle),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
								
							}
//##############################################判断东西是否左转################################################
							
//##############################################判断东西是否右转################################################
							//这里的判断很有必要，注意boolean类型的变量的初始值是false
							if(Main.list_all.get(j).turn_right==false&&Main.list_all.get(j).turn_left==false) {
								//这条语句总是会抛出异常，得查看下
								G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_height, Car.car_width, this);
						    }else if(Main.list_all.get(j).turn_right==true) {
						    	G.rotate(Math.toRadians(90),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
								G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_height, Car.car_width, this);
								G.rotate(Math.toRadians(-90),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
						    }
					}
//##############################################判断东西是否右转################################################
	
	}//for循环结束
	}//paint()结束
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start) {
			//启动四组灯
			LightControl.RUN=true;
		 	f1.start();
		 	f2.start();
		 	f3.start();
		 	f4.start();
		 	
		}
		else if(e.getSource()==stop)
	 	{
			LightControl.RUN=false;
	 	}
		
	}//actionPerformed方法结束

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {	
		System.out.println("x:"+e.getX()+","+"y:"+e.getY());
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {	
		
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
	
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {	
	}
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {	
	}
	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
	}
	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
}
//	/**设置面板的弹出式菜单*/
//	public void popmenu() {
//		ButtonGroup buttongroup = new ButtonGroup();//创建一个按钮组
//	JPopupMenu popup  = new JPopupMenu();//创建一个弹出式菜单
//	JPanel poppanel = new JPanel();//创建一个面板
//	JMenu Vel_menu=new JMenu("时间选择");//创建一个“速度选择”菜单
//	JMenuItem start_menu=new JMenuItem("开始");//创建一个“开始”菜单项
//	JMenuItem stop_menu=new JMenuItem("停止");//创建一个“停止”菜单项
//		JRadioButtonMenuItem T_50 = new JRadioButtonMenuItem("时间X2",true);
//		JRadioButtonMenuItem T_25 = new JRadioButtonMenuItem("时间X4");
//		buttongroup.add(T_50);
//		buttongroup.add(T_25);//按钮组添加选项按钮
//		Vel_menu.add(T_50);
//		Vel_menu.add(T_25);//将选项按钮添加到弹出式菜单
//		Vel_menu.addSeparator();
//		popup.add(start_menu);
//		popup.addSeparator();//添加分隔符
//		popup.add(Vel_menu);
//		popup.addSeparator();//添加分隔符
//		popup.add(stop_menu);//弹出式菜单添加菜单项
//		poppanel.setComponentPopupMenu(popup);//设置面板容器为弹出式菜单
//		poppanel.addMouseListener(new MouseAdapter() {
//		});//设置面板的鼠标监听
//		//poppanel.setBackground(Color.CYAN);
//		this.add(poppanel);//当前窗体或者面板添加panel
//	}//弹出式菜单方法结束
	}
