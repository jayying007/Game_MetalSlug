package com.zhuangjieying.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Gift extends SuperElement {
	private ImageIcon img;
	public Gift(int x, int y, int w, int h, ImageIcon img) {
		super(x, y, w, h);
		this.img = img;
		// TODO Auto-generated constructor stub
	}
	public static Gift createGift(int x,int y) {
		ImageIcon img=new ImageIcon("img/gift/gift2.png");
		return new Gift(x,y,30,50,img);
	}
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img.getImage(), getX(), getY(), 
				getW(), getH(), null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
