package java��ͨ��;

import java.util.ArrayList;
import java.util.List;

public class Main {
/**********************************��(����)������************************************************/
	//����12�����ϣ������ֱ���ÿ����·�ϵĳ�������
		//����ܵĳ�����
		static List <Car>list_all=new ArrayList<Car>();
		//�ϱ�������·����
		static List <Car>list_S2W=new ArrayList<Car>();
		static List <Car>list_S2N=new ArrayList<Car>();
		static List <Car>list_S2E=new ArrayList<Car>();
		//����������·����
		static List <Car>list_N2E=new ArrayList<Car>();
		static List <Car>list_N2S=new ArrayList<Car>();
		static List <Car>list_N2W=new ArrayList<Car>();
		//����������·����
		static List <Car>list_E2S=new ArrayList<Car>();
		static List <Car>list_E2W=new ArrayList<Car>();
		static List <Car>list_E2N=new ArrayList<Car>();
		//����������·����
		static List <Car>list_W2N=new ArrayList<Car>();
		static List <Car>list_W2E=new ArrayList<Car>();
		static List <Car>list_W2S=new ArrayList<Car>();
		//ʵ����Map�ľ�̬����ֵʵ����һ�Σ����粻ʹ��static����ÿ����һ��������ͻ�ʵ����һ��Map����
		//������ʵ����Map��������Ϊ�����������Car���������Map��repaint();
		//��static�ؼ������εı���������ʵ���������ʱ�򿪱�һ��������ڴ棬ʵ������ÿ�����󶼹�������ڴ�
		static Map map=new Map();
/**********************************��(����)������************************************************/
	/**�������������ɳ������������п��ƣ�������ÿ�������߳�*/
	public static void main(String[] args) {
		for(int i=0;i<100;i++) {
	    Car car=new Car();
		new Thread(car).start();
		
	}
}//����������
}
