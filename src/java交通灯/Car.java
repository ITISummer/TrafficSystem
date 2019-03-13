package java��ͨ��;
/**�Գ��������ܻ����Ƶ���*/
public class Car implements Runnable{
/**********************************��Ա������***************************************************/
	int x1,y1;//������ʼλ��
	int x2,y2;//�����յ�λ��
	int velocity;//�����ٶ�
	int random_R;//·�������
	int random_V;//�ٶ������
	int safety_line;//�����Ⱥ��̵�ʱ�İ�ȫ�� 
	boolean turn_left;
	boolean turn_right;//ע��boolean���͵ı����ĳ�ʼֵ��false
	private boolean isAlive=true;
	String car_img;//������ͼƬ
	String road;//��·����ѡ��
	int rotation_angle;//ת�䳵������ת�Ƕ�
/**********************************��Ա������***************************************************/
	
/**********************************��(����)������***********************************************/
	static  final int car_width=30;//���Ŀ��
	static  final int car_height=70;//���ĸ߶�
	private static  int car_U=0;//��
	private static  int car_D=1;//��
	private static  int car_L=2;//��
	private static  int car_R=3;//��
	private static  final int time=100;//С��ˢ�µ�ʱ��
	static int car_velocity[]= {10,15,20};//�����ٶ�
	/**����ͼƬ·�����飬0-�ϣ�1-�£�2-��3-��*/
	private static String img[][]= {{"src/ͼƬ/S2N.png","src/ͼƬ/S2N2.png"},{"src/ͼƬ/N2S.png","src/ͼƬ/N2S2.png"},{"src/ͼƬ/E2W.png","src/ͼƬ/E2W2.png"},{"src/ͼƬ/W2E.png","src/ͼƬ/W2E2.png"}};
	/**·������*/
	private static String car_on_road[]= {"S2W","S2N","S2E","N2E","N2S","N2W","E2S","E2W","E2N","W2N","W2E","W2S"};
/**********************************��(����)������************************************************/
	
