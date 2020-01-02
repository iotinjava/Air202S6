import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

public class Air202S6Demo01Uart {
	private static String com = "COM1";
	private static int baudrate = 115200;

	private static String EXIT_CMD = "quit";

	private static StreamConnection sc;
	private static InputStream in;
	private static OutputStream out;
	
	public static void main(String[] args) {
		String host = "comm:" + com + ";baudrate=" + baudrate;
		System.out.println("Air202S6Demo01Uart start...");
		try {
			sc = (StreamConnection) Connector.open(host);
			in = sc.openInputStream();
			out = sc.openOutputStream();

			int len = 0;
			byte[] buffer = new byte[256];
			StringBuffer stringBuffer = null;

			while ((len = in.read(buffer)) != -1) {
				String str = new String(buffer,0,len);
				System.out.println("len:" + len + "; str:"+str);
				
				if(EXIT_CMD.equals(str))
					break;

				out.write(str.getBytes());
				out.write("\r\n".getBytes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null) 
					out.close();
				if (sc != null) 
					sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Air202S6Demo01Uart end...");
	}
}
