package com.yang.currencyConverter;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class MainFram extends JFrame {
	private static final String[] CURRENCIES = {
			"Euro",
			"United States Dollar"
	};
	
	private static Map<String, String> apprev = new HashMap<String, String>();
	
	
	private static final String APIKEY = "8df47781ac3d279c54abb657";

	private JPanel contentPane;
	private JTextField textCurrency1;
	private JTextField textCurrency2;
	
	private JComboBox<String> baseBox;
	private JComboBox<String> targetBox;
	
	public static void setMap() {
		apprev.put("Euro", "EUR");
		apprev.put("United States Dollar", "USD");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setMap();
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
		textCurrency1.setBounds(18, 95, 130, 26);
		contentPane.add(textCurrency1);
		textCurrency1.setColumns(10);
		
		baseBox = new JComboBox<String>(CURRENCIES);
		baseBox.setBounds(147, 96, 82, 27);
		contentPane.add(baseBox);
		
		textCurrency2 = new JTextField();
		textCurrency2.setEditable(false);
		textCurrency2.setBounds(230, 95, 130, 26);
		contentPane.add(textCurrency2);
		textCurrency2.setColumns(10);
		
		targetBox = new JComboBox<String>(CURRENCIES);
		targetBox.setBounds(362, 96, 82, 27);
		contentPane.add(targetBox);
		
		
		/*
		 * Will grab the base currency. And send a request using the API 
		 * to get the correct target currency.
		 */
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(185, 125, 88, 29);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String baseCur = String.valueOf(baseBox.getSelectedItem());
				String targetCur = String.valueOf(targetBox.getSelectedItem());
				String amount = String.valueOf(textCurrency1.getText());
				
				String baseVal = apprev.get(baseCur);
				String targetVal = apprev.get(targetCur);
				
				//https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP/AMOUNT
				String url_str = "https://v6.exchangerate-api.com/v6/" + APIKEY + "/pair" + "/" + baseVal + "/" + targetVal + "/" + amount;
				
				URL url;
				try {
					url = new URL(url_str);
					HttpURLConnection request = (HttpURLConnection) url.openConnection();
					request.connect();
					
					// Convert to JSON
					JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
					JsonObject jsonobj = root.getAsJsonObject();
					
					// Accessing object
					String req_result = jsonobj.get("conversion_result").getAsString();
					
					textCurrency2.setText(req_result);
					
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				

				

				
			}
		});
		
		contentPane.add(submitButton);
	}

}
