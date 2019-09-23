/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Artik
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of test.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML)
    public String getXml() {
        //throw new UnsupportedOperationException();
        return "Привет from getXml()";
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Path("users")
    @Path("/users")
    public ResponseList getUsers() {
        User customer = new User();
        customer.setId(100);
        customer.setName("Вася");
        customer.setAge(29);
        
        ResponseList responseList = new ResponseList();
        responseList.getUsers().add(customer);
        return responseList;
    }
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
