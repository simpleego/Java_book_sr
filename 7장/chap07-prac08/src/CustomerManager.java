import java.util.*;

public class CustomerManager {
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	private Scanner scanner = new Scanner(System.in);
	
	public CustomerManager() { }
	
	public void run() {
		System.out.println("** ����Ʈ ���� ���α׷��Դϴ� **");

		while(true) {
			System.out.print("�̸��� ����Ʈ �Է�>> ");
			String name = scanner.next();
			if(name.equals("�׸�"))
				break;
			int point = scanner.nextInt();
			
			Integer n = map.get(name); // �̸����� ����Ʈ �˻�
			if(n != null) { // �ű԰� �ƴ� ���
				point += n; // ����Ʈ ���� ����
			}
			
			map.put(name, point); // �̸��� ���� ����Ʈ ����
			printAll();
		}
	}
	
	void printAll() {
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String name = it.next();
			int point = map.get(name);
			System.out.print("("+name+","+point+")");			
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		CustomerManager man = new CustomerManager();
		man.run();
	}
}

