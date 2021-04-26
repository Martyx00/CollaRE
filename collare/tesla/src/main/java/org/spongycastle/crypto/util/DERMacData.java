package org.spongycastle.crypto.util;

import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public final class DERMacData {
    private final byte[] macData;

    public enum Type {
        UNILATERALU("KC_1_U"),
        UNILATERALV("KC_1_V"),
        BILATERALU("KC_2_U"),
        BILATERALV("KC_2_V");
        
        private final String enc;

        private Type(String str) {
            this.enc = str;
        }

        public byte[] getHeader() {
            return Strings.toByteArray(this.enc);
        }
    }

    public static final class Builder {
        private ASN1OctetString ephemDataU;
        private ASN1OctetString ephemDataV;
        private ASN1OctetString idU;
        private ASN1OctetString idV;
        private byte[] text;
        private final Type type;

        public Builder(Type type2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            this.type = type2;
            this.idU = DerUtil.getOctetString(bArr);
            this.idV = DerUtil.getOctetString(bArr2);
            this.ephemDataU = DerUtil.getOctetString(bArr3);
            this.ephemDataV = DerUtil.getOctetString(bArr4);
        }

        public Builder withText(byte[] bArr) {
            this.text = DerUtil.toByteArray(new DERTaggedObject(false, 0, DerUtil.getOctetString(bArr)));
            return this;
        }

        public DERMacData build() {
            switch (this.type) {
                case UNILATERALU:
                case BILATERALU:
                    return new DERMacData(concatenate(this.type.getHeader(), DerUtil.toByteArray(this.idU), DerUtil.toByteArray(this.idV), DerUtil.toByteArray(this.ephemDataU), DerUtil.toByteArray(this.ephemDataV), this.text));
                case UNILATERALV:
                case BILATERALV:
                    return new DERMacData(concatenate(this.type.getHeader(), DerUtil.toByteArray(this.idV), DerUtil.toByteArray(this.idU), DerUtil.toByteArray(this.ephemDataV), DerUtil.toByteArray(this.ephemDataU), this.text));
                default:
                    throw new IllegalStateException("Unknown type encountered in build");
            }
        }

        private byte[] concatenate(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            return Arrays.concatenate(Arrays.concatenate(bArr, bArr2, bArr3), Arrays.concatenate(bArr4, bArr5, bArr6));
        }
    }

    private DERMacData(byte[] bArr) {
        this.macData = bArr;
    }

    public byte[] getMacData() {
        return Arrays.clone(this.macData);
    }
}
