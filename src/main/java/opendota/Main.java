package opendota;

import java.io.*;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
    
public class Main {

    public static void main(String[] args) throws Exception {

        File replayFile = new File(args[0]);
        File outFile = new File(args[1]);
        InputStream is = new FileInputStream(replayFile);
        OutputStream os = new FileOutputStream(outFile);
        try {
            new Parse(is, os);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        os.close();
    }
    
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            t.sendResponseHeaders(200, 0);
            InputStream is = t.getRequestBody();
            OutputStream os = t.getResponseBody();
            try {
            	new Parse(is, os);
            }
            catch (Exception e)
            {
            	e.printStackTrace();
            }
            os.close();
        }
    }
}
