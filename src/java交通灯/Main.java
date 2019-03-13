package java交通灯;

import java.util.ArrayList;
import java.util.List;

public class Main {
/**********************************类(共享)变量区************************************************/
	//创建12个集合，用来分别存放每条道路上的车辆数量
		//添加总的车对象
		static List <Car>list_all=new ArrayList<Car>();
		//南边三条道路集合
		static List <Car>list_S2W=new ArrayList<Car>();
		static List <Car>list_S2N=new ArrayList<Car>();
		static List <Car>list_S2E=new ArrayList<Car>();
		//北边三条道路集合
		static List <Car>list_N2E=new ArrayList<Car>();
		static List <Car>list_N2S=new ArrayList<Car>();
		static List <Car>list_N2W=new ArrayList<Car>();
		//东边三条道路集合
		static List <Car>list_E2S=new ArrayList<Car>();
		static List <Car>list_E2W=new ArrayList<Car>();
		static List <Car>list_E2N=new ArrayList<Car>();
		//西边三条道路集合
		static List <Car>list_W2N=new ArrayList<Car>();
		static List <Car>list_W2E=new ArrayList<Car>();
		static List <Car>list_W2S=new ArrayList<Car>();
		//实例化Map的静态对象，值实例化一次，假如不使用static，则每生成一个车对象就会实例化一个Map对象
		//在这里实例化Map对象是因为方便后续的在Car类里面调用Map的repaint();
		//用static关键字修饰的变量，在类实例化对象的时候开辟一块独立的内存，实例化的每个对象都共享这块内存
		static Map map=new Map();
/**********************************类(共享)变量区************************************************/
	/**主方法，对生成车辆的数量进行控制，并启动每辆车的线程*/
	public static void main(String[] args) {
		for(int i=0;i<100;i++) {
	    Car car=new Car();
		new Thread(car).start();
		
	}
}//主方法结束
}
