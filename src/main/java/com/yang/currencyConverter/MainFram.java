package com.yang.currencyConverter;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
			"UAE Dirham",
			"Afghan Afghani",
			"Albanian Lek",
			"Armenian Dram",
			"Netherlands Antillian Guilder",
			"Angolan Kwanza",
			"Argentine Peso",
			"Australian Dollar",
			"Aruban Florin",
			"Azerbaijani Manat",
			"Bosnia and Herzegovina Mark",
			"Barbados Dollar",
			"Bangladeshi Taka",
			"Bulgarian Lev",
			"Bahraini Dinar",
			"Burundian Franc",
			"Bermudian Dollar",
			"Brunei Dollar",
			"Bolivian Boliviano",
			"Brazilian Real",
			"Bahamian Dollar",
			"Bhutanese Ngultrum",
			"Botswana Pula",
			"Belarusian Ruble",
			"Belize Dollar",
			"Canadian Dollar",
			"Congolese Franc",
			"Swiss Franc",
			"Chilean Peso",
			"Chinese Renminbi",
			"Colombian Peso",
			"Costa Rican Colon",
			"Cuban Peso",
			"Cape Verdean Escudo",
			"Czech Koruna",
			"Djiboutian Franc",
			"Danish Krone",
			"Dominican Peso",
			"Algerian Dinar",
			"Egyptian Pound",
			"Eritrean Nakfa",
			"Ethiopian Birr",
			"Euro",
			"Fiji Dollar",
			"Falkland Islands Pound",
			"Faroese Króna",
			"Pound Sterling",
			"Georgian Lari",
			"Guernsey Pound",
			"Ghanaian Cedi",
			"Gibraltar Pound",
			"Gambian Dalasi",
			"Guinean Franc",
			"Guatemalan Quetzal",
			"Guyanese Dollar",
			"Hong Kong Dollar",
			"Honduran Lempira",
			"Croatian Kuna",
			"Haitian Gourde",
			"Hungarian Forint",
			"Indonesian Rupiah",
			"Israeli New Shekel",
			"Manx Pound",
			"Indian Rupee",
			"Iraqi Dinar",
			"Iranian Rial",
			"Icelandic Krona",
			"Jersey Pound",
			"Jamaican Dollar",
			"Jordanian Dinar",
			"Japanese Yen",
			"Kenyan Shilling",
			"Kyrgyzstani Som",
			"Cambodian Riel",
			"Kiribati Dollar",
			"Comorian Franc",
			"South Korean Won",
			"Kuwaiti Dinar",
			"Cayman Islands Dollar",
			"Kazakhstani Tenge",
			"Lao Kip",
			"Lebanese Pound",
			"Sri Lanka Rupee",
			"Liberian Dollar",
			"Lesotho Loti",
			"Libyan Dinar",
			"Moroccan Dirham",
			"Moldovan Leu",
			"Malagasy Ariary",
			"Macedonian Denar",
			"Burmese Kyat",
			"Mongolian Togrog",
			"Macanese Pataca",
			"Mauritanian Ouguiya",
			"Mauritian Rupee",
			"Maldivian Rufiyaa",
			"Malawian Kwacha",
			"Mexican Peso",
			"Malaysian Ringgit",
			"Mozambican Metical",
			"Namibian Dollar",
			"Nigerian Naira",
			"Nicaraguan Córdoba",
			"Norwegian Krone",
			"Nepalese Rupee",
			"New Zealand Dollar",
			"Omani Rial",
			"Panamanian Balboa",
			"Peruvian Sol",
			"Papua New Guinean Kina",
			"Philippine Peso",
			"Pakistani Rupee",
			"Polish Zloty",
			"Paraguayan Guarani",
			"Qatari Riyal",
			"Romanian Leu",
			"Serbian Dinar",
			"Russian Ruble",
			"Rwandan Franc",
			"Saudi Riyal",
			"Solomon Islands Dollar",
			"Seychellois Rupee",
			"Sudanese Pound",
			"Swedish Krona",
			"Singapore Dollar",
			"Saint Helena Pound",
			"Sierra Leonean Leone",
			"Somali Shilling",
			"Surinamese Dollar",
			"South Sudanese Pound",
			"Sao Tome and Principe Dobra",
			"Syrian Pound",
			"Eswatini Lilangeni",
			"Thai Baht",
			"Tajikistani Somoni",
			"Turkmenistan Manat",
			"Tunisian Dinar",
			"Tongan Paʻanga",
			"Turkish Lira",
			"Trinidad and Tobago Dollar",
			"Tuvaluan Dollar",
			"New Taiwan Dollar",
			"Tanzanian Shilling",
			"Ukrainian Hryvnia",
			"Ugandan Shilling",
			"United States Dollar",
			"Uruguayan Peso",
			"Uzbekistani So'm",
			"Venezuelan Bolivar Soberano",
			"Vietnamese Dong",
			"Vanuatu Vatu",
			"Samoan Tala",
			"Central African CFA Franc",
			"East Caribbean Dollar",
			"Special Drawing Rights",
			"West African CFA franc",
			"CFP Franc",
			"Yemeni Rial",
			"South African Rand",
			"Zambian Kwacha",
			"Zimbabwean Dollar"
	};
	private static String[] CURRENCIES2 = new String[162];
	private static Map<String, String> apprev = new HashMap<String, String>();
	
	
	private static final String APIKEY = "8df47781ac3d279c54abb657";

	private JPanel contentPane;
	private JTextField textCurrency1;
	private JTextField textCurrency2;

	private JComboBox<String> baseBox;
	private JComboBox<String> targetBox;

	public static void getCode() throws FileNotFoundException {
		FileReader file = new FileReader("codes.json");
		JsonElement root = JsonParser.parseReader(file);
		JsonObject jsonobj = root.getAsJsonObject();
		
		// Accessing object
		JsonArray codes = jsonobj.getAsJsonArray("supported_codes");
		
		for(int i = 0; i < codes.size(); i++) {
			JsonArray innerArray = codes.get(i).getAsJsonArray();
			String key = innerArray.get(1).getAsString();
			String val = innerArray.get(0).getAsString();
			CURRENCIES2[i] = key;
			apprev.put(key, val);
		}
		
		System.out.println(CURRENCIES2.length);
		
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getCode();
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
		
		
		baseBox = new JComboBox<String>(CURRENCIES2);
		baseBox.setBounds(147, 96, 82, 27);
		contentPane.add(baseBox);
		
		textCurrency2 = new JTextField();
		textCurrency2.setEditable(false);
		textCurrency2.setBounds(230, 95, 130, 26);
		contentPane.add(textCurrency2);
		textCurrency2.setColumns(10);
		
		targetBox = new JComboBox<String>(CURRENCIES2);
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
