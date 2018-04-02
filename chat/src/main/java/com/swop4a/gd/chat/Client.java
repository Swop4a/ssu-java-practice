package com.swop4a.gd.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private static final Scanner SCANNER = new Scanner(System.in);

	private static final String HOST = "localhost";
	private static final int PORT = 8083;

	public static void main(String[] args) throws IOException {

		Socket client = new Socket(HOST, PORT);

		try (InputStream in = client.getInputStream();
			OutputStream out = client.getOutputStream()) {

			String line = SCANNER.nextLine();
			out.write(line.getBytes());
			out.flush();

			byte[] buffer = new byte[30 * 1024];
			int read = in.read(buffer);

			System.out.printf("Server> %s", new String(buffer, 0, read));
		}
	}
}
