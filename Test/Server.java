import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
			ServerSocket ss = new ServerSocket();
			ss.bind(new InetSocketAddress("localhost",8888));
			Socket s = ss.accept();
			System.out.println("Hello");
		
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = reader.readLine();
			System.out.println(str);
	}

}
