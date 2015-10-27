package instance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FIIAurelian 
 */
public class Observation {
	
	private List<Attribute> attributes = new ArrayList<Attribute>();
	private String label;
	
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
	
	public void setLabel( String label ) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
