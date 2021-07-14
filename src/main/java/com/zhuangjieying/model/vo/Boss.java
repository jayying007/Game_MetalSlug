package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.manager.BossMoveType;
import com.zhuangjieying.model.manager.ElementManager;

public class Boss extends SuperElement{
	private int i=0;//����ͼƬ����˳��
	private BossMoveType moveType;
	private Map<String, List<ImageIcon>> actMap;
	private List<ImageIcon> tempList;//��ʱ�洢����������������ͼƬ
	private int imgWidth=0,imgHeight=0;//��ǰ���ŵ�ͼƬ�Ŀ�͸�
	private int anger;//ŭ��ֵ�������Ϳ�ǹ
	private int preImgWidth;
	private int preImgHeight;
	private int hp;
	private boolean bossDie;
	public Boss(int x,int y,int w,int h,Map<String, List<ImageIcon>> map) {
		super(x,y,w,h);
		this.moveType=BossMoveType.stop;
		this.actMap=map;
		this.hp=5;
		this.bossDie=false;
	}
	
	public static Boss createBoss(String str,Map<String, List<ImageIcon>> map){
//		System.out.println(str);
		String [] arr=str.split(",");
		int x = Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		int w = Integer.parseInt(arr[2])*7/4;
		int h = Integer.parseInt(arr[3])*7/4;
		return new Boss(x, y, w, h,map);
	}
	
	@Override
	public void showElement(Graphics g) {
//		System.out.println("boss hp is:"+hp);
		// TODO Auto-generated method stub
		if(anger<30&&!bossDie) {
			anger++;
//			System.out.println("anger is "+anger);
			setMoveType(BossMoveType.stop);
		}
		switch (moveType) {
		case stop:
			tempList=actMap.get("bossAttack");
			i=0;
			imgWidth=tempList.get(i).getIconWidth()*7/4;
			imgHeight=tempList.get(i).getIconHeight()*7/4;
			g.drawImage(tempList.get(0).getImage(), getX(), getY(), imgWidth, imgHeight, null);
			break;
		case attack:
			tempList=actMap.get("bossAttack");
			i=i%(tempList.size());
			imgWidth=tempList.get(i).getIconWidth()*7/4;
			imgHeight=tempList.get(i).getIconHeight()*7/4;
			switch (i) {
			case 0:
				g.drawImage(tempList.get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
				break;
			case 1:
				g.drawImage(tempList.get(i).getImage(), getX(), getY()+2, imgWidth, imgHeight, null);
				break;
			case 2:
				g.drawImage(tempList.get(i).getImage(), getX(), getY()-8, imgWidth, imgHeight, null);
				break;
			case 3:
				g.drawImage(tempList.get(i).getImage(), getX(), getY()+20, imgWidth, imgHeight, null);
				break;
			case 4:
				addFire();
				g.drawImage(tempList.get(i).getImage(), getX(), getY()+6, imgWidth, imgHeight, null);
				anger=0;
				break;
			default:
				break;
			}
			break;
		case die://Ҫ���л�״̬�ĵط���i��Ϊ0
			if(i<9) {
				g.drawString(String.valueOf(i), getX(), getY());
				imgWidth=actMap.get("bossAttack").get(0).getIconWidth()*7/4;
				imgHeight=actMap.get("bossAttack").get(0).getIconHeight()*7/4;
				g.drawImage(actMap.get("bossAttack").get(0).getImage(), getX(), getY(), imgWidth, imgHeight, null);
				switch (i) {
				case 0:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 1:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 2:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 3:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 4:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 5:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 6:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 7:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 8:
					preImgWidth=imgWidth;
					preImgHeight=imgHeight;
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				default:
					break;
				}
			}
			else if (i>=9 && i<17) {
				g.drawString(String.valueOf(i), getX(), getY());
				imgWidth=actMap.get("bossCrash").get(0).getIconWidth()*7/4;
				imgHeight=actMap.get("bossCrash").get(0).getIconHeight()*7/4;
				g.drawImage(actMap.get("bossCrash").get(0).getImage(), getX()+60, getY()+190, imgWidth, imgHeight, null);
				switch (i) {
				case 9:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), preImgWidth, preImgHeight, null);
					break;
				case 10:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), preImgWidth, preImgHeight, null);
					break;
				case 11:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), preImgWidth, preImgHeight, null);
					break;
				case 12:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), preImgWidth, preImgHeight, null);
					break;
				case 13:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), preImgWidth, preImgHeight, null);
					break;
				case 14:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), preImgWidth, preImgHeight, null);
					break;
				case 15:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), preImgWidth, preImgHeight, null);
					break;
				case 16:
					g.drawImage(actMap.get("bossBoom").get(i).getImage(), getX(), getY(), preImgWidth, preImgHeight, null);
					break;
				}
			}
			else {
				g.drawString(String.valueOf(i), getX(), getY());
				imgWidth=actMap.get("bossCrash").get(0).getIconWidth()*7/4;
				imgHeight=actMap.get("bossCrash").get(0).getIconHeight()*7/4;
				g.drawImage(actMap.get("bossCrash").get(0).getImage(), getX()+60, getY()+190, imgWidth, imgHeight, null);
				
				this.setVisible(false);
			}
			break;
		}
		i++;
	}

	@Override
	public void move() {
		if(this.getHp()==0&&!bossDie) {
			bossDie=true;
			this.setMoveType(BossMoveType.die);
			this.setI(0);
		}
	}

	public void addFire(){
		List<SuperElement> list=ElementManager.getManager().getElementList("enemyFire");
		switch (moveType) {
		case stop:
			break;
		case attack:
			list.add(EnemyBullet.createEnemyBullet(getX(), getY(), "bossBullet"));
			break;
		default:
			break;
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public BossMoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(BossMoveType moveType) {
		this.moveType = moveType;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getAnger() {
		return anger;
	}

	public void setAnger(int anger) {
		this.anger = anger;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
}
