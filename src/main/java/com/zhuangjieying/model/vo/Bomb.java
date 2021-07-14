package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.manager.ElementManager;

public class Bomb extends SuperElement {
	private static ImageIcon img;
	private static List<ImageIcon> list;
	private int time;
	public Bomb(int x,int y,int w,int h,ImageIcon img) {

		super(x,y,w,h);
		this.img = img;
		time = 0;
	}
	
	public static Bomb createBomb(int x,int y,List<ImageIcon> list2) {
		list = list2;
		img = list2.get(0);
		return new Bomb(x-20, y-50, 110, 120, img);
	}
	@Override
	public void showElement(Graphics g) {
		
		List<SuperElement>playList=ElementManager.getManager().getElementList("player");
		if(playList==null||playList.size()==0)return;
		List<SuperElement>enemyList=ElementManager.getManager().getElementList("enemy");
		List<SuperElement>bossList=ElementManager.getManager().getElementList("boss");
		if((enemyList==null||enemyList.size()==0)&&(bossList==null||bossList.size()==0))return;
		
		img = list.get(time++);
		g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
		if(time==15) {
			setVisible(false);
		}
	}

	@Override
	public void move() {
		
	}

	@Override
	public void destroy() {
		
	}

}
