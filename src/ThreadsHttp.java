import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;


public class ThreadsHttp {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8550),0);
        System.out.println("Basic http server threads started...");
        HttpContext context = server.createContext("/");
        context.setHandler(
                exchange -> {
                    try {
                        handleRequestThread(exchange);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        server.start();
    }


    /**
     * Response to Vegeta attack
     * @param exchange Request received
     * @throws IOException I/O exception
     * @throws InterruptedException Thread sleep
     */
    
    
    
    
    private static void handleRequestThread(HttpExchange exchange) throws IOException, InterruptedException{
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
               //creates new Duke object
                   Duke duke = new Duke(1, "Duke", 20.0, 'A', true);
                   BufferedWriter bw = new BufferedWriter(new FileWriter(new File("duke.csv")));

                   bw.write(duke.getId() + ", " + duke.getName());

                   String response = duke.getName();
                   exchange.sendResponseHeaders(200, response.getBytes().length);
                   OutputStream os = exchange.getResponseBody();
                   os.write(response.getBytes());
           }
       };

       Thread thread = new Thread(runnable);
       thread.start();
    }
}
