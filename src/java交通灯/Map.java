package java交通灯;

import java.awt.Color;
import javax.swing.JFrame;

/**十字路口设计图*/
@SuppressWarnings("serial")
public class Map extends JFrame{
	public Map() {//实现构造方法
			initialize();//调用初始化方法，对界面进行设计
			MyCanvas mycanvas=new MyCanvas();//调用画布类实例一个对象
			this.getContentPane().add(mycanvas);//添加画布	
			mycanvas.setSize(this.getWidth(),this.getHeight());
		 	this.setLayout(null);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	    
	}//构造方法结束
	/**初始化依附的窗体*/
public void initialize() {
			this.setTitle("十字路口交通信号灯管理系统");
			this.setBounds(500, 130, 1200, 900);//设置窗体大小
			this.setBackground(new Color(147,147,147));//设置窗体背景色
			this.setVisible(true);//设置窗体可见
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭模式
	}//初始化方法结束
}//初始化窗体方法结束
