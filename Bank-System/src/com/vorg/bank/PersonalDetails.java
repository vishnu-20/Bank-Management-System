package com.vorg.bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersonalDetails extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConToDb.class.getName());


	String id;
	JLabel heading, phoneNo, detail, religion, AdhaarNo, qualification;
	JTextField phone, aadharNo;
	JComboBox religionC, qual;
	JButton next;

	public PersonalDetails(String id) {
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

		phoneNo = new JLabel("Phone No:");
		phoneNo.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		phoneNo.setBounds(50, 80, 100, 20);
		add(phoneNo);

		phone = new JTextField(12);
		phone.setBounds(170, 120, 230, 20);
		add(phone);

		AdhaarNo = new JLabel("Aadhar No:");
		AdhaarNo.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		AdhaarNo.setBounds(50, 120, 95, 20);
		add(AdhaarNo);

		aadharNo = new JTextField(12);
		aadharNo.setBounds(170, 80, 230, 20);
		add(aadharNo);

		religion = new JLabel("Religion:");
		religion.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		religion.setBounds(50, 160, 100, 20);
		add(religion);

		String relegion[] = { "select", "Hindu", "Muslim", "Christian" };

		religionC = new JComboBox(relegion);
		religionC.setBounds(170, 160, 100, 18);
		religionC.setBackground(Color.WHITE);
		add(religionC);

		qualification = new JLabel("qualification:");
		qualification.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		qualification.setBounds(50, 200, 100, 20);
		add(qualification);

		String qualification[] = { "select", "UG", "PG", "+2", "tenth" };

		qual = new JComboBox(qualification);
		qual.setBounds(170, 200, 100, 18);
		qual.setBackground(Color.WHITE);
		add(qual);

		next = new JButton("Next");
		next.setFont(new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		next.setBounds(180, 260, 80, 25);
		next.setForeground(Color.white);
		next.setBackground(Color.black);
		add(next);

		setLayout(null);
		setSize(460, 350);
		setLocation(500, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setResizable(false);

		next.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String mobile = phone.getText();
		String quali = (String) qual.getSelectedItem();
		String r = (String) religionC.getSelectedItem();
		String aadhar = aadharNo.getText();

		if (phone.getText().equals("") || quali.equals("")

				|| aadharNo.getText().matches("")

				|| r.equals(""))

		{
			JOptionPane.showMessageDialog(null, "Fill all the required fields");
		} else {

			try {

				ConToDb conToDb = new ConToDb();
				conToDb.connect();
				log.info("-----------insert into details-------------");
				String query = "insert into details values(?,?,?,?,?)";
				PreparedStatement s = conToDb.c.prepareStatement(query);
				s.setString(1, aadhar);
				s.setString(2, id);
				s.setString(3, mobile);
				s.setString(4, r);
				s.setString(5, quali);
				s.executeUpdate();

				setVisible(false);
				new AccountDetail(id).setVisible(true);

			} catch (Exception e1) {
				System.out.println(e1);
			}

		}
	}

}
