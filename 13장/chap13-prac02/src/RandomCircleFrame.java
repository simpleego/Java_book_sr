import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RandomCircleFrame extends JFrame {
	public RandomCircleFrame() {
		super("���� 0.5�� �������� ������ ��ġ�� �̵���Ű�� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new CirclePanel());
		setSize(250,250);
		setVisible(true);
	}
	
	class CirclePanel extends JPanel implements Runnable {
		private int x = 100; // ���� �׷����� ��ġ
		private int y = 100;
		
		public CirclePanel() {
			this.addMouseListener(new MouseAdapter() {
				private Thread th = null;
				
				@Override
				public void mousePressed(MouseEvent e) {
					if(th == null) { // �����尡 ���� ��������� �ʾ��� ���� ������ ����
						th = new Thread(CirclePanel.this);
						th.start();
					}
				}
			});
			
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.MAGENTA);
			g.drawOval(x, y, 50, 50); // (x, y)���� 50x50 ũ�� ��
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) { return; }
				
				// ���� �׷����� ��ġ�� �����ϰ� ����
				x = (int)(Math.random()*this.getWidth()); // �г��� ���� ������ ����
				y = (int)(Math.random()*this.getHeight());
				repaint();
			}
		}		
	}
	
	public static void main(String[] args) {
		new RandomCircleFrame();
	}
}
