import java.util.Scanner;

public class GamblingGame {
	private Player [] p = new Player[2]; // 2��� 3���� ��ġ�� 3���� �ϴ� ������ �ȴ�.
	private Scanner scanner = new Scanner(System.in);
	
	public GamblingGame() {
		for(int i=0; i<p.length; i++) {
			System.out.print((i+1)+"��° ���� �̸�>>");
			p[i] = new Player(scanner.nextLine());
		}
	}
	
	public void run() {
		int i=0;
		while (true) {
			if (p[i].turn()) { // ���� i�� �� ���� ��� ���� ���
				System.out.println(p[i].getName()+"���� �̰���ϴ�!");
				break;
			}
			else {
				System.out.println("�ƽ�����!");
				i++; // ���� ���
				i = i%p.length; // �� ����� ������ ������
			}
		}
	}
		
	public static void main(String [] args) {
		GamblingGame game = new GamblingGame();
		game.run();
	}
}
