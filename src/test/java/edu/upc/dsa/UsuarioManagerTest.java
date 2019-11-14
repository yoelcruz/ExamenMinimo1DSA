package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class UsuarioManagerTest {

    UsuariosManager usuarioManager = null;

    @Before
    public void setUp()  {
        usuarioManager = new UsuariosManagerImp();

        usuarioManager.addUsuario("1", "yoel", "cruz torres");
        usuarioManager.addUsuario("2", "jose", "ruiz ortega");
        usuarioManager.addUsuario("3", "manuel", "torres castillo");
        usuarioManager.addUsuario("4", "laura", "jimenez lopez");
    }

    @Test
    public void addUsuario() {
        Assert.assertEquals("ProductManager addProduct", 4, usuarioManager.size());
    }

    @Test
    public void usuariosOrdenadosAlfabeticamente() {
        List<Usuario> list = new LinkedList<Usuario>();
        list = usuarioManager.usuariosOrdenadosAlfabeticamente();
        System.out.println("Listado ordenado alfabeticamente:");
        for (Usuario usuario: list){
            String name = usuario.getNombre();
            System.out.println(name+ " ");
        }
    }
    @Test
    public void testAñadirObjetosAUnUsuario() {

        usuarioManager.addObjetoConIdUsuario("1","espada",1);
        usuarioManager.addObjetoConIdUsuario("1","cofre",3);
        usuarioManager.addObjetoConIdUsuario("2","escudo",5);
        usuarioManager.addObjetoConIdUsuario("3","cofre",6);
        usuarioManager.addObjetoConIdUsuario("4","monedas",100);

        Assert.assertEquals("Objetos usuario id1", 4, usuarioManager.getUsuarioById("1").numeroObjetos());
        Assert.assertEquals("Objetos usuario id2", 5, usuarioManager.getUsuarioById("2").numeroObjetos());
        Assert.assertEquals("Objetos usuario id3", 6, usuarioManager.getUsuarioById("3").numeroObjetos());
        Assert.assertEquals("Objetos usuario id4", 100, usuarioManager.getUsuarioById("4").numeroObjetos());

    }




}
