package org.spongycastle.crypto.params;

public class DHKeyParameters extends AsymmetricKeyParameter {
    private DHParameters params;

    protected DHKeyParameters(boolean z, DHParameters dHParameters) {
        super(z);
        this.params = dHParameters;
    }

    public DHParameters getParameters() {
        return this.params;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHKeyParameters)) {
            return false;
        }
        DHKeyParameters dHKeyParameters = (DHKeyParameters) obj;
        DHParameters dHParameters = this.params;
        if (dHParameters != null) {
            return dHParameters.equals(dHKeyParameters.getParameters());
        }
        if (dHKeyParameters.getParameters() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = !isPrivate();
        DHParameters dHParameters = this.params;
        return dHParameters != null ? i ^ dHParameters.hashCode() : i;
    }
}
