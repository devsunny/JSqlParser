package com.asksunny.tools;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.asksunny.tools.domain.schema.EntityDef;
import com.asksunny.tools.domain.schema.FieldDef;
import com.asksunny.tools.spring.gen.SchemaCreateStatementVisitor;

public class SpringMVCGenerator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String baseDir = ".";
	String basePackage = null;
	String domainPackage = "domain";
	String persistencePackage = "persistence";
	String servicePackage = "service";
	String controllerPackage = "controller";
	String mapperPackage = "mapper";

	File ddlFile = null;
	public static final String TABSPACE1 = "    ";
	public static final String TABSPACE2 = "        ";
	public static final String TABSPACE3 = "            ";

	public SpringMVCGenerator() {
	}

	public void generateEntitysFromFile(File pathToDDL) throws Exception {
		String sql = IOUtils.toString(pathToDDL.toURI());
		generateEntitySets(sql);
	}

	public String getBaseDir() {
		return baseDir;
	}

	public SpringMVCGenerator setBaseDir(String baseDir) {
		this.baseDir = baseDir;
		return this;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public SpringMVCGenerator setBasePackage(String basePackage) {
		this.basePackage = basePackage;
		return this;
	}

	public String getDomainPackage() {
		return domainPackage;
	}

	public SpringMVCGenerator setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
		return this;
	}

	public String getPersistencePackage() {
		return persistencePackage;
	}

	public SpringMVCGenerator setPersistencePackage(String persistencePackage) {
		this.persistencePackage = persistencePackage;
		return this;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public SpringMVCGenerator setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
		return this;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public SpringMVCGenerator setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
		return this;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public SpringMVCGenerator setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
		return this;
	}

	public File getDdlFile() {
		return ddlFile;
	}

	public SpringMVCGenerator setDdlFile(File ddlFile) {
		this.ddlFile = ddlFile;
		return this;
	}

	public void generateEntitySets(String ddlcontent) throws Exception {
		File based = new File(this.baseDir);
		if (!based.exists()) {
			if (!based.mkdirs()) {
				throw new RuntimeException(
						"Permission denied to create directory:" + this.baseDir);
			}
		}
		String pkgDir = basePackage.replaceAll("\\.", "/");
		File pkgbased = new File(based, pkgDir);
		if (!pkgbased.exists()) {
			if (!pkgbased.mkdirs()) {
				throw new RuntimeException(
						"Permission denied to create directory:" + pkgbased);
			}
		}
		File domaind = new File(based, domainPackage);
		if (!domaind.exists()) {
			if (!domaind.mkdirs()) {
				throw new RuntimeException(
						"Permission denied to create directory:" + domaind);
			}
		}
		File persistencePackageD = new File(based, persistencePackage);
		if (!persistencePackageD.exists()) {
			if (!persistencePackageD.mkdirs()) {
				throw new RuntimeException(
						"Permission denied to create directory:"
								+ persistencePackageD);
			}
		}
		Statements stmts = CCJSqlParserUtil.parseStatements(ddlcontent);
		SchemaCreateStatementVisitor entityCollector = new SchemaCreateStatementVisitor();
		for (Statement stmt : stmts.getStatements()) {
			stmt.accept(entityCollector);
		}
		
		List<EntityDef> entities = entityCollector.getEntities();
		for (EntityDef entityDef : entities) {
			generateEntity(entityDef, domaind, persistencePackageD);
		}
		

	}

	public void generateEntity(EntityDef entityDef, File domaind, File persistencePackageD) throws Exception {
		StringBuilder entityBuf = new StringBuilder();
		
		
		StringBuilder fieldBuf = new StringBuilder();
		StringBuilder accessorBuf = new StringBuilder();

		JavaIdentifierTransformer entityNametransformer = new JavaIdentifierTransformer(
				entityDef.getName());
		entityBuf.append(String.format(
				"public class %s implements Serializable\n{\n\n",
				entityNametransformer.getAccessorName()));
		entityBuf.append(TABSPACE1).append(
				"private static final long serialVersionUID = 1L;\n");
		List<FieldDef> fields = entityDef.getFields();
		List<FieldDef> primaryKeys = new ArrayList<>();
		for (FieldDef fieldDef : fields) {
			String typeName = fieldDef.getTypename() == null ? JdbcJavaTypeMappings
					.getJavaType(fieldDef.getJdbcType()) : JdbcJavaTypeMappings
					.getJavaType(fieldDef.getTypename());
			JavaIdentifierTransformer transformer = new JavaIdentifierTransformer(
					fieldDef.getName());
			String tmp = String.format("private %s %s;", typeName,
					transformer.getVarName());
			fieldBuf.append(TABSPACE1).append(tmp).append("\n");
			String tmp2 = String.format(
					"public %s get%s(){ return this.%s; }", typeName,
					transformer.getAccessorName(), transformer.getVarName());
			String tmp3 = String.format(
					"public void set%1$s(%2$s %3$s){ this.%3$s = %3$s; }",
					transformer.getAccessorName(), typeName,
					transformer.getVarName());
			accessorBuf.append(TABSPACE1).append(tmp2).append("\n");
			accessorBuf.append(TABSPACE1).append(tmp3).append("\n");
			if (fieldDef.isPrimaryKey())
				primaryKeys.add(fieldDef);
		}
		entityBuf.append(fieldBuf).append("\n\n");
		entityBuf.append(accessorBuf).append("\n\n");
		entityBuf.append("}").append("\n\n");
		entityBuf.insert(0, String.format("package %s.%s;\n\n\n", this.basePackage, this.domainPackage));
		File domainF = new File(domaind, String.format("%s.java", entityNametransformer.getAccessorName()));
		FileUtils.write(domainF, entityBuf);
		
		
		
		StringBuilder persistBuf = new StringBuilder();
		persistBuf.append(String.format("package %s.%s;\n\n\n", this.basePackage, this.persistencePackage));
		persistBuf.append(String.format("import %s.%s.%s;\n\n\n", this.basePackage, this.domainPackage, entityNametransformer.getAccessorName()));
		
		persistBuf.append(String.format("public interface %sMapper {\n\n\n", entityNametransformer.getAccessorName()));
		
		StringBuilder persistXml = new StringBuilder();
		
		String tmpl = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">";
		persistXml.append(tmpl);
		persistXml.append(String.format("\n<mapper namespace=\"%s.%s.%sMapper\">\n\n", this.basePackage, this.persistencePackage, entityNametransformer.getAccessorName()));
		
		
		
		StringBuilder select = new StringBuilder();
		StringBuilder insert = new StringBuilder();
		StringBuilder insertValue = new StringBuilder();
		StringBuilder delete = new StringBuilder();
		StringBuilder update = new StringBuilder();
		StringBuilder fullWhere = new StringBuilder();
		
		select.append("SELECT ");
		insert.append("INSERT INTO ").append(entityNametransformer.getDbName())
				.append(" (");
		insertValue.append(" VALUES ( ");
		update.append("UPATE ").append(entityNametransformer.getDbName())
				.append(" SET ");
		delete.append("DELETE FROM  ")
				.append(entityNametransformer.getDbName());
		fullWhere.append(" WHERE ");
		
		
		
		int size = fields.size();
		for (int i = 0; i < size; i++) {
			FieldDef fieldDef = fields.get(i);
			JavaIdentifierTransformer transformer = new JavaIdentifierTransformer(
					fieldDef.getName());
			select.append(transformer.getDbName()).append(" AS ")
					.append(transformer.getVarName());
			insert.append(transformer.getDbName());
			insertValue
					.append(String.format("${%s}", transformer.getVarName()));
			update.append(String.format("%s=${%s}", transformer.getDbName(),
					transformer.getVarName()));

			fullWhere.append(String.format("%s=${%s}", transformer.getDbName(),
					transformer.getVarName()));

			if (i < size - 1) {
				select.append(",");
				update.append(",");
				insert.append(",");
				insertValue.append(",");
				fullWhere.append(" AND ");
			} else {
				insert.append(")");
				insertValue.append(")");
				update.append(" ");
			}
			select.append(" ");
			insert.append(" ");
			update.append(" ");
			insertValue.append(" ");
		}
		insert.append(insertValue);
		select.append("FROM ").append(entityNametransformer.getDbName());
		
		
		persistBuf.append(TABSPACE1).append(String.format("List<%1$s> getAll%1$s();\n\n", entityNametransformer.getAccessorName()));
		persistBuf.append(TABSPACE1).append(String.format("void insert%1$s(%1$s %2$s);\n\n", entityNametransformer.getAccessorName(), entityNametransformer.getVarName()));
		
		
		persistXml.append(TABSPACE1).append(String.format("<select id=\"getAll%1$s\" resultType=\"%1$s\">\n",  entityNametransformer.getAccessorName()));
		persistXml.append(TABSPACE1).append(select);
		persistXml.append("\n").append(TABSPACE1).append(String.format("</select>\n\n",  entityNametransformer.getAccessorName()));
		
		persistXml.append(TABSPACE1).append(String.format("<insert id=\"insert%1$s\" parameterType=\"%1$s\">\n",  entityNametransformer.getAccessorName()));
		persistXml.append(TABSPACE1).append(insert);
		persistXml.append("\n").append(TABSPACE1).append(String.format("</insert>\n\n",  entityNametransformer.getAccessorName()));
		
			
		
		StringBuilder slectByPk = new StringBuilder();
		slectByPk.append(" WHERE ");
		int pkz = primaryKeys.size();
		if (pkz > 0) {
			for (int i = 0; i < pkz; i++) {
				FieldDef fieldDef = primaryKeys.get(i);
				JavaIdentifierTransformer transformer = new JavaIdentifierTransformer(
						fieldDef.getName());
				slectByPk.append(String.format("%s=${%s}",
						transformer.getDbName(), transformer.getVarName()));
				if (i < pkz - 1) {
					slectByPk.append(" AND ");
				}
			}
						
			persistBuf.append(TABSPACE1).append(String.format("%1$s get%1$sBykey(%1$s %2$s);\n\n", entityNametransformer.getAccessorName(), entityNametransformer.getVarName()));
			persistBuf.append(TABSPACE1).append(String.format("void update%1$sByKey(%1$s %2$s);\n\n", entityNametransformer.getAccessorName(), entityNametransformer.getVarName()));
			persistBuf.append(TABSPACE1).append(String.format("void delete%1$sByKey(%1$s %2$s);\n\n", entityNametransformer.getAccessorName(), entityNametransformer.getVarName()));
			
			
			persistXml.append(TABSPACE1).append(String.format("<select id=\"get%1$sBykey\" parameterType=\"%1$s\" resultType=\"%1$s\">\n",  entityNametransformer.getAccessorName()));
			persistXml.append(TABSPACE1).append(select.toString() + slectByPk.toString());
			persistXml.append("\n").append(TABSPACE1).append(String.format("</select>\n\n",  entityNametransformer.getAccessorName()));
		
			persistXml.append(TABSPACE1).append(String.format("<update id=\"update%1$sByKey\" parameterType=\"%1$s\">\n",  entityNametransformer.getAccessorName()));
			persistXml.append(TABSPACE1).append(update.toString() + slectByPk.toString());
			persistXml.append("\n").append(TABSPACE1).append(String.format("</update>\n\n",  entityNametransformer.getAccessorName()));
			
			persistXml.append(TABSPACE1).append(String.format("<delete id=\"delete%1$sByKey\" parameterType=\"%1$s\">\n",  entityNametransformer.getAccessorName()));
			persistXml.append(TABSPACE1).append(delete.toString() + slectByPk.toString());
			persistXml.append("\n").append(TABSPACE1).append(String.format("</delete>\n\n",  entityNametransformer.getAccessorName()));	
			
		} else {
			
			persistBuf.append(TABSPACE1).append(String.format("void update%1$s(%1$s %2$s);\n\n", entityNametransformer.getAccessorName(), entityNametransformer.getVarName()));
			persistBuf.append(TABSPACE1).append(String.format("void delete%1$s(%1$s %2$s);\n\n", entityNametransformer.getAccessorName(), entityNametransformer.getVarName()));
			
			
			persistXml.append(TABSPACE1).append(String.format("<update id=\"update%1$s\" parameterType=\"%1$s\">\n",  entityNametransformer.getAccessorName()));
			persistXml.append(TABSPACE1).append(update.toString() + fullWhere.toString());
			persistXml.append("\n").append(TABSPACE1).append(String.format("</update>\n\n",  entityNametransformer.getAccessorName()));
			
			persistXml.append(TABSPACE1).append(String.format("<delete id=\"delete%1$s\" parameterType=\"%1$s\">\n",  entityNametransformer.getAccessorName()));
			persistXml.append(TABSPACE1).append(delete.toString() + fullWhere.toString());
			persistXml.append("\n").append(TABSPACE1).append(String.format("</delete>\n\n",  entityNametransformer.getAccessorName()));
		
			
			
			persistBuf.append(TABSPACE1).append(String.format("%1$s get%1$sBykey(%1$s %2$s);\n\n", entityNametransformer.getAccessorName(), entityNametransformer.getVarName()));
			persistXml.append(TABSPACE1).append(String.format("<select id=\"get%1$sBykey\" parameterType=\"%1$s\" resultType=\"%1$s\">\n",  entityNametransformer.getAccessorName()));
			persistXml.append(TABSPACE1).append(select.toString() + fullWhere.toString());
			persistXml.append("\n").append(TABSPACE1).append(String.format("</select>\n\n",  entityNametransformer.getAccessorName()));				
		}
		
		
		
		persistBuf.append("}");
		File persistF = new File(persistencePackageD, String.format(" %sMapper.java", entityNametransformer.getAccessorName()));
		FileUtils.write(persistF, persistBuf);
		persistXml.append("<mapper>");
		File persistXmlF = new File(persistencePackageD, String.format(" %sMapper.xml", entityNametransformer.getAccessorName()));
		FileUtils.write(persistXmlF, persistXml);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SpringMVCGenerator mvcGenerator = new SpringMVCGenerator();
		mvcGenerator.setBaseDir("src/test2/java");
		mvcGenerator.setBasePackage("com.asksunny.demo");
		mvcGenerator.setDomainPackage("domain");
		mvcGenerator.setPersistencePackage("persistence");		
		mvcGenerator.generateEntitysFromFile(new File(
				"src\\test\\resources\\schema.sql"));
		

	}

}
