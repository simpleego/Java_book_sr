import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BubbleGameFrame extends JFrame{
	public BubbleGameFrame() {
		setTitle("버블 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel p = new GamePanel();
		setContentPane(p);
		setSize(300,300);	
		setVisible(true);
	}
	
	public static void main(String [] args) {
		new BubbleGameFrame();
	}
}
class GamePanel extends JPanel {
	public GamePanel() {
		setLayout(null);
		addMouseListener(new MouseAdapter() {		
			@Override
			public void mousePressed(MouseEvent e) {
				BubbleThread bubbleThread = new BubbleThread(e.getX(), e.getY());
				bubbleThread.start();
			}
		});
	}
	
	class BubbleThread extends Thread {
		private JLabel bubble;
		public BubbleThread(int bubbleX, int bubbleY) {
			ImageIcon img = new ImageIcon("images/bubble.jpg");
			bubble = new JLabel(img);
			bubble.setSize(img.getIconWidth(),img.getIconWidth());
			bubble.setLocation(bubbleX, bubbleY);
			add(bubble); // GamePanel에 add()
			GamePanel.this.repaint();
		}
		
		@Override
		public void run() {
			while(true) {
				int x = bubble.getX() ;
				int y = bubble.getY() - 5;
				if(y < 0) {
					GamePanel.this.remove(bubble); // 컴포넌트 제거
					GamePanel.this.repaint(); // 컴포넌트 제거 후 패널 다시 그리기
					return; // thread ends
				}
				bubble.setLocation(x, y); // 컴포넌트 위치 이동
				GamePanel.this.repaint(); // 이동된 컴포넌트 다시 크리기
				try {
					sleep(200);
				}
				catch(InterruptedException e) {}
			}
		}
	}
}
