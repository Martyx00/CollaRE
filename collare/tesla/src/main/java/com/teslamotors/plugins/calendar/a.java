package com.teslamotors.plugins.calendar;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CalendarData */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() {
        /* class com.teslamotors.plugins.calendar.a.AnonymousClass1 */

        /* renamed from: a */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        /* renamed from: a */
        public a[] newArray(int i) {
            return new a[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static final String f5476a = "a";

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f5477b = {"_id", "title", "eventLocation", "allDay", "calendar_color", "organizer", "calendar_id", "eventStatus", "description"};

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f5478c = {"begin", "end", "event_id"};

    /* renamed from: d  reason: collision with root package name */
    private static final String[] f5479d = {"_id", "calendar_displayName", "calendar_color"};
    private HashMap<Integer, C0149a> e;
    private boolean f;

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: private */
    /* compiled from: CalendarData */
    public class b implements Comparable<b> {

        /* renamed from: a  reason: collision with root package name */
        public long f5484a;

        /* renamed from: b  reason: collision with root package name */
        public String f5485b;

        /* renamed from: c  reason: collision with root package name */
        public long f5486c;

        /* renamed from: d  reason: collision with root package name */
        public long f5487d;
        public boolean e;
        public String f;
        public String g;
        public String h;
        public int i;
        public String j;

        public b(long j2, String str, long j3, long j4, boolean z, String str2, String str3, String str4, int i2, String str5) {
            this.f5484a = j2;
            this.f5485b = str;
            this.f5486c = j3;
            this.f5487d = j4;
            this.e = z;
            this.f = str2;
            this.g = str3;
            this.h = str4;
            this.i = i2;
            this.j = str5;
        }

        public b(Parcel parcel) {
            this.f5485b = d.d(parcel);
            this.f5486c = d.a(parcel).longValue();
            this.f5487d = d.a(parcel).longValue();
            this.e = d.c(parcel).booleanValue();
            this.f = d.d(parcel);
            this.g = d.d(parcel);
            this.h = d.d(parcel);
            this.i = d.b(parcel).intValue();
            this.f5484a = d.a(parcel).longValue();
            this.j = d.d(parcel);
        }

        /* access modifiers changed from: package-private */
        public void a(Parcel parcel) {
            d.a(parcel, this.f5485b);
            d.a(parcel, Long.valueOf(this.f5486c));
            d.a(parcel, Long.valueOf(this.f5487d));
            d.a(parcel, Boolean.valueOf(this.e));
            d.a(parcel, this.f);
            d.a(parcel, this.g);
            d.a(parcel, this.h);
            d.a(parcel, Integer.valueOf(this.i));
            d.a(parcel, Long.valueOf(this.f5484a));
            d.a(parcel, this.j);
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            boolean z = this.i == 2;
            try {
                jSONObject.put("name", this.f5485b);
                jSONObject.put("start", this.f5486c);
                jSONObject.put("end", this.f5487d);
                jSONObject.put("location", this.f);
                jSONObject.put("all_day", this.e);
                jSONObject.put("color", this.g);
                jSONObject.put("organizer", this.h);
                jSONObject.put("cancelled", z);
                jSONObject.put("tentative", false);
                jSONObject.put("description", this.j);
            } catch (Exception e2) {
                Log.e(a.f5476a, "CalendarEvent toJSON failure", e2);
            }
            return jSONObject;
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            long j2 = this.f5486c;
            long j3 = bVar.f5486c;
            return j2 == j3 ? (int) (this.f5484a - bVar.f5484a) : (int) (j2 - j3);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: com.teslamotors.plugins.calendar.a$a  reason: collision with other inner class name */
    /* compiled from: CalendarData */
    public class C0149a implements Comparable<C0149a> {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<b> f5480a = new ArrayList<>();

        /* renamed from: b  reason: collision with root package name */
        public String f5481b;

        /* renamed from: c  reason: collision with root package name */
        public String f5482c;

        /* renamed from: d  reason: collision with root package name */
        public int f5483d;
        private boolean f;

        public C0149a(int i, String str, String str2) {
            this.f5483d = i;
            this.f5481b = str;
            this.f5482c = str2;
            this.f = false;
        }

        public C0149a(Parcel parcel) {
            this.f5483d = d.b(parcel).intValue();
            this.f5481b = d.d(parcel);
            this.f5482c = d.d(parcel);
            int intValue = d.b(parcel).intValue();
            for (int i = 0; i < intValue; i++) {
                this.f5480a.add(new b(parcel));
            }
            this.f = false;
        }

        /* renamed from: a */
        public int compareTo(C0149a aVar) {
            return this.f5483d - aVar.f5483d;
        }

        public void a(b bVar) {
            this.f5480a.add(bVar);
            this.f = false;
        }

        public void a(Parcel parcel) {
            d.a(parcel, Integer.valueOf(this.f5483d));
            d.a(parcel, this.f5481b);
            d.a(parcel, this.f5482c);
            d.a(parcel, Integer.valueOf(this.f5480a.size()));
            Iterator<b> it = this.f5480a.iterator();
            while (it.hasNext()) {
                it.next().a(parcel);
            }
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", this.f5481b);
                jSONObject.put("color", this.f5482c);
                if (!this.f) {
                    Collections.sort(this.f5480a);
                    this.f = true;
                }
                JSONArray jSONArray = new JSONArray();
                Iterator<b> it = this.f5480a.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().a());
                }
                jSONObject.put("events", jSONArray);
            } catch (Exception e2) {
                Log.e(a.f5476a, "Calendar toJSON failure", e2);
            }
            return jSONObject;
        }
    }

    public a() {
        this.e = new HashMap<>();
        this.f = false;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean a() {
        return this.f;
    }

    public void a(int i, String str, String str2) {
        this.e.put(Integer.valueOf(i), new C0149a(i, str, str2));
    }

    public void a(int i, long j, String str, long j2, long j3, boolean z, String str2, String str3, String str4, int i2, String str5) {
        if (this.e.containsKey(Integer.valueOf(i))) {
            this.e.get(Integer.valueOf(i)).a(new b(j, str, j2, j3, z, str2, str3, str4, i2, str5));
        }
    }

    public JSONObject b() {
        JSONArray jSONArray = new JSONArray();
        ArrayList arrayList = new ArrayList(this.e.values());
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put(((C0149a) it.next()).a());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("calendars", jSONArray);
        } catch (Exception e2) {
            Log.e(f5476a, "CalendarData toJSON failure", e2);
        }
        return jSONObject;
    }

    public a(Parcel parcel) {
        int intValue = d.b(parcel).intValue();
        for (int i = 0; i < intValue; i++) {
            C0149a aVar = new C0149a(parcel);
            this.e.put(Integer.valueOf(aVar.f5483d), aVar);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        d.a(parcel, Integer.valueOf(this.e.size()));
        for (C0149a aVar : this.e.values()) {
            aVar.a(parcel);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x01e3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01e4 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.teslamotors.plugins.calendar.a a(android.content.Context r26) {
        /*
        // Method dump skipped, instructions count: 485
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teslamotors.plugins.calendar.a.a(android.content.Context):com.teslamotors.plugins.calendar.a");
    }

    static void a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            try {
                cursor.close();
            } catch (Exception e2) {
                Log.e(f5476a, "Failed to close cursor", e2);
            }
        }
    }
}
