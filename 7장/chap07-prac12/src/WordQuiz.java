import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

class Word { // ���� �ܾ�� �ѱ� �ܾ ������ ���� �ϳ��� �ܾ� ǥ��
	private String english; // ���� �ܾ�
	private String korean; // ���� �ܾ �ش��ϴ� �ѱ� �ܾ�
	public Word(String english, String korean) {
		this.english = english;
		this.korean = korean;
	}
	public String getEnglish() { return english; }
	public String getKorean() { return korean; }
	
}
public class WordQuiz {
	private String name; // WordQuiz ���α׷��� �̸�
	private Scanner scanner = new Scanner(System.in);
	private Vector<Word> v;

	public WordQuiz(String name) {
		this.name = name;
		v = new Vector<Word>();
		v.add(new Word("love", "���"));
		v.add(new Word("animal", "����"));
		v.add(new Word("emotion", "����"));
		v.add(new Word("human", "�ΰ�"));
		v.add(new Word("stock", "�ֽ�"));
		v.add(new Word("trade", "�ŷ�"));
		v.add(new Word("society", "��ȸ"));
		v.add(new Word("baby", "�Ʊ�"));
		v.add(new Word("honey", "����"));
		v.add(new Word("dall", "����"));
		v.add(new Word("bear", "��"));
		v.add(new Word("picture", "����"));
		v.add(new Word("painting", "�׸�"));
		v.add(new Word("fault", "����"));
		v.add(new Word("example", "����"));
		v.add(new Word("eye", "��"));
		v.add(new Word("statue", "������"));
	}
	
	// ex[] �迭�� 4���� ���⸦ �����. ����� ���� �ܾ� ���Ϳ� �ִ� �ܾ �����ϰ� 4���� �����ϰ�, ���Ϳ� ���� �ε�����
	// ex[] �迭�� �����Ѵ�.
	// answerIndex�� ������ �ִ� ������ �ε����̹Ƿ�, ex []���� answerIndex ���� ���� �ʵ��� �Ѵ�.
	// �׷��Ƿ� �� �޼ҵ尡 ������ ���� answerIndex�� ���� ex[] �迭�� ���������, ex[] �迭�� ���� ������ �ε���Ʋ
	// �����Ѵ�. �� �޼ҵ尡 ���� �� �� ��ġ�� answerIndex�� �ɴ´�. 
	private int makeExample(int ex[], int answerIndex) {
		int n[] = {-1, -1, -1, -1}; // -1�� �ʱ�ȭ
		int index;
		for(int i=0; i<n.length; i++) {
			do {
				index = (int)(Math.random()*v.size());
			} while(index == answerIndex || exists(n, index)); // �ٽ� �õ�
			n[i] = index;
		}

		for(int i=0; i<n.length; i++) ex[i] = n[i];
		return (int)(Math.random()*n.length); // ex[] �迭 ���� ������ ��ġ ����. �̰��� ������ �ɴ´�.
	}
	
	// �迭 n[]�� index�� ���� �����ϸ� true, �ƴϸ� false ����
	private boolean exists(int n[], int index) {
		for(int i=0; i<n.length; i++) {
			if(n[i] == index)
				return true;
		}
		return false;
	}
	
	public void run() {
		System.out.println("**** ���� �ܾ� �׽�Ʈ ���α׷� \"" + name + "\" �Դϴ�. ****"); 
		while(true) {
			System.out.print("�ܾ� �׽�Ʈ:1, �ܾ� ����:2. ����:3>> ");
			try {
				int menu = scanner.nextInt();
				switch(menu) {
					case 1: wordQuiz(); break;					
					case 2: insertWords(); break;
					case 3: finish(); return;
					default :
						System.out.println("�߸� �Է��Ͽ����ϴ�.");
				}
			}
			catch(InputMismatchException e) { // ����ڰ� ������ �ƴ� ���ڳ� �Ǽ��� �Է��� ��� ���� ó��
				scanner.next(); // ���� ��Ʈ�� ���ۿ� �ԷµǾ� �ִ� �Է��� �о ������
				System.out.println("���ڸ� �Է��ϼ��� !!");
				// �ٽ� while ������ �ݺ�
			}
			System.out.println(); // �� �� �� �� ���
		}
	}

	private void insertWords() {
		System.out.println("���� �ܾ �׸��� �Է��ϸ� �Է��� �����մϴ�.");
		while(true) {
			System.out.print("���� �ѱ� �Է� >> ");
			String engWord = scanner.next(); // ���� �ܾ� �б�
			if(engWord.equals("�׸�"))
				break;
			
			String korWord = scanner.next(); // �ѱ� �ܾ� �б�
			v.add(new Word(engWord, korWord));
		}		
	}

	private void finish() {
		System.out.println("\"" + name + "\"�� �����մϴ�.");
		scanner.close();
	}
		
	private void wordQuiz() {
		System.out.println("���� " + v.size() + "���� �ܾ ��� �ֽ��ϴ�. -1�� �Է��ϸ� �׽�Ʈ�� �����մϴ�.");
		while(true) { 
			int answerIndex = (int)(Math.random()*v.size()); // ������ ��� �ִ� ���� �׸��� �ε���
			String eng = v.get(answerIndex).getEnglish(); // ������ �־��� ���� �ܾ�
			
			// 4���� ���⸦ ���� ������ index �迭
			int example[] = new int [4];
			
			int answerLoc = makeExample(example, answerIndex); // ������ �ִ� ���� ��ȣ
			example[answerLoc] = answerIndex; // ���⿡ ���� �ε��� ����

			// ������ ����մϴ�.
			System.out.println(eng + "?");
			
			// ���� ��� ���
			for(int i=0; i<example.length; i++)
				System.out.print("(" + (i+1) + ")" + v.get(example[i]).getKorean() + " "); // ���� ���
			
			
			// ������ �Է¹��� ������Ʈ ���
			System.out.print(":>"); // ������Ʈ
			try {
				int in = scanner.nextInt(); // ������� ���� �Է�
				if(in == -1) 
					break; //�ܾ� �׽�Ʈ�� �������� �ϴ� ���
				
				in--; // �Էµ� ����(1~4)�� 	1�� ���� 0���κ��� �����ϴ� �ε����� �ٲ�
				if(in == answerLoc)
					System.out.println("Excellent !!");
				else
					System.out.println("No. !!");
			}
			catch(InputMismatchException e) {
				scanner.next(); // ���� ��Ʈ�� ���ۿ� �ԷµǾ� �ִ� �Է��� �о ������
				System.out.println("���ڸ� �Է��ϼ��� !!");
				// �ٽ� while ������ �ݺ�
			}
		}		
	}
	
	public static void main(String[] args) {
		WordQuiz quiz = new WordQuiz("��ǰ����");
		quiz.run();
	}

}
