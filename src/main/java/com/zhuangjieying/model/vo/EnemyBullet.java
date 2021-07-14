package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.manager.ElementManager;

public class EnemyBullet extends SuperElement{
	private ImageIcon img;
	private String bulletType;
	private int g=10;//�������ٶ�
//	private double t=1.0;//�ڵ���������ʱ��
	private double vx=0;//�ڵ���ʼ�ٶȣ�Ҳ��ˮƽ�ٶȷ���
	private double vy=0;//�ڵ���ֱ�����ٶ�
	private int SX=0;//bossBullet��Ҫ���е�ˮƽ����
	private int SY=0;//bossBullet��Ҫ���е���ֱ����
	
	private boolean firstIn;//��һ�ν���
	private double a;//���κ�����ϵ��
	private int playerY;//��ҵ�����
	
	public EnemyBullet(int x, int y, int w, int h,String bulletType,ImageIcon img) {
		super(x, y, w, h);

		this.img=img;
		this.bulletType=bulletType;
		this.SX=x;
		this.SY=y;
		this.firstIn=true;
	}
	
	public static EnemyBullet createEnemyBullet(int x,int y,String bulletType){
		switch (bulletType) {
		case "bullet1Left":
			return new EnemyBullet(x-75,y+16,21,7,bulletType,//�����30Ҫswitchһ�£�x��yҲҪ��һ�µ���
					ElementLoad.getElementLoad().getBulletMap().get(bulletType));
		case "bullet1Right":
			return new EnemyBullet(x+100,y+18,21,7,bulletType,//�����30Ҫswitchһ�£�x��yҲҪ��һ�µ���
					ElementLoad.getElementLoad().getBulletMap().get(bulletType));
		case "bullet2Left":
			return new EnemyBullet(x-70,y+15,33,12,bulletType,//�����30Ҫswitchһ�£�x��yҲҪ��һ�µ���
					ElementLoad.getElementLoad().getBulletMap().get(bulletType));
		case "bullet2Right":
			return new EnemyBullet(x+90,y+15,33,12,bulletType,//�����30Ҫswitchһ�£�x��yҲҪ��һ�µ���
					ElementLoad.getElementLoad().getBulletMap().get(bulletType));
		case "bossBullet":
			return new EnemyBullet(x,y+215,18,18,bulletType,//���x��yҪ�и�ƫ����
					ElementLoad.getElementLoad().getBulletMap().get(bulletType));
		default:
			break;
		}
		return null;
	}
	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		switch (bulletType) {
		case "bullet1Left":
			setX(getX()-12);
			break;
		case "bullet1Right":
			setX(getX()+12);
			break;
		case "bullet2Left":
			setX(getX()-12);
			break;
		case "bullet2Right":
			setX(getX()+12);
			break;
		case "bossBullet":
			setX(getX()-10);
			parabola();
			break;
		default:
			break;
		}
	}

	private void parabola(){
		if(firstIn){
			firstIn=false;
//			Player.getPlayer().getY2()+Player.getPlayer().getH2()Ϊ�ڵ������λ�ã�365Ϊˮƽ����ڵ�
			playerY=Math.max(Player.getPlayer().getY2()+Player.getPlayer().getH2(),365);
			a=-(playerY-365.0)/((Player.getPlayer().getX()-SX+0.0)*(Player.getPlayer().getX()-SX+0.0));  // (playX-bossX)*(playX-bossX)+bossY=0;
			
		}
		double finalY=365-a*(getX()-SX)*(getX()-SX);
		setY((int)finalY);//a*(bulletX-bossX)*(bulletX-bossX)+bossY;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
