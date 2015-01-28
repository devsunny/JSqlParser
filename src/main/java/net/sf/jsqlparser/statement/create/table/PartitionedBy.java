package net.sf.jsqlparser.statement.create.table;

import java.util.List;

public class PartitionedBy {

	private List<ColumnDefinition> partitionDefinitions;
	
	public PartitionedBy() {		
	}

	public List<ColumnDefinition> getPartitionDefinitions() {
		return partitionDefinitions;
	}

	public void setPartitionDefinitions(List<ColumnDefinition> partitionDefinitions) {
		this.partitionDefinitions = partitionDefinitions;
	}
	
}
