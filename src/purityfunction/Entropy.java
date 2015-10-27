package purityfunction;

import decisiontree.ConfusionMatrix;
import instance.Attribute;

/**
 * Created by virgil on 27.10.2015.
 */
public class Entropy implements PurityFunction {

    @Override
    public double calculate(ConfusionMatrix confusionMatrix, Attribute attributeA, Attribute attributeY) {
        //TODO: Based on the confusionMatrix and on the attributes, calculate H(attributeY | attributeA)
        return 0;
    }
}
