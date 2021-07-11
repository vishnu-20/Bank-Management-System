package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Transactions extends JFrame implements ActionListener {

	JLabel l1, l2, l3;
	JButton b1, b2, b3, b4, b5;
	String acc,bal;
	

	public Transactions(String acc) {
		this.acc = acc;
		
		 bal = balanceEnquiry(acc);
		l1 = new JLabel("Please Select Your Transaction");
		l1.setForeground(Color.red);
		l1.setFont(new Font("System", Font.BOLD, 16));
		l1.setBounds(120, 20, 300, 25);
		add(l1);

		b1 = new JButton("DEPOSIT");
		b1.setBounds(50, 100, 150, 35);
		b1.setForeground(Color.white);
		b1.setBackground(Color.black);
		add(b1);

		b2 = new JButton("CASH WITHDRAWL");
		b2.setBounds(260, 100, 150, 35);
		b2.setForeground(Color.white);
		b2.setBackground(Color.black);
		add(b2);

		b3 = new JButton("PIN CHANGE");
		b3.setBounds(50, 180, 150, 35);
		b3.setForeground(Color.white);
		b3.setBackground(Color.black);
		add(b3);

		b4 = new JButton("MINI STATEMENT");
		b4.setForeground(Color.white);
		b4.setBackground(Color.black);
		b4.setBounds(260, 180, 150, 35);
		add(b4);

		b5 = new JButton("EXIT");
		b5.setForeground(Color.white);
		b5.setBackground(Color.black);
		b5.setBounds(160, 260, 150, 35);
		add(b5);

		l2 = new JLabel("Account No:" + acc);
		l2.setForeground(Color.red);
		l2.setFont(new Font("System", Font.BOLD, 20));
		l2.setBounds(110, 340, 300, 35);
		add(l2);

		l3 = new JLabel("Balance ₹" + bal);
		l3.setForeground(Color.green);
		l3.setFont(new Font("System", Font.BOLD, 20));
		l3.setBounds(165, 380, 300, 35);
		add(l3);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);

		setLayout(null);
		setSize(500, 550);
		setLocation(500, 200);
		setVisible(true);
		getContentPane().setBackground(Color.white);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b5) {
			System.exit(0);
		} else if (e.getSource() == b1) {
			setVisible(false);
			new Depo(acc).setVisible(true);
		}else if(e.getSource()==b2) {
			setVisible(false);
			new Withdrawal(acc,bal).setVisible(true);
		}else if(e.getSource()==b3) {
			
			setVisible(false);
			new PinChange(acc).setVisible(true);
			
		}else if(e.getSource()==b4) {
			
			
			new MiniStatement(acc).setVisible(true);
		}
		
	}

	public static String balanceEnquiry(String acc) {

		ConToDb conToDb = new ConToDb();
		conToDb.connect();
		String total=null;

		try {
			String query = "select * from bank where account_no=" + acc;
			Statement statement = conToDb.c.createStatement();
			ResultSet rs = statement.executeQuery(query);
			rs.next();
			total=rs.getString(5);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return total;

	}

	

}
