import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketConnector {

	// umjesto 127.0.0.1. moze i localHost
	public static final String serverAdress = "127.0.0.1";
	// port mora biti isti ako i kod servera!!
	public static final int port = 1728;

	/**
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */

	private static void connectToServer() throws UnknownHostException,
			IOException {
		Scanner input = new Scanner(System.in);
		Socket client = new Socket(serverAdress, port);

		SocketRW sc = new SocketRW(client.getInputStream(),
				client.getOutputStream());
		while(true)
		{
			System.out.println("Enter message: ");
			String msg = input.nextLine();
			sc.send(msg);
			String rec = sc.recieve();
			System.out.println(rec);
			if (msg.equals("quit") || rec.equals("quit"))
			{
				break;
			}
		}
		

		System.out.println("\nGotovo");
		client.close();

	}

	public static void main(String[] args) {
		try {
			connectToServer();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}