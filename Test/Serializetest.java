import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

public class Serializetest {

	@Test
	public void testSave() {
		
		try {
			T t = new T();
			File f = new File("./s.dat");
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(t);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}		
	}
	
	@Test
	public void testLoad() {
			
			try {
				File f = new File("./s.dat");
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				T t = (T)ois.readObject();
				System.out.println(t.m+t.n);
				Assert.assertEquals(4,t.m);
				ois.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}		
		}
		
		
	}
	class T implements Serializable{
		int m = 4;
		 int n = 8;
	}
