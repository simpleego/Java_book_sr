import java.util.Scanner;

public class StringRotate {

	public static void main(String[] args) {
		System.out.println("���ڿ��� �Է��ϼ���. �� ĭ�̳� �־ �ǰ� ���� �ѱ� ��� �˴ϴ�.");
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();
		int len = text.length();
		for(int i=0; i<len; i++) {
			String first = text.substring(0,1); //�ε��� 0���� 1���� 1�� ����
			String last = text.substring(1); // �ε��� 1���� ������
			text = last + first;
			System.out.println(text);
		}
		scanner.close();;
	}

}
