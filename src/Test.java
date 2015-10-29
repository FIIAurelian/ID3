import instance.Attribute;
import instance.Dataset;
import instance.Observation;
import decisiontree.Tree;


public class Test {
	public static void main(String[] args) {
		Tree tree = Tree.construct( "covtype-train.csv", "Cover_Type" );
		tree.showTree();
		Dataset dataset = Dataset.loadFromCsv("covtype-test.csv", "," );
		Double hit = .0, count = .0;
		for( Observation observation: dataset.getObservations() ) {
			String actualLabel = observation.getAttributeByName( "Cover_Type" ).getValue();
			String predictedLabel = tree.evaluate( observation );
			count = count + 1.0;
			if( actualLabel.equals( predictedLabel ) )
				hit = hit + 1.0;
		}
		System.out.println( hit / count );
	}
}
