import java.util.Scanner;

public class StringRotate {

	public static void main(String[] args) {
		System.out.println("문자열을 입력하세요. 빈 칸이나 있어도 되고 영어 한글 모두 됩니다.");
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();
		int len = text.length();
		for(int i=0; i<len; i++) {
			String first = text.substring(0,1); //인덱스 0에서 1까지 1개 문자
			String last = text.substring(1); // 인덱스 1에서 끌까지
			text = last + first;
			System.out.println(text);
		}
		scanner.close();;
	}

}
