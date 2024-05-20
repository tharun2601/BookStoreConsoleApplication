import java.sql.*;
import java.util.*;

public class DB {
	
	//User user = new User();
	ConnectionClass con = new ConnectionClass();
	Connection conn = con.getConn();
	
	public boolean isValid(String uname, String pass) {
		String query = "Select * from users where USERNAME = ? and PASSWORD = ?;";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			
			pst.setString(1, uname);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
				return true;
			
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isUnique(String uname) {
		
		String query = "Select * from users where USERNAME = ?;";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			
			pst.setString(1, uname);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
				return false;
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addUser(String uname, String pass, String email) {
		
		String query = "insert into users(USERNAME, PASSWORD, EMAIL) values(?,?,?);";
		
		
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			
			pst.setString(1, uname);
			pst.setString(2, pass);
			pst.setString(3, email);
			
			int count = pst.executeUpdate();
			
			if(count == 1) {
				System.out.print("User Registered Successfully!");
			}
			else {
				System.out.print("Sorry, registration was not complete");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void viewBooks() {
		
		String query = "select * from books;";
		
		try {
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			
			System.out.println("\n\tBOOK ID\tBOOK NAME\tAUTHOR NAME\tGENRE\tPRICE\tQUANTITY\t");
			while(rs.next()) {
				System.out.println("\t"+rs.getString("BOOK_ID") + "\t"+rs.getString("BOOK_NAME")+"\t"+rs.getString("AUTHOR_NAME")+"\t"+rs.getString("GENRE")+"\t"+rs.getString("PRICE")+"\t"+rs.getString("QUANTITY")+"\t");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void searchBooks() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the book's name, or author's name or the price or the genre - ");
		String search = sc.next();
		
		String query1 = "select * from books where BOOK_NAME = ?";
		String query2 = "select * from books where AUTHOR_NAME = ?";
		String query3 = "select * from books where GENRE = ?";
		String query4 = "select * from books where PRICE = ?";
		
		try {
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, search);
			PreparedStatement pst2 = conn.prepareStatement(query2);
			pst2.setString(1, search);
			PreparedStatement pst3 = conn.prepareStatement(query3);
			pst3.setString(1, search);
			PreparedStatement pst4 = conn.prepareStatement(query4);
			pst4.setString(1, search);
			
			
			ResultSet rs1 = pst1.executeQuery();
			ResultSet rs2 = pst2.executeQuery();
			ResultSet rs3 = pst3.executeQuery();
			ResultSet rs4 = pst4.executeQuery();
			
			int display = 0;
			
			if(rs1.next()) {
				if(display == 0) {
					System.out.println("\n\tBOOK ID\tBOOK NAME\tAUTHOR NAME\tGENRE\tPRICE\tQUANTITY\t");
					display = 1;
				}
				System.out.println("\t"+rs1.getString("BOOK_ID") + "\t"+rs1.getString("BOOK_NAME")+"\t"+rs1.getString("AUTHOR_NAME")+"\t"+rs1.getString("GENRE")+"\t"+rs1.getString("PRICE")+"\t"+rs1.getString("QUANTITY")+"\t");
			}
			else if(rs2.next()) {
				if(display == 0) {
					System.out.println("\n\tBOOK ID\tBOOK NAME\tAUTHOR NAME\tGENRE\tPRICE\tQUANTITY\t");
					display = 1;
				}
				System.out.println("\t"+rs2.getString("BOOK_ID") + "\t"+rs2.getString("BOOK_NAME")+"\t"+rs2.getString("AUTHOR_NAME")+"\t"+rs2.getString("GENRE")+"\t"+rs2.getString("PRICE")+"\t"+rs2.getString("QUANTITY")+"\t");
			}
			else if(rs3.next()) {
				if(display == 0) {
					System.out.println("\n\tBOOK ID\tBOOK NAME\tAUTHOR NAME\tGENRE\tPRICE\tQUANTITY\t");
					display = 1;
				}
				System.out.println("\t"+rs3.getString("BOOK_ID") + "\t"+rs3.getString("BOOK_NAME")+"\t"+rs3.getString("AUTHOR_NAME")+"\t"+rs3.getString("GENRE")+"\t"+rs3.getString("PRICE")+"\t"+rs3.getString("QUANTITY")+"\t");
			}
			else if(rs4.next()){
				if(display == 0) {
					System.out.println("\n\tBOOK ID\tBOOK NAME\tAUTHOR NAME\tGENRE\tPRICE\tQUANTITY\t");
					display = 1;
				}
				System.out.println("\t"+rs4.getString("BOOK_ID") + "\t"+rs4.getString("BOOK_NAME")+"\t"+rs4.getString("AUTHOR_NAME")+"\t"+rs4.getString("GENRE")+"\t"+rs4.getString("PRICE")+"\t"+rs4.getString("QUANTITY")+"\t");
			}
			else {
				System.out.println("\n NO RECORDS FOUND! ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void buyBooks(User user, String uname) {
		// TODO Auto-generated method stub
		
		viewBooks();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nChose you Book Id to purchase - ");
		int bookID = sc.nextInt();
		
		try {
			
			
				String query = "select * from books where BOOK_ID = ?;";
				
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setInt(1,bookID);
				
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					String qty = rs.getString("QUANTITY");
					if(Integer.parseInt(qty) == 0) {
						System.out.print("Sorry, the book is out of stock");
					}
					else {
						System.out.print("How many books do you want ?");
						int reqQty = sc.nextInt();
						
						
						if(reqQty <= 0) {
							System.out.print("");
						}
						else if(Integer.parseInt(qty)<reqQty) {
							System.out.print("The available quantity is "+qty+" and please enter the quantity less or equal to this.");
						}
						else {
							int updQty = Integer.parseInt(qty)-reqQty;
							updateQuantity(bookID, updQty);
							addBooks(user,uname, bookID, reqQty);
							System.out.print("Book is purchased!");
						}
						
					}
				}
				else {
					System.out.print("No such BookID exists!");
				}
				
				//System.out.print("Do you want to search");
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void updateQuantity(int bookID, int updQty) {
		// TODO Auto-generated method stub
		
		String query = "update books set QUANTITY=? where BOOK_ID=?;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, updQty);
			pst.setInt(2,bookID);
			
			int rs = pst.executeUpdate();
			if(rs==0) {
				System.out.print("Quantity update failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void addBooks(User user, String uname, int bookID, int qty) {
		// TODO Auto-generated method stub
		
		String bookName, author;
		
		String query = "select * from books where BOOK_ID=?;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, bookID);
			
			ResultSet rs = pst.executeQuery();
			
			
			if(rs.next()) {
				
				String query1 = "select * from "+uname+"mybooks;";
				Statement st1 = conn.createStatement();
				
				ResultSet rs1 = st1.executeQuery(query1);
				
				int flag = 0;
				
				while(rs1.next() && rs1.getString("BOOK_ID").equals(bookID+"")) {
					
					String addBooks = "update "+uname+"mybooks set QUANTITY = "+ (Integer.parseInt(rs1.getString("QUANTITY")) + qty) + " where BOOK_ID = " + bookID +";";
				
					Statement st = conn.createStatement();
					int rs2 = st.executeUpdate(addBooks);
					flag=1;
				}
				if(flag==0) {
					String addBooks = "insert into " +uname+"mybooks values("+rs.getString("BOOK_ID")+", '"+rs.getString("BOOK_NAME")+"', '"+rs.getString("AUTHOR_NAME")+"', "+qty+");";
					
					Statement st = conn.createStatement();
					int rs2 = st.executeUpdate(addBooks);
				}
				
			}
			else {
				System.out.print("Invalid entry!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void myBooks(User user, String uname) {
		// TODO Auto-generated method stub
		
		int f=0;
		
//		for(String books : user.myBooks) {
//			f=1;
//			if(f==1) {
//				System.out.print("BOOK_ID  BOOK_NAME   AUTHOR   QUANTITTY\n\n");
//				f++;
//			}
//			System.out.println(books);
//		}
//		if(f==0) {
//			System.out.print("No books purchased yet.");
//		}
//		System.out.println("");
//		
//		
		String tablename = uname + "mybooks";
		
		String query = "select * from " + tablename +";";
		
		try {
			Statement st = conn.createStatement();
		
			ResultSet rs = st.executeQuery(query);
			f=1;
			
			while(rs.next()) {
				if(f==1) {
					System.out.print("BOOK_ID  BOOK_NAME   AUTHOR   QUANTITTY\n\n");
					f++;
				}
				System.out.print(rs.getString("BOOK_ID")+"  "+rs.getString("BOOK_NAME")+"  "+rs.getString("AUTHOR")+"  "+rs.getString("QUANTITY"));
				System.out.println("");
			}
			if(f==1) {
				System.out.print("No books purchased yet.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createTable(String uname) {
		// TODO Auto-generated method stub
		String query = "create table "+uname+"mybooks(BOOK_ID int, BOOK_NAME varchar(30), AUTHOR varchar(30), QUANTITY int);";
		
		try {
			Statement st = conn.createStatement();
			int rs = st.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
