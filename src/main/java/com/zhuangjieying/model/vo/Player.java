package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.manager.GunType;
import com.zhuangjieying.model.manager.MoveType;
import com.zhuangjieying.model.vo.BackGroud;

public class Player extends SuperElement implements Runnable{
	private int score;//��ҷ���
	private int preY;//��ʼY����Ծ�Ļ�׼��
	private ImageIcon topImg;//����ϰ���
	private ImageIcon downImg;//����°���
	
	private int x2,y2,w2,h2;//�°���ͼƬ������Ϣ
	
	private MoveType moveType;//�Ƿ��ƶ����ƶ�����left,top,right,down,stop;
	private GunType gunType;//��ǰ��������gun1,gun2,knife

	private boolean jump;//��Ծ״̬��Ĭ��Ϊfalse
	private boolean squat;//����״̬��Ĭ��Ϊfalse
	private boolean jumpType;//��Ծ����Ĭ��Ϊtrue������
	private boolean pk;//����״̬��Ĭ��Ϊ false
	private boolean die;//����Ƿ���������Ĭ��Ϊfalse
	private boolean close;//����˻����ʽ���Ĭ��Ϊfalse
	
	String [][][]topArr;//�ϰ����ӦplayerImgMap�ļ�ֵ
	String []downArr;//�°����ӦplayerImgMap�ļ�ֵ
	
	private static Player player;//����ģʽ
	
	public Player(int x1,int y1,int w1,int h1,ImageIcon topImg,int x2,int y2,int w2,int h2,ImageIcon downImg){
		super(x1,y1,w1,h1);
		this.preY=y1;
		this.topImg=topImg;
		this.downImg=downImg;
		this.x2=x2;
		this.y2=y2;
		this.w2=w2;
		this.h2=h2;
		init();
		initArr();
	}
//	��ʼ������
	public void init() {
		this.score=0;
		this.moveType=MoveType.standRight;
		this.gunType=GunType.gun1;
		this.jump=false;
		this.squat=false;
		this.jumpType=true;
		this.pk=false;
		this.die=false;
		this.close=false;
	}
//	��ʼ��Arr����
	public void initArr() {
		topArr=new String[100][10][2];
		downArr=new String[100];
//		��ȡö��ֵ��Ӧ�±�
		int runLeft=MoveType.runLeft.ordinal();
		int runRight=MoveType.runRight.ordinal();
		int standLeft=MoveType.standLeft.ordinal();
		int standRight=MoveType.standRight.ordinal();
		int jumpLeft=MoveType.jumpLeft.ordinal();
		int jumpRight=MoveType.jumpRight.ordinal();
		int squatRunLeft=MoveType.squatRunLeft.ordinal();
		int squatRunRight=MoveType.squatRunRight.ordinal();
		int squatStandLeft=MoveType.squatStandLeft.ordinal();
		int squatStandRight=MoveType.squatStandRight.ordinal();
		int gun1=GunType.gun1.ordinal();
		int gun2=GunType.gun2.ordinal();
		int knife=GunType.knife.ordinal();
//		��ʼ���ϰ���ͼƬ��Ӧmap��ֵ
		topArr[standLeft][gun1][0]="playerTopLeftGun1StandNofire";
		topArr[standLeft][gun1][1]="playerTopLeftGun1StandFire";
		topArr[standLeft][gun2][0]="playerTopLeftGun2StandNofire";
		topArr[standLeft][gun2][1]="playerTopLeftGun2StandFire";
		topArr[standLeft][knife][0]="playerTopLeftKnife";
		topArr[standLeft][knife][1]="playerTopLeftKnife";
		topArr[standRight][gun1][0]="playerTopRightGun1StandNofire";
		topArr[standRight][gun1][1]="playerTopRightGun1StandFire";
		topArr[standRight][gun2][0]="playerTopRightGun2StandNofire";
		topArr[standRight][gun2][1]="playerTopRightGun2StandFire";
		topArr[standRight][knife][0]="playerTopRightKnife";
		topArr[standRight][knife][1]="playerTopRightKnife";
		topArr[jumpLeft][gun1][0]="playerTopLeftGun1Jump";
		topArr[jumpLeft][gun1][1]="playerTopLeftGun1StandFire";
		topArr[jumpLeft][gun2][0]="playerTopLeftGun2Jump";
		topArr[jumpLeft][gun2][1]="playerTopLeftGun2StandFire";
		topArr[jumpLeft][knife][0]="playerTopLeftKnife";
		topArr[jumpLeft][knife][1]="playerTopLeftKnife";
		topArr[jumpRight][gun1][0]="playerTopRightGun1Jump";
		topArr[jumpRight][gun1][1]="playerTopRightGun1StandFire";
		topArr[jumpRight][gun2][0]="playerTopRightGun2Jump";
		topArr[jumpRight][gun2][1]="playerTopRightGun2StandFire";
		topArr[jumpRight][knife][0]="playerTopRightKnife";
		topArr[jumpRight][knife][1]="playerTopRightKnife";
//		��ʼ���°���ͼƬ��Ӧmap��ֵ
		downArr[standLeft]="playerDownLeftStand";
		downArr[standRight]="playerDownRightStand";
		downArr[jumpLeft]="playerDownLeftJump";
		downArr[jumpRight]="playerDownRightJump";
		downArr[runLeft]="playerDownLeftRun";
		downArr[runRight]="playerDownRightRun";
		downArr[squatRunLeft]="playerDownLeftSquatrun";
		downArr[squatRunRight]="playerDownRightSquatrun";
		downArr[squatStandLeft]="playerDownLeftSquatstand";
		downArr[squatStandRight]="playerDownRightSquatstand";
	}
	//����ֱ�ӵ�����������������õ�һ����Ҷ���  str��������ľ�����Ҷ������Ϣ
	public static Player createPlayer(String str){
//		playerTopRightGun1StandNofire,playerDownRightStand,80,250,100,80,80,330,50,40

		String [] arr=str.split(",");
		int x1=Integer.parseInt(arr[2]);
		int y1=Integer.parseInt(arr[3]);
		int w1=Integer.parseInt(arr[4]);
		int h1=Integer.parseInt(arr[5]);
		int x2=Integer.parseInt(arr[6]);
		int y2=Integer.parseInt(arr[7]);
		int w2=Integer.parseInt(arr[8]);
		int h2=Integer.parseInt(arr[9]);
		ImageIcon topImg=ElementLoad.getElementLoad().getPlayerImgMap().get(arr[0]).get(0);
		ImageIcon downImg=ElementLoad.getElementLoad().getPlayerImgMap().get(arr[1]).get(0);
		
		return player=new Player(x1,y1,w1,h1,topImg,x2,y2,w2,h2,downImg);
	}
	
