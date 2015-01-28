package net.sf.jsqlparser.statement.create.table;

public class RowFormat {

	private char fieldTerminator;
	private char escapedBy;
	private char collectionItemTerminator;
	private char mapKeysTerminator;
	private char nullDefinedAs;
	private char lineTerminator;
	
	public char getLineTerminator() {
		return lineTerminator;
	}
	public void setLineTerminator(char lineTerminator) {
		this.lineTerminator = lineTerminator;
	}
	public char getFieldTerminator() {
		return fieldTerminator;
	}
	public void setFieldTerminator(char fieldTerminator) {
		this.fieldTerminator = fieldTerminator;
	}
	public char getEscapedBy() {
		return escapedBy;
	}
	public void setEscapedBy(char escapedBy) {
		this.escapedBy = escapedBy;
	}
	public char getCollectionItemTerminator() {
		return collectionItemTerminator;
	}
	public void setCollectionItemTerminator(char collectionItemTerminator) {
		this.collectionItemTerminator = collectionItemTerminator;
	}
	public char getMapKeysTerminator() {
		return mapKeysTerminator;
	}
	public void setMapKeysTerminator(char mapKeysTerminator) {
		this.mapKeysTerminator = mapKeysTerminator;
	}
	public char getNullDefinedAs() {
		return nullDefinedAs;
	}
	public void setNullDefinedAs(char nullDefinedAs) {
		this.nullDefinedAs = nullDefinedAs;
	}
	

}
