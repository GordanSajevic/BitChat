import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	// nesto kao adresa ili otvor gdje cekamo konekciju,
	// rade od 0 do cc.65000,
	// koristiti portove iznad 1500
	public static final int port = 1728;

	/**
	 * @throws IOException
	 * 
	 */

	public static void startServer() {
		Scanner input = new Scanner(System.in);
		try {
			// socket koji ceka da se neko na njega spoji
			ServerSocket server = new ServerSocket(port);
			while (true) {

				System.out.println("waiting");
				Socket client = server.accept();
				SocketRW sc = new SocketRW(client.getInputStream(), client.getOutputStream());
				while(true)
				{
					String rec = sc.recieve();
					System.out.println("Enter message: ");
					System.out.println(rec);
					String msg = input.nextLine();
					sc.send(msg);
					if (msg.equals("quit") || rec.equals("quit"))
					{
						break;
					}
				}
				client.close();
				// accept vraca new Socket
				// kada upisemo accept nista ispod ove
				// linije nece se izvrsiti dok se ne desi konekcija

				System.out.println("Gotovo");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		startServer();
	}

}