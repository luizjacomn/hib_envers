package com.luizjacomn.hib_envers.util;

import org.hibernate.envers.RevisionListener;

import com.luizjacomn.hib_envers.model.AuditEntity;
import com.luizjacomn.hib_envers.sec.SessionBean;

public class AuditListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		AuditEntity revEntity = (AuditEntity) revisionEntity;  
	    revEntity.setUsuario(SessionBean.getUserName());
//	    revEntity.setIp(SessionBean.getIP());
	}
}