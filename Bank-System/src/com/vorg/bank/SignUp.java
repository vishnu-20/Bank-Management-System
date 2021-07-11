package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignUp extends JFrame implements ActionListener {

	/**
	 * 
	 */

	JLabel heading, detail, name, mName, lName, birth, gender, address, pincode, city, state, email;
	JTextField nameT, mNameT, lNameT, addressT, pincodeT, cityT, stateT, emailT;
	JRadioButton r1, r2;
	JComboBox day, month, year;
	JButton next;

	Random random = new Random();
	long value = (random.nextLong() % 9000L) + 1000L;
	String id = "" + Math.abs(value);

	public SignUp() {

		heading = new JLabel("Application Number : " + id);
		heading.setFont(new Font("", Font.BOLD, 20));
		heading.setBounds(100, 10, 250, 40);
		add(heading);

		detail = new JLabel("Enter the Details");
		detail.setFont(new Font("", Font.BOLD, 10));
		detail.setForeground(Color.red);
		detail.setBounds(100, 40, 100, 40);
		add(detail);

		name = new JLabel("First Name:");
		name.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		name.setBounds(50, 80, 100, 20);
		add(name);

		mNameT = new JTextField(12);
		mNameT.setBounds(170, 120, 230, 20);
		add(mNameT);

		mName = new JLabel("Middle Name:");
		mName.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		mName.setBounds(50, 120, 95, 20);
		add(mName);

		nameT = new JTextField(12);
		nameT.setBounds(170, 80, 230, 20);
		add(nameT);

		lName = new JLabel("Last Name:");
		lName.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		lName.setBounds(50, 160, 100, 20);
		add(lName);

		lNameT = new JTextField(12);
		lNameT.setBounds(170, 160, 230, 20);
		add(lNameT);

		gender = new JLabel("Gender:");
		gender.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		gender.setBounds(50, 200, 100, 20);
		add(gender);

		r1 = new JRadioButton("Male");
		r1.setFont(new Font("Raleway", Font.BOLD, 10));
		r1.setBackground(Color.WHITE);
		r1.setBounds(170, 200, 80, 20);
		add(r1);

		r2 = new JRadioButton("Female");
		r2.setFont(new Font("Raleway", Font.BOLD, 10));
		r2.setBackground(Color.WHITE);
		r2.setBounds(270, 200, 80, 20);
		add(r2);

		ButtonGroup g = new ButtonGroup();
		g.add(r1);
		g.add(r2);

		email = new JLabel("Email:");
		email.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		email.setBounds(50, 240, 100, 20);
		add(email);

		emailT = new JTextField(12);
		emailT.setBounds(170, 240, 230, 20);
		add(emailT);

		String[] date = new String[32];
		date[0] = "day";
		for (int i = 1; i < date.length; i++) {
			date[i] = String.valueOf(i);

		}

		birth = new JLabel("Birth:");
		birth.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		birth.setBounds(50, 280, 100, 20);
		add(birth);

		day = new JComboBox(date);
		day.setBackground(Color.WHITE);
		day.setBounds(170, 280, 50, 18);
		add(day);

		String[] months = { "month", "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };

		month = new JComboBox(months);
		month.setBounds(240, 280, 80, 18);
		month.setBackground(Color.WHITE);
		add(month);

		String[] years = new String[25];
		years[0] = "year";
		int j = 1998;

		for (int i = 1; i < years.length; i++) {

			years[i] = String.valueOf(j);
			j = j + 1;

		}

		year = new JComboBox(years);
		year.setBounds(340, 280, 60, 18);
		year.setBackground(Color.WHITE);
		add(year);

		address = new JLabel("Address:");
		address.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		address.setBounds(50, 320, 100, 20);
		add(address);

		addressT = new JTextField(12);
		addressT.setBounds(170, 320, 230, 20);
		add(addressT);

		city = new JLabel("City:");
		city.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		city.setBounds(50, 360, 100, 20);
		add(city);

		cityT = new JTextField(12);
		cityT.setBounds(170, 360, 230, 20);
		add(cityT);

		pincode = new JLabel("Pincode:");
		pincode.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		pincode.setBounds(50, 400, 100, 20);
		add(pincode);

		pincodeT = new JTextField(12);
		pincodeT.setBounds(170, 400, 230, 20);
		add(pincodeT);

		state = new JLabel("State:");
		state.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		state.setBounds(50, 440, 100, 20);
		add(state);

		stateT = new JTextField(12);
		stateT.setBounds(170, 440, 230, 20);
		add(stateT);

		next = new JButton("Next");
		next.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		next.setBounds(180, 480, 80, 25);
		next.setForeground(Color.white);
		next.setBackground(Color.black);
		add(next);

		next.addActionListener(this);
		setLayout(null);
		setSize(460, 580);
		setLocation(500, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String firstName = nameT.getText();
		String lastName = lNameT.getText();
		String middleName = mNameT.getText();
		String address = addressT.getText();
		String city = cityT.getText();
		String pin = pincodeT.getText();
		String st = stateT.getText();
		String ema = emailT.getText();

		String gend = null;
		String days = (String) day.getSelectedItem();
		String mon = (String) month.getSelectedItem();
		String yea = (String) year.getSelectedItem();

		if (r1.isSelected()) {
			gend = "male";
		} else if (r2.isSelected()) {
			gend = "female";
		}

		if (nameT.getText().equals("") || lNameT.getText().equals("")

				 || addressT.getText().equals("")

				|| emailT.getText().equals("") || cityT.getText().equals("")

				|| pincodeT.getText().equals("") || stateT.getText().equals("")

				|| days.equals("") || days.equals("day") || days.equals("") || yea.equals("year") || mon.equals("")
				|| days.equals("month")) {
			JOptionPane.showMessageDialog(null, "Fill all the required fields");
		} else {

			try {

				ConToDb conToDb = new ConToDb();
				conToDb.connect();

				String query = "insert into application values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement s = conToDb.c.prepareStatement(query);
				s.setString(1, id);
				s.setString(2, firstName);
				s.setString(3, middleName);
				s.setString(4, lastName);
				s.setString(5, gend);
				s.setString(6, ema);
				s.setString(7, days);
				s.setString(8, mon);
				s.setString(9, yea);
				s.setString(10, address);
				s.setString(11, city);
				s.setString(12, pin);
				s.setString(13, st);

				s.executeUpdate();

				setVisible(false);
				new PersonalDetails(id).setVisible(true);
				

			} catch (Exception e1) {
				e1.printStackTrace();

			}
		}

	}

	public static void main(String[] args) {

		new SignUp().setVisible(true);

	}

}