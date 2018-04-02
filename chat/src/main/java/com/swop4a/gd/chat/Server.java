package com.swop4a.gd.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static final int PORT = 8083;

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(PORT);

		System.out.println("Server is running...");

		Socket accept = server.accept();

		System.out.println("Received request");

		try (InputStream in = accept.getInputStream();
			OutputStream out = accept.getOutputStream()) {

			byte[] buffer = new byte[32 * 1024];
			int read = in.read(buffer);

			String line = new String(buffer, 0, read);
			System.out.printf("Client> %s", line);

			out.write(line.getBytes());
			out.flush();
		}
	}
}
