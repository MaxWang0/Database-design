

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.Random; 

import javax.swing.*;

public class DialogFactory extends JDialog {
	Font largeFont = new Font("TimesRoman", Font.BOLD, 20);
	JLabel firstNameLabel = new JLabel("First Name: ");
	JTextField firstNameField = new JTextField(20);
	JLabel lastNameLabel = new JLabel("Last Name: ");
	JTextField lastNameField = new JTextField(20);
	JLabel addressLabel = new JLabel("Address: ");
	JTextField addressField = new JTextField(20);
	JLabel cityLabel = new JLabel("City: ");
	JTextField cityField = new JTextField(20);
	JLabel stateLabel = new JLabel("State: ");
	JTextField stateField = new JTextField(20);
	JLabel phoneLabel = new JLabel("Phone: ");
	JTextField phoneField = new JTextField(20);
	JLabel bookIDLabel = new JLabel("Book ID: ");
	JTextField bookIDField = new JTextField(20);
	JLabel branchIDLabel = new JLabel("Branch ID: ");
	JTextField branchIDField = new JTextField(20);
	JLabel cardNoLabel = new JLabel("Card No: ");
	JTextField cardNoField = new JTextField(20);
	JLabel borrowerLabel = new JLabel("Borrower: ");
	JTextField borrowerField = new JTextField(20);
	JLabel titleLabel = new JLabel("Title: ");
	JTextField titleField = new JTextField(20);
	JLabel authorLabel = new JLabel("Author: ");
	JTextField authorField = new JTextField(20);
	JLabel copiesLabel = new JLabel("Copies: ");
	JTextField copiesField = new JTextField(20);
	JTable resultTable = new JTable();
	JPanel tablePanel = new JPanel();
	JLabel fineLabel = new JLabel("Fine: ");
	JTextField fineText = new JTextField(20);
	
