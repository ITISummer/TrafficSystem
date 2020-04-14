package java交通灯;
/**对车进行智能化控制的类*/
public class Car implements Runnable{
/**********************************成员变量区***************************************************/
	int x1,y1;//车的起始位置
	int x2,y2;//车的终点位置
	int velocity;//车的速度
	int random_R;//路名随机数
	int random_V;//速度随机数
	int safety_line;//车辆等红绿灯时的安全线 
	boolean turn_left;
	boolean turn_right;//注意boolean类型的变量的初始值是false
	private boolean isAlive=true;
	String car_img;//车自身图片
	String road;//对路名的选择
	int rotation_angle;//转弯车辆的旋转角度
/**********************************成员变量区***************************************************/
	
/**********************************类(共享)变量区***********************************************/
	static  final int car_width=30;//车的宽度
	static  final int car_height=70;//车的高度
	private static  int car_U=0;//上
	private static  int car_D=1;//下
	private static  int car_L=2;//左
	private static  int car_R=3;//右
	private static  final int time=100;//小车刷新的时间
	static int car_velocity[]= {10,15,20};//车辆速度
	/**车辆图片路径数组，0-上，1-下，2-左，3-右*/
	private static String img[][]= {{"src/图片/S2N.png","src/图片/S2N2.png"},{"src/图片/N2S.png","src/图片/N2S2.png"},{"src/图片/E2W.png","src/图片/E2W2.png"},{"src/图片/W2E.png","src/图片/W2E2.png"}};
	/**路名数组*/
	private static String car_on_road[]= {"S2W","S2N","S2E","N2E","N2S","N2W","E2S","E2W","E2N","W2N","W2E","W2S"};
/**********************************类(共享)变量区************************************************/
	
