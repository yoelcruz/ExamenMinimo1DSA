package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class UsuariosManagerImp implements UsuariosManager {
    private static UsuariosManager instance;
    protected List<Usuario> usuarios;
    protected List<Objeto> objetos;
    final static Logger logger = Logger.getLogger(UsuariosManagerImp.class);

    public UsuariosManagerImp() {

        this.usuarios = new LinkedList<>();
        this.objetos = new LinkedList<>();
    }

    public static UsuariosManager getInstance() {
        if (instance==null) instance = new UsuariosManagerImp();
        return instance;
    }

    public void addUsuario(String id, String nombre, String apellidos) {
        Usuario usuario = new Usuario(id, nombre, apellidos);
        logger.info(usuario+" añadido ");
        this.usuarios.add(usuario);
    }

    public int size() {
        int ret = this.usuarios.size();
        logger.info("size " + ret);

        return ret;
    }

    public List<Usuario> usuariosOrdenadosAlfabeticamente(){

        List<Usuario> listaOrdenada = new LinkedList<Usuario>();
        listaOrdenada.addAll(this.usuarios);

        Collections.sort(listaOrdenada, new Comparator<Usuario>() {
            public int compare(Usuario u1, Usuario u2) {
                return u1.getNombre().compareTo(u2.getNombre());
            }
        });
        return listaOrdenada;
    }

    public Usuario addUsuario (Usuario usuario){
        logger.info("New Product " + usuario);
        this.usuarios.add (usuario);
        logger.info("New Product added");
        return usuario;
    }

    public Usuario getUsuarioById(String id) {
        logger.info("getUsuario("+id+")");

        for (Usuario u: this.usuarios) {
            if (u.getId().equals(id)) {
                logger.info("getProduct("+id+"): "+u);

                return u;
            }
        }
        logger.warn("not found " + id);
        return null;
    }


    public Usuario updateUsuarioById (Usuario uParameter){
        Usuario uSet =this.getUsuarioById(uParameter.getId());
        if (uParameter!=null) {
            logger.info(uParameter+" rebut!!!! ");

            uSet.setNombre(uParameter.getNombre());
            uSet.setApellidos(uParameter.getApellidos());

            logger.info(uSet+" updated ");
        }
        else {
            logger.warn("not found "+uParameter);
        }
        return uSet;
    }

    public void addObjetoConIdUsuario(String id, String nombre, int cantidad){
        logger.info("getUsuario("+id+")");
        for(Usuario usuario: this.usuarios){
            if (id ==usuario.getId()){
                logger.info("getUsuario("+id+"): "+usuario);
                usuario.addObjeto(nombre, cantidad);
            }
            else {
                logger.warn("not found " + id);
            }
        }
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

}
