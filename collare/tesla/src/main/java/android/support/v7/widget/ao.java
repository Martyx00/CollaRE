package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.c;
import android.support.v4.widget.n;
import android.support.v7.a.a;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: SuggestionsAdapter */
class ao extends n implements View.OnClickListener {
    private final SearchManager j = ((SearchManager) this.f715d.getSystemService(FirebaseAnalytics.a.SEARCH));
    private final SearchView k;
    private final SearchableInfo l;
    private final Context m;
    private final WeakHashMap<String, Drawable.ConstantState> n;
    private final int o;
    private boolean p = false;
    private int q = 1;
    private ColorStateList r;
    private int s = -1;
    private int t = -1;
    private int u = -1;
    private int v = -1;
    private int w = -1;
    private int x = -1;

    @Override // android.support.v4.widget.f
    public boolean hasStableIds() {
        return false;
    }

    public ao(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.k = searchView;
        this.l = searchableInfo;
        this.o = searchView.getSuggestionCommitIconResId();
        this.m = context;
        this.n = weakHashMap;
    }

    public void a(int i) {
        this.q = i;
    }

    @Override // android.support.v4.widget.f, android.support.v4.widget.g.a
    public Cursor a(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        if (this.k.getVisibility() != 0 || this.k.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor a2 = a(this.l, charSequence2, 50);
            if (a2 != null) {
                a2.getCount();
                return a2;
            }
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
        }
        return null;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        d(a());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        d(a());
    }

