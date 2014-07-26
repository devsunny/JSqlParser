package com.asksunny.tools.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public final class DatabaseMetaDataUtil {

	private final static char FIELD_DELIMITER = (char) 1;
	private final static char RECORD_DELIMITER = (char) 2;
	
	
	private final static java.util.HashMap<Integer, String> int2cmdMap = new java.util.HashMap<Integer, String>();
	private final static java.util.HashMap<String, Integer> cmd2intMap = new java.util.HashMap<String, Integer>();
	static {
		int2cmdMap.put(101, "getURL");
		cmd2intMap.put("getURL", 101);
		int2cmdMap.put(102, "isReadOnly");
		cmd2intMap.put("isReadOnly", 102);
		int2cmdMap.put(103, "getAttributes");
		cmd2intMap.put("getAttributes", 103);
		int2cmdMap.put(104,
				"supportsDataDefinitionAndDataManipulationTransactions");
		cmd2intMap.put("supportsDataDefinitionAndDataManipulationTransactions",
				104);
		int2cmdMap.put(105, "getCatalogs");
		cmd2intMap.put("getCatalogs", 105);
		int2cmdMap.put(106, "getColumns");
		cmd2intMap.put("getColumns", 106);
		int2cmdMap.put(107, "getFunctions");
		cmd2intMap.put("getFunctions", 107);
		int2cmdMap.put(108, "getIndexInfo");
		cmd2intMap.put("getIndexInfo", 108);
		int2cmdMap.put(109, "getSchemas");
		cmd2intMap.put("getSchemas", 109);
		int2cmdMap.put(110, "getSchemas");
		cmd2intMap.put("getSchemas", 110);
		int2cmdMap.put(111, "getTables");
		cmd2intMap.put("getTables", 111);
		int2cmdMap.put(112, "getTypeInfo");
		cmd2intMap.put("getTypeInfo", 112);
		int2cmdMap.put(113, "getUDTs");
		cmd2intMap.put("getUDTs", 113);
		int2cmdMap.put(114, "getUserName");
		cmd2intMap.put("getUserName", 114);
		int2cmdMap.put(115, "updatesAreDetected");
		cmd2intMap.put("updatesAreDetected", 115);
		int2cmdMap.put(116, "usesLocalFilePerTable");
		cmd2intMap.put("usesLocalFilePerTable", 116);
		int2cmdMap.put(117, "usesLocalFiles");
		cmd2intMap.put("usesLocalFiles", 117);
		int2cmdMap.put(118, "allProceduresAreCallable");
		cmd2intMap.put("allProceduresAreCallable", 118);
		int2cmdMap.put(119, "allTablesAreSelectable");
		cmd2intMap.put("allTablesAreSelectable", 119);
		int2cmdMap.put(120, "deletesAreDetected");
		cmd2intMap.put("deletesAreDetected", 120);
		int2cmdMap.put(121, "doesMaxRowSizeIncludeBlobs");
		cmd2intMap.put("doesMaxRowSizeIncludeBlobs", 121);
		int2cmdMap.put(122, "generatedKeyAlwaysReturned");
		cmd2intMap.put("generatedKeyAlwaysReturned", 122);
		int2cmdMap.put(123, "getBestRowIdentifier");
		cmd2intMap.put("getBestRowIdentifier", 123);
		int2cmdMap.put(124, "getCatalogSeparator");
		cmd2intMap.put("getCatalogSeparator", 124);
		int2cmdMap.put(125, "getCatalogTerm");
		cmd2intMap.put("getCatalogTerm", 125);
		int2cmdMap.put(126, "getClientInfoProperties");
		cmd2intMap.put("getClientInfoProperties", 126);
		int2cmdMap.put(127, "getColumnPrivileges");
		cmd2intMap.put("getColumnPrivileges", 127);
		int2cmdMap.put(128, "getConnection");
		cmd2intMap.put("getConnection", 128);
		int2cmdMap.put(129, "getCrossReference");
		cmd2intMap.put("getCrossReference", 129);
		int2cmdMap.put(130, "getDatabaseMajorVersion");
		cmd2intMap.put("getDatabaseMajorVersion", 130);
		int2cmdMap.put(131, "getDatabaseMinorVersion");
		cmd2intMap.put("getDatabaseMinorVersion", 131);
		int2cmdMap.put(132, "getDatabaseProductName");
		cmd2intMap.put("getDatabaseProductName", 132);
		int2cmdMap.put(133, "getDatabaseProductVersion");
		cmd2intMap.put("getDatabaseProductVersion", 133);
		int2cmdMap.put(134, "getDriverMajorVersion");
		cmd2intMap.put("getDriverMajorVersion", 134);
		int2cmdMap.put(135, "getDriverMinorVersion");
		cmd2intMap.put("getDriverMinorVersion", 135);
		int2cmdMap.put(136, "getDriverName");
		cmd2intMap.put("getDriverName", 136);
		int2cmdMap.put(137, "getDriverVersion");
		cmd2intMap.put("getDriverVersion", 137);
		int2cmdMap.put(138, "getExportedKeys");
		cmd2intMap.put("getExportedKeys", 138);
		int2cmdMap.put(139, "getExtraNameCharacters");
		cmd2intMap.put("getExtraNameCharacters", 139);
		int2cmdMap.put(140, "getFunctionColumns");
		cmd2intMap.put("getFunctionColumns", 140);
		int2cmdMap.put(141, "getIdentifierQuoteString");
		cmd2intMap.put("getIdentifierQuoteString", 141);
		int2cmdMap.put(142, "getImportedKeys");
		cmd2intMap.put("getImportedKeys", 142);
		int2cmdMap.put(143, "getJDBCMajorVersion");
		cmd2intMap.put("getJDBCMajorVersion", 143);
		int2cmdMap.put(144, "getJDBCMinorVersion");
		cmd2intMap.put("getJDBCMinorVersion", 144);
		int2cmdMap.put(145, "getMaxBinaryLiteralLength");
		cmd2intMap.put("getMaxBinaryLiteralLength", 145);
		int2cmdMap.put(146, "getMaxCatalogNameLength");
		cmd2intMap.put("getMaxCatalogNameLength", 146);
		int2cmdMap.put(147, "getMaxCharLiteralLength");
		cmd2intMap.put("getMaxCharLiteralLength", 147);
		int2cmdMap.put(148, "getMaxColumnNameLength");
		cmd2intMap.put("getMaxColumnNameLength", 148);
		int2cmdMap.put(149, "getMaxColumnsInGroupBy");
		cmd2intMap.put("getMaxColumnsInGroupBy", 149);
		int2cmdMap.put(150, "getMaxColumnsInIndex");
		cmd2intMap.put("getMaxColumnsInIndex", 150);
		int2cmdMap.put(151, "getMaxColumnsInOrderBy");
		cmd2intMap.put("getMaxColumnsInOrderBy", 151);
		int2cmdMap.put(152, "getMaxColumnsInSelect");
		cmd2intMap.put("getMaxColumnsInSelect", 152);
		int2cmdMap.put(153, "getMaxColumnsInTable");
		cmd2intMap.put("getMaxColumnsInTable", 153);
		int2cmdMap.put(154, "getMaxConnections");
		cmd2intMap.put("getMaxConnections", 154);
		int2cmdMap.put(155, "getMaxCursorNameLength");
		cmd2intMap.put("getMaxCursorNameLength", 155);
		int2cmdMap.put(156, "getMaxIndexLength");
		cmd2intMap.put("getMaxIndexLength", 156);
		int2cmdMap.put(157, "getMaxProcedureNameLength");
		cmd2intMap.put("getMaxProcedureNameLength", 157);
		int2cmdMap.put(158, "getMaxRowSize");
		cmd2intMap.put("getMaxRowSize", 158);
		int2cmdMap.put(159, "getMaxSchemaNameLength");
		cmd2intMap.put("getMaxSchemaNameLength", 159);
		int2cmdMap.put(160, "getMaxStatementLength");
		cmd2intMap.put("getMaxStatementLength", 160);
		int2cmdMap.put(161, "getMaxStatements");
		cmd2intMap.put("getMaxStatements", 161);
		int2cmdMap.put(162, "getMaxTableNameLength");
		cmd2intMap.put("getMaxTableNameLength", 162);
		int2cmdMap.put(163, "getMaxTablesInSelect");
		cmd2intMap.put("getMaxTablesInSelect", 163);
		int2cmdMap.put(164, "getMaxUserNameLength");
		cmd2intMap.put("getMaxUserNameLength", 164);
		int2cmdMap.put(165, "getNumericFunctions");
		cmd2intMap.put("getNumericFunctions", 165);
		int2cmdMap.put(166, "getPrimaryKeys");
		cmd2intMap.put("getPrimaryKeys", 166);
		int2cmdMap.put(167, "getProcedureColumns");
		cmd2intMap.put("getProcedureColumns", 167);
		int2cmdMap.put(168, "getProcedureTerm");
		cmd2intMap.put("getProcedureTerm", 168);
		int2cmdMap.put(169, "getProcedures");
		cmd2intMap.put("getProcedures", 169);
		int2cmdMap.put(170, "getPseudoColumns");
		cmd2intMap.put("getPseudoColumns", 170);
		int2cmdMap.put(171, "getResultSetHoldability");
		cmd2intMap.put("getResultSetHoldability", 171);
		int2cmdMap.put(172, "getRowIdLifetime");
		cmd2intMap.put("getRowIdLifetime", 172);
		int2cmdMap.put(173, "getSQLKeywords");
		cmd2intMap.put("getSQLKeywords", 173);
		int2cmdMap.put(174, "getSQLStateType");
		cmd2intMap.put("getSQLStateType", 174);
		int2cmdMap.put(175, "getSchemaTerm");
		cmd2intMap.put("getSchemaTerm", 175);
		int2cmdMap.put(176, "getSearchStringEscape");
		cmd2intMap.put("getSearchStringEscape", 176);
		int2cmdMap.put(177, "getStringFunctions");
		cmd2intMap.put("getStringFunctions", 177);
		int2cmdMap.put(178, "getSuperTables");
		cmd2intMap.put("getSuperTables", 178);
		int2cmdMap.put(179, "getSuperTypes");
		cmd2intMap.put("getSuperTypes", 179);
		int2cmdMap.put(180, "getSystemFunctions");
		cmd2intMap.put("getSystemFunctions", 180);
		int2cmdMap.put(181, "getTablePrivileges");
		cmd2intMap.put("getTablePrivileges", 181);
		int2cmdMap.put(182, "getTableTypes");
		cmd2intMap.put("getTableTypes", 182);
		int2cmdMap.put(183, "getTimeDateFunctions");
		cmd2intMap.put("getTimeDateFunctions", 183);
		int2cmdMap.put(184, "getVersionColumns");
		cmd2intMap.put("getVersionColumns", 184);
		int2cmdMap.put(185, "insertsAreDetected");
		cmd2intMap.put("insertsAreDetected", 185);
		int2cmdMap.put(186, "isCatalogAtStart");
		cmd2intMap.put("isCatalogAtStart", 186);
		int2cmdMap.put(187, "locatorsUpdateCopy");
		cmd2intMap.put("locatorsUpdateCopy", 187);
		int2cmdMap.put(188, "nullPlusNonNullIsNull");
		cmd2intMap.put("nullPlusNonNullIsNull", 188);
		int2cmdMap.put(189, "nullsAreSortedAtEnd");
		cmd2intMap.put("nullsAreSortedAtEnd", 189);
		int2cmdMap.put(190, "nullsAreSortedAtStart");
		cmd2intMap.put("nullsAreSortedAtStart", 190);
		int2cmdMap.put(191, "nullsAreSortedHigh");
		cmd2intMap.put("nullsAreSortedHigh", 191);
		int2cmdMap.put(192, "nullsAreSortedLow");
		cmd2intMap.put("nullsAreSortedLow", 192);
		int2cmdMap.put(193, "othersDeletesAreVisible");
		cmd2intMap.put("othersDeletesAreVisible", 193);
		int2cmdMap.put(194, "othersInsertsAreVisible");
		cmd2intMap.put("othersInsertsAreVisible", 194);
		int2cmdMap.put(195, "othersUpdatesAreVisible");
		cmd2intMap.put("othersUpdatesAreVisible", 195);
		int2cmdMap.put(196, "ownDeletesAreVisible");
		cmd2intMap.put("ownDeletesAreVisible", 196);
		int2cmdMap.put(197, "ownInsertsAreVisible");
		cmd2intMap.put("ownInsertsAreVisible", 197);
		int2cmdMap.put(198, "ownUpdatesAreVisible");
		cmd2intMap.put("ownUpdatesAreVisible", 198);
		int2cmdMap.put(199, "storesLowerCaseIdentifiers");
		cmd2intMap.put("storesLowerCaseIdentifiers", 199);
		int2cmdMap.put(200, "storesMixedCaseIdentifiers");
		cmd2intMap.put("storesMixedCaseIdentifiers", 200);
		int2cmdMap.put(201, "storesUpperCaseIdentifiers");
		cmd2intMap.put("storesUpperCaseIdentifiers", 201);
		int2cmdMap.put(202, "supportsANSI92EntryLevelSQL");
		cmd2intMap.put("supportsANSI92EntryLevelSQL", 202);
		int2cmdMap.put(203, "supportsANSI92FullSQL");
		cmd2intMap.put("supportsANSI92FullSQL", 203);
		int2cmdMap.put(204, "supportsBatchUpdates");
		cmd2intMap.put("supportsBatchUpdates", 204);
		int2cmdMap.put(205, "supportsColumnAliasing");
		cmd2intMap.put("supportsColumnAliasing", 205);
		int2cmdMap.put(206, "supportsConvert");
		cmd2intMap.put("supportsConvert", 206);
		int2cmdMap.put(207, "supportsConvert");
		cmd2intMap.put("supportsConvert", 207);
		int2cmdMap.put(208, "supportsCoreSQLGrammar");
		cmd2intMap.put("supportsCoreSQLGrammar", 208);
		int2cmdMap.put(209, "supportsCorrelatedSubqueries");
		cmd2intMap.put("supportsCorrelatedSubqueries", 209);
		int2cmdMap.put(210, "supportsExpressionsInOrderBy");
		cmd2intMap.put("supportsExpressionsInOrderBy", 210);
		int2cmdMap.put(211, "supportsExtendedSQLGrammar");
		cmd2intMap.put("supportsExtendedSQLGrammar", 211);
		int2cmdMap.put(212, "supportsFullOuterJoins");
		cmd2intMap.put("supportsFullOuterJoins", 212);
		int2cmdMap.put(213, "supportsGetGeneratedKeys");
		cmd2intMap.put("supportsGetGeneratedKeys", 213);
		int2cmdMap.put(214, "supportsGroupBy");
		cmd2intMap.put("supportsGroupBy", 214);
		int2cmdMap.put(215, "supportsGroupByBeyondSelect");
		cmd2intMap.put("supportsGroupByBeyondSelect", 215);
		int2cmdMap.put(216, "supportsGroupByUnrelated");
		cmd2intMap.put("supportsGroupByUnrelated", 216);
		int2cmdMap.put(217, "supportsLikeEscapeClause");
		cmd2intMap.put("supportsLikeEscapeClause", 217);
		int2cmdMap.put(218, "supportsLimitedOuterJoins");
		cmd2intMap.put("supportsLimitedOuterJoins", 218);
		int2cmdMap.put(219, "supportsMinimumSQLGrammar");
		cmd2intMap.put("supportsMinimumSQLGrammar", 219);
		int2cmdMap.put(220, "supportsMixedCaseIdentifiers");
		cmd2intMap.put("supportsMixedCaseIdentifiers", 220);
		int2cmdMap.put(221, "supportsMultipleOpenResults");
		cmd2intMap.put("supportsMultipleOpenResults", 221);
		int2cmdMap.put(222, "supportsMultipleResultSets");
		cmd2intMap.put("supportsMultipleResultSets", 222);
		int2cmdMap.put(223, "supportsMultipleTransactions");
		cmd2intMap.put("supportsMultipleTransactions", 223);
		int2cmdMap.put(224, "supportsNamedParameters");
		cmd2intMap.put("supportsNamedParameters", 224);
		int2cmdMap.put(225, "supportsNonNullableColumns");
		cmd2intMap.put("supportsNonNullableColumns", 225);
		int2cmdMap.put(226, "supportsOrderByUnrelated");
		cmd2intMap.put("supportsOrderByUnrelated", 226);
		int2cmdMap.put(227, "supportsOuterJoins");
		cmd2intMap.put("supportsOuterJoins", 227);
		int2cmdMap.put(228, "supportsPositionedDelete");
		cmd2intMap.put("supportsPositionedDelete", 228);
		int2cmdMap.put(229, "supportsPositionedUpdate");
		cmd2intMap.put("supportsPositionedUpdate", 229);
		int2cmdMap.put(230, "supportsResultSetConcurrency");
		cmd2intMap.put("supportsResultSetConcurrency", 230);
		int2cmdMap.put(231, "supportsResultSetHoldability");
		cmd2intMap.put("supportsResultSetHoldability", 231);
		int2cmdMap.put(232, "supportsResultSetType");
		cmd2intMap.put("supportsResultSetType", 232);
		int2cmdMap.put(233, "supportsSavepoints");
		cmd2intMap.put("supportsSavepoints", 233);
		int2cmdMap.put(234, "supportsSelectForUpdate");
		cmd2intMap.put("supportsSelectForUpdate", 234);
		int2cmdMap.put(235, "supportsStatementPooling");
		cmd2intMap.put("supportsStatementPooling", 235);
		int2cmdMap.put(236, "supportsStoredProcedures");
		cmd2intMap.put("supportsStoredProcedures", 236);
		int2cmdMap.put(237, "supportsSubqueriesInExists");
		cmd2intMap.put("supportsSubqueriesInExists", 237);
		int2cmdMap.put(238, "supportsSubqueriesInIns");
		cmd2intMap.put("supportsSubqueriesInIns", 238);
		int2cmdMap.put(239, "supportsTransactions");
		cmd2intMap.put("supportsTransactions", 239);
		int2cmdMap.put(240, "supportsUnion");
		cmd2intMap.put("supportsUnion", 240);
		int2cmdMap.put(241, "supportsUnionAll");
		cmd2intMap.put("supportsUnionAll", 241);
		int2cmdMap.put(242, "autoCommitFailureClosesAllResultSets");
		cmd2intMap.put("autoCommitFailureClosesAllResultSets", 242);
		int2cmdMap.put(243, "dataDefinitionCausesTransactionCommit");
		cmd2intMap.put("dataDefinitionCausesTransactionCommit", 243);
		int2cmdMap.put(244, "dataDefinitionIgnoredInTransactions");
		cmd2intMap.put("dataDefinitionIgnoredInTransactions", 244);
		int2cmdMap.put(245, "getDefaultTransactionIsolation");
		cmd2intMap.put("getDefaultTransactionIsolation", 245);
		int2cmdMap.put(246, "storesLowerCaseQuotedIdentifiers");
		cmd2intMap.put("storesLowerCaseQuotedIdentifiers", 246);
		int2cmdMap.put(247, "storesMixedCaseQuotedIdentifiers");
		cmd2intMap.put("storesMixedCaseQuotedIdentifiers", 247);
		int2cmdMap.put(248, "storesUpperCaseQuotedIdentifiers");
		cmd2intMap.put("storesUpperCaseQuotedIdentifiers", 248);
		int2cmdMap.put(249, "supportsANSI92IntermediateSQL");
		cmd2intMap.put("supportsANSI92IntermediateSQL", 249);
		int2cmdMap.put(250, "supportsAlterTableWithAddColumn");
		cmd2intMap.put("supportsAlterTableWithAddColumn", 250);
		int2cmdMap.put(251, "supportsAlterTableWithDropColumn");
		cmd2intMap.put("supportsAlterTableWithDropColumn", 251);
		int2cmdMap.put(252, "supportsCatalogsInDataManipulation");
		cmd2intMap.put("supportsCatalogsInDataManipulation", 252);
		int2cmdMap.put(253, "supportsCatalogsInIndexDefinitions");
		cmd2intMap.put("supportsCatalogsInIndexDefinitions", 253);
		int2cmdMap.put(254, "supportsCatalogsInPrivilegeDefinitions");
		cmd2intMap.put("supportsCatalogsInPrivilegeDefinitions", 254);
		int2cmdMap.put(255, "supportsCatalogsInProcedureCalls");
		cmd2intMap.put("supportsCatalogsInProcedureCalls", 255);
		int2cmdMap.put(256, "supportsCatalogsInTableDefinitions");
		cmd2intMap.put("supportsCatalogsInTableDefinitions", 256);
		int2cmdMap.put(257, "supportsDataManipulationTransactionsOnly");
		cmd2intMap.put("supportsDataManipulationTransactionsOnly", 257);
		int2cmdMap.put(258, "supportsDifferentTableCorrelationNames");
		cmd2intMap.put("supportsDifferentTableCorrelationNames", 258);
		int2cmdMap.put(259, "supportsIntegrityEnhancementFacility");
		cmd2intMap.put("supportsIntegrityEnhancementFacility", 259);
		int2cmdMap.put(260, "supportsMixedCaseQuotedIdentifiers");
		cmd2intMap.put("supportsMixedCaseQuotedIdentifiers", 260);
		int2cmdMap.put(261, "supportsOpenCursorsAcrossCommit");
		cmd2intMap.put("supportsOpenCursorsAcrossCommit", 261);
		int2cmdMap.put(262, "supportsOpenCursorsAcrossRollback");
		cmd2intMap.put("supportsOpenCursorsAcrossRollback", 262);
		int2cmdMap.put(263, "supportsOpenStatementsAcrossCommit");
		cmd2intMap.put("supportsOpenStatementsAcrossCommit", 263);
		int2cmdMap.put(264, "supportsOpenStatementsAcrossRollback");
		cmd2intMap.put("supportsOpenStatementsAcrossRollback", 264);
		int2cmdMap.put(265, "supportsSchemasInDataManipulation");
		cmd2intMap.put("supportsSchemasInDataManipulation", 265);
		int2cmdMap.put(266, "supportsSchemasInIndexDefinitions");
		cmd2intMap.put("supportsSchemasInIndexDefinitions", 266);
		int2cmdMap.put(267, "supportsSchemasInPrivilegeDefinitions");
		cmd2intMap.put("supportsSchemasInPrivilegeDefinitions", 267);
		int2cmdMap.put(268, "supportsSchemasInProcedureCalls");
		cmd2intMap.put("supportsSchemasInProcedureCalls", 268);
		int2cmdMap.put(269, "supportsSchemasInTableDefinitions");
		cmd2intMap.put("supportsSchemasInTableDefinitions", 269);
		int2cmdMap.put(270, "supportsStoredFunctionsUsingCallSyntax");
		cmd2intMap.put("supportsStoredFunctionsUsingCallSyntax", 270);
		int2cmdMap.put(271, "supportsSubqueriesInComparisons");
		cmd2intMap.put("supportsSubqueriesInComparisons", 271);
		int2cmdMap.put(272, "supportsSubqueriesInQuantifieds");
		cmd2intMap.put("supportsSubqueriesInQuantifieds", 272);
		int2cmdMap.put(273, "supportsTableCorrelationNames");
		cmd2intMap.put("supportsTableCorrelationNames", 273);
		int2cmdMap.put(274, "supportsTransactionIsolationLevel");
		cmd2intMap.put("supportsTransactionIsolationLevel", 274);
		int2cmdMap.put(275, "unwrap");
		cmd2intMap.put("unwrap", 275);
		int2cmdMap.put(276, "isWrapperFor");
		cmd2intMap.put("isWrapperFor", 276);
	}

	public static HashMap<String, String> convertToHashMap(String dbmd)
			throws SQLException {
		HashMap<String, String> dbmdmap = new HashMap<>();
		int len = dbmd.length();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < len; i++) {
			i = readField(buf, dbmd, i, len, FIELD_DELIMITER, RECORD_DELIMITER);
			String key = buf.toString();
			if(key.length()==0){
				break;
			}
			buf.setLength(0);
			System.out.println(int2cmdMap.get(Integer.valueOf(key)));
			i++;
			i = readField(buf, dbmd, i, len, RECORD_DELIMITER, (char) -1);
			String val = buf.toString();
			buf.setLength(0);
			dbmdmap.put(key, val);			
		}
		return dbmdmap;
	}

	protected static int readField(StringBuilder buf, String dbmd, int offset,
			int len, char delimiter, char delimiter2) {
		int i = offset;
		for (; i < len; i++) {
			char c = dbmd.charAt(i);
			if (c == delimiter || (delimiter2 > 0 && c == delimiter2))
				break;
			buf.append(c);
		}
		return i;
	}

	public static String convertToString(DatabaseMetaData dbmd)
			throws SQLException {
		StringBuilder dbmsbuf = new StringBuilder();
		dbmsbuf.append(cmd2intMap.get("getURL"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getURL());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("isReadOnly"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.isReadOnly());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getUserName"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getUserName());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getConnection"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getConnection());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("autoCommitFailureClosesAllResultSets"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.autoCommitFailureClosesAllResultSets());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("dataDefinitionCausesTransactionCommit"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.dataDefinitionCausesTransactionCommit());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("dataDefinitionIgnoredInTransactions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.dataDefinitionIgnoredInTransactions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDefaultTransactionIsolation"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getDefaultTransactionIsolation());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("storesLowerCaseQuotedIdentifiers"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.storesLowerCaseQuotedIdentifiers());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("storesMixedCaseQuotedIdentifiers"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.storesMixedCaseQuotedIdentifiers());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("storesUpperCaseQuotedIdentifiers"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.storesUpperCaseQuotedIdentifiers());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsANSI92IntermediateSQL"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsANSI92IntermediateSQL());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsAlterTableWithAddColumn"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsAlterTableWithAddColumn());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsAlterTableWithDropColumn"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsAlterTableWithDropColumn());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsCatalogsInDataManipulation"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsCatalogsInDataManipulation());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsCatalogsInIndexDefinitions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsCatalogsInIndexDefinitions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsCatalogsInPrivilegeDefinitions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsCatalogsInPrivilegeDefinitions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsCatalogsInProcedureCalls"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsCatalogsInProcedureCalls());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("allProceduresAreCallable"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.allProceduresAreCallable());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("allTablesAreSelectable"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.allTablesAreSelectable());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("doesMaxRowSizeIncludeBlobs"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.doesMaxRowSizeIncludeBlobs());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("generatedKeyAlwaysReturned"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.generatedKeyAlwaysReturned());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getCatalogSeparator"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getCatalogSeparator());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getCatalogTerm"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getCatalogTerm());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDatabaseMajorVersion"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getDatabaseMajorVersion());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDatabaseMinorVersion"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getDatabaseMinorVersion());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDatabaseProductName"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getDatabaseProductName());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDatabaseProductVersion"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getDatabaseProductVersion());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDriverMajorVersion"));
		dbmsbuf.append(FIELD_DELIMITER);
		dbmd.getDriverMajorVersion();
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDriverMinorVersion"));
		dbmsbuf.append(FIELD_DELIMITER);
		dbmsbuf.append(dbmd.getDriverMinorVersion());
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap
				.get("supportsDataDefinitionAndDataManipulationTransactions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd
					.supportsDataDefinitionAndDataManipulationTransactions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDriverName"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getDriverName());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getDriverVersion"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getDriverVersion());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getExtraNameCharacters"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getExtraNameCharacters());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getIdentifierQuoteString"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getIdentifierQuoteString());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getJDBCMajorVersion"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getJDBCMajorVersion());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getJDBCMinorVersion"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getJDBCMinorVersion());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxBinaryLiteralLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxBinaryLiteralLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxCatalogNameLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxCatalogNameLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxCharLiteralLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxCharLiteralLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxColumnNameLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxColumnNameLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxColumnsInGroupBy"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxColumnsInGroupBy());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxColumnsInIndex"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxColumnsInIndex());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxColumnsInOrderBy"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxColumnsInOrderBy());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxColumnsInSelect"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxColumnsInSelect());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxColumnsInTable"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxColumnsInTable());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxConnections"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxConnections());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxCursorNameLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxCursorNameLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxIndexLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxIndexLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxProcedureNameLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxProcedureNameLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxRowSize"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxRowSize());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxSchemaNameLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxSchemaNameLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxStatementLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxStatementLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxStatements"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxStatements());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxTableNameLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxTableNameLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxTablesInSelect"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxTablesInSelect());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getMaxUserNameLength"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getMaxUserNameLength());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getNumericFunctions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getNumericFunctions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getProcedureTerm"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getProcedureTerm());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getResultSetHoldability"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getResultSetHoldability());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getRowIdLifetime"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getRowIdLifetime());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getSQLKeywords"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getSQLKeywords());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getSQLStateType"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getSQLStateType());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getSchemaTerm"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getSchemaTerm());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getSearchStringEscape"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getSearchStringEscape());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getStringFunctions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getStringFunctions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getSystemFunctions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getSystemFunctions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("getTimeDateFunctions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.getTimeDateFunctions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("isCatalogAtStart"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.isCatalogAtStart());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("locatorsUpdateCopy"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.locatorsUpdateCopy());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("nullPlusNonNullIsNull"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.nullPlusNonNullIsNull());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("nullsAreSortedAtEnd"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.nullsAreSortedAtEnd());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("nullsAreSortedAtStart"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.nullsAreSortedAtStart());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("nullsAreSortedHigh"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.nullsAreSortedHigh());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("nullsAreSortedLow"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.nullsAreSortedLow());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("storesLowerCaseIdentifiers"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.storesLowerCaseIdentifiers());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("storesMixedCaseIdentifiers"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.storesMixedCaseIdentifiers());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("storesUpperCaseIdentifiers"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.storesUpperCaseIdentifiers());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsANSI92EntryLevelSQL"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsANSI92EntryLevelSQL());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsANSI92FullSQL"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsANSI92FullSQL());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsBatchUpdates"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsBatchUpdates());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsColumnAliasing"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsColumnAliasing());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsConvert"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsConvert());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsCoreSQLGrammar"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsCoreSQLGrammar());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsCorrelatedSubqueries"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsCorrelatedSubqueries());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsExpressionsInOrderBy"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsExpressionsInOrderBy());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsExtendedSQLGrammar"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsExtendedSQLGrammar());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsFullOuterJoins"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsFullOuterJoins());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsGetGeneratedKeys"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsGetGeneratedKeys());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsGroupBy"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsGroupBy());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsGroupByBeyondSelect"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsGroupByBeyondSelect());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsGroupByUnrelated"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsGroupByUnrelated());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsLikeEscapeClause"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsLikeEscapeClause());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsLimitedOuterJoins"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsLimitedOuterJoins());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsMinimumSQLGrammar"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsMinimumSQLGrammar());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsMixedCaseIdentifiers"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsMixedCaseIdentifiers());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsMultipleOpenResults"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsMultipleOpenResults());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsMultipleResultSets"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsMultipleResultSets());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsMultipleTransactions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsMultipleTransactions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsNamedParameters"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsNamedParameters());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsNonNullableColumns"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsNonNullableColumns());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsOrderByUnrelated"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsOrderByUnrelated());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsOuterJoins"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsOuterJoins());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsPositionedDelete"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsPositionedDelete());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsPositionedUpdate"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsPositionedUpdate());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSavepoints"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSavepoints());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSelectForUpdate"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSelectForUpdate());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsStatementPooling"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsStatementPooling());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsStoredProcedures"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsStoredProcedures());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSubqueriesInExists"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSubqueriesInExists());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSubqueriesInIns"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSubqueriesInIns());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsTransactions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsTransactions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsUnion"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsUnion());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsUnionAll"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsUnionAll());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("usesLocalFilePerTable"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.usesLocalFilePerTable());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("usesLocalFiles"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.usesLocalFiles());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsCatalogsInTableDefinitions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsCatalogsInTableDefinitions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap
				.get("supportsDataManipulationTransactionsOnly"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsDataManipulationTransactionsOnly());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsDifferentTableCorrelationNames"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsDifferentTableCorrelationNames());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsIntegrityEnhancementFacility"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsIntegrityEnhancementFacility());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsMixedCaseQuotedIdentifiers"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsMixedCaseQuotedIdentifiers());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsOpenCursorsAcrossCommit"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsOpenCursorsAcrossCommit());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsOpenCursorsAcrossRollback"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsOpenCursorsAcrossRollback());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsOpenStatementsAcrossCommit"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsOpenStatementsAcrossCommit());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsOpenStatementsAcrossRollback"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsOpenStatementsAcrossRollback());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSchemasInDataManipulation"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSchemasInDataManipulation());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSchemasInIndexDefinitions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSchemasInIndexDefinitions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSchemasInPrivilegeDefinitions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSchemasInPrivilegeDefinitions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSchemasInProcedureCalls"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSchemasInProcedureCalls());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSchemasInTableDefinitions"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSchemasInTableDefinitions());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsStoredFunctionsUsingCallSyntax"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsStoredFunctionsUsingCallSyntax());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSubqueriesInComparisons"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSubqueriesInComparisons());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsSubqueriesInQuantifieds"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsSubqueriesInQuantifieds());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}
		dbmsbuf.append(RECORD_DELIMITER);
		dbmsbuf.append(cmd2intMap.get("supportsTableCorrelationNames"));
		dbmsbuf.append(FIELD_DELIMITER);
		try {
			dbmsbuf.append(dbmd.supportsTableCorrelationNames());
		} catch (SQLException sex) {
			;// Ignore it; it is not implemented in vendor
		}	
		return dbmsbuf.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
