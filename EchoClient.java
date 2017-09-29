
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) 
        {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
       
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        BufferedReader brIn = new BufferedReader(new InputStreamReader(System.in));
	        String input;
	        
	        System.out.print("Client > ");
        	
	        while((input = brIn.readLine()) != null)
	        {
	        	out.println(input);
	        	if(!input.equalsIgnoreCase("exit"))
	        	{
	        		String sin = in.readLine();
	        		System.out.println("Server > " + sin);
	        	}
	        	
	        	System.out.print("Client > ");
	        	 
	        }
	        
	        System.out.print("GoodBye.");
        	
        }
    }
}






