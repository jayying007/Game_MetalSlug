package com.zhuangjieying.model.load;

import com.zhuangjieying.thread.GameThread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;



public class ElementLoad {
	private final String RESOURCE_IMG_PREFIX = "src/main/resources/";
	private final String RESOURCE_PRO_PREFIX = "pro/";
	//
	private Map<String, ImageIcon> map;
	private Map<String,List<ImageIcon>> playerImgMap;
	private Map<String,List<String>> playerMap;	
	private Map<String, String> playerBulletMap;
	//
	private Map<String, List<ImageIcon>>bombMap;
	//
	private Map<String,List<String>> hostageMap;
	private Map<String, List<String >>hostageMoveListMap;
	private List<String> hostageMoveList;
	private Map<String, String> giftMap;  //���ʸ��Ľ�Ʒ
	File[] arr;
	//
	private Map<String,List<ImageIcon>> enemyMap;//ע��������ElementManager������Ƕ���
	private Map<String, ImageIcon> bulletMap;//�����ӵ�
	private Map<String, String> enemyLocationMap;//�����������˺�boss������λ���Լ���ʼ��С
	private Map<String, List<ImageIcon> > bossMap;
	private Map<String, String> bossLocationMap;
	private static ElementLoad load;
	//	pro�ļ���ȡ����
	private Properties pro;
	
