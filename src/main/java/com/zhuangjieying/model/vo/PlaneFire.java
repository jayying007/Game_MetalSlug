package com.zhuangjieying.model.vo;

import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import com.zhuangjieying.model.load.ElementLoad;
import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.manager.MoveType;

public class PlaneFire extends SuperElement {
	private ImageIcon img;
	
	private int flag;//ը����ת����
	private int random;

	public PlaneFire(int x, int y, int w, int h,ImageIcon img) {
		super(x, y, w, h);
		this.img=img;
		flag=4;
	}

	public static PlaneFire createPlaneFire(int x,int y,String str){
		ImageIcon img=new ImageIcon("src/main/resources/img/plane/PlaneFire/plane_bomb.png");
		return new PlaneFire(x+20,y+50,30,50,img);
	}
	@Override
	public void showElement(Graphics g) {
		
		if(flag<3)
			flag++;
		else
			flag=0;
		
		/*g.drawImage(img.getImage(), getX(), getY(), 
				getW(), getH(), null);*/
		g.drawImage(img.getImage(), getX(), getY(), getX()+getW(), getY()+getH(),flag*29 , 0, (flag+1)*29, 44, null);
	}
	@Override
	public void move() {
		setY(getY()+5);
		MoveType moveType = BackGroud.getBackGround().getMoveType();
//		�����߽�� �ǲ���Ҫ ����
		random = (int)(Math.random()*120);
		if(getY()>440+random){
			this.setVisible(false);
			destroy();
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
		if(!isVisible()) {
		List<SuperElement> list = ElementManager.getManager().getElementList("explode");
		Map<String, List<ImageIcon>>map = ElementLoad.getElementLoad().getBombMap();
		List<ImageIcon> list2 = map.get("bombA");
		list.add(Bomb.createBomb(getX(), getY(),list2));
		}
	}
}
