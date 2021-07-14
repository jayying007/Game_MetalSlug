package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.manager.MoveType;

public class BackGroud extends SuperElement {
	private ImageIcon img;
	private MoveType moveType;
	private int moveRight;
	private static BackGroud backGroud;
	public BackGroud(int x,int y,int w,int h,ImageIcon img) {
		// TODO Auto-generated constructor stub
		super(x,y,w,h);
		this.img = img;
		setMoveType(MoveType.stop);
		moveRight = 0;
	}
	
	public static BackGroud getBackGround() {
		
		return backGroud;
	}
	
	public static BackGroud CreateBackGroud(ImageIcon img) {
		int x = 0;
		int y = 0;
		int w = 1000;
		int h = 600;		
		return backGroud = new BackGroud(x,y,w,h,img);
	}
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
	//	g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
		g.drawImage(img.getImage(), getX(), getY(), getX()+getW(), getY()+getH(), moveRight, 0, 1000+moveRight, 600, null);
	}

	@Override
	public void move() {
		if(Player.getPlayer().isDie())return;
		
		if(moveRight==2964)
			this.moveType=MoveType.stop;
		switch (moveType) {
		case runRight:
			setMoveRight(Math.min(getMoveRight()+10,2964));
			if(getMoveRight()==2964)
				return ;
			//С������ڱ����ƶ�
			
			List<SuperElement> enemyList = ElementManager.getManager().getElementList("enemy");
			List<SuperElement> planeList = ElementManager.getManager().getElementList("plane");
			List<SuperElement> fireList = ElementManager.getManager().getElementList("enemyFire");
			List<SuperElement> bossList = ElementManager.getManager().getElementList("boss");
			List<SuperElement> hostageList = ElementManager.getManager().getElementList("hostage");
			List<SuperElement> giftList = ElementManager.getManager().getElementList("gift");
			List<SuperElement> bombList = ElementManager.getManager().getElementList("bombA");
//			System.out.println(bombList);
			for (SuperElement enemyElement : enemyList) {
				enemyElement.setX(enemyElement.getX()-10);
			}
			for (SuperElement bossElement : bossList) {
				bossElement.setX(bossElement.getX()-10);
			}
			for (SuperElement planeElement : planeList) {
				planeElement.setX(planeElement.getX()-10);
			}
			for (SuperElement fireElement : fireList) {
				fireElement.setX(fireElement.getX()-10);
			}
			for (SuperElement hostageElement : hostageList) {
				hostageElement.setX(hostageElement.getX()-10);
			}
			for (SuperElement giftElement : giftList) {
				giftElement.setX(giftElement.getX()-10);
			}
			for (SuperElement bombElement : bombList) {
				bombElement.setX(bombElement.getX()-10);
			}
			break;
		case stop:
			setMoveRight(getMoveRight());
			break;
		default:
			break;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public MoveType getMoveType() {
		return moveType;
	}

	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}

	public int getMoveRight() {
		return moveRight;
	}

	public void setMoveRight(int moveRight) {
		this.moveRight = moveRight;
	}

}