	private ElementLoad(){
		//
		map=new HashMap<String, ImageIcon>();
		playerImgMap=new HashMap<>();
		playerMap=new HashMap<>();
		pro=new Properties();
		playerBulletMap=new HashMap<>();
		//
		bombMap=new HashMap<String, List<ImageIcon>>();
		//
		hostageMap=new HashMap<>();
		hostageMoveListMap=new HashMap<String, List<String>>();
		giftMap = new HashMap<String, String>();
		//
		enemyMap = new HashMap<>();
		bulletMap = new HashMap<>();
		enemyLocationMap = new HashMap<>();
		bossMap = new HashMap<>();
		bossLocationMap = new HashMap<>();
	}
	public static synchronized ElementLoad getElementLoad(){
		if(load==null){
			load=new ElementLoad();
		}
		return load;
	}
	
//	��ȡ��������
	public void readPlayPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream(RESOURCE_PRO_PREFIX + "player.pro");
		try {
			pro.clear();
			pro.load(in);
			
			for(Object o:pro.keySet()){
				List<String> list=new ArrayList<>();
				String str=pro.getProperty(o.toString());
				list.add(str);
				//����Ϊlist������
				playerMap.put(o.toString(), list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	��ȡ����ͼƬ
	public void readPlayerImgPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream(RESOURCE_PRO_PREFIX + "playerImg.pro");
		try {
			pro.clear();//���
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				File f=new File(RESOURCE_IMG_PREFIX + url);
				File [] arr=f.listFiles();
//					System.out.println(Arrays.toString(arr));
				List<ImageIcon> list=new ArrayList<ImageIcon>();
				for(File file:arr) {
					list.add(new ImageIcon(file.toString()));
				}
				playerImgMap.put(o.toString(), list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(playerImgMap);
//		System.out.println(playerImgMap.keySet());
	}
//	��ȡ����ӵ�ͼƬ
	public void readPlayerBulletPro(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream(RESOURCE_PRO_PREFIX + "bullet.pro");
		try {
			pro.clear();//���
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String str=pro.getProperty(o.toString());

				playerBulletMap.put(o.toString(), str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	��ȡ����Ԫ��ͼƬ
	public void readImgPro(){
		if(GameThread.getFlag()==0) {
			InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream("pro/map1.pro");
			try {
				pro.clear();//���
				pro.load(in);
				Set<?> set=pro.keySet();
				for(Object o:set){
					String url=pro.getProperty(o.toString());
					map.put(o.toString(), new ImageIcon(RESOURCE_IMG_PREFIX + url));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(GameThread.getFlag()==1) {
			InputStream in=ElementLoad.class.getClassLoader()
					.getResourceAsStream(RESOURCE_PRO_PREFIX + "map2.pro");
			try {
				pro.clear();//���
				pro.load(in);
				Set<?> set=pro.keySet();
				for(Object o:set){
					String url=pro.getProperty(o.toString());
					map.put(o.toString(), new ImageIcon(RESOURCE_IMG_PREFIX + url));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
//		System.out.println(map);
	}
//	ը��ͼƬ
	public void readImgProS(){
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream(RESOURCE_PRO_PREFIX + "mapB.pro");
		try {
			pro.clear();//���
			pro.load(in);
//			System.out.println(pro.keys());
//			System.out.println(pro.keySet());
			Set<?> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				File f=new File(RESOURCE_IMG_PREFIX + url);
//				if(f.isFile()){
//					System.out.println("�Ǹ��ļ�");
//				}else{
//					System.out.println("�Ǹ�·��");
//				}
				File [] arr=f.listFiles();
				List<ImageIcon> list = new ArrayList<>();
				for (File file : arr) {
					list.add(new ImageIcon(file.toString()));
				}
				bombMap.put(o.toString(), list);
//				System.out.println(bombMap);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	����ͼƬ
	public void readHostageMove1(){       //�����е�ͼƬ���ص�һ��list����
		InputStream in=ElementLoad.class.getClassLoader()
			.getResourceAsStream(RESOURCE_PRO_PREFIX + "HostageImg1.pro");
		try {
			pro.clear();//���
			pro.load(in);
			Set<?> set=pro.keySet();
//			System.out.println("set:::::"+set);
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				File f=new File(RESOURCE_IMG_PREFIX + url);
//				if(f.isFile()){
//					System.out.println("�Ǹ��ļ�");
//				}else{
//					System.out.println("�Ǹ�·��");
//				}
				arr=f.listFiles();
//				System.out.println("arr:::"+arr[0]);
				hostageMoveList= new ArrayList<String>();
				for(Object object:arr) {
					String urlString=object.toString();
					hostageMoveList.add(urlString);
				}
//				System.out.println("hostageMoveList::::"+hostageMoveList);
				hostageMoveListMap.put(o.toString(), hostageMoveList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readHostagePro() {
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream(RESOURCE_PRO_PREFIX + "Hostage.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			List<String> list=new ArrayList<>();
			List<String> list2=new ArrayList<String>();
			for(Object o:pro.keySet()){
				String str=pro.getProperty(o.toString());
				list.add(str);
				hostageMap.put(o.toString(), list);
				String s=list.get(list.size()-1);//004=enemyA,enemyA,20,170,40,40,10000
				String[] stringArr=s.split(",");
//				System.out.println("elementLoad-->stringArr[0]-->���ʵ�һ��ͼ"+stringArr[0]);
				
//				String str2=playmap.get("oneHostage");
//				System.out.println(str2);
//				Set<String> keys = playmap.keySet() ;// �õ�ȫ����key
//				Iterator<String> iter = keys.iterator() ;
//				while(iter.hasNext()){
//					String str3 = iter.next() ;
//					System.out.print(str3 + "��") ;
//					System.out.println();
//				}
//				list2.add(e)
//				list.add(str);
//				playmap.put(o.toString(), list);
			}
		} catch (Exception e) {
			// TO
		}
	}
//	��ȡ���������ļ�
	public void readEnemyPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream(RESOURCE_PRO_PREFIX + "enemy.pro");
		pro.clear();
		try {
			pro.load(in);
			Set<?> set=pro.keySet();//�õ���
			for(Object o:set){
				String url=pro.getProperty(o.toString());//ͨ�����õ�ֵ
				File f=new File(RESOURCE_IMG_PREFIX + url);
				File [] arr=f.listFiles();
				//System.out.println(Arrays.toString(arr));
				List<ImageIcon> tempList=new ArrayList<>();
				for (File file : arr) {
					tempList.add(new ImageIcon(file.toString()));
				}
				enemyMap.put(o.toString(), tempList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readBulletPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream(RESOURCE_PRO_PREFIX + "bullet.pro");
		pro.clear();
		try {
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				bulletMap.put(o.toString(), new ImageIcon(RESOURCE_IMG_PREFIX + url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readEnemyLocationPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream(RESOURCE_PRO_PREFIX + "enemyLocation.pro");
		pro.clear();
		try {
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String value=pro.getProperty(o.toString());
				enemyLocationMap.put(o.toString(), value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readBossPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream(RESOURCE_PRO_PREFIX + "boss.pro");
		pro.clear();
		try {
			pro.load(in);
			Set<?> set=pro.keySet();//�õ���
			for(Object o:set){
				String url=pro.getProperty(o.toString());//ͨ�����õ�ֵ
				File f=new File(RESOURCE_IMG_PREFIX + url);
				File [] arr=f.listFiles();
				List<ImageIcon> tempList=new ArrayList<>();
				for (File file : arr) {
					tempList.add(new ImageIcon(file.toString()));
				}
				bossMap.put(o.toString(), tempList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readBossLocationPro(){
		InputStream in=ElementLoad.class.getClassLoader()
				.getResourceAsStream(RESOURCE_PRO_PREFIX + "bossLocation.pro");
		pro.clear();
		try {
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String value=pro.getProperty(o.toString());
				bossLocationMap.put(o.toString(), value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		getElementLoad().readPlayPro();
//		getElementLoad().readPlayerImgPro();
//		getElementLoad().readImgPro();
//		getElementLoad().readPlayerBulletPro();
	}
	
	public Map<String, List<ImageIcon>> getPlayerImgMap() {
		return playerImgMap;
	}
	public Map<String, List<String>> getPlayerMap() {
		return playerMap;
	}
	public Map<String, ImageIcon> getMap() {
		return map;
	}
	public Map<String, String> getPlayerBulletMap() {
		return playerBulletMap;
	}
	public Map<String, List<ImageIcon>> getBombMap() {
		return bombMap;
	}
	public File[] getArr() {
		return arr;
	}
	public Map<String, List<String>> getHostageMap() {
		return hostageMap;
	}
	public List<String> getHostageMoveList() {
		List<String> aList=(List<String>)hostageMoveList;
		return aList;
	}
	public Map<String, List<String>> getHostageMoveListMap() {
		return hostageMoveListMap;
	}
	
	public Map<String, List<ImageIcon>> getEnemyMap() {
		return enemyMap;
	}
	public void setEnemyMap(Map<String, List<ImageIcon>> enemyMap) {
		this.enemyMap = enemyMap;
	}
	public Map<String, ImageIcon> getBulletMap() {
		return bulletMap;
	}
	public void setBulletMap(Map<String, ImageIcon> bulletMap) {
		this.bulletMap = bulletMap;
	}
	public Map<String, String> getEnemyLocationMap() {
		return enemyLocationMap;
	}
	public Map<String, List<ImageIcon>> getBossMap() {
		return bossMap;
	}
	public void setBossMap(Map<String, List<ImageIcon>> bossMap) {
		this.bossMap = bossMap;
	}
	public Map<String, String> getBossLocationMap() {
		return bossLocationMap;
	}
}
