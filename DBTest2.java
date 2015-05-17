import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*; 
import java.util.*;
import java.util.Date;
import java.lang.*;


public class DBTest2 {
	static Connection conn = null;
	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws IOException {

		// Initialize variables for fields by data type
		String ssn;
		String firstName;
		String lastName;
		String address;
		int dno;
		int n = 0;

		int linect = 0;
		
		try {
			BufferedReader books_authors = new BufferedReader(new FileReader("src/books_authors.csv"));		
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "FIREBOY", "8708165");
					Statement stmt = conn.createStatement();
					
					
					//stmt.execute("create schema library;");
					stmt.execute("use library;");
					stmt.execute("create table book( book_id varchar(50) not null, title varchar(100) not null, primary key(book_id));");
					stmt.execute("create table book_authors( book_id varchar(50) not null, author_name varchar(50) not null,  fname varchar(50), minit varchar(50), lname varchar(50) , primary key(book_id, author_name), FOREIGN KEY (book_id) REFERENCES book(book_id));");
					String line = null;
					int i = 0;
					int jj = 0;
					int kk = 0;
					int ll = 0;
					int mm = 0;
					int nn = 0;
					
					
					int hh = 0;
					while ((line = books_authors.readLine()) != null ) 
					{
						
						String[] parts = line.split("\t");
						if (i != 0){
							if( parts[0].length() < 10){
								int temp = parts[0].length();
								for (int ii = 0; ii < 10-temp; ii++){
									parts[0] = "0" + parts[0];
								}
							}
						stmt.execute("INSERT INTO book VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[2] + "\"" + ");");
						}
						i++;
						
					}
					BufferedReader books_authors2 = new BufferedReader(new FileReader("src/books_authors.csv"));
					int a = 0;
					int b = 0;
					int c = 0;
					int d = 0;
					int e = 0;
					int f = 0;
					int g = 0;
					int h = 0;
					while ((line = books_authors2.readLine()) != null ) 
					{
						
						String[] parts = line.split("\t");
						String[] parts2 = parts[1].split("\\s+");
						if ( jj != 0 ){
						if (parts[1].contains(",")){
							String[] parts3 = parts[1].split(",\\s+");
							for ( int j = 0; j < parts3.length; j++)
							{
								String[] parts4 = parts3[j].split("\\s+");
								if (parts4.length == 2){

									if( parts[0].length() < 10){
										int temp = parts[0].length();
										for (int ii = 0; ii < 10-temp; ii++){
											parts[0] = "0" + parts[0];
										}
									}
									stmt.execute("INSERT INTO book_authors (book_id, author_name, fname, lname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts3[j] + "\"" + "," + "\"" + parts4[0] + "\"" + "," + "\"" + parts4[1] + "\"" + ");");
									c++;
								}else if(parts4.length == 3){
									//System.out.println(parts4[0] + parts4[1] + parts4[2]);
									if(parts4[2].contains("(")){
										if( parts[0].length() < 10){
											int temp = parts[0].length();
											for (int ii = 0; ii < 10-temp; ii++){
												parts[0] = "0" + parts[0];
											}
										}
										stmt.execute("INSERT INTO book_authors (book_id, author_name, fname, lname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts3[j] + "\"" + "," + "\"" + parts4[0] + "\"" + "," + "\"" + parts4[1] + "\"" + ");");
									}else{
										if( parts[0].length() < 10){
											int temp = parts[0].length();
											for (int ii = 0; ii < 10-temp; ii++){
												parts[0] = "0" + parts[0];
											}
										}
									stmt.execute("INSERT INTO book_authors (book_id, author_name, fname, minit, lname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts3[j] + "\"" + "," + "\"" + parts4[0] + "\"" + "," + "\"" + parts4[1] + "\"" + "," + "\"" + parts4[2] + "\"" + ");");
									}
									d++;
								}else if(parts4.length == 1){
									//System.out.println(parts4[0]);
									if( parts[0].length() < 10){
										int temp = parts[0].length();
										for (int ii = 0; ii < 10-temp; ii++){
											parts[0] = "0" + parts[0];
										}
									}
									stmt.execute("INSERT INTO book_authors (book_id, author_name, fname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts3[j] + "\"" + "," + "\"" + parts4[0] + "\"" + ");");
									e++;
								}
								//System.out.println( parts3[j] + parts4.length);
							}
							a++;
							//stmt.execute("INSERT INTO book VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[2] + "\"" + ");");
							//stmt.execute("INSERT INTO book_authors (book_id, author_name, fname, minit, lname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\"" + "," + "\"" + parts2[0] + "\"" + "," + "\"" + parts2[1] + "\"" + "," + "\"" + parts2[2] + "\"" + ");");
							//System.out.println(parts[0] + parts[2] + parts2[0]  + parts[1] + parts2.length);
						}else{
							String[] parts5 = parts[1].split("\\s+");
							if (parts5.length == 2){
								//System.out.println(parts5[1] + parts5[0]);
								if( parts[0].length() < 10){
									int temp = parts[0].length();
									for (int ii = 0; ii < 10-temp; ii++){
										parts[0] = "0" + parts[0];
									}
								}
								stmt.execute("INSERT INTO book_authors (book_id, author_name, fname, lname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\"" + "," + "\"" + parts5[0] + "\"" + "," + "\"" + parts5[1] + "\"" + ");");
								f++;
							}else if(parts5.length == 3){
								//System.out.println(parts5[0] + parts5[1] + parts5[2]);
								if(parts5[2].contains("(")){
									if( parts[0].length() < 10){
										int temp = parts[0].length();
										for (int ii = 0; ii < 10-temp; ii++){
											parts[0] = "0" + parts[0];
										}
									}
									stmt.execute("INSERT INTO book_authors (book_id, author_name, fname, lname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\"" + "," + "\"" + parts5[0] + "\"" + "," + "\"" + parts5[1] + "\"" + ");");
								}else{
									if( parts[0].length() < 10){
										int temp = parts[0].length();
										for (int ii = 0; ii < 10-temp; ii++){
											parts[0] = "0" + parts[0];
										}
									}
								stmt.execute("INSERT INTO book_authors (book_id, author_name, fname, minit, lname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\"" + "," + "\"" + parts5[0] + "\"" + "," + "\"" + parts5[1] + "\"" + "," + "\"" + parts5[2] + "\"" + ");");
								}
								g++;
							}else if(parts5.length == 1){
								if( parts[0].length() < 10){
									int temp = parts[0].length();
									for (int ii = 0; ii < 10-temp; ii++){
										parts[0] = "0" + parts[0];
									}
								}
								stmt.execute("INSERT INTO book_authors (book_id, author_name, fname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\"" + "," + "\"" + parts5[0] + "\"" + ");");
								h++;
							}else if(parts5.length == 4){
								if( parts[0].length() < 10){
									int temp = parts[0].length();
									for (int ii = 0; ii < 10-temp; ii++){
										parts[0] = "0" + parts[0];
									}
								}
								stmt.execute("INSERT INTO book_authors (book_id, author_name, fname, minit, lname) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\"" + "," + "\"" + parts5[0] + "\"" + "," + "\"" + parts5[1] + "\"" + "," + "\"" + parts5[parts5.length-1] + "\"" + ");");
							}else{
								if( parts[0].length() < 10){
									int temp = parts[0].length();
									for (int ii = 0; ii < 10-temp; ii++){
										parts[0] = "0" + parts[0];
									}
								}
								stmt.execute("INSERT INTO book_authors (book_id, author_name) VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\"" + ");");
							}
							b++;
						}
						}
					jj++;
					}
					
					
					stmt.execute("create table library_branch( branch_id varchar(15) not null, branch_name varchar(15) not null, address varchar(50) not null, primary key(branch_id));");
					BufferedReader library_branch = new BufferedReader(new FileReader("src/library_branch.csv"));
					while ((line = library_branch.readLine()) != null) 
					{
						String[] parts = line.split("\t");
						if (kk != 0){
						stmt.execute("INSERT INTO library_branch VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\""  + "," + "\"" + parts[2] + "\"" + ");");
						}
						kk++;
					}
					
					stmt.execute("create table book_copies( book_id varchar(50) not null, branch_id varchar(50) not null, no_of_copies varchar(50) not null, primary key(book_id, branch_id), FOREIGN KEY (book_id) REFERENCES book(book_id), FOREIGN KEY (branch_id) REFERENCES library_branch(branch_id));" );
					BufferedReader book_copies = new BufferedReader(new FileReader("src/book_copies.csv"));
					while ((line = book_copies.readLine()) != null) 
					{
						String[] parts = line.split("\t");
						if (parts.length == 3){
						if( parts[0].length() < 10){
							int temp = parts[0].length();
							for (int ii = 0; ii < 10-temp; ii++){
								parts[0] = "0" + parts[0];
							}
						}
						
						if (ll != 0){
						stmt.execute("INSERT INTO book_copies VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\""  + "," + "\"" + parts[2] + "\"" + ");");
						}
						}
						ll++;
					}
					stmt.execute("create table borrowers( card_no varchar(15) not null, fname varchar(15) not null, lname varchar(15) not null, address varchar(100) not null, city varchar(15) not null, state varchar(15) not null, phone varchar(15) not null, primary key(card_no) );");
					BufferedReader borrowers = new BufferedReader(new FileReader("src/borrowers.csv"));
					while ((line = borrowers.readLine()) != null)
					{
						String[] parts = line.split("\t");
						if (mm != 0){
							stmt.execute("INSERT INTO borrowers VALUES" + "(" + "\"" + parts[0] + "\""  + "," + "\"" + parts[1] + "\""  + "," + "\"" + parts[2] + "\"" + "," + "\"" + parts[3] + "\"" + "," + "\"" + parts[4] + "\"" + "," + "\"" + parts[5] + "\"" + "," + "\"" + parts[6] + "\"" + ");");
						}
						mm++;
					}
					stmt.execute("create table book_loans( loan_id int , book_id varchar(50) , branch_id varchar(15) , card_no varchar(15) , date_out varchar(15) , due_date varchar(15) , date_in varchar(15) , primary key(loan_id, book_id, branch_id, card_no), FOREIGN KEY (book_id) REFERENCES book(book_id), FOREIGN KEY (branch_id) REFERENCES library_branch(branch_id), FOREIGN KEY (card_no) REFERENCES borrowers(card_no) );");
					BufferedReader book_loans = new BufferedReader(new FileReader("src/book_loans.csv"));
					while ((line = book_loans.readLine()) != null)
					{
						if (nn != 0 && nn !=11 && nn < 13){
						String[] parts = line.split("\t");
						//System.out.println(parts[0]+nn);
							int temp = Integer.parseInt(parts[0]);
							//System.out.println(parts[5]+ parts[6]);
							if ( parts[6].equals("NULL") ){
							stmt.execute("INSERT INTO book_loans (loan_id, book_id, branch_id, card_no, date_out, due_date )VALUES" + "("   + temp   + "," + "\"" + parts[1] + "\""  + "," + "\"" + parts[2] + "\"" + "," + "\"" + parts[3] + "\"" + "," + "\"" + parts[4] + "\"" + "," + "\"" + parts[5] + "\"" + ");");
							}else{
								stmt.execute("INSERT INTO book_loans VALUES" + "(" + temp  + "," + "\"" + parts[1] + "\""  + "," + "\"" + parts[2] + "\"" + "," + "\"" + parts[3] + "\"" + "," + "\"" + parts[4] + "\"" + "," + "\"" + parts[5] + "\"" + "," + "\"" + parts[6] + "\"" + ");");
							}
							//System.out.println(parts[6]);
						}else if (nn == 11){
							String[] parts = line.split("\\s+");
							//System.out.println(parts[0]+nn);
								int temp = Integer.parseInt(parts[0]);
								//System.out.println(parts[5]+ parts[6]);
								if ( parts[6].equals("NULL") ){
								stmt.execute("INSERT INTO book_loans (loan_id, book_id, branch_id, card_no, date_out, due_date )VALUES" + "("   + temp   + "," + "\"" + parts[1] + "\""  + "," + "\"" + parts[2] + "\"" + "," + "\"" + parts[3] + "\"" + "," + "\"" + parts[4] + "\"" + "," + "\"" + parts[5] + "\"" + ");");
								}else{
									stmt.execute("INSERT INTO book_loans VALUES" + "(" + temp  + "," + "\"" + parts[1] + "\""  + "," + "\"" + parts[2] + "\"" + "," + "\"" + parts[3] + "\"" + "," + "\"" + parts[4] + "\"" + "," + "\"" + parts[5] + "\"" + "," + "\"" + parts[6] + "\"" + ");");
								}
							//System.out.println(parts[6]);
						}
						nn++;
					}
				
					
					stmt.execute("CREATE TABLE FINES( loan_id int , fine_amt decimal(10,2) ,  paid boolean, primary key(loan_id), FOREIGN KEY (loan_id) REFERENCES book_loans(loan_id) );");
					BufferedReader fines = new BufferedReader(new FileReader("src/book_loans.csv"));
					while ((line = fines.readLine()) != null)
					{
						if (hh != 0 && hh !=11 && hh < 13){
						String[] parts = line.split("\t");
						//System.out.println(parts[0]+hh);
							int temp = Integer.parseInt(parts[0]);
							if ( parts[6].equals("NULL") ){
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								Calendar dateOut = Calendar.getInstance();
								Date out = dateOut.getTime();
								String outString = dateFormat.format(out).toString();
								StringBuffer sqlBuffer = new StringBuffer();
								stmt.execute("update book_loans set date_in = " + "'" +outString + "'" + "where loan_id =" + temp + ";");
								sqlBuffer.append("select timestampdiff(day, due_date, date_in)");
								sqlBuffer.append(" from book_loans where loan_id = " + temp );
								System.out.println(temp);
								String sql = sqlBuffer.toString();
								Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
								Statement stmt2 = conn.createStatement();
								stmt2.executeQuery("use library;");
								ResultSet rs = stmt2.executeQuery(sql);
								rs.next();
								String overdue = rs.getString(1);
								System.out.println(overdue);
								int overduedays = (new Integer(overdue)).intValue();
								float fine = (float) (0.25 * overduedays);
								rs.close();
								conn.close();
								System.out.println(fine);
								//System.out.println(fine);
								if(overduedays <= 0){
								// stmt.execute("INSERT INTO FINES VALUES" + "(" + temp  + "," +  0  + ","  + false + ");");
								}else{
									stmt.execute("INSERT INTO FINES VALUES" + "(" + temp  + "," +  fine  + ","  + false + ");");
								}
								stmt.execute("update book_loans set date_in = " + null + " where loan_id =" + temp + ";");
							}else{
								
							     //stmt.execute("INSERT INTO FINES VALUES" + "(" + temp  + "," +  0  + ","  + false + ");");
							}
							//System.out.println(parts[6]);
						}else if (hh == 11){
							String[] parts = line.split("\\s+");
							//System.out.println(parts[0]+hh);
								int temp = Integer.parseInt(parts[0]);
								//System.out.println(parts[5]+ parts[6]);
								if ( parts[6].equals("NULL") ){
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									Calendar dateOut = Calendar.getInstance();
									Date out = dateOut.getTime();
									String outString = dateFormat.format(out).toString();
									StringBuffer sqlBuffer = new StringBuffer();
									stmt.execute("update book_loans set date_in = " + "'" +outString + "'" + "where loan_id =" + temp + ";");
									sqlBuffer.append("select timestampdiff(day, due_date, date_in)");
									sqlBuffer.append(" from book_loans where loan_id = " + temp );
									System.out.println(temp);
									String sql = sqlBuffer.toString();
									Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									Statement stmt2 = conn.createStatement();
									stmt2.executeQuery("use library;");
									ResultSet rs = stmt2.executeQuery(sql);
									rs.next();
									String overdue = rs.getString(1);
									System.out.println(overdue);
									int overduedays = (new Integer(overdue)).intValue();
									float fine = (float) (0.25 * overduedays);
									rs.close();
									conn.close();
									System.out.println(fine);
									//System.out.println(fine);
									if(overduedays <= 0){
									//stmt.execute("INSERT INTO FINES VALUES" + "(" + temp  + "," +  0  + ","  + false + ");");
									}else{
										stmt.execute("INSERT INTO FINES VALUES" + "(" + temp  + "," +  fine  + ","  + false + ");");
									}
									stmt.execute("update book_loans set date_in = " + null + " where loan_id =" + temp + ";");
								}else{
									//stmt.execute("INSERT INTO FINES VALUES" + "(" + temp  + "," + "\"" + 0 + "\""  + "," +  false + ");");
								}
							//System.out.println(parts[6]);
						}
						hh++;
					}
					//stmt.execute("create table library_branch( branch_id int not null, branch_name varchar(15) not null, address varchar(50) not null, primary key(branch_id));");
					
					//stmt.execute("create table book_copies( book_id int not null, branch_id int not null, no_of_copies int not null, primary key(book_id, branch_id), FOREIGN KEY (book_id) REFERENCES book(book_id), FOREIGN KEY (branch_id) REFERENCES library_branch(branch_id));" );
					
					//stmt.execute("create table borrowers( card_no int not null, fname varchar(15) not null, lname varchar(15) not null, address varchar(15) not null, city varchar(15) not null, state varchar(15) not null, phone varchar(15) not null, primary key(card_no) );");
					
					
					//stmt.execute("create table book_loan( book_id int not null, branch_id int not null, card_no int not null, date_out varchar(15) not null, due_date varchar(15) not null, date_in varchar(15) not null, primary key(book_id, branch_id, card_no), FOREIGN KEY (book_id) REFERENCES book(book_id), FOREIGN KEY (branch_id) REFERENCES library_branch(branch_id), FOREIGN KEY (card_no) REFERENCES borrowers(card_no) );");
					

					
					//ResultSet rs = stmt.executeQuery("");
					

					//rs.close();
					conn.close();
				System.out.println("Success!! " );

				} 
				catch(SQLException ex) {
					System.out.println("Error in connection: " + ex.getMessage());
				}
	}
}
