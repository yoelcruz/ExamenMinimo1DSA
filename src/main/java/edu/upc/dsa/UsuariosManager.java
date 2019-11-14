package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface UsuariosManager {
    public void addUsuario(String id, String nombre, String apellidos);
    public int size();
    public List<Usuario> usuariosOrdenadosAlfabeticamente();
    public Usuario updateUsuarioById(Usuario usuario);
    public Usuario addUsuario (Usuario usuario);
    public Usuario getUsuarioById(String id);
}
