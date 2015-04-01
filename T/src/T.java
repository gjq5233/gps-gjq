import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;

public class T {
	public static void main(String[] args) {
		
		ByteBuffer b= null;
		try {
			ServerSocket ss = new ServerSocket(15501);
			System.out.println("listern 15501");
			Thread.sleep(20000);
			System.out.println("end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
