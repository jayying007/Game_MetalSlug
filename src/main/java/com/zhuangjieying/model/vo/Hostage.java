package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.manager.MoveType;


public class Hostage extends SuperElement{
	private boolean isTied ;   //������ʵ�֣��Ȳ��Կ����ܲ��ܰ��˼��ؽ���
	private static ImageIcon img;
	private MoveType moveType;//0 1 2 3
	private static File [] arr;
	private static int count=0;   //ͼƬ�±�ļ�����
	private List<String> hostageMoveList;
	private String [] addressStrings;
	private  boolean collision;  //���PK�ǹ���״̬���������ʵĻ�Ӧ���ò���
	private static int time=0;
//	private boolean  pk;//����״̬��Ĭ��Ϊ false
	
	public Hostage(int x,int y,int w,int z,ImageIcon img) {
		super(x,y,w,z);
		this.img=img;
		moveType=MoveType.stop;
		this.isTied = true;
		collision=false;
		time=0;
	}
	
	public static Hostage createHostage(String str) {
//		int x=0,y=0,w=100,z=100;
//		oneHostage=img/Hostage/oder0.png,100,100,40,40,10
		String [] arr=str.split(",");
//		System.out.println("hostage-->arr-->"+arr[0]+arr[1]+arr[2]+arr[3]+arr[4]);
		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=Integer.parseInt(arr[3]);
		int h=Integer.parseInt(arr[4]);

		
		img=new ImageIcon("src/main/resources/" + arr[0]);
		return new Hostage(x, y, w, h, img);
		
	}
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		Map<String, List<String>> hostageMoveListMap=
				ElementLoad.getElementLoad().getHostageMoveListMap();
		hostageMoveList=hostageMoveListMap.get("hostageimg");
//		System.out.println("hostage-->hostageMoveList-->"+hostageMoveList);
		String s=hostageMoveList.toString();
		String s2=s.substring(1, s.length()-1);
//		System.out.println("hostage-->hostageMoveList-->"+s2);

		String[] stringArr=s2.split(",");
		for(int i=0;i<stringArr.length;i++) {
			if(i!=0) {
				String tempString=stringArr[i].substring(1,stringArr[i].length());
				stringArr[i]=tempString;
//				System.out.println("hostage-->stringArr-->"+stringArr[i].toString());
			}
		}
		if(count>18&&isCollision()==false)count=0;
		if(count<=18&&isCollision()==false)   //���ʺ��Ƚ���ǰδ�����
		{
//			System.out.println("count<<<<<<<<<<<<<<<<<<<9");
			if(getTime()==2) {
				img=new ImageIcon("src/main/resources/" + stringArr[count++]);
				setTime(0);
				
			}
			else {
				img=new ImageIcon("src/main/resources/" + stringArr[count]);
				setTime(getTime()+1);
			}
//			System.out.println("hostage-->"+count);
		}
		else if(count<=18&&isCollision()==true)   //���ʺ��Ƚ���ǰ�����
		{
//			System.out.println("���ʺ��ȶ�����û����ͱ�����ˣ�����");
			if(getTime()==2) {
				count=19;  //�������ȣ�ֱ������
				img=new ImageIcon("src/main/resources/" + stringArr[count]);
			}
			else {
				img=new ImageIcon("src/main/resources/" + stringArr[count]);
				setTime(getTime()+1);
			}
//			System.out.println("hostage-->"+count);
		}
		else if(18<count&&isCollision()==false) //���ʺ�����ϻ�δ����� 
		{
			img=new ImageIcon("src/main/resources/" + stringArr[0]);
			setTime(getTime()+1);
			if(getTime()==2) {
//				setCollision(true);
				setTime(0);
			}
//			System.out.println("hostage-->"+count);
		}
		else if (18<count&&count<=24&&isCollision()==true)    //
		{
			if(getTime()==2) {
				img=new ImageIcon("src/main/resources/" + stringArr[count++]);
				setTime(0);
			}
			else {
				img=new ImageIcon("src/main/resources/" + stringArr[count]);
				setTime(getTime()+1);
			}
//			System.out.println("hostage-->"+count);
		}
		else if(24<count&&count<=29&&isCollision()==true) 
		{
			if(getTime()==2) {
				img=new ImageIcon("src/main/resources/" + stringArr[count++]);
				setTime(0);
				escape();
			}
			else {
				img=new ImageIcon("src/main/resources/" + stringArr[count]);
				setTime(getTime()+1);

			}
//			System.out.println("hostage-->"+count);
//			img=new ImageIcon("src/main/resources/" + stringArr[count++]);
//			escape();
		}
		else if(29<count&&count<=36&&isCollision()==true) {
			if(getTime()==2) {
				img=new ImageIcon("src/main/resources/" + stringArr[count++]);
				setTime(0);
				List<SuperElement> list=
						ElementManager.getManager().getElementList("gift");
				list.add(Gift.createGift(getX(), getY()+70));
				ElementManager.getManager().getMap().put("gift", list);
			}
			else {
				img=new ImageIcon("src/main/resources/" + stringArr[count]);
				setTime(getTime()+1);
			}
		}
//		else if (count==27&&isCollision()==true)   //52��ͼƬ
//		{
////			System.out.println("hostage-->count-->"+count);
//			img=new ImageIcon("src/main/resources/" + stringArr[27]);
//			System.out.println("hostage-->"+count);
//		}
		else if (36<count&&count<66&&isCollision()==true) {
			if(getTime()==1) {
				img=new ImageIcon("src/main/resources/" + stringArr[count++]);
				setTime(0);
				escape();
			}
			else {
				img=new ImageIcon("src/main/resources/" + stringArr[count]);
				setTime(getTime()+1);

			}
//			System.out.println("hostage-->"+count);
//			img=new ImageIcon("src/main/resources/" + stringArr[count++]);
//			escape();
		}
		
		g.drawImage(img.getImage(), 
				getX(), getY(), 
				getX()+getW(), getY()+getH(),
				0, 0,
				80, 80,
				null);		
	}
	public void escape() {
		setX(getX()-20);
//		System.out.println("hostage-->move-->������");
	}
	@Override
	public void move(){
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public static ImageIcon getImg() {
		return img;
	}

	public static void setImg(ImageIcon img) {
		Hostage.img = img;
	}

	public static File[] getArr() {
		return arr;
	}

	public static void setArr(File[] arr) {
		Hostage.arr = arr;
	}

	public boolean isTied() {
		return isTied;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		Hostage.time = time;
	}
	
	
	
}
