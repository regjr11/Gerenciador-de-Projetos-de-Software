package gerenciador_projetos_software.model.service;

import gerenciador_projetos_software.model.dao.UsuarioDao;
import gerenciador_projetos_software.model.entity.Usuario;

public class UsuarioService { //todo mudar para usuario service
    private UsuarioDao usuarioDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
    }

    public Boolean verificarLogin(Usuario usuario) {
        return usuarioDao.checkLogin(usuario.getEmail(),usuario.getSenha());
    }
}
