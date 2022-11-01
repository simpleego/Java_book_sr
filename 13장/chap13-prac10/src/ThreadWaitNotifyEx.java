import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadWaitNotifyEx extends JFrame{
	public ThreadWaitNotifyEx() {
		this.setContentPane(new DrawingPanel()); // DrawingPanel �г��� ����Ʈ������ ����Ѵ�. 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setVisible(true);
	}
	
	// Ÿ���� �׷��� �г�
	class DrawingPanel extends JPanel {
		private DrawingThread th = new DrawingThread(this); // ������ ����		
		private Rectangle r = new Rectangle(0,0,0,0);
		
		public DrawingPanel() {
			this.addMouseListener(new MouseHandler());
			th.start();
		}
		public void setObj(int x, int y, int w, int h) {
			r.x = x;
			r.y = y;
			r.width = w;
			r.height = h;
		}
		
		// �г� ���θ� �׸���. �гο� �ֱٿ� ���� Ÿ�� �ϳ��� �׸���.
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // ������ �׸� Ÿ���� ��� ������.
			if(r.width == 0 || r.height == 0) return;
			g.setColor(Color.MAGENTA);
			g.fillOval(r.x, r.y, r.width, r.height); // �����尡 ���� ������ Ÿ���� �׸���.
		}
		
		// ���콺 ������. 
		// ���콺�� �гο� �ö���� �����带 ������ ���콺�� �гο��� �������� �����带 �Ͻ� ������Ų��.
		class MouseHandler extends MouseAdapter {
			public void mouseEntered(MouseEvent e) {
				setTitle("Make Drawing to Start"); // ������ Ÿ��Ʋ ����
				th.wakeup(); // �����带 ���� Ÿ���� �����ϵ��� �Ѵ�.
			}
			public void mouseExited(MouseEvent e) {
				setTitle("Make Drawing to Pause"); // ������ Ÿ��Ʋ ����				
				th.pause(); // �����带 ���� ���� Ÿ�� ������ �����ϵ��� �Ѵ�.
			}			
		}
	}
	
	// 300ms �������� Ÿ���� �����ϴ� ������
	class DrawingThread extends Thread {
		private boolean running = true; // ������ ����(true: Ÿ�������� ����� ����)
		
		// wait-notify ����ȭ�� ���� ��ü
		// UI ������� DrawingThread ������ ����ȭ�� ���� ��ü
		private Object obj = new Object();
		private DrawingPanel p; // Ÿ���� �׸� �г� �ּ�
		private Random r = new Random(new Date().getTime()); // ������ ������

		public DrawingThread(DrawingPanel p) {
			this.p = p;
		}

		// wait ������ �����带 �����.
		public void wakeup() {
			running = true; // Ÿ������� ���
			synchronized(obj) {
				obj.notify(); // obj�� ��ٸ��� �����带 �����.
			}
		}
		
		public void pause() {
			running = false; // Ÿ������� ����
		}
		
		// 300ms �������� Ÿ���� �����ϴ� ������ �ڵ�
		public void run() {
			try {
				synchronized(obj) {
					// �����尡 ���۵Ǹ� ó���� �� �� Ÿ�� �׸��� ������ �˸� ������ ����Ѵ�.
					obj.wait(); 
				}
			}
			catch(InterruptedException e) { return; }

			// 300 ms �������� Ÿ���� �����Ѵ�.
			while(true) {
				if(p.isValid()) { // �г��� ��������� ��밡���ϴٸ�
					if(!running) { //Ÿ������Ⱑ ���Ǿ� ���� �ʴٸ�
						try {
							synchronized(obj) {
								obj.wait(); // Ÿ������Ⱑ ���� ������ ��ٸ���.
							}
						}
						catch(InterruptedException e) {	return; }
					}
					int x = r.nextInt(p.getWidth()); // �г��� ������ ���� ���� ����
					int y = r.nextInt(p.getHeight()); // �г��� ���̺��� ���� ���� ����
					int w = r.nextInt(100)+1; // (1, 100) ������ ���� ����
					int h = r.nextInt(100)+1; // (1, 100) ������ ���� ����
				
					p.setObj(x,y,w,h); // �гο� �׸� Ÿ�� ���� ����
					
					// �гη� �Ͽ��� ������ �׸� Ÿ���� ����� ���ο� Ÿ������ �ٽ� �׸��� ����
					// ��������� �г��� paintComponent()�� ȣ��ȴ�.
					p.repaint(); 		
				}
				try {
					Thread.sleep(300); // 300ms �� ���� ���� �ܴ�.
				}
				catch(InterruptedException e){return;}
			}
		}
	}
	
	public static void main(String[] args) {
		new ThreadWaitNotifyEx();
	}
}