	@Override
	public void showElement(Graphics g) {
//		System.out.println(score);
//		ImageIcon icon = new ImageIcon("./img/score/0.png");
//		g.drawImage(icon.getImage(), 
//				0, 0,   //��Ļ���Ͻ�����
//				getW()/2,getH()/2,    //ͼƬ��͸�	
//					null);
		if(isDie()) {
//			������ͼƬ������ȫ��
			g.drawImage(topImg.getImage(), 
				getX(), getY()+getH2()/2,   //��Ļ���Ͻ�����
				getW(),getH(),    //ͼƬ��͸�	
					null);
		}
		else {
			if(isJump()) {
				switch(getMoveType()) {
				case jumpLeft:
				case runLeft:
				case squatRunLeft:
				case squatStandLeft:
				case standLeft:
					g.drawImage(topImg.getImage(), 
							getX()+getW()*5/12, getY(),   //��Ļ���Ͻ�����
							getW()*3/4,getH(),    //ͼƬ��͸�	
								null);
					break;
				case jumpRight:
				case runRight:
				case squatRunRight:
				case squatStandRight:
				case standRight:
					g.drawImage(topImg.getImage(), 
							getX()-getW()/6, getY(),   //��Ļ���Ͻ�����
							getW()*3/4,getH(),    //ͼƬ��͸�	
								null);
					break;
				default:
					break;
				}
				g.drawImage(downImg.getImage(), 
						getX2(), getY2(),   //��Ļ���Ͻ�����
						getW2(),getH2(),    //ͼƬ��͸�	
							null);
			}
			else {
				g.drawImage(topImg.getImage(), 
						getX(), getY(),   //��Ļ���Ͻ�����
						getW(),getH(),    //ͼƬ��͸�	
							null);
				g.drawImage(downImg.getImage(), 
						getX2(), getY2(),   //��Ļ���Ͻ�����
						getW2(),getH2(),    //ͼƬ��͸�	
							null);
			}
		}
	}
	public void move(){
		if(isDie()) return;
//		������һ��bug
		if(isJump()){
			if(getY()>getPreY()-getH()*2 && jumpType) {
				setY(getY()-getH()/4);
				setY2(getY2()-getH()/4);
			}
			else {
				jumpType=false;
			}
			if(getY()<getPreY() && jumpType==false) {
				setY(getY()+getH()/4);
				setY2(getY2()+getH()/4);
			}
			else if(getY()==preY){
				this.setJump(false);
				jumpType=true;
			}
		}
		switch(moveType){
		case runLeft:
		case squatRunLeft:
			if(getX()>0) {
				setX(getX()-10);
				setX2(getX2()-10);
			}
			break;
		case runRight:
		case squatRunRight:
			if(getX()<=400) {
				setX(getX()+10);
				setX2(getX2()+10);
			}
			break;
		default:
			break;
		}
	}
//	��д�����ģ��
	public void update(){
		super.update();//���û����仰������ �����ƶ���ģ��,���û�ִ�������������.
//		move();
//		destroy();
		updateBackground();
		updateLocate();
	}
//	�����ƶ�Ӱ�챳���ƶ�
	public void updateBackground() {
		if(getX()<400) {
		}
		else if(getX()>=400&&(moveType!=MoveType.runRight
				&&moveType!=MoveType.jumpRight&&moveType!=MoveType.squatRunRight)) {
			BackGroud.getBackGround().setMoveType(MoveType.stop);
		}
		else  {
			switch (this.moveType) {
			case jumpRight:
			case runRight:
			case squatRunRight:
				BackGroud.getBackGround().setMoveType(MoveType.runRight);
				break;
			default:
				BackGroud.getBackGround().setMoveType(MoveType.stop);
				break;
			}
		}
	}
//	�¶���ָ�
	public void updateLocate() {
		
		switch(getMoveType()) {
		case jumpLeft:
		case runLeft:
		case standLeft:
			setX(getX2()+getW2()-getW());
			if(squat) {
				squat=false;
				setY(getY()-30);
			}
			break;
		case squatRunLeft:
		case squatStandLeft:
			setX(getX2()+getW2()-getW());
			if(!squat) {
				squat=true;
				setY(getY()+30);
			}
			break;
		case jumpRight:
		case runRight:
		case standRight:
			setX(getX2());
			if(squat) {
				squat=false;
				setY(getY()-30);
			}
			break;
		case squatRunRight:
		case squatStandRight:
			setX(getX2());
			if(!squat) {
				squat=true;
				setY(getY()+30);
			}
			break;
		default:
			break;
		}
	}
//	��ҿ�ǹ������ӵ�
	public void addFire(){
		if(!isPk()){//���PK��false�Ͳ���Ҫ ����ӵ�
			return;
		}
		
		List<SuperElement> list=
				ElementManager.getManager().getElementList("playFire");
		String url;
		if(list==null){
			list=new ArrayList<>();
		}
		switch (gunType) {
		case gun1:
			url="playerBullet1";
			switch(moveType) {
			case jumpLeft:
			case runLeft:
			case squatRunLeft:
			case squatStandLeft:
			case standLeft:
				list.add(PlayerFire.createPlayerFire(getX(), getY()+getH()/3, url, moveType));
				break;
			case jumpRight:
			case runRight:
			case squatRunRight:
			case squatStandRight:
			case standRight:
				list.add(PlayerFire.createPlayerFire(getX()+getW()*4/5, getY()+getH()/3, url, moveType));
				break;
			default:
				break;
			}
			
			break;
		case gun2:
			switch(moveType) {
			case jumpLeft:
			case runLeft:
			case squatRunLeft:
			case squatStandLeft:
			case standLeft:
				url="playerBullet2";
				list.add(PlayerFire.createPlayerFire(getX(), getY()+getH()/3, url, moveType));
				break;
			case jumpRight:
			case runRight:
			case squatRunRight:
			case squatStandRight:
			case standRight:
				url="playerBullet3";
				list.add(PlayerFire.createPlayerFire(getX()+getW()*4/5, getY()+getH()/3, url, moveType));
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		ElementManager.getManager().getMap().put("playFire", list);
		
		this.setPk(false);;//ÿ��һ�� ֻ�ܷ���һ���ӵ�
	}
	
	
	public GunType getGunType() {
		return gunType;
	}
	public void setGunType(GunType gunType) {
		this.gunType = gunType;
	}
	public MoveType getMoveType() {
		return moveType;
	}
	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
	public ImageIcon getTopImg() {
		return topImg;
	}
	public void setTopImg(ImageIcon img) {
		this.topImg = img;
	}
	public ImageIcon getDownImg() {
		return downImg;
	}
	public void setDownImg(ImageIcon img) {
		this.downImg = img;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	public boolean isJump() {
		return jump;
	}
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	public int getPreY() {
		return preY;
	}
	public boolean isDie() {
		return die;
	}
	public void setDie(boolean die) {
		this.die = die;
	}
	
	public boolean isClose() {
		return close;
	}
	public void setClose(boolean close) {
		this.close = close;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public int getW2() {
		return w2;
	}
	public void setW2(int w2) {
		this.w2 = w2;
	}
	public int getH2() {
		return h2;
	}
	public void setH2(int h2) {
		this.h2 = h2;
	}
	public boolean isSquat() {
		return squat;
	}
	public void setSquat(boolean squat) {
		this.squat = squat;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public static Player getPlayer() {
		return player;
	}
	@Override
	public void destroy() {
			
	}
//	���̸߳���player��״̬��������ͼƬ������
	@Override
	public void run() {
		Map<String, List<ImageIcon>>map=ElementLoad.getElementLoad()
				.getPlayerImgMap();
		List<ImageIcon>topList=null;
		List<ImageIcon>downList=null;
		
		
		while(true) {
			if(isDie()) {
				switch(moveType) {
				case jumpLeft:
				case runLeft:
				case squatRunLeft:
				case squatStandLeft:
				case standLeft:
					topList=map.get("playerDieLeft");
					break;
				case jumpRight:
				case runRight:
				case squatRunRight:
				case squatStandRight:
				case standRight:
					topList=map.get("playerDieRight");
					break;
				default:
					break;
				}
				updateTopImg(topList, 300);
				this.setVisible(false);
				break;
			}
			
			
			int t1;
			switch(moveType) {
			case jumpLeft:
			case runLeft:
			case squatRunLeft:
			case squatStandLeft:
			case standLeft:
				t1=MoveType.standLeft.ordinal();
				break;
			case jumpRight:
			case runRight:
			case squatRunRight:
			case squatStandRight:
			case standRight:
				t1=MoveType.standRight.ordinal();
				break;
			default:
				t1=0;
			}
			int t2=gunType.ordinal();
			int t3=0;
			if(isPk()) {
				t3=1;
				if(isClose()) {
					t2=GunType.knife.ordinal();
				}
				else {
					addFire();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.setPk(false);
			}
			int d1=moveType.ordinal();
			
//			�����жϣ�����������ƶ�
			if(isJump()) {
				switch(moveType) {
				case runLeft:
				case squatRunLeft:
				case squatStandLeft:
				case standLeft:
					topList=map.get(topArr[MoveType.jumpLeft.ordinal()][t2][t3]);
					break;
				case runRight:
				case squatRunRight:
				case squatStandRight:
				case standRight:
					topList=map.get(topArr[MoveType.jumpRight.ordinal()][t2][t3]);
					break;
				default:
					break;
				}
			}
			else {
				topList=map.get(topArr[t1][t2][t3]);
			}
			
			if(isJump()) {
				switch(moveType) {
				case runLeft:
				case squatRunLeft:
				case squatStandLeft:
				case standLeft:
					downList=map.get(downArr[MoveType.jumpLeft.ordinal()]);
					break;
				case runRight:
				case squatRunRight:
				case squatStandRight:
				case standRight:
					downList=map.get(downArr[MoveType.jumpRight.ordinal()]);
					break;
				default:
					break;
				}
			}
			else {
				downList=map.get(downArr[d1]);
			}

			updateImg(topList, downList, 20);
		}
	}
	private void updateImg(List<ImageIcon> topList,List<ImageIcon> downList,int t) {
		
		int len1=topList.size();
		int len2=downList.size();
		int max=Math.max(len1, len2);
		for(int i=0;i<max;i++) {
			if(len1>len2) {
				this.setTopImg(topList.get(i));
				this.setDownImg(downList.get(i%len2));
			}
			else {
				this.setTopImg(topList.get(i%len1));
				this.setDownImg(downList.get(i));
			}
			
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void updateTopImg(List<ImageIcon> topList,int t) {
		for(int i=0;i<topList.size();i++) {
			this.setTopImg(topList.get(i));
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
