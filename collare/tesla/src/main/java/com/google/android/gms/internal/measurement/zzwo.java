package com.google.android.gms.internal.measurement;

import com.google.android.gms.tagmanager.zzdi;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

final class zzwo implements zzwp {
    private HttpURLConnection zzbmn;
    private InputStream zzbmo = null;

    zzwo() {
    }

    @Override // com.google.android.gms.internal.measurement.zzwp
    public final void close() {
        HttpURLConnection httpURLConnection = this.zzbmn;
        try {
            if (this.zzbmo != null) {
                this.zzbmo.close();
            }
        } catch (IOException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzdi.zza(valueOf.length() != 0 ? "HttpUrlConnectionNetworkClient: Error when closing http input stream: ".concat(valueOf) : new String("HttpUrlConnectionNetworkClient: Error when closing http input stream: "), e);
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzwp
    public final InputStream zzem(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        this.zzbmn = httpURLConnection;
        HttpURLConnection httpURLConnection2 = this.zzbmn;
        int responseCode = httpURLConnection2.getResponseCode();
        if (responseCode == 200) {
            this.zzbmo = httpURLConnection2.getInputStream();
            return this.zzbmo;
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("Bad response: ");
        sb.append(responseCode);
        String sb2 = sb.toString();
        if (responseCode == 404) {
            throw new FileNotFoundException(sb2);
        } else if (responseCode == 503) {
            throw new zzwr(sb2);
        } else {
            throw new IOException(sb2);
        }
    }
}
