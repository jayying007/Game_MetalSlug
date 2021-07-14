package com.zhuangjieying.model.vo;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.manager.MoveType;

public class PlayerFire extends SuperElement{
	
	private ImageIcon img;
	private MoveType moveType;
	
	public PlayerFire(int x, int y, int w, int h,ImageIcon img, MoveType moveType) {
		super(x, y, w, h);
		this.img=img;
		this.moveType=moveType;
	}

	public static PlayerFire createPlayerFire(int x,int y,String str, MoveType moveType){
		String url=ElementLoad.getElementLoad().getPlayerBulletMap().get(str);
		
		String []arr = url.split(",");
		
		ImageIcon img=new ImageIcon("src/main/resources/" + arr[0]);
		int w=Integer.parseInt(arr[1]);
		int h=Integer.parseInt(arr[2]);
		
		return new PlayerFire(x,y,w,h,img,moveType);
	}
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), getX(), getY(), 
				getW(), getH(), null);
	}
	@Override
	public void move() {
		switch (moveType) {
		case jumpLeft:
		case runLeft:
		case squatRunLeft:
		case squatStandLeft:
		case standLeft:
			setX(getX()-20);
			if(getX()<0) {
				setVisible(false);
			}
			break;
		case jumpRight:
		case runRight:
		case squatRunRight:
		case squatStandRight:
		case standRight:
			setX(getX()+20);
//			�����߽�� �ǲ���Ҫ ����
			if(getX()>1000){
				setVisible(false);
			}
			break;
		default:
			break;
		}
	}
	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	@Override
	public void destroy() {
//		���ٵ�ʱ����Ҫ���� ��ը���� ����뵽 ��ը��list������
//		if(!isVisible())
//			System.out.println("��ը��");
	}
}
