package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class SuperElement {
	private int x;//��ͼƬ�����Ͻ�x
	private int y;//��ͼƬ�����Ͻ�y
	private int w;
	private int h;
	private boolean visible;//Ĭ��Ϊ true ���� ���
//	
	public SuperElement(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		visible=true;
	}
//	��Щ�����ǿ����� ˳��ִ�еġ�ģ��ģʽ  //���������ָ���� �����ʵ�����
	public void update(){
		move();
		destroy();
	}
	public boolean gamePK(SuperElement se){
		Rectangle r1=new Rectangle(x, y, w, h);
		Rectangle r2=new Rectangle(se.x, se.y, se.w, se.h);
		return r1.intersects(r2);//��������н��������� true
	}
	
	public abstract void showElement(Graphics g);
	public abstract void move();
	public abstract void destroy();
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
