package com.asksunny.tools;

import java.io.File;
import java.util.List;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;

import org.apache.commons.io.IOUtils;

import com.asksunny.tools.domain.schema.EntityDef;
import com.asksunny.tools.spring.gen.SchemaCreateStatementVisitor;

public class SpringMVCGenerator {

	String baseDir = ".";
	String basePackage = null;
	String domainPackage = "domain";
	String persistencePackage = "persistence";
	String servicePackage = "service";
	String controllerPackage = "controller";
	String mapperPackage = "mapper";
	File ddlFile = null;

	public SpringMVCGenerator(File ddlFile) {
		this.ddlFile = ddlFile;
	}

	protected List<EntityDef> loadEntityDef(File pathToDDL) throws Exception {

		String sql = IOUtils.toString(pathToDDL.toURI());
		Statements stmts = CCJSqlParserUtil.parseStatements(sql);
		SchemaCreateStatementVisitor entityCollector = new SchemaCreateStatementVisitor();
		for (Statement stmt : stmts.getStatements()) {
			stmt.accept(entityCollector);
		}
		return entityCollector.getEntities();
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

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

	}

}
