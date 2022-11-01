import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class VibratingFrame extends JFrame{
	public VibratingFrame() {
		setTitle("진동하는 레이블 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		JLabel label = new JLabel("진동 레이블");
		label.setFont(new Font("고딕", Font.PLAIN, 20));
		label.setLocation(100,100);
		label.setSize(150, 20);
		c.add(label);
		
		setSize(300,300);
		setLocation(100,100);
		setVisible(true);
		
		VibratingThread thread = new VibratingThread(label); // 레이블을 진동시키도록 지시
		thread.start();
	}

	// Component를 상속받은 어떤 컴포넌트도 진동시킬 수 있는 스레드
	class VibratingThread extends Thread {
		private Component comp; // Component를 상속받은 어떤 컴포넌트
		public VibratingThread(Component comp) {
			this.comp = comp;
		}
		
		@Override
		public void run() {
			Random r = new Random();
			int y = comp.getY(); // 진동시키고자하는 컴포넌트의 시작 위치 x
			int x = comp.getX(); // 진동시키고자하는 컴포넌트의 시작 위치 y
			
			while(true) {
				try {
					Thread.sleep(10); // 10ms초 
				}
				catch(InterruptedException e) { return; }
				
				// sign은 방향
				int sign = (r.nextBoolean())? 1 : -1;
				int tmpX = x + r.nextInt(5)*sign;

				sign = (r.nextBoolean())? 1 : -1;
				int tmpY = y + r.nextInt(5)*sign;
				
				comp.setLocation(tmpX, tmpY);
			}
		}

	}
	public static void main(String [] args) {
		new VibratingFrame();
	}
} 
