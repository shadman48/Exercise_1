
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
    	
    	Socket socket = null;
    	
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            
        	System.out.println("Server is now online.");
        	while (true) 
        	{
                try 
                {
                	socket = serverSocket.accept();
                    String address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Client connected: %s%n", address);  
                }
                catch (IOException e) {
                    System.out.println("I/O error: " + e);
                }
                
                new EchoThread(socket).start();
            }
        }
    }
}



class EchoThread extends Thread {
    protected Socket socket;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        InputStream in = null;
        BufferedReader br = null;
        DataOutputStream out = null;
        OutputStream os = null;
        PrintStream ps = null;
        String address;
        try {
        	address = socket.getInetAddress().getHostAddress();
        	in = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            out = new DataOutputStream(socket.getOutputStream());
            os = socket.getOutputStream();
            ps = new PrintStream(os, true, "UTF-8");
            
            ps.println("Hello : " + address);
        } catch (IOException e) {
            return;
        }
        String str;
        Boolean bool = true;
        
        while (bool == true) {
            try {
            	str = br.readLine();
                if ((str == null) || str.equalsIgnoreCase("exit")) {
                	System.out.printf("Client disconnected: %s%n", address);
                	socket.close();
                    return;
                } else {
                    out.writeBytes(str + "\n\r");
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}


