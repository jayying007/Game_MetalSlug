package com.zhuangjieying.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


import com.zhuangjieying.frame.MyJFrame;
import com.zhuangjieying.frame.MyJPanel;
import com.zhuangjieying.thread.GameListener;

/**
 * ��������� �Լ��������Լ���-����ȷ�ķֹ�
 * 
 */
public class GameStart {
//	������Ϸ����ڣ�����
	private static MyJFrame jf;
	private static MyJPanel jp;
	public static void main(String[] args) {
//		try {
//			URL url=new URL("file:\\G:\\back.mp3");
//			AudioClip ac=Applet.newAudioClip(url);
//			ac.play();        //ac.stop();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		��Դ����
//		������أ��Զ���������
		jf=new MyJFrame();
		jp=new MyJPanel();
		GameListener listener=new GameListener();
		
		jf.setKeyListener(listener);
		jf.setJp(jp);//ע��
//		��������
		jf.addListener();
		jf.addJPanels();//����jp
//		��Ϸ��������ʼ��
		jf.start();
	}
	/**
	 * 1.����һ�� VO�࣬�̳�superElement
	 * 2.�ڹ�������ʵ����
	 * 3.�����ļ��н�������
	 * 4.�����Ҫ���������� ��ͦ��д����
	 */
	public MyJFrame getJf() {
		return jf;
	}
	public static MyJPanel getJp() {
		return jp;
	}
}
