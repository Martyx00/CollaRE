package com.facebook.react.views.picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.events.d;
import com.facebook.react.views.picker.a;

public abstract class ReactPickerManager extends SimpleViewManager<a> {
    @com.facebook.react.uimanager.a.a(a = "items")
    public void setItems(a aVar, ReadableArray readableArray) {
        if (readableArray != null) {
            ReadableMap[] readableMapArr = new ReadableMap[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                readableMapArr[i] = readableArray.getMap(i);
            }
            b bVar = new b(aVar.getContext(), readableMapArr);
            bVar.a(aVar.getPrimaryColor());
            aVar.setAdapter((SpinnerAdapter) bVar);
            return;
        }
        aVar.setAdapter((SpinnerAdapter) null);
    }

    @com.facebook.react.uimanager.a.a(a = "color", b = "Color")
    public void setColor(a aVar, Integer num) {
        aVar.setPrimaryColor(num);
        b bVar = (b) aVar.getAdapter();
        if (bVar != null) {
            bVar.a(num);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "prompt")
    public void setPrompt(a aVar, String str) {
        aVar.setPrompt(str);
    }

    @com.facebook.react.uimanager.a.a(a = "enabled", f = true)
    public void setEnabled(a aVar, boolean z) {
        aVar.setEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "selected")
    public void setSelected(a aVar, int i) {
        aVar.setStagedSelection(i);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(a aVar) {
        super.onAfterUpdateTransaction((View) aVar);
        aVar.a();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(af afVar, a aVar) {
        aVar.setOnSelectListener(new a(aVar, ((UIManagerModule) afVar.getNativeModule(UIManagerModule.class)).getEventDispatcher()));
    }

    private static class b extends ArrayAdapter<ReadableMap> {

        /* renamed from: a  reason: collision with root package name */
        private final LayoutInflater f3361a;

        /* renamed from: b  reason: collision with root package name */
        private Integer f3362b;

        public b(Context context, ReadableMap[] readableMapArr) {
            super(context, 0, readableMapArr);
            this.f3361a = (LayoutInflater) com.facebook.i.a.a.a(context.getSystemService("layout_inflater"));
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return a(i, view, viewGroup, false);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return a(i, view, viewGroup, true);
        }

        private View a(int i, View view, ViewGroup viewGroup, boolean z) {
            Integer num;
            ReadableMap readableMap = (ReadableMap) getItem(i);
            if (view == null) {
                view = this.f3361a.inflate(z ? 17367049 : 17367048, viewGroup, false);
            }
            TextView textView = (TextView) view;
            textView.setText(readableMap.getString("label"));
            if (!z && (num = this.f3362b) != null) {
                textView.setTextColor(num.intValue());
            } else if (readableMap.hasKey("color") && !readableMap.isNull("color")) {
                textView.setTextColor(readableMap.getInt("color"));
            }
            return view;
        }

        public void a(Integer num) {
            this.f3362b = num;
            notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public static class a implements a.AbstractC0058a {

        /* renamed from: a  reason: collision with root package name */
        private final a f3359a;

        /* renamed from: b  reason: collision with root package name */
        private final d f3360b;

        public a(a aVar, d dVar) {
            this.f3359a = aVar;
            this.f3360b = dVar;
        }

        @Override // com.facebook.react.views.picker.a.AbstractC0058a
        public void a(int i) {
            this.f3360b.a(new com.facebook.react.views.picker.a.a(this.f3359a.getId(), i));
        }
    }
}
