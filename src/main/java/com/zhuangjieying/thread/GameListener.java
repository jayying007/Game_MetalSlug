package com.zhuangjieying.thread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.manager.MoveType;
import com.zhuangjieying.model.vo.Player;
import com.zhuangjieying.model.vo.SuperElement;

public class GameListener implements KeyListener{
	
	List<SuperElement> list;
	
	@Override
	//j-74	k-75	l-76	w-87	a-65	s-83	d-68 
	public void keyPressed(KeyEvent e) {
//		System.out.println(e.getKeyCode());
		list= ElementManager.getManager().getElementList("player");
		if(list==null||list.size()==0)return;
		Player play=(Player)list.get(0);
		if(play.isDie())return;
		switch (e.getKeyCode()) {
		case 65://������
			switch(play.getMoveType()) {
			case squatStandLeft:
			case squatRunLeft:
			case squatStandRight:
			case squatRunRight:
				play.setMoveType(MoveType.squatRunLeft);
				break;
			default:
				play.setMoveType(MoveType.runLeft);
				break;
			}
			break;
		case 68://������
			switch(play.getMoveType()) {
			case squatStandLeft:
			case squatRunLeft:
			case squatStandRight:
			case squatRunRight:
				play.setMoveType(MoveType.squatRunRight);
				break;
			default:
				play.setMoveType(MoveType.runRight);
				break;
			}
			break;
		case 83://����
			if(play.isJump()==false) {
				switch(play.getMoveType()) {
				case runLeft:
					play.setMoveType(MoveType.squatRunLeft);
					break;
				case standLeft:
					play.setMoveType(MoveType.squatStandLeft);
					break;
				case runRight:
					play.setMoveType(MoveType.squatRunRight);
					break;
				case standRight:
					play.setMoveType(MoveType.squatStandRight);
					break;
				default:
					break;
				}
			}
			break;
		case 75://��Ծ
			if(play.isJump()==false) {
				play.setJump(true);
			}
			break;
		case 74://����
			play.setPk(true);
			break;
		case 76://�л�����
//			if(play.getGunType()==GunType.gun1) {
//				play.setGunType(GunType.gun2);
//			}
//			else {
//				play.setGunType(GunType.gun1);
//			}
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		list=ElementManager.getManager().getElementList("player");
		if(list==null||list.size()==0)return;
		Player play=(Player)list.get(0);
		if(play.isDie())return;
		switch (e.getKeyCode()) {
		case 65://�ͷ���
			switch(play.getMoveType()) {
			case squatRunLeft:
				play.setMoveType(MoveType.squatStandLeft);
				break;
			default:
				play.setMoveType(MoveType.standLeft);
				break;
			}
			break;
		case 68://�ͷ���
			switch(play.getMoveType()) {
			case squatRunRight:
				play.setMoveType(MoveType.squatStandRight);
				break;
			default:
				play.setMoveType(MoveType.standRight);
				break;
			}
			break;
		case 83://�����ͷ�
			switch(play.getMoveType()) {
			case squatRunLeft:
				play.setMoveType(MoveType.runLeft);
				break;
			case squatStandLeft:
				play.setMoveType(MoveType.standLeft);
				break;
			case squatRunRight:
				play.setMoveType(MoveType.runRight);
				break;
			case squatStandRight:
				play.setMoveType(MoveType.standRight);
				break;
			default:
				break;
			}
			break;
		case 75://��Ծ
			break;
		case 74://����
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
