package prac2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySocket extends Socket {

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;

    public MySocket(String ipAdd, int port) {
        try {
            this.socket = new Socket(ipAdd, port);
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
        } catch (IOException e) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public MySocket(Socket mysocket) {
        try {
            this.socket = mysocket;
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
        } catch (IOException e) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void println(String s) {
        this.writer.println(s);
    }

    public String readLine() {
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    public void close() {
        try {
            this.socket.close();
            this.writer.close();
            this.reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
