package com.luizjacomn.hib_envers.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.luizjacomn.hib_envers.model.Aluno;
import com.luizjacomn.hib_envers.util.HibernateUtil;

public class AlunoDAO {

	private static SessionFactory factory = HibernateUtil.getSessionFactory();
	
	private static final Logger LOGGER = Logger.getLogger(AlunoDAO.class.getCanonicalName()); 

	public Integer adicionarAluno(Aluno aluno) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer codAluno = null;

		try {
			tx = session.beginTransaction();
			codAluno = (Integer) session.save(aluno);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.severe(e.getMessage());
		} finally {
			session.close();
		}
		return codAluno;
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> listarAlunos() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Aluno> alunos = null;

		try {
			tx = session.beginTransaction();
			alunos = session.createCriteria(Aluno.class).list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.severe(e.getMessage());
		} finally {
			session.close();
		}
		return alunos;
	}

	public void atualizarAluno(Aluno aluno) {
		Session session = factory.openSession();
		Transaction tx = null;
		Aluno alunoUpd = new Aluno();

		try {
			tx = session.beginTransaction();
			alunoUpd.setCidade(aluno.getCidade());
			alunoUpd.setCodigo(aluno.getCodigo());
			alunoUpd.setCpf(aluno.getCpf());
			alunoUpd.setEmail(aluno.getEmail());
			alunoUpd.setMatricula(aluno.getMatricula());
			alunoUpd.setNome(aluno.getNome());
			session.update(alunoUpd);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.severe(e.getMessage());
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void apagarAluno(Integer matricula) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Aluno.class);
			criteria.add(Restrictions.eq("matricula", matricula));
			List<Aluno> results = criteria.list();
			session.delete((Aluno) results.get(0));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.severe(e.getMessage());
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Aluno getAlunoByMatricula(int matricula) {
		Session session = factory.openSession();
		Transaction tx = null;
		Aluno aluno = null;

		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Aluno.class);
			criteria.add(Restrictions.eq("matricula", matricula));
			List<Aluno> results = criteria.list();
			aluno = results.get(0);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.severe(e.getMessage());
		} finally {
			session.close();
		}
		return aluno;
	}
}