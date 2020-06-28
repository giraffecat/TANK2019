package com.bryce.Nettychat;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends Frame {
	
	public static final ClientFrame INSTANCE = new ClientFrame();
	
	private TextArea ta = new TextArea();
	private TextField tf = new TextField();
	private Client client = null;
	
	private ClientFrame() {
		this.setSize(300, 400);
		this.setLocation(400, 20);
		this.add(ta,BorderLayout.CENTER);
		this.add(tf,BorderLayout.SOUTH);
		this.setTitle("NettyChatter");
		tf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				client.send(tf.getText());
//				ta.setText(ta.getText()+tf.getText()+"\r\n");
				
				tf.setText("");
			}
		});
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				client.closeConnection();
				System.out.println(e);
				System.exit(0);
			}
			
		});
	}
	
	public void connectToServer() {
		client = new Client();
		client.connect();
	}

	public static void main(String[] args) {
		ClientFrame fm = ClientFrame.INSTANCE;
		fm.setVisible(true);
		fm.connectToServer();
	}

	public void updateText(String str) {
		ta.setText(ta.getText()+str+"\r\n");		
	}
}
