package ezvcard.a.b;

import com.github.a.a.b.e;
import ezvcard.VCard;
import ezvcard.VCardDataType;
import ezvcard.VCardVersion;
import ezvcard.a.b;
import ezvcard.a.c;
import ezvcard.parameter.VCardParameters;
import ezvcard.property.Agent;
import ezvcard.property.VCardProperty;

/* compiled from: AgentScribe */
public class b extends bg<Agent> {
    /* access modifiers changed from: protected */
    @Override // ezvcard.a.b.bg
    public VCardDataType a(VCardVersion vCardVersion) {
        return null;
    }

    public b() {
        super(Agent.class, "AGENT");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Agent b(String str, VCardDataType vCardDataType, VCardParameters vCardParameters, c cVar) {
        Agent agent = new Agent();
        if (vCardDataType != null) {
            agent.setUrl(e.a(str));
            return agent;
        }
        throw new ezvcard.a.b(new a(agent));
    }

    /* access modifiers changed from: private */
    /* compiled from: AgentScribe */
    public static class a implements b.a {

        /* renamed from: a  reason: collision with root package name */
        private final Agent f5710a;

        public a(Agent agent) {
            this.f5710a = agent;
        }

        @Override // ezvcard.a.b.a
        public void a(VCard vCard) {
            this.f5710a.setVCard(vCard);
        }

        @Override // ezvcard.a.b.a
        public VCardProperty a() {
            return this.f5710a;
        }
    }
}
