package com.luizjacomn.hib_envers.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.luizjacomn.hib_envers.model.Usuario;
import com.luizjacomn.hib_envers.sec.SessionBean;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient Usuario usuario = new Usuario();

	public String validateUsernamePassword() {
		String login = "test";
		String senha = "T35T";

		if (login.equals(usuario.getUser()) && senha.equalsIgnoreCase(usuario.getPwd())) {
			HttpSession session = SessionBean.getSession();
			session.setAttribute("username", usuario.getUser());
			return "menu";
		} else {
			return "login";
		}
	}

	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		return "login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}