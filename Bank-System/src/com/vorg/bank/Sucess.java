package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Sucess extends JFrame implements ActionListener {

	JLabel l1, l2;
	JButton b1, b2;
	String value;

	public Sucess(String value) {
		this.value = value;

		l1 = new JLabel("Success");
		l1.setFont(new Font("Osward", Font.BOLD, 20));
		l1.setBounds(150, 20, 400, 40);
		l1.setForeground(Color.green);
		add(l1);

		l2 = new JLabel("Card No:" + value);
		l2.setFont(new Font("Osward", Font.PLAIN, 20));
		l2.setBounds(78, 95, 350, 20);
		add(l2);

		b1 = new JButton("ok");
		b1.setFont(new Font("Osward", Font.BOLD, 10));
		b1.setBounds(90, 160, 70, 20);
		b1.setForeground(Color.white);
		b1.setBackground(Color.black);
		add(b1);

		b2 = new JButton("login");
		b2.setFont(new Font("Osward", Font.BOLD, 10));
		b2.setBounds(190, 160, 70, 20);
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
		if (e.getSource() == b1) {
			
			new Deposit(value).setVisible(true);

		} else if (e.getSource() == b2) {

			setVisible(false);
			new Login().setVisible(true);
		}

	}

	public static void main(String[] args) {
		Sucess sucess=new Sucess("ok");
	}
}