	/**���췽���������ɳ��������Եķ������е���*/
	public Car(){
		create_car();//�������ɳ����Եķ���
}//���췽������
	/**@���ɳ������Եķ���
	 * @1������ͼƬ
	 * @2��������ʼλ�ú��յ�λ��*/
	public void create_car() {
		//ÿ��5*Car.time�������һ����
		try {Thread.sleep((int)(15*Car.time));}catch(Throwable e) {e.printStackTrace();}
		//���������
		this.random_R=(int) (Math.random()*12);//������ķ�Χ��0~���Ե����ֵ�ǰ�պ�����
		this.random_V=(int) (Math.random()*3);
		this.road=Car.car_on_road[this.random_R];//�����ڵ�·��ѡ��  
		this.velocity=Car.car_velocity[this.random_V];//�����ٶȵ�ѡ��
		Main.list_all.add(this);//ʹ�ü���list_all������Ա��ں�������ȫ�����Ķ���
		switch(this.road) {
		case "S2W"://����ת��
			this.car_img=img[Car.car_U][(int) (Math.random()*2)];
			x1=565;y1=950;x2=-50;y2=430;
			this.safety_line=600;//������ʻ��ȫ��Ϊy1=600;
			Main.list_S2W.add(this);
			break;
		case "S2N"://��ֱ�߱�
			this.car_img=img[Car.car_U][(int) (Math.random()*2)];
			x1=610;y1=950;x2=620;y2=-50;
			this.safety_line=600;//������ʻ��ȫ��Ϊy1=600;
			Main.list_S2N.add(this);
			 break;
		case "S2E"://����ת��
			this.car_img=img[Car.car_U][(int) (Math.random()*2)];
			x1=660;y1=950;x2=1250;y2=430;
			this.safety_line=600;//������ʻ��ȫ��Ϊy1=600;
			Main.list_S2E.add(this);
			 break;
		case "N2E"://����ת��
			this.car_img=img[Car.car_D][(int) (Math.random()*2)];
			x1=505;y1=-50;x2=1250;y2=470;
			this.safety_line=300;//������ʻ��ȫ��Ϊy1=300;
			Main.list_N2E.add(this);
			 break;
		case "N2S"://��ֱ����
			this.car_img=img[Car.car_D][(int) (Math.random()*2)];
			x1=460;y1=-50;x2=480;y2=950;
			this.safety_line=300;//������ʻ��ȫ��Ϊy1=300;
			Main.list_N2S.add(this);
			 break;
		case "N2W"://����ת��
			this.car_img=img[Car.car_D][(int) (Math.random()*2)];
			x1=410;y1=-50;x2=-50;y2=330;
			this.safety_line=300;//������ʻ��ȫ��Ϊy1=300;
			Main.list_N2W.add(this);
			 break;
		case "E2S"://����ת��
			this.car_img=img[Car.car_L][(int) (Math.random()*2)];
			x1=1250;y1=405;x2=520;y2=950;
			this.safety_line=700;//������ʻ��ȫ��Ϊx1=700
			Main.list_E2S.add(this);
			 break;
		case "E2W"://��ֱ����
			this.car_img=img[Car.car_L][(int) (Math.random()*2)];
			x1=1250;y1=360;x2=-50;y2=400;	
			this.safety_line=700;//������ʻ��ȫ��Ϊx1=700
			Main.list_E2W.add(this);
			 break;
		case "E2N"://����ת��
			this.car_img=img[Car.car_L][(int) (Math.random()*2)];
			x1=1250;y1=310;x2=670;y2=-50;
			this.safety_line=700;//������ʻ��ȫ��Ϊx1=700
			Main.list_E2N.add(this);
			 break;
		case "W2N"://����ת��
			this.car_img=img[Car.car_R][(int) (Math.random()*2)];
			x1=-50;y1=465;x2=570;y2=-50;
			this.safety_line=400;//������ʻ��ȫ��Ϊx1=400
			Main.list_W2N.add(this);
			 break;
		case "W2E"://��ֱ�߶�
			this.car_img=img[Car.car_R][(int) (Math.random()*2)];
			x1=-50;y1=510;x2=1250;y2=550;	
			this.safety_line=400;//������ʻ��ȫ��Ϊx1=400
			Main.list_W2E.add(this);
			 break;
		case "W2S"://����ת��
			this.car_img=img[Car.car_R][(int) (Math.random()*2)];
			x1=-50;y1=560;x2=600;y2=950;
			this.safety_line=400;//������ʻ��ȫ��Ϊx1=400
			Main.list_W2S.add(this);
			 break;
		}//switch������
}//���ɳ����Եķ�������
	@Override
	public void run() {
		while(this.isAlive) {
/*******************************�ֲ�������***********************************************/
			int original_V=this.velocity;//�ñ����������ʼ�ٶ�
			//֮��������ֲ���������ΪҪ�������this.velocityʵ������ĵõ���ֵ
			int myself;//����һ������������this(�Լ�)�ڼ����е�λ��
			boolean isrun;//����һ��boolean��������Ϊ�Ƿ��жϵ�״̬
			//�������������Ŀ�����Ǳ����ڳ���ֹͣ��ʱ������жϵƵ���ѭ�����Ӷ�ʹ����CPU�Ĺ���
/*******************************�ֲ�������***********************************************/	
			switch(this.road) {
			case "S2W"://����ת��
				while(this.x1>=this.x2) {
							try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
							Main.map.repaint();
						if(this.y1>=this.safety_line-20) {
								isrun=true;
								this.y1-=this.velocity;
//**************************************�ж���ת****************************************************
							    this.turn_left=false;
//**************************************�ж���ת****************************************************
		        		 		 
//##############################################�жϺ��̵�#################################################
		    		 			 /*****************��һ�׶Σ���this.safety_line~this.safety_line+60֮�俪ʼ����*********************/ 
		        		 		while(isrun) {
				        		 if((MyCanvas.light_S_left.get_status()==Light.light_red||MyCanvas.light_S_left.get_status()==Light.light_yellow)&&(this.y1<=this.safety_line+50&&this.y1>this.safety_line)) {
				        			 this.velocity=5;
				        		 }
				        		  /****************�ڶ��׶Σ���this.safety_line~this.safety_line+10֮��ֹͣ***********************/
		         				  if((MyCanvas.light_S_left.get_status()==Light.light_red)&&(this.y1<=this.safety_line+30&&this.y1>this.safety_line)) {
		         					  	this.velocity=0;
		         				  } 
		         				 /****************�����׶Σ���С�ڵ���this.safety_lineʱ�ָ���ʼ�ٶ�***********************/	
				        		  if(this.y1<=this.safety_line) {
				        			  	this.velocity=original_V;
				        		  }
		         				 isrun=false;
				        		  }//whileѭ�� 
//##############################################�жϺ��̵�#################################################
//**************************************�жϳ����****************************************************
				        		  for(int i=0;i<Main.list_S2W.size();i++) {
				   		        	 if((Main.list_S2W.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_S2W.get(i).hashCode())) {
				   		        		 myself=i;//�ҵ��Լ��ڼ����е�λ��
				   		        		if((Main.list_S2W.get(myself-1).y1>=this.safety_line-160)&&(Main.list_S2W.get(myself).y1-(Main.list_S2W.get(myself-1).y1+Car.car_height)<=15)) {//
				   		        			Main.list_S2W.get(myself).velocity=Main.list_S2W.get(myself-1).velocity;
				   		        			Main.list_S2W.get(myself).y1=Main.list_S2W.get(myself-1).y1+car_height+10;
				   		        		}
				   		        		else if(Main.list_S2W.get(myself).y1<this.safety_line-160){
				   		        			Main.list_S2W.get(myself).velocity=Main.list_S2W.get(myself-1).velocity;
				   	   		        		Main.list_S2W.get(myself-1).velocity+=10;//����֮��ǰ����ٶȼ�10����Ϊ��ֹת�����ֳ������ص�����Ϊת�������û�н��г�����жϵ�
				   		        		}
				   		        	 }
				        		  } //forѭ��
//**************************************�жϳ����****************************************************
		        	 }
		        	 else {
		        		 	this.turn_left=true;
//########################ת��ʱ���ƽǶȵı仯########################################	
		        		 if(this.y1>this.safety_line-160) {//y=440
		        			 this.rotation_angle+=7;//�Ƕ�����
		        			 this.y1-=this.velocity;
		        		 }else if(this.y1<=this.safety_line-160) {
		        			 this.rotation_angle=90;
		        		 }
//########################ת��ʱ���ƽǶȵı仯######################################## 
		        		 this.x1-=this.velocity;
		        		 if(this.x1<this.x2) {
		        			 Main.list_S2W.remove(this);
		        			 Main.list_all.remove(this);
		        			 isAlive=false;
		        		 }
		        	 }//if���
	
				}//while��� 
				
			break; 
			case "S2N"://��ֱ�߱�; 
				while(this.y1>=this.y2) {
	            	try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            	Main.map.repaint();
	            	isrun=true;
	            	this.y1-=this.velocity;
//##############################################�жϺ��̵�#################################################
		 			 /*****************��һ�׶Σ���this.safety_line~this.safety_line+60֮�俪ʼ����*********************/ 
   		 		while(isrun) {
	        		 if((MyCanvas.light_S_middle.get_status()==Light.light_red||MyCanvas.light_S_middle.get_status()==Light.light_yellow)&&(this.y1<=this.safety_line+50&&this.y1>this.safety_line)) {
	        			 this.velocity=5;
	        		 }
	        		  /****************�ڶ��׶Σ���this.safety_line~this.safety_line+10֮��ֹͣ***********************/
    				  if((MyCanvas.light_S_middle.get_status()==Light.light_red)&&(this.y1<=this.safety_line+30&&this.y1>this.safety_line)) {
    					  	this.velocity=0;
    				  } 
    				 /****************�����׶Σ���С�ڵ���this.safety_lineʱ�ָ���ʼ�ٶ�***********************/	
	        		  if(this.y1<=this.safety_line) {
	        			  	this.velocity=original_V;
	        		  }
    				 isrun=false;
	        		  }//whileѭ�� 
//##############################################�жϺ��̵�#################################################
//**************************************�жϳ����****************************************************
	        		  for(int i=0;i<Main.list_S2N.size();i++) {
	   		        	 if((Main.list_S2N.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_S2N.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((this.y1>=this.y2)&&(Main.list_S2N.get(myself).y1-(Main.list_S2N.get(myself-1).y1+Car.car_height)<=20)) {
	   		        			Main.list_S2N.get(myself).velocity=Main.list_S2N.get(myself-1).velocity;
	   		        			Main.list_S2N.get(myself).y1=Main.list_S2N.get(myself-1).y1+car_height+10;
	   		        		}

	   		        	 }
	        		  } //forѭ��
//**************************************�жϳ����****************************************************
	        		  if(this.y1<this.y2){
	        			  Main.list_S2N.remove(this);
	        			  Main.list_all.remove(this);
	        			  this.isAlive=false;
	            }//if�жϽ���
	            }//whileѭ������
			break;
			case "S2E"://����ת��
				 while(this.x1<=this.x2) {
	            		try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            		Main.map.repaint();
	            		if(this.y1>=570) {
	            			this.y1-=this.velocity;
//**************************************�ж���ת****************************************************
						    this.turn_right=false;
//**************************************�ж���ת****************************************************
//**************************************�жϳ����****************************************************
	   	        		  for(int i=0;i<Main.list_S2E.size();i++) {
	   	   		        	 if((Main.list_S2E.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_S2E.get(i).hashCode())) {
	   	   		        		 myself=i;
	   	   		        		if((Main.list_S2E.get(myself-1).y1>=570)&&(Main.list_S2E.get(myself).y1-(Main.list_S2E.get(myself-1).y1+Car.car_height)<=20)) {
	   	   		        		Main.list_S2E.get(myself).y1=Main.list_S2E.get(myself-1).y1+car_height+10;
	   	   		        		}
	   	   		        		else if(Main.list_S2E.get(myself).y1<570){
	   	   		        		Main.list_S2E.get(myself).velocity=Main.list_S2E.get(myself-1).velocity;
	   	   		        		Main.list_S2E.get(myself-1).velocity+=10;//����֮��ǰ����ٶȼ�10����Ϊ��ֹת�����ֳ������ص�����Ϊת�������û�н��г�����жϵ�
	   	   		        		}
	   	   		        	 }
	   	        		  } //forѭ��
//**************************************�жϳ����****************************************************
	            		}
	            		else {
	            			//����ת90�ȣ�δд
	            			this.turn_right=true;
			        		 this.x1+=this.velocity;
			        		 if(this.x1>=this.x2) {
			        			 Main.list_S2E.remove(this);
			        			 Main.list_all.remove(this);
			        			 this.isAlive=false;
			        		 }
			        	
			        	 }//if���
				 }//whileѭ��
				
			break;
			case "N2E"://����ת��;
				 while(this.x1<=this.x2) {
		        	 try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
		        	 	Main.map.repaint();
		        if(this.y1<=this.safety_line) {
		        	 isrun=true;
         			 this.y1+=this.velocity;
//**************************************�ж���ת****************************************************
					    this.turn_left=false;
//**************************************�ж���ת****************************************************
//##############################################�жϺ��̵�#################################################
		 			 /*****************��һ�׶Σ���this.safety_line~this.safety_line+60֮�俪ʼ����*********************/ 
         			 while(isrun) {
	        		  if((MyCanvas.light_N_right.get_status()==Light.light_red||MyCanvas.light_N_right.get_status()==Light.light_yellow)&&(this.y1<this.safety_line&&this.y1>=this.safety_line-130)) {
	        		 		this.velocity=5;
         			 }
	        		  /****************�ڶ��׶Σ���this.safety_line~this.safety_line+10֮��ֹͣ***********************/
     				  if((MyCanvas.light_N_right.get_status()==Light.light_red)&&(this.y1<this.safety_line&&this.y1>=this.safety_line-100)) {
     					  	this.velocity=0;
     				  } 
     				 /****************�����׶Σ���С�ڵ���this.safety_lineʱ�ָ���ʼ�ٶ�***********************/	
	        		  if((this.y1>=this.safety_line)) {
	        			  	this.velocity=original_V;
	        		  }
	        		  isrun=false;
	        		  }//whileѭ��
//##############################################�жϺ��̵�#################################################
//**************************************�жϳ����****************************************************
	        		  for(int i=0;i<Main.list_N2E.size();i++) {
	   		        	 if((Main.list_N2E.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_N2E.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((Main.list_N2E.get(myself-1).y1<=this.safety_line+180)&&(Main.list_N2E.get(myself-1).y1-(Main.list_N2E.get(myself).y1+Car.car_height)<=20)) {
	   		        			Main.list_N2E.get(myself).velocity=Main.list_N2E.get(myself-1).velocity;
	   		        			Main.list_N2E.get(myself).y1=Main.list_N2E.get(myself-1).y1-(car_height+10);
	   		        		}
	   		        		else if(Main.list_N2E.get(myself).y1>this.safety_line+180){
	   		        			Main.list_N2E.get(myself).velocity=Main.list_N2E.get(myself-1).velocity;
	   	   		        		Main.list_N2E.get(myself-1).velocity+=10;//����֮��ǰ����ٶȼ�10����Ϊ��ֹת�����ֳ������ص�����Ϊת�������û�н��г�����жϵ�

	   		        		}
	   		        	 }
	        		  } //forѭ��
//**************************************�жϳ����****************************************************
		        	 }
		        	 else {
		        		 	this.turn_left=true;
//########################ת��ʱ���ƽǶȵı仯########################################	
			        		 if(this.y1<this.safety_line+180) {
			        			 this.rotation_angle+=5;
			        			 this.y1+=this.velocity;
			        		 }else if(this.y1>=this.safety_line+180) {
			        			 this.rotation_angle=90;
			        		 }
//########################ת��ʱ���ƽǶȵı仯######################################## 
		        		 	this.x1+=this.velocity;
		        		 if(this.x1>=this.x2) {
		        			 Main.list_N2E.remove(this);
		        			 Main.list_all.remove(this);
		        			 this.isAlive=false;
		        		 }
		        	 }//if���
	
		       }//while���	
			break;
			case "N2S"://��ֱ����;
				while(this.y1<=this.y2) {
	            	try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            		Main.map.repaint(); 
	            		isrun=true;
	            		this.y1+=this.velocity;
//##############################################�жϺ��̵�#################################################
			 			 /*****************��һ�׶Σ���this.safety_line~this.safety_line+60֮�俪ʼ����*********************/ 
	         			 while(isrun) {
		        		  if((MyCanvas.light_N_middle.get_status()==Light.light_red||MyCanvas.light_N_middle.get_status()==Light.light_yellow)&&(this.y1<this.safety_line&&this.y1>=this.safety_line-130)) {
		        		 		this.velocity=5;
	         			 }
		        		  /****************�ڶ��׶Σ���this.safety_line~this.safety_line+10֮��ֹͣ***********************/
	     				  if((MyCanvas.light_N_middle.get_status()==Light.light_red)&&(this.y1<this.safety_line&&this.y1>=this.safety_line-100)) {
	     					  	this.velocity=0;
	     				  } 
	     				 /****************�����׶Σ���С�ڵ���this.safety_lineʱ�ָ���ʼ�ٶ�***********************/	
		        		  if((this.y1>=this.safety_line)) {
		        			  	this.velocity=original_V;
		        		  }
		        		  isrun=false;
		        		  }//whileѭ��
//##############################################�жϺ��̵�#################################################
//**************************************�жϳ����****************************************************
	        		  for(int i=0;i<Main.list_N2S.size();i++) {
	   		        	 if((Main.list_N2S.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_N2S.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((this.y1<=this.y2)&&(Main.list_N2S.get(myself-1).y1-(Main.list_N2S.get(myself).y1+Car.car_height)<=20)) {
	   		        			Main.list_N2S.get(myself).velocity=Main.list_N2S.get(myself-1).velocity;
	   		        			Main.list_N2S.get(myself).y1=Main.list_N2S.get(myself-1).y1-(car_height+10);
	   		        		}

	   		        	 }
	        		  } //forѭ��
//**************************************�жϳ����****************************************************
	            if(this.y1>=this.y2) {
	            	Main.list_N2S.remove(this);
	            	Main.list_all.remove(this);
	            	this.isAlive=false;
	            }//if�жϽ���
	            }//whileѭ������
			break;
			case "N2W"://����ת��;
				 while(this.x1>=this.x2) {
	            		try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            		Main.map.repaint();
	            		if(this.y1<=this.safety_line) {
	            			this.y1+=this.velocity;
//**************************************�ж���ת****************************************************
						    this.turn_right=false;
//**************************************�ж���ת****************************************************
//**************************************�жϳ����****************************************************
		   	        		  for(int i=0;i<Main.list_N2W.size();i++) {
		   	   		        	 if((Main.list_N2W.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_N2W.get(i).hashCode())) {
		   	   		        		 myself=i;
		   	   		        		if((Main.list_N2W.get(myself-1).y1<=this.safety_line)&&(Main.list_N2W.get(myself-1).y1-(Main.list_N2W.get(myself).y1+Car.car_height)<=20)) {
		   	   		        			Main.list_N2W.get(myself).velocity=Main.list_N2W.get(myself-1).velocity;
		   	   		        			Main.list_N2W.get(myself).y1=Main.list_N2W.get(myself-1).y1-(car_height+10);
		   	   		        		}
		   	   		        		else if(Main.list_N2W.get(myself).y1>this.safety_line){
		   	   		        			Main.list_N2W.get(myself).velocity=Main.list_N2W.get(myself-1).velocity;
		   	   		        			Main.list_N2W.get(myself-1).velocity+=10;//����֮��ǰ����ٶȼ�10����Ϊ��ֹת�����ֳ������ص�����Ϊת�������û�н��г�����жϵ�
		   	   		        		}
		   	   		        	 }
		   	        		  } //forѭ��
//**************************************�жϳ����****************************************************
	            		}
	            		else {
	            			 this.turn_right=true;
			        		 this.x1-=this.velocity;
			        		 if(this.x1<=this.x2) {
			        			 Main.list_N2W.remove(this);
			        			 Main.list_all.remove(this);
			        			 this.isAlive=false;
			        		 }
			        		
			        	 }//if���
				 }//whileѭ��
				
			break;
			case "E2S"://����ת�� 
				 while(this.y1<=this.y2) {
		        	 try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
		        	 Main.map.repaint();
		        	 if(this.x1>=this.safety_line-20) {
		        	 isrun=true;
         			 this.x1-=this.velocity;
//**************************************�ж���ת****************************************************
					    this.turn_left=false;
//**************************************�ж���ת****************************************************
//##############################################�жϺ��̵�#################################################
		 			 /*****************��һ�׶Σ���this.safety_line~this.safety_line+60֮�俪ʼ����*********************/ 
         			 while(isrun) {
	        		 if((MyCanvas.light_E_right.get_status()==Light.light_red||MyCanvas.light_E_right.get_status()==Light.light_yellow)&&(this.x1<=this.safety_line+90&&this.x1>this.safety_line)) {
	        		 		this.velocity=5;
	        		  }
	        		  /****************�ڶ��׶Σ���this.safety_line~this.safety_line+10֮��ֹͣ***********************/
     				  if((MyCanvas.light_E_right.get_status()==Light.light_red)&&(this.x1>this.safety_line&&this.x1<this.safety_line+60)) {
     					  	this.velocity=0;
     				  
     				  } 
     				 /****************�����׶Σ���С�ڵ���this.safety_lineʱ�ָ���ʼ�ٶ�***********************/	
	        		  if((this.x1<=this.safety_line)) {
	        			  	this.velocity=original_V;
	        		  }
	        		  isrun=false;
	        		  }//whileѭ��
//##############################################�жϺ��̵�#################################################
//**************************************�жϳ����****************************************************
	        		  for(int i=0;i<Main.list_E2S.size();i++) {
	   		        	 if((Main.list_E2S.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_E2S.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((Main.list_E2S.get(myself-1).x1>=500)&&(Main.list_E2S.get(myself).x1-(Main.list_E2S.get(myself-1).x1+Car.car_height)<=20)) {
	   		        			Main.list_E2S.get(myself).velocity=Main.list_E2S.get(myself-1).velocity;
	   		        			Main.list_E2S.get(myself).x1=Main.list_E2S.get(myself-1).x1+(car_height+10);
	   		        		}
	   		        		else if(Main.list_E2S.get(myself).x1<500){
	   		        			Main.list_E2S.get(myself).velocity=Main.list_E2S.get(myself-1).velocity;
	   	   		        		Main.list_E2S.get(myself-1).velocity+=10;//����֮��ǰ����ٶȼ�10����Ϊ��ֹת�����ֳ������ص�����Ϊת�������û�н��г�����жϵ�

	   		        		}
	   		        	 }
	        		  } //forѭ��
//**************************************�жϳ����****************************************************
		        	 }
		        	 else {
		        		 
		        		 this.turn_left=true;
//########################ת��ʱ���ƽǶȵı仯########################################	
		        		 if(this.x1>510) {
		        			 this.rotation_angle+=5;
		        			 this.x1-=this.velocity;
		        		 }else if(this.x1<=510) {
		        			 this.rotation_angle=90;
		        		 }
//########################ת��ʱ���ƽǶȵı仯######################################## 
		        		 this.y1+=this.velocity;
		        		 if(this.y1>this.y2) {
		        			 Main.list_E2S.remove(this);
		        			 Main.list_all.remove(this);
		        			 this.isAlive=false;
		        		 }
		        	 }//if���
	
		       }//while���	
			break;
			case "E2W"://��ֱ����;
				while(this.x1>=this.x2) {
	            	try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            		Main.map.repaint();
	            		isrun=true;
	            		this.x1-=this.velocity;
//##############################################�жϺ��̵�#################################################
			 			 /*****************��һ�׶Σ���this.safety_line~this.safety_line+60֮�俪ʼ����*********************/ 
	         			 while(isrun) {
		        		 if((MyCanvas.light_E_middle.get_status()==Light.light_red||MyCanvas.light_E_middle.get_status()==Light.light_yellow)&&(this.x1<=this.safety_line+90&&this.x1>this.safety_line)) {
		        		 		this.velocity=5;
		        		  }
		        		  /****************�ڶ��׶Σ���this.safety_line~this.safety_line+10֮��ֹͣ***********************/
	     				  if((MyCanvas.light_E_middle.get_status()==Light.light_red)&&(this.x1>this.safety_line&&this.x1<this.safety_line+60)) {
	     					  	this.velocity=0;
	     				  
	     				  } 
	     				 /****************�����׶Σ���С�ڵ���this.safety_lineʱ�ָ���ʼ�ٶ�***********************/	
		        		  if((this.x1<=this.safety_line)) {
		        			  	this.velocity=original_V;
		        		  }
		        		  isrun=false;
		        		  }//whileѭ��
//##############################################�жϺ��̵�#################################################
//**************************************�жϳ����****************************************************
	        		  for(int i=0;i<Main.list_E2W.size();i++) {
	   		        	 if((Main.list_E2W.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_E2W.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((this.x1>=this.x2)&&(Main.list_E2W.get(myself).x1-(Main.list_E2W.get(myself-1).x1+Car.car_height)<=20)) {
	   		        			Main.list_E2W.get(myself).velocity=Main.list_E2W.get(myself-1).velocity;
	   		        			Main.list_E2W.get(myself).x1=Main.list_E2W.get(myself-1).x1+(car_height+10);
	   		        		}

	   		        	 }
	        		  } //forѭ��
//**************************************�жϳ����****************************************************
	            if(this.x1<this.x2) {
	            		Main.list_E2W.remove(this);
	            		Main.list_all.remove(this);
	            		this.isAlive=false;
	            }//if�жϽ���
	            }//whileѭ������
			break;
			case "E2N"://����ת��
				while(this.y1>=this.y2) {
            		try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
            		Main.map.repaint();
            		if(this.x1>=700) {
            			this.x1-=this.velocity;
//**************************************�ж���ת****************************************************
					    this.turn_right=false;
//**************************************�ж���ת****************************************************
//**************************************�жϳ����****************************************************
  	        		  for(int i=0;i<Main.list_E2N.size();i++) {
  	   		        	 if((Main.list_E2N.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_E2N.get(i).hashCode())) {
  	   		        		 myself=i;
  	   		        		if((Main.list_E2N.get(myself-1).x1>=700)&&(Main.list_E2N.get(myself).x1-(Main.list_E2N.get(myself-1).x1+Car.car_height)<=20)) {
  	   		        			Main.list_E2N.get(myself).velocity=Main.list_E2N.get(myself-1).velocity;
  	   		        			Main.list_E2N.get(myself).x1=Main.list_E2N.get(myself-1).x1+(car_height+10);
  	   		        		}
  	   		        		else if(Main.list_E2N.get(myself).x1<700){
  	   		        			Main.list_E2N.get(myself).velocity=Main.list_E2N.get(myself-1).velocity;
  	   		        			Main.list_E2N.get(myself-1).velocity+=10;//����֮��ǰ����ٶȼ�10����Ϊ��ֹת�����ֳ������ص�����Ϊת�������û�н��г�����жϵ�
  	   		        		}
  	   		        	 }
  	        		  } //forѭ��
//**************************************�жϳ����****************************************************
            		}
            		else {
		        		 //����ת90�ȣ�δд
            			 this.turn_right=true;
		        		 this.y1-=this.velocity;
		        		if(this.y1<this.y2) {
		        			 Main.list_E2N.remove(this);
		        			 Main.list_all.remove(this);
		        			 this.isAlive=false;
		        		 }
		        	 }//if���
			 }//whileѭ��
			
			break;
			case "W2N"://����ת�� ;
				 while(this.y1>=this.y2) {
		        	 try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
		        	 Main.map.repaint();
		        	 if(this.x1<=this.safety_line+20) {
		        	 isrun=true;
         			 this.x1+=this.velocity;
//**************************************�ж���ת****************************************************
					    this.turn_left=false;
//**************************************�ж���ת****************************************************
//##############################################�жϺ��̵�#################################################
		 			 /*****************��һ�׶Σ���this.safety_line~this.safety_line+60֮�俪ʼ����*********************/ 
         			 while(isrun) {
	        		  if((MyCanvas.light_W_left.get_status()==Light.light_red||MyCanvas.light_W_left.get_status()==Light.light_yellow)&&(this.x1>=this.safety_line-130&&this.x1<this.safety_line)) {
	        		 		this.velocity=5;
	        		  }
	        		  /****************�ڶ��׶Σ���this.safety_line~this.safety_line+10֮��ֹͣ***********************/
     				  if((MyCanvas.light_W_left.get_status()==Light.light_red)&&(this.x1<this.safety_line&&this.x1>=this.safety_line-100)) {
     					  	this.velocity=0;
     				  
     				  } 
     				 /****************�����׶Σ���С�ڵ���this.safety_lineʱ�ָ���ʼ�ٶ�***********************/	
	        		  if((this.x1>=this.safety_line)) {
	        			  	this.velocity=original_V;
	        		  }
	        		  isrun=false;
	        		  } //whileѭ�� 
//##############################################�жϺ��̵�#################################################
//**************************************�жϳ����****************************************************
	        		  for(int i=0;i<Main.list_W2N.size();i++) {
	   		        	 if((Main.list_W2N.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_W2N.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((Main.list_W2N.get(myself-1).x1<=530)&&(Main.list_W2N.get(myself-1).x1-(Main.list_W2N.get(myself).x1+Car.car_height)<=20)) {
	   		        			Main.list_W2N.get(myself).velocity=Main.list_W2N.get(myself-1).velocity;
	   		        			Main.list_W2N.get(myself).x1=Main.list_W2N.get(myself-1).x1-(car_height+10);
	   		        		}
	   		        		else if(Main.list_W2N.get(myself).x1>530){
	   		        			Main.list_W2N.get(myself).velocity=Main.list_W2N.get(myself-1).velocity;
	   	   		        		Main.list_W2N.get(myself-1).velocity+=10;//����֮��ǰ����ٶȼ�10����Ϊ��ֹת�����ֳ������ص�����Ϊת�������û�н��г�����жϵ�

	   		        		}
	   		        	 }
	        		  } //forѭ��
//**************************************�жϳ����****************************************************
		        	 }
		        	 else {
		        		 
		        		 this.turn_left=true;
//########################ת��ʱ���ƽǶȵı仯########################################	
		        		 if(this.x1<560) {
		        			 this.rotation_angle+=5;
		        			 this.x1+=this.velocity;
		        		 }else if(this.x1>=560) {
		        			 this.rotation_angle=90;
		        		 }
//########################ת��ʱ���ƽǶȵı仯######################################## 
		        		 this.y1-=this.velocity;
		        		 if(this.y1<this.y2) {
		        			 //Main.list_W2N.remove(this);
		        			 Main.list_all.remove(this);
		        			 this.isAlive=false;
		        		 }
		        	 }//if���
		       }//while���	
			break;
			case "W2E"://��ֱ�߶�;
				while(this.x1<=this.x2) {
	            	try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
	            	Main.map.repaint();
	            	isrun=true;
	            	this.x1+=this.velocity;
//##############################################�жϺ��̵�#################################################
		 			 /*****************��һ�׶Σ���this.safety_line~this.safety_line+60֮�俪ʼ����*********************/ 
        			 while(isrun) {
	        		  if((MyCanvas.light_W_middle.get_status()==Light.light_red||MyCanvas.light_W_middle.get_status()==Light.light_yellow)&&(this.x1>=this.safety_line-130&&this.x1<this.safety_line)) {
	        		 		this.velocity=5;
	        		  }
	        		  /****************�ڶ��׶Σ���this.safety_line~this.safety_line+10֮��ֹͣ***********************/
    				  if((MyCanvas.light_W_middle.get_status()==Light.light_red)&&(this.x1<this.safety_line&&this.x1>=this.safety_line-100)) {
    					  	this.velocity=0;
    				  } 
    				 /****************�����׶Σ���С�ڵ���this.safety_lineʱ�ָ���ʼ�ٶ�***********************/	
	        		  if((this.x1>=this.safety_line)) {
	        			  	this.velocity=original_V;
	        		  }
	        		  isrun=false;
	        		  } //whileѭ�� 
//##############################################�жϺ��̵�#################################################
//**************************************�жϳ����****************************************************
	        		  for(int i=0;i<Main.list_W2E.size();i++) {
	   		        	 if((Main.list_W2E.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_W2E.get(i).hashCode())) {
	   		        		 myself=i;
	   		        		if((this.x1<=this.x2)&&(Main.list_W2E.get(myself-1).x1-(Main.list_W2E.get(myself).x1+Car.car_height)<=20)) {
	   		        			Main.list_W2E.get(myself).velocity=Main.list_W2E.get(myself-1).velocity;
	   		        			Main.list_W2E.get(myself).x1=Main.list_W2E.get(myself-1).x1-(car_height+10);
	   		        		}

	   		        	 }
	        		  } //forѭ��
//**************************************�жϳ����****************************************************
	            if(this.x1>this.x2) {
	            	Main.list_W2E.remove(this);
	            	Main.list_all.remove(this);
	            	this.isAlive=false;
	            }//if�жϽ���
	            }//whileѭ������
			break;
			case "W2S"://����ת��;
				while(this.y1<=this.y2) {
            		try {Thread.sleep(Car.time);}catch(InterruptedException e) {e.printStackTrace();}
            		Main.map.repaint();
            		if(this.x1<=425) {
            			this.x1+=this.velocity;
//**************************************�ж���ת****************************************************
    				    this.turn_right=false;
//**************************************�ж���ת****************************************************
//**************************************�жϳ����****************************************************
    	        		  for(int i=0;i<Main.list_W2S.size();i++) {
    	   		        	 if((Main.list_W2S.size()>=2)&&(i!=0)&&(this.hashCode()==Main.list_W2S.get(i).hashCode())) {
    	   		        		 myself=i;
    	   		        		if((Main.list_W2S.get(myself-1).x1<=425)&&(Main.list_W2S.get(myself-1).x1-(Main.list_W2S.get(myself).x1+Car.car_height)<=20)) {
    	   		        			Main.list_W2S.get(myself).velocity=Main.list_W2S.get(myself-1).velocity;
    	   		        			Main.list_W2S.get(myself).x1=Main.list_W2S.get(myself-1).x1-(car_height+10);
    	   		        		}
    	   		        		else if(Main.list_W2S.get(myself).x1>425){
    	   		        			Main.list_W2S.get(myself).velocity=Main.list_W2S.get(myself-1).velocity;
    	   		        			Main.list_W2S.get(myself-1).velocity+=10;//����֮��ǰ����ٶȼ�10����Ϊ��ֹת�����ֳ������ص�����Ϊת�������û�н��г�����жϵ�
    	   		        		}
    	   		        	 }
    	        		  } //forѭ��
//**************************************�жϳ����****************************************************
            		}
            		else {
		        		 //����ת90�ȣ�δд
            			 this.turn_right=true;
		        		 this.y1+=this.velocity;
		        		 if(this.y1>this.y2) {
		        			 Main.list_W2S.remove(this);
		        			 Main.list_all.remove(this);
		        		 this.isAlive=false;
		        		 }
		        	 }//if���
			 }//whileѭ��
			break;
			}//switch���
			
		}//whileѭ��
		
	}//run����
}

 