	/**构造方法，对生成车各个属性的方法进行调用*/
	public Car(){
		create_car();//调用生成车属性的方法
}//构造方法结束
	/**@生成车的属性的方法
	 * @1、车辆图片
	 * @2、车辆起始位置和终点位置*/
	public void create_car() {
		//每隔5*Car.time毫秒产生一辆车
		try {Thread.sleep((int)(15*Car.time));}catch(Throwable e) {e.printStackTrace();}
		//生成随机数
		this.random_R=(int) (Math.random()*12);//随机数的范围是0~乘以的数字的前闭后开区间
		this.random_V=(int) (Math.random()*3);
		this.road=Car.car_on_road[this.random_R];//车所在道路的选择  
		this.velocity=Car.car_velocity[this.random_V];//车的速度的选择
		Main.list_all.add(this);//使用集合list_all来添加以便于后续操作全部车的对象
		switch(this.road) {
		case "S2W"://南左转西
			this.car_img=img[Car.car_U][(int) (Math.random()*2)];
			x1=565;y1=950;x2=-50;y2=430;
			this.safety_line=600;//设置行驶安全线为y1=600;
			Main.list_S2W.add(this);
			break;
		case "S2N"://南直走北
			this.car_img=img[Car.car_U][(int) (Math.random()*2)];
			x1=610;y1=950;x2=620;y2=-50;
			this.safety_line=600;//设置行驶安全线为y1=600;
			Main.list_S2N.add(this);
			 break;
		case "S2E"://南右转东
			this.car_img=img[Car.car_U][(int) (Math.random()*2)];
			x1=660;y1=950;x2=1250;y2=430;
			this.safety_line=600;//设置行驶安全线为y1=600;
			Main.list_S2E.add(this);
			 break;
		case "N2E"://北左转东
			this.car_img=img[Car.car_D][(int) (Math.random()*2)];
			x1=505;y1=-50;x2=1250;y2=470;
			this.safety_line=300;//设置行驶安全线为y1=300;
			Main.list_N2E.add(this);
			 break;
		case "N2S"://北直走南
			this.car_img=img[Car.car_D][(int) (Math.random()*2)];
			x1=460;y1=-50;x2=480;y2=950;
			this.safety_line=300;//设置行驶安全线为y1=300;
			Main.list_N2S.add(this);
			 break;
		case "N2W"://北右转西
			this.car_img=img[Car.car_D][(int) (Math.random()*2)];
			x1=410;y1=-50;x2=-50;y2=330;
			this.safety_line=300;//设置行驶安全线为y1=300;
			Main.list_N2W.add(this);
			 break;
		case "E2S"://东左转南
			this.car_img=img[Car.car_L][(int) (Math.random()*2)];
			x1=1250;y1=405;x2=520;y2=950;
			this.safety_line=700;//设置行驶安全线为x1=700
			Main.list_E2S.add(this);
			 break;
		case "E2W"://东直走西
			this.car_img=img[Car.car_L][(int) (Math.random()*2)];
			x1=1250;y1=360;x2=-50;y2=400;	
			this.safety_line=700;//设置行驶安全线为x1=700
			Main.list_E2W.add(this);
			 break;
		case "E2N"://东右转北
			this.car_img=img[Car.car_L][(int) (Math.random()*2)];
			x1=1250;y1=310;x2=670;y2=-50;
			this.safety_line=700;//设置行驶安全线为x1=700
			Main.list_E2N.add(this);
			 break;
		case "W2N"://西左转北
			this.car_img=img[Car.car_R][(int) (Math.random()*2)];
			x1=-50;y1=465;x2=570;y2=-50;
			this.safety_line=400;//设置行驶安全线为x1=400
			Main.list_W2N.add(this);
			 break;
		case "W2E"://西直走东
			this.car_img=img[Car.car_R][(int) (Math.random()*2)];
			x1=-50;y1=510;x2=1250;y2=550;	
			this.safety_line=400;//设置行驶安全线为x1=400
			Main.list_W2E.add(this);
			 break;
		case "W2S"://西右转南
			this.car_img=img[Car.car_R][(int) (Math.random()*2)];
			x1=-50;y1=560;x2=600;y2=950;
			this.safety_line=400;//设置行驶安全线为x1=400
			Main.list_W2S.add(this);
			 break;
		}//switch语句结束
}//生成车属性的方法结束
	@Override
	public void run() {
		while(this.isAlive) {
/*******************************局部变量区***********************************************/
			int original_V=this.velocity;//用变量来保存初始速度
			//之所以申请局部变量是因为要保存的是this.velocity实例化后的得到的值
			int myself;//定义一个变量来保存this(自己)在集合中的位置
			boolean isrun;//定义一个boolean变量来作为是否判断灯状态
			//设置这个变量的目的是是避免在车辆停止的时候进入判断灯的死循环，从而使电脑CPU的过载
/*******************************局部变量区***********************************************/	
			switch(this.road) {
			case "S2W"://南左转西
				while(this.x1>=this.x2) {
							try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
							Main.map.repaint();
						if(this.y1>=this.safety_line-20) {
								isrun=true;
								this.y1-=this.velocity;
//**************************************判断左转****************************************************
							    this.turn_left=false;
//**************************************判断左转****************************************************
		        		 		 
//##############################################判断红绿灯#################################################
		    		 			 /*****************第一阶段：在this.safety_line~this.safety_line+60之间开始减速*********************/ 
		        		 		while(isrun) {
				        		 if((MyCanvas.light_S_left.get_status()==Light.light_red||MyCanvas.light_S_left.get_status()==Light.light_yellow)&&(this.y1<=this.safety_line+50&&this.y1>this.safety_line)) {
				        			 this.velocity=5;
				        		 }
				        		  /****************第二阶段：在this.safety_line~this.safety_line+10之间停止***********************/
		         				  if((MyCanvas.light_S_left.get_status()==Light.light_red)&&(this.y1<=this.safety_line+30&&this.y1>this.safety_line)) {
		         					  	this.velocity=0;
		         				  } 
		         				 /****************第三阶段：在小于等于this.safety_line时恢复初始速度***********************/	
				        		  if(this.y1<=this.safety_line) {
				        			  	this.velocity=original_V;
				        		  }
		         				 isrun=false;
				        		  }//while循环 
//##############################################判断红绿灯#################################################
//**************************************判断车间距****************************************************
				        		  for(int i=0;i<Main.list_S2W.size();i++) {
				   		        	 if((Main.list_S2W.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_S2W.get(i).hashCode())) {
				   		        		 myself=i;//找到自己在集合中的位置
				   		        		if((Main.list_S2W.get(myself-1).y1>=this.safety_line-160)&&(Main.list_S2W.get(myself).y1-(Main.list_S2W.get(myself-1).y1+Car.car_height)<=15)) {//
				   		        			Main.list_S2W.get(myself).velocity=Main.list_S2W.get(myself-1).velocity;
				   		        			Main.list_S2W.get(myself).y1=Main.list_S2W.get(myself-1).y1+car_height+10;
				   		        		}
				   		        		else if(Main.list_S2W.get(myself).y1<this.safety_line-160){
				   		        			Main.list_S2W.get(myself).velocity=Main.list_S2W.get(myself-1).velocity;
				   	   		        		Main.list_S2W.get(myself-1).velocity+=10;//这里之所前面的速度加10是因为防止转完后出现车辆的重叠，因为转弯完后是没有进行车距的判断的
				   		        		}
				   		        	 }
				        		  } //for循环
//**************************************判断车间距****************************************************
		        	 }
		        	 else {
		        		 	this.turn_left=true;
//########################转弯时控制角度的变化########################################	
		        		 if(this.y1>this.safety_line-160) {//y=440
		        			 this.rotation_angle+=7;//角度自增
		        			 this.y1-=this.velocity;
		        		 }else if(this.y1<=this.safety_line-160) {
		        			 this.rotation_angle=90;
		        		 }
//########################转弯时控制角度的变化######################################## 
		        		 this.x1-=this.velocity;
		        		 if(this.x1<this.x2) {
		        			 Main.list_S2W.remove(this);
		        			 Main.list_all.remove(this);
		        			 isAlive=false;
		        		 }
		        	 }//if语句
	
				}//while语句 
				
			break; 
			case "S2N"://南直走北; 
				while(this.y1>=this.y2) {
	            	try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            	Main.map.repaint();
	            	isrun=true;
	            	this.y1-=this.velocity;
//##############################################判断红绿灯#################################################
		 			 /*****************第一阶段：在this.safety_line~this.safety_line+60之间开始减速*********************/ 
   		 		while(isrun) {
	        		 if((MyCanvas.light_S_middle.get_status()==Light.light_red||MyCanvas.light_S_middle.get_status()==Light.light_yellow)&&(this.y1<=this.safety_line+50&&this.y1>this.safety_line)) {
	        			 this.velocity=5;
	        		 }
	        		  /****************第二阶段：在this.safety_line~this.safety_line+10之间停止***********************/
    				  if((MyCanvas.light_S_middle.get_status()==Light.light_red)&&(this.y1<=this.safety_line+30&&this.y1>this.safety_line)) {
    					  	this.velocity=0;
    				  } 
    				 /****************第三阶段：在小于等于this.safety_line时恢复初始速度***********************/	
	        		  if(this.y1<=this.safety_line) {
	        			  	this.velocity=original_V;
	        		  }
    				 isrun=false;
	        		  }//while循环 
//##############################################判断红绿灯#################################################
//**************************************判断车间距****************************************************
	        		  for(int i=0;i<Main.list_S2N.size();i++) {
	   		        	 if((Main.list_S2N.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_S2N.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((this.y1>=this.y2)&&(Main.list_S2N.get(myself).y1-(Main.list_S2N.get(myself-1).y1+Car.car_height)<=20)) {
	   		        			Main.list_S2N.get(myself).velocity=Main.list_S2N.get(myself-1).velocity;
	   		        			Main.list_S2N.get(myself).y1=Main.list_S2N.get(myself-1).y1+car_height+10;
	   		        		}

	   		        	 }
	        		  } //for循环
//**************************************判断车间距****************************************************
	        		  if(this.y1<this.y2){
	        			  Main.list_S2N.remove(this);
	        			  Main.list_all.remove(this);
	        			  this.isAlive=false;
	            }//if判断结束
	            }//while循环结束
			break;
			case "S2E"://南右转东
				 while(this.x1<=this.x2) {
	            		try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            		Main.map.repaint();
	            		if(this.y1>=570) {
	            			this.y1-=this.velocity;
//**************************************判断右转****************************************************
						    this.turn_right=false;
//**************************************判断右转****************************************************
//**************************************判断车间距****************************************************
	   	        		  for(int i=0;i<Main.list_S2E.size();i++) {
	   	   		        	 if((Main.list_S2E.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_S2E.get(i).hashCode())) {
	   	   		        		 myself=i;
	   	   		        		if((Main.list_S2E.get(myself-1).y1>=570)&&(Main.list_S2E.get(myself).y1-(Main.list_S2E.get(myself-1).y1+Car.car_height)<=20)) {
	   	   		        		Main.list_S2E.get(myself).y1=Main.list_S2E.get(myself-1).y1+car_height+10;
	   	   		        		}
	   	   		        		else if(Main.list_S2E.get(myself).y1<570){
	   	   		        		Main.list_S2E.get(myself).velocity=Main.list_S2E.get(myself-1).velocity;
	   	   		        		Main.list_S2E.get(myself-1).velocity+=10;//这里之所前面的速度加10是因为防止转完后出现车辆的重叠，因为转弯完后是没有进行车距的判断的
	   	   		        		}
	   	   		        	 }
	   	        		  } //for循环
//**************************************判断车间距****************************************************
	            		}
	            		else {
	            			//右旋转90度，未写
	            			this.turn_right=true;
			        		 this.x1+=this.velocity;
			        		 if(this.x1>=this.x2) {
			        			 Main.list_S2E.remove(this);
			        			 Main.list_all.remove(this);
			        			 this.isAlive=false;
			        		 }
			        	
			        	 }//if语句
				 }//while循环
				
			break;
			case "N2E"://北左转东;
				 while(this.x1<=this.x2) {
		        	 try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
		        	 	Main.map.repaint();
		        if(this.y1<=this.safety_line) {
		        	 isrun=true;
         			 this.y1+=this.velocity;
//**************************************判断左转****************************************************
					    this.turn_left=false;
//**************************************判断左转****************************************************
//##############################################判断红绿灯#################################################
		 			 /*****************第一阶段：在this.safety_line~this.safety_line+60之间开始减速*********************/ 
         			 while(isrun) {
	        		  if((MyCanvas.light_N_right.get_status()==Light.light_red||MyCanvas.light_N_right.get_status()==Light.light_yellow)&&(this.y1<this.safety_line&&this.y1>=this.safety_line-130)) {
	        		 		this.velocity=5;
         			 }
	        		  /****************第二阶段：在this.safety_line~this.safety_line+10之间停止***********************/
     				  if((MyCanvas.light_N_right.get_status()==Light.light_red)&&(this.y1<this.safety_line&&this.y1>=this.safety_line-100)) {
     					  	this.velocity=0;
     				  } 
     				 /****************第三阶段：在小于等于this.safety_line时恢复初始速度***********************/	
	        		  if((this.y1>=this.safety_line)) {
	        			  	this.velocity=original_V;
	        		  }
	        		  isrun=false;
	        		  }//while循环
//##############################################判断红绿灯#################################################
//**************************************判断车间距****************************************************
	        		  for(int i=0;i<Main.list_N2E.size();i++) {
	   		        	 if((Main.list_N2E.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_N2E.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((Main.list_N2E.get(myself-1).y1<=this.safety_line+180)&&(Main.list_N2E.get(myself-1).y1-(Main.list_N2E.get(myself).y1+Car.car_height)<=20)) {
	   		        			Main.list_N2E.get(myself).velocity=Main.list_N2E.get(myself-1).velocity;
	   		        			Main.list_N2E.get(myself).y1=Main.list_N2E.get(myself-1).y1-(car_height+10);
	   		        		}
	   		        		else if(Main.list_N2E.get(myself).y1>this.safety_line+180){
	   		        			Main.list_N2E.get(myself).velocity=Main.list_N2E.get(myself-1).velocity;
	   	   		        		Main.list_N2E.get(myself-1).velocity+=10;//这里之所前面的速度加10是因为防止转完后出现车辆的重叠，因为转弯完后是没有进行车距的判断的

	   		        		}
	   		        	 }
	        		  } //for循环
//**************************************判断车间距****************************************************
		        	 }
		        	 else {
		        		 	this.turn_left=true;
//########################转弯时控制角度的变化########################################	
			        		 if(this.y1<this.safety_line+180) {
			        			 this.rotation_angle+=5;
			        			 this.y1+=this.velocity;
			        		 }else if(this.y1>=this.safety_line+180) {
			        			 this.rotation_angle=90;
			        		 }
//########################转弯时控制角度的变化######################################## 
		        		 	this.x1+=this.velocity;
		        		 if(this.x1>=this.x2) {
		        			 Main.list_N2E.remove(this);
		        			 Main.list_all.remove(this);
		        			 this.isAlive=false;
		        		 }
		        	 }//if语句
	
		       }//while语句	
			break;
			case "N2S"://北直走南;
				while(this.y1<=this.y2) {
	            	try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            		Main.map.repaint(); 
	            		isrun=true;
	            		this.y1+=this.velocity;
//##############################################判断红绿灯#################################################
			 			 /*****************第一阶段：在this.safety_line~this.safety_line+60之间开始减速*********************/ 
	         			 while(isrun) {
		        		  if((MyCanvas.light_N_middle.get_status()==Light.light_red||MyCanvas.light_N_middle.get_status()==Light.light_yellow)&&(this.y1<this.safety_line&&this.y1>=this.safety_line-130)) {
		        		 		this.velocity=5;
	         			 }
		        		  /****************第二阶段：在this.safety_line~this.safety_line+10之间停止***********************/
	     				  if((MyCanvas.light_N_middle.get_status()==Light.light_red)&&(this.y1<this.safety_line&&this.y1>=this.safety_line-100)) {
	     					  	this.velocity=0;
	     				  } 
	     				 /****************第三阶段：在小于等于this.safety_line时恢复初始速度***********************/	
		        		  if((this.y1>=this.safety_line)) {
		        			  	this.velocity=original_V;
		        		  }
		        		  isrun=false;
		        		  }//while循环
//##############################################判断红绿灯#################################################
//**************************************判断车间距****************************************************
	        		  for(int i=0;i<Main.list_N2S.size();i++) {
	   		        	 if((Main.list_N2S.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_N2S.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((this.y1<=this.y2)&&(Main.list_N2S.get(myself-1).y1-(Main.list_N2S.get(myself).y1+Car.car_height)<=20)) {
	   		        			Main.list_N2S.get(myself).velocity=Main.list_N2S.get(myself-1).velocity;
	   		        			Main.list_N2S.get(myself).y1=Main.list_N2S.get(myself-1).y1-(car_height+10);
	   		        		}

	   		        	 }
	        		  } //for循环
//**************************************判断车间距****************************************************
	            if(this.y1>=this.y2) {
	            	Main.list_N2S.remove(this);
	            	Main.list_all.remove(this);
	            	this.isAlive=false;
	            }//if判断结束
	            }//while循环结束
			break;
			case "N2W"://北右转西;
				 while(this.x1>=this.x2) {
	            		try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            		Main.map.repaint();
	            		if(this.y1<=this.safety_line) {
	            			this.y1+=this.velocity;
//**************************************判断左转****************************************************
						    this.turn_right=false;
//**************************************判断左转****************************************************
//**************************************判断车间距****************************************************
		   	        		  for(int i=0;i<Main.list_N2W.size();i++) {
		   	   		        	 if((Main.list_N2W.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_N2W.get(i).hashCode())) {
		   	   		        		 myself=i;
		   	   		        		if((Main.list_N2W.get(myself-1).y1<=this.safety_line)&&(Main.list_N2W.get(myself-1).y1-(Main.list_N2W.get(myself).y1+Car.car_height)<=20)) {
		   	   		        			Main.list_N2W.get(myself).velocity=Main.list_N2W.get(myself-1).velocity;
		   	   		        			Main.list_N2W.get(myself).y1=Main.list_N2W.get(myself-1).y1-(car_height+10);
		   	   		        		}
		   	   		        		else if(Main.list_N2W.get(myself).y1>this.safety_line){
		   	   		        			Main.list_N2W.get(myself).velocity=Main.list_N2W.get(myself-1).velocity;
		   	   		        			Main.list_N2W.get(myself-1).velocity+=10;//这里之所前面的速度加10是因为防止转完后出现车辆的重叠，因为转弯完后是没有进行车距的判断的
		   	   		        		}
		   	   		        	 }
		   	        		  } //for循环
//**************************************判断车间距****************************************************
	            		}
	            		else {
	            			 this.turn_right=true;
			        		 this.x1-=this.velocity;
			        		 if(this.x1<=this.x2) {
			        			 Main.list_N2W.remove(this);
			        			 Main.list_all.remove(this);
			        			 this.isAlive=false;
			        		 }
			        		
			        	 }//if语句
				 }//while循环
				
			break;
			case "E2S"://东左转南 
				 while(this.y1<=this.y2) {
		        	 try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
		        	 Main.map.repaint();
		        	 if(this.x1>=this.safety_line-20) {
		        	 isrun=true;
         			 this.x1-=this.velocity;
//**************************************判断左转****************************************************
					    this.turn_left=false;
//**************************************判断左转****************************************************
//##############################################判断红绿灯#################################################
		 			 /*****************第一阶段：在this.safety_line~this.safety_line+60之间开始减速*********************/ 
         			 while(isrun) {
	        		 if((MyCanvas.light_E_right.get_status()==Light.light_red||MyCanvas.light_E_right.get_status()==Light.light_yellow)&&(this.x1<=this.safety_line+90&&this.x1>this.safety_line)) {
	        		 		this.velocity=5;
	        		  }
	        		  /****************第二阶段：在this.safety_line~this.safety_line+10之间停止***********************/
     				  if((MyCanvas.light_E_right.get_status()==Light.light_red)&&(this.x1>this.safety_line&&this.x1<this.safety_line+60)) {
     					  	this.velocity=0;
     				  
     				  } 
     				 /****************第三阶段：在小于等于this.safety_line时恢复初始速度***********************/	
	        		  if((this.x1<=this.safety_line)) {
	        			  	this.velocity=original_V;
	        		  }
	        		  isrun=false;
	        		  }//while循环
//##############################################判断红绿灯#################################################
//**************************************判断车间距****************************************************
	        		  for(int i=0;i<Main.list_E2S.size();i++) {
	   		        	 if((Main.list_E2S.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_E2S.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((Main.list_E2S.get(myself-1).x1>=500)&&(Main.list_E2S.get(myself).x1-(Main.list_E2S.get(myself-1).x1+Car.car_height)<=20)) {
	   		        			Main.list_E2S.get(myself).velocity=Main.list_E2S.get(myself-1).velocity;
	   		        			Main.list_E2S.get(myself).x1=Main.list_E2S.get(myself-1).x1+(car_height+10);
	   		        		}
	   		        		else if(Main.list_E2S.get(myself).x1<500){
	   		        			Main.list_E2S.get(myself).velocity=Main.list_E2S.get(myself-1).velocity;
	   	   		        		Main.list_E2S.get(myself-1).velocity+=10;//这里之所前面的速度加10是因为防止转完后出现车辆的重叠，因为转弯完后是没有进行车距的判断的

	   		        		}
	   		        	 }
	        		  } //for循环
//**************************************判断车间距****************************************************
		        	 }
		        	 else {
		        		 
		        		 this.turn_left=true;
//########################转弯时控制角度的变化########################################	
		        		 if(this.x1>510) {
		        			 this.rotation_angle+=5;
		        			 this.x1-=this.velocity;
		        		 }else if(this.x1<=510) {
		        			 this.rotation_angle=90;
		        		 }
//########################转弯时控制角度的变化######################################## 
		        		 this.y1+=this.velocity;
		        		 if(this.y1>this.y2) {
		        			 Main.list_E2S.remove(this);
		        			 Main.list_all.remove(this);
		        			 this.isAlive=false;
		        		 }
		        	 }//if语句
	
		       }//while语句	
			break;
			case "E2W"://东直走西;
				while(this.x1>=this.x2) {
	            	try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            		Main.map.repaint();
	            		isrun=true;
	            		this.x1-=this.velocity;
//##############################################判断红绿灯#################################################
			 			 /*****************第一阶段：在this.safety_line~this.safety_line+60之间开始减速*********************/ 
	         			 while(isrun) {
		        		 if((MyCanvas.light_E_middle.get_status()==Light.light_red||MyCanvas.light_E_middle.get_status()==Light.light_yellow)&&(this.x1<=this.safety_line+90&&this.x1>this.safety_line)) {
		        		 		this.velocity=5;
		        		  }
		        		  /****************第二阶段：在this.safety_line~this.safety_line+10之间停止***********************/
	     				  if((MyCanvas.light_E_middle.get_status()==Light.light_red)&&(this.x1>this.safety_line&&this.x1<this.safety_line+60)) {
	     					  	this.velocity=0;
	     				  
	     				  } 
	     				 /****************第三阶段：在小于等于this.safety_line时恢复初始速度***********************/	
		        		  if((this.x1<=this.safety_line)) {
		        			  	this.velocity=original_V;
		        		  }
		        		  isrun=false;
		        		  }//while循环
//##############################################判断红绿灯#################################################
//**************************************判断车间距****************************************************
	        		  for(int i=0;i<Main.list_E2W.size();i++) {
	   		        	 if((Main.list_E2W.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_E2W.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((this.x1>=this.x2)&&(Main.list_E2W.get(myself).x1-(Main.list_E2W.get(myself-1).x1+Car.car_height)<=20)) {
	   		        			Main.list_E2W.get(myself).velocity=Main.list_E2W.get(myself-1).velocity;
	   		        			Main.list_E2W.get(myself).x1=Main.list_E2W.get(myself-1).x1+(car_height+10);
	   		        		}

	   		        	 }
	        		  } //for循环
//**************************************判断车间距****************************************************
	            if(this.x1<this.x2) {
	            		Main.list_E2W.remove(this);
	            		Main.list_all.remove(this);
	            		this.isAlive=false;
	            }//if判断结束
	            }//while循环结束
			break;
			case "E2N"://东右转北
				while(this.y1>=this.y2) {
            		try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
            		Main.map.repaint();
            		if(this.x1>=700) {
            			this.x1-=this.velocity;
//**************************************判断右转****************************************************
					    this.turn_right=false;
//**************************************判断右转****************************************************
//**************************************判断车间距****************************************************
  	        		  for(int i=0;i<Main.list_E2N.size();i++) {
  	   		        	 if((Main.list_E2N.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_E2N.get(i).hashCode())) {
  	   		        		 myself=i;
  	   		        		if((Main.list_E2N.get(myself-1).x1>=700)&&(Main.list_E2N.get(myself).x1-(Main.list_E2N.get(myself-1).x1+Car.car_height)<=20)) {
  	   		        			Main.list_E2N.get(myself).velocity=Main.list_E2N.get(myself-1).velocity;
  	   		        			Main.list_E2N.get(myself).x1=Main.list_E2N.get(myself-1).x1+(car_height+10);
  	   		        		}
  	   		        		else if(Main.list_E2N.get(myself).x1<700){
  	   		        			Main.list_E2N.get(myself).velocity=Main.list_E2N.get(myself-1).velocity;
  	   		        			Main.list_E2N.get(myself-1).velocity+=10;//这里之所前面的速度加10是因为防止转完后出现车辆的重叠，因为转弯完后是没有进行车距的判断的
  	   		        		}
  	   		        	 }
  	        		  } //for循环
//**************************************判断车间距****************************************************
            		}
            		else {
		        		 //右旋转90度，未写
            			 this.turn_right=true;
		        		 this.y1-=this.velocity;
		        		if(this.y1<this.y2) {
		        			 Main.list_E2N.remove(this);
		        			 Main.list_all.remove(this);
		        			 this.isAlive=false;
		        		 }
		        	 }//if语句
			 }//while循环
			
			break;
			case "W2N"://西左转北 ;
				 while(this.y1>=this.y2) {
		        	 try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
		        	 Main.map.repaint();
		        	 if(this.x1<=this.safety_line+20) {
		        	 isrun=true;
         			 this.x1+=this.velocity;
//**************************************判断右转****************************************************
					    this.turn_left=false;
//**************************************判断右转****************************************************
//##############################################判断红绿灯#################################################
		 			 /*****************第一阶段：在this.safety_line~this.safety_line+60之间开始减速*********************/ 
         			 while(isrun) {
	        		  if((MyCanvas.light_W_left.get_status()==Light.light_red||MyCanvas.light_W_left.get_status()==Light.light_yellow)&&(this.x1>=this.safety_line-130&&this.x1<this.safety_line)) {
	        		 		this.velocity=5;
	        		  }
	        		  /****************第二阶段：在this.safety_line~this.safety_line+10之间停止***********************/
     				  if((MyCanvas.light_W_left.get_status()==Light.light_red)&&(this.x1<this.safety_line&&this.x1>=this.safety_line-100)) {
     					  	this.velocity=0;
     				  
     				  } 
     				 /****************第三阶段：在小于等于this.safety_line时恢复初始速度***********************/	
	        		  if((this.x1>=this.safety_line)) {
	        			  	this.velocity=original_V;
	        		  }
	        		  isrun=false;
	        		  } //while循环 
//##############################################判断红绿灯#################################################
//**************************************判断车间距****************************************************
	        		  for(int i=0;i<Main.list_W2N.size();i++) {
	   		        	 if((Main.list_W2N.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_W2N.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((Main.list_W2N.get(myself-1).x1<=530)&&(Main.list_W2N.get(myself-1).x1-(Main.list_W2N.get(myself).x1+Car.car_height)<=20)) {
	   		        			Main.list_W2N.get(myself).velocity=Main.list_W2N.get(myself-1).velocity;
	   		        			Main.list_W2N.get(myself).x1=Main.list_W2N.get(myself-1).x1-(car_height+10);
	   		        		}
	   		        		else if(Main.list_W2N.get(myself).x1>530){
	   		        			Main.list_W2N.get(myself).velocity=Main.list_W2N.get(myself-1).velocity;
	   	   		        		Main.list_W2N.get(myself-1).velocity+=10;//这里之所前面的速度加10是因为防止转完后出现车辆的重叠，因为转弯完后是没有进行车距的判断的

	   		        		}
	   		        	 }
	        		  } //for循环
//**************************************判断车间距****************************************************
		        	 }
		        	 else {
		        		 
		        		 this.turn_left=true;
//########################转弯时控制角度的变化########################################	
		        		 if(this.x1<560) {
		        			 this.rotation_angle+=5;
		        			 this.x1+=this.velocity;
		        		 }else if(this.x1>=560) {
		        			 this.rotation_angle=90;
		        		 }
//########################转弯时控制角度的变化######################################## 
		        		 this.y1-=this.velocity;
		        		 if(this.y1<this.y2) {
		        			 //Main.list_W2N.remove(this);
		        			 Main.list_all.remove(this);
		        			 this.isAlive=false;
		        		 }
		        	 }//if语句
		       }//while语句	
			break;
			case "W2E"://西直走东;
				while(this.x1<=this.x2) {
	            	try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            	Main.map.repaint();
	            	isrun=true;
	            	this.x1+=this.velocity;
//##############################################判断红绿灯#################################################
		 			 /*****************第一阶段：在this.safety_line~this.safety_line+60之间开始减速*********************/ 
        			 while(isrun) {
	        		  if((MyCanvas.light_W_middle.get_status()==Light.light_red||MyCanvas.light_W_middle.get_status()==Light.light_yellow)&&(this.x1>=this.safety_line-130&&this.x1<this.safety_line)) {
	        		 		this.velocity=5;
	        		  }
	        		  /****************第二阶段：在this.safety_line~this.safety_line+10之间停止***********************/
    				  if((MyCanvas.light_W_middle.get_status()==Light.light_red)&&(this.x1<this.safety_line&&this.x1>=this.safety_line-100)) {
    					  	this.velocity=0;
    				  } 
    				 /****************第三阶段：在小于等于this.safety_line时恢复初始速度***********************/	
	        		  if((this.x1>=this.safety_line)) {
	        			  	this.velocity=original_V;
	        		  }
	        		  isrun=false;
	        		  } //while循环 
//##############################################判断红绿灯#################################################
//**************************************判断车间距****************************************************
	        		  for(int i=0;i<Main.list_W2E.size();i++) {
	   		        	 if((Main.list_W2E.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_W2E.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((this.x1<=this.x2)&&(Main.list_W2E.get(myself-1).x1-(Main.list_W2E.get(myself).x1+Car.car_height)<=20)) {
	   		        			Main.list_W2E.get(myself).velocity=Main.list_W2E.get(myself-1).velocity;
	   		        			Main.list_W2E.get(myself).x1=Main.list_W2E.get(myself-1).x1-(car_height+10);
	   		        		}

	   		        	 }
	        		  } //for循环
//**************************************判断车间距****************************************************
	            if(this.x1>this.x2) {
	            	Main.list_W2E.remove(this);
	            	Main.list_all.remove(this);
	            	this.isAlive=false;
	            }//if判断结束
	            }//while循环结束
			break;
			case "W2S"://西右转南;
				while(this.y1<=this.y2) {
            		try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
            		Main.map.repaint();
            		if(this.x1<=425) {
            			this.x1+=this.velocity;
//**************************************判断右转****************************************************
    				    this.turn_right=false;
//**************************************判断右转****************************************************
//**************************************判断车间距****************************************************
    	        		  for(int i=0;i<Main.list_W2S.size();i++) {
    	   		        	 if((Main.list_W2S.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_W2S.get(i).hashCode())) {
    	   		        		 myself=i;
    	   		        		if((Main.list_W2S.get(myself-1).x1<=425)&&(Main.list_W2S.get(myself-1).x1-(Main.list_W2S.get(myself).x1+Car.car_height)<=20)) {
    	   		        			Main.list_W2S.get(myself).velocity=Main.list_W2S.get(myself-1).velocity;
    	   		        			Main.list_W2S.get(myself).x1=Main.list_W2S.get(myself-1).x1-(car_height+10);
    	   		        		}
    	   		        		else if(Main.list_W2S.get(myself).x1>425){
    	   		        			Main.list_W2S.get(myself).velocity=Main.list_W2S.get(myself-1).velocity;
    	   		        			Main.list_W2S.get(myself-1).velocity+=10;//这里之所前面的速度加10是因为防止转完后出现车辆的重叠，因为转弯完后是没有进行车距的判断的
    	   		        		}
    	   		        	 }
    	        		  } //for循环
//**************************************判断车间距****************************************************
            		}
            		else {
		        		 //右旋转90度，未写
            			 this.turn_right=true;
		        		 this.y1+=this.velocity;
		        		 if(this.y1>this.y2) {
		        			 Main.list_W2S.remove(this);
		        			 Main.list_all.remove(this);
		        		 this.isAlive=false;
		        		 }
		        	 }//if语句
			 }//while循环
			break;
			}//switch语句
			
		}//while循环
		
	}//run方法
}

 
