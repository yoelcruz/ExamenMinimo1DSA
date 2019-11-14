package edu.upc.dsa.services;


import edu.upc.dsa.ProductsManager;
import edu.upc.dsa.ProductsManagerImp;
import edu.upc.dsa.TracksManagerImpl;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.Track;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/products", description = "Endpoint to Product Service")
@Path("/products")
public class ProductsService {

    private ProductsManager pm;

    public ProductsService() {

        this.pm = new ProductsManagerImp().getInstance();
        if (pm.size()==0) {
            this.pm.addProduct("coca", 5, 20);
            this.pm.addProduct("cafe", 5, 1.5);
            this.pm.addProduct("pasta", 5, 15);
        }
    }

    @GET
    @ApiOperation(value = "get all products", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/orderedByPrice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {

        List<Product> products = this.pm.productsOrderedByPrice();

        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "Get a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") String id) {
        Product p = this.pm.getProductById(id);
        if (p == null) return Response.status(404).build();
        else  return Response.status(201).entity(p).build();
    }

    @DELETE
    @ApiOperation(value = "delete a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        Product p = this.pm.getProductById(id);
        if (p == null) return Response.status(404).build();
        else this.pm.deleteProduct(id);
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Create a new Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Product.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct(Product product) {

        if (product.getId()==null)  return Response.status(500).entity(product).build();
        this.pm.addProduct(product);
        return Response.status(201).entity(product).build();
    }

    @PUT
    @ApiOperation(value = "Update a Product with the id that you write", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateProductById(Product product) {

        Product p = this.pm.updateProductById(product);

        if (p == null) return Response.status(404).build();

        return Response.status(201).build();
    }

}