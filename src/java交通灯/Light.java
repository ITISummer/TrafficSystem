package java��ͨ��;
public class Light {
	/**����ɫ����������ɫ*/
	public static final int light_yellow=0;
	/**����ɫ����������ɫ*/
	public static final int light_red=1;
	/**����ɫ����������ɫ*/
	public static final int light_green=2;
	/**��ŵ�ͼƬ��·������ 0-�ƣ�1-�죬2-��*/
	String light_img[]= {"src/ͼƬ/��.png","src/ͼƬ/��.png","src/ͼƬ/��.png"};
	/**��������λ��*/
  	int x,y;
  	/**�Ƶ�״̬������(0-�ƣ�1-�죬2-��)*/
  	int status;
/**���ڳ�ʼ��ʱ���õĵƵ�״̬*/
public Light(int x,int y,int status) {
  		this.x=x;                
  		this.y=y;
  		this.status=status;
}
/**�õ����̵�ͼƬ*/
public String getphoto() {
	return this.light_img[status];
}//getphoto()����
/**����õ��Ƶ�״̬*/
public int get_status() {
	return this.status;
}

}

