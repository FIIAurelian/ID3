package decisiontree;

import instance.Attribute;

import java.util.ArrayList;
import java.util.List;

import utils.Pair;

/**
 * @author FIIAurelian
 */
public class Node {
	
	private boolean terminal;
	private String label;
	private List<Pair<Attribute, Node>> children = new ArrayList<Pair<Attribute, Node>>();
	
	public Node() {
		this.terminal = false;
	}
	
	public void setTerminal( boolean terminal ) {
		this.terminal = terminal;
	}
	
	public boolean isTerminal() {
		return terminal;
	}
	
	public void setLabel( String label ) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void addChildren( Pair<Attribute, Node> pair ) {
		children.add(pair);
	}
	
	public void addChildren( Attribute attribute, Node node ) {
		this.addChildren( new Pair<Attribute, Node>( attribute, node ) );
	}
	
	public Node getChildrenNodeForAttribute( Attribute attribute ) {
		Node node = null;
		for( Pair<Attribute, Node> children: this.children) {
			if( children.getFirst().equals( attribute ) ) {
				node = children.getSecond();
			}
		}
		return node;
	}

	
}
