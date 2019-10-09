package com.luizjacomn.hib_envers.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.luizjacomn.hib_envers.dao.AlunoDAO;
import com.luizjacomn.hib_envers.model.Aluno;

@ManagedBean
@SessionScoped
public class AlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Aluno aluno = new Aluno();

	private List<Aluno> lista = new ArrayList<Aluno>();

	public void buscar() {
		AlunoDAO alunoDAO = new AlunoDAO();
		aluno = alunoDAO.getAlunoByMatricula(aluno.getMatricula());
	}

	public String salvar() {
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.adicionarAluno(aluno);
		return "sucesso";
	}

	public String editar() {
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.atualizarAluno(aluno);
		return "sucesso_edi";
	}

	public String remover() {
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.apagarAluno(aluno.getMatricula());
		return "sucesso_del";
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getLista() {
		return lista;
	}

	public void setLista(List<Aluno> lista) {
		this.lista = lista;
	}
}