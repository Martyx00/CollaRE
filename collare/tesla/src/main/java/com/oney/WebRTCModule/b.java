package com.oney.WebRTCModule;

import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.measurement.AppMeasurement;
import java.nio.charset.Charset;
import org.spongycastle.i18n.TextBundle;
import org.webrtc.DataChannel;

/* access modifiers changed from: package-private */
/* compiled from: DataChannelObserver */
public class b implements DataChannel.Observer {

    /* renamed from: a  reason: collision with root package name */
    private final int f4702a;

    /* renamed from: b  reason: collision with root package name */
    private final DataChannel f4703b;

    /* renamed from: c  reason: collision with root package name */
    private final int f4704c;

    /* renamed from: d  reason: collision with root package name */
    private final WebRTCModule f4705d;

    @Override // org.webrtc.DataChannel.Observer
    public void onBufferedAmountChange(long j) {
    }

    b(WebRTCModule webRTCModule, int i, int i2, DataChannel dataChannel) {
        this.f4705d = webRTCModule;
        this.f4704c = i;
        this.f4702a = i2;
        this.f4703b = dataChannel;
    }

    private String a(DataChannel.State state) {
        switch (state) {
            case CONNECTING:
                return "connecting";
            case OPEN:
                return "open";
            case CLOSING:
                return "closing";
            case CLOSED:
                return "closed";
            default:
                return null;
        }
    }

    @Override // org.webrtc.DataChannel.Observer
    public void onMessage(DataChannel.Buffer buffer) {
        byte[] bArr;
        String str;
        String str2;
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.f4702a);
        createMap.putInt("peerConnectionId", this.f4704c);
        if (buffer.data.hasArray()) {
            bArr = buffer.data.array();
        } else {
            bArr = new byte[buffer.data.remaining()];
            buffer.data.get(bArr);
        }
        if (buffer.binary) {
            str = "binary";
            str2 = Base64.encodeToString(bArr, 2);
        } else {
            str = TextBundle.TEXT_ENTRY;
            str2 = new String(bArr, Charset.forName("UTF-8"));
        }
        createMap.putString(AppMeasurement.Param.TYPE, str);
        createMap.putString("data", str2);
        this.f4705d.sendEvent("dataChannelReceiveMessage", createMap);
    }

    @Override // org.webrtc.DataChannel.Observer
    public void onStateChange() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.f4702a);
        createMap.putInt("peerConnectionId", this.f4704c);
        createMap.putString("state", a(this.f4703b.state()));
        this.f4705d.sendEvent("dataChannelStateChanged", createMap);
    }
}
