package com.zhuangjieying.model.manager;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.vo.*;

import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import javax.swing.ImageIcon;


public class ElementFactory {
	public static SuperElement elementFactory(String name){
		String []arr=name.split("-");
		
		Map<String, List<String>> playerMap=
			ElementLoad.getElementLoad().getPlayerMap();
		Map<String, ImageIcon> map=
				ElementLoad.getElementLoad().getMap();
		//
		Map<String, List<String>> hostageMap=
				ElementLoad.getElementLoad().getHostageMap();   //��ȡ�����ʵĳ�ʼ��Ϣ��ͼƬ
		Map<String, String> enemyLocationMap=
				ElementLoad.getElementLoad().getEnemyLocationMap();
		Map<String, String>bossLocationMap=
				ElementLoad.getElementLoad().getBossLocationMap();
		Map<String, List<ImageIcon>>enemyMap=
				ElementLoad.getElementLoad().getEnemyMap();
		switch(arr[0]){
		case "onePlayer":
			List<String> list=playerMap.get(name);
//			playerTopRightGun1StandNofire,playerDownRightStand,80,250,100,80,80,330,50,40
			String s=list.get(0);
			Player player=Player.createPlayer(s);
			if(player instanceof Runnable){//player����ָ���ʵ����� �ǲ��� Runnable������
				new Thread((Runnable)player).start();
			}
			return player;
		case "background":
			ImageIcon img=map.get("background");
			return BackGroud.CreateBackGroud(img);
		case "onePlane":
			List<String> list2=playerMap.get(name);
			s=list2.get(0);
			return Plane.CreatePlane(s);
		case "oneHostage":

			List<String> tempList2=hostageMap.get(name);

			s=tempList2.get(0);//img/Hostage/oder0.png,100,100,40,40,10
			return Hostage.createHostage(s);  //����ط��ǲ���Ӧ�����߳���ʵ�֣���
		case "Enemy":
			String enemyInfo=enemyLocationMap.get(name);
			arr=enemyInfo.split(",");//simpleEnemy,100,100,50,75
			switch(arr[0]){
			case "simpleEnemy":
				return Enemy.createEnemy(enemyInfo.substring(arr[0].length()+1, enemyInfo.length()),enemyMap);
				//��һ��������������Ϣ�Լ�����ͼƬ�Ĵ�С
			
			}
		case "boss":
			String bossInfo=bossLocationMap.get(name);
			arr=bossInfo.split(",");//boss1,700,135,205,205
			switch(arr[0]){
			case "boss1":
//				System.out.println("����createEnemy���ַ�����"+str.substring(arr[0].length()+1, str.length()));
				return Boss.createBoss(bossInfo.substring(arr[0].length()+1,bossInfo.length()),ElementLoad.getElementLoad().getBossMap());
				//��һ��������������Ϣ�Լ�����ͼƬ�Ĵ�С
			
			}
			
		}
		
		
		return null;
	}
}
