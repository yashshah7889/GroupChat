package chatting.application;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.*;


public class User1 extends JFrame implements ActionListener, Runnable{
	
	JPanel p1;
	JTextField t1;
	JButton b1;
	JTextArea a1;
	
	BufferedWriter writer;
	BufferedReader reader;
	String user;
	User1(String user){
		this.user=user;
		p1= new JPanel();
		p1.setLayout(null);
		p1.setBackground(Color.gray);
		p1.setBounds(0, 0, 400, 70);
		add(p1);
		
		ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
		Image i2= i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i3= new ImageIcon(i2);
		JLabel l2=new JLabel(i3);
		l2.setBounds(5,17,30,30);
		p1.add(l2);		
		
		l2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});
	
		
		JLabel l1=new JLabel("DSD");
		l1.setFont(new Font("SAN_SARIF",Font.BOLD, 30));
		l1.setBounds(50, 3, 100, 55);
		p1.add(l1);
		
		JLabel l3=new JLabel(this.user);
		l3.setFont(new Font("SAN_SARIF",Font.PLAIN, 20));
		l3.setBounds(50, 30, 100, 55);
		p1.add(l3);
		
		
		a1= new JTextArea();
		a1.setBounds(5, 75, 440, 420);
		a1.setFont(new Font("SAN_SARIF",Font.PLAIN, 16));
		a1.setEditable(false);
		a1.setLineWrap(true);
		a1.setWrapStyleWord(false);
		add(a1);;
		
		
		t1= new JTextField();
		t1.setFont(new Font("SAN_SARIF",Font.PLAIN, 16));
		t1.setBounds(5, 500, 310, 40);
		add(t1);
		
		b1= new JButton("SEND");
		b1.setBounds(315, 500, 80, 40);
		b1.addActionListener(this);
		add(b1);
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setSize(400,550);
		//setLocation(0,100);
		//setUndecorated(true);
		setVisible(true);
		
		try {
			
			Socket socketClient= new Socket("localhost", 3002); 
			writer= new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			reader= new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		}catch (Exception e){
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String s = this.user+": "+t1.getText();
		try{
			writer.write(s);
			writer.write("\r\n");
			writer.flush();
		}catch (Exception e){e.printStackTrace();}
		t1.setText("");
	}
	
	@Override
	public void run() {
		try{
		String msg= "";
		while((msg = reader.readLine()) != null) {
			a1.append(msg + "\n");
		}
		}catch (Exception e){e.printStackTrace();}
		t1.setText("");
	}
	
		
	
//	
//	public static void main(String args[]) {
//		User1 one= new User1();
//		Thread t1= new Thread(one);
//		t1.start();
//	}
//	

	
}
