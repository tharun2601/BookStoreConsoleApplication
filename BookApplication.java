import java.util.*;

public class BookApplication{
	ArrayList<User> al = new ArrayList<>();
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		char ans;
		
		do {
			System.out.print("Welcome!\n\n1) User Register\n2) User Login\n3) Admin Login\n\nYour choice - ");
			int ch1 = sc.nextInt();
			switch(ch1) {
				case 1 :
					User userR = new User();
					userR.register();
					break;
				case 2:
					User userL = new User();
					userL.login();
					break;
				case 3:
					Admin admin = new Admin();
					admin.login();
					break;
				default:
					System.out.print("\nEnter a proper choice within 1 - 4");
				
			}
			System.out.print("\nDo you wish to continue in main menu(Y/N) - ");
			ans = sc.next().charAt(0);
		}while(ans == 'Y');
		System.out.print("\nThank You!");
	}
}