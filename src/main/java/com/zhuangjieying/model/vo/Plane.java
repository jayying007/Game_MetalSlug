package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.manager.MoveType;
import com.zhuangjieying.model.manager.PlaneMoveType;

public class Plane extends SuperElement {
	private int HP;//Ѫ��
	private int planeMoveType; //0 ������ 1������ 2������
	private ImageIcon img;
	private boolean pk;//����״̬
	private int flag;//����������
	private int time;
	public void update(){
		super.update();//���û����仰������ �����ƶ���ģ��
		addFire();//׷��
		updateImage();
		time++;
	}
	
	private void updateImage() {
		
		
	}
	private void addFire() {
		if(time>70) {
		List<SuperElement> list=
				ElementManager.getManager().getElementList("enemyFire");
//		if(list==null){
//			list=new ArrayList<>();
//		}
		list.add(PlaneFire.createPlaneFire(getX(), getY(), ""));
		ElementManager.getManager().getMap().put("enemyFire", list);
		time = 0;
		}
	}

	public Plane(int x,int y,int w,int h,ImageIcon img) {
		super(x,y,w,h);
		this.img=img;//�ͽ�ԭ��  
		HP=100;
		planeMoveType=0;
		pk=true;
		flag = 0;
		time = 0;
	}
	public static Plane CreatePlane(String str) {
		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img=
				ElementLoad.getElementLoad().getMap().get(arr[0]);
		
		return new Plane(x,y,w,h,img);
	}
	@Override
	public void showElement(Graphics g) {
		flag = 1-flag;
		
		int flag2 = flag;
		//img = new ImageIcon(url);
	//	g.drawImage(img.getImage(), getX(), getY(), getW(), getH(), null);
		g.drawImage(img.getImage(), getX(), getY(), getX()+getW(), getY()+getH(),flag2*222 , 0, (flag2+1)*222, 114, null);
	//	g.drawImage(img.getImage(), getX(), getY(), getX()+getW(), getY()+getH(), moveRight, 0, 1000+moveRight, 600, null);
	}

	@Override
	public void move() {
		
		
		if(this.getX()>Player.getPlayer().getX()) {
			planeMoveType=1;
		}
		else if(this.getX()==Player.getPlayer().getX()) {
			planeMoveType=0;
		}
		else {
			planeMoveType=2;
		}
		
		switch(planeMoveType){
		case 1:setX(getX()-5);break;
		case 2:setX(getX()+5);break;
		case 0:break;
		default:
			break;
		}
	}

	@Override
	public void destroy() {
		
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getPlaneMoveType() {
		return planeMoveType;
	}
	public void setPlaneMoveType(int planeMoveType) {
		this.planeMoveType = planeMoveType;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public boolean isPk() {
		return pk;
	}
	public void setPk(boolean pk) {
		this.pk = pk;
	}
	
}
