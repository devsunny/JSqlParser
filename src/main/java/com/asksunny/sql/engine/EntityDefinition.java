package com.asksunny.sql.engine;

import java.util.List;

import com.asksunny.sql.engine.util.CaseInsensitiveBiMap;

public class EntityDefinition {

	private List<EntityFieldDefinition> fieldDefinitions;
	private List<EntityFieldDefinition> partitionDefinitions;
	private CaseInsensitiveBiMap<Integer> columnIndexMap;	
	private CaseInsensitiveBiMap<Integer> partitionIndexMap;
	
	
	
	public List<EntityFieldDefinition> getFieldDefinitions() {
		return fieldDefinitions;
	}
	public void setFieldDefinitions(List<EntityFieldDefinition> fieldDefinitions) {
		this.fieldDefinitions = fieldDefinitions;
		columnIndexMap = new CaseInsensitiveBiMap<Integer>();		
		int pos = 0;
		for (EntityFieldDefinition entityFieldDefinition : fieldDefinitions) {
			Integer idx = new Integer(pos++);
			columnIndexMap.put(entityFieldDefinition.getFieldName(), idx);
		}
	}
	
	
	public int getFieldIndex(String name)
	{
		if(columnIndexMap.containsKey(name)){
			return columnIndexMap.get(name);
		}else{
			throw new IllegalArgumentException(String.format("Field name %s does not exist", name));
		}
	}
	
	
	public String getFieldName(int idx)
	{
		if(idx>=columnIndexMap.size()|| idx<0){
			throw new IndexOutOfBoundsException(String.format("Field Index Out of bound [%d] of size of [%d]", idx, columnIndexMap.size()));
		}		
		return columnIndexMap.getKey(idx);
		
	}
	
	
	public String getPartitionFieldName(int idx)
	{
		if(idx>=partitionIndexMap.size()|| idx<0){
			throw new IndexOutOfBoundsException(String.format("Field Index Out of bound [%d] of size of [%d]", idx, partitionIndexMap.size()));
		}		
		return partitionIndexMap.getKey(idx);
	}
	
	public boolean isPartitionColumn(String name)
	{
		return partitionIndexMap.containsKey(name);
	}
	
	public int getPartitionFieldIndex(String name)
	{
		if(partitionIndexMap.containsKey(name)){
			return partitionIndexMap.get(name);
		}else{
			throw new IllegalArgumentException(String.format("Partition Field name %s does not exist", name));
		}
	}
	
	public List<EntityFieldDefinition> getPartitionDefinitions() {
		return partitionDefinitions;
	}
	public void setPartitionDefinitions(
			List<EntityFieldDefinition> partitionDefinitions) {				
		partitionIndexMap = new CaseInsensitiveBiMap<Integer>();
		this.partitionDefinitions = partitionDefinitions;
		int pos = 0;
		for (EntityFieldDefinition entityFieldDefinition : partitionDefinitions) {
			Integer idx = new Integer(pos++);
			partitionIndexMap.put(entityFieldDefinition.getFieldName(), idx);
		}
	}
	
	
	

}
