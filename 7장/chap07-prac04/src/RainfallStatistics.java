import java.util.*;

public class RainfallStatistics {
	
	public static void print(Vector<Integer> v) {
		int sum = 0;
		Iterator<Integer> it = v.iterator();
		while(it.hasNext()) {
			int n = it.next();
			System.out.print(n + " ");
			sum += n;
		}
		System.out.println();
		System.out.println("���� ��� " + sum/v.size());		
		
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Vector<Integer> v = new Vector<Integer>();
		while(true) {
			System.out.print("������ �Է� (0 �Է½� ����)>> ");
			int n = scanner.nextInt();
			if(n == 0) 
				break;
			v.add(n);
			print(v);
		}
		
		scanner.close();
	}

}