    private void d(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null && !extras.getBoolean("in_progress")) {
        }
    }

    @Override // android.support.v4.widget.f, android.support.v4.widget.g.a
    public void a(Cursor cursor) {
        if (this.p) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.a(cursor);
            if (cursor != null) {
                this.s = cursor.getColumnIndex("suggest_text_1");
                this.t = cursor.getColumnIndex("suggest_text_2");
                this.u = cursor.getColumnIndex("suggest_text_2_url");
                this.v = cursor.getColumnIndex("suggest_icon_1");
                this.w = cursor.getColumnIndex("suggest_icon_2");
                this.x = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    @Override // android.support.v4.widget.f, android.support.v4.widget.n
    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a2 = super.a(context, cursor, viewGroup);
        a2.setTag(new a(a2));
        ((ImageView) a2.findViewById(a.f.edit_query)).setImageResource(this.o);
        return a2;
    }

    /* access modifiers changed from: private */
    /* compiled from: SuggestionsAdapter */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f1172a;

        /* renamed from: b  reason: collision with root package name */
        public final TextView f1173b;

        /* renamed from: c  reason: collision with root package name */
        public final ImageView f1174c;

        /* renamed from: d  reason: collision with root package name */
        public final ImageView f1175d;
        public final ImageView e;

        public a(View view) {
            this.f1172a = (TextView) view.findViewById(16908308);
            this.f1173b = (TextView) view.findViewById(16908309);
            this.f1174c = (ImageView) view.findViewById(16908295);
            this.f1175d = (ImageView) view.findViewById(16908296);
            this.e = (ImageView) view.findViewById(a.f.edit_query);
        }
    }

    @Override // android.support.v4.widget.f
    public void a(View view, Context context, Cursor cursor) {
        CharSequence charSequence;
        a aVar = (a) view.getTag();
        int i = this.x;
        int i2 = i != -1 ? cursor.getInt(i) : 0;
        if (aVar.f1172a != null) {
            a(aVar.f1172a, a(cursor, this.s));
        }
        if (aVar.f1173b != null) {
            String a2 = a(cursor, this.u);
            if (a2 != null) {
                charSequence = b((CharSequence) a2);
            } else {
                charSequence = a(cursor, this.t);
            }
            if (TextUtils.isEmpty(charSequence)) {
                if (aVar.f1172a != null) {
                    aVar.f1172a.setSingleLine(false);
                    aVar.f1172a.setMaxLines(2);
                }
            } else if (aVar.f1172a != null) {
                aVar.f1172a.setSingleLine(true);
                aVar.f1172a.setMaxLines(1);
            }
            a(aVar.f1173b, charSequence);
        }
        if (aVar.f1174c != null) {
            a(aVar.f1174c, e(cursor), 4);
        }
        if (aVar.f1175d != null) {
            a(aVar.f1175d, f(cursor), 8);
        }
        int i3 = this.q;
        if (i3 == 2 || (i3 == 1 && (i2 & 1) != 0)) {
            aVar.e.setVisibility(0);
            aVar.e.setTag(aVar.f1172a.getText());
            aVar.e.setOnClickListener(this);
            return;
        }
        aVar.e.setVisibility(8);
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.k.a((CharSequence) tag);
        }
    }

    private CharSequence b(CharSequence charSequence) {
        if (this.r == null) {
            TypedValue typedValue = new TypedValue();
            this.f715d.getTheme().resolveAttribute(a.C0020a.textColorSearchUrl, typedValue, true);
            this.r = this.f715d.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.r, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private void a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private Drawable e(Cursor cursor) {
        int i = this.v;
        if (i == -1) {
            return null;
        }
        Drawable a2 = a(cursor.getString(i));
        if (a2 != null) {
            return a2;
        }
        return g(cursor);
    }

    private Drawable f(Cursor cursor) {
        int i = this.w;
        if (i == -1) {
            return null;
        }
        return a(cursor.getString(i));
    }

    private void a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    @Override // android.support.v4.widget.f, android.support.v4.widget.g.a
    public CharSequence c(Cursor cursor) {
        String a2;
        String a3;
        if (cursor == null) {
            return null;
        }
        String a4 = a(cursor, "suggest_intent_query");
        if (a4 != null) {
            return a4;
        }
        if (this.l.shouldRewriteQueryFromData() && (a3 = a(cursor, "suggest_intent_data")) != null) {
            return a3;
        }
        if (!this.l.shouldRewriteQueryFromText() || (a2 = a(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return a2;
    }

    @Override // android.support.v4.widget.f
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View a2 = a(this.f715d, this.f714c, viewGroup);
            if (a2 != null) {
                ((a) a2.getTag()).f1172a.setText(e.toString());
            }
            return a2;
        }
    }

    @Override // android.support.v4.widget.f
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View b2 = b(this.f715d, this.f714c, viewGroup);
            if (b2 != null) {
                ((a) b2.getTag()).f1172a.setText(e.toString());
            }
            return b2;
        }
    }

    private Drawable a(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.m.getPackageName() + "/" + parseInt;
            Drawable b2 = b(str2);
            if (b2 != null) {
                return b2;
            }
            Drawable a2 = c.a(this.m, parseInt);
            a(str2, a2);
            return a2;
        } catch (NumberFormatException unused) {
            Drawable b3 = b(str);
            if (b3 != null) {
                return b3;
            }
            Drawable b4 = b(Uri.parse(str));
            a(str, b4);
            return b4;
        } catch (Resources.NotFoundException unused2) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        }
    }

    private Drawable b(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return a(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            } else {
                InputStream openInputStream = this.m.getContentResolver().openInputStream(uri);
                if (openInputStream != null) {
                    try {
                        return Drawable.createFromStream(openInputStream, null);
                    } finally {
                        try {
                            openInputStream.close();
                        } catch (IOException e) {
                            Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e);
                        }
                    }
                } else {
                    throw new FileNotFoundException("Failed to open " + uri);
                }
            }
        } catch (FileNotFoundException e2) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e2.getMessage());
            return null;
        }
    }

    private Drawable b(String str) {
        Drawable.ConstantState constantState = this.n.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private void a(String str, Drawable drawable) {
        if (drawable != null) {
            this.n.put(str, drawable.getConstantState());
        }
    }

    private Drawable g(Cursor cursor) {
        Drawable a2 = a(this.l.getSearchActivity());
        if (a2 != null) {
            return a2;
        }
        return this.f715d.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable a(ComponentName componentName) {
        String flattenToShortString = componentName.flattenToShortString();
        Drawable.ConstantState constantState = null;
        if (this.n.containsKey(flattenToShortString)) {
            Drawable.ConstantState constantState2 = this.n.get(flattenToShortString);
            if (constantState2 == null) {
                return null;
            }
            return constantState2.newDrawable(this.m.getResources());
        }
        Drawable b2 = b(componentName);
        if (b2 != null) {
            constantState = b2.getConstantState();
        }
        this.n.put(flattenToShortString, constantState);
        return b2;
    }

    private Drawable b(ComponentName componentName) {
        PackageManager packageManager = this.f715d.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("SuggestionsAdapter", e.toString());
            return null;
        }
    }

    public static String a(Cursor cursor, String str) {
        return a(cursor, cursor.getColumnIndex(str));
    }

    private static String a(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Drawable a(Uri uri) {
        int i;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources resourcesForApplication = this.f715d.getPackageManager().getResourcesForApplication(authority);
                List<String> pathSegments = uri.getPathSegments();
                if (pathSegments != null) {
                    int size = pathSegments.size();
                    if (size == 1) {
                        try {
                            i = Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if (size == 2) {
                        i = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if (i != 0) {
                        return resourcesForApplication.getDrawable(i);
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        } else {
            throw new FileNotFoundException("No authority: " + uri);
        }
    }

    /* access modifiers changed from: package-private */
    public Cursor a(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme(FirebaseAnalytics.b.CONTENT).authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
            strArr = null;
        }
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.f715d.getContentResolver().query(fragment.build(), null, suggestSelection, strArr, null);
    }
}
