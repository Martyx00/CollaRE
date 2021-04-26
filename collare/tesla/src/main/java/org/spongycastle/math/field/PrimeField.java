package org.spongycastle.math.field;

import java.math.BigInteger;

/* access modifiers changed from: package-private */
public class PrimeField implements FiniteField {
    protected final BigInteger characteristic;

    @Override // org.spongycastle.math.field.FiniteField
    public int getDimension() {
        return 1;
    }

    PrimeField(BigInteger bigInteger) {
        this.characteristic = bigInteger;
    }

    @Override // org.spongycastle.math.field.FiniteField
    public BigInteger getCharacteristic() {
        return this.characteristic;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrimeField)) {
            return false;
        }
        return this.characteristic.equals(((PrimeField) obj).characteristic);
    }

    public int hashCode() {
        return this.characteristic.hashCode();
    }
}
