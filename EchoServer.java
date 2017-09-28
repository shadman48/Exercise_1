
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            		
        	System.out.println("Server is now online.");
    		
        	while (true) 
        	{
                try (Socket socket = serverSocket.accept()) 
                {
                    String address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Client connected: %s%n", address);
                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    out.printf("Hi %s, thanks for connecting!%n", address);
                    
                
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                  
                    String s;
                    while((s = in.readLine()) != null)
                    {
                    	if(s.equalsIgnoreCase("exit"))
                    		System.out.printf("Client disconnected: %s%n", address);
                    	out.println(s);
                    }
                    
                    
                }
                
            }
        }
    }
}


