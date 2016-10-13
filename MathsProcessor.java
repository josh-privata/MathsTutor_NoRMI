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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author josh
 */
public class MathsProcessor implements Runnable {

    // System Variables
    private Socket connectionSocket;
    private PrintWriter output;
    private BufferedReader input;
    private MathsTutor maths;
    private String message;
    private final String linesplit = "#";

    // Constructor creates stream IO and sends header
    public MathsProcessor(Socket s) {
        try {
            this.connectionSocket = s;
            System.out.println("[+] Server has accepted connection from client at address "
                    + connectionSocket.getInetAddress() + ":" + connectionSocket.getPort());
            input = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream())));
            // Send linesplit deliminater and server header
            output.println(linesplit);
            output.flush();
            output.println("Welcome to the Maths Tutor Service.");
            output.flush();
        } catch (Exception e) {
        }
    }

    // Recieve data from client
    private String Recieve() throws IOException {
        String s = input.readLine();
        System.out.println("[+] Received a response from client at address "
                + connectionSocket.getInetAddress() + ":" + connectionSocket.getPort());
        System.out.print("<+> ");
        return s;
    }

    // Main runtime method
    @Override
    public void run() {
        while (true) {
            try {
                String summary;
                System.out.println("[+] Attempting to create a new MathsTutor session");
                maths = new MathsTutor();

                // Send menu
                System.out.println("[+] Sending menu to client");
                output.println(maths.getMenu());
                output.flush();

                // Recieve menu selection
                try {
                    message = Recieve();
                    if (message.equalsIgnoreCase("q")) {
                        System.out.print("q\n[+] Connection to server has been ended by the client\n");
                        break;
                    }
                    maths.setOperation(message);
                } catch (Exception e) {
                }
                summary = "Correct Answers" + linesplit;

                // Initiate question loop
                for (int i = 0; i < maths.getNumberOfQuestions(); i++) {

                    // Send problem
                    System.out.println("[+] Sending problem to client");
                    String problem = maths.setProblem();
                    output.println(problem + linesplit);
                    output.flush();

                    // Recieve answer
                    int response = 0;
                    try {
                        message = Recieve();
                        if (message.equalsIgnoreCase("q")) {
                            System.out.print("q\n[+] Connection to server has been ended by the client\n");
                            break;
                        }
                        response = Integer.parseInt(message);
                    } catch (IOException | NumberFormatException e) {
                    }
                    maths.checkAnswer(response);
                    summary += problem + message + maths.getResult() + linesplit;
                }

                // Send summary
                String total = "You got a total of " + maths.getRight()
                        + " out of " + maths.getNumberOfQuestions()
                        + " correct" + linesplit
                        + "Press <enter> to continue";
                summary += total + linesplit;
                System.out.println("[+] Sending summary response to client");
                output.println(summary);
                output.flush();

                // Recive acknowledgement
                try {
                    Recieve();
                } catch (Exception e) {
                }

            } catch (Exception e) {
                System.out.println("[-] Error with input/output to client");
                break;
            }
        }
    }
}
