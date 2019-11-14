package edu.upc.dsa.services;

import edu.upc.dsa.UsuariosManager;
import edu.upc.dsa.UsuariosManagerImp;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/usuarios", description = "Endpoint to Usuario Service")
@Path("/usuarios")
public class UsuariosService {

    private UsuariosManager um;

    public UsuariosService() {
        this.um = new UsuariosManagerImp().getInstance();
        if (um.size()==0) {
            this.um.addUsuario("1", "yoel", "cruz_torres");
            this.um.addUsuario("2", "miriam", "cruz_torres");
            this.um.addUsuario("3", "jose", "cruz_sierra");
            this.um.addObjetoConIdUsuario("1","espada",1);
            this.um.addObjetoConIdUsuario("1","cofre",3);
            this.um.addObjetoConIdUsuario("2","escudo",5);
        }
    }

    @GET
    @ApiOperation(value = "get all usuarios", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
    })
    @Path("/ordenadoAlfabeticamente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {

        List<Usuario> usuarios = this.um.usuariosOrdenadosAlfabeticamente();

        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios) {
        };
        return Response.status(201).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "Create a new Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {

        if (usuario.getId()==null)  return Response.status(500).entity(usuario).build();
        this.um.addUsuario(usuario);
        return Response.status(201).entity(usuario).build();
    }

    @PUT
    @ApiOperation(value = "Update a Usuario with the id that you write", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateUsuarioById(Usuario usuario) {

        Usuario u = this.um.updateUsuarioById(usuario);

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Get a Usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Usuario not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("id") String id) {
        Usuario u = this.um.getUsuarioById(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "Get Objeto de un usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Usuario not found")
    })
    @Path("/{id}/objetos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjetoDeUnUsuario(@PathParam("id") String id) {
        Usuario u = this.um.getUsuarioById(id);
        if (u == null) return Response.status(404).build();
        else {
            List<Objeto> objetos = u.getObjetos();
            GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(objetos) {};
            return Response.status(201).entity(entity).build();
        }
    }


    @POST
    @ApiOperation(value = "Crear un objeto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Objeto.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Usuario not found"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/{id}/objetos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearObjeto(@PathParam("id") String id, Objeto objeto) {

        Usuario u = this.um.getUsuarioById(id);
        if (u == null) return Response.status(404).build();
        else  {
            u.addObjeto(objeto.getName(),objeto.getCantidad());
            return Response.status(201).entity(objeto).build();
        }
    }
}
