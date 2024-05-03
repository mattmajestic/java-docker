import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // Set response headers
                Headers headers = exchange.getResponseHeaders();
                headers.set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, 0);
                
                // Read and write content of index.html
                OutputStream os = exchange.getResponseBody();
                File file = new File("index.html");
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                fis.close();
                os.close();
            }
        });
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port " + port);
    }
}
