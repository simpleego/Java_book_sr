import java.util.Scanner;

public class Player {
	private String name;
	private Scanner scanner = new Scanner(System.in);
	public Player(String name) {
		this.name = name;
	}
	public String getName() {return name;}
	public void getEnterKey() {
		scanner.nextLine(); // <Enter> Ű�� ��ٸ���.
	} 
	public boolean turn() {
		System.out.print("[" + name + "]:<Enter>");			
		getEnterKey(); // �����ڰ� <Enter>Ű �Է��� ������ ��ٸ�
		
		int num[] = new int [3]; // 3���� ������ �����ϱ� ���� �迭
		 // 3���� ���� ���� 
		for (int i=0; i<num.length; i++) {
			num[i] = (int)(Math.random()*3 + 1); // 1~3������ ������ �� �߻�
		}
		
		 // 3���� ���� ���
		System.out.print("\t\t");
		for (int i=0; i<num.length; i++) {
			System.out.print(num[i]+"\t");
		}
	
		 // 3���� ������ ������ ��
		boolean result = true;
		for (int i=0; i<num.length; i++) {
			if (num[i] != num[0]) { // �ϳ��� �ٸ��� false
				result = false; // ���� ����
				break;
			}
		}
		
		return result; // result�� true �̸� �¸�
	}
}
