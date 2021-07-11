package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;

import java.sql.Statement;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MiniStatement extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String acc;
	JTable jTable;
	JButton b2;

	public MiniStatement(String acc) {
		super("Mini Statement");

		this.acc = acc;

		String x[] = { "Account number", "Deposit", "Withdraw", "date", "Balance" };
		String y[][] = new String[20][5];
		int i = 0, j = 0;

		b2 = new JButton("back");
		b2.setFont(new Font("Osward", Font.BOLD, 10));
		b2.setBounds(0, 340, 1200, 20);
		b2.setForeground(Color.white);
		b2.setBackground(Color.black);
		add(b2);
		b2.addActionListener(this);
		ConToDb conToDb = new ConToDb();
		conToDb.connect();

		try {
			String query1 = " select * from bank where account_no='" + acc + "'";
			Statement statement = conToDb.c.createStatement();
			ResultSet rs = statement.executeQuery(query1);

			while (rs.next()) {
				y[i][j++] = rs.getString("account_No");
				y[i][j++] = rs.getString("deposit");
				y[i][j++] = rs.getString("withdraw");
				y[i][j++] = rs.getString("date");
				y[i][j++] = rs.getString("balance");
				i++;
				j = 0;

			}

			jTable = new JTable(y, x);

			add(jTable);
			JScrollPane sp = new JScrollPane(jTable);
			add(sp);

		} catch (Exception e) {

			e.printStackTrace();
		}

//		setLayout(null);
		setSize(1200, 400);
		setLocation(200, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b2) {
			setVisible(false);
			new Transactions(acc).setVisible(true);
		}

	}

}
