package com.teslamotors.share;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement;
import ezvcard.VCard;
import ezvcard.a;
import ezvcard.property.Address;
import ezvcard.property.Email;
import ezvcard.property.Telephone;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VCardParser */
public class d {
    public static JSONObject a(Context context, Intent intent) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Uri uri = intent.getExtras() != null ? (Uri) intent.getExtras().get("android.intent.extra.STREAM") : null;
            if (uri != null) {
                InputStream openInputStream = contentResolver.openInputStream(uri);
                VCard a2 = a.a(openInputStream).a();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("first_name", a2.d().getGiven());
                jSONObject.put("last_name", a2.d().getFamily());
                if (a2.h() != null) {
                    jSONObject.put("org_name", TextUtils.join(",", a2.h().getValues()));
                }
                List<Telephone> g = a2.g();
                if (g != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (Telephone telephone : g) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put(AppMeasurement.Param.TYPE, TextUtils.join(",", telephone.getTypes()));
                        jSONObject2.put("phone_number", telephone.getText());
                        jSONArray.put(jSONObject2);
                    }
                    jSONObject.put("phone_numbers", jSONArray);
                }
                List<Email> f = a2.f();
                if (f != null && !f.isEmpty()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (Email email : f) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put(AppMeasurement.Param.TYPE, TextUtils.join(",", email.getTypes()));
                        jSONObject3.put("email_address", email.getValue());
                        jSONArray2.put(jSONObject3);
                    }
                    jSONObject.put("email_addresses", jSONArray2);
                }
                List<Address> e = a2.e();
                if (e != null && !e.isEmpty()) {
                    JSONArray jSONArray3 = new JSONArray();
                    for (Address address : e) {
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put(AppMeasurement.Param.TYPE, TextUtils.join(",", address.getTypes()));
                        jSONObject4.put("address_string", address.getStreetAddress());
                        jSONObject4.put("city", address.getLocality());
                        jSONObject4.put("country", address.getCountry());
                        jSONObject4.put("postal_code", address.getPostalCode());
                        jSONObject4.put("state", address.getRegion());
                        jSONArray3.put(jSONObject4);
                    }
                    jSONObject.put("postal_addresses", jSONArray3);
                }
                openInputStream.close();
                return jSONObject;
            }
        } catch (IOException | NullPointerException | JSONException unused) {
        }
        return null;
    }
}
