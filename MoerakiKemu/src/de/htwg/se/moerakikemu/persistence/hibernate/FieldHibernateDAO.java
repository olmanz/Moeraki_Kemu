package de.htwg.se.moerakikemu.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.modellayer.ISpot;
import de.htwg.se.moerakikemu.modellayer.modellayerimpl.Field;
import de.htwg.se.moerakikemu.persistence.IFieldDAO;

public class FieldHibernateDAO implements IFieldDAO {

	public void saveField(IField field) {
		if (field == null)
			return;
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
		String fieldId = field.getId();
		PersistentField pfield;
		if (containsFieldByID(fieldId)) {
			Session session = HibernateUtil.getInstance().getCurrentSession();
			pfield = (PersistentField) session.get(PersistentField.class, fieldId);

			List<PersistentSpot> spots = pfield.getSpots();
			for (PersistentSpot s : spots) {
				int col = s.getColumn();
				int row = s.getRow();
				s.setOccupiedByPlayer(field.getIsOccupiedFrom(col, row));
			}
		} else {
			pfield = new PersistentField();

			List<PersistentSpot> spots = new ArrayList<PersistentSpot>();

			for (int column = 0; column < field.getEdgeLength(); column++) {
				for (int row = 0; row < field.getEdgeLength(); row++) {
					ISpot spot = field.getISpot(column, row);

					PersistentSpot pspot = new PersistentSpot(column, row);
					pspot.setIsOccupied(spot.isOccupied());
					pspot.setOccupiedByPlayer(spot.getOccupiedByPlayer());

					pspot.setField(pfield);
					spots.add(pspot);
				}
			}
			pfield.setSpots(spots);
		}

		pfield.setId(field.getId());
		pfield.setName(field.getName());
		pfield.setEdgeLength(field.getEdgeLength());

		return pfield;
	}

	public void deleteFieldByID(String id) {
		Transaction tx = null;
		Session session = null;
		if (containsFieldByID(id)) {
			try {
				session = HibernateUtil.getInstance().getCurrentSession();
				tx = session.beginTransaction();

				PersistentField pfield = (PersistentField) session.get(PersistentField.class, id);
				for (PersistentSpot c : pfield.getSpots()) {
					session.delete(c);
				}
				session.delete(pfield);
				tx.commit();
			} catch (HibernateException ex) {
				if (tx != null)
					tx.rollback();
				throw new RuntimeException(ex.getMessage());
			}
		}
	}

	public boolean containsFieldByID(String id) {
		if (getFieldByID(id) != null)
			return true;
		return false;
	}

	public IField getFieldByID(String id) {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();

		return copyField((PersistentField) session.get(PersistentField.class, id));
	}

	private IField copyField(PersistentField pfield) {
		if (pfield == null) {
			return null;
		}
		IField field = new Field(pfield.getEdgeLength());
		field.setId(pfield.getId());
		field.setName(pfield.getName());

		for (PersistentSpot spotBase : pfield.getSpots()) {
			int column = spotBase.getColumn();
			int row = spotBase.getRow();
			String occupier = spotBase.getOccupiedByPlayer();

			ISpot spot = field.getISpot(column, row);
			spot.occupy(occupier);
		}
		return field;
	}

	public void generateFields(int number, int edgeLength) {
		for (int i = 0; i < number; i++) {
			IField field = new Field(edgeLength);
			saveField(field);
		}
	}

	public List<IField> getAllFields() {
		Session session = HibernateUtil.getInstance().getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PersistentField.class);

		@SuppressWarnings("unchecked")
		List<PersistentField> results = criteria.list();

		List<IField> fields = new ArrayList<IField>();
		for (PersistentField pfield : results) {
			IField field = copyField(pfield);
			fields.add(field);
		}
		return fields;
	}

}
