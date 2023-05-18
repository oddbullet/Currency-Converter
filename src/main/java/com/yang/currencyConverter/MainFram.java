package com.yang.currencyConverter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;

public class MainFram extends JFrame {

	private JPanel contentPane;
	private JTextField textCurrency1;
	private JTextField textCurrency2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFram frame = new MainFram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFram() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textCurrency1 = new JTextField();
		textCurrency1.setBounds(37, 95, 130, 26);
		contentPane.add(textCurrency1);
		textCurrency1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(172, 95, 52, 27);
		contentPane.add(comboBox);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(185, 125, 88, 29);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		textCurrency2 = new JTextField();
		textCurrency2.setBounds(230, 95, 130, 26);
		contentPane.add(textCurrency2);
		textCurrency2.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(362, 96, 52, 27);
		contentPane.add(comboBox_1);
		contentPane.add(submitButton);
	}

}
