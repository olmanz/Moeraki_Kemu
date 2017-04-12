package de.htwg.se.moerakikemu.persistence.couchdb;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.modellayer.ISpot;
import de.htwg.se.moerakikemu.modellayer.modellayerimpl.Field;
import de.htwg.se.moerakikemu.persistence.IFieldDAO;
import de.htwg.se.moerakikemu.persistence.hibernate.HibernateUtil;
import de.htwg.se.moerakikemu.persistence.hibernate.PersistentField;
import de.htwg.se.moerakikemu.persistence.hibernate.PersistentSpot;

import org.apache.log4j.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.Revision;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;
import org.ektorp.ViewResult.Row;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.hibernate.Session;

public class FieldCouchdbDAO implements IFieldDAO {
	private CouchDbConnector db = null;
	private Logger logger = Logger.getLogger("de.htwg.se.moerakikemu.persistence.couchdb");
	
	public FieldCouchdbDAO() {
		HttpClient client = null;
		
		try {
			client = new StdHttpClient.Builder().url("http://lenny2.in.htwg-konstanz.de:5984").build();
		} catch (MalformedURLException e) {
			logger.error("Malformed URL", e);
		}
		
		CouchDbInstance dbInstance = new StdCouchDbInstance(client);
		db = dbInstance.createConnector("moerakikemu_db", true);
		db.createDatabaseIfNotExists();
	}
	
	public IField copyField(PersistentField pField) {
		if (pField == null) {
			return null;
		}
		IField field = new Field(pField.getEdgeLength());
		field.setId(pField.getId());
		field.setName(pField.getName());

		for (PersistentSpot spotBase : pField.getSpots()) {
			int column = spotBase.getColumn();
			int row = spotBase.getRow();
			String occupier = spotBase.getOccupiedByPlayer();

			ISpot spot = field.getISpot(column, row);
			spot.occupy(occupier);
		}
		return field;
	}
	
	public PersistentField copyField(IField field) {
		String fieldId = field.getId();
		PersistentField pfield;
		if (containsFieldByID(fieldId)) {
			pfield = (PersistentField) db.get(PersistentField.class, fieldId);

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

	public void saveField(IField field) {
		if (containsFieldByID(field.getId())) {
			db.update(copyField(field));
		} else {
			db.create(field.getId(), copyField(field));
		}
	}

	public void deleteFieldByID(String id) {
		db.delete(copyField(getFieldByID(id)));
	}

	public boolean containsFieldByID(String id) {
		if (getFieldByID(id) == null) {
			return false;
		}
		
		return true;
	}

	public IField getFieldByID(String id) {
		PersistentField pField = db.find(PersistentField.class, id);
		
		if (pField == null) {
			return null;
		}
		
		return copyField(pField);
	}

	public void generateFields(int number, int edgeLength) {
		for (int i = 0; i < number; i++) {
			IField field = new Field(edgeLength);
			saveField(field);
		}
	}

	public List<IField> getAllFields() {
		List<IField> lst = new ArrayList<IField>();
		ViewQuery query = new ViewQuery().allDocs();
		ViewResult vr = db.queryView(query);
		
		for (Row r : vr.getRows()) {
			lst.add(getFieldByID(r.getId()));
		}
		
		return lst;
	}

}
