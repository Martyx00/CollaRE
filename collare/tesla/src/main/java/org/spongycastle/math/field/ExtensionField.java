package org.spongycastle.math.field;

public interface ExtensionField extends FiniteField {
    int getDegree();

    FiniteField getSubfield();
}
