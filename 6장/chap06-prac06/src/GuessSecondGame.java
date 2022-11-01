import java.util.Calendar;
import java.util.Scanner;

class Player {
	private Scanner scanner = new Scanner(System.in);
	private String name;
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() { return name; }
	
	public int turn() {
		System.out.print(name + " 시작 <Enter>키>>");
		// 헌재의 초 시간 출력
		String key = scanner.nextLine(); // <Enter>키를 읽음
		Calendar c = Calendar.getInstance();
		int startSecond = c.get(Calendar.SECOND);
		System.out.println("\t현재 초 시간 = " +  startSecond);
			
		System.out.print("10초 예상 후 <Enter>키>>");
		// <Enter> 키 때가지 기다림
		key = scanner.nextLine(); // <Enter>키를 읽음
		c = Calendar.getInstance();
		int endSecond = c.get(Calendar.SECOND);
		System.out.println("\t현재 초 시간 = " +  endSecond);
		
		if(endSecond < startSecond)
			endSecond += 60; // 60초 후로 만들어야 함
		
		return Math.abs(startSecond-endSecond);  // 절대값 구하기		
	}	
}

public class GuessSecondGame {
	public GuessSecondGame() {
		
	}
	public void run() {
		Player player[] = new Player[2];
		player[0] = new Player("황기태");
		player[1] = new Player("이재문");
	
		System.out.println("10초에 가까운 사람이 이기는 게임입니다.");
		int duration1 = player[0].turn();
		int duration2 = player[1].turn();
		
		System.out.print(player[0].getName()+"의 결과 " + duration1 + ", ");
		System.out.print(player[1].getName()+"의 결과 " + duration2);		
		
		System.out.print(", 승자는 ");
		if(Math.abs(10-duration1) < Math.abs(10-duration2)) // 시간 간격이 작은 사람이 이김
			System.out.println(player[0].getName());
		else
			System.out.println(player[1].getName());			
	}
	
	public static void main(String[] args) {
		GuessSecondGame game = new GuessSecondGame();
		game.run();
	}

}
