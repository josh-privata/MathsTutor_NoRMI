/*
 * Copyright (c) 2016, josh
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @filename Client.java
 *
 * @application MathsTutor_Client
 *
 * @author Josh Cannons April 9th 2016
 *
 * @description
 *
 */
public class Client implements Runnable {

    // User Variables
    private static final int PORT = 5555;
    private static final String ADDRESS = "localhost";

    // System Variables
    private Socket connectionSocket;
    private String inputText;
    private BufferedReader input;
    private BufferedWriter output;
    private Scanner scanner;
    private String linesplit;

    // Constructor creates stream IO and gets server header
    public Client(Socket s) {
        try {
            connectionSocket = s;
            System.out.println("[+] Client has connected to server");
            scanner = new Scanner(System.in);
            input = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
            // Recieve linesplit deliminater and server header
            linesplit = input.readLine();
            System.out.println(Recieve());
        } catch (Exception e) {
        }
    }

    // Close connection to server
    private void Close() throws IOException {
        output.write("q\r\n");
        output.flush();
        connectionSocket.close();
        System.out.println("[+] Connection to server has been ended by user");
        System.exit(0);
    }

    // Send data to server
    private void Send(String s) throws IOException {
        if (s.equalsIgnoreCase("q")) {
            Close();
        } else {
            output.write(s + "\r\n");
            output.flush();
        }
    }

    // Recieve data from server
    private String Recieve() {
        Boolean b = true;
        String line;
        inputText = "";
        while (b) {
            try {
                line = input.readLine();
                String[] i = line.split(linesplit);
                if (i.length == 1) {
                    inputText = i[0];
                } else {
                    for (String s : i) {
                        inputText += System.getProperty("line.separator") + s;
                    }
                }
            } catch (Exception ex) {
            }
            if (inputText != null) {
                b = false;
            }
        }
        return inputText;
    }

    // Main runtime method
    @Override
    public void run() {
        do {
            System.out.print(Recieve());
            try {
                Send(scanner.nextLine());
            } catch (IOException ex) {
            }
        } while (connectionSocket.isConnected());
    }

    // Go for launch
    public static void main(String argv[]) {
        try {
            System.out.println("[+] Client is Running");
            Socket socket = new Socket(ADDRESS, PORT);
            Client client = new Client(socket);
            while (true) {
                client.run();
            }
        } catch (Exception e) {
        }
    }

}
