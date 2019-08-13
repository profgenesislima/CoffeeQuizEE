package edu.genesislima.coffeequiz.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.genesislima.coffeequiz.model.Usuario;

public class LoginBean implements Serializable{

	
	private Usuario usuario = new Usuario();
//	private UsuarioDao dao = new UsuarioDao();
	
	@Inject
	UsuarioLogadoBean usuarioLogado;
	
	@Inject
	MenuBean menu;

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		
		//Usuario usuarioEncontrado = this.dao.buscaPeloLogin(usuario.getLogin());
		
//		if(usuarioEncontrado!= null && possuiMesmaSenha(usuarioEncontrado)) {
//			usuarioLogado.logar(usuarioEncontrado);
//			return menu.paginaLivros();
//		}
		
		criaMensagem("Usuário não encontrado!");
		limparForm();
		
		return "";
	}
	
	public String efetuaLogout() {
		this.usuarioLogado.deslogar();
		return this.menu.paginaLogin();
	}

	
	private void limparForm() {
		this.usuario = new Usuario();
	}

	private void criaMensagem(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, ""));
	}

	private boolean possuiMesmaSenha(Usuario usuarioEncontrado) {
		return usuarioEncontrado.getSenha().equals(usuario.getSenha());
	}
}
