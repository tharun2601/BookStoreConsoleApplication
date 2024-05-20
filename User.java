import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class User {
	
	ArrayList<String> myBooks;
	ConnectionClass con = new ConnectionClass();
	Connection conn = con.getConn();
	
	User(){
		myBooks = new ArrayList<>();
		
		
	}
	
	


	Scanner sc = new Scanner(System.in);
	
	DB db = new DB();
	
	
	public void login() {
		char ans;
		do {
			System.out.print("Enter your user name - ");
			String uname = sc.next();
			
			System.out.print("Enter your password - ");
			String pass = sc.next();
				
			if(db.isValid(uname, pass)) {
				
				displayUserOptions(uname);
			}
			else {
				System.out.print("Invalid Credentials!");
			}
			System.out.print("\nDo you want to try again for another login(Y/N) - ");
			ans = sc.next().charAt(0);
		}while(ans == 'Y');
	}
	
	public void register() {
		
		char ans;
		do {
			System.out.print("Enter your user name (must be unique) - ");
			String uname = sc.next();
			
			if(db.isUnique(uname)) {
				System.out.print("Enter your password - ");
				String pass = sc.next();
				
				System.out.print("Enter your email - ");
				String email = sc.next();
				
				db.addUser(uname, pass, email);
				db.createTable(uname);
				break;
			}
			else {
				System.out.print("Already exists, try to login!");
			}
			System.out.print("\nDo you want to try again for another register(Y/N) - ");
			ans = sc.next().charAt(0);
		}while(ans == 'Y');
		
		
	}
	
	public void displayUserOptions(String uname) {
		char ans;
		User user = new User();
		do {
			System.out.print("Welcome " + uname + " !\n\n1) View Books\n2) Search Books\n3) Buy Books \n4) My Books\n\nYour choice - ");
			int ch1 = sc.nextInt();
			switch(ch1) {
				case 1 :
					db.viewBooks();
					break;
				case 2:
					db.searchBooks();
					break;
				case 3:
					db.buyBooks(user, uname);
					break;
				case 4:
					db.myBooks(user, uname);
					break;
				default:
					System.out.print("\nEnter a proper choice within 1 - 4");
				
			}
			System.out.print("\nDo you wish to continue in User menu(Y/N) - ");
			ans = sc.next().charAt(0);
		}while(ans == 'Y');
	}

	

}
