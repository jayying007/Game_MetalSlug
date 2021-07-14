package com.zhuangjieying.thread;

import com.zhuangjieying.frame.MyJPanel;
import com.zhuangjieying.main.GameStart;
import com.zhuangjieying.model.manager.BossMoveType;
import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.manager.EnemyMoveType;
import com.zhuangjieying.model.manager.GunType;
import com.zhuangjieying.model.vo.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;


public class GameThread extends Thread{
	//��ǰ�ؿ���
	public static int flag=0;
	
	
//	��ʱ����
	private MyJPanel jp = GameStart.getJp();
//	��ʱ����
	private int time;
//	��������� �� ˼��Ľ��� ����ͨ���ܶ����Ŀ����
//	�����Ŀ���࣬�� �ع�����Ŀ
	public void run(){
//		while(true){   //��Ϸ�������
	//		��ѭ���������б�����״̬�����п���
	//		1.���ص�ͼ������
			loadElement();
	//		2.��ʾ�����ͼ(����,�Զ���(�ƶ�����ײ))
			time=0;
			runGame();
	//		3.��������ͼ
			overGame();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
//			}
		}
	}
	private void runGame() {
		while(true){  //ÿ���������ʱ���״̬
			//�ж��������������
			List<SuperElement>playList= ElementManager.getManager().getElementList("player");
			if(playList==null||playList.size()==0)break;
			
			Map<String,List<SuperElement>> map=
					ElementManager.getManager().getMap();
			Set<String> set=map.keySet();
			
//			List<String> lists=new ArrayList<>();
//			lists.addAll(set);
//			for(int j=0;j<lists.size();j++){
//				String key=lists.get(j);
				
			for(String key:set){//�������ڱ����Ĺ����У��������ڵ�Ԫ�ز����� ���ӻ���ɾ��
				List<SuperElement> list=map.get(key);
//				for(int i=list.size()-1;i>=0;i--){
				for(int i=0;i<list.size();i++){
//					System.out.println(list.get(i).toString());
					list.get(i).update();
					if(!list.get(i).isVisible()){
						list.remove(i--);
					}
				}
				
			}
//			ʹ��һ�������ķ����������ж�
			Near();
	
			PK();
//			д�ɻ�����ӵ� list��map  //��Ϸ�����̿��� 
			linkGame();
			//������ͨ�ص� ���� runGame����
			try {
				sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isWin();
		}
	}
	private void isWin() {
		List<SuperElement> list2 = ElementManager.getManager().getElementList("boss");
		if(list2.size()==0) {
			String apologize = "��ϲ! �������Boss���Ƿ����һ�¹أ�";
	    	int go_on = JOptionPane.showConfirmDialog(jp,apologize, "You Win��", JOptionPane.YES_NO_OPTION);
	    	if (go_on == JOptionPane.YES_OPTION) {
	    		Map<String,List<SuperElement>> map=
						ElementManager.getManager().getMap();
				Set<String> set=map.keySet();
				for(String key:set){
					List<SuperElement> list1=map.get(key);	
					for(int i=0;i<list1.size();i++){			
							list1.remove(i--);
					}
					
				}
				flag++;
	            run();
	    	}
		}
	}
//	��ս����
	private synchronized void Near() {
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("player");
		List<SuperElement> list2=
				ElementManager.getManager().getElementList("enemy");
		for(int i=0;i<list1.size();i++) {
			for(int j=0;j<list2.size();j++) {
				if(list1.get(i).gamePK(list2.get(j))) {
					Player player=(Player)list1.get(i);
					player.setClose(true);
//					System.out.println("Close");
//					System.out.println(player.isPk());
					if(player.isPk()) {
						Enemy enemy = (Enemy) list2.get(j);
						if(enemy.getMoveType()!= EnemyMoveType.die) {
							enemy.setMoveType(EnemyMoveType.die);
							
							Player.getPlayer().setScore(Player.getPlayer().getScore()+100);
						}
						
					}
				}
			}
		}
		Boolean noClose=true;
		for(int i=0;i<list1.size();i++) {
			for(int j=0;j<list2.size();j++) {
				if(list1.get(i).gamePK(list2.get(j))) {
					noClose=false;
				}
			}
		}
		if(noClose) {
			if(list1!=null&&list1.size()!=0) {
				Player player=(Player)list1.get(0);
				player.setClose(false);
			}
			
		}
	}
	
	private void PK() {
		List<SuperElement> list1=
				ElementManager.getManager().getElementList("playFire");
		List<SuperElement> list2=
				ElementManager.getManager().getElementList("enemy");
		List<SuperElement> list3=
				ElementManager.getManager().getElementList("player");
		List<SuperElement> list4=
				ElementManager.getManager().getElementList("enemyFire");
		List<SuperElement> list5=
				ElementManager.getManager().getElementList("hostage");
		List<SuperElement> list6=
				ElementManager.getManager().getElementList("gift");
		List<SuperElement> list7=
				ElementManager.getManager().getElementList("boss");
		//���Ǵ���� //Ĭ���ӵ��б����ڵ�һ������
		if(list1!=null&&list2!=null&&list1.size()!=0&&list2.size()!=0) {
			enemyListPK(list1,list2);
		}
		if(list1!=null&&list7!=null&&list1.size()!=0&&list7.size()!=0) {
			enemyListPK(list1, list7);
		}
//		//���˴�����
		if(list3!=null&&list4!=null&&list3.size()!=0&&list4.size()!=0) {
			playerListPK(list4,list3);
		}
		//���Ǿ�����
		if(list1!=null&&list5!=null&&list1.size()!=0&&list5.size()!=0) {
			playerSaveHostage(list1,list5);
		}
		//���Ǽ񲹸�
		if(list3!=null&&list6!=null&&list3.size()!=0&&list6.size()!=0) {
			playerGetGift(list3,list6);
		}
	}
	//���Ǿ�����
	public void playerSaveHostage(List<SuperElement> list1,
			List<SuperElement> list2){
		
		for(int i=0;i<list1.size();i++) {
			for(int j=0;j<list2.size();j++) {
				if(list1.get(i).gamePK(list2.get(j))) {
					Hostage hostage=(Hostage) list2.get(j);
					if(!hostage.isCollision()) {
						list1.get(i).setVisible(false);
						//��ȡ������
						hostage.setCollision(true);
					}
				}
			}
		}
		
	}
	//���Ǽ񲹸�
	public void playerGetGift(List<SuperElement> list1,
			List<SuperElement> list2){
		for(int i=0;i<list1.size();i++) {
			for(int j=0;j<list2.size();j++) {
				if(list1.get(i).gamePK(list2.get(j))) {
					//��ȡ������
					Gift gift=(Gift)list2.get(j);
					gift.setVisible(false);
					Player play=(Player)list1.get(i);
					play.setGunType(GunType.gun2);
				}
			}
		}
	}
	/// �жϵ����Ƿ��е�
	public void enemyListPK(List<SuperElement> list1,
			List<SuperElement> list2){
		
		for(int i=0;i<list1.size();i++) {
			for(int j=0;j<list2.size();j++) {
				if(list1.get(i).gamePK(list2.get(j))) {
					if(list2.get(j) instanceof Enemy){
						Enemy enemy = (Enemy) list2.get(j);
						if(enemy.getMoveType()!=EnemyMoveType.die) {
							list1.get(i).setVisible(false);
							enemy.setMoveType(EnemyMoveType.die);
							
							Player.getPlayer().setScore(Player.getPlayer().getScore()+100);
						}
					}
					else if(list2.get(j) instanceof Boss) {
						Boss boss = (Boss) list2.get(j);
						
						list1.get(i).setVisible(false);
						boss.setHp(boss.getHp()-1);
//						if(boss.getMoveType()!=BossMoveType.die) {
//							
//							boss.setMoveType(BossMoveType.die);
//							
							Player.getPlayer().setScore(Player.getPlayer().getScore()+100);
//						}
					}
				}
			}
		}
	}
	//�ж������Ƿ��е�
	public void playerListPK(List<SuperElement> list1,
			List<SuperElement> list2){
		
		for(int i=0;i<list1.size();i++) {
			for(int j=0;j<list2.size();j++) {
				if(list1.get(i).gamePK(list2.get(j))) {
					List<SuperElement> bossList=ElementManager.getManager().getElementList("boss");
					if(bossList==null||bossList.size()==0)return;
					Boss boss=(Boss)bossList.get(0);
					if(boss.getMoveType()== BossMoveType.die)continue;
					
					list1.get(i).setVisible(false);
					Player player = (Player) list2.get(j);
					player.setDie(true);
				}
			}
		}
	}
	
	
	//��Ϸ�����̿��� 
	public void linkGame(){
		ElementManager.getManager().linkGame(time);
	}
	
	private void overGame() {
		String apologize = "��ϧ! �Ƿ�������Ϸ��";
    	int go_on = JOptionPane.showConfirmDialog(jp,apologize, "GAME OVER��", JOptionPane.YES_NO_OPTION);
    	if (go_on == JOptionPane.YES_OPTION) {
    		Map<String,List<SuperElement>> map=
					ElementManager.getManager().getMap();
			Set<String> set=map.keySet();
			for(String key:set){
				List<SuperElement> list1=map.get(key);	
				for(int i=0;i<list1.size();i++){			
						list1.remove(i--);
				}
				
			}
            run();
    	}
//		System.out.println("Game Over");
	}
//	���ƽ���,���ǣ���Ϊ ���ƣ��벻Ҫ�Ӵ� load
	private void loadElement() {
		ElementManager.getManager().load();
		
	}
	public static int getFlag() {
		return flag;
	}
}
