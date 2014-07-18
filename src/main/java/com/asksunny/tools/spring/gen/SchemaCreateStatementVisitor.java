package com.asksunny.tools.spring.gen;

import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;

import com.asksunny.tools.domain.schema.EntityDef;
import com.asksunny.tools.domain.schema.FieldDef;

public class SchemaCreateStatementVisitor extends StatementVisitorAdapter {

	List<EntityDef> entities = new ArrayList<EntityDef>();

	public SchemaCreateStatementVisitor(List<EntityDef> entities) {
		if (entities == null) {
			this.entities = new ArrayList<EntityDef>();
		} else {
			this.entities = entities;
		}
	}

	public SchemaCreateStatementVisitor() {
		this(null);
	}

	public List<EntityDef> getEntities() {
		return entities;
	}

	@Override
	public void visit(CreateTable createTable) {
		EntityDef entity = new EntityDef();
		entities.add(entity);
		Table tb = createTable.getTable();
		if (tb.getDatabase() != null)
			entity.setDatabaseName(tb.getDatabase().getDatabaseName());
		entity.setSchemaName(tb.getSchemaName());
		entity.setName(tb.getName());
		List<ColumnDefinition> coldefs = createTable.getColumnDefinitions();
		for (ColumnDefinition columnDefinition : coldefs) {

			FieldDef fdef = new FieldDef();
			entity.addField(fdef);
			String colString = (columnDefinition.toString());
			fdef.setName(columnDefinition.getColumnName());
			FieldTypeExtractor.extractNullable(fdef, colString);
			FieldTypeExtractor.extractType(fdef, columnDefinition
					.getColDataType().toString());
			FieldTypeExtractor.isForeignKey(fdef, colString);
			FieldTypeExtractor.isPrimaryKey(fdef, colString);
		}
		List<Index> idxs = createTable.getIndexes();
		if (idxs != null) {
			for (Index idx : idxs) {
				if (idx.getType().equalsIgnoreCase("primary key")) {
					FieldTypeExtractor.setPrimaryKey(entity, idx.toString());
				} else if (idx.getType().equalsIgnoreCase("FOREIGN KEY")) {
					FieldTypeExtractor.setForeignKey(entity, idx.toString());
				}
			}
		}
	}

	@Override
	public void visit(Alter alter) {
		String fts = alter.getDataType().toString();
		String tbl = alter.getTable().getName();
		if (FieldTypeExtractor.PK_Pattern.matcher(fts).find()) {
			for (EntityDef entityDef : entities) {
				if (entityDef.getName().equalsIgnoreCase(tbl)) {
					FieldTypeExtractor.setPrimaryKey(entityDef, fts);
					break;
				}
			}
		}
	}

}
