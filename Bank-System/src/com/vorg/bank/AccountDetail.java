package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AccountDetail extends JFrame implements ActionListener {

	String id;
	JLabel heading, detail, aType, card, cardNo, pin;
	JTextField t1;
	JRadioButton r1, r2, r3, r4;
	JButton submit;
	JCheckBox j1;

	Random ran = new Random();

	String first = String.valueOf(Math.abs(ran.nextLong()));
	String value = first.substring(0, 12);
	String last4 = value.substring(8, 12);

	public AccountDetail(String id) {

		this.id = id;

		heading = new JLabel("Application Number : " + id);
		heading.setFont(new Font("", Font.BOLD, 20));
		heading.setBounds(100, 10, 250, 40);
		add(heading);

		detail = new JLabel("Enter the Details");
		detail.setFont(new Font("", Font.BOLD, 10));
		detail.setForeground(Color.red);
		detail.setBounds(100, 40, 100, 40);
		add(detail);

		aType = new JLabel("Account Type");
		aType.setFont(new Font("", Font.BOLD, 15));
		aType.setBounds(50, 80, 100, 20);
		add(aType);

		r1 = new JRadioButton("Saving Account");
		r1.setFont(new Font("Raleway", Font.BOLD, 10));
		r1.setBackground(Color.WHITE);
		r1.setBounds(50, 120, 150, 30);
		add(r1);

		r2 = new JRadioButton("Fixed Deposit Account");
		r2.setFont(new Font("Raleway", Font.BOLD, 10));
		r2.setBackground(Color.WHITE);
		r2.setBounds(200, 120, 300, 30);
		add(r2);

		r3 = new JRadioButton("Current Account");
		r3.setFont(new Font("Raleway", Font.BOLD, 10));
		r3.setBackground(Color.WHITE);
		r3.setBounds(50, 160, 150, 30);
		add(r3);

		r4 = new JRadioButton("Salary Account");
		r4.setFont(new Font("Raleway", Font.BOLD, 10));
		r4.setBackground(Color.WHITE);
		r4.setBounds(200, 160, 150, 30);
		add(r4);

		ButtonGroup groupgender = new ButtonGroup();
		groupgender.add(r1);
		groupgender.add(r2);
		groupgender.add(r3);
		groupgender.add(r4);

		card = new JLabel("Card No:");
		card.setFont(new Font("", Font.BOLD, 20));
		card.setBounds(50, 220, 95, 20);
		add(card);

		cardNo = new JLabel("XXXX-XXXX-XXXX-" + last4);
		cardNo.setFont(new Font("", Font.BOLD, 20));
		cardNo.setBounds(150, 220, 300, 20);
		add(cardNo);

		pin = new JLabel("ATM Pin:");
		pin.setFont(new Font("", Font.BOLD, 20));
		pin.setBounds(50, 300, 95, 20);
		add(pin);

		t1 = new JTextField(20);
		t1.setFont(new Font("", Font.BOLD, 20));
		t1.setBounds(150, 300, 220, 20);
		add(t1);

		submit = new JButton("Submit");
		submit.setFont(new Font("", Font.BOLD, 20));
		submit.setBounds(170, 400, 100, 25);
		submit.setForeground(Color.white);
		submit.setBackground(Color.black);
		add(submit);

		j1 = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.",
				true);
		j1.setFont(new Font("", Font.BOLD, 8));
		j1.setBackground(Color.white);
		j1.setBounds(50, 350, 460, 10);
		add(j1);

		submit.addActionListener(this);

		setLayout(null);
		setSize(460, 500);
		setLocation(500, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String atype = null;
		String pin = t1.getText();
		if (r1.isSelected()) {
			atype = "Saving Account";
		} else if (r2.isSelected()) {
			atype = "Fixed Deposit Account";
		} else if (r3.isSelected()) {
			atype = "Current Account";
		} else if (r4.isSelected()) {
			atype = "Salary Account";
		}
		if (atype.equals("") || t1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Fill all the required fields");
		} else {

			try {

				ConToDb conToDb = new ConToDb();
				conToDb.connect();

				String query = "insert into login values(?,?,?)";
				String query1 = "insert into accountDetails values(?,?,?,?)";
				PreparedStatement s = conToDb.c.prepareStatement(query);
				PreparedStatement s1 = conToDb.c.prepareStatement(query1);
				s.setString(1, value);
				s.setString(2, id);
				s.setString(3, pin);

				s1.setString(1, value);
				s1.setString(2, id);
				s1.setString(3, pin);
				s1.setString(4, atype);

				s.executeUpdate();
				s1.executeUpdate();

				setVisible(false);
				new Sucess(value).setVisible(true);

			} catch (Exception e1) {
				System.out.println(e1);
			}

		}

	}

}
