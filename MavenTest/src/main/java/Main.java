import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import java.net.URI;
import java.net.URISyntaxException;

public class    Main {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        WebSocketClient mWs = new WebSocketClient( new URI( "wss://ws.kraken.com" ), new Draft_6455() )
        {
            @Override
            public void onMessage( String message ) {
//                System.out.println("{ \"entry\": " + message + "}");
                JSONObject obj = new JSONObject("{ \"entry\": " + message + "}");
                System.out.println(obj.get("entry"));
//                String channel = obj.getString("channel");
            }

            @Override
            public void onOpen( ServerHandshake handshake ) {
                System.out.println( "opened connection" );
            }

            @Override
            public void onClose( int code, String reason, boolean remote ) {
                System.out.println( "closed connection" );
            }

            @Override
            public void onError( Exception ex ) {
                ex.printStackTrace();
            }

        };
        //open websockets
        mWs.connect();
//        JSONObject testConnection= new JSONObject();
//        testConnection.put("event","ping");
//        String testMessage= testConnection.toString();
//        mWs.send(testMessage);
        Thread.sleep(3000);
        JSONObject obj = new JSONObject();
//        obj.put("event", "ping");
        String[] pairs = {"XBT/USD","ETH/USD","ADA/USD","DOGE/USD","XLM/USD"};
        obj.put("event", "subscribe");
        obj.put("pair", pairs);
        obj.put("subscription", new JSONObject().put("name", "ticker"));
        String message = obj.toString();
        mWs.send(message);
//        Thread.sleep(4000);
    }
}
