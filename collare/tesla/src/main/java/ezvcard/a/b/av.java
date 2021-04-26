package ezvcard.a.b;

import ezvcard.parameter.SoundType;
import ezvcard.property.Sound;

/* compiled from: SoundScribe */
public class av extends d<Sound, SoundType> {
    public av() {
        super(Sound.class, "SOUND");
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public SoundType b(String str) {
        return SoundType.b(str, null, null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public SoundType a(String str) {
        return SoundType.b(null, str, null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public SoundType c(String str) {
        return SoundType.a(null, null, str);
    }

    /* access modifiers changed from: protected */
    public Sound a(String str, SoundType soundType) {
        return new Sound(str, soundType);
    }

    /* access modifiers changed from: protected */
    public Sound a(byte[] bArr, SoundType soundType) {
        return new Sound(bArr, soundType);
    }
}
