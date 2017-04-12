package de.htwg.se.moerakikemu.persistence.couchdb;

import java.net.MalformedURLException;
import java.util.List;

import de.htwg.se.moerakikemu.modellayer.IField;
import de.htwg.se.moerakikemu.persistence.IFieldDAO;
import org.apache.log4j.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.Revision;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

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

	public void saveField(IField field) {
		// TODO Auto-generated method stub
		
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
