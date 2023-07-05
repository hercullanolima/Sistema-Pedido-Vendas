package db;

import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosBD {
    private List<Usuario> usuarioList = new ArrayList<>();

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void AddNovoUsuario(Usuario usuario){
        int id = usuarioList.size() +1;
        usuario.setId(id);
        usuarioList.add(usuario);
    }
}
