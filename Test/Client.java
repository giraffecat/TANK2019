import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		
		Socket s = new Socket("localhost",8888);

		BufferedWriter Writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
		Writer.write("fuck you idiot\n ");
		
		Writer.flush();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String str = reader.readLine();
		System.out.println(str);

		Writer.close();
	}	
}
