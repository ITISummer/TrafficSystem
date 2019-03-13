package java��ͨ��;

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
 * @������Ҫ�ǶԵ�ͼ������ƣ�������������*/
	@SuppressWarnings("serial")
public class MyCanvas extends JPanel implements ActionListener,MouseListener,MouseMotionListener{
/**********************************��Ա������***************************************************/
		JButton start=new JButton("statrt");//��������İ�ť
		JButton stop=new JButton("stop");//��ͣ����İ�ť
		//������飬֮�����ܹ�������ʵ�������ڵ�����ʵ����ǰ������Ϊ�����Ƶ����η���static�ģ�����ʵ����֮ǰ�����˷�����ڴ�
	 	LightControl LightControl1=new LightControl(light_N_right,light_N_middle,light_N_left,"NS",'N',10);
	 	LightControl LightControl2=new LightControl(light_S_left,light_S_middle,light_S_right,"NS",'S',10);
	 	LightControl LightControl3=new LightControl(light_E_right,light_E_middle,light_E_left,"WE",'E',20);
	 	LightControl LightControl4=new LightControl(light_W_left,light_W_middle,light_W_right,"WE",'W',20);
	 	//�̶߳���
			Thread f1=new Thread(LightControl1);
		 	Thread f2=new Thread(LightControl2);
		 	Thread f3=new Thread(LightControl3);
		 	Thread f4=new Thread(LightControl4);
/**********************************��Ա������***************************************************/
		
/**********************************��(����)������***************************************************/
		 //ʮ������
		 //����
	 	static Light light_N_left=new Light(0,0,Light.light_green);
	 	static Light light_N_middle=new Light(50,0,Light.light_green);
	 	static Light light_N_right=new Light(100,0,Light.light_red);
	 	//�ϵ�
	 	static Light light_S_left=new Light(50,0,Light.light_red);
	 	static Light light_S_middle=new Light(100,0,Light.light_green);
	 	static Light light_S_right=new Light(150,0,Light.light_green);
	 	//����
	 	static Light light_E_left=new Light(0,0,Light.light_green);
	 	static Light light_E_middle=new Light(0,50,Light.light_red);
	 	static Light light_E_right=new Light(0,100,Light.light_red);
	 	//����
	 	static Light light_W_left=new Light(0,50,Light.light_red);
	 	static Light light_W_middle=new Light(0,100,Light.light_red);
	 	static Light light_W_right=new Light(0,150,Light.light_green);
/**********************************��(����)������***************************************************/
			
	 	/**���췽��*/
MyCanvas(){
	
			//�������������Ϊ����鿴��������ÿ���������
		    addMouseListener(this);
		    addMouseMotionListener(this);
			//������ť
			start.addActionListener(this);
			start.setBounds(30, 30, 80, 30);
			this.add(start);
			stop.addActionListener(this);
			stop.setBounds(30, 70, 80, 30);
			this.add(stop);
			//���õ���������λ��
	 	 	LightControl1.setBounds(405, 305, 200,50);//N
	 	 	LightControl2.setBounds(495, 545,200,50);//S
	 	 	LightControl3.setBounds(645, 305, 50,200);//E
	 	 	LightControl4.setBounds(405, 395, 50,200);//W
	 	 	//����ĸ�����
 	 	 	this.add(LightControl1);
 	 	 	this.add(LightControl2);
 	 	 	this.add(LightControl3);
 	 	 	this.add(LightControl4);
 	 	 	
		}
	public void paint(Graphics g) {
		Graphics2D G=(Graphics2D)g;
		//--------------------���û��ʴ�С-----------------------
		BasicStroke bs1=new BasicStroke(3.0f);
		BasicStroke bs2=new BasicStroke(5.0f);
		BasicStroke bs3 = new BasicStroke(5.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND, 2.0f, new float[] { 10, 10, }, 0f);
		BasicStroke bs4=new BasicStroke(5.0f);
		//���ľ���
		Rectangle2D Rectangle=new Rectangle2D.Double(400,300,300,300);
		G.setStroke(bs1);
		G.setColor(Color.WHITE);
		G.draw(Rectangle);
		//---------------------����ɫ��ƺ-----------------------
		//ǰ��������һ��Ҫ�����Ͻ�λ��
		G.setColor(new Color(14,84,12));
		G.fillRect(0, 0, 400, 300);
		G.fillRect(0, 600,400,300);
		G.fillRect(700,600,500,300);
		G.fillRect(700, 0, 500, 300);
		//---------------------����ɫʵ��------------------------
		//�ϻ���
		Line2D line_south1=new Line2D.Double(400, 600, 400, 900);
		Line2D line_south2=new Line2D.Double(550, 600, 550,900);
		Line2D line_south3=new Line2D.Double(700, 600, 700, 900);
		//������
		Line2D line_east1=new Line2D.Double(700, 600, 1200, 600);
		Line2D line_east2=new Line2D.Double(700,450,1200,450);
		Line2D line_east3=new Line2D.Double(700, 300, 1200, 300);
		//������
		Line2D line_north1=new Line2D.Double(700, 300, 700, 0);
		Line2D line_north2=new Line2D.Double(550, 300, 550, 0);
		Line2D line_north3=new Line2D.Double(400, 300, 400, 0);
		//������
		Line2D line_west1=new Line2D.Double(400, 300, 0, 300);
		Line2D line_west2=new Line2D.Double(400, 450, 0, 450);
		Line2D line_west3=new Line2D.Double(400, 600, 0, 600);
		//-----------------���û������Բ���ӻ�ɫʵ��----------
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
		//------------------------����ɫ����----------------
		Line2D dashed_line_north=new Line2D.Double(540, 300, 540, 230);
        G.setStroke(bs3);
        G.setColor(Color.yellow);
        G.draw(dashed_line_north);//������
        Line2D line_north=new Line2D.Double(540, 220, 540, 0);
        G.setStroke(bs2);
        G.setColor(Color.yellow);
        G.draw(line_north);//��ʵ��
        
		Line2D dashed_line_west=new Line2D.Double(400, 460, 330, 460);
        G.setStroke(bs3);
        G.setColor(Color.yellow);
        G.draw(dashed_line_west);//����2
        Line2D line_west=new Line2D.Double(320, 460, 0, 460);
        G.setStroke(bs2);
        G.setColor(Color.yellow);
        G.draw(line_west);//ʵ��2
        
        Line2D dashed_line_south=new Line2D.Double(560, 600, 560, 670);
        G.setStroke(bs3);
        G.setColor(Color.yellow);
        G.draw(dashed_line_south);//����3
        Line2D line_south=new Line2D.Double(560, 680, 560, 900);
        G.setStroke(bs2);
        G.setColor(Color.yellow);
        G.draw(line_south);//ʵ��3
        
        Line2D dashed_line_east=new Line2D.Double(700, 440, 770, 440);
        G.setStroke(bs3);
        G.setColor(Color.yellow);
        G.draw(dashed_line_east);//����4
        Line2D line_east=new Line2D.Double(780, 440, 1200, 440);
        G.setStroke(bs2);
        G.setColor(Color.yellow);
        G.draw(line_east);//ʵ��4
        //-------------------------------������----------------
        //��һ��,�ϰ���
        Line2D line21=new Line2D.Double(450,600,450,900);
		Line2D line22=new Line2D.Double(500,600,500,900);
		Line2D line23=new Line2D.Double(600, 600, 600,900);
		Line2D line24=new Line2D.Double(650, 600, 650,900);
		G.setStroke(bs4);
		G.setColor(Color.WHITE);
		G.draw(line21);//����1
		G.draw(line22);//����2
		G.draw(line23);//����3
		G.draw(line24);//����4
		//�ڶ���,������
		Line2D line25=new Line2D.Double(700,350,1200,350);
		Line2D line26=new Line2D.Double(700,400,1200,400);
		Line2D line27=new Line2D.Double(700,500,1200,500);
		Line2D line28=new Line2D.Double(700,550,1200,550);
		G.setStroke(bs4);
		G.setColor(Color.WHITE);
		G.draw(line25);//����1
		G.draw(line26);//����2
		G.draw(line27);//����3
		G.draw(line28);//����4
		//�����飬������
		Line2D line29=new Line2D.Double(450,300,450,0);
		Line2D line30=new Line2D.Double(500,300,500,0);
		Line2D line31=new Line2D.Double(600,300,600,0);
		Line2D line32=new Line2D.Double(650,300,650,0);
		G.setStroke(bs4);
		G.setColor(Color.WHITE);
		G.draw(line29);//����1
		G.draw(line30);//����2
		G.draw(line31);//����3
		G.draw(line32);//����4
		//�����飬������
		Line2D line33=new Line2D.Double(400,350,0,350);
		Line2D line34=new Line2D.Double(400,400,0,400);
		Line2D line35=new Line2D.Double(400,500,0,500);
		Line2D line36=new Line2D.Double(400,550,0,550);
		G.setStroke(bs4);
		G.setColor(Color.WHITE);
		G.draw(line33);//����1
		G.draw(line34);//����2
		G.draw(line35);//����3
		G.draw(line36);//����4
		//-------------------------д��ʾ����----------
		//��������
		Font font1=new Font("����",Font.BOLD,50);
		Font font2=new Font("����",Font.BOLD,20);
		G.setColor(Color.white);
		G.setFont(font1);
		G.drawString("ʮ��·��", 700, 60);
		G.setFont(font2);
		G.drawString("1.��ת����ת���ֹͣ����ת�̵���ת", 750, 100);
		G.drawString("2.ֱ�У�ֱ�к��ֹͣ��ֱ���̵�ͨ��", 750, 140);
		G.drawString("3.��ת������", 750, 180);
		Toolkit toolkit=getToolkit();
	    //-------------------------��һ��ͼƬ------------
	    Image img1=toolkit.getImage("src/ͼƬ/1.1.png");
	    G.drawImage(img1, 545, 625,70,130,this);
	    Image img2=toolkit.getImage("src/ͼƬ/1.2.png");
	    G.drawImage(img2, 590, 650,70,70,this);
	    Image img3=toolkit.getImage("src/ͼƬ/1.3.png");
	    G.drawImage(img3, 640, 625,70,130,this);
	    //--------------------------�ڶ���ͼƬ-----------
	    Image img4=toolkit.getImage("src/ͼƬ/2.1.png");
	    G.drawImage(img4, 735, 385,130,70,this);
	    Image img5=toolkit.getImage("src/ͼƬ/2.2.png");
	    G.drawImage(img5, 760, 340,70,70,this);
	    Image img6=toolkit.getImage("src/ͼƬ/2.3.png");
	    G.drawImage(img6, 735, 290,130,70,this);
	    //--------------------------������ͼƬ-----------
	    Image img7=toolkit.getImage("src/ͼƬ/3.1.png");
	    G.drawImage(img7, 485, 140,70,130,this);
	    Image img8=toolkit.getImage("src/ͼƬ/3.2.png");
	    G.drawImage(img8, 440, 175,70,70,this);
	    Image img9=toolkit.getImage("src/ͼƬ/3.3.png");
	    G.drawImage(img9, 390, 140,70,130,this);
	    //---------------------------������ͼƬ----------
	    Image img10=toolkit.getImage("src/ͼƬ/4.1.png");
	    G.drawImage(img10, 240, 445,130,70,this);
	    Image img11=toolkit.getImage("src/ͼƬ/4.2.png");
	    G.drawImage(img11, 275, 490,70,70,this);
	    Image img12=toolkit.getImage("src/ͼƬ/4.3.png");
	    G.drawImage(img12, 240, 540,130,70,this);

//#####################################ͨ����������ܳ��ļ���list_all����######################################################################
for (int j = 0; j <Main.list_all.size(); j++) { 
		if(Main.list_all.get(j).road=="S2W"||Main.list_all.get(j).road=="S2N"||
				Main.list_all.get(j).road=="S2E"||Main.list_all.get(j).road=="N2E"||
						Main.list_all.get(j).road=="N2S"||Main.list_all.get(j).road=="N2W") {
//##############################################�ж��ϱ��Ƿ���ת################################################
					//������жϺ��б�Ҫ��ע��boolean���͵ı����ĳ�ʼֵ��false
					if(Main.list_all.get(j).turn_right==false&&Main.list_all.get(j).turn_left==false){
						G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_width, Car.car_height, this);
					}else if(Main.list_all.get(j).turn_left==true) {
						G.rotate(Math.toRadians(-Main.list_all.get(j).rotation_angle),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
						G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_width, Car.car_height, this);
						G.rotate(Math.toRadians(Main.list_all.get(j).rotation_angle),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
					}
//##############################################�ж��ϱ��Ƿ���ת################################################
					
//##############################################�ж��ϱ��Ƿ���ת################################################
					//������жϺ��б�Ҫ��ע��boolean���͵ı����ĳ�ʼֵ��false
					if(Main.list_all.get(j).turn_right==false&&Main.list_all.get(j).turn_left==false) {
						G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_width, Car.car_height, this);
					}else if(Main.list_all.get(j).turn_right==true) {
						G.rotate(Math.toRadians(90),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
						G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_width, Car.car_height, this);
						G.rotate(Math.toRadians(-90),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
					}
//##############################################�ж��ϱ��Ƿ���ת################################################
					}//if�жϽ���

					else {
 //##############################################�ж϶����Ƿ���ת################################################
						    //������жϺ��б�Ҫ��ע��boolean���͵ı����ĳ�ʼֵ��false
							if(Main.list_all.get(j).turn_right==false&&Main.list_all.get(j).turn_left==false){
								G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_height, Car.car_width, this);
							}else if(Main.list_all.get(j).turn_left==true) {
								G.rotate(Math.toRadians(-Main.list_all.get(j).rotation_angle),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
								G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_height, Car.car_width, this);
								G.rotate(Math.toRadians(Main.list_all.get(j).rotation_angle),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
								
							}
//##############################################�ж϶����Ƿ���ת################################################
							
//##############################################�ж϶����Ƿ���ת################################################
							//������жϺ��б�Ҫ��ע��boolean���͵ı����ĳ�ʼֵ��false
							if(Main.list_all.get(j).turn_right==false&&Main.list_all.get(j).turn_left==false) {
								//����������ǻ��׳��쳣���ò鿴��
								G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_height, Car.car_width, this);
						    }else if(Main.list_all.get(j).turn_right==true) {
						    	G.rotate(Math.toRadians(90),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
								G.drawImage(getToolkit().getImage(Main.list_all.get(j).car_img), Main.list_all.get(j).x1, Main.list_all.get(j).y1, Car.car_height, Car.car_width, this);
								G.rotate(Math.toRadians(-90),Main.list_all.get(j).x1,Main.list_all.get(j).y1);
						    }
					}
//##############################################�ж϶����Ƿ���ת################################################
	
	}//forѭ������
	}//paint()����
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start) {
			//���������
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
		
	}//actionPerformed��������

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
//	/**�������ĵ���ʽ�˵�*/
//	public void popmenu() {
//		ButtonGroup buttongroup = new ButtonGroup();//����һ����ť��
//	JPopupMenu popup  = new JPopupMenu();//����һ������ʽ�˵�
//	JPanel poppanel = new JPanel();//����һ�����
//	JMenu Vel_menu=new JMenu("ʱ��ѡ��");//����һ�����ٶ�ѡ�񡱲˵�
//	JMenuItem start_menu=new JMenuItem("��ʼ");//����һ������ʼ���˵���
//	JMenuItem stop_menu=new JMenuItem("ֹͣ");//����һ����ֹͣ���˵���
//		JRadioButtonMenuItem T_50 = new JRadioButtonMenuItem("ʱ��X2",true);
//		JRadioButtonMenuItem T_25 = new JRadioButtonMenuItem("ʱ��X4");
//		buttongroup.add(T_50);
//		buttongroup.add(T_25);//��ť�����ѡ�ť
//		Vel_menu.add(T_50);
//		Vel_menu.add(T_25);//��ѡ�ť��ӵ�����ʽ�˵�
//		Vel_menu.addSeparator();
//		popup.add(start_menu);
//		popup.addSeparator();//��ӷָ���
//		popup.add(Vel_menu);
//		popup.addSeparator();//��ӷָ���
//		popup.add(stop_menu);//����ʽ�˵���Ӳ˵���
//		poppanel.setComponentPopupMenu(popup);//�����������Ϊ����ʽ�˵�
//		poppanel.addMouseListener(new MouseAdapter() {
//		});//��������������
//		//poppanel.setBackground(Color.CYAN);
//		this.add(poppanel);//��ǰ�������������panel
//	}//����ʽ�˵���������
	}
