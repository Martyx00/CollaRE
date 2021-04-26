package org.spongycastle.asn1.util;

import java.io.IOException;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1ApplicationSpecific;
import org.spongycastle.asn1.ASN1Boolean;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.ASN1UTCTime;
import org.spongycastle.asn1.BERApplicationSpecific;
import org.spongycastle.asn1.BEROctetString;
import org.spongycastle.asn1.BERSequence;
import org.spongycastle.asn1.BERSet;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DERBMPString;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERExternal;
import org.spongycastle.asn1.DERGraphicString;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERT61String;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.DERVideotexString;
import org.spongycastle.asn1.DERVisibleString;
import org.spongycastle.util.Strings;
import org.spongycastle.util.encoders.Hex;

public class ASN1Dump {
    private static final int SAMPLE_SIZE = 32;
    private static final String TAB = "    ";

    static void _dumpAsString(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        String lineSeparator = Strings.lineSeparator();
        if (aSN1Primitive instanceof ASN1Sequence) {
            Enumeration objects = ((ASN1Sequence) aSN1Primitive).getObjects();
            String str2 = str + TAB;
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERSequence) {
                stringBuffer.append("BER Sequence");
            } else if (aSN1Primitive instanceof DERSequence) {
                stringBuffer.append("DER Sequence");
            } else {
                stringBuffer.append("Sequence");
            }
            stringBuffer.append(lineSeparator);
            while (objects.hasMoreElements()) {
                Object nextElement = objects.nextElement();
                if (nextElement == null || nextElement.equals(DERNull.INSTANCE)) {
                    stringBuffer.append(str2);
                    stringBuffer.append("NULL");
                    stringBuffer.append(lineSeparator);
                } else if (nextElement instanceof ASN1Primitive) {
                    _dumpAsString(str2, z, (ASN1Primitive) nextElement, stringBuffer);
                } else {
                    _dumpAsString(str2, z, ((ASN1Encodable) nextElement).toASN1Primitive(), stringBuffer);
                }
            }
        } else if (aSN1Primitive instanceof ASN1TaggedObject) {
            String str3 = str + TAB;
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERTaggedObject) {
                stringBuffer.append("BER Tagged [");
            } else {
                stringBuffer.append("Tagged [");
            }
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            stringBuffer.append(Integer.toString(aSN1TaggedObject.getTagNo()));
            stringBuffer.append(']');
            if (!aSN1TaggedObject.isExplicit()) {
                stringBuffer.append(" IMPLICIT ");
            }
            stringBuffer.append(lineSeparator);
            if (aSN1TaggedObject.isEmpty()) {
                stringBuffer.append(str3);
                stringBuffer.append("EMPTY");
                stringBuffer.append(lineSeparator);
                return;
            }
            _dumpAsString(str3, z, aSN1TaggedObject.getObject(), stringBuffer);
        } else if (aSN1Primitive instanceof ASN1Set) {
            Enumeration objects2 = ((ASN1Set) aSN1Primitive).getObjects();
            String str4 = str + TAB;
            stringBuffer.append(str);
            if (aSN1Primitive instanceof BERSet) {
                stringBuffer.append("BER Set");
            } else {
                stringBuffer.append("DER Set");
            }
            stringBuffer.append(lineSeparator);
            while (objects2.hasMoreElements()) {
                Object nextElement2 = objects2.nextElement();
                if (nextElement2 == null) {
                    stringBuffer.append(str4);
                    stringBuffer.append("NULL");
                    stringBuffer.append(lineSeparator);
                } else if (nextElement2 instanceof ASN1Primitive) {
                    _dumpAsString(str4, z, (ASN1Primitive) nextElement2, stringBuffer);
                } else {
                    _dumpAsString(str4, z, ((ASN1Encodable) nextElement2).toASN1Primitive(), stringBuffer);
                }
            }
        } else if (aSN1Primitive instanceof ASN1OctetString) {
            ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
            if (aSN1Primitive instanceof BEROctetString) {
                stringBuffer.append(str + "BER Constructed Octet String[" + aSN1OctetString.getOctets().length + "] ");
            } else {
                stringBuffer.append(str + "DER Octet String[" + aSN1OctetString.getOctets().length + "] ");
            }
            if (z) {
                stringBuffer.append(dumpBinaryDataAsString(str, aSN1OctetString.getOctets()));
            } else {
                stringBuffer.append(lineSeparator);
            }
        } else if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
            stringBuffer.append(str + "ObjectIdentifier(" + ((ASN1ObjectIdentifier) aSN1Primitive).getId() + ")" + lineSeparator);
        } else if (aSN1Primitive instanceof ASN1Boolean) {
            stringBuffer.append(str + "Boolean(" + ((ASN1Boolean) aSN1Primitive).isTrue() + ")" + lineSeparator);
        } else if (aSN1Primitive instanceof ASN1Integer) {
            stringBuffer.append(str + "Integer(" + ((ASN1Integer) aSN1Primitive).getValue() + ")" + lineSeparator);
        } else if (aSN1Primitive instanceof DERBitString) {
            DERBitString dERBitString = (DERBitString) aSN1Primitive;
            stringBuffer.append(str + "DER Bit String[" + dERBitString.getBytes().length + ", " + dERBitString.getPadBits() + "] ");
            if (z) {
                stringBuffer.append(dumpBinaryDataAsString(str, dERBitString.getBytes()));
            } else {
                stringBuffer.append(lineSeparator);
            }
        } else if (aSN1Primitive instanceof DERIA5String) {
            stringBuffer.append(str + "IA5String(" + ((DERIA5String) aSN1Primitive).getString() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof DERUTF8String) {
            stringBuffer.append(str + "UTF8String(" + ((DERUTF8String) aSN1Primitive).getString() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof DERPrintableString) {
            stringBuffer.append(str + "PrintableString(" + ((DERPrintableString) aSN1Primitive).getString() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof DERVisibleString) {
            stringBuffer.append(str + "VisibleString(" + ((DERVisibleString) aSN1Primitive).getString() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof DERBMPString) {
            stringBuffer.append(str + "BMPString(" + ((DERBMPString) aSN1Primitive).getString() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof DERT61String) {
            stringBuffer.append(str + "T61String(" + ((DERT61String) aSN1Primitive).getString() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof DERGraphicString) {
            stringBuffer.append(str + "GraphicString(" + ((DERGraphicString) aSN1Primitive).getString() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof DERVideotexString) {
            stringBuffer.append(str + "VideotexString(" + ((DERVideotexString) aSN1Primitive).getString() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof ASN1UTCTime) {
            stringBuffer.append(str + "UTCTime(" + ((ASN1UTCTime) aSN1Primitive).getTime() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof ASN1GeneralizedTime) {
            stringBuffer.append(str + "GeneralizedTime(" + ((ASN1GeneralizedTime) aSN1Primitive).getTime() + ") " + lineSeparator);
        } else if (aSN1Primitive instanceof BERApplicationSpecific) {
            stringBuffer.append(outputApplicationSpecific(ASN1Encoding.BER, str, z, aSN1Primitive, lineSeparator));
        } else if (aSN1Primitive instanceof DERApplicationSpecific) {
            stringBuffer.append(outputApplicationSpecific(ASN1Encoding.DER, str, z, aSN1Primitive, lineSeparator));
        } else if (aSN1Primitive instanceof ASN1Enumerated) {
            stringBuffer.append(str + "DER Enumerated(" + ((ASN1Enumerated) aSN1Primitive).getValue() + ")" + lineSeparator);
        } else if (aSN1Primitive instanceof DERExternal) {
            DERExternal dERExternal = (DERExternal) aSN1Primitive;
            stringBuffer.append(str + "External " + lineSeparator);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(TAB);
            String sb2 = sb.toString();
            if (dERExternal.getDirectReference() != null) {
                stringBuffer.append(sb2 + "Direct Reference: " + dERExternal.getDirectReference().getId() + lineSeparator);
            }
            if (dERExternal.getIndirectReference() != null) {
                stringBuffer.append(sb2 + "Indirect Reference: " + dERExternal.getIndirectReference().toString() + lineSeparator);
            }
            if (dERExternal.getDataValueDescriptor() != null) {
                _dumpAsString(sb2, z, dERExternal.getDataValueDescriptor(), stringBuffer);
            }
            stringBuffer.append(sb2 + "Encoding: " + dERExternal.getEncoding() + lineSeparator);
            _dumpAsString(sb2, z, dERExternal.getExternalContent(), stringBuffer);
        } else {
            stringBuffer.append(str + aSN1Primitive.toString() + lineSeparator);
        }
    }

    private static String outputApplicationSpecific(String str, String str2, boolean z, ASN1Primitive aSN1Primitive, String str3) {
        ASN1ApplicationSpecific instance = ASN1ApplicationSpecific.getInstance(aSN1Primitive);
        StringBuffer stringBuffer = new StringBuffer();
        if (instance.isConstructed()) {
            try {
                ASN1Sequence instance2 = ASN1Sequence.getInstance(instance.getObject(16));
                stringBuffer.append(str2 + str + " ApplicationSpecific[" + instance.getApplicationTag() + "]" + str3);
                Enumeration objects = instance2.getObjects();
                while (objects.hasMoreElements()) {
                    _dumpAsString(str2 + TAB, z, (ASN1Primitive) objects.nextElement(), stringBuffer);
                }
            } catch (IOException e) {
                stringBuffer.append(e);
            }
            return stringBuffer.toString();
        }
        return str2 + str + " ApplicationSpecific[" + instance.getApplicationTag() + "] (" + Strings.fromByteArray(Hex.encode(instance.getContents())) + ")" + str3;
    }

    public static String dumpAsString(Object obj) {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (obj instanceof ASN1Primitive) {
            _dumpAsString("", z, (ASN1Primitive) obj, stringBuffer);
        } else if (obj instanceof ASN1Encodable) {
            _dumpAsString("", z, ((ASN1Encodable) obj).toASN1Primitive(), stringBuffer);
        } else {
            return "unknown object type " + obj.toString();
        }
        return stringBuffer.toString();
    }

    private static String dumpBinaryDataAsString(String str, byte[] bArr) {
        String lineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = str + TAB;
        stringBuffer.append(lineSeparator);
        for (int i = 0; i < bArr.length; i += 32) {
            if (bArr.length - i > 32) {
                stringBuffer.append(str2);
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, 32)));
                stringBuffer.append(TAB);
                stringBuffer.append(calculateAscString(bArr, i, 32));
                stringBuffer.append(lineSeparator);
            } else {
                stringBuffer.append(str2);
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, bArr.length - i)));
                for (int length = bArr.length - i; length != 32; length++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append(TAB);
                stringBuffer.append(calculateAscString(bArr, i, bArr.length - i));
                stringBuffer.append(lineSeparator);
            }
        }
        return stringBuffer.toString();
    }

    private static String calculateAscString(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 != i + i2; i3++) {
            if (bArr[i3] >= 32 && bArr[i3] <= 126) {
                stringBuffer.append((char) bArr[i3]);
            }
        }
        return stringBuffer.toString();
    }
}
