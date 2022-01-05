import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;


public class VTHttp {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8550),0);
        System.out.println("Basic Http VT Server started...");
        HttpContext context = server.createContext("/");
        context.setHandler(
                exchange -> {
                    try {
                        handleRequest(exchange);
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
     */
    private static void handleRequest(HttpExchange exchange) throws IOException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //creates new Duke object
                Duke duke = new Duke(1, "DukeVT", 20.0, 'A', true);

                //writes data into a file
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(new File("duke.csv")));

                    bw.write(duke.getId() + ", " + duke.getName() + ", " + duke.getHeight() + ", " + duke.getCategory() + ", " + duke.isHasGadgets());
                    bw.flush();
                    bw.close();

                    //returns the object to the user
                    // response message
                    String response = duke.getName();
                    exchange.sendResponseHeaders(200, response.getBytes().length);

                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.flush();
                    os.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread.startVirtualThread(runnable);
    }
}
