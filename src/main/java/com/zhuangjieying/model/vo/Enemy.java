package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.manager.EnemyMoveType;


public class Enemy extends SuperElement{

	private Map<String,List<ImageIcon>> actMap;//�������еĶ���������ͼƬ
	private EnemyMoveType moveType;//���˵�ǰ״̬
	private EnemyMoveType previousMoveType;//���˵���һ��״̬
	private boolean towardLeft;//�����Ƿ������
	private List<ImageIcon> tempList;//��ʱ�洢����������������ͼƬ
	private int i=0;//����ͼƬ����˳��
	private int imgWidth=0,imgHeight=0;//��ǰ���ŵ�ͼƬ�Ŀ�͸�
	private int anger;//ŭ��ֵ�������Ϳ�ǹ
	private int j=0;//������������״̬�Ķ�������ΪҪ����
	private int previousDieY=0;
	
	public Enemy(int x,int y,int w,int h,Map<String, List<ImageIcon>> map) {
		super(x,y,w,h);
		this.actMap=map;
		moveType=EnemyMoveType.stop;
		towardLeft=true;
		this.anger=30;//һ��ʼ��ŭ���ṥ��
		tempList=new ArrayList<>();
		previousDieY=y;
	}
	
	public static Enemy createEnemy(String str,Map<String, List<ImageIcon>>map){
//		System.out.println(str);
		String [] arr=str.split(",");
		int x = Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		int w = Integer.parseInt(arr[2]);
		int h = Integer.parseInt(arr[3]);
		Map<String, List<ImageIcon>>actMap=map;
		return new Enemy(x, y, w, h, actMap);
	}

