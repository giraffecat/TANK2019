import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class imagetest {

	@Test
	public void testLoadImage() {
		
		try {
			BufferedImage image = ImageIO.read(new File("./src/images/BadTank1"));
			assertNotNull(image);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
