package com.zhuangjieying.frame;

import com.zhuangjieying.model.manager.ElementManager;
import com.zhuangjieying.model.vo.SuperElement;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;


public class MyJPanel extends JPanel implements Runnable{
	/**
	 * 1.���paint���� �ײ��Զ����õģ�������д����ķ���
	 * 2.�������ֻ��ִ��1�Σ�������������ã��Ͳ������ִ��
	 * 
	 * ֡: 50-100����ÿ֡    20-10֡/��
	 */
//	������ ������ʾ
	public void paint(Graphics g) {
		
		super.paint(g);
		this.setBackground(Color.gray);
//		��һ���ж�ֵ  Ҳ����ʹ��ö��
//		1.ǰ����
//		2.gameRuntime
		gameRunTime(g);//Graphics ����
//		3.�νӶ���
		
	}

	private void gameRunTime(Graphics g) {
		Map<String,List<SuperElement>> map=
				ElementManager.getManager().getMap();
		Set<String> set=new TreeSet<String>();
		set.addAll(map.keySet());
		for(String key:set){
			List<SuperElement> list=map.get(key);
			for(int i=0;i<list.size();i++){
				list.get(i).showElement(g);
			}
		}
	}
	
	/**
	 * ʲô����д��
	 * 1.���м̳й�ϵ�� ������֮����﷨����(��̬��һ��ʵ��)
	 * 2.��д�ķ�������� ����ķ�����ǩ��һ��(����ֵ���������ƣ���������)
	 * 3.��д�ķ����������η�ֻ���Աȸ���ĸ��ӿ��ţ������Աȸ�����ӷ��
	 * 4.��д�ķ����׳��쳣�����Ա� ����ĸ��ӿ���
	 */
	@Override
	public void run(){
		while(true){//��ѭ��:����᲻ֹͣ��ˢ��
//			�̵߳�����
			try {
				Thread.sleep(80);//����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.repaint();//Ҫ�� ����ٴ�ˢ��
		}
	}
}
