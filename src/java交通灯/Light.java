package java交通灯;
public class Light {
	/**灯颜色常量——黄色*/
	public static final int light_yellow=0;
	/**灯颜色常量——红色*/
	public static final int light_red=1;
	/**灯颜色常量——绿色*/
	public static final int light_green=2;
	/**存放灯图片的路径数组 0-黄，1-红，2-绿*/
	String light_img[]= {"src/图片/黄.png","src/图片/红.png","src/图片/绿.png"};
	/**灯在面板的位置*/
  	int x,y;
  	/**灯的状态，三种(0-黄，1-红，2-绿)*/
  	int status;
/**对内初始化时设置的灯的状态*/
public Light(int x,int y,int status) {
  		this.x=x;                
  		this.y=y;
  		this.status=status;
}
/**得到红绿灯图片*/
public String getphoto() {
	return this.light_img[status];
}//getphoto()结束
/**对外得到灯的状态*/
public int get_status() {
	return this.status;
}

}

