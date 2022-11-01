import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadWaitNotifyEx extends JFrame{
	public ThreadWaitNotifyEx() {
		this.setContentPane(new DrawingPanel()); // DrawingPanel 패널을 컨텐트팬으로 사용한다. 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setVisible(true);
	}
	
	// 타원이 그려질 패널
	class DrawingPanel extends JPanel {
		private DrawingThread th = new DrawingThread(this); // 스레드 생성		
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
		
		// 패널 내부를 그린다. 패널에 최근에 만든 타원 하나만 그린다.
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 이전에 그린 타원을 모두 지원다.
			if(r.width == 0 || r.height == 0) return;
			g.setColor(Color.MAGENTA);
			g.fillOval(r.x, r.y, r.width, r.height); // 스레드가 새로 생성한 타원을 그린다.
		}
		
		// 마우스 리스너. 
		// 마우스가 패널에 올라오면 스레드를 깨워고 마우스가 패널에서 내려가면 스레드를 일시 중지시킨다.
		class MouseHandler extends MouseAdapter {
			public void mouseEntered(MouseEvent e) {
				setTitle("Make Drawing to Start"); // 프레임 타이틀 변경
				th.wakeup(); // 스레드를 깨워 타원을 생성하도록 한다.
			}
			public void mouseExited(MouseEvent e) {
				setTitle("Make Drawing to Pause"); // 프레임 타이틀 변경				
				th.pause(); // 스레드를 중지 시켜 타원 생성을 중지하도록 한다.
			}			
		}
	}
	
	// 300ms 간격으로 타원을 생성하는 스레드
	class DrawingThread extends Thread {
		private boolean running = true; // 스레드 상태(true: 타원생성이 허락된 상태)
		
		// wait-notify 동기화를 위한 객체
		// UI 스레드와 DrawingThread 사이의 동기화를 위한 객체
		private Object obj = new Object();
		private DrawingPanel p; // 타원을 그릴 패널 주소
		private Random r = new Random(new Date().getTime()); // 랜덤수 생성기

		public DrawingThread(DrawingPanel p) {
			this.p = p;
		}

		// wait 상태인 스레드를 깨운다.
		public void wakeup() {
			running = true; // 타원만들기 허용
			synchronized(obj) {
				obj.notify(); // obj를 기다리는 스레드를 깨운다.
			}
		}
		
		public void pause() {
			running = false; // 타원만들기 중지
		}
		
		// 300ms 간격으로 타원을 생성하는 스레드 코드
		public void run() {
			try {
				synchronized(obj) {
					// 스레드가 시작되면 처음에 한 번 타원 그리기 시작을 알릴 때까지 대기한다.
					obj.wait(); 
				}
			}
			catch(InterruptedException e) { return; }

			// 300 ms 간격으로 타원을 생성한다.
			while(true) {
				if(p.isValid()) { // 패널이 만들어져서 사용가능하다면
					if(!running) { //타원만들기가 허용되어 있지 않다면
						try {
							synchronized(obj) {
								obj.wait(); // 타원만들기가 허용될 때까지 기다린다.
							}
						}
						catch(InterruptedException e) {	return; }
					}
					int x = r.nextInt(p.getWidth()); // 패널의 폭보다 작은 랜덤 정수
					int y = r.nextInt(p.getHeight()); // 패널의 넓이보다 작은 랜덤 정수
					int w = r.nextInt(100)+1; // (1, 100) 사이의 랜덤 정수
					int h = r.nextInt(100)+1; // (1, 100) 사이의 랜덤 정수
				
					p.setObj(x,y,w,h); // 패널에 그릴 타원 정보 전달
					
					// 패널로 하여금 이전에 그린 타원을 지우고 새로운 타원으로 다시 그리기 지시
					// 결과적으로 패널의 paintComponent()가 호출된다.
					p.repaint(); 		
				}
				try {
					Thread.sleep(300); // 300ms 초 동안 잠을 잔다.
				}
				catch(InterruptedException e){return;}
			}
		}
	}
	
	public static void main(String[] args) {
		new ThreadWaitNotifyEx();
	}
}
