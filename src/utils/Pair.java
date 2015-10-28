package utils;

/**
 * @author FIIAurelian
 */
public class Pair<F, S> {
	
	private F first;
	private S second;
	
	public Pair( F first, S second ) {
		this.first = first;
		this.second = second;
	}
	
	public F getFirst() {
		return this.first;
	}
	
	public S getSecond() {
		return this.second;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals( Object obj ) {
		if( obj == null )
			return false;
		if( this == obj )
			return true;
		if( obj instanceof Pair<?, ?> )
			return equals( ( Pair<F, S> ) obj );
		else
			return false;
	}
	
	public boolean equals( Pair<F, S> pair ) {
		return ( pair.getFirst().equals( first ) && pair.getSecond().equals( second ) ); 
	}

}
