package de.htwg.se.moerakikemu.persistence.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.persistence.IFieldDAO;

public class FieldHibernateDAO implements IFieldDAO {

	public void saveField(IField field) {
		Transaction tx = null;
		Session session = null;

		try {
			session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();

			PersistentField pfield = copyField(field);

			session.saveOrUpdate(pfield);
			for (PersistentSpot pspot : pfield.getSpots()) {
				session.saveOrUpdate(pspot);
			}
			tx.commit();

		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(ex.getMessage());

		}
	}

	private PersistentField copyField(IField field) {
		if (field == null) {
			return null;
		}

		String fieldId = field.getID();
		PersistentField pfield;
		if (containsFieldByID(fieldId)) {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			pfield = (PersistentField) session.get(PersistentField.class, fieldId);

		}

		return null;
	}

	public void deleteFieldByID(String id) {
		// TODO Auto-generated method stub

	}

	public boolean containsFieldByID(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public IField getFieldByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void generateFields(int number, int edgeLength) {
		// TODO Auto-generated method stub

	}

	public List<IField> getAllFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
