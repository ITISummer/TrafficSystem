package java��ͨ��;

import java.awt.Color;
import javax.swing.JFrame;

/**ʮ��·�����ͼ*/
@SuppressWarnings("serial")
public class Map extends JFrame{
	public Map() {//ʵ�ֹ��췽��
			initialize();//���ó�ʼ���������Խ���������
			MyCanvas mycanvas=new MyCanvas();//���û�����ʵ��һ������
			this.getContentPane().add(mycanvas);//��ӻ���	
			mycanvas.setSize(this.getWidth(),this.getHeight());
		 	this.setLayout(null);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	    
	}//���췽������
	/**��ʼ�������Ĵ���*/
public void initialize() {
			this.setTitle("ʮ��·�ڽ�ͨ�źŵƹ���ϵͳ");
			this.setBounds(500, 130, 1200, 900);//���ô����С
			this.setBackground(new Color(147,147,147));//���ô��屳��ɫ
			this.setVisible(true);//���ô���ɼ�
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���ر�ģʽ
	}//��ʼ����������
}//��ʼ�����巽������
