package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Withdrawal extends JFrame implements ActionListener{

	
	JLabel l1;
	JTextField l2;
	JButton b1, b2;
	String acc,bal;

	public Withdrawal(String acc, String bal) {
		
		this.acc = acc;
		this.bal = bal;
		

		l1 = new JLabel("Enter the amount you want to withdraw");
		l1.setFont(new Font("Osward", Font.BOLD, 15));
		l1.setBounds(50, 20, 400, 40);
		l1.setForeground(Color.red);
		add(l1);

		l2 = new JTextField(20);
		l2.setFont(new Font("Osward", Font.PLAIN, 20));
		l2.setBounds(78, 95, 200, 40);
		add(l2);

		b1 = new JButton("ok");
		b1.setFont(new Font("Osward", Font.BOLD, 10));
		b1.setBounds(50, 160, 70, 20);
		b1.setForeground(Color.white);
		b1.setBackground(Color.black);
		add(b1);

		b2 = new JButton("back");
		b2.setFont(new Font("Osward", Font.BOLD, 10));
		b2.setBounds(250, 160, 70, 20);
		b2.setForeground(Color.white);
		b2.setBackground(Color.black);
		add(b2);

		b1.addActionListener(this);
		b2.addActionListener(this);

		setLayout(null);
		setSize(400, 250);
		setLocation(500, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b2) {
			new Transactions(acc).setVisible(true);
		}else if (l2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "please enter the amount");
		}else {

			try {

				String withdraw=l2.getText();
				ConToDb conToDb = new ConToDb();
				conToDb.connect();
                
				String query1=" select * from bank where account_no="+acc+" order by date desc ";
				String query = "insert into bank values(?,?,?,?,?)";
				
				Statement statement=conToDb.c.createStatement();
				ResultSet resultSet=statement.executeQuery(query1);
				resultSet.next();
				
				 String balance=resultSet.getString(5);
				
				
				int b=Math.abs(Integer.parseInt(balance)-Integer.parseInt(withdraw));
				
				
				
				PreparedStatement s1 = conToDb.c.prepareStatement(query);
			
			    s1.setString(1, acc);

				s1.setString(2, bal);
				s1.setString(3, withdraw);
				s1.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
				s1.setString(5, String.valueOf(b));

				s1.executeUpdate();
				
				System.exit(0);
			 
			}catch (Exception e1) {
			System.out.println(e1);
			}
		}
		
	}


}
