package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PinChange extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String acc;
	JLabel j1, j2;
	JTextField l2, l1;
	JButton b1, b2;

	public PinChange(String acc) {

		this.acc = acc;

		j1 = new JLabel("Old:");
		j1.setFont(new Font("Osward", Font.BOLD, 15));
		j1.setBounds(50, 20, 250, 40);
		add(j1);

		l1 = new JTextField(20);
		l1.setFont(new Font("Osward", Font.BOLD, 15));
		l1.setBounds(90, 20, 250, 40);
		add(l1);

		j2 = new JLabel("New:");
		j2.setFont(new Font("Osward", Font.BOLD, 15));
		j2.setBounds(50, 95, 250, 40);
		add(j2);

		l2 = new JTextField(20);
		l2.setFont(new Font("Osward", Font.PLAIN, 20));
		l2.setBounds(90, 95, 250, 40);
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

		String newpin = l2.getText();
		if (e.getSource() == b2) {
			new Transactions(acc).setVisible(true);
		} else if (l2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "please enter a valid pin");
		} else {

			try {

				ConToDb conToDb = new ConToDb();
				conToDb.connect();

				String query = "update login set pin='" + newpin + "'where card_no='" + acc + "'";

				Statement statement = conToDb.c.createStatement();
				statement.executeUpdate(query);

				setVisible(false);
				new Login().setVisible(true);
				;

			} catch (Exception e1) {
				System.out.println(e1);
			}
		}

	}

	
}
