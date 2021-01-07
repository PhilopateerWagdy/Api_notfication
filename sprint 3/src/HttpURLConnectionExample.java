
import model.LanguageEnum;
import model.Notification;
import model.Type;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        // Sending get request
         http.sendingGetRequest();

        // Sending post request
        Notification notification;
        notification =new Notification(0,"confirmation","hazem", LanguageEnum.English, Type.mail,"hazem");
        http.sendingPostRequest(notification);




    }

    // HTTP GET request
    public void sendingGetRequest() throws Exception {

        String urlString = "http://localhost:8080/api/Notification";

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // By default it is GET request
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("Sending get request : "+ url);
        System.out.println("Response code : "+ responseCode);

        // Reading response from input Stream
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println(response.toString());

    }

    // HTTP GET request
    public void sendingGetRequest(String user) throws Exception {

        String urlString = "http://localhost:8080/api/Notification/"+user;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // By default it is GET request
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("Sending get request : "+ url);
        System.out.println("Response code : "+ responseCode);

        // Reading response from input Stream
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println(response.toString());

    }

    // HTTP Post request
    public void sendingPostRequest(Notification notification) throws Exception {

        String url = "http://localhost:8080/api/Notification";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("content", "\""+notification.getContent()+"\"");
        con.setRequestProperty("subject", "\""+notification.getSubject()+"\"");
        con.setRequestProperty("language", "\""+notification.getLanguage().toString()+"\"");
        con.setRequestProperty("type", "\""+notification.getType().toString()+"\"");
        con.setRequestProperty("user", "\""+notification.getUser()+"\"");
        con.setRequestProperty("Content-Type","application/json");

        String postJsonData = "{\"content\":\""+notification.getContent()+"\",\"subject\":\""+notification.getSubject()+"\"," +
                "\"language\":\""+notification.getLanguage().toString()+"\"," +
                " \"type\":\""+notification.getType().toString()+"\"" +
                ",\"user\":\""+notification.getUser()+"\"}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println(response.toString());
    }
}


