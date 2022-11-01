import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class VibratingFrame extends JFrame{
	public VibratingFrame() {
		setTitle("�����ϴ� ������ �����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		setLocation(100,100);
		setVisible(true);
		
		VibratingThread thread = new VibratingThread(this);
		thread.start();
	}

	// Component�� ��ӹ��� � ������Ʈ�� ������ų �� �ִ� ������
	class VibratingThread extends Thread {
		private Component comp; // Component�� ��ӹ��� � ������Ʈ
		public VibratingThread(Component comp) {
			this.comp = comp;
		}
		
		@Override
		public void run() {
			Random r = new Random();
			int y = comp.getY(); // ������Ű�����ϴ� ������Ʈ�� ���� ��ġ x
			int x = comp.getX(); // ������Ű�����ϴ� ������Ʈ�� ���� ��ġ y
			
			while(true) {
				try {
					Thread.sleep(10); // 10ms�� 
				}
				catch(InterruptedException e) { return; }
				
				// sign�� ����
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
