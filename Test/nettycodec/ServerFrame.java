package nettycodec;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ServerFrame extends Frame{
	public static final ServerFrame INTSANCE = new ServerFrame();
	
	TextArea taServer = new TextArea();
	TextArea taClient = new TextArea();
 
	private NettyServer server = new NettyServer();
	
	public ServerFrame() {
		this.setSize(800,600);
		this.setLocation(300, 30);
		Panel p = new Panel(new GridLayout(1, 2));
		p.add(taServer);
		p.add(taClient);
		this.add(p);
		
		taServer.setFont(new Font("consolas",Font.PLAIN, 25));
		taClient.setFont(new Font("consolas",Font.PLAIN, 25));

		this.updateServerMsg("server:");
		this.updateClientMsg("Client:");
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
	}
	
	public void updateServerMsg(String str) {
		this.taServer.setText(taServer.getText() + str + System.getProperty("line.separator"));
	}
	
	public void updateClientMsg(String str) {
		this.taClient.setText(taClient.getText() + str + System.getProperty("line.separator"));
	}
	
	public static void main(String[] args) {
		ServerFrame.INTSANCE.setVisible(true);
		ServerFrame.INTSANCE.server.ServerStart();

	}
}
