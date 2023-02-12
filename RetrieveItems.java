import java.io.IOException; 
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.String;

public class RetrieveItems 
{
	
	/**
     * /getItemDetails
     * Get Request: Returns all items sold in a store with their base price and discount prices
	 * @throws ParseException 
     */
    public static Object getItemsDetails() throws ParseException 
    {
        //String results = "Request Failed";  // Placeholder if function fails
        JSONArray array = new JSONArray();
        Object obj1 = 0;

        JSONParser jsonParser = new JSONParser();

        
        String url = "https://apimdev.wakefern.com/mockexample/V1/getItemDetails";  // getItemDetails URL

        // 1 - Create Client
        HttpClient clientItems = HttpClient.newHttpClient();

        // 2 - Build Request with headers
        HttpRequest requestItems = HttpRequest.newBuilder()
                .uri(URI.create(url))      //Set URL
                .header("Ocp-Apim-Subscription-Key", "4ae9400a1eda4f14b3e7227f24b74b44")      //Set Headers
                .method("GET", HttpRequest.BodyPublishers.noBody())                             //Set Method
                .build();

        try {
            // 3 - Client sends request and saves the response in a variable
            HttpResponse<String> responseItems = clientItems.send(requestItems, HttpResponse.BodyHandlers.ofString());

            // 4 - Save response in results variable
            obj1 = responseItems.body();
            
            
            //test______________
            

            JSONObject jsonObject = (JSONObject) jsonParser.parse((String) obj1);
            String val = (String) jsonObject.get("key_name");
            JSONArray jsonArray = (JSONArray) jsonObject.get("Department");
            
            Iterator<String> iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
               System.out.println(iterator.next());
            }
            

            //test __________
            
            // 5 - Error Handling
            /**
             * Error Handling Conventions:
             * Errors are generally saved in a separate log file to be accessed in the future
             * In production, code generally should not print anything on the console
             * Console logging is okay for development/debugging purposes
             */
        } catch (IOException e) {
            System.out.println("Cannot send request to " + url + " : " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Cannot send request to " + url + " : " + e.getMessage());
        }

        return obj1;
    }


}
