// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA.
//
// Le code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence").
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package pipeline_talend.talend_pipeline_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Talend_pipeline Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.8.8.20231002_2031-SNAPSHOT
 * @status
 */
public class Talend_pipeline implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Talend_pipeline";
	private final String projectName = "PIPELINE_TALEND";
	public Integer errorCode = null;
	private String currentComponent = "";

	private String cLabel = null;

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;

		private String currentComponent = null;
		private String cLabel = null;

		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		private TalendException(Exception e, String errorComponent, String errorComponentLabel,
				final java.util.Map<String, Object> globalMap) {
			this(e, errorComponent, globalMap);
			this.cLabel = errorComponentLabel;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Talend_pipeline.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Talend_pipeline.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tUniqRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];

		public String ID_produit;

		public String getID_produit() {
			return this.ID_produit;
		}

		public Boolean ID_produitIsNullable() {
			return true;
		}

		public Boolean ID_produitIsKey() {
			return false;
		}

		public Integer ID_produitLength() {
			return 15;
		}

		public Integer ID_produitPrecision() {
			return 0;
		}

		public String ID_produitDefault() {

			return null;

		}

		public String ID_produitComment() {

			return "";

		}

		public String ID_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_produitOriginalDbColumnName() {

			return "ID_produit";

		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public Boolean CategorieIsNullable() {
			return true;
		}

		public Boolean CategorieIsKey() {
			return false;
		}

		public Integer CategorieLength() {
			return 23;
		}

		public Integer CategoriePrecision() {
			return 0;
		}

		public String CategorieDefault() {

			return null;

		}

		public String CategorieComment() {

			return "";

		}

		public String CategoriePattern() {

			return "dd-MM-yyyy";

		}

		public String CategorieOriginalDbColumnName() {

			return "Categorie";

		}

		public String Sous_categorie;

		public String getSous_categorie() {
			return this.Sous_categorie;
		}

		public Boolean Sous_categorieIsNullable() {
			return true;
		}

		public Boolean Sous_categorieIsKey() {
			return false;
		}

		public Integer Sous_categorieLength() {
			return 20;
		}

		public Integer Sous_categoriePrecision() {
			return 0;
		}

		public String Sous_categorieDefault() {

			return null;

		}

		public String Sous_categorieComment() {

			return "";

		}

		public String Sous_categoriePattern() {

			return "dd-MM-yyyy";

		}

		public String Sous_categorieOriginalDbColumnName() {

			return "Sous_categorie";

		}

		public String Nom_du_produit;

		public String getNom_du_produit() {
			return this.Nom_du_produit;
		}

		public Boolean Nom_du_produitIsNullable() {
			return true;
		}

		public Boolean Nom_du_produitIsKey() {
			return false;
		}

		public Integer Nom_du_produitLength() {
			return 200;
		}

		public Integer Nom_du_produitPrecision() {
			return 0;
		}

		public String Nom_du_produitDefault() {

			return null;

		}

		public String Nom_du_produitComment() {

			return "";

		}

		public String Nom_du_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String Nom_du_produitOriginalDbColumnName() {

			return "Nom_du_produit";

		}

		public Double prix;

		public Double getPrix() {
			return this.prix;
		}

		public Boolean prixIsNullable() {
			return true;
		}

		public Boolean prixIsKey() {
			return false;
		}

		public Integer prixLength() {
			return 18;
		}

		public Integer prixPrecision() {
			return 0;
		}

		public String prixDefault() {

			return null;

		}

		public String prixComment() {

			return "";

		}

		public String prixPattern() {

			return "dd-MM-yyyy";

		}

		public String prixOriginalDbColumnName() {

			return "prix";

		}

		public String Remise;

		public String getRemise() {
			return this.Remise;
		}

		public Boolean RemiseIsNullable() {
			return true;
		}

		public Boolean RemiseIsKey() {
			return false;
		}

		public Integer RemiseLength() {
			return 200;
		}

		public Integer RemisePrecision() {
			return 0;
		}

		public String RemiseDefault() {

			return null;

		}

		public String RemiseComment() {

			return "";

		}

		public String RemisePattern() {

			return "dd-MM-yyyy";

		}

		public String RemiseOriginalDbColumnName() {

			return "Remise";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_produit = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_categorie = readString(dis);

					this.Nom_du_produit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.prix = null;
					} else {
						this.prix = dis.readDouble();
					}

					this.Remise = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_produit = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_categorie = readString(dis);

					this.Nom_du_produit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.prix = null;
					} else {
						this.prix = dis.readDouble();
					}

					this.Remise = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_categorie, dos);

				// String

				writeString(this.Nom_du_produit, dos);

				// Double

				if (this.prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.prix);
				}

				// String

				writeString(this.Remise, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_categorie, dos);

				// String

				writeString(this.Nom_du_produit, dos);

				// Double

				if (this.prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.prix);
				}

				// String

				writeString(this.Remise, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_produit=" + ID_produit);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Sous_categorie=" + Sous_categorie);
			sb.append(",Nom_du_produit=" + Nom_du_produit);
			sb.append(",prix=" + String.valueOf(prix));
			sb.append(",Remise=" + Remise);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String ID_commande;

		public String getID_commande() {
			return this.ID_commande;
		}

		public Boolean ID_commandeIsNullable() {
			return true;
		}

		public Boolean ID_commandeIsKey() {
			return true;
		}

		public Integer ID_commandeLength() {
			return 15;
		}

		public Integer ID_commandePrecision() {
			return 0;
		}

		public String ID_commandeDefault() {

			return null;

		}

		public String ID_commandeComment() {

			return "";

		}

		public String ID_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String ID_commandeOriginalDbColumnName() {

			return "ID_commande";

		}

		public String Date_de_commande;

		public String getDate_de_commande() {
			return this.Date_de_commande;
		}

		public Boolean Date_de_commandeIsNullable() {
			return true;
		}

		public Boolean Date_de_commandeIsKey() {
			return false;
		}

		public Integer Date_de_commandeLength() {
			return 29;
		}

		public Integer Date_de_commandePrecision() {
			return 0;
		}

		public String Date_de_commandeDefault() {

			return null;

		}

		public String Date_de_commandeComment() {

			return "";

		}

		public String Date_de_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String Date_de_commandeOriginalDbColumnName() {

			return "Date_de_commande";

		}

		public String Date_d_expedition;

		public String getDate_d_expedition() {
			return this.Date_d_expedition;
		}

		public Boolean Date_d_expeditionIsNullable() {
			return true;
		}

		public Boolean Date_d_expeditionIsKey() {
			return false;
		}

		public Integer Date_d_expeditionLength() {
			return 29;
		}

		public Integer Date_d_expeditionPrecision() {
			return 0;
		}

		public String Date_d_expeditionDefault() {

			return null;

		}

		public String Date_d_expeditionComment() {

			return "";

		}

		public String Date_d_expeditionPattern() {

			return "dd-MM-yyyy";

		}

		public String Date_d_expeditionOriginalDbColumnName() {

			return "Date_d_expedition";

		}

		public String Mode_d_expedition;

		public String getMode_d_expedition() {
			return this.Mode_d_expedition;
		}

		public Boolean Mode_d_expeditionIsNullable() {
			return true;
		}

		public Boolean Mode_d_expeditionIsKey() {
			return false;
		}

		public Integer Mode_d_expeditionLength() {
			return 12;
		}

		public Integer Mode_d_expeditionPrecision() {
			return 0;
		}

		public String Mode_d_expeditionDefault() {

			return null;

		}

		public String Mode_d_expeditionComment() {

			return "";

		}

		public String Mode_d_expeditionPattern() {

			return "dd-MM-yyyy";

		}

		public String Mode_d_expeditionOriginalDbColumnName() {

			return "Mode_d_expedition";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_commande == null) ? 0 : this.ID_commande.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row3Struct other = (row3Struct) obj;

			if (this.ID_commande == null) {
				if (other.ID_commande != null)
					return false;

			} else if (!this.ID_commande.equals(other.ID_commande))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.ID_commande = this.ID_commande;
			other.Date_de_commande = this.Date_de_commande;
			other.Date_d_expedition = this.Date_d_expedition;
			other.Mode_d_expedition = this.Mode_d_expedition;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.ID_commande = this.ID_commande;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_commande = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Date_d_expedition = readString(dis);

					this.Mode_d_expedition = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_commande = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Date_d_expedition = readString(dis);

					this.Mode_d_expedition = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Date_d_expedition, dos);

				// String

				writeString(this.Mode_d_expedition, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Date_d_expedition, dos);

				// String

				writeString(this.Mode_d_expedition, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_commande=" + ID_commande);
			sb.append(",Date_de_commande=" + Date_de_commande);
			sb.append(",Date_d_expedition=" + Date_d_expedition);
			sb.append(",Mode_d_expedition=" + Mode_d_expedition);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_commande, other.ID_commande);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String ID_client;

		public String getID_client() {
			return this.ID_client;
		}

		public Boolean ID_clientIsNullable() {
			return true;
		}

		public Boolean ID_clientIsKey() {
			return true;
		}

		public Integer ID_clientLength() {
			return 8;
		}

		public Integer ID_clientPrecision() {
			return 0;
		}

		public String ID_clientDefault() {

			return null;

		}

		public String ID_clientComment() {

			return "";

		}

		public String ID_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_clientOriginalDbColumnName() {

			return "ID_client";

		}

		public String Nom_du_client;

		public String getNom_du_client() {
			return this.Nom_du_client;
		}

		public Boolean Nom_du_clientIsNullable() {
			return true;
		}

		public Boolean Nom_du_clientIsKey() {
			return false;
		}

		public Integer Nom_du_clientLength() {
			return 200;
		}

		public Integer Nom_du_clientPrecision() {
			return 0;
		}

		public String Nom_du_clientDefault() {

			return null;

		}

		public String Nom_du_clientComment() {

			return "";

		}

		public String Nom_du_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String Nom_du_clientOriginalDbColumnName() {

			return "Nom_du_client";

		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public Boolean RegionIsNullable() {
			return true;
		}

		public Boolean RegionIsKey() {
			return false;
		}

		public Integer RegionLength() {
			return 35;
		}

		public Integer RegionPrecision() {
			return 0;
		}

		public String RegionDefault() {

			return null;

		}

		public String RegionComment() {

			return "";

		}

		public String RegionPattern() {

			return "dd-MM-yyyy";

		}

		public String RegionOriginalDbColumnName() {

			return "Region";

		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public Boolean PaysIsNullable() {
			return true;
		}

		public Boolean PaysIsKey() {
			return false;
		}

		public Integer PaysLength() {
			return 11;
		}

		public Integer PaysPrecision() {
			return 0;
		}

		public String PaysDefault() {

			return null;

		}

		public String PaysComment() {

			return "";

		}

		public String PaysPattern() {

			return "dd-MM-yyyy";

		}

		public String PaysOriginalDbColumnName() {

			return "Pays";

		}

		public String Zone_geographique;

		public String getZone_geographique() {
			return this.Zone_geographique;
		}

		public Boolean Zone_geographiqueIsNullable() {
			return true;
		}

		public Boolean Zone_geographiqueIsKey() {
			return false;
		}

		public Integer Zone_geographiqueLength() {
			return 6;
		}

		public Integer Zone_geographiquePrecision() {
			return 0;
		}

		public String Zone_geographiqueDefault() {

			return null;

		}

		public String Zone_geographiqueComment() {

			return "";

		}

		public String Zone_geographiquePattern() {

			return "dd-MM-yyyy";

		}

		public String Zone_geographiqueOriginalDbColumnName() {

			return "Zone_geographique";

		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public Boolean VilleIsNullable() {
			return true;
		}

		public Boolean VilleIsKey() {
			return false;
		}

		public Integer VilleLength() {
			return 200;
		}

		public Integer VillePrecision() {
			return 0;
		}

		public String VilleDefault() {

			return null;

		}

		public String VilleComment() {

			return "";

		}

		public String VillePattern() {

			return "dd-MM-yyyy";

		}

		public String VilleOriginalDbColumnName() {

			return "Ville";

		}

		public String Segment;

		public String getSegment() {
			return this.Segment;
		}

		public Boolean SegmentIsNullable() {
			return true;
		}

		public Boolean SegmentIsKey() {
			return false;
		}

		public Integer SegmentLength() {
			return 28;
		}

		public Integer SegmentPrecision() {
			return 0;
		}

		public String SegmentDefault() {

			return null;

		}

		public String SegmentComment() {

			return "";

		}

		public String SegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String SegmentOriginalDbColumnName() {

			return "Segment";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_client == null) ? 0 : this.ID_client.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row4Struct other = (row4Struct) obj;

			if (this.ID_client == null) {
				if (other.ID_client != null)
					return false;

			} else if (!this.ID_client.equals(other.ID_client))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.ID_client = this.ID_client;
			other.Nom_du_client = this.Nom_du_client;
			other.Region = this.Region;
			other.Pays = this.Pays;
			other.Zone_geographique = this.Zone_geographique;
			other.Ville = this.Ville;
			other.Segment = this.Segment;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.ID_client = this.ID_client;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_client = readString(dis);

					this.Nom_du_client = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Zone_geographique = readString(dis);

					this.Ville = readString(dis);

					this.Segment = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_client = readString(dis);

					this.Nom_du_client = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Zone_geographique = readString(dis);

					this.Ville = readString(dis);

					this.Segment = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.Nom_du_client, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Segment, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.Nom_du_client, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Segment, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_client=" + ID_client);
			sb.append(",Nom_du_client=" + Nom_du_client);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Zone_geographique=" + Zone_geographique);
			sb.append(",Ville=" + Ville);
			sb.append(",Segment=" + Segment);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_client, other.ID_client);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class dim_clientStruct implements routines.system.IPersistableRow<dim_clientStruct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];

		public String ID_client;

		public String getID_client() {
			return this.ID_client;
		}

		public Boolean ID_clientIsNullable() {
			return true;
		}

		public Boolean ID_clientIsKey() {
			return false;
		}

		public Integer ID_clientLength() {
			return 8;
		}

		public Integer ID_clientPrecision() {
			return 0;
		}

		public String ID_clientDefault() {

			return null;

		}

		public String ID_clientComment() {

			return "";

		}

		public String ID_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_clientOriginalDbColumnName() {

			return "ID_client";

		}

		public String Nom_du_client;

		public String getNom_du_client() {
			return this.Nom_du_client;
		}

		public Boolean Nom_du_clientIsNullable() {
			return true;
		}

		public Boolean Nom_du_clientIsKey() {
			return false;
		}

		public Integer Nom_du_clientLength() {
			return 200;
		}

		public Integer Nom_du_clientPrecision() {
			return 0;
		}

		public String Nom_du_clientDefault() {

			return null;

		}

		public String Nom_du_clientComment() {

			return "";

		}

		public String Nom_du_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String Nom_du_clientOriginalDbColumnName() {

			return "Nom_du_client";

		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public Boolean RegionIsNullable() {
			return true;
		}

		public Boolean RegionIsKey() {
			return false;
		}

		public Integer RegionLength() {
			return 35;
		}

		public Integer RegionPrecision() {
			return 0;
		}

		public String RegionDefault() {

			return null;

		}

		public String RegionComment() {

			return "";

		}

		public String RegionPattern() {

			return "dd-MM-yyyy";

		}

		public String RegionOriginalDbColumnName() {

			return "Region";

		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public Boolean PaysIsNullable() {
			return true;
		}

		public Boolean PaysIsKey() {
			return false;
		}

		public Integer PaysLength() {
			return 11;
		}

		public Integer PaysPrecision() {
			return 0;
		}

		public String PaysDefault() {

			return null;

		}

		public String PaysComment() {

			return "";

		}

		public String PaysPattern() {

			return "dd-MM-yyyy";

		}

		public String PaysOriginalDbColumnName() {

			return "Pays";

		}

		public String Zone_geographique;

		public String getZone_geographique() {
			return this.Zone_geographique;
		}

		public Boolean Zone_geographiqueIsNullable() {
			return true;
		}

		public Boolean Zone_geographiqueIsKey() {
			return false;
		}

		public Integer Zone_geographiqueLength() {
			return 6;
		}

		public Integer Zone_geographiquePrecision() {
			return 0;
		}

		public String Zone_geographiqueDefault() {

			return null;

		}

		public String Zone_geographiqueComment() {

			return "";

		}

		public String Zone_geographiquePattern() {

			return "dd-MM-yyyy";

		}

		public String Zone_geographiqueOriginalDbColumnName() {

			return "Zone_geographique";

		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public Boolean VilleIsNullable() {
			return true;
		}

		public Boolean VilleIsKey() {
			return false;
		}

		public Integer VilleLength() {
			return 200;
		}

		public Integer VillePrecision() {
			return 0;
		}

		public String VilleDefault() {

			return null;

		}

		public String VilleComment() {

			return "";

		}

		public String VillePattern() {

			return "dd-MM-yyyy";

		}

		public String VilleOriginalDbColumnName() {

			return "Ville";

		}

		public String Segment;

		public String getSegment() {
			return this.Segment;
		}

		public Boolean SegmentIsNullable() {
			return true;
		}

		public Boolean SegmentIsKey() {
			return false;
		}

		public Integer SegmentLength() {
			return 28;
		}

		public Integer SegmentPrecision() {
			return 0;
		}

		public String SegmentDefault() {

			return null;

		}

		public String SegmentComment() {

			return "";

		}

		public String SegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String SegmentOriginalDbColumnName() {

			return "Segment";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_client = readString(dis);

					this.Nom_du_client = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Zone_geographique = readString(dis);

					this.Ville = readString(dis);

					this.Segment = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_client = readString(dis);

					this.Nom_du_client = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Zone_geographique = readString(dis);

					this.Ville = readString(dis);

					this.Segment = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.Nom_du_client, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Segment, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.Nom_du_client, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Segment, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_client=" + ID_client);
			sb.append(",Nom_du_client=" + Nom_du_client);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Zone_geographique=" + Zone_geographique);
			sb.append(",Ville=" + Ville);
			sb.append(",Segment=" + Segment);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(dim_clientStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class dim_commandeStruct implements routines.system.IPersistableRow<dim_commandeStruct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];

		public String ID_commande;

		public String getID_commande() {
			return this.ID_commande;
		}

		public Boolean ID_commandeIsNullable() {
			return true;
		}

		public Boolean ID_commandeIsKey() {
			return false;
		}

		public Integer ID_commandeLength() {
			return 15;
		}

		public Integer ID_commandePrecision() {
			return 0;
		}

		public String ID_commandeDefault() {

			return null;

		}

		public String ID_commandeComment() {

			return "";

		}

		public String ID_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String ID_commandeOriginalDbColumnName() {

			return "ID_commande";

		}

		public String Date_de_commande;

		public String getDate_de_commande() {
			return this.Date_de_commande;
		}

		public Boolean Date_de_commandeIsNullable() {
			return true;
		}

		public Boolean Date_de_commandeIsKey() {
			return false;
		}

		public Integer Date_de_commandeLength() {
			return 29;
		}

		public Integer Date_de_commandePrecision() {
			return 0;
		}

		public String Date_de_commandeDefault() {

			return null;

		}

		public String Date_de_commandeComment() {

			return "";

		}

		public String Date_de_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String Date_de_commandeOriginalDbColumnName() {

			return "Date_de_commande";

		}

		public String Date_d_expedition;

		public String getDate_d_expedition() {
			return this.Date_d_expedition;
		}

		public Boolean Date_d_expeditionIsNullable() {
			return true;
		}

		public Boolean Date_d_expeditionIsKey() {
			return false;
		}

		public Integer Date_d_expeditionLength() {
			return 29;
		}

		public Integer Date_d_expeditionPrecision() {
			return 0;
		}

		public String Date_d_expeditionDefault() {

			return null;

		}

		public String Date_d_expeditionComment() {

			return "";

		}

		public String Date_d_expeditionPattern() {

			return "dd-MM-yyyy";

		}

		public String Date_d_expeditionOriginalDbColumnName() {

			return "Date_d_expedition";

		}

		public String Mode_d_expedition;

		public String getMode_d_expedition() {
			return this.Mode_d_expedition;
		}

		public Boolean Mode_d_expeditionIsNullable() {
			return true;
		}

		public Boolean Mode_d_expeditionIsKey() {
			return false;
		}

		public Integer Mode_d_expeditionLength() {
			return 12;
		}

		public Integer Mode_d_expeditionPrecision() {
			return 0;
		}

		public String Mode_d_expeditionDefault() {

			return null;

		}

		public String Mode_d_expeditionComment() {

			return "";

		}

		public String Mode_d_expeditionPattern() {

			return "dd-MM-yyyy";

		}

		public String Mode_d_expeditionOriginalDbColumnName() {

			return "Mode_d_expedition";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_commande = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Date_d_expedition = readString(dis);

					this.Mode_d_expedition = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_commande = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Date_d_expedition = readString(dis);

					this.Mode_d_expedition = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Date_d_expedition, dos);

				// String

				writeString(this.Mode_d_expedition, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Date_d_expedition, dos);

				// String

				writeString(this.Mode_d_expedition, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_commande=" + ID_commande);
			sb.append(",Date_de_commande=" + Date_de_commande);
			sb.append(",Date_d_expedition=" + Date_d_expedition);
			sb.append(",Mode_d_expedition=" + Mode_d_expedition);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(dim_commandeStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class dim_produitStruct implements routines.system.IPersistableRow<dim_produitStruct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];

		public String ID_produit;

		public String getID_produit() {
			return this.ID_produit;
		}

		public Boolean ID_produitIsNullable() {
			return true;
		}

		public Boolean ID_produitIsKey() {
			return false;
		}

		public Integer ID_produitLength() {
			return 15;
		}

		public Integer ID_produitPrecision() {
			return 0;
		}

		public String ID_produitDefault() {

			return null;

		}

		public String ID_produitComment() {

			return "";

		}

		public String ID_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_produitOriginalDbColumnName() {

			return "ID_produit";

		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public Boolean CategorieIsNullable() {
			return true;
		}

		public Boolean CategorieIsKey() {
			return false;
		}

		public Integer CategorieLength() {
			return 23;
		}

		public Integer CategoriePrecision() {
			return 0;
		}

		public String CategorieDefault() {

			return null;

		}

		public String CategorieComment() {

			return "";

		}

		public String CategoriePattern() {

			return "dd-MM-yyyy";

		}

		public String CategorieOriginalDbColumnName() {

			return "Categorie";

		}

		public String Sous_categorie;

		public String getSous_categorie() {
			return this.Sous_categorie;
		}

		public Boolean Sous_categorieIsNullable() {
			return true;
		}

		public Boolean Sous_categorieIsKey() {
			return false;
		}

		public Integer Sous_categorieLength() {
			return 20;
		}

		public Integer Sous_categoriePrecision() {
			return 0;
		}

		public String Sous_categorieDefault() {

			return null;

		}

		public String Sous_categorieComment() {

			return "";

		}

		public String Sous_categoriePattern() {

			return "dd-MM-yyyy";

		}

		public String Sous_categorieOriginalDbColumnName() {

			return "Sous_categorie";

		}

		public String Nom_du_produit;

		public String getNom_du_produit() {
			return this.Nom_du_produit;
		}

		public Boolean Nom_du_produitIsNullable() {
			return true;
		}

		public Boolean Nom_du_produitIsKey() {
			return false;
		}

		public Integer Nom_du_produitLength() {
			return 200;
		}

		public Integer Nom_du_produitPrecision() {
			return 0;
		}

		public String Nom_du_produitDefault() {

			return null;

		}

		public String Nom_du_produitComment() {

			return "";

		}

		public String Nom_du_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String Nom_du_produitOriginalDbColumnName() {

			return "Nom_du_produit";

		}

		public Double prix;

		public Double getPrix() {
			return this.prix;
		}

		public Boolean prixIsNullable() {
			return true;
		}

		public Boolean prixIsKey() {
			return false;
		}

		public Integer prixLength() {
			return 18;
		}

		public Integer prixPrecision() {
			return 0;
		}

		public String prixDefault() {

			return null;

		}

		public String prixComment() {

			return "";

		}

		public String prixPattern() {

			return "dd-MM-yyyy";

		}

		public String prixOriginalDbColumnName() {

			return "prix";

		}

		public String Remise;

		public String getRemise() {
			return this.Remise;
		}

		public Boolean RemiseIsNullable() {
			return true;
		}

		public Boolean RemiseIsKey() {
			return false;
		}

		public Integer RemiseLength() {
			return 200;
		}

		public Integer RemisePrecision() {
			return 0;
		}

		public String RemiseDefault() {

			return null;

		}

		public String RemiseComment() {

			return "";

		}

		public String RemisePattern() {

			return "dd-MM-yyyy";

		}

		public String RemiseOriginalDbColumnName() {

			return "Remise";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_produit = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_categorie = readString(dis);

					this.Nom_du_produit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.prix = null;
					} else {
						this.prix = dis.readDouble();
					}

					this.Remise = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_produit = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_categorie = readString(dis);

					this.Nom_du_produit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.prix = null;
					} else {
						this.prix = dis.readDouble();
					}

					this.Remise = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_categorie, dos);

				// String

				writeString(this.Nom_du_produit, dos);

				// Double

				if (this.prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.prix);
				}

				// String

				writeString(this.Remise, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_categorie, dos);

				// String

				writeString(this.Nom_du_produit, dos);

				// Double

				if (this.prix == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.prix);
				}

				// String

				writeString(this.Remise, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_produit=" + ID_produit);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Sous_categorie=" + Sous_categorie);
			sb.append(",Nom_du_produit=" + Nom_du_produit);
			sb.append(",prix=" + String.valueOf(prix));
			sb.append(",Remise=" + Remise);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(dim_produitStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class table_faitStruct implements routines.system.IPersistableRow<table_faitStruct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ID_ligne;

		public Integer getID_ligne() {
			return this.ID_ligne;
		}

		public Boolean ID_ligneIsNullable() {
			return true;
		}

		public Boolean ID_ligneIsKey() {
			return true;
		}

		public Integer ID_ligneLength() {
			return 2;
		}

		public Integer ID_lignePrecision() {
			return 0;
		}

		public String ID_ligneDefault() {

			return null;

		}

		public String ID_ligneComment() {

			return "";

		}

		public String ID_lignePattern() {

			return "dd-MM-yyyy";

		}

		public String ID_ligneOriginalDbColumnName() {

			return "ID_ligne";

		}

		public String ID_commande;

		public String getID_commande() {
			return this.ID_commande;
		}

		public Boolean ID_commandeIsNullable() {
			return true;
		}

		public Boolean ID_commandeIsKey() {
			return false;
		}

		public Integer ID_commandeLength() {
			return 15;
		}

		public Integer ID_commandePrecision() {
			return 0;
		}

		public String ID_commandeDefault() {

			return null;

		}

		public String ID_commandeComment() {

			return "";

		}

		public String ID_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String ID_commandeOriginalDbColumnName() {

			return "ID_commande";

		}

		public String ID_client;

		public String getID_client() {
			return this.ID_client;
		}

		public Boolean ID_clientIsNullable() {
			return true;
		}

		public Boolean ID_clientIsKey() {
			return false;
		}

		public Integer ID_clientLength() {
			return 8;
		}

		public Integer ID_clientPrecision() {
			return 0;
		}

		public String ID_clientDefault() {

			return null;

		}

		public String ID_clientComment() {

			return "";

		}

		public String ID_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_clientOriginalDbColumnName() {

			return "ID_client";

		}

		public String ID_produit;

		public String getID_produit() {
			return this.ID_produit;
		}

		public Boolean ID_produitIsNullable() {
			return true;
		}

		public Boolean ID_produitIsKey() {
			return false;
		}

		public Integer ID_produitLength() {
			return 15;
		}

		public Integer ID_produitPrecision() {
			return 0;
		}

		public String ID_produitDefault() {

			return null;

		}

		public String ID_produitComment() {

			return "";

		}

		public String ID_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_produitOriginalDbColumnName() {

			return "ID_produit";

		}

		public String Montant_des_ventes;

		public String getMontant_des_ventes() {
			return this.Montant_des_ventes;
		}

		public Boolean Montant_des_ventesIsNullable() {
			return true;
		}

		public Boolean Montant_des_ventesIsKey() {
			return false;
		}

		public Integer Montant_des_ventesLength() {
			return 18;
		}

		public Integer Montant_des_ventesPrecision() {
			return 0;
		}

		public String Montant_des_ventesDefault() {

			return null;

		}

		public String Montant_des_ventesComment() {

			return "";

		}

		public String Montant_des_ventesPattern() {

			return "dd-MM-yyyy";

		}

		public String Montant_des_ventesOriginalDbColumnName() {

			return "Montant_des_ventes";

		}

		public String Profit;

		public String getProfit() {
			return this.Profit;
		}

		public Boolean ProfitIsNullable() {
			return true;
		}

		public Boolean ProfitIsKey() {
			return false;
		}

		public Integer ProfitLength() {
			return 200;
		}

		public Integer ProfitPrecision() {
			return 0;
		}

		public String ProfitDefault() {

			return null;

		}

		public String ProfitComment() {

			return "";

		}

		public String ProfitPattern() {

			return "dd-MM-yyyy";

		}

		public String ProfitOriginalDbColumnName() {

			return "Profit";

		}

		public Integer Quantite;

		public Integer getQuantite() {
			return this.Quantite;
		}

		public Boolean QuantiteIsNullable() {
			return true;
		}

		public Boolean QuantiteIsKey() {
			return false;
		}

		public Integer QuantiteLength() {
			return 1;
		}

		public Integer QuantitePrecision() {
			return 0;
		}

		public String QuantiteDefault() {

			return null;

		}

		public String QuantiteComment() {

			return "";

		}

		public String QuantitePattern() {

			return "dd-MM-yyyy";

		}

		public String QuantiteOriginalDbColumnName() {

			return "Quantite";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ID_ligne == null) ? 0 : this.ID_ligne.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final table_faitStruct other = (table_faitStruct) obj;

			if (this.ID_ligne == null) {
				if (other.ID_ligne != null)
					return false;

			} else if (!this.ID_ligne.equals(other.ID_ligne))

				return false;

			return true;
		}

		public void copyDataTo(table_faitStruct other) {

			other.ID_ligne = this.ID_ligne;
			other.ID_commande = this.ID_commande;
			other.ID_client = this.ID_client;
			other.ID_produit = this.ID_produit;
			other.Montant_des_ventes = this.Montant_des_ventes;
			other.Profit = this.Profit;
			other.Quantite = this.Quantite;

		}

		public void copyKeysDataTo(table_faitStruct other) {

			other.ID_ligne = this.ID_ligne;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_ligne = readInteger(dis);

					this.ID_commande = readString(dis);

					this.ID_client = readString(dis);

					this.ID_produit = readString(dis);

					this.Montant_des_ventes = readString(dis);

					this.Profit = readString(dis);

					this.Quantite = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_ligne = readInteger(dis);

					this.ID_commande = readString(dis);

					this.ID_client = readString(dis);

					this.ID_produit = readString(dis);

					this.Montant_des_ventes = readString(dis);

					this.Profit = readString(dis);

					this.Quantite = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_ligne, dos);

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Montant_des_ventes, dos);

				// String

				writeString(this.Profit, dos);

				// Integer

				writeInteger(this.Quantite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_ligne, dos);

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Montant_des_ventes, dos);

				// String

				writeString(this.Profit, dos);

				// Integer

				writeInteger(this.Quantite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_ligne=" + String.valueOf(ID_ligne));
			sb.append(",ID_commande=" + ID_commande);
			sb.append(",ID_client=" + ID_client);
			sb.append(",ID_produit=" + ID_produit);
			sb.append(",Montant_des_ventes=" + Montant_des_ventes);
			sb.append(",Profit=" + Profit);
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(table_faitStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ID_ligne, other.ID_ligne);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];

		public Integer ID_ligne;

		public Integer getID_ligne() {
			return this.ID_ligne;
		}

		public Boolean ID_ligneIsNullable() {
			return true;
		}

		public Boolean ID_ligneIsKey() {
			return false;
		}

		public Integer ID_ligneLength() {
			return 2;
		}

		public Integer ID_lignePrecision() {
			return 0;
		}

		public String ID_ligneDefault() {

			return null;

		}

		public String ID_ligneComment() {

			return "";

		}

		public String ID_lignePattern() {

			return "dd-MM-yyyy";

		}

		public String ID_ligneOriginalDbColumnName() {

			return "ID_ligne";

		}

		public String ID_commande;

		public String getID_commande() {
			return this.ID_commande;
		}

		public Boolean ID_commandeIsNullable() {
			return true;
		}

		public Boolean ID_commandeIsKey() {
			return false;
		}

		public Integer ID_commandeLength() {
			return 15;
		}

		public Integer ID_commandePrecision() {
			return 0;
		}

		public String ID_commandeDefault() {

			return null;

		}

		public String ID_commandeComment() {

			return "";

		}

		public String ID_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String ID_commandeOriginalDbColumnName() {

			return "ID_commande";

		}

		public String Date_de_commande;

		public String getDate_de_commande() {
			return this.Date_de_commande;
		}

		public Boolean Date_de_commandeIsNullable() {
			return true;
		}

		public Boolean Date_de_commandeIsKey() {
			return false;
		}

		public Integer Date_de_commandeLength() {
			return 29;
		}

		public Integer Date_de_commandePrecision() {
			return 0;
		}

		public String Date_de_commandeDefault() {

			return null;

		}

		public String Date_de_commandeComment() {

			return "";

		}

		public String Date_de_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String Date_de_commandeOriginalDbColumnName() {

			return "Date_de_commande";

		}

		public String Date_d_expedition;

		public String getDate_d_expedition() {
			return this.Date_d_expedition;
		}

		public Boolean Date_d_expeditionIsNullable() {
			return true;
		}

		public Boolean Date_d_expeditionIsKey() {
			return false;
		}

		public Integer Date_d_expeditionLength() {
			return 29;
		}

		public Integer Date_d_expeditionPrecision() {
			return 0;
		}

		public String Date_d_expeditionDefault() {

			return null;

		}

		public String Date_d_expeditionComment() {

			return "";

		}

		public String Date_d_expeditionPattern() {

			return "dd-MM-yyyy";

		}

		public String Date_d_expeditionOriginalDbColumnName() {

			return "Date_d_expedition";

		}

		public String Mode_d_expedition;

		public String getMode_d_expedition() {
			return this.Mode_d_expedition;
		}

		public Boolean Mode_d_expeditionIsNullable() {
			return true;
		}

		public Boolean Mode_d_expeditionIsKey() {
			return false;
		}

		public Integer Mode_d_expeditionLength() {
			return 12;
		}

		public Integer Mode_d_expeditionPrecision() {
			return 0;
		}

		public String Mode_d_expeditionDefault() {

			return null;

		}

		public String Mode_d_expeditionComment() {

			return "";

		}

		public String Mode_d_expeditionPattern() {

			return "dd-MM-yyyy";

		}

		public String Mode_d_expeditionOriginalDbColumnName() {

			return "Mode_d_expedition";

		}

		public String ID_client;

		public String getID_client() {
			return this.ID_client;
		}

		public Boolean ID_clientIsNullable() {
			return true;
		}

		public Boolean ID_clientIsKey() {
			return false;
		}

		public Integer ID_clientLength() {
			return 8;
		}

		public Integer ID_clientPrecision() {
			return 0;
		}

		public String ID_clientDefault() {

			return null;

		}

		public String ID_clientComment() {

			return "";

		}

		public String ID_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_clientOriginalDbColumnName() {

			return "ID_client";

		}

		public String Nom_du_client;

		public String getNom_du_client() {
			return this.Nom_du_client;
		}

		public Boolean Nom_du_clientIsNullable() {
			return true;
		}

		public Boolean Nom_du_clientIsKey() {
			return false;
		}

		public Integer Nom_du_clientLength() {
			return 18;
		}

		public Integer Nom_du_clientPrecision() {
			return 0;
		}

		public String Nom_du_clientDefault() {

			return null;

		}

		public String Nom_du_clientComment() {

			return "";

		}

		public String Nom_du_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String Nom_du_clientOriginalDbColumnName() {

			return "Nom_du_client";

		}

		public String Segment;

		public String getSegment() {
			return this.Segment;
		}

		public Boolean SegmentIsNullable() {
			return true;
		}

		public Boolean SegmentIsKey() {
			return false;
		}

		public Integer SegmentLength() {
			return 28;
		}

		public Integer SegmentPrecision() {
			return 0;
		}

		public String SegmentDefault() {

			return null;

		}

		public String SegmentComment() {

			return "";

		}

		public String SegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String SegmentOriginalDbColumnName() {

			return "Segment";

		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public Boolean VilleIsNullable() {
			return true;
		}

		public Boolean VilleIsKey() {
			return false;
		}

		public Integer VilleLength() {
			return 24;
		}

		public Integer VillePrecision() {
			return 0;
		}

		public String VilleDefault() {

			return null;

		}

		public String VilleComment() {

			return "";

		}

		public String VillePattern() {

			return "dd-MM-yyyy";

		}

		public String VilleOriginalDbColumnName() {

			return "Ville";

		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public Boolean RegionIsNullable() {
			return true;
		}

		public Boolean RegionIsKey() {
			return false;
		}

		public Integer RegionLength() {
			return 35;
		}

		public Integer RegionPrecision() {
			return 0;
		}

		public String RegionDefault() {

			return null;

		}

		public String RegionComment() {

			return "";

		}

		public String RegionPattern() {

			return "dd-MM-yyyy";

		}

		public String RegionOriginalDbColumnName() {

			return "Region";

		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public Boolean PaysIsNullable() {
			return true;
		}

		public Boolean PaysIsKey() {
			return false;
		}

		public Integer PaysLength() {
			return 11;
		}

		public Integer PaysPrecision() {
			return 0;
		}

		public String PaysDefault() {

			return null;

		}

		public String PaysComment() {

			return "";

		}

		public String PaysPattern() {

			return "dd-MM-yyyy";

		}

		public String PaysOriginalDbColumnName() {

			return "Pays";

		}

		public String Zone_geographique;

		public String getZone_geographique() {
			return this.Zone_geographique;
		}

		public Boolean Zone_geographiqueIsNullable() {
			return true;
		}

		public Boolean Zone_geographiqueIsKey() {
			return false;
		}

		public Integer Zone_geographiqueLength() {
			return 6;
		}

		public Integer Zone_geographiquePrecision() {
			return 0;
		}

		public String Zone_geographiqueDefault() {

			return null;

		}

		public String Zone_geographiqueComment() {

			return "";

		}

		public String Zone_geographiquePattern() {

			return "dd-MM-yyyy";

		}

		public String Zone_geographiqueOriginalDbColumnName() {

			return "Zone_geographique";

		}

		public String ID_produit;

		public String getID_produit() {
			return this.ID_produit;
		}

		public Boolean ID_produitIsNullable() {
			return true;
		}

		public Boolean ID_produitIsKey() {
			return false;
		}

		public Integer ID_produitLength() {
			return 15;
		}

		public Integer ID_produitPrecision() {
			return 0;
		}

		public String ID_produitDefault() {

			return null;

		}

		public String ID_produitComment() {

			return "";

		}

		public String ID_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_produitOriginalDbColumnName() {

			return "ID_produit";

		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public Boolean CategorieIsNullable() {
			return true;
		}

		public Boolean CategorieIsKey() {
			return false;
		}

		public Integer CategorieLength() {
			return 23;
		}

		public Integer CategoriePrecision() {
			return 0;
		}

		public String CategorieDefault() {

			return null;

		}

		public String CategorieComment() {

			return "";

		}

		public String CategoriePattern() {

			return "dd-MM-yyyy";

		}

		public String CategorieOriginalDbColumnName() {

			return "Categorie";

		}

		public String Sous_categorie;

		public String getSous_categorie() {
			return this.Sous_categorie;
		}

		public Boolean Sous_categorieIsNullable() {
			return true;
		}

		public Boolean Sous_categorieIsKey() {
			return false;
		}

		public Integer Sous_categorieLength() {
			return 20;
		}

		public Integer Sous_categoriePrecision() {
			return 0;
		}

		public String Sous_categorieDefault() {

			return null;

		}

		public String Sous_categorieComment() {

			return "";

		}

		public String Sous_categoriePattern() {

			return "dd-MM-yyyy";

		}

		public String Sous_categorieOriginalDbColumnName() {

			return "Sous_categorie";

		}

		public String Nom_du_produit;

		public String getNom_du_produit() {
			return this.Nom_du_produit;
		}

		public Boolean Nom_du_produitIsNullable() {
			return true;
		}

		public Boolean Nom_du_produitIsKey() {
			return false;
		}

		public Integer Nom_du_produitLength() {
			return 44;
		}

		public Integer Nom_du_produitPrecision() {
			return 0;
		}

		public String Nom_du_produitDefault() {

			return null;

		}

		public String Nom_du_produitComment() {

			return "";

		}

		public String Nom_du_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String Nom_du_produitOriginalDbColumnName() {

			return "Nom_du_produit";

		}

		public String Montant_des_ventes;

		public String getMontant_des_ventes() {
			return this.Montant_des_ventes;
		}

		public Boolean Montant_des_ventesIsNullable() {
			return true;
		}

		public Boolean Montant_des_ventesIsKey() {
			return false;
		}

		public Integer Montant_des_ventesLength() {
			return 18;
		}

		public Integer Montant_des_ventesPrecision() {
			return 0;
		}

		public String Montant_des_ventesDefault() {

			return null;

		}

		public String Montant_des_ventesComment() {

			return "";

		}

		public String Montant_des_ventesPattern() {

			return "dd-MM-yyyy";

		}

		public String Montant_des_ventesOriginalDbColumnName() {

			return "Montant_des_ventes";

		}

		public Integer Quantite;

		public Integer getQuantite() {
			return this.Quantite;
		}

		public Boolean QuantiteIsNullable() {
			return true;
		}

		public Boolean QuantiteIsKey() {
			return false;
		}

		public Integer QuantiteLength() {
			return 1;
		}

		public Integer QuantitePrecision() {
			return 0;
		}

		public String QuantiteDefault() {

			return null;

		}

		public String QuantiteComment() {

			return "";

		}

		public String QuantitePattern() {

			return "dd-MM-yyyy";

		}

		public String QuantiteOriginalDbColumnName() {

			return "Quantite";

		}

		public String Remise;

		public String getRemise() {
			return this.Remise;
		}

		public Boolean RemiseIsNullable() {
			return true;
		}

		public Boolean RemiseIsKey() {
			return false;
		}

		public Integer RemiseLength() {
			return 4;
		}

		public Integer RemisePrecision() {
			return 0;
		}

		public String RemiseDefault() {

			return null;

		}

		public String RemiseComment() {

			return "";

		}

		public String RemisePattern() {

			return "dd-MM-yyyy";

		}

		public String RemiseOriginalDbColumnName() {

			return "Remise";

		}

		public String Profit;

		public String getProfit() {
			return this.Profit;
		}

		public Boolean ProfitIsNullable() {
			return true;
		}

		public Boolean ProfitIsKey() {
			return false;
		}

		public Integer ProfitLength() {
			return 19;
		}

		public Integer ProfitPrecision() {
			return 0;
		}

		public String ProfitDefault() {

			return null;

		}

		public String ProfitComment() {

			return "";

		}

		public String ProfitPattern() {

			return "dd-MM-yyyy";

		}

		public String ProfitOriginalDbColumnName() {

			return "Profit";

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_ligne = readInteger(dis);

					this.ID_commande = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Date_d_expedition = readString(dis);

					this.Mode_d_expedition = readString(dis);

					this.ID_client = readString(dis);

					this.Nom_du_client = readString(dis);

					this.Segment = readString(dis);

					this.Ville = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Zone_geographique = readString(dis);

					this.ID_produit = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_categorie = readString(dis);

					this.Nom_du_produit = readString(dis);

					this.Montant_des_ventes = readString(dis);

					this.Quantite = readInteger(dis);

					this.Remise = readString(dis);

					this.Profit = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_ligne = readInteger(dis);

					this.ID_commande = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Date_d_expedition = readString(dis);

					this.Mode_d_expedition = readString(dis);

					this.ID_client = readString(dis);

					this.Nom_du_client = readString(dis);

					this.Segment = readString(dis);

					this.Ville = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Zone_geographique = readString(dis);

					this.ID_produit = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_categorie = readString(dis);

					this.Nom_du_produit = readString(dis);

					this.Montant_des_ventes = readString(dis);

					this.Quantite = readInteger(dis);

					this.Remise = readString(dis);

					this.Profit = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_ligne, dos);

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Date_d_expedition, dos);

				// String

				writeString(this.Mode_d_expedition, dos);

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.Nom_du_client, dos);

				// String

				writeString(this.Segment, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_categorie, dos);

				// String

				writeString(this.Nom_du_produit, dos);

				// String

				writeString(this.Montant_des_ventes, dos);

				// Integer

				writeInteger(this.Quantite, dos);

				// String

				writeString(this.Remise, dos);

				// String

				writeString(this.Profit, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_ligne, dos);

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Date_d_expedition, dos);

				// String

				writeString(this.Mode_d_expedition, dos);

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.Nom_du_client, dos);

				// String

				writeString(this.Segment, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_categorie, dos);

				// String

				writeString(this.Nom_du_produit, dos);

				// String

				writeString(this.Montant_des_ventes, dos);

				// Integer

				writeInteger(this.Quantite, dos);

				// String

				writeString(this.Remise, dos);

				// String

				writeString(this.Profit, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_ligne=" + String.valueOf(ID_ligne));
			sb.append(",ID_commande=" + ID_commande);
			sb.append(",Date_de_commande=" + Date_de_commande);
			sb.append(",Date_d_expedition=" + Date_d_expedition);
			sb.append(",Mode_d_expedition=" + Mode_d_expedition);
			sb.append(",ID_client=" + ID_client);
			sb.append(",Nom_du_client=" + Nom_du_client);
			sb.append(",Segment=" + Segment);
			sb.append(",Ville=" + Ville);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Zone_geographique=" + Zone_geographique);
			sb.append(",ID_produit=" + ID_produit);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Sous_categorie=" + Sous_categorie);
			sb.append(",Nom_du_produit=" + Nom_du_produit);
			sb.append(",Montant_des_ventes=" + Montant_des_ventes);
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Remise=" + Remise);
			sb.append(",Profit=" + Profit);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputExcel_1Struct
			implements routines.system.IPersistableRow<after_tFileInputExcel_1Struct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];

		public Integer ID_ligne;

		public Integer getID_ligne() {
			return this.ID_ligne;
		}

		public Boolean ID_ligneIsNullable() {
			return true;
		}

		public Boolean ID_ligneIsKey() {
			return false;
		}

		public Integer ID_ligneLength() {
			return 2;
		}

		public Integer ID_lignePrecision() {
			return 0;
		}

		public String ID_ligneDefault() {

			return null;

		}

		public String ID_ligneComment() {

			return "";

		}

		public String ID_lignePattern() {

			return "dd-MM-yyyy";

		}

		public String ID_ligneOriginalDbColumnName() {

			return "ID_ligne";

		}

		public String ID_commande;

		public String getID_commande() {
			return this.ID_commande;
		}

		public Boolean ID_commandeIsNullable() {
			return true;
		}

		public Boolean ID_commandeIsKey() {
			return false;
		}

		public Integer ID_commandeLength() {
			return 15;
		}

		public Integer ID_commandePrecision() {
			return 0;
		}

		public String ID_commandeDefault() {

			return null;

		}

		public String ID_commandeComment() {

			return "";

		}

		public String ID_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String ID_commandeOriginalDbColumnName() {

			return "ID_commande";

		}

		public String Date_de_commande;

		public String getDate_de_commande() {
			return this.Date_de_commande;
		}

		public Boolean Date_de_commandeIsNullable() {
			return true;
		}

		public Boolean Date_de_commandeIsKey() {
			return false;
		}

		public Integer Date_de_commandeLength() {
			return 29;
		}

		public Integer Date_de_commandePrecision() {
			return 0;
		}

		public String Date_de_commandeDefault() {

			return null;

		}

		public String Date_de_commandeComment() {

			return "";

		}

		public String Date_de_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String Date_de_commandeOriginalDbColumnName() {

			return "Date_de_commande";

		}

		public String Date_d_expedition;

		public String getDate_d_expedition() {
			return this.Date_d_expedition;
		}

		public Boolean Date_d_expeditionIsNullable() {
			return true;
		}

		public Boolean Date_d_expeditionIsKey() {
			return false;
		}

		public Integer Date_d_expeditionLength() {
			return 29;
		}

		public Integer Date_d_expeditionPrecision() {
			return 0;
		}

		public String Date_d_expeditionDefault() {

			return null;

		}

		public String Date_d_expeditionComment() {

			return "";

		}

		public String Date_d_expeditionPattern() {

			return "dd-MM-yyyy";

		}

		public String Date_d_expeditionOriginalDbColumnName() {

			return "Date_d_expedition";

		}

		public String Mode_d_expedition;

		public String getMode_d_expedition() {
			return this.Mode_d_expedition;
		}

		public Boolean Mode_d_expeditionIsNullable() {
			return true;
		}

		public Boolean Mode_d_expeditionIsKey() {
			return false;
		}

		public Integer Mode_d_expeditionLength() {
			return 12;
		}

		public Integer Mode_d_expeditionPrecision() {
			return 0;
		}

		public String Mode_d_expeditionDefault() {

			return null;

		}

		public String Mode_d_expeditionComment() {

			return "";

		}

		public String Mode_d_expeditionPattern() {

			return "dd-MM-yyyy";

		}

		public String Mode_d_expeditionOriginalDbColumnName() {

			return "Mode_d_expedition";

		}

		public String ID_client;

		public String getID_client() {
			return this.ID_client;
		}

		public Boolean ID_clientIsNullable() {
			return true;
		}

		public Boolean ID_clientIsKey() {
			return false;
		}

		public Integer ID_clientLength() {
			return 8;
		}

		public Integer ID_clientPrecision() {
			return 0;
		}

		public String ID_clientDefault() {

			return null;

		}

		public String ID_clientComment() {

			return "";

		}

		public String ID_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_clientOriginalDbColumnName() {

			return "ID_client";

		}

		public String Nom_du_client;

		public String getNom_du_client() {
			return this.Nom_du_client;
		}

		public Boolean Nom_du_clientIsNullable() {
			return true;
		}

		public Boolean Nom_du_clientIsKey() {
			return false;
		}

		public Integer Nom_du_clientLength() {
			return 18;
		}

		public Integer Nom_du_clientPrecision() {
			return 0;
		}

		public String Nom_du_clientDefault() {

			return null;

		}

		public String Nom_du_clientComment() {

			return "";

		}

		public String Nom_du_clientPattern() {

			return "dd-MM-yyyy";

		}

		public String Nom_du_clientOriginalDbColumnName() {

			return "Nom_du_client";

		}

		public String Segment;

		public String getSegment() {
			return this.Segment;
		}

		public Boolean SegmentIsNullable() {
			return true;
		}

		public Boolean SegmentIsKey() {
			return false;
		}

		public Integer SegmentLength() {
			return 28;
		}

		public Integer SegmentPrecision() {
			return 0;
		}

		public String SegmentDefault() {

			return null;

		}

		public String SegmentComment() {

			return "";

		}

		public String SegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String SegmentOriginalDbColumnName() {

			return "Segment";

		}

		public String Ville;

		public String getVille() {
			return this.Ville;
		}

		public Boolean VilleIsNullable() {
			return true;
		}

		public Boolean VilleIsKey() {
			return false;
		}

		public Integer VilleLength() {
			return 24;
		}

		public Integer VillePrecision() {
			return 0;
		}

		public String VilleDefault() {

			return null;

		}

		public String VilleComment() {

			return "";

		}

		public String VillePattern() {

			return "dd-MM-yyyy";

		}

		public String VilleOriginalDbColumnName() {

			return "Ville";

		}

		public String Region;

		public String getRegion() {
			return this.Region;
		}

		public Boolean RegionIsNullable() {
			return true;
		}

		public Boolean RegionIsKey() {
			return false;
		}

		public Integer RegionLength() {
			return 35;
		}

		public Integer RegionPrecision() {
			return 0;
		}

		public String RegionDefault() {

			return null;

		}

		public String RegionComment() {

			return "";

		}

		public String RegionPattern() {

			return "dd-MM-yyyy";

		}

		public String RegionOriginalDbColumnName() {

			return "Region";

		}

		public String Pays;

		public String getPays() {
			return this.Pays;
		}

		public Boolean PaysIsNullable() {
			return true;
		}

		public Boolean PaysIsKey() {
			return false;
		}

		public Integer PaysLength() {
			return 11;
		}

		public Integer PaysPrecision() {
			return 0;
		}

		public String PaysDefault() {

			return null;

		}

		public String PaysComment() {

			return "";

		}

		public String PaysPattern() {

			return "dd-MM-yyyy";

		}

		public String PaysOriginalDbColumnName() {

			return "Pays";

		}

		public String Zone_geographique;

		public String getZone_geographique() {
			return this.Zone_geographique;
		}

		public Boolean Zone_geographiqueIsNullable() {
			return true;
		}

		public Boolean Zone_geographiqueIsKey() {
			return false;
		}

		public Integer Zone_geographiqueLength() {
			return 6;
		}

		public Integer Zone_geographiquePrecision() {
			return 0;
		}

		public String Zone_geographiqueDefault() {

			return null;

		}

		public String Zone_geographiqueComment() {

			return "";

		}

		public String Zone_geographiquePattern() {

			return "dd-MM-yyyy";

		}

		public String Zone_geographiqueOriginalDbColumnName() {

			return "Zone_geographique";

		}

		public String ID_produit;

		public String getID_produit() {
			return this.ID_produit;
		}

		public Boolean ID_produitIsNullable() {
			return true;
		}

		public Boolean ID_produitIsKey() {
			return false;
		}

		public Integer ID_produitLength() {
			return 15;
		}

		public Integer ID_produitPrecision() {
			return 0;
		}

		public String ID_produitDefault() {

			return null;

		}

		public String ID_produitComment() {

			return "";

		}

		public String ID_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String ID_produitOriginalDbColumnName() {

			return "ID_produit";

		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public Boolean CategorieIsNullable() {
			return true;
		}

		public Boolean CategorieIsKey() {
			return false;
		}

		public Integer CategorieLength() {
			return 23;
		}

		public Integer CategoriePrecision() {
			return 0;
		}

		public String CategorieDefault() {

			return null;

		}

		public String CategorieComment() {

			return "";

		}

		public String CategoriePattern() {

			return "dd-MM-yyyy";

		}

		public String CategorieOriginalDbColumnName() {

			return "Categorie";

		}

		public String Sous_categorie;

		public String getSous_categorie() {
			return this.Sous_categorie;
		}

		public Boolean Sous_categorieIsNullable() {
			return true;
		}

		public Boolean Sous_categorieIsKey() {
			return false;
		}

		public Integer Sous_categorieLength() {
			return 20;
		}

		public Integer Sous_categoriePrecision() {
			return 0;
		}

		public String Sous_categorieDefault() {

			return null;

		}

		public String Sous_categorieComment() {

			return "";

		}

		public String Sous_categoriePattern() {

			return "dd-MM-yyyy";

		}

		public String Sous_categorieOriginalDbColumnName() {

			return "Sous_categorie";

		}

		public String Nom_du_produit;

		public String getNom_du_produit() {
			return this.Nom_du_produit;
		}

		public Boolean Nom_du_produitIsNullable() {
			return true;
		}

		public Boolean Nom_du_produitIsKey() {
			return false;
		}

		public Integer Nom_du_produitLength() {
			return 44;
		}

		public Integer Nom_du_produitPrecision() {
			return 0;
		}

		public String Nom_du_produitDefault() {

			return null;

		}

		public String Nom_du_produitComment() {

			return "";

		}

		public String Nom_du_produitPattern() {

			return "dd-MM-yyyy";

		}

		public String Nom_du_produitOriginalDbColumnName() {

			return "Nom_du_produit";

		}

		public String Montant_des_ventes;

		public String getMontant_des_ventes() {
			return this.Montant_des_ventes;
		}

		public Boolean Montant_des_ventesIsNullable() {
			return true;
		}

		public Boolean Montant_des_ventesIsKey() {
			return false;
		}

		public Integer Montant_des_ventesLength() {
			return 18;
		}

		public Integer Montant_des_ventesPrecision() {
			return 0;
		}

		public String Montant_des_ventesDefault() {

			return null;

		}

		public String Montant_des_ventesComment() {

			return "";

		}

		public String Montant_des_ventesPattern() {

			return "dd-MM-yyyy";

		}

		public String Montant_des_ventesOriginalDbColumnName() {

			return "Montant_des_ventes";

		}

		public Integer Quantite;

		public Integer getQuantite() {
			return this.Quantite;
		}

		public Boolean QuantiteIsNullable() {
			return true;
		}

		public Boolean QuantiteIsKey() {
			return false;
		}

		public Integer QuantiteLength() {
			return 1;
		}

		public Integer QuantitePrecision() {
			return 0;
		}

		public String QuantiteDefault() {

			return null;

		}

		public String QuantiteComment() {

			return "";

		}

		public String QuantitePattern() {

			return "dd-MM-yyyy";

		}

		public String QuantiteOriginalDbColumnName() {

			return "Quantite";

		}

		public String Remise;

		public String getRemise() {
			return this.Remise;
		}

		public Boolean RemiseIsNullable() {
			return true;
		}

		public Boolean RemiseIsKey() {
			return false;
		}

		public Integer RemiseLength() {
			return 4;
		}

		public Integer RemisePrecision() {
			return 0;
		}

		public String RemiseDefault() {

			return null;

		}

		public String RemiseComment() {

			return "";

		}

		public String RemisePattern() {

			return "dd-MM-yyyy";

		}

		public String RemiseOriginalDbColumnName() {

			return "Remise";

		}

		public String Profit;

		public String getProfit() {
			return this.Profit;
		}

		public Boolean ProfitIsNullable() {
			return true;
		}

		public Boolean ProfitIsKey() {
			return false;
		}

		public Integer ProfitLength() {
			return 19;
		}

		public Integer ProfitPrecision() {
			return 0;
		}

		public String ProfitDefault() {

			return null;

		}

		public String ProfitComment() {

			return "";

		}

		public String ProfitPattern() {

			return "dd-MM-yyyy";

		}

		public String ProfitOriginalDbColumnName() {

			return "Profit";

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_ligne = readInteger(dis);

					this.ID_commande = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Date_d_expedition = readString(dis);

					this.Mode_d_expedition = readString(dis);

					this.ID_client = readString(dis);

					this.Nom_du_client = readString(dis);

					this.Segment = readString(dis);

					this.Ville = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Zone_geographique = readString(dis);

					this.ID_produit = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_categorie = readString(dis);

					this.Nom_du_produit = readString(dis);

					this.Montant_des_ventes = readString(dis);

					this.Quantite = readInteger(dis);

					this.Remise = readString(dis);

					this.Profit = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.ID_ligne = readInteger(dis);

					this.ID_commande = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Date_d_expedition = readString(dis);

					this.Mode_d_expedition = readString(dis);

					this.ID_client = readString(dis);

					this.Nom_du_client = readString(dis);

					this.Segment = readString(dis);

					this.Ville = readString(dis);

					this.Region = readString(dis);

					this.Pays = readString(dis);

					this.Zone_geographique = readString(dis);

					this.ID_produit = readString(dis);

					this.Categorie = readString(dis);

					this.Sous_categorie = readString(dis);

					this.Nom_du_produit = readString(dis);

					this.Montant_des_ventes = readString(dis);

					this.Quantite = readInteger(dis);

					this.Remise = readString(dis);

					this.Profit = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ID_ligne, dos);

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Date_d_expedition, dos);

				// String

				writeString(this.Mode_d_expedition, dos);

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.Nom_du_client, dos);

				// String

				writeString(this.Segment, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_categorie, dos);

				// String

				writeString(this.Nom_du_produit, dos);

				// String

				writeString(this.Montant_des_ventes, dos);

				// Integer

				writeInteger(this.Quantite, dos);

				// String

				writeString(this.Remise, dos);

				// String

				writeString(this.Profit, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ID_ligne, dos);

				// String

				writeString(this.ID_commande, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Date_d_expedition, dos);

				// String

				writeString(this.Mode_d_expedition, dos);

				// String

				writeString(this.ID_client, dos);

				// String

				writeString(this.Nom_du_client, dos);

				// String

				writeString(this.Segment, dos);

				// String

				writeString(this.Ville, dos);

				// String

				writeString(this.Region, dos);

				// String

				writeString(this.Pays, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// String

				writeString(this.ID_produit, dos);

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Sous_categorie, dos);

				// String

				writeString(this.Nom_du_produit, dos);

				// String

				writeString(this.Montant_des_ventes, dos);

				// Integer

				writeInteger(this.Quantite, dos);

				// String

				writeString(this.Remise, dos);

				// String

				writeString(this.Profit, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ID_ligne=" + String.valueOf(ID_ligne));
			sb.append(",ID_commande=" + ID_commande);
			sb.append(",Date_de_commande=" + Date_de_commande);
			sb.append(",Date_d_expedition=" + Date_d_expedition);
			sb.append(",Mode_d_expedition=" + Mode_d_expedition);
			sb.append(",ID_client=" + ID_client);
			sb.append(",Nom_du_client=" + Nom_du_client);
			sb.append(",Segment=" + Segment);
			sb.append(",Ville=" + Ville);
			sb.append(",Region=" + Region);
			sb.append(",Pays=" + Pays);
			sb.append(",Zone_geographique=" + Zone_geographique);
			sb.append(",ID_produit=" + ID_produit);
			sb.append(",Categorie=" + Categorie);
			sb.append(",Sous_categorie=" + Sous_categorie);
			sb.append(",Nom_du_produit=" + Nom_du_produit);
			sb.append(",Montant_des_ventes=" + Montant_des_ventes);
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Remise=" + Remise);
			sb.append(",Profit=" + Profit);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputExcel_1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputExcel_2Process(globalMap);

				row1Struct row1 = new row1Struct();
				dim_clientStruct dim_client = new dim_clientStruct();
				row4Struct row4 = new row4Struct();
				dim_commandeStruct dim_commande = new dim_commandeStruct();
				row3Struct row3 = new row3Struct();
				dim_produitStruct dim_produit = new dim_produitStruct();
				row5Struct row5 = new row5Struct();
				table_faitStruct table_fait = new table_faitStruct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tDBOutput_1 = 0;

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				String tableName_tDBOutput_1 = "Dim_client";
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				long date_tDBOutput_1;

				java.sql.Connection conn_tDBOutput_1 = null;

				String properties_tDBOutput_1 = "noDatetimeStringSync=true&serverTimezone=Europe/Paris";
				if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
					properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "talend_db" + "?"
						+ properties_tDBOutput_1;

				String driverClass_tDBOutput_1 = "com.mysql.cj.jdbc.Driver";

				String dbUser_tDBOutput_1 = "berto";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:ndvhwdsdJ4so7tMoGx6hhDTJ1ZmwdClNd2kWIvuEC/zmnWwB");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				java.lang.Class.forName(driverClass_tDBOutput_1);

				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);

				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_1 = conn_tDBOutput_1.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_1 = dbMetaData_tDBOutput_1.getTables("talend_db", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_1 = false;
				while (rsTable_tDBOutput_1.next()) {
					String table_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_NAME");
					if (table_tDBOutput_1.equalsIgnoreCase("Dim_client")) {
						whetherExist_tDBOutput_1 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtDrop_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						stmtDrop_tDBOutput_1.execute("DROP TABLE `" + tableName_tDBOutput_1 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1
							+ "`(`ID_client` VARCHAR(8)  ,`Nom_du_client` VARCHAR(200)  ,`Region` VARCHAR(35)  ,`Pays` VARCHAR(11)  ,`Zone_geographique` VARCHAR(6)  ,`Ville` VARCHAR(200)  ,`Segment` VARCHAR(28)  ,primary key(`ID_client`))");
				}

				String insert_tDBOutput_1 = "INSERT INTO `" + "Dim_client"
						+ "` (`ID_client`,`Nom_du_client`,`Region`,`Pays`,`Zone_geographique`,`Ville`,`Segment`) VALUES (?,?,?,?,?,?,?)";

				int batchSize_tDBOutput_1 = 100;
				int batchSizeCounter_tDBOutput_1 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tUniqRow_2 begin ] start
				 */

				ok_Hash.put("tUniqRow_2", false);
				start_Hash.put("tUniqRow_2", System.currentTimeMillis());

				currentComponent = "tUniqRow_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "dim_client");
				}

				int tos_count_tUniqRow_2 = 0;

				class KeyStruct_tUniqRow_2 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String ID_client;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.ID_client == null) ? 0 : this.ID_client.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_2 other = (KeyStruct_tUniqRow_2) obj;

						if (this.ID_client == null) {
							if (other.ID_client != null)
								return false;

						} else if (!this.ID_client.equals(other.ID_client))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_2 = 0;
				int nb_duplicates_tUniqRow_2 = 0;
				KeyStruct_tUniqRow_2 finder_tUniqRow_2 = new KeyStruct_tUniqRow_2();
				java.util.Set<KeyStruct_tUniqRow_2> keystUniqRow_2 = new java.util.HashSet<KeyStruct_tUniqRow_2>();

				/**
				 * [tUniqRow_2 begin ] stop
				 */

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tDBOutput_2 = 0;

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;
				int rejectedCount_tDBOutput_2 = 0;

				String tableName_tDBOutput_2 = "Dim_commande";
				boolean whetherReject_tDBOutput_2 = false;

				java.util.Calendar calendar_tDBOutput_2 = java.util.Calendar.getInstance();
				calendar_tDBOutput_2.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_2 = calendar_tDBOutput_2.getTime().getTime();
				calendar_tDBOutput_2.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_2 = calendar_tDBOutput_2.getTime().getTime();
				long date_tDBOutput_2;

				java.sql.Connection conn_tDBOutput_2 = null;

				String properties_tDBOutput_2 = "noDatetimeStringSync=true&serverTimezone=Europe/Paris";
				if (properties_tDBOutput_2 == null || properties_tDBOutput_2.trim().length() == 0) {
					properties_tDBOutput_2 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_2.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_2 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_2.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_2 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_2 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "talend_db" + "?"
						+ properties_tDBOutput_2;

				String driverClass_tDBOutput_2 = "com.mysql.cj.jdbc.Driver";

				String dbUser_tDBOutput_2 = "berto";

				final String decryptedPassword_tDBOutput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:OPCCPeWOByeVoIv3PXDf91bnJUI3ivUPizBY7Y7wibQ8nF2r");

				String dbPwd_tDBOutput_2 = decryptedPassword_tDBOutput_2;
				java.lang.Class.forName(driverClass_tDBOutput_2);

				conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2, dbUser_tDBOutput_2,
						dbPwd_tDBOutput_2);

				resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);

				conn_tDBOutput_2.setAutoCommit(false);
				int commitEvery_tDBOutput_2 = 10000;
				int commitCounter_tDBOutput_2 = 0;

				int count_tDBOutput_2 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_2 = conn_tDBOutput_2.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_2 = dbMetaData_tDBOutput_2.getTables("talend_db", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_2 = false;
				while (rsTable_tDBOutput_2.next()) {
					String table_tDBOutput_2 = rsTable_tDBOutput_2.getString("TABLE_NAME");
					if (table_tDBOutput_2.equalsIgnoreCase("Dim_commande")) {
						whetherExist_tDBOutput_2 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_2) {
					try (java.sql.Statement stmtDrop_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
						stmtDrop_tDBOutput_2.execute("DROP TABLE `" + tableName_tDBOutput_2 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
					stmtCreate_tDBOutput_2.execute("CREATE TABLE `" + tableName_tDBOutput_2
							+ "`(`ID_commande` VARCHAR(15)  ,`Date_de_commande` VARCHAR(29)  ,`Date_d_expedition` VARCHAR(29)  ,`Mode_d_expedition` VARCHAR(12)  ,primary key(`ID_commande`))");
				}

				String insert_tDBOutput_2 = "INSERT INTO `" + "Dim_commande"
						+ "` (`ID_commande`,`Date_de_commande`,`Date_d_expedition`,`Mode_d_expedition`) VALUES (?,?,?,?)";

				int batchSize_tDBOutput_2 = 100;
				int batchSizeCounter_tDBOutput_2 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tUniqRow_1 begin ] start
				 */

				ok_Hash.put("tUniqRow_1", false);
				start_Hash.put("tUniqRow_1", System.currentTimeMillis());

				currentComponent = "tUniqRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "dim_commande");
				}

				int tos_count_tUniqRow_1 = 0;

				class KeyStruct_tUniqRow_1 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String ID_commande;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.ID_commande == null) ? 0 : this.ID_commande.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_1 other = (KeyStruct_tUniqRow_1) obj;

						if (this.ID_commande == null) {
							if (other.ID_commande != null)
								return false;

						} else if (!this.ID_commande.equals(other.ID_commande))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_1 = 0;
				int nb_duplicates_tUniqRow_1 = 0;
				KeyStruct_tUniqRow_1 finder_tUniqRow_1 = new KeyStruct_tUniqRow_1();
				java.util.Set<KeyStruct_tUniqRow_1> keystUniqRow_1 = new java.util.HashSet<KeyStruct_tUniqRow_1>();

				/**
				 * [tUniqRow_1 begin ] stop
				 */

				/**
				 * [tDBOutput_3 begin ] start
				 */

				ok_Hash.put("tDBOutput_3", false);
				start_Hash.put("tDBOutput_3", System.currentTimeMillis());

				currentComponent = "tDBOutput_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tDBOutput_3 = 0;

				int nb_line_tDBOutput_3 = 0;
				int nb_line_update_tDBOutput_3 = 0;
				int nb_line_inserted_tDBOutput_3 = 0;
				int nb_line_deleted_tDBOutput_3 = 0;
				int nb_line_rejected_tDBOutput_3 = 0;

				int deletedCount_tDBOutput_3 = 0;
				int updatedCount_tDBOutput_3 = 0;
				int insertedCount_tDBOutput_3 = 0;
				int rowsToCommitCount_tDBOutput_3 = 0;
				int rejectedCount_tDBOutput_3 = 0;

				String tableName_tDBOutput_3 = "Dim_produit";
				boolean whetherReject_tDBOutput_3 = false;

				java.util.Calendar calendar_tDBOutput_3 = java.util.Calendar.getInstance();
				calendar_tDBOutput_3.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_3 = calendar_tDBOutput_3.getTime().getTime();
				calendar_tDBOutput_3.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_3 = calendar_tDBOutput_3.getTime().getTime();
				long date_tDBOutput_3;

				java.sql.Connection conn_tDBOutput_3 = null;

				String properties_tDBOutput_3 = "noDatetimeStringSync=true&serverTimezone=Europe/Paris";
				if (properties_tDBOutput_3 == null || properties_tDBOutput_3.trim().length() == 0) {
					properties_tDBOutput_3 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_3.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_3 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_3.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_3 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_3 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "talend_db" + "?"
						+ properties_tDBOutput_3;

				String driverClass_tDBOutput_3 = "com.mysql.cj.jdbc.Driver";

				String dbUser_tDBOutput_3 = "berto";

				final String decryptedPassword_tDBOutput_3 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:jkehGiXdJCWJ3oP27q32ZMUlIXAVUxxXXZV04VhHDM5PHbug");

				String dbPwd_tDBOutput_3 = decryptedPassword_tDBOutput_3;
				java.lang.Class.forName(driverClass_tDBOutput_3);

				conn_tDBOutput_3 = java.sql.DriverManager.getConnection(url_tDBOutput_3, dbUser_tDBOutput_3,
						dbPwd_tDBOutput_3);

				resourceMap.put("conn_tDBOutput_3", conn_tDBOutput_3);

				conn_tDBOutput_3.setAutoCommit(false);
				int commitEvery_tDBOutput_3 = 10000;
				int commitCounter_tDBOutput_3 = 0;

				int count_tDBOutput_3 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_3 = conn_tDBOutput_3.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_3 = dbMetaData_tDBOutput_3.getTables("talend_db", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_3 = false;
				while (rsTable_tDBOutput_3.next()) {
					String table_tDBOutput_3 = rsTable_tDBOutput_3.getString("TABLE_NAME");
					if (table_tDBOutput_3.equalsIgnoreCase("Dim_produit")) {
						whetherExist_tDBOutput_3 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_3) {
					try (java.sql.Statement stmtDrop_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
						stmtDrop_tDBOutput_3.execute("DROP TABLE `" + tableName_tDBOutput_3 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
					stmtCreate_tDBOutput_3.execute("CREATE TABLE `" + tableName_tDBOutput_3
							+ "`(`ID_produit` VARCHAR(15)  ,`Categorie` VARCHAR(23)  ,`Sous_categorie` VARCHAR(20)  ,`Nom_du_produit` VARCHAR(200)  ,`prix` DOUBLE(18,0)  ,`Remise` VARCHAR(200)  )");
				}

				String insert_tDBOutput_3 = "INSERT INTO `" + "Dim_produit"
						+ "` (`ID_produit`,`Categorie`,`Sous_categorie`,`Nom_du_produit`,`prix`,`Remise`) VALUES (?,?,?,?,?,?)";

				int batchSize_tDBOutput_3 = 100;
				int batchSizeCounter_tDBOutput_3 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_3 = conn_tDBOutput_3.prepareStatement(insert_tDBOutput_3);
				resourceMap.put("pstmt_tDBOutput_3", pstmt_tDBOutput_3);

				/**
				 * [tDBOutput_3 begin ] stop
				 */

				/**
				 * [tUniqRow_3 begin ] start
				 */

				ok_Hash.put("tUniqRow_3", false);
				start_Hash.put("tUniqRow_3", System.currentTimeMillis());

				currentComponent = "tUniqRow_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "dim_produit");
				}

				int tos_count_tUniqRow_3 = 0;

				class KeyStruct_tUniqRow_3 {

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String ID_produit;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.ID_produit == null) ? 0 : this.ID_produit.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final KeyStruct_tUniqRow_3 other = (KeyStruct_tUniqRow_3) obj;

						if (this.ID_produit == null) {
							if (other.ID_produit != null)
								return false;

						} else if (!this.ID_produit.equals(other.ID_produit))

							return false;

						return true;
					}

				}

				int nb_uniques_tUniqRow_3 = 0;
				int nb_duplicates_tUniqRow_3 = 0;
				KeyStruct_tUniqRow_3 finder_tUniqRow_3 = new KeyStruct_tUniqRow_3();
				java.util.Set<KeyStruct_tUniqRow_3> keystUniqRow_3 = new java.util.HashSet<KeyStruct_tUniqRow_3>();

				/**
				 * [tUniqRow_3 begin ] stop
				 */

				/**
				 * [tDBOutput_4 begin ] start
				 */

				ok_Hash.put("tDBOutput_4", false);
				start_Hash.put("tDBOutput_4", System.currentTimeMillis());

				currentComponent = "tDBOutput_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "table_fait");
				}

				int tos_count_tDBOutput_4 = 0;

				int nb_line_tDBOutput_4 = 0;
				int nb_line_update_tDBOutput_4 = 0;
				int nb_line_inserted_tDBOutput_4 = 0;
				int nb_line_deleted_tDBOutput_4 = 0;
				int nb_line_rejected_tDBOutput_4 = 0;

				int deletedCount_tDBOutput_4 = 0;
				int updatedCount_tDBOutput_4 = 0;
				int insertedCount_tDBOutput_4 = 0;
				int rowsToCommitCount_tDBOutput_4 = 0;
				int rejectedCount_tDBOutput_4 = 0;

				String tableName_tDBOutput_4 = "fact_table";
				boolean whetherReject_tDBOutput_4 = false;

				java.util.Calendar calendar_tDBOutput_4 = java.util.Calendar.getInstance();
				calendar_tDBOutput_4.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_4 = calendar_tDBOutput_4.getTime().getTime();
				calendar_tDBOutput_4.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_4 = calendar_tDBOutput_4.getTime().getTime();
				long date_tDBOutput_4;

				java.sql.Connection conn_tDBOutput_4 = null;

				String properties_tDBOutput_4 = "noDatetimeStringSync=true&serverTimezone=Europe/Paris";
				if (properties_tDBOutput_4 == null || properties_tDBOutput_4.trim().length() == 0) {
					properties_tDBOutput_4 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_4.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_4 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_4.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_4 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_4 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "talend_db" + "?"
						+ properties_tDBOutput_4;

				String driverClass_tDBOutput_4 = "com.mysql.cj.jdbc.Driver";

				String dbUser_tDBOutput_4 = "berto";

				final String decryptedPassword_tDBOutput_4 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:eLZKTFDkrABQ/a30HJpKQ4dduhDu0OiSnb2XhtClzr5jEpH2");

				String dbPwd_tDBOutput_4 = decryptedPassword_tDBOutput_4;
				java.lang.Class.forName(driverClass_tDBOutput_4);

				conn_tDBOutput_4 = java.sql.DriverManager.getConnection(url_tDBOutput_4, dbUser_tDBOutput_4,
						dbPwd_tDBOutput_4);

				resourceMap.put("conn_tDBOutput_4", conn_tDBOutput_4);

				conn_tDBOutput_4.setAutoCommit(false);
				int commitEvery_tDBOutput_4 = 10000;
				int commitCounter_tDBOutput_4 = 0;

				int count_tDBOutput_4 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_4 = conn_tDBOutput_4.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_4 = dbMetaData_tDBOutput_4.getTables("talend_db", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_4 = false;
				while (rsTable_tDBOutput_4.next()) {
					String table_tDBOutput_4 = rsTable_tDBOutput_4.getString("TABLE_NAME");
					if (table_tDBOutput_4.equalsIgnoreCase("fact_table")) {
						whetherExist_tDBOutput_4 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_4) {
					try (java.sql.Statement stmtDrop_tDBOutput_4 = conn_tDBOutput_4.createStatement()) {
						stmtDrop_tDBOutput_4.execute("DROP TABLE `" + tableName_tDBOutput_4 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_4 = conn_tDBOutput_4.createStatement()) {
					stmtCreate_tDBOutput_4.execute("CREATE TABLE `" + tableName_tDBOutput_4
							+ "`(`ID_ligne` INT(2)  ,`ID_commande` VARCHAR(15)  ,`ID_client` VARCHAR(8)  ,`ID_produit` VARCHAR(15)  ,`Montant_des_ventes` VARCHAR(18)  ,`Profit` VARCHAR(200)  ,`Quantite` INT(1)  ,primary key(`ID_ligne`))");
				}

				String insert_tDBOutput_4 = "INSERT INTO `" + "fact_table"
						+ "` (`ID_ligne`,`ID_commande`,`ID_client`,`ID_produit`,`Montant_des_ventes`,`Profit`,`Quantite`) VALUES (?,?,?,?,?,?,?)";

				int batchSize_tDBOutput_4 = 100;
				int batchSizeCounter_tDBOutput_4 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_4 = conn_tDBOutput_4.prepareStatement(insert_tDBOutput_4);
				resourceMap.put("pstmt_tDBOutput_4", pstmt_tDBOutput_4);

				/**
				 * [tDBOutput_4 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
						.get("tHash_Lookup_row2"));

				row2Struct row2HashKey = new row2Struct();
				row2Struct row2Default = new row2Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					Double prix;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				dim_clientStruct dim_client_tmp = new dim_clientStruct();
				dim_commandeStruct dim_commande_tmp = new dim_commandeStruct();
				dim_produitStruct dim_produit_tmp = new dim_produitStruct();
				table_faitStruct table_fait_tmp = new table_faitStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:HwDUNbvLtaEVcwRbynE4Re3JHM1fA25QQ8Kw5w==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				class RegexUtil_tFileInputExcel_1 {

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

						if (useRegex) {// this part process the regex issue

							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
								String sheetName = sheet.getSheetName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									if (sheet != null) {
										list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
									}
								}
							}

						} else {
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
								.getSheetAt(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}
				RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

				Object source_tFileInputExcel_1 = "C:/Data/Hypermarche.xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof String) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_1), password_tFileInputExcel_1,
									true);
				} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_1, password_tFileInputExcel_1);
				} else {
					workbook_tFileInputExcel_1 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.ss.usermodel.Sheet sheet_tFileInputExcel_1 : workbook_tFileInputExcel_1) {
						sheetList_tFileInputExcel_1
								.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet_tFileInputExcel_1);
					}
					if (sheetList_tFileInputExcel_1.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
						if (sheet_FilterNull_tFileInputExcel_1 != null
								&& sheetList_FilterNull_tFileInputExcel_1.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
						}
					}
					sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
					if (sheetList_tFileInputExcel_1.size() > 0) {
						int nb_line_tFileInputExcel_1 = 0;

						int begin_line_tFileInputExcel_1 = 1;

						int footer_input_tFileInputExcel_1 = 0;

						int end_line_tFileInputExcel_1 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							end_line_tFileInputExcel_1 += (sheet_tFileInputExcel_1.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
						int limit_tFileInputExcel_1 = -1;
						int start_column_tFileInputExcel_1 = 1 - 1;
						int end_column_tFileInputExcel_1 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
								.get(0);
						int rowCount_tFileInputExcel_1 = 0;
						int sheetIndex_tFileInputExcel_1 = 0;
						int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

							int emptyColumnCount_tFileInputExcel_1 = 0;

							if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
								break;
							}

							while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
								rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
								sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
										.get(++sheetIndex_tFileInputExcel_1);
								currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_1_CURRENT_SHEET", sheet_tFileInputExcel_1.getSheetName());
							if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
								row_tFileInputExcel_1 = sheet_tFileInputExcel_1
										.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
							}
							row1 = null;
							int tempRowLength_tFileInputExcel_1 = 20;

							int columnIndex_tFileInputExcel_1 = 0;

							String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
							int excel_end_column_tFileInputExcel_1;
							if (row_tFileInputExcel_1 == null) {
								excel_end_column_tFileInputExcel_1 = 0;
							} else {
								excel_end_column_tFileInputExcel_1 = row_tFileInputExcel_1.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_1;
							if (end_column_tFileInputExcel_1 == -1) {
								actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
							} else {
								actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > excel_end_column_tFileInputExcel_1
										? excel_end_column_tFileInputExcel_1
										: end_column_tFileInputExcel_1;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {
								if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1
											.getCell(i + start_column_tFileInputExcel_1);
									if (cell_tFileInputExcel_1 != null) {
										switch (cell_tFileInputExcel_1.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_1)) {
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1
														.format(cell_tFileInputExcel_1.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_1[i] = String
													.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_1)) {
													temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_1.getNumericCellValue());
													temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_1[i] = String
														.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_1[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_1[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_1[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_1[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_1 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "ID_ligne";

									row1.ID_ligne = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.ID_ligne = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "ID_commande";

									row1.ID_commande = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.ID_commande = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 2;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Date_de_commande";

									row1.Date_de_commande = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Date_de_commande = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 3;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Date_d_expedition";

									row1.Date_d_expedition = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Date_d_expedition = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 4;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Mode_d_expedition";

									row1.Mode_d_expedition = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Mode_d_expedition = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 5;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "ID_client";

									row1.ID_client = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.ID_client = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 6;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom_du_client";

									row1.Nom_du_client = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Nom_du_client = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 7;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Segment";

									row1.Segment = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Segment = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 8;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Ville";

									row1.Ville = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Ville = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 9;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Region";

									row1.Region = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Region = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 10;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Pays";

									row1.Pays = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Pays = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 11;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Zone_geographique";

									row1.Zone_geographique = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Zone_geographique = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 12;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "ID_produit";

									row1.ID_produit = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.ID_produit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 13;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Categorie";

									row1.Categorie = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Categorie = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 14;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sous_categorie";

									row1.Sous_categorie = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Sous_categorie = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 15;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nom_du_produit";

									row1.Nom_du_produit = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Nom_du_produit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 16;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Montant_des_ventes";

									row1.Montant_des_ventes = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Montant_des_ventes = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 17;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Quantite";

									row1.Quantite = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Quantite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 18;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Remise";

									row1.Remise = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Remise = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 19;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Profit";

									row1.Profit = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Profit = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputExcel_1 begin ] stop
							 */

							/**
							 * [tFileInputExcel_1 main ] start
							 */

							currentComponent = "tFileInputExcel_1";

							tos_count_tFileInputExcel_1++;

							/**
							 * [tFileInputExcel_1 main ] stop
							 */

							/**
							 * [tFileInputExcel_1 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tMap_1 main ] start
								 */

								currentComponent = "tMap_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row1"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

								row2Struct row2 = null;

								// ###############################
								// # Input tables (lookups)

								boolean rejectedInnerJoin_tMap_1 = false;
								boolean mainRowRejected_tMap_1 = false;

								///////////////////////////////////////////////
								// Starting Lookup Table "row2"
								///////////////////////////////////////////////

								boolean forceLooprow2 = false;

								row2Struct row2ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_1 = false;

									row2HashKey.Categorie = row1.Categorie;

									row2HashKey.Date_de_commande = row1.Date_de_commande;

									row2HashKey.Segment = row1.Segment;

									row2HashKey.hashCodeDirty = true;

									tHash_Lookup_row2.lookup(row2HashKey);

									if (!tHash_Lookup_row2.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_1 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								if (tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2'
									// and it contains more one result from keys : row2.Categorie = '" +
									// row2HashKey.Categorie + "', row2.Date_de_commande = '" +
									// row2HashKey.Date_de_commande + "', row2.Segment = '" + row2HashKey.Segment +
									// "'");
								} // G 071

								row2Struct fromLookup_row2 = null;
								row2 = row2Default;

								if (tHash_Lookup_row2 != null && tHash_Lookup_row2.hasNext()) { // G 099

									fromLookup_row2 = tHash_Lookup_row2.next();

								} // G 099

								if (fromLookup_row2 != null) {
									row2 = fromLookup_row2;
								}

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;
									Var.prix = Double.parseDouble(row1.Montant_des_ventes.replace(",", "."))
											+ (Double.parseDouble(row1.Montant_des_ventes.replace(",", "."))
													* Double.parseDouble(row1.Remise.replace(",", ".")))
													/ row1.Quantite;// ###############################
									// ###############################
									// # Output tables

									dim_client = null;
									dim_commande = null;
									dim_produit = null;
									table_fait = null;

									if (!rejectedInnerJoin_tMap_1) {

// # Output table : 'dim_client'
										dim_client_tmp.ID_client = row1.ID_client;
										dim_client_tmp.Nom_du_client = row1.Nom_du_client;
										dim_client_tmp.Region = row1.Region;
										dim_client_tmp.Pays = row1.Pays;
										dim_client_tmp.Zone_geographique = row1.Zone_geographique;
										dim_client_tmp.Ville = row1.Ville;
										dim_client_tmp.Segment = row1.Segment;
										dim_client = dim_client_tmp;

// # Output table : 'dim_commande'
										dim_commande_tmp.ID_commande = row1.ID_commande;
										dim_commande_tmp.Date_de_commande = row1.Date_de_commande;
										dim_commande_tmp.Date_d_expedition = row1.Date_d_expedition;
										dim_commande_tmp.Mode_d_expedition = row1.Mode_d_expedition;
										dim_commande = dim_commande_tmp;

// # Output table : 'dim_produit'
										dim_produit_tmp.ID_produit = row1.ID_produit;
										dim_produit_tmp.Categorie = row1.Categorie;
										dim_produit_tmp.Sous_categorie = row1.Sous_categorie;
										dim_produit_tmp.Nom_du_produit = row1.Nom_du_produit;
										dim_produit_tmp.prix = Var.prix;
										dim_produit_tmp.Remise = row1.Remise;
										dim_produit = dim_produit_tmp;

// # Output table : 'table_fait'
										table_fait_tmp.ID_ligne = row1.ID_ligne;
										table_fait_tmp.ID_commande = row1.ID_commande;
										table_fait_tmp.ID_client = row1.ID_client;
										table_fait_tmp.ID_produit = row1.ID_produit;
										table_fait_tmp.Montant_des_ventes = row1.Montant_des_ventes;
										table_fait_tmp.Profit = row1.Profit;
										table_fait_tmp.Quantite = row1.Quantite;
										table_fait = table_fait_tmp;
									} // closing inner join bracket (2)
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_1 = false;

								tos_count_tMap_1++;

								/**
								 * [tMap_1 main ] stop
								 */

								/**
								 * [tMap_1 process_data_begin ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_begin ] stop
								 */
// Start of branch "dim_client"
								if (dim_client != null) {

									/**
									 * [tUniqRow_2 main ] start
									 */

									currentComponent = "tUniqRow_2";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "dim_client"

										);
									}

									row4 = null;
									if (dim_client.ID_client == null) {
										finder_tUniqRow_2.ID_client = null;
									} else {
										finder_tUniqRow_2.ID_client = dim_client.ID_client.toLowerCase();
									}
									finder_tUniqRow_2.hashCodeDirty = true;
									if (!keystUniqRow_2.contains(finder_tUniqRow_2)) {
										KeyStruct_tUniqRow_2 new_tUniqRow_2 = new KeyStruct_tUniqRow_2();

										if (dim_client.ID_client == null) {
											new_tUniqRow_2.ID_client = null;
										} else {
											new_tUniqRow_2.ID_client = dim_client.ID_client.toLowerCase();
										}

										keystUniqRow_2.add(new_tUniqRow_2);
										if (row4 == null) {

											row4 = new row4Struct();
										}
										row4.ID_client = dim_client.ID_client;
										row4.Nom_du_client = dim_client.Nom_du_client;
										row4.Region = dim_client.Region;
										row4.Pays = dim_client.Pays;
										row4.Zone_geographique = dim_client.Zone_geographique;
										row4.Ville = dim_client.Ville;
										row4.Segment = dim_client.Segment;
										nb_uniques_tUniqRow_2++;
									} else {
										nb_duplicates_tUniqRow_2++;
									}

									tos_count_tUniqRow_2++;

									/**
									 * [tUniqRow_2 main ] stop
									 */

									/**
									 * [tUniqRow_2 process_data_begin ] start
									 */

									currentComponent = "tUniqRow_2";

									/**
									 * [tUniqRow_2 process_data_begin ] stop
									 */
// Start of branch "row4"
									if (row4 != null) {

										/**
										 * [tDBOutput_1 main ] start
										 */

										currentComponent = "tDBOutput_1";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row4"

											);
										}

										whetherReject_tDBOutput_1 = false;
										if (row4.ID_client == null) {
											pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_1.setString(1, row4.ID_client);
										}

										if (row4.Nom_du_client == null) {
											pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_1.setString(2, row4.Nom_du_client);
										}

										if (row4.Region == null) {
											pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_1.setString(3, row4.Region);
										}

										if (row4.Pays == null) {
											pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_1.setString(4, row4.Pays);
										}

										if (row4.Zone_geographique == null) {
											pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_1.setString(5, row4.Zone_geographique);
										}

										if (row4.Ville == null) {
											pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_1.setString(6, row4.Ville);
										}

										if (row4.Segment == null) {
											pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_1.setString(7, row4.Segment);
										}

										pstmt_tDBOutput_1.addBatch();
										nb_line_tDBOutput_1++;

										batchSizeCounter_tDBOutput_1++;
										if (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1) {
											try {
												int countSum_tDBOutput_1 = 0;
												for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
													countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED
															? 0
															: 1);
												}
												rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
												insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
											} catch (java.sql.BatchUpdateException e) {
												globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
												int countSum_tDBOutput_1 = 0;
												for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
													countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
															: countEach_tDBOutput_1);
												}
												rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
												insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
												System.err.println(e.getMessage());
											}

											batchSizeCounter_tDBOutput_1 = 0;
										}
										commitCounter_tDBOutput_1++;

										if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

											try {
												int countSum_tDBOutput_1 = 0;
												for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
													countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : 1);
												}
												rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
												insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
											} catch (java.sql.BatchUpdateException e) {
												globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
												int countSum_tDBOutput_1 = 0;
												for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
													countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
															: countEach_tDBOutput_1);
												}
												rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
												insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
												System.err.println(e.getMessage());

											}
											if (rowsToCommitCount_tDBOutput_1 != 0) {
											}
											conn_tDBOutput_1.commit();
											if (rowsToCommitCount_tDBOutput_1 != 0) {
												rowsToCommitCount_tDBOutput_1 = 0;
											}
											commitCounter_tDBOutput_1 = 0;
										}

										tos_count_tDBOutput_1++;

										/**
										 * [tDBOutput_1 main ] stop
										 */

										/**
										 * [tDBOutput_1 process_data_begin ] start
										 */

										currentComponent = "tDBOutput_1";

										/**
										 * [tDBOutput_1 process_data_begin ] stop
										 */

										/**
										 * [tDBOutput_1 process_data_end ] start
										 */

										currentComponent = "tDBOutput_1";

										/**
										 * [tDBOutput_1 process_data_end ] stop
										 */

									} // End of branch "row4"

									/**
									 * [tUniqRow_2 process_data_end ] start
									 */

									currentComponent = "tUniqRow_2";

									/**
									 * [tUniqRow_2 process_data_end ] stop
									 */

								} // End of branch "dim_client"

// Start of branch "dim_commande"
								if (dim_commande != null) {

									/**
									 * [tUniqRow_1 main ] start
									 */

									currentComponent = "tUniqRow_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "dim_commande"

										);
									}

									row3 = null;
									if (dim_commande.ID_commande == null) {
										finder_tUniqRow_1.ID_commande = null;
									} else {
										finder_tUniqRow_1.ID_commande = dim_commande.ID_commande.toLowerCase();
									}
									finder_tUniqRow_1.hashCodeDirty = true;
									if (!keystUniqRow_1.contains(finder_tUniqRow_1)) {
										KeyStruct_tUniqRow_1 new_tUniqRow_1 = new KeyStruct_tUniqRow_1();

										if (dim_commande.ID_commande == null) {
											new_tUniqRow_1.ID_commande = null;
										} else {
											new_tUniqRow_1.ID_commande = dim_commande.ID_commande.toLowerCase();
										}

										keystUniqRow_1.add(new_tUniqRow_1);
										if (row3 == null) {

											row3 = new row3Struct();
										}
										row3.ID_commande = dim_commande.ID_commande;
										row3.Date_de_commande = dim_commande.Date_de_commande;
										row3.Date_d_expedition = dim_commande.Date_d_expedition;
										row3.Mode_d_expedition = dim_commande.Mode_d_expedition;
										nb_uniques_tUniqRow_1++;
									} else {
										nb_duplicates_tUniqRow_1++;
									}

									tos_count_tUniqRow_1++;

									/**
									 * [tUniqRow_1 main ] stop
									 */

									/**
									 * [tUniqRow_1 process_data_begin ] start
									 */

									currentComponent = "tUniqRow_1";

									/**
									 * [tUniqRow_1 process_data_begin ] stop
									 */
// Start of branch "row3"
									if (row3 != null) {

										/**
										 * [tDBOutput_2 main ] start
										 */

										currentComponent = "tDBOutput_2";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row3"

											);
										}

										whetherReject_tDBOutput_2 = false;
										if (row3.ID_commande == null) {
											pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_2.setString(1, row3.ID_commande);
										}

										if (row3.Date_de_commande == null) {
											pstmt_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_2.setString(2, row3.Date_de_commande);
										}

										if (row3.Date_d_expedition == null) {
											pstmt_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_2.setString(3, row3.Date_d_expedition);
										}

										if (row3.Mode_d_expedition == null) {
											pstmt_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_2.setString(4, row3.Mode_d_expedition);
										}

										pstmt_tDBOutput_2.addBatch();
										nb_line_tDBOutput_2++;

										batchSizeCounter_tDBOutput_2++;
										if (batchSize_tDBOutput_2 <= batchSizeCounter_tDBOutput_2) {
											try {
												int countSum_tDBOutput_2 = 0;
												for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
													countSum_tDBOutput_2 += (countEach_tDBOutput_2 == java.sql.Statement.EXECUTE_FAILED
															? 0
															: 1);
												}
												rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
												insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
											} catch (java.sql.BatchUpdateException e) {
												globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
												int countSum_tDBOutput_2 = 0;
												for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
													countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0
															: countEach_tDBOutput_2);
												}
												rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
												insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
												System.err.println(e.getMessage());
											}

											batchSizeCounter_tDBOutput_2 = 0;
										}
										commitCounter_tDBOutput_2++;

										if (commitEvery_tDBOutput_2 <= commitCounter_tDBOutput_2) {

											try {
												int countSum_tDBOutput_2 = 0;
												for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
													countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : 1);
												}
												rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
												insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
											} catch (java.sql.BatchUpdateException e) {
												globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
												int countSum_tDBOutput_2 = 0;
												for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
													countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0
															: countEach_tDBOutput_2);
												}
												rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;
												insertedCount_tDBOutput_2 += countSum_tDBOutput_2;
												System.err.println(e.getMessage());

											}
											if (rowsToCommitCount_tDBOutput_2 != 0) {
											}
											conn_tDBOutput_2.commit();
											if (rowsToCommitCount_tDBOutput_2 != 0) {
												rowsToCommitCount_tDBOutput_2 = 0;
											}
											commitCounter_tDBOutput_2 = 0;
										}

										tos_count_tDBOutput_2++;

										/**
										 * [tDBOutput_2 main ] stop
										 */

										/**
										 * [tDBOutput_2 process_data_begin ] start
										 */

										currentComponent = "tDBOutput_2";

										/**
										 * [tDBOutput_2 process_data_begin ] stop
										 */

										/**
										 * [tDBOutput_2 process_data_end ] start
										 */

										currentComponent = "tDBOutput_2";

										/**
										 * [tDBOutput_2 process_data_end ] stop
										 */

									} // End of branch "row3"

									/**
									 * [tUniqRow_1 process_data_end ] start
									 */

									currentComponent = "tUniqRow_1";

									/**
									 * [tUniqRow_1 process_data_end ] stop
									 */

								} // End of branch "dim_commande"

// Start of branch "dim_produit"
								if (dim_produit != null) {

									/**
									 * [tUniqRow_3 main ] start
									 */

									currentComponent = "tUniqRow_3";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "dim_produit"

										);
									}

									row5 = null;
									if (dim_produit.ID_produit == null) {
										finder_tUniqRow_3.ID_produit = null;
									} else {
										finder_tUniqRow_3.ID_produit = dim_produit.ID_produit.toLowerCase();
									}
									finder_tUniqRow_3.hashCodeDirty = true;
									if (!keystUniqRow_3.contains(finder_tUniqRow_3)) {
										KeyStruct_tUniqRow_3 new_tUniqRow_3 = new KeyStruct_tUniqRow_3();

										if (dim_produit.ID_produit == null) {
											new_tUniqRow_3.ID_produit = null;
										} else {
											new_tUniqRow_3.ID_produit = dim_produit.ID_produit.toLowerCase();
										}

										keystUniqRow_3.add(new_tUniqRow_3);
										if (row5 == null) {

											row5 = new row5Struct();
										}
										row5.ID_produit = dim_produit.ID_produit;
										row5.Categorie = dim_produit.Categorie;
										row5.Sous_categorie = dim_produit.Sous_categorie;
										row5.Nom_du_produit = dim_produit.Nom_du_produit;
										row5.prix = dim_produit.prix;
										row5.Remise = dim_produit.Remise;
										nb_uniques_tUniqRow_3++;
									} else {
										nb_duplicates_tUniqRow_3++;
									}

									tos_count_tUniqRow_3++;

									/**
									 * [tUniqRow_3 main ] stop
									 */

									/**
									 * [tUniqRow_3 process_data_begin ] start
									 */

									currentComponent = "tUniqRow_3";

									/**
									 * [tUniqRow_3 process_data_begin ] stop
									 */
// Start of branch "row5"
									if (row5 != null) {

										/**
										 * [tDBOutput_3 main ] start
										 */

										currentComponent = "tDBOutput_3";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row5"

											);
										}

										whetherReject_tDBOutput_3 = false;
										if (row5.ID_produit == null) {
											pstmt_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_3.setString(1, row5.ID_produit);
										}

										if (row5.Categorie == null) {
											pstmt_tDBOutput_3.setNull(2, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_3.setString(2, row5.Categorie);
										}

										if (row5.Sous_categorie == null) {
											pstmt_tDBOutput_3.setNull(3, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_3.setString(3, row5.Sous_categorie);
										}

										if (row5.Nom_du_produit == null) {
											pstmt_tDBOutput_3.setNull(4, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_3.setString(4, row5.Nom_du_produit);
										}

										if (row5.prix == null) {
											pstmt_tDBOutput_3.setNull(5, java.sql.Types.DOUBLE);
										} else {
											pstmt_tDBOutput_3.setDouble(5, row5.prix);
										}

										if (row5.Remise == null) {
											pstmt_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_3.setString(6, row5.Remise);
										}

										pstmt_tDBOutput_3.addBatch();
										nb_line_tDBOutput_3++;

										batchSizeCounter_tDBOutput_3++;
										if (batchSize_tDBOutput_3 <= batchSizeCounter_tDBOutput_3) {
											try {
												int countSum_tDBOutput_3 = 0;
												for (int countEach_tDBOutput_3 : pstmt_tDBOutput_3.executeBatch()) {
													countSum_tDBOutput_3 += (countEach_tDBOutput_3 == java.sql.Statement.EXECUTE_FAILED
															? 0
															: 1);
												}
												rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;
												insertedCount_tDBOutput_3 += countSum_tDBOutput_3;
											} catch (java.sql.BatchUpdateException e) {
												globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());
												int countSum_tDBOutput_3 = 0;
												for (int countEach_tDBOutput_3 : e.getUpdateCounts()) {
													countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0
															: countEach_tDBOutput_3);
												}
												rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;
												insertedCount_tDBOutput_3 += countSum_tDBOutput_3;
												System.err.println(e.getMessage());
											}

											batchSizeCounter_tDBOutput_3 = 0;
										}
										commitCounter_tDBOutput_3++;

										if (commitEvery_tDBOutput_3 <= commitCounter_tDBOutput_3) {

											try {
												int countSum_tDBOutput_3 = 0;
												for (int countEach_tDBOutput_3 : pstmt_tDBOutput_3.executeBatch()) {
													countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0 : 1);
												}
												rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;
												insertedCount_tDBOutput_3 += countSum_tDBOutput_3;
											} catch (java.sql.BatchUpdateException e) {
												globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());
												int countSum_tDBOutput_3 = 0;
												for (int countEach_tDBOutput_3 : e.getUpdateCounts()) {
													countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0
															: countEach_tDBOutput_3);
												}
												rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;
												insertedCount_tDBOutput_3 += countSum_tDBOutput_3;
												System.err.println(e.getMessage());

											}
											if (rowsToCommitCount_tDBOutput_3 != 0) {
											}
											conn_tDBOutput_3.commit();
											if (rowsToCommitCount_tDBOutput_3 != 0) {
												rowsToCommitCount_tDBOutput_3 = 0;
											}
											commitCounter_tDBOutput_3 = 0;
										}

										tos_count_tDBOutput_3++;

										/**
										 * [tDBOutput_3 main ] stop
										 */

										/**
										 * [tDBOutput_3 process_data_begin ] start
										 */

										currentComponent = "tDBOutput_3";

										/**
										 * [tDBOutput_3 process_data_begin ] stop
										 */

										/**
										 * [tDBOutput_3 process_data_end ] start
										 */

										currentComponent = "tDBOutput_3";

										/**
										 * [tDBOutput_3 process_data_end ] stop
										 */

									} // End of branch "row5"

									/**
									 * [tUniqRow_3 process_data_end ] start
									 */

									currentComponent = "tUniqRow_3";

									/**
									 * [tUniqRow_3 process_data_end ] stop
									 */

								} // End of branch "dim_produit"

// Start of branch "table_fait"
								if (table_fait != null) {

									/**
									 * [tDBOutput_4 main ] start
									 */

									currentComponent = "tDBOutput_4";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "table_fait"

										);
									}

									whetherReject_tDBOutput_4 = false;
									if (table_fait.ID_ligne == null) {
										pstmt_tDBOutput_4.setNull(1, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_4.setInt(1, table_fait.ID_ligne);
									}

									if (table_fait.ID_commande == null) {
										pstmt_tDBOutput_4.setNull(2, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_4.setString(2, table_fait.ID_commande);
									}

									if (table_fait.ID_client == null) {
										pstmt_tDBOutput_4.setNull(3, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_4.setString(3, table_fait.ID_client);
									}

									if (table_fait.ID_produit == null) {
										pstmt_tDBOutput_4.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_4.setString(4, table_fait.ID_produit);
									}

									if (table_fait.Montant_des_ventes == null) {
										pstmt_tDBOutput_4.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_4.setString(5, table_fait.Montant_des_ventes);
									}

									if (table_fait.Profit == null) {
										pstmt_tDBOutput_4.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_4.setString(6, table_fait.Profit);
									}

									if (table_fait.Quantite == null) {
										pstmt_tDBOutput_4.setNull(7, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_4.setInt(7, table_fait.Quantite);
									}

									pstmt_tDBOutput_4.addBatch();
									nb_line_tDBOutput_4++;

									batchSizeCounter_tDBOutput_4++;
									if (batchSize_tDBOutput_4 <= batchSizeCounter_tDBOutput_4) {
										try {
											int countSum_tDBOutput_4 = 0;
											for (int countEach_tDBOutput_4 : pstmt_tDBOutput_4.executeBatch()) {
												countSum_tDBOutput_4 += (countEach_tDBOutput_4 == java.sql.Statement.EXECUTE_FAILED
														? 0
														: 1);
											}
											rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;
											insertedCount_tDBOutput_4 += countSum_tDBOutput_4;
										} catch (java.sql.BatchUpdateException e) {
											globalMap.put("tDBOutput_4_ERROR_MESSAGE", e.getMessage());
											int countSum_tDBOutput_4 = 0;
											for (int countEach_tDBOutput_4 : e.getUpdateCounts()) {
												countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0
														: countEach_tDBOutput_4);
											}
											rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;
											insertedCount_tDBOutput_4 += countSum_tDBOutput_4;
											System.err.println(e.getMessage());
										}

										batchSizeCounter_tDBOutput_4 = 0;
									}
									commitCounter_tDBOutput_4++;

									if (commitEvery_tDBOutput_4 <= commitCounter_tDBOutput_4) {

										try {
											int countSum_tDBOutput_4 = 0;
											for (int countEach_tDBOutput_4 : pstmt_tDBOutput_4.executeBatch()) {
												countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0 : 1);
											}
											rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;
											insertedCount_tDBOutput_4 += countSum_tDBOutput_4;
										} catch (java.sql.BatchUpdateException e) {
											globalMap.put("tDBOutput_4_ERROR_MESSAGE", e.getMessage());
											int countSum_tDBOutput_4 = 0;
											for (int countEach_tDBOutput_4 : e.getUpdateCounts()) {
												countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0
														: countEach_tDBOutput_4);
											}
											rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;
											insertedCount_tDBOutput_4 += countSum_tDBOutput_4;
											System.err.println(e.getMessage());

										}
										if (rowsToCommitCount_tDBOutput_4 != 0) {
										}
										conn_tDBOutput_4.commit();
										if (rowsToCommitCount_tDBOutput_4 != 0) {
											rowsToCommitCount_tDBOutput_4 = 0;
										}
										commitCounter_tDBOutput_4 = 0;
									}

									tos_count_tDBOutput_4++;

									/**
									 * [tDBOutput_4 main ] stop
									 */

									/**
									 * [tDBOutput_4 process_data_begin ] start
									 */

									currentComponent = "tDBOutput_4";

									/**
									 * [tDBOutput_4 process_data_begin ] stop
									 */

									/**
									 * [tDBOutput_4 process_data_end ] start
									 */

									currentComponent = "tDBOutput_4";

									/**
									 * [tDBOutput_4 process_data_end ] stop
									 */

								} // End of branch "table_fait"

								/**
								 * [tMap_1 process_data_end ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputExcel_1 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_1 end ] start
							 */

							currentComponent = "tFileInputExcel_1";

						}

						globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);

					}

				} finally {

					if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_1.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row2 != null) {
					tHash_Lookup_row2.endGet();
				}
				globalMap.remove("tHash_Lookup_row2");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tUniqRow_2 end ] start
				 */

				currentComponent = "tUniqRow_2";

				globalMap.put("tUniqRow_2_NB_UNIQUES", nb_uniques_tUniqRow_2);
				globalMap.put("tUniqRow_2_NB_DUPLICATES", nb_duplicates_tUniqRow_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "dim_client");
				}

				ok_Hash.put("tUniqRow_2", true);
				end_Hash.put("tUniqRow_2", System.currentTimeMillis());

				/**
				 * [tUniqRow_2 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (batchSizeCounter_tDBOutput_1 != 0) {
						int countSum_tDBOutput_1 = 0;

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

						insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					}
				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_1 = 0;

				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");

				}

				resourceMap.put("statementClosed_tDBOutput_1", true);

				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;

				conn_tDBOutput_1.close();

				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

				/**
				 * [tUniqRow_1 end ] start
				 */

				currentComponent = "tUniqRow_1";

				globalMap.put("tUniqRow_1_NB_UNIQUES", nb_uniques_tUniqRow_1);
				globalMap.put("tUniqRow_1_NB_DUPLICATES", nb_duplicates_tUniqRow_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "dim_commande");
				}

				ok_Hash.put("tUniqRow_1", true);
				end_Hash.put("tUniqRow_1", System.currentTimeMillis());

				/**
				 * [tUniqRow_1 end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (batchSizeCounter_tDBOutput_2 != 0) {
						int countSum_tDBOutput_2 = 0;

						for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
							countSum_tDBOutput_2 += (countEach_tDBOutput_2 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

						insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

					}
				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_2 = 0;
					for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
						countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
					}
					rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

					insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_2 = 0;

				if (pstmt_tDBOutput_2 != null) {

					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");

				}

				resourceMap.put("statementClosed_tDBOutput_2", true);

				if (commitCounter_tDBOutput_2 > 0 && rowsToCommitCount_tDBOutput_2 != 0) {

				}
				conn_tDBOutput_2.commit();
				if (commitCounter_tDBOutput_2 > 0 && rowsToCommitCount_tDBOutput_2 != 0) {

					rowsToCommitCount_tDBOutput_2 = 0;
				}
				commitCounter_tDBOutput_2 = 0;

				conn_tDBOutput_2.close();

				resourceMap.put("finish_tDBOutput_2", true);

				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

				/**
				 * [tUniqRow_3 end ] start
				 */

				currentComponent = "tUniqRow_3";

				globalMap.put("tUniqRow_3_NB_UNIQUES", nb_uniques_tUniqRow_3);
				globalMap.put("tUniqRow_3_NB_DUPLICATES", nb_duplicates_tUniqRow_3);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "dim_produit");
				}

				ok_Hash.put("tUniqRow_3", true);
				end_Hash.put("tUniqRow_3", System.currentTimeMillis());

				/**
				 * [tUniqRow_3 end ] stop
				 */

				/**
				 * [tDBOutput_3 end ] start
				 */

				currentComponent = "tDBOutput_3";

				try {
					if (batchSizeCounter_tDBOutput_3 != 0) {
						int countSum_tDBOutput_3 = 0;

						for (int countEach_tDBOutput_3 : pstmt_tDBOutput_3.executeBatch()) {
							countSum_tDBOutput_3 += (countEach_tDBOutput_3 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;

						insertedCount_tDBOutput_3 += countSum_tDBOutput_3;

					}
				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_3 = 0;
					for (int countEach_tDBOutput_3 : e.getUpdateCounts()) {
						countSum_tDBOutput_3 += (countEach_tDBOutput_3 < 0 ? 0 : countEach_tDBOutput_3);
					}
					rowsToCommitCount_tDBOutput_3 += countSum_tDBOutput_3;

					insertedCount_tDBOutput_3 += countSum_tDBOutput_3;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_3 = 0;

				if (pstmt_tDBOutput_3 != null) {

					pstmt_tDBOutput_3.close();
					resourceMap.remove("pstmt_tDBOutput_3");

				}

				resourceMap.put("statementClosed_tDBOutput_3", true);

				if (commitCounter_tDBOutput_3 > 0 && rowsToCommitCount_tDBOutput_3 != 0) {

				}
				conn_tDBOutput_3.commit();
				if (commitCounter_tDBOutput_3 > 0 && rowsToCommitCount_tDBOutput_3 != 0) {

					rowsToCommitCount_tDBOutput_3 = 0;
				}
				commitCounter_tDBOutput_3 = 0;

				conn_tDBOutput_3.close();

				resourceMap.put("finish_tDBOutput_3", true);

				nb_line_deleted_tDBOutput_3 = nb_line_deleted_tDBOutput_3 + deletedCount_tDBOutput_3;
				nb_line_update_tDBOutput_3 = nb_line_update_tDBOutput_3 + updatedCount_tDBOutput_3;
				nb_line_inserted_tDBOutput_3 = nb_line_inserted_tDBOutput_3 + insertedCount_tDBOutput_3;
				nb_line_rejected_tDBOutput_3 = nb_line_rejected_tDBOutput_3 + rejectedCount_tDBOutput_3;

				globalMap.put("tDBOutput_3_NB_LINE", nb_line_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_UPDATED", nb_line_update_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_DELETED", nb_line_deleted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_3);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tDBOutput_3", true);
				end_Hash.put("tDBOutput_3", System.currentTimeMillis());

				/**
				 * [tDBOutput_3 end ] stop
				 */

				/**
				 * [tDBOutput_4 end ] start
				 */

				currentComponent = "tDBOutput_4";

				try {
					if (batchSizeCounter_tDBOutput_4 != 0) {
						int countSum_tDBOutput_4 = 0;

						for (int countEach_tDBOutput_4 : pstmt_tDBOutput_4.executeBatch()) {
							countSum_tDBOutput_4 += (countEach_tDBOutput_4 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;

						insertedCount_tDBOutput_4 += countSum_tDBOutput_4;

					}
				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_4 = 0;
					for (int countEach_tDBOutput_4 : e.getUpdateCounts()) {
						countSum_tDBOutput_4 += (countEach_tDBOutput_4 < 0 ? 0 : countEach_tDBOutput_4);
					}
					rowsToCommitCount_tDBOutput_4 += countSum_tDBOutput_4;

					insertedCount_tDBOutput_4 += countSum_tDBOutput_4;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_4 = 0;

				if (pstmt_tDBOutput_4 != null) {

					pstmt_tDBOutput_4.close();
					resourceMap.remove("pstmt_tDBOutput_4");

				}

				resourceMap.put("statementClosed_tDBOutput_4", true);

				if (commitCounter_tDBOutput_4 > 0 && rowsToCommitCount_tDBOutput_4 != 0) {

				}
				conn_tDBOutput_4.commit();
				if (commitCounter_tDBOutput_4 > 0 && rowsToCommitCount_tDBOutput_4 != 0) {

					rowsToCommitCount_tDBOutput_4 = 0;
				}
				commitCounter_tDBOutput_4 = 0;

				conn_tDBOutput_4.close();

				resourceMap.put("finish_tDBOutput_4", true);

				nb_line_deleted_tDBOutput_4 = nb_line_deleted_tDBOutput_4 + deletedCount_tDBOutput_4;
				nb_line_update_tDBOutput_4 = nb_line_update_tDBOutput_4 + updatedCount_tDBOutput_4;
				nb_line_inserted_tDBOutput_4 = nb_line_inserted_tDBOutput_4 + insertedCount_tDBOutput_4;
				nb_line_rejected_tDBOutput_4 = nb_line_rejected_tDBOutput_4 + rejectedCount_tDBOutput_4;

				globalMap.put("tDBOutput_4_NB_LINE", nb_line_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_UPDATED", nb_line_update_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_DELETED", nb_line_deleted_tDBOutput_4);
				globalMap.put("tDBOutput_4_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_4);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "table_fait");
				}

				ok_Hash.put("tDBOutput_4", true);
				end_Hash.put("tDBOutput_4", System.currentTimeMillis());

				/**
				 * [tDBOutput_4 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row2");

			try {

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tUniqRow_2 finally ] start
				 */

				currentComponent = "tUniqRow_2";

				/**
				 * [tUniqRow_2 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

				/**
				 * [tUniqRow_1 finally ] start
				 */

				currentComponent = "tUniqRow_1";

				/**
				 * [tUniqRow_1 finally ] stop
				 */

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
						if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_2")) != null) {
							pstmtToClose_tDBOutput_2.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_2") == null) {
						java.sql.Connection ctn_tDBOutput_2 = null;
						if ((ctn_tDBOutput_2 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_2")) != null) {
							try {
								ctn_tDBOutput_2.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_2) {
								String errorMessage_tDBOutput_2 = "failed to close the connection in tDBOutput_2 :"
										+ sqlEx_tDBOutput_2.getMessage();
								System.err.println(errorMessage_tDBOutput_2);
							}
						}
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

				/**
				 * [tUniqRow_3 finally ] start
				 */

				currentComponent = "tUniqRow_3";

				/**
				 * [tUniqRow_3 finally ] stop
				 */

				/**
				 * [tDBOutput_3 finally ] start
				 */

				currentComponent = "tDBOutput_3";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_3") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_3 = null;
						if ((pstmtToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_3")) != null) {
							pstmtToClose_tDBOutput_3.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_3") == null) {
						java.sql.Connection ctn_tDBOutput_3 = null;
						if ((ctn_tDBOutput_3 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_3")) != null) {
							try {
								ctn_tDBOutput_3.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_3) {
								String errorMessage_tDBOutput_3 = "failed to close the connection in tDBOutput_3 :"
										+ sqlEx_tDBOutput_3.getMessage();
								System.err.println(errorMessage_tDBOutput_3);
							}
						}
					}
				}

				/**
				 * [tDBOutput_3 finally ] stop
				 */

				/**
				 * [tDBOutput_4 finally ] start
				 */

				currentComponent = "tDBOutput_4";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_4") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_4 = null;
						if ((pstmtToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_4")) != null) {
							pstmtToClose_tDBOutput_4.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_4") == null) {
						java.sql.Connection ctn_tDBOutput_4 = null;
						if ((ctn_tDBOutput_4 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_4")) != null) {
							try {
								ctn_tDBOutput_4.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_4) {
								String errorMessage_tDBOutput_4 = "failed to close the connection in tDBOutput_4 :"
										+ sqlEx_tDBOutput_4.getMessage();
								System.err.println(errorMessage_tDBOutput_4);
							}
						}
					}
				}

				/**
				 * [tDBOutput_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
		final static byte[] commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		static byte[] commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		public Boolean CategorieIsNullable() {
			return true;
		}

		public Boolean CategorieIsKey() {
			return false;
		}

		public Integer CategorieLength() {
			return 23;
		}

		public Integer CategoriePrecision() {
			return 0;
		}

		public String CategorieDefault() {

			return null;

		}

		public String CategorieComment() {

			return "";

		}

		public String CategoriePattern() {

			return "dd-MM-yyyy";

		}

		public String CategorieOriginalDbColumnName() {

			return "Categorie";

		}

		public String Date_de_commande;

		public String getDate_de_commande() {
			return this.Date_de_commande;
		}

		public Boolean Date_de_commandeIsNullable() {
			return true;
		}

		public Boolean Date_de_commandeIsKey() {
			return false;
		}

		public Integer Date_de_commandeLength() {
			return 28;
		}

		public Integer Date_de_commandePrecision() {
			return 0;
		}

		public String Date_de_commandeDefault() {

			return null;

		}

		public String Date_de_commandeComment() {

			return "";

		}

		public String Date_de_commandePattern() {

			return "dd-MM-yyyy";

		}

		public String Date_de_commandeOriginalDbColumnName() {

			return "Date_de_commande";

		}

		public String Segment;

		public String getSegment() {
			return this.Segment;
		}

		public Boolean SegmentIsNullable() {
			return true;
		}

		public Boolean SegmentIsKey() {
			return false;
		}

		public Integer SegmentLength() {
			return 23;
		}

		public Integer SegmentPrecision() {
			return 0;
		}

		public String SegmentDefault() {

			return null;

		}

		public String SegmentComment() {

			return "";

		}

		public String SegmentPattern() {

			return "dd-MM-yyyy";

		}

		public String SegmentOriginalDbColumnName() {

			return "Segment";

		}

		public Integer Objectif_de_vente;

		public Integer getObjectif_de_vente() {
			return this.Objectif_de_vente;
		}

		public Boolean Objectif_de_venteIsNullable() {
			return true;
		}

		public Boolean Objectif_de_venteIsKey() {
			return false;
		}

		public Integer Objectif_de_venteLength() {
			return 4;
		}

		public Integer Objectif_de_ventePrecision() {
			return 0;
		}

		public String Objectif_de_venteDefault() {

			return null;

		}

		public String Objectif_de_venteComment() {

			return "";

		}

		public String Objectif_de_ventePattern() {

			return "dd-MM-yyyy";

		}

		public String Objectif_de_venteOriginalDbColumnName() {

			return "Objectif_de_vente";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Categorie == null) ? 0 : this.Categorie.hashCode());

				result = prime * result + ((this.Date_de_commande == null) ? 0 : this.Date_de_commande.hashCode());

				result = prime * result + ((this.Segment == null) ? 0 : this.Segment.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row2Struct other = (row2Struct) obj;

			if (this.Categorie == null) {
				if (other.Categorie != null)
					return false;

			} else if (!this.Categorie.equals(other.Categorie))

				return false;

			if (this.Date_de_commande == null) {
				if (other.Date_de_commande != null)
					return false;

			} else if (!this.Date_de_commande.equals(other.Date_de_commande))

				return false;

			if (this.Segment == null) {
				if (other.Segment != null)
					return false;

			} else if (!this.Segment.equals(other.Segment))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.Categorie = this.Categorie;
			other.Date_de_commande = this.Date_de_commande;
			other.Segment = this.Segment;
			other.Objectif_de_vente = this.Objectif_de_vente;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.Categorie = this.Categorie;
			other.Date_de_commande = this.Date_de_commande;
			other.Segment = this.Segment;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PIPELINE_TALEND_Talend_pipeline.length) {
					if (length < 1024 && commonByteArray_PIPELINE_TALEND_Talend_pipeline.length == 0) {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[1024];
					} else {
						commonByteArray_PIPELINE_TALEND_Talend_pipeline = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length);
				strReturn = new String(commonByteArray_PIPELINE_TALEND_Talend_pipeline, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.Categorie = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Segment = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PIPELINE_TALEND_Talend_pipeline) {

				try {

					int length = 0;

					this.Categorie = readString(dis);

					this.Date_de_commande = readString(dis);

					this.Segment = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Segment, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Categorie, dos);

				// String

				writeString(this.Date_de_commande, dos);

				// String

				writeString(this.Segment, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.Objectif_de_vente = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Objectif_de_vente = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.Objectif_de_vente, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.Objectif_de_vente, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Categorie=" + Categorie);
			sb.append(",Date_de_commande=" + Date_de_commande);
			sb.append(",Segment=" + Segment);
			sb.append(",Objectif_de_vente=" + String.valueOf(Objectif_de_vente));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Categorie, other.Categorie);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Date_de_commande, other.Date_de_commande);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Segment, other.Segment);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedHash_row2 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row2", false);
				start_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tAdvancedHash_row2 = 0;

				// connection name:row2
				// source node:tFileInputExcel_2 - inputs:(after_tFileInputExcel_1)
				// outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2)
				// outputs:()
				// linked node: tMap_1 - inputs:(row1,row2)
				// outputs:(dim_client,dim_commande,dim_produit,table_fait)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedHash_row2 begin ] stop
				 */

				/**
				 * [tFileInputExcel_2 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_2", false);
				start_Hash.put("tFileInputExcel_2", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_2";

				int tos_count_tFileInputExcel_2 = 0;

				final String decryptedPassword_tFileInputExcel_2 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:P1YFg3c/SNJUD4Gq68cTKooCSn2zT5KHF4L6lA==");
				String password_tFileInputExcel_2 = decryptedPassword_tFileInputExcel_2;
				if (password_tFileInputExcel_2.isEmpty()) {
					password_tFileInputExcel_2 = null;
				}
				class RegexUtil_tFileInputExcel_2 {

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

						if (useRegex) {// this part process the regex issue

							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
								String sheetName = sheet.getSheetName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									if (sheet != null) {
										list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
									}
								}
							}

						} else {
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
								.getSheetAt(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}
				RegexUtil_tFileInputExcel_2 regexUtil_tFileInputExcel_2 = new RegexUtil_tFileInputExcel_2();

				Object source_tFileInputExcel_2 = "C:/Data/Objectifs_ventes.xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_2 = null;

				if (source_tFileInputExcel_2 instanceof String) {
					workbook_tFileInputExcel_2 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_2), password_tFileInputExcel_2,
									true);
				} else if (source_tFileInputExcel_2 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_2 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_2, password_tFileInputExcel_2);
				} else {
					workbook_tFileInputExcel_2 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_2 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.ss.usermodel.Sheet sheet_tFileInputExcel_2 : workbook_tFileInputExcel_2) {
						sheetList_tFileInputExcel_2
								.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet_tFileInputExcel_2);
					}
					if (sheetList_tFileInputExcel_2.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_2 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_2 : sheetList_tFileInputExcel_2) {
						if (sheet_FilterNull_tFileInputExcel_2 != null
								&& sheetList_FilterNull_tFileInputExcel_2.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_2.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_2.add(sheet_FilterNull_tFileInputExcel_2);
						}
					}
					sheetList_tFileInputExcel_2 = sheetList_FilterNull_tFileInputExcel_2;
					if (sheetList_tFileInputExcel_2.size() > 0) {
						int nb_line_tFileInputExcel_2 = 0;

						int begin_line_tFileInputExcel_2 = 1;

						int footer_input_tFileInputExcel_2 = 0;

						int end_line_tFileInputExcel_2 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_2 : sheetList_tFileInputExcel_2) {
							end_line_tFileInputExcel_2 += (sheet_tFileInputExcel_2.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_2 -= footer_input_tFileInputExcel_2;
						int limit_tFileInputExcel_2 = -1;
						int start_column_tFileInputExcel_2 = 1 - 1;
						int end_column_tFileInputExcel_2 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_2 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_2 = sheetList_tFileInputExcel_2
								.get(0);
						int rowCount_tFileInputExcel_2 = 0;
						int sheetIndex_tFileInputExcel_2 = 0;
						int currentRows_tFileInputExcel_2 = (sheetList_tFileInputExcel_2.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_2 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_2 = df_tFileInputExcel_2.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_2 = begin_line_tFileInputExcel_2; i_tFileInputExcel_2 < end_line_tFileInputExcel_2; i_tFileInputExcel_2++) {

							int emptyColumnCount_tFileInputExcel_2 = 0;

							if (limit_tFileInputExcel_2 != -1 && nb_line_tFileInputExcel_2 >= limit_tFileInputExcel_2) {
								break;
							}

							while (i_tFileInputExcel_2 >= rowCount_tFileInputExcel_2 + currentRows_tFileInputExcel_2) {
								rowCount_tFileInputExcel_2 += currentRows_tFileInputExcel_2;
								sheet_tFileInputExcel_2 = sheetList_tFileInputExcel_2
										.get(++sheetIndex_tFileInputExcel_2);
								currentRows_tFileInputExcel_2 = (sheet_tFileInputExcel_2.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_2_CURRENT_SHEET", sheet_tFileInputExcel_2.getSheetName());
							if (rowCount_tFileInputExcel_2 <= i_tFileInputExcel_2) {
								row_tFileInputExcel_2 = sheet_tFileInputExcel_2
										.getRow(i_tFileInputExcel_2 - rowCount_tFileInputExcel_2);
							}
							row2 = null;
							row2 = null;
							int tempRowLength_tFileInputExcel_2 = 4;

							int columnIndex_tFileInputExcel_2 = 0;

							String[] temp_row_tFileInputExcel_2 = new String[tempRowLength_tFileInputExcel_2];
							int excel_end_column_tFileInputExcel_2;
							if (row_tFileInputExcel_2 == null) {
								excel_end_column_tFileInputExcel_2 = 0;
							} else {
								excel_end_column_tFileInputExcel_2 = row_tFileInputExcel_2.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_2;
							if (end_column_tFileInputExcel_2 == -1) {
								actual_end_column_tFileInputExcel_2 = excel_end_column_tFileInputExcel_2;
							} else {
								actual_end_column_tFileInputExcel_2 = end_column_tFileInputExcel_2 > excel_end_column_tFileInputExcel_2
										? excel_end_column_tFileInputExcel_2
										: end_column_tFileInputExcel_2;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_2 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_2; i++) {
								if (i + start_column_tFileInputExcel_2 < actual_end_column_tFileInputExcel_2) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_2 = row_tFileInputExcel_2
											.getCell(i + start_column_tFileInputExcel_2);
									if (cell_tFileInputExcel_2 != null) {
										switch (cell_tFileInputExcel_2.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_2[i] = cell_tFileInputExcel_2
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_2)) {
												temp_row_tFileInputExcel_2[i] = cell_tFileInputExcel_2
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_2[i] = df_tFileInputExcel_2
														.format(cell_tFileInputExcel_2.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_2[i] = String
													.valueOf(cell_tFileInputExcel_2.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_2.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_2[i] = cell_tFileInputExcel_2
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_2)) {
													temp_row_tFileInputExcel_2[i] = cell_tFileInputExcel_2
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_2 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_2.getNumericCellValue());
													temp_row_tFileInputExcel_2[i] = ne_tFileInputExcel_2
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_2[i] = String
														.valueOf(cell_tFileInputExcel_2.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_2[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_2[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_2[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_2[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_2 = false;
							row2 = new row2Struct();
							int curColNum_tFileInputExcel_2 = -1;
							String curColName_tFileInputExcel_2 = "";
							try {
								columnIndex_tFileInputExcel_2 = 0;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Categorie";

									row2.Categorie = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row2.Categorie = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 1;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Date_de_commande";

									row2.Date_de_commande = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row2.Date_de_commande = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 2;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Segment";

									row2.Segment = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row2.Segment = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 3;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Objectif_de_vente";

									row2.Objectif_de_vente = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
											'.' == decimalChar_tFileInputExcel_2 ? null
													: decimalChar_tFileInputExcel_2));
								} else {
									row2.Objectif_de_vente = null;
									emptyColumnCount_tFileInputExcel_2++;
								}

								nb_line_tFileInputExcel_2++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_2_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_2 = true;
								System.err.println(e.getMessage());
								row2 = null;
							}

							/**
							 * [tFileInputExcel_2 begin ] stop
							 */

							/**
							 * [tFileInputExcel_2 main ] start
							 */

							currentComponent = "tFileInputExcel_2";

							tos_count_tFileInputExcel_2++;

							/**
							 * [tFileInputExcel_2 main ] stop
							 */

							/**
							 * [tFileInputExcel_2 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_2";

							/**
							 * [tFileInputExcel_2 process_data_begin ] stop
							 */
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tAdvancedHash_row2 main ] start
								 */

								currentComponent = "tAdvancedHash_row2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

									);
								}

								row2Struct row2_HashRow = new row2Struct();

								row2_HashRow.Categorie = row2.Categorie;

								row2_HashRow.Date_de_commande = row2.Date_de_commande;

								row2_HashRow.Segment = row2.Segment;

								row2_HashRow.Objectif_de_vente = row2.Objectif_de_vente;

								tHash_Lookup_row2.put(row2_HashRow);

								tos_count_tAdvancedHash_row2++;

								/**
								 * [tAdvancedHash_row2 main ] stop
								 */

								/**
								 * [tAdvancedHash_row2 process_data_begin ] start
								 */

								currentComponent = "tAdvancedHash_row2";

								/**
								 * [tAdvancedHash_row2 process_data_begin ] stop
								 */

								/**
								 * [tAdvancedHash_row2 process_data_end ] start
								 */

								currentComponent = "tAdvancedHash_row2";

								/**
								 * [tAdvancedHash_row2 process_data_end ] stop
								 */

							} // End of branch "row2"

							/**
							 * [tFileInputExcel_2 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_2";

							/**
							 * [tFileInputExcel_2 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_2 end ] start
							 */

							currentComponent = "tFileInputExcel_2";

						}

						globalMap.put("tFileInputExcel_2_NB_LINE", nb_line_tFileInputExcel_2);

					}

				} finally {

					if (!(source_tFileInputExcel_2 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_2.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_2", true);
				end_Hash.put("tFileInputExcel_2", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row2 end ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				tHash_Lookup_row2.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tAdvancedHash_row2", true);
				end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputExcel_2 finally ] start
				 */

				currentComponent = "tFileInputExcel_2";

				/**
				 * [tFileInputExcel_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row2 finally ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				/**
				 * [tAdvancedHash_row2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_2_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	private final static java.util.Properties jobInfo = new java.util.Properties();
	private final static java.util.Map<String, String> mdcInfo = new java.util.HashMap<>();
	private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();

	public static void main(String[] args) {
		final Talend_pipeline Talend_pipelineClass = new Talend_pipeline();

		int exitCode = Talend_pipelineClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if (path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
		readJobInfo(new java.io.File(BUILD_PATH));
	}

	private void readJobInfo(java.io.File jobInfoFile) {

		if (jobInfoFile.exists()) {
			try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
				jobInfo.load(is);
			} catch (IOException e) {

			}
		}
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}

		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		try {
			java.util.Dictionary<String, Object> jobProperties = null;
			if (inOSGi) {
				jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

				if (jobProperties != null && jobProperties.get("context") != null) {
					contextStr = (String) jobProperties.get("context");
				}
			}
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Talend_pipeline.class.getClassLoader()
					.getResourceAsStream("pipeline_talend/talend_pipeline_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Talend_pipeline.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						if (inOSGi && jobProperties != null) {
							java.util.Enumeration<String> keys = jobProperties.keys();
							while (keys.hasMoreElements()) {
								String propKey = keys.nextElement();
								if (defaultProps.containsKey(propKey)) {
									defaultProps.put(propKey, (String) jobProperties.get(propKey));
								}
							}
						}
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, ContextProperties.class, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputExcel_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_1) {
			globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Talend_pipeline");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");
		resumeUtil.flush();

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--context_file")) {
			String keyValue = arg.substring(15);
			String filePath = new String(java.util.Base64.getDecoder().decode(keyValue));
			java.nio.file.Path contextFile = java.nio.file.Paths.get(filePath);
			try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(contextFile)) {
				String line;
				while ((line = reader.readLine()) != null) {
					int index = -1;
					if ((index = line.indexOf('=')) > -1) {
						if (line.startsWith("--context_param")) {
							if ("id_Password".equals(context_param.getContextType(line.substring(16, index)))) {
								context_param.put(line.substring(16, index),
										routines.system.PasswordEncryptUtil.decryptPassword(line.substring(index + 1)));
							} else {
								context_param.put(line.substring(16, index), line.substring(index + 1));
							}
						} else {// --context_type
							context_param.setContextType(line.substring(15, index), line.substring(index + 1));
						}
					}
				}
			} catch (java.io.IOException e) {
				System.err.println("Could not load the context file: " + filePath);
				e.printStackTrace();
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 298176 characters generated by Talend Open Studio for Data Integration on the
 * 23 mars 2025, 12:59:24 CET
 ************************************************************************************************/