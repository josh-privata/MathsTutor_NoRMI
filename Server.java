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
package server;

import java.io.*;
import java.net.*;

/**
 * @filename Server.java
 *
 * @application MathsTutor_Server
 *
 * @author Josh Cannons April 9th 2016
 *
 * @description
 *
 */
public class Server {

    // User Variables
    private static final int PORT = 5555;

    // System Variables
    private static MathsProcessor SERVERPROCESSOR;

    // Go for launch
    public static void main(String argv[]) {
        System.out.println("[+] Threaded Server is Running  ");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("[+] Successfully created socket");
        } catch (IOException e) {
            System.out.println("[-] Error creating socket");
        }
        while (true) {
            Socket sock = null;
            Thread serverThread;
            try {
                try {
                    sock = serverSocket.accept();
                    System.out.println("[+] Successfully accepted socket");
                } catch (IOException e) {
                    System.out.println("[-] Error accepting client connection");
                }
                if (sock != null) {
                    SERVERPROCESSOR = new MathsProcessor(sock);
                    serverThread = new Thread(SERVERPROCESSOR);
                    serverThread.start();
                }
            } catch (Exception e) {
                try {
                    serverSocket.close();
                    System.out.println("[+] Connection to client has been ended by server");
                } catch (Exception ex) {
                }
            }
        }
    }
}
