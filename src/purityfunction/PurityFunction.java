package purityfunction;

import decisiontree.ConfusionMatrix;
import instance.Attribute;

/**
 * Created by virgil on 27.10.2015.
 */
public interface PurityFunction {

    /**
     * This function calculates the value of the purity function for the given variables
     * It is implemented in the classes that extend this class, as only they know how to calculate each function
     * @param confusionMatrix the confusion matrix
     * @param attributeA the first Attribute
     * @param attributeY the second Attribute (the outcome)
     * @return the value of the purity function for the variables
     */
    double calculate(ConfusionMatrix confusionMatrix, Attribute attributeA, Attribute attributeY);

}
