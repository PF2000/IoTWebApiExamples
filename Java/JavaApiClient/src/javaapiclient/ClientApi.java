/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapiclient;

/**
 *
 * @author Ferreira
 */
public class ClientApi {
    
    public static void main(String[] args) {
        
        SchoolController sc = new SchoolController();
        //Get all Schools
        sc.GetAll();
        //Create a school object to post
        School school = new School("JavaTest", 1);
        //Post a school object
        school = sc.Post(school);
        //Get the created object
        sc.Get(school.id);
        //Change the  School name to update
        school.name = "TesteUpdateJava";
        //Update the school
        sc.Put(school.id,school);    
        //Delete the school
        sc.Destroy(school.id);
    }
}
