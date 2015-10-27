package instance;

/**
 * @author FIIAurelian
 */
public class Attribute {
	
	private String name;
	private String value;
	
	public Attribute( String name, String value ) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue( String value ) {
		this.value = value;
	}
	
	public boolean equalName( String name ) {
		return ( this.name.compareTo( name ) == 0 );
	}
	
	public boolean equals( Attribute other ) {
		if( equalName( other.getName() ) == false )
			return false;
		if( value.compareTo( other.getValue() ) != 0 )
			return false;
		return true;	
	}

}
