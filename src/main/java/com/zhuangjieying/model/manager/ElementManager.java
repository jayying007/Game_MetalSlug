package com.zhuangjieying.model.manager;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.vo.Boss;
import com.zhuangjieying.model.vo.Enemy;
import com.zhuangjieying.model.vo.Player;
import com.zhuangjieying.model.vo.SuperElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ElementManager {
//	����  NPCԪ�أ�����Ԫ�أ���������
	Map<String,List<SuperElement>> map;//�ô���ʲô������
//	private MoveType moveType;
	
//	��ʼ��
	protected void init(){
		map=new HashMap<>();
		List<SuperElement> list=new ArrayList<>();
		map.put("player", list);
		map.put("playFire", new ArrayList<>());
		
		map.put("background", new ArrayList<>());
		map.put("plane",new ArrayList<SuperElement>());
//		map.put("planeFire", new ArrayList<SuperElement>());
		map.put("explode", new ArrayList<SuperElement>());
		map.put("bombA", new ArrayList<>());
		//
		map.put("hostage", new ArrayList<>());
		map.put("gift", new ArrayList<>());
		//
		map.put("enemy", new ArrayList<>());
		map.put("enemyFire", new ArrayList<>());//��������Ŵ����������ӵ�����
		map.put("boss", new ArrayList<>());//�����boss
//		map.put("playFire", new ArrayList<>());
	}
//	�õ�һ�������� map����
	public Map<String, List<SuperElement>> getMap() {
		return map;
	}
//	�õ�һ��Ԫ�صļ���
	public List<SuperElement> getElementList(String key){
		return map.get(key);
	}
	
	
	
//	��������Ҫһ��Ψһ������
	private static ElementManager elementManager;
//	���췽��˽�л�����ֻ���ڱ����п��� new
	private ElementManager(){
		init();
	}
	static{//��̬������ ��������ص�ʱ��ͻ�ִ��
		if(elementManager ==null){
			elementManager=new ElementManager();
		}
	}
//	�ṩ���������ⲿ���ʵ�Ψһ���   synchronized �̱߳�����
	public static /*synchronized*/ ElementManager getManager(){
//		if(elementManager ==null){
//			elementManager=new ElementManager();
//		}
		return elementManager;
	}
//	������Ҫ����Դ
	public void load() {
		ElementLoad.getElementLoad().readImgPro();
		ElementLoad.getElementLoad().readPlayPro();
		ElementLoad.getElementLoad().readPlayerImgPro();
		ElementLoad.getElementLoad().readPlayerBulletPro();
		
		ElementLoad.getElementLoad().readImgProS();
		
		ElementLoad.getElementLoad().readHostageMove1();
		ElementLoad.getElementLoad().readHostagePro();
//		����һ�� ״̬�����������  ǰϦ����ǰ��Ĺ�����Ϣ��
//		......
//		System.out.println(map.get("player"));
		map.get("player").add(ElementFactory.elementFactory("onePlayer-1"));
		
		map.get("background").add(ElementFactory.elementFactory("background-1"));
		map.get("plane").add(ElementFactory.elementFactory("onePlane-1"));
		
		map.get("hostage").add(ElementFactory.elementFactory("oneHostage-1"));

		ElementLoad.getElementLoad().readEnemyPro();
		ElementLoad.getElementLoad().readEnemyLocationPro();
		ElementLoad.getElementLoad().readBossPro();
		ElementLoad.getElementLoad().readBossLocationPro();
		ElementLoad.getElementLoad().readBulletPro();
//		����һ�� ״̬�����������  ǰϦ����ǰ��Ĺ�����Ϣ��
//		......
		Map<String, String> enemyLocationMap = ElementLoad.getElementLoad().getEnemyLocationMap();
		Set<?> enemyLocationMapKeys = enemyLocationMap.keySet();
		for(Object o:enemyLocationMapKeys){
//			String value=enemyLocationMap.get(o);
//			System.out.println("In ElementManager,enemyLocationMap  Key is "+o.toString()+"  value is "+value);
			map.get("enemy").add(ElementFactory.elementFactory(o.toString()));
		}
		Map<String, String> bossLocationMap = ElementLoad.getElementLoad().getBossLocationMap();
		Set<?> bossLocationMapKeys = bossLocationMap.keySet();
		for(Object o:bossLocationMapKeys){
			String value=bossLocationMap.get(o);
			map.get("boss").add(ElementFactory.elementFactory(o.toString()));
		}
	
	}
//	��������   int time��Ϸ����ʱ��
	public void linkGame(int time) {//����˵�ж�ĳ�������Ƿ��Ѿ���ɣ��Ͻ�ͷע�ⱳ���ƶ��͵��˳��ֵĴ���
		if(Player.getPlayer().isDie())return;
		//�ж�����λ�ã����˳����ǿ�ǹ
		Player player=(Player) map.get("player").get(0);
		List<SuperElement>enemyList = map.get("enemy");
		for (SuperElement enemyElement : enemyList) {
			Enemy enemy = (Enemy)enemyElement;
			if(enemy.getMoveType()==EnemyMoveType.die)
				continue;
			if(Math.abs(player.getX()-enemy.getX())<=800) {//������˱�����
				//��һЩ����
				if(Math.abs(player.getX()-enemy.getX())>500) {//����̫Զ������Ҫ�ܽ�һ��
					if(player.getX()<=enemy.getX()) {//����ڵ������
						enemy.setTowardLeft(true);//�泯��
						enemy.setMoveType(EnemyMoveType.run);//�����ܽ�һ��
					}else {//����ڵ����ұ�
						enemy.setTowardLeft(false);
						enemy.setMoveType(EnemyMoveType.run);
					}
				}
				else {//���빥������
					if(player.getX()<=enemy.getX()) {//����ڵ������
						enemy.setTowardLeft(true);//���˳������
						if((enemy.getX()-player.getX())>=70) {//���ڵ���70������ǹ
							if(enemy.getPreviousMoveType()!=EnemyMoveType.attackGun1)
								enemy.setI(0);
							enemy.setMoveType(EnemyMoveType.attackGun1);
							enemy.setPreviousMoveType(EnemyMoveType.attackGun1);
						}
						else {//С��50������
							if(enemy.getPreviousMoveType()!=EnemyMoveType.attackKnife)
								enemy.setI(0);
							enemy.setMoveType(EnemyMoveType.attackKnife);
							enemy.setPreviousMoveType(EnemyMoveType.attackKnife);
						}
					}
					else {//����ڵ����ұ�
						enemy.setTowardLeft(false);//���˳������
						if(player.getX()-enemy.getX()>=90) {//����90������ǹ
							if(enemy.getPreviousMoveType()!=EnemyMoveType.attackGun2)
								enemy.setI(0);
							enemy.setMoveType(EnemyMoveType.attackGun2);
							enemy.setPreviousMoveType(EnemyMoveType.attackGun2);
						}
						else {//С��90�����ҵ�
							if(enemy.getPreviousMoveType()!=EnemyMoveType.attackKnife)
								enemy.setI(0);
							enemy.setMoveType(EnemyMoveType.attackKnife);
							enemy.setPreviousMoveType(EnemyMoveType.attackKnife);
						}
					}
				}
			}
			else {//�����������
				enemy.setMoveType(EnemyMoveType.stop);
			}
		}
		
		
		List<SuperElement> bossList = map.get("boss");
		for (SuperElement bossElement : bossList) {
			Boss boss = (Boss)bossElement;
			if(boss.getMoveType()==BossMoveType.die)
				continue;
			if(Math.abs(player.getX()-boss.getX())<=600) {//���boss������
				//��һЩ����
				boss.setMoveType(BossMoveType.attack);
			}
			else {//�����������
				boss.setMoveType(BossMoveType.stop);
			}
		}
	}
}