	@Override
	public void showElement(Graphics g) {
		if(anger<30) {//���ŭ��ֵ�������ǾͲ�����
			anger++;
			if(moveType!=EnemyMoveType.die) {
				this.moveType=EnemyMoveType.stop;
			}
		}
		switch (moveType) {
		case run:
			if(towardLeft) {
				tempList=actMap.get("enemyLeftRun");
				i=i%(tempList.size());
				g.drawImage(tempList.get(i).getImage(), getX(), getY(), getW(), getH(), null);
				break;
			}else {
				tempList=actMap.get("enemyRightRun");
				i=i%(tempList.size());
				g.drawImage(tempList.get(i).getImage(), getX(), getY(), getW(), getH(), null);
				break;
			}
		case stop:
			if(towardLeft){
				tempList=actMap.get("enemyLeftStand");
				i=i%(tempList.size());
				g.drawImage(tempList.get(i).getImage(), getX(), getY(), getW(), getH(), null);
			}else {
				tempList=actMap.get("enemyRightStand");
				i=i%(tempList.size());
				g.drawImage(tempList.get(i).getImage(), getX(), getY(), getW(), getH(), null);
			}
			break;
		case attackKnife:
			if(towardLeft) {
				tempList=actMap.get("enemyLeftAttackKnife");
				i=i%(tempList.size());
				imgWidth=tempList.get(i).getIconWidth();
				imgHeight=tempList.get(i).getIconHeight();
//				System.out.println("i is "+i);
				switch (i) {
				case 0:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);
					break;
				case 1:
					g.drawImage(tempList.get(i).getImage(), getX()-10, getY()+10, imgWidth, imgHeight, null);
					break;
				case 2:
					g.drawImage(tempList.get(i).getImage(), getX()-40, getY()+10, imgWidth, imgHeight, null);
					break;
				case 3:
					g.drawImage(tempList.get(i).getImage(), getX()-60, getY()+10, imgWidth, imgHeight, null);
					break;
				case 4:
					g.drawImage(tempList.get(i).getImage(), getX()-55, getY()+2, imgWidth, imgHeight, null);
					anger=5;
					break;
				default:
					break;
				}
			}else {
				tempList=actMap.get("enemyRightAttackKnife");
				i=i%(tempList.size());
				imgWidth=tempList.get(i).getIconWidth();
				imgHeight=tempList.get(i).getIconHeight();
//				System.out.println("i is "+i);
				switch (i) {
				case 0:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);
					break;
				case 1:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);
					break;
				case 2:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);
					break;
				case 3:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);
					break;
				case 4:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()+2, imgWidth, imgHeight, null);
					anger=5;
					break;
				default:
					break;
				}
			}
			break;
		case attackGun1:
			if(towardLeft) {
				tempList=actMap.get("enemyLeftAttackGun1");
				i=i%(tempList.size());
				imgWidth=tempList.get(i).getIconWidth();
				imgHeight=tempList.get(i).getIconHeight();
				switch (i) {
				case 0:
					g.drawImage(tempList.get(i).getImage(), getX()-45, getY(), imgWidth, imgHeight, null);
					break;
				case 1:
					addFire();
					g.drawImage(tempList.get(i).getImage(), getX()-55, getY(), imgWidth, imgHeight, null);
					break;
				case 2:
					g.drawImage(tempList.get(i).getImage(), getX()-60, getY(), imgWidth, imgHeight, null);
					break;
				case 3:
					g.drawImage(tempList.get(i).getImage(), getX()-60, getY(), imgWidth, imgHeight, null);
					break;
				case 4:
					g.drawImage(tempList.get(i).getImage(), getX()-50, getY()-8, imgWidth, imgHeight, null);
					anger=0;
					break;
				default:
					break;
				}
			}else {
				tempList=actMap.get("enemyRightAttackGun1");
				i=i%(tempList.size());
				imgWidth=tempList.get(i).getIconWidth();
				imgHeight=tempList.get(i).getIconHeight();
				switch (i) {
				case 0:
					g.drawImage(tempList.get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 1:
					addFire();
					g.drawImage(tempList.get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 2:
					g.drawImage(tempList.get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 3:
					g.drawImage(tempList.get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 4:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()-8, imgWidth, imgHeight, null);
					anger=0;
					break;
				default:
					break;
				}
			}
			break;
		case attackGun2:
			if(towardLeft) {
				tempList=actMap.get("enemyLeftAttackGun2");
				i=i%(tempList.size());
				imgWidth=tempList.get(i).getIconWidth();
				imgHeight=tempList.get(i).getIconHeight();
				switch (i) {
				case 0:
					g.drawImage(tempList.get(i).getImage(), getX()-45, getY(), imgWidth, imgHeight, null);
					break;
				case 1:
					addFire();
					g.drawImage(tempList.get(i).getImage(), getX()-45, getY(), imgWidth, imgHeight, null);
					break;
				case 2:
					g.drawImage(tempList.get(i).getImage(), getX()-40, getY()-15, imgWidth, imgHeight, null);
					break;
				case 3:
					g.drawImage(tempList.get(i).getImage(), getX()-30, getY()-15, imgWidth, imgHeight, null);
					break;
				case 4:
					g.drawImage(tempList.get(i).getImage(), getX()-40, getY()-10, imgWidth, imgHeight, null);
					anger=0;
					break;
				default:
					break;
				}
			}else {
				tempList=actMap.get("enemyRightAttackGun2");
				i=i%(tempList.size());
				imgWidth=tempList.get(i).getIconWidth();
				imgHeight=tempList.get(i).getIconHeight();
				switch (i) {
				case 0:
					g.drawImage(tempList.get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 1:
					addFire();
					g.drawImage(tempList.get(i).getImage(), getX(), getY(), imgWidth, imgHeight, null);
					break;
				case 2:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()-15, imgWidth, imgHeight, null);
					break;
				case 3:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()-15, imgWidth, imgHeight, null);
					break;
				case 4:
					g.drawImage(tempList.get(i).getImage(), getX(), getY()-10, imgWidth, imgHeight, null);
					anger=0;
					break;
				default:
					break;
				}
			}
			break;
		case die:
			if(towardLeft) {
				tempList=actMap.get("enemyLeftDie1");
				imgWidth=tempList.get(j).getIconWidth();
				imgHeight=tempList.get(j).getIconHeight();
				switch (i) {
				case 0:
					j=0;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+10;
					break;
				case 2:
					j=1;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+10;
					break;
				case 4:
					j=2;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+15;
					break;
				case 6:
					j=3;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+20, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+25;
					break;
				case 8:
					j=4;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+30, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+40;
					break;
				case 14:
					setVisible(false);
					break;
				default:
					g.drawImage(tempList.get(j).getImage(), getX(), previousDieY, imgWidth, imgHeight, null);//���j*10���Գ�����
					break;
				}
			}
			else {
				tempList=actMap.get("enemyRightDie1");
				imgWidth=tempList.get(j).getIconWidth();
				imgHeight=tempList.get(j).getIconHeight();
				switch (i) {
				case 0:
					j=0;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+10;
					break;
				case 2:
					j=1;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+10;
					break;
				case 4:
					j=2;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+10, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+15;
					break;
				case 6:
					j=3;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+20, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+25;
					break;
				case 8:
					j=4;
					g.drawImage(tempList.get(j).getImage(), getX(), getY()+30, imgWidth, imgHeight, null);//���j*10���Գ�����
					previousDieY=getY()+40;
					break;
				case 14:
					setVisible(false);
					break;
				default:
					g.drawImage(tempList.get(j).getImage(), getX(), previousDieY, imgWidth, imgHeight, null);//���j*10���Գ�����
					break;
				}
			}
			break;
		default:
			break;
		}
		i=i+1;
	}

	public void addFire(){
		List<SuperElement> list=ElementManager.getManager().getElementList("enemyFire");
		switch (moveType) {
		case attackGun1:
			if(towardLeft) {
				list.add(EnemyBullet.createEnemyBullet(getX(), getY(), "bullet1Left"));
			}else {
				list.add(EnemyBullet.createEnemyBullet(getX(), getY(), "bullet1Right"));
			}
			break;
		case attackGun2:
			if(towardLeft) {
				list.add(EnemyBullet.createEnemyBullet(getX(), getY(), "bullet2Left"));
			}else {
				list.add(EnemyBullet.createEnemyBullet(getX(), getY(), "bullet2Right"));
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		switch (moveType) {
		case run:
			if(towardLeft) {
				setX(getX()-3);
				break;
			}else {
				setX(getX()+3);
				break;
			}
		case attackGun1:
			break;
		case attackGun2:
			break;
		case attackKnife:
			break;
		case stop:
			break;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public int getAnger() {
		return anger;
	}

	public void setAnger(int anger) {
		this.anger = anger;
	}

	public EnemyMoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(EnemyMoveType moveType) {
		this.moveType = moveType;
	}
	
	public boolean isTowardLeft() {
		return towardLeft;
	}

	public void setTowardLeft(boolean towardLeft) {
		this.towardLeft = towardLeft;
	}

	public void setI(int i) {
		this.i = i;
	}

	public EnemyMoveType getPreviousMoveType() {
		return previousMoveType;
	}

	public void setPreviousMoveType(EnemyMoveType previousMoveType) {
		this.previousMoveType = previousMoveType;
	}
	
}