	protected DialogFactory(GUI father, String type){
		super(father, type, true);
		setResizable(false);
		tablePanel.setLayout(new GridLayout(1, 0));
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		switch(type){
			case "New Borrower":{
				this.setLayout(new GridLayout(7,1,5,5));
				firstNameLabel.setFont(largeFont);
				firstNameField.setFont(largeFont);
				lastNameLabel.setFont(largeFont);
				lastNameLabel.setFont(largeFont);
				addressLabel.setFont(largeFont);
				addressField.setFont(largeFont);
				cityLabel.setFont(largeFont);
				cityField.setFont(largeFont);
				stateLabel.setFont(largeFont);
				stateField.setFont(largeFont);
				phoneLabel.setFont(largeFont);
				phoneField.setFont(largeFont);
				okButton.setFont(largeFont);
				cancelButton.setFont(largeFont);
				add(firstNameLabel);
				add(firstNameField);
				add(lastNameLabel);
				add(lastNameField);
				add(addressLabel);
				add(addressField);
				add(cityLabel);
				add(cityField);
				add(stateLabel);
				add(stateField);
				add(phoneLabel);
				add(phoneField);
				add(okButton);
				add(cancelButton);
				okButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String firstName = firstNameField.getText();
						String lastName = lastNameField.getText();
						String address = addressField.getText();
						String city = cityField.getText();
						String state = stateField.getText();
						String phone = phoneField.getText();
						if(firstName.length() == 0 || lastName.length() == 0 || address.length() == 0){
							JOptionPane.showMessageDialog(null, "Must Fill these fields!!");
						}
						else{
							StringBuffer sqlBuffer = new StringBuffer();
							sqlBuffer.append("Select Fname, Lname, Address From borrowers Where");
							sqlBuffer.append(" Fname='" + firstName + "' AND Lname='" + lastName + "' AND Address='" + address + "';");
							String sql = sqlBuffer.toString();
							try{
								Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
								Statement stmt = conn.createStatement();
								stmt.executeQuery("use library;");
								ResultSet rs = stmt.executeQuery(sql);
								
								int count = 0;
								while(rs.next()){
									count++;
								}
								rs.close();
								conn.close();
								
								if(count == 0){
									sql = new String("Select card_no From borrowers Order By card_no DESC;");
									conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									stmt = conn.createStatement();
									stmt.executeQuery("use library;");
									rs = stmt.executeQuery(sql);
									rs.next();
									String highestCardNoString = rs.getString(1);
									rs.close();
									conn.close();
									
									long highestCardNo = (new Long(highestCardNoString)).longValue();
									String card_no = (new Long(highestCardNo + 1)).toString();
									
									sqlBuffer = new StringBuffer();
									
									if(phone.length() != 0){
										sqlBuffer.append("Insert Into Borrowers (card_no, Fname, Lname, Address, City, State, Phone)");
										sqlBuffer.append(" Values ('" + card_no + "', '" + firstName + "', '" + lastName + "', '" 
													+ address + "', '" + city + "', '" + state + "','" + phone + "');");	
									}
									else{
										sqlBuffer.append("Insert Into Borrowers (card_no, Fname, Lname, Address, City, State, Phone)");
										sqlBuffer.append(" Values ('" + card_no + "', '" + firstName + "', '" + lastName + "', '" 
													+ address + "', '" + city + "', '" + state + "', '" + phone + "');");
									}
									sql = sqlBuffer.toString();
									System.out.println(sql);
									conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									stmt = conn.createStatement();
									stmt.executeQuery("use library;");
									stmt.executeUpdate(sql);
									conn.close();
									dispose();
								}
								else{
									JOptionPane.showMessageDialog(null, "This person has registered!!");
								}
							} catch(SQLException ex){
								ex.printStackTrace();
							}
						}
					}
				});
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
					}
				});
				pack();
				setVisible(true);
				break;	
			}
			case "Check Out":{
				this.setLayout(new GridLayout(4,1,5,5));
				bookIDLabel.setFont(largeFont);
				bookIDField.setFont(largeFont);
				branchIDLabel.setFont(largeFont);
				branchIDField.setFont(largeFont);
				cardNoLabel.setFont(largeFont);
				cardNoField.setFont(largeFont);
				okButton.setFont(largeFont);
				cancelButton.setFont(largeFont);
				add(bookIDLabel);
				add(bookIDField);
				add(branchIDLabel);
				add(branchIDField);
				add(cardNoLabel);
				add(cardNoField);
				add(okButton);
				add(cancelButton);
				okButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String bookID = bookIDField.getText();
						String branchID = branchIDField.getText();
						String cardNo = cardNoField.getText();
						if(bookID.length() == 0 || branchID.length() == 0 || cardNo.length() == 0){
							JOptionPane.showMessageDialog(null, "Must Fill these fields!!");
						}
						else{
							try{
								StringBuffer sqlBuffer = new StringBuffer();
								sqlBuffer.append("Select * From borrowers where card_no='" + cardNo + "';");
								String sql = sqlBuffer.toString();
								Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
								Statement stmt = conn.createStatement();
								stmt.executeQuery("use library;");
								ResultSet rs = stmt.executeQuery(sql);
								if(!rs.next()){
									rs.close();
									conn.close();
									JOptionPane.showMessageDialog(null, "No Such Borrower!!");
								}
								else{
									rs.close();
									conn.close();
									sqlBuffer = new StringBuffer();
									sqlBuffer.append("Select count(*) From book_loans");
									sqlBuffer.append(" Where card_no='" + cardNo + "' AND date_in IS NULL Group By card_no;");
									sql = sqlBuffer.toString();
									conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									stmt = conn.createStatement();
									stmt.executeQuery("use library;");
									rs = stmt.executeQuery(sql);
									int count;
									if(!rs.next()){
										count = 0;
									}
									else{
										String countString = rs.getString(1);
										count = (new Integer(countString)).intValue();
									}
									rs.close();
									conn.close();
									if(count >= 3){
										JOptionPane.showMessageDialog(null, "This person has already borrowed three books!!");
									}
									else{
										sqlBuffer = new StringBuffer();
										sqlBuffer.append("Select * from book where book_id='" + bookID + "';");
										sql = sqlBuffer.toString();
										conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
										stmt = conn.createStatement();
										stmt.executeQuery("use library;");
										rs = stmt.executeQuery(sql);
										if(!rs.next()){
											rs.close();
											conn.close();
											JOptionPane.showMessageDialog(null, "No Such Book!!");
										}
										else{
											rs.close();
											conn.close();
											sqlBuffer = new StringBuffer();
											sqlBuffer.append("Select * from library_branch where branch_id='" + branchID + "';");
											sql = sqlBuffer.toString();
											conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
											stmt = conn.createStatement();
											stmt.executeQuery("use library;");
											rs = stmt.executeQuery(sql);
											if(!rs.next()){
												rs.close();
												conn.close();
												JOptionPane.showMessageDialog(null, "No Such Branch!!");
											}
											else{
												sqlBuffer = new StringBuffer();
												sqlBuffer.append("Select book_copies.no_of_copies-count(card_no) From book_copies left join book_loans on book_copies.book_id=book_loans.book_id AND date_in IS NULL");
												sqlBuffer.append(" Where book_copies.book_id='" + bookID + "' AND book_copies.branch_id='" + branchID + "'");
												sqlBuffer.append(" Group By book_copies.book_id, book_copies.branch_id");
												sql = sqlBuffer.toString();
												conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
												stmt = conn.createStatement();
												stmt.executeQuery("use library;");
												rs = stmt.executeQuery(sql);
												int bookAvailable;
												if(!rs.next()){
													bookAvailable = 0;
												}
												else{
													String bookAvailableString = rs.getString(1);
													bookAvailable = (new Integer(bookAvailableString)).intValue();
												}
												rs.close();
												conn.close();
												if(bookAvailable == 0){
													JOptionPane.showMessageDialog(null, "There's no book available in this branch!!");
												}
												else{
													// Current Date and Due Date
													SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
													Calendar dateOut = Calendar.getInstance();
													Date out = dateOut.getTime();
													dateOut.set(Calendar.DATE, (dateOut.get(Calendar.DATE) + 14));
													Date due = dateOut.getTime();
													String outString = dateFormat.format(out).toString();
													String dueString = dateFormat.format(due).toString();
													
													sqlBuffer = new StringBuffer();
													StringBuffer sqlBuffer2 = new StringBuffer();
													sql = new String("Select loan_id From book_loans Order By loan_id DESC;");
													conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
													stmt = conn.createStatement();
													stmt.executeQuery("use library;");
													rs = stmt.executeQuery(sql);
													rs.next();
													String highestCardNoString = rs.getString(1);
													rs.close();
													conn.close();
													
													long highestCardNo = (new Long(highestCardNoString)).longValue();
													String loan_id = (new Long(highestCardNo + 1)).toString();
													int loan_id2 = Integer.parseInt(loan_id);
													sqlBuffer.append("Insert Into Book_Loans (loan_id, book_id, branch_id, card_no, date_out, due_date)");
													sqlBuffer.append(" Values (" + loan_id2 + ", '" + bookID + "', '" + branchID + "', '" + cardNo + "', '" + outString + "', '" + dueString + "');");
													sql = sqlBuffer.toString();
													conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
													stmt = conn.createStatement();
													stmt.executeQuery("use library;");
													stmt.executeUpdate(sql);
													conn.close();
													dispose();
												}
											}
										}
									}
								}
							} catch(SQLException ex){
								ex.printStackTrace();
							}
						}
					}
				});
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
					}
				});
				pack();
				setVisible(true);
				break;
			}
			case "Pay Fine":{
				this.setLayout(new BorderLayout());
				JButton searchButton = new JButton("Search");
				JButton updateButton = new JButton("Update");
				//JButton unpaidButton = new JButton("Unpaid");
				JButton cardnoButton = new JButton("By Cardno");
				JPanel inputPanel = new JPanel();
				inputPanel.setLayout(new GridLayout(10,10,10,10));
				bookIDLabel.setFont(largeFont);
				bookIDField.setFont(largeFont);
				branchIDLabel.setFont(largeFont);
				branchIDField.setFont(largeFont);
				cardNoLabel.setFont(largeFont);
				cardNoField.setFont(largeFont);
				borrowerLabel.setFont(largeFont);
				borrowerField.setFont(largeFont);
				okButton.setFont(largeFont);
				searchButton.setFont(largeFont);
				cancelButton.setFont(largeFont);
				updateButton.setFont(largeFont);
				//unpaidButton.setFont(largeFont);
				cardnoButton.setFont(largeFont);
				inputPanel.add(bookIDLabel);
				inputPanel.add(bookIDField);
				inputPanel.add(cardNoLabel);
				inputPanel.add(cardNoField);
				inputPanel.add(borrowerLabel);
				inputPanel.add(borrowerField);
				inputPanel.add(searchButton);
				inputPanel.add(cancelButton);
				inputPanel.add(updateButton);
				//inputPanel.add(unpaidButton);
				inputPanel.add(cardnoButton);
				add(inputPanel, BorderLayout.NORTH);
				add(tablePanel, BorderLayout.CENTER);
				cardnoButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(((JButton)e.getSource()).getText().equals("By Cardno")){
							StringBuffer sqlBuffer = new StringBuffer();
							sqlBuffer.append("Select card_no " + ","  + " sum(fine_amt) " + " from Book_loans NATURAL JOIN FINES where paid = " + false + " group by card_no" + ";");
							String sql = sqlBuffer.toString();
							try {
								Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
								Statement stmt = conn.createStatement();
								stmt.executeQuery("use library;");
								ResultSet rs = stmt.executeQuery(sql);
								Vector<Vector<String>> result = new Vector<Vector<String>>();
								Vector<String> tempResult = new Vector<String>();
								if(rs.next()){
									rs.previous();
									Vector<String >columnName = new Vector<String>();
									columnName.add("CardNo");
									columnName.add("Fine");
									while(rs.next()){
										String cardNumber = rs.getString(1);
										String fine = rs.getString(2);
										
										tempResult = new Vector<String>(Arrays.asList(cardNumber, fine));
										result.add(tempResult);
									}
									resultTable = new JTable(result, columnName);
									Dimension d = getSize();
									resultTable.setPreferredScrollableViewportSize(new Dimension(d.width, 2000));
									resultTable.setRowSelectionAllowed(true);
									resultTable.setFillsViewportHeight(true);
									resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
									JScrollPane tableScrollPane = new JScrollPane(resultTable);
									tablePanel.removeAll();
									tablePanel.add(tableScrollPane);
									tablePanel.validate();
									//tablePanel.validate();
									pack();
									repaint();
									rs.close();
									conn.close();
								}
								else{
									JOptionPane.showMessageDialog(null, "There's no such record!! Please check again!!");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					
				});
				
				
				
				updateButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						if(((JButton)e.getSource()).getText().equals("Update")){
							System.out.println("suc");
								StringBuffer sqlBuffer = new StringBuffer();
								sqlBuffer.append("Select * from book_loans where date_in IS NULL");
								System.out.println("SUC");
								String sql = sqlBuffer.toString();
								try {
									Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									Statement stmt = conn.createStatement();
									stmt.executeQuery("use library;");
									ResultSet rs = stmt.executeQuery(sql);
									Vector<Vector<String>> result = new Vector<Vector<String>>();
									Vector<String> tempResult = new Vector<String>();
									while(rs.next()){
										String loan_id = rs.getString(1);
										System.out.println(loan_id);
										SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
										Calendar dateOut = Calendar.getInstance();
										Date out = dateOut.getTime();
										String outString = dateFormat.format(out).toString();
										System.out.println(outString);
										Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
										Statement stmt2 = conn.createStatement();
										stmt2.executeQuery("use library;");
										System.out.println(outString);
										stmt2.executeUpdate("Update book_loans set date_in ='" + outString + "' where loan_id = " + loan_id + ";");
										System.out.println(outString);
										ResultSet rs2 = stmt2.executeQuery("select timestampdiff(day, due_date, date_in) as  A from book_loans where loan_id = '" + loan_id + "';");
										rs2.next();
										String overdue = rs2.getString(1);
										int overduedays = (new Integer(overdue)).intValue();
										System.out.println(overdue);
										Connection conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
										Statement stmt3 = conn.createStatement();
										stmt3.executeQuery("use library;");
										ResultSet rs3 = stmt3.executeQuery("select * from FINES where loan_id = " + loan_id + ";");
										if (rs3.next()){
										if (overduedays > 0){
											float fine = (float) (0.25 * overduedays);
											stmt2.executeUpdate("update FINES set fine_amt = " + fine + "where loan_id = " + loan_id + ";" );
										}else{
											
										}
										}else{
											if (overduedays > 0){
												float fine = (float) (0.25 * overduedays);
												stmt2.executeUpdate("insert FINES VALUES (" + loan_id + ", " + fine + ", " + false + ");" );
											}
											else{
												
											}
										}
										stmt2.executeUpdate("Update book_loans set date_in =" + null + " where loan_id = '" + loan_id + "';");
										
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
						 
						}
						
					}
					
				});
				searchButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
					if(((JButton)e.getSource()).getText().equals("Search")){
							String bookID = bookIDField.getText();
							String borrower = borrowerField.getText();
							String cardNo = cardNoField.getText();
							if(bookID.length() == 0 && cardNo.length() == 0 && borrower.length() == 0){
								JOptionPane.showMessageDialog(null, "Must Fill these fields!!");
							}
							else{
								try{
									Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									Statement stmt = conn.createStatement();
									StringBuffer sqlBuffer = new StringBuffer();
									String sql = new String();
									if(cardNo.length() == 0){
										if(borrower.length() == 0){
											sqlBuffer.append("Select * From Book_loans NATURAL JOIN FINES");
											sqlBuffer.append(" Where Book_loans.book_id='" + bookID + "'" + "AND book_loans.loan_id = FINES.loan_id");
											sqlBuffer.append(" AND paid = " + false + ";");
										}
										else{
											if(borrower.contains(" ")){
												String[] borrowerArrays = borrower.split(" ");
												String lName = borrowerArrays[1];
												String fName = borrowerArrays[0];
												sqlBuffer.append("Select Card_No From Borrowers");
												sqlBuffer.append(" Where fname='" + fName + "'");
												sqlBuffer.append(" AND lname='" + lName + "';");
											}
											else{
												sqlBuffer.append("Select Card_No From Borrowers");
												sqlBuffer.append(" Where fname='" + borrower + "'");
												sqlBuffer.append(" OR lname='" + borrower + "';");
											}
											sql = sqlBuffer.toString();
											conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
											stmt = conn.createStatement();
											stmt.executeQuery("use library;");
											ResultSet rs = stmt.executeQuery(sql);
											int count = 1;
											sqlBuffer = new StringBuffer();
											sqlBuffer.append("Select * From book_loans Natural join FINES");
											while(rs.next()){
												cardNo = rs.getString(1);
												System.out.println(cardNo);
												if(count == 1){
													if(bookID.length() != 0){
														sqlBuffer.append(" Where book_id='" + bookID + "'");
														sqlBuffer.append(" AND (card_no='" + cardNo + "'");
														sqlBuffer.append("AND book_loans.loan_id = FINES.loan_id");
														sqlBuffer.append(" AND paid = " + false + ";");
													}
													else{
														sqlBuffer.append(" Where (card_no='" + cardNo + "'");
														sqlBuffer.append("AND book_loans.loan_id = FINES.loan_id");
														sqlBuffer.append("AND paid = " + false + ";");
													}
													
												}
												else{
													sqlBuffer.append(" or card_no='" + cardNo + "'");
													sqlBuffer.append("AND book_loans.loan_id = FINES.loan_id");
													sqlBuffer.append(" AND paid = " + false + ";");
												}
												count++;
											}
											rs.close();
											conn.close();
											if(count == 1){
												sqlBuffer = new StringBuffer();
												JOptionPane.showMessageDialog(null, "No such Borrowers!!");
												dispose();
											}
											else{
												sqlBuffer.append(");");
											}
										}
									}
									else{
										sqlBuffer.append("Select * From Book_loans NATURAL JOIN FINES");
										if(bookID.length() != 0){
											sqlBuffer.append(" Where book_id='" + bookID + "'");
											sqlBuffer.append(" AND card_No='" + cardNo + "'");
											sqlBuffer.append("AND book_loans.loan_id = FINES.loan_id");
											sqlBuffer.append(" AND paid = " + false + ";");
										}
										else{
											sqlBuffer.append(" Where card_No='" + cardNo + "'");
											sqlBuffer.append("AND book_loans.loan_id = FINES.loan_id");
											sqlBuffer.append(" AND paid = " + false + ";");
										}
									}
									sql = sqlBuffer.toString();
									conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									stmt = conn.createStatement();
									stmt.executeQuery("use library;");
									ResultSet rs = stmt.executeQuery(sql);
									Vector<Vector<String>> result = new Vector<Vector<String>>();
									Vector<String> tempResult = new Vector<String>();
									if(rs.next()){
										rs.previous();
										Vector<String >columnName = new Vector<String>();
										columnName.add("LoanID");
										columnName.add("BookID");
										columnName.add("BranchID");
										columnName.add("CardNo");
										columnName.add("DateOut");
										columnName.add("DueDate");
										columnName.add("Fine");
										while(rs.next()){
											String loan_id = rs.getString(1);
											String book_id = rs.getString(2);
											String branch_id = rs.getString(3);
											String cardNumber = rs.getString(4);
											String dateOut = rs.getString(5);
											String dueDate = rs.getString(6);
											String fine = rs.getString(8);
											
											tempResult = new Vector<String>(Arrays.asList(loan_id, book_id, branch_id, cardNumber, dateOut, dueDate, fine));
											System.out.println(tempResult);
											result.add(tempResult);
										}
										resultTable = new JTable(result, columnName);
										Dimension d = getSize();
										resultTable.setPreferredScrollableViewportSize(new Dimension(d.width, 300));
										resultTable.setRowSelectionAllowed(true);
										resultTable.setFillsViewportHeight(true);
										resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
										JScrollPane tableScrollPane = new JScrollPane(resultTable);
										tablePanel.removeAll();
										tablePanel.add(tableScrollPane);
										tablePanel.validate();
										pack();
										repaint();
										
										rs.close();
										conn.close();
										((JButton)e.getSource()).setText("OK");
									}
									else{
										JOptionPane.showMessageDialog(null, "There's no such record!! Please check again!!");
									}
								} catch(SQLException ex){
									ex.printStackTrace();
								}	
							}
						}
						else{
							if(((JButton)e.getSource()).getText().equals("OK")){
								int selected = resultTable.getSelectedRow();
								String loanID;
								String bookID;
								String branchID;
								String cardNo;
								String dateOutString;
								String dueDateString;
								String fine;
								System.out.println(selected);
								if(selected < 0){
									selected = 0;
								}
								else{
									
								}
								loanID = (String)resultTable.getValueAt(selected, 0);
								bookID = (String)resultTable.getValueAt(selected, 1);
								branchID = (String)resultTable.getValueAt(selected, 2);
								cardNo = (String)resultTable.getValueAt(selected, 3);
								dateOutString = (String)resultTable.getValueAt(selected, 4);
								dueDateString = (String)resultTable.getValueAt(selected, 5);
								fine = (String)resultTable.getValueAt(selected, 6);
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								Calendar dateOut = Calendar.getInstance();
								Date in = dateOut.getTime();
								String inString = dateFormat.format(in).toString();
								
								try{
									Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									Statement stmt = conn.createStatement();
									stmt.executeQuery("use library;");
									ResultSet rs = stmt.executeQuery("select * from book_loans where loan_id= " + loanID + " AND date_in IS NULL" + ";");
									if(rs.next()){
										JOptionPane.showMessageDialog(null, "Please check in first");
									}else{
										int loan_id = Integer.parseInt(loanID);
										stmt.executeUpdate("Update FINES set fine_amt =" + 0.00 + " where loan_id = " + loan_id + ";");
										stmt.executeUpdate("Update FINES set paid = " + true + " where loan_id = " + loan_id + ";");
									}
									rs.close();
									conn.close();
									dispose();
								} catch(SQLException ex){
									ex.printStackTrace();
								}
							}
							
							else{
								
							}
						}
					}
				});
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
					}
				});
				pack();
				setVisible(true);
				break;
				
			}
			case "Check In":{
				this.setLayout(new BorderLayout());
				JButton searchButton = new JButton("Search");
				JPanel inputPanel = new JPanel();
				inputPanel.setLayout(new GridLayout(5,1,5,5));
				bookIDLabel.setFont(largeFont);
				bookIDField.setFont(largeFont);
				branchIDLabel.setFont(largeFont);
				branchIDField.setFont(largeFont);
				cardNoLabel.setFont(largeFont);
				cardNoField.setFont(largeFont);
				borrowerLabel.setFont(largeFont);
				borrowerField.setFont(largeFont);
				okButton.setFont(largeFont);
				searchButton.setFont(largeFont);
				cancelButton.setFont(largeFont);
				inputPanel.add(bookIDLabel);
				inputPanel.add(bookIDField);
				inputPanel.add(cardNoLabel);
				inputPanel.add(cardNoField);
				inputPanel.add(borrowerLabel);
				inputPanel.add(borrowerField);
				inputPanel.add(searchButton);
				inputPanel.add(cancelButton);
				add(inputPanel, BorderLayout.NORTH);
				add(tablePanel, BorderLayout.CENTER);
				searchButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(((JButton)e.getSource()).getText().equals("Search")){
							String bookID = bookIDField.getText();
							String borrower = borrowerField.getText();
							String cardNo = cardNoField.getText();
							if(bookID.length() == 0 && cardNo.length() == 0 && borrower.length() == 0){
								JOptionPane.showMessageDialog(null, "Must Fill these fields!!");
							}
							else{
								try{
									Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									Statement stmt = conn.createStatement();
									StringBuffer sqlBuffer = new StringBuffer();
									String sql = new String();
									if(cardNo.length() == 0){
										if(borrower.length() == 0){
											sqlBuffer.append("Select * From Book_loans");
											sqlBuffer.append(" Where book_id='" + bookID + "'");
											sqlBuffer.append(" AND date_in IS NULL;");
										}
										else{
											if(borrower.contains(" ")){
												String[] borrowerArrays = borrower.split(" ");
												String lName = borrowerArrays[1];
												String fName = borrowerArrays[0];
												sqlBuffer.append("Select Card_No From Borrowers");
												sqlBuffer.append(" Where fname='" + fName + "'");
												sqlBuffer.append(" AND lname='" + lName + "';");
											}
											else{
												sqlBuffer.append("Select Card_No From Borrowers");
												sqlBuffer.append(" Where fname='" + borrower + "'");
												sqlBuffer.append(" OR lname='" + borrower + "';");
											}
											sql = sqlBuffer.toString();
											conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
											stmt = conn.createStatement();
											stmt.executeQuery("use library;");
											ResultSet rs = stmt.executeQuery(sql);
											int count = 1;
											sqlBuffer = new StringBuffer();
											sqlBuffer.append("Select * From book_loans");
											while(rs.next()){
												cardNo = rs.getString(1);
												System.out.println(cardNo);
												if(count == 1){
													if(bookID.length() != 0){
														sqlBuffer.append(" Where book_id='" + bookID + "'");
														sqlBuffer.append(" AND (card_no='" + cardNo + "'");
														sqlBuffer.append(" AND date_in IS NULL;");
													}
													else{
														sqlBuffer.append(" Where (card_no='" + cardNo + "'");
														sqlBuffer.append(" AND date_in IS NULL;");
													}
													
												}
												else{
													sqlBuffer.append(" or card_no='" + cardNo + "'");
													sqlBuffer.append(" AND date_in IS NULL;");
												}
												count++;
											}
											rs.close();
											conn.close();
											if(count == 1){
												sqlBuffer = new StringBuffer();
												JOptionPane.showMessageDialog(null, "No such Borrowers!!");
												dispose();
											}
											else{
												sqlBuffer.append(");");
											}
										}
									}
									else{
										sqlBuffer.append("Select * From Book_loans");
										if(bookID.length() != 0){
											sqlBuffer.append(" Where book_id='" + bookID + "'");
											sqlBuffer.append(" AND card_No='" + cardNo + "'");
											sqlBuffer.append(" AND date_in IS NULL;");
										}
										else{
											sqlBuffer.append(" Where card_No='" + cardNo + "'");
											sqlBuffer.append(" AND date_in IS NULL;");
										}
									}
									sql = sqlBuffer.toString();
									conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									stmt = conn.createStatement();
									stmt.executeQuery("use library;");
									ResultSet rs = stmt.executeQuery(sql);
									Vector<Vector<String>> result = new Vector<Vector<String>>();
									Vector<String> tempResult = new Vector<String>();
									if(rs.next()){
										rs.previous();
										Vector<String >columnName = new Vector<String>();
										columnName.add("LoanID");
										columnName.add("BookID");
										columnName.add("BranchID");
										columnName.add("CardNo");
										columnName.add("DateOut");
										columnName.add("DueDate");
										while(rs.next()){
											String loan_id = rs.getString(1);
											String book_id = rs.getString(2);
											String branch_id = rs.getString(3);
											String cardNumber = rs.getString(4);
											String dateOut = rs.getString(5);
											String dueDate = rs.getString(6);
											
											tempResult = new Vector<String>(Arrays.asList(loan_id, book_id, branch_id, cardNumber, dateOut, dueDate));
											System.out.println(tempResult);
											result.add(tempResult);
										}
										resultTable = new JTable(result, columnName);
										Dimension d = getSize();
										resultTable.setPreferredScrollableViewportSize(new Dimension(d.width, 150));
										resultTable.setRowSelectionAllowed(true);
										resultTable.setFillsViewportHeight(true);
										resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
										JScrollPane tableScrollPane = new JScrollPane(resultTable);
										tablePanel.removeAll();
										tablePanel.add(tableScrollPane);
										tablePanel.validate();
										pack();
										repaint();
										
										rs.close();
										conn.close();
										((JButton)e.getSource()).setText("OK");
									}
									else{
										JOptionPane.showMessageDialog(null, "There's no such record!! Please check again!!");
									}
								} catch(SQLException ex){
									ex.printStackTrace();
								}	
							}
						}
						else{
							if(((JButton)e.getSource()).getText().equals("OK")){
								int selected = resultTable.getSelectedRow();
								String loanID;
								String bookID;
								String branchID;
								String cardNo;
								String dateOutString;
								String dueDateString;
								System.out.println(selected);
								if(selected < 0){
									selected = 0;
								}
								else{
									
								}
								loanID = (String)resultTable.getValueAt(selected, 0);
								bookID = (String)resultTable.getValueAt(selected, 1);
								branchID = (String)resultTable.getValueAt(selected, 2);
								cardNo = (String)resultTable.getValueAt(selected, 3);
								dateOutString = (String)resultTable.getValueAt(selected, 4);
								dueDateString = (String)resultTable.getValueAt(selected, 5);
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								Calendar dateOut = Calendar.getInstance();
								Date in = dateOut.getTime();
								String inString = dateFormat.format(in).toString();
								
								StringBuffer sqlBuffer = new StringBuffer();
								sqlBuffer.append("Update book_loans set date_in = '" + inString + "'");
								sqlBuffer.append(" where book_id='" + bookID +"'");
								sqlBuffer.append(" AND loan_id='" + loanID + "'");
								sqlBuffer.append(" AND branch_id='" + branchID + "'");
								sqlBuffer.append(" AND card_no='" + cardNo + "'");
								sqlBuffer.append(" AND date_out='" + dateOutString + "'");
								sqlBuffer.append(" AND due_date='" + dueDateString + "'");
								sqlBuffer.append(" AND date_in IS NULL;");
								String sql = sqlBuffer.toString();
								try{
									Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
									Statement stmt = conn.createStatement();
									stmt.executeQuery("use library;");
									stmt.executeUpdate(sql);
									conn.close();
									dispose();
								} catch(SQLException ex){
									ex.printStackTrace();
								}
							}
							else{
								
							}
						}
					}
				});
				cancelButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						dispose();
					}
				});
				pack();
				setVisible(true);
				break;
			}
			
			
			
			default:{
				JOptionPane.showMessageDialog(null, "Wrong Dialog Message!");
			}
		}
		
	}
}
