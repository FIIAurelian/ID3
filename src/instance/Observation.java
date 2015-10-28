package instance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FIIAurelian 
 */
public class Observation {
	
	private List<Attribute> attributes = new ArrayList<Attribute>();
	
	public Observation() {}
	
	public void addAttribute( Attribute attribute ) {
		attributes.add( attribute );
	}
	
	public Attribute getAttributeByName( String name ) {
		Attribute result = null;
		for( Attribute attribute: attributes ) {
			if( attribute.equalName( name ) ) {
				result = attribute;
				break;
			}
		}
		return result;
	}
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	public boolean matchAttribute( Attribute attribute ) {
		Attribute attr = getAttributeByName( attribute.getName() );
		if( attr == null )
			return false;
		else
			return attr.equals( attribute );
	}
	
	public String toString() {
		String result = "";
		for( Attribute attribute: attributes)
			result = result + attribute + "\n";
		return result;
	}
}
