/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapiclient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;


/**
 *
 * @author Ferreira
 */
public class SchoolController {

    DefaultHttpClient httpClient;
    String baseUrl; 
    
    public SchoolController() {
      httpClient = new DefaultHttpClient();
      baseUrl = "http://localhost:3000/";
    }
    
    public void GetAll(){     
        try {
        
            HttpGet getRequest = new HttpGet(baseUrl+"v1/schools");
            getRequest.addHeader("accept", "application/json");
            getRequest.addHeader("Authorization", "Token token=pJzVJ9DZlhWxVotjRRRqoQtt");
            
            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                            (response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                    System.out.println(output);
            }

            //httpClient.getConnectionManager(). shutdown();

        }catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(SchoolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Get(int id){     
        try {
            
            HttpGet getRequest = new HttpGet(baseUrl+"v1/schools/"+id);
            getRequest.addHeader("accept", "application/json");
            getRequest.addHeader("Authorization", "Token token=pJzVJ9DZlhWxVotjRRRqoQtt");
            
            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                            (response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                    System.out.println(output);
            }

            //httpClient.getConnectionManager().shutdown();

        }catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(SchoolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public School Post(School sc) {
        Gson gson = new Gson();
        String output1 = "";
        try {

            HttpPost postRequest = new HttpPost(baseUrl+"v1/schools");
            postRequest.addHeader("accept", "application/json");
            postRequest.addHeader("Authorization", "Token token=pJzVJ9DZlhWxVotjRRRqoQtt");

            //Serialize
            
            String json = gson.toJson(sc);
                      
            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");
            
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 201) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                            (response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                    System.out.println(output);
                    output1 += output;
            }

            //httpClient.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {

                e.printStackTrace();
        } catch (IOException e) {

                e.printStackTrace();

        }
        
        return gson.fromJson(output1, School.class);
    }
    
    public School Put(int id,School sc) {
        Gson gson = new Gson();
        String output1 = "";
        try {

            HttpPut putRequest = new HttpPut(baseUrl+"v1/schools/"+id);
            putRequest.addHeader("accept", "application/json");
            putRequest.addHeader("Authorization", "Token token=pJzVJ9DZlhWxVotjRRRqoQtt");

            //Serialize
            String json = gson.toJson(sc);
                      
            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");
            
            putRequest.setEntity(input);

            HttpResponse response = httpClient.execute(putRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                            (response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                    System.out.println(output);
                    output1 += output;
            }

            //httpClient.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {

                e.printStackTrace();
        } catch (IOException e) {

                e.printStackTrace();

        }
        
        return gson.fromJson(output1, School.class);
    }
    
    public void Destroy(int id) {
        try {

            HttpDelete deleteRequest = new HttpDelete(baseUrl+"v1/schools/"+id);
            deleteRequest.addHeader("accept", "application/json");
            deleteRequest.addHeader("Authorization", "Token token=pJzVJ9DZlhWxVotjRRRqoQtt");

            HttpResponse response = httpClient.execute(deleteRequest);

            if (response.getStatusLine().getStatusCode() != 204) {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + response.getStatusLine().getStatusCode());
            }
            httpClient.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {

                e.printStackTrace();
        } catch (IOException e) {

                e.printStackTrace();

        }
    }
}
