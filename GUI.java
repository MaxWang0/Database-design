import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

import java.util.*;

public  class GUI extends JFrame implements ActionListener {
	JMenuBar menuBar;
	JMenu function;
	JMenuItem newBorrower, checkOut, checkIn, payFine, deleteBorrower, updateBorrower, addBook;
	
	JPanel searchPanel;
	JPanel tablePanel;
	
	JLabel bookIDLabel, titleLabel, authorLabel;
	JTextField bookIDField, titleField, authorField;
	JButton searchButton;
	JTable resultTable;
	JScrollPane tableScrollPane;
	
	Vector<Vector<String>> result;
	Vector<String> columnName;
	protected GUI(){
		Font largeFont = new Font("TimesRoman", Font.BOLD, 20);
		setTitle("Library DataBase");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		newBorrower = new JMenuItem("New Borrower");
		newBorrower.setFont(largeFont);
		newBorrower.addActionListener(this);
		checkOut = new JMenuItem("Check Out");
		checkOut.setFont(largeFont);
		checkOut.addActionListener(this);
		checkIn = new JMenuItem("Check In");
		checkIn.setFont(largeFont);
		checkIn.addActionListener(this);
		payFine = new JMenuItem("Pay Fine");
		payFine.setFont(largeFont);
		payFine.addActionListener(this);
		
		function = new JMenu("Function");
		function.setFont(largeFont);
		function.add(newBorrower);
		function.add(checkOut);
		function.add(checkIn);
		function.add(payFine);
		menuBar = new JMenuBar();
		menuBar.add(function);
		this.setJMenuBar(menuBar);
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		
		
		bookIDLabel = new JLabel("Book ID: ");
		bookIDLabel.setFont(largeFont);
		bookIDField = new JTextField(10);
		bookIDField.setFont(largeFont);
		titleLabel = new JLabel("Title: ");
		titleLabel.setFont(largeFont);
		titleField = new JTextField(10);
		titleField.setFont(largeFont);
		authorLabel = new JLabel("Author: ");
		authorLabel.setFont(largeFont);
		authorField = new JTextField(10);
		authorField.setFont(largeFont);
		searchButton = new JButton("Search");
		searchButton.setFont(largeFont);
		searchButton.addActionListener(this);
		
		searchPanel.add(bookIDLabel);
		searchPanel.add(bookIDField);
		searchPanel.add(titleLabel);
		searchPanel.add(titleField);
		searchPanel.add(authorLabel);
		searchPanel.add(authorField);
		searchPanel.add(searchButton);
		
		result = new Vector<Vector<String>>();
		Vector<String> tempResult = new Vector<String>();
		
		columnName = new Vector<String>();
		columnName.add("BookID");
		columnName.add("Title");
		columnName.add("Author_name");
		columnName.add("Fname");
		columnName.add("Minit");
		columnName.add("Lname");
		columnName.add("BranchID");
		columnName.add("Total");
		columnName.add("Available");
		
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(1, 0));
		
		setLayout(new BorderLayout());
		add(searchPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		
		pack();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent event) {
		Font largeFont = new Font("TimesRoman", Font.BOLD, 15);
				if(event.getSource() == newBorrower){
					DialogFactory dialog = new DialogFactory(this, "New Borrower");
				}
				else{
					if(event.getSource() == checkOut){
						DialogFactory dialog = new DialogFactory(this, "Check Out");
					}
					else if (event.getSource() == payFine){
						DialogFactory dialog = new DialogFactory(this, "Pay Fine");
					}
					else{
						if(event.getSource() == checkIn){
							DialogFactory dialog = new DialogFactory(this, "Check In");
						}
						else{
							if(event.getSource() == searchButton){
								String bookID = bookIDField.getText();
								String title = titleField.getText();
								String author = authorField.getText();
								if(bookID.length() == 0 && title.length() == 0 && author.length() == 0){
									JOptionPane.showMessageDialog(null, "Must Fill at least one fields!!");
								}
								else{
									try{
										StringBuffer sqlBuffer = new StringBuffer();
										sqlBuffer.append("SELECT book.book_id, book.title, book_authors.author_name, book_authors.Fname, book_authors.Minit, book_authors.Lname, book_copies.branch_id, book_copies.no_of_copies, book_c"
												+ "opies.no_of_copies-count(card_no)");
										sqlBuffer.append(" FROM BOOK Natural Join BOOK_AUTHORS Natural Join BOOK_COPIES" + 
												" Left Join book_loans on book_copies.book_id=book_loans.book_id AND date_in IS NULL AND book_copies.branch_id = book_loans.branch_id");
										sqlBuffer.append(" WHERE");
										if(bookID.length() != 0){
											sqlBuffer.append(" book.book_id LIKE'%" + bookID + "%'");
											if(title.length() != 0){
												sqlBuffer.append(" AND book.title LIKE'%" + title + "%'");
												if(author.length() != 0){
													sqlBuffer.append(" AND book_authors.author_name LIKE'%" + author + "%'");
												}
												else{
												}
											}
											else{
												if(author.length() != 0){
													sqlBuffer.append(" AND book_authors.author_name LIKE'%" + author + "%'");
												}
												else{
												}
											}
										}
										else{
											if(title.length() != 0){
												sqlBuffer.append(" book.title LIKE'%" + title + "%'");
												if(author.length() != 0){
													sqlBuffer.append(" AND book_authors.author_name LIKE'%" + author + "%'");
												}
												else{
												}
											}
											else{
												if(author.length() != 0){
													sqlBuffer.append(" book_authors.author_name LIKE'%" + author + "%'");
												}
												else{
												}
											}
										}
										sqlBuffer.append(" group by book.book_id, book_copies.branch_id, book_authors.author_name;");
										String sql = sqlBuffer.toString();
										Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "8708165");
										Statement stmt = conn.createStatement();
										stmt.executeQuery("use library;");
										ResultSet rs = stmt.executeQuery(sql);
										result = new Vector<Vector<String>>();
										Vector<String> tempResult = new Vector<String>();
										
										while (rs.next()){									
											String book_id = rs.getString(1);
											String Title = rs.getString(2);
											String author_name = rs.getString(3);
											String Fname = rs.getString(4);
											String Minit = rs.getString(5);
											String Lname = rs.getString(6);
											String branch_id = rs.getString(7);
											String total = rs.getString(8);
											String available = rs.getString(9);
											
											tempResult = new Vector<String>(Arrays.asList(book_id, Title, author_name, Fname, Minit, Lname, branch_id, total, available));
											result.add(tempResult);
										}
										
										
										columnName = new Vector<String>();
										columnName.add("BookID");
										columnName.add("Title");
										columnName.add("Author_name");
										columnName.add("Fname");
										columnName.add("Minit");
										columnName.add("Lname");
										columnName.add("BranchID");
										columnName.add("Total");
										columnName.add("Available");
										resultTable = new JTable(result, columnName);
										resultTable.setPreferredScrollableViewportSize(new Dimension(500, 150));
										resultTable.setFillsViewportHeight(true);
										resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
										resultTable.setFont(largeFont);
										tableScrollPane = new JScrollPane(resultTable);
										tablePanel.removeAll();
										tablePanel.add(tableScrollPane);
										tablePanel.validate();
										
										pack();
										repaint();
										
										rs.close();
										conn.close();
									} catch(SQLException ex){
										System.out.println("Error in connection: " + ex.getMessage());
									}
								}
							}
							else{
							
							}
						}
					}
				}
		
	}
	public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}

