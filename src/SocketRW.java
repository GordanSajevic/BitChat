import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketRW {
	InputStream is;
	OutputStream os;

	public SocketRW(InputStream is, OutputStream os) {
		this.is = is;
		this.os = os;
	}

	public void send(String msg) throws IOException {

		byte[] msgByte = msg.getBytes();
		os.write(msgByte.length);

		os.write(msgByte);
	}

	public String recieve() throws IOException {
		StringBuilder sb = new StringBuilder();
		int byteRead = 0;
		int msgLength = is.read();
		byte[] buffer = new byte[msgLength];
		while ((byteRead += is.read(buffer)) >= 0) {
			sb.append(new String(buffer).replaceAll("\\s+", " "));
			if (byteRead >= msgLength)
				break;

		}

		return sb.toString();

	}

}