package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;



public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1, l2, l3;
	JButton b1, b2, b3;
//	JPasswordField p1;
	JTextField t1,p1;

	public Login() {

		setTitle("ATM SYSTEM");

		l1 = new JLabel("Welcome to Bank");
		l1.setFont(new Font("Osward", Font.BOLD, 38));
		l1.setBounds(200, 40, 400, 40);
		add(l1);

		l2 = new JLabel("Card No:");
		l2.setFont(new Font("Osward", Font.PLAIN, 25));
		l2.setBounds(150, 120, 350, 20);
		add(l2);

		l3 = new JLabel("Pin  No:");
		l3.setFont(new Font("Osward", Font.PLAIN, 25));
		l3.setBounds(150, 180, 350, 20);
		add(l3);

		t1 = new JTextField(15);
		t1.setBounds(300, 117, 230, 30);
		add(t1);

		p1 = new JTextField(15);
		p1.setBounds(300, 177, 230, 30);
		add(p1);

		b1 = new JButton("Login");
		b1.setBounds(180, 250, 150, 30);
		b1.setForeground(Color.white);
		b1.setBackground(Color.black);
		add(b1);

		b2 = new JButton("Sign up");
		b2.setBounds(350, 250, 150, 30);
		b2.setForeground(Color.white);
		b2.setBackground(Color.black);
		add(b2);

		b3 = new JButton("Clear");
		b3.setBounds(270, 300, 150, 30);
		b3.setForeground(Color.white);
		b3.setBackground(Color.black);
		add(b3);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		setLayout(null);
		getContentPane().setBackground(Color.white);
		setResizable(false);
		setSize(700, 480);
		setLocation(550, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	if(e.getSource()==b1) {
		
		 try{        
			 ConToDb conToDb = new ConToDb();
				conToDb.connect();
	                String cardno  = t1.getText();
	                String pin  = p1.getText();
	                String q  = "select * from login where card_no = '"+cardno+"' and pin = '"+pin+"'";

	                 Statement s=conToDb.c.createStatement();
	                 ResultSet rs=s.executeQuery(q);
	                 
	                 
	                   
	                if(rs.next()){
	                	System.out.println(rs.getString(1)+"  "+rs.getString(3));
	                    setVisible(false);
	                    new Transactions(cardno).setVisible(true);
	                }else{
	                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
	                }	
		
		
		 }catch (Exception e1) {
			System.out.println(e1);
		}
	}
	else if (e.getSource() == b2) {
			setVisible(false);
			new SignUp().setVisible(true);
			
		}

		else if (e.getSource() == b3) {
			
			t1.setText("");
			p1.setText("");
		}

	}

	public static void main(String[] args) {

		Login obj = new Login();
	}

}
