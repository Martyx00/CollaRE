package org.spongycastle.crypto.params;

public class CramerShoupKeyParameters extends AsymmetricKeyParameter {
    private CramerShoupParameters params;

    protected CramerShoupKeyParameters(boolean z, CramerShoupParameters cramerShoupParameters) {
        super(z);
        this.params = cramerShoupParameters;
    }

    public CramerShoupParameters getParameters() {
        return this.params;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupKeyParameters)) {
            return false;
        }
        CramerShoupKeyParameters cramerShoupKeyParameters = (CramerShoupKeyParameters) obj;
        CramerShoupParameters cramerShoupParameters = this.params;
        if (cramerShoupParameters != null) {
            return cramerShoupParameters.equals(cramerShoupKeyParameters.getParameters());
        }
        if (cramerShoupKeyParameters.getParameters() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = !isPrivate();
        CramerShoupParameters cramerShoupParameters = this.params;
        return cramerShoupParameters != null ? i ^ cramerShoupParameters.hashCode() : i;
    }
}
