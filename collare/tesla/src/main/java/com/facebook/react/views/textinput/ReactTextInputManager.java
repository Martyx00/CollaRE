package com.facebook.react.views.textinput;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.e;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.o;
import com.facebook.react.views.scroll.i;
import com.facebook.react.views.text.q;
import com.facebook.react.views.text.x;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.crypto.tls.CipherSuite;

@com.facebook.react.module.a.a(a = ReactTextInputManager.REACT_CLASS)
public class ReactTextInputManager extends BaseViewManager<c, h> {
    private static final int BLUR_TEXT_INPUT = 2;
    private static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];
    private static final int FOCUS_TEXT_INPUT = 1;
    private static final int IME_ACTION_ID = 1648;
    private static final int INPUT_TYPE_KEYBOARD_DECIMAL_PAD = 8194;
    private static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    private static final int INPUT_TYPE_KEYBOARD_NUMBER_PAD = 2;
    private static final String KEYBOARD_TYPE_DECIMAL_PAD = "decimal-pad";
    private static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    private static final int KEYBOARD_TYPE_FLAGS = 12339;
    private static final String KEYBOARD_TYPE_NUMBER_PAD = "number-pad";
    private static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    private static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    private static final String KEYBOARD_TYPE_VISIBLE_PASSWORD = "visible-password";
    private static final int PASSWORD_VISIBILITY_FLAG = 16;
    protected static final String REACT_CLASS = "AndroidTextInput";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    public static final String TAG = "ReactTextInputManager";
    private static final int UNSET = -1;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public c createViewInstance(af afVar) {
        c cVar = new c(afVar);
        cVar.setInputType(cVar.getInputType() & -131073);
        cVar.setReturnKeyType("done");
        return cVar;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public h createShadowNodeInstance() {
        return new m();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends h> getShadowNodeClass() {
        return m.class;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return e.c().a("topSubmitEditing", e.a("phasedRegistrationNames", e.a("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture"))).a("topEndEditing", e.a("phasedRegistrationNames", e.a("bubbled", "onEndEditing", "captured", "onEndEditingCapture"))).a("topTextInput", e.a("phasedRegistrationNames", e.a("bubbled", "onTextInput", "captured", "onTextInputCapture"))).a("topFocus", e.a("phasedRegistrationNames", e.a("bubbled", "onFocus", "captured", "onFocusCapture"))).a("topBlur", e.a("phasedRegistrationNames", e.a("bubbled", "onBlur", "captured", "onBlurCapture"))).a("topKeyPress", e.a("phasedRegistrationNames", e.a("bubbled", "onKeyPress", "captured", "onKeyPressCapture"))).a();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return e.c().a(i.a(i.SCROLL), e.a("registrationName", "onScroll")).a();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return e.a("focusTextInput", 1, "blurTextInput", 2);
    }

    public void receiveCommand(c cVar, int i, ReadableArray readableArray) {
        switch (i) {
            case 1:
                cVar.b();
                return;
            case 2:
                cVar.c();
                return;
            default:
                return;
        }
    }

    public void updateExtraData(c cVar, Object obj) {
        if (obj instanceof q) {
            q qVar = (q) obj;
            cVar.setPadding((int) qVar.d(), (int) qVar.e(), (int) qVar.f(), (int) qVar.g());
            if (qVar.c()) {
                x.a(qVar.a(), cVar);
            }
            cVar.a(qVar);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "fontSize", d = 14.0f)
    public void setFontSize(c cVar, float f) {
        cVar.setFontSize(f);
    }

    @com.facebook.react.uimanager.a.a(a = "fontFamily")
    public void setFontFamily(c cVar, String str) {
        cVar.setTypeface(com.facebook.react.views.text.i.a().a(str, cVar.getTypeface() != null ? cVar.getTypeface().getStyle() : 0, cVar.getContext().getAssets()));
    }

    @com.facebook.react.uimanager.a.a(a = "maxFontSizeMultiplier", d = Float.NaN)
    public void setMaxFontSizeMultiplier(c cVar, float f) {
        cVar.setMaxFontSizeMultiplier(f);
    }

    @com.facebook.react.uimanager.a.a(a = "fontWeight")
    public void setFontWeight(c cVar, String str) {
        int i = -1;
        int parseNumericFontWeight = str != null ? parseNumericFontWeight(str) : -1;
        if (parseNumericFontWeight >= 500 || "bold".equals(str)) {
            i = 1;
        } else if ("normal".equals(str) || (parseNumericFontWeight != -1 && parseNumericFontWeight < 500)) {
            i = 0;
        }
        Typeface typeface = cVar.getTypeface();
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        if (i != typeface.getStyle()) {
            cVar.setTypeface(typeface, i);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "fontStyle")
    public void setFontStyle(c cVar, String str) {
        int i;
        if ("italic".equals(str)) {
            i = 2;
        } else {
            i = "normal".equals(str) ? 0 : -1;
        }
        Typeface typeface = cVar.getTypeface();
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        if (i != typeface.getStyle()) {
            cVar.setTypeface(typeface, i);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "selection")
    public void setSelection(c cVar, ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("start") && readableMap.hasKey("end")) {
            cVar.setSelection(readableMap.getInt("start"), readableMap.getInt("end"));
        }
    }

    @com.facebook.react.uimanager.a.a(a = "importantForAutofill")
    public void setImportantForAutofill(c cVar, String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            int i = 0;
            if ("no".equals(str)) {
                i = 2;
            } else if ("noExcludeDescendants".equals(str)) {
                i = 8;
            } else if ("yes".equals(str)) {
                i = 1;
            } else if ("yesExcludeDescendants".equals(str)) {
                i = 4;
            }
            cVar.setImportantForAutofill(i);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "onSelectionChange", f = false)
    public void setOnSelectionChange(c cVar, boolean z) {
        if (z) {
            cVar.setSelectionWatcher(new c(cVar));
        } else {
            cVar.setSelectionWatcher(null);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "blurOnSubmit")
    public void setBlurOnSubmit(c cVar, Boolean bool) {
        cVar.setBlurOnSubmit(bool);
    }

    @com.facebook.react.uimanager.a.a(a = "onContentSizeChange", f = false)
    public void setOnContentSizeChange(c cVar, boolean z) {
        if (z) {
            cVar.setContentSizeWatcher(new a(cVar));
        } else {
            cVar.setContentSizeWatcher(null);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "onScroll", f = false)
    public void setOnScroll(c cVar, boolean z) {
        if (z) {
            cVar.setScrollWatcher(new b(cVar));
        } else {
            cVar.setScrollWatcher(null);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "onKeyPress", f = false)
    public void setOnKeyPress(c cVar, boolean z) {
        cVar.setOnKeyPress(z);
    }

    @com.facebook.react.uimanager.a.a(a = "letterSpacing", d = BitmapDescriptorFactory.HUE_RED)
    public void setLetterSpacing(c cVar, float f) {
        cVar.setLetterSpacingPt(f);
    }

    @com.facebook.react.uimanager.a.a(a = "allowFontScaling", f = true)
    public void setAllowFontScaling(c cVar, boolean z) {
        cVar.setAllowFontScaling(z);
    }

    @com.facebook.react.uimanager.a.a(a = "placeholder")
    public void setPlaceholder(c cVar, String str) {
        cVar.setHint(str);
    }

    @com.facebook.react.uimanager.a.a(a = "placeholderTextColor", b = "Color")
    public void setPlaceholderTextColor(c cVar, Integer num) {
        if (num == null) {
            cVar.setHintTextColor(com.facebook.react.views.text.d.a(cVar.getContext()));
        } else {
            cVar.setHintTextColor(num.intValue());
        }
    }

    @com.facebook.react.uimanager.a.a(a = "selectionColor", b = "Color")
    public void setSelectionColor(c cVar, Integer num) {
        if (num == null) {
            cVar.setHighlightColor(com.facebook.react.views.text.d.c(cVar.getContext()));
        } else {
            cVar.setHighlightColor(num.intValue());
        }
        setCursorColor(cVar, num);
    }

    private void setCursorColor(c cVar, Integer num) {
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            int i = declaredField.getInt(cVar);
            if (i != 0) {
                Drawable a2 = android.support.v4.content.c.a(cVar.getContext(), i);
                if (num != null) {
                    a2.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                }
                Drawable[] drawableArr = {a2, a2};
                Field declaredField2 = TextView.class.getDeclaredField("mEditor");
                declaredField2.setAccessible(true);
                Object obj = declaredField2.get(cVar);
                Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
                declaredField3.setAccessible(true);
                declaredField3.set(obj, drawableArr);
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }

    @com.facebook.react.uimanager.a.a(a = "mostRecentEventCount", e = 0)
    public void setMostRecentEventCount(c cVar, int i) {
        cVar.setMostRecentEventCount(i);
    }

    @com.facebook.react.uimanager.a.a(a = "caretHidden", f = false)
    public void setCaretHidden(c cVar, boolean z) {
        cVar.setCursorVisible(!z);
    }

    @com.facebook.react.uimanager.a.a(a = "contextMenuHidden", f = false)
    public void setContextMenuHidden(c cVar, final boolean z) {
        cVar.setOnLongClickListener(new View.OnLongClickListener() {
            /* class com.facebook.react.views.textinput.ReactTextInputManager.AnonymousClass1 */

            public boolean onLongClick(View view) {
                return z;
            }
        });
    }

    @com.facebook.react.uimanager.a.a(a = "selectTextOnFocus", f = false)
    public void setSelectTextOnFocus(c cVar, boolean z) {
        cVar.setSelectAllOnFocus(z);
    }

    @com.facebook.react.uimanager.a.a(a = "color", b = "Color")
    public void setColor(c cVar, Integer num) {
        if (num == null) {
            cVar.setTextColor(com.facebook.react.views.text.d.b(cVar.getContext()));
        } else {
            cVar.setTextColor(num.intValue());
        }
    }

    @com.facebook.react.uimanager.a.a(a = "underlineColorAndroid", b = "Color")
    public void setUnderlineColor(c cVar, Integer num) {
        Drawable background = cVar.getBackground();
        if (background.getConstantState() != null) {
            try {
                background = background.mutate();
            } catch (NullPointerException e) {
                com.facebook.common.e.a.c(TAG, "NullPointerException when setting underlineColorAndroid for TextInput", e);
            }
        }
        if (num == null) {
            background.clearColorFilter();
        } else {
            background.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "textAlign")
    public void setTextAlign(c cVar, String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                cVar.setJustificationMode(1);
            }
            cVar.setGravityHorizontal(3);
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            cVar.setJustificationMode(0);
        }
        if (str == null || "auto".equals(str)) {
            cVar.setGravityHorizontal(0);
        } else if ("left".equals(str)) {
            cVar.setGravityHorizontal(3);
        } else if ("right".equals(str)) {
            cVar.setGravityHorizontal(5);
        } else if ("center".equals(str)) {
            cVar.setGravityHorizontal(1);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + str);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "textAlignVertical")
    public void setTextAlignVertical(c cVar, String str) {
        if (str == null || "auto".equals(str)) {
            cVar.setGravityVertical(0);
        } else if ("top".equals(str)) {
            cVar.setGravityVertical(48);
        } else if ("bottom".equals(str)) {
            cVar.setGravityVertical(80);
        } else if ("center".equals(str)) {
            cVar.setGravityVertical(16);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + str);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "inlineImageLeft")
    public void setInlineImageLeft(c cVar, String str) {
        cVar.setCompoundDrawablesWithIntrinsicBounds(com.facebook.react.views.b.c.a().a(cVar.getContext(), str), 0, 0, 0);
    }

    @com.facebook.react.uimanager.a.a(a = "inlineImagePadding")
    public void setInlineImagePadding(c cVar, int i) {
        cVar.setCompoundDrawablePadding(i);
    }

    @com.facebook.react.uimanager.a.a(a = "editable", f = true)
    public void setEditable(c cVar, boolean z) {
        cVar.setEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "numberOfLines", e = 1)
    public void setNumLines(c cVar, int i) {
        cVar.setLines(i);
    }

    @com.facebook.react.uimanager.a.a(a = "maxLength")
    public void setMaxLength(c cVar, Integer num) {
        InputFilter[] filters = cVar.getFilters();
        InputFilter[] inputFilterArr = EMPTY_FILTERS;
        if (num == null) {
            if (filters.length > 0) {
                LinkedList linkedList = new LinkedList();
                for (int i = 0; i < filters.length; i++) {
                    if (!(filters[i] instanceof InputFilter.LengthFilter)) {
                        linkedList.add(filters[i]);
                    }
                }
                if (!linkedList.isEmpty()) {
                    inputFilterArr = (InputFilter[]) linkedList.toArray(new InputFilter[linkedList.size()]);
                }
            }
        } else if (filters.length > 0) {
            boolean z = false;
            for (int i2 = 0; i2 < filters.length; i2++) {
                if (filters[i2] instanceof InputFilter.LengthFilter) {
                    filters[i2] = new InputFilter.LengthFilter(num.intValue());
                    z = true;
                }
            }
            if (!z) {
                inputFilterArr = new InputFilter[(filters.length + 1)];
                System.arraycopy(filters, 0, inputFilterArr, 0, filters.length);
                filters[filters.length] = new InputFilter.LengthFilter(num.intValue());
            } else {
                inputFilterArr = filters;
            }
        } else {
            inputFilterArr = new InputFilter[]{new InputFilter.LengthFilter(num.intValue())};
        }
        cVar.setFilters(inputFilterArr);
    }

    @com.facebook.react.uimanager.a.a(a = "autoComplete")
    public void setTextContentType(c cVar, String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            if (str == null) {
                cVar.setImportantForAutofill(2);
            } else if ("username".equals(str)) {
                cVar.setAutofillHints(new String[]{"username"});
            } else if ("password".equals(str)) {
                cVar.setAutofillHints(new String[]{"password"});
            } else if (Scopes.EMAIL.equals(str)) {
                cVar.setAutofillHints(new String[]{"emailAddress"});
            } else if ("name".equals(str)) {
                cVar.setAutofillHints(new String[]{"name"});
            } else if ("tel".equals(str)) {
                cVar.setAutofillHints(new String[]{"phone"});
            } else if ("street-address".equals(str)) {
                cVar.setAutofillHints(new String[]{"postalAddress"});
            } else if ("postal-code".equals(str)) {
                cVar.setAutofillHints(new String[]{"postalCode"});
            } else if ("cc-number".equals(str)) {
                cVar.setAutofillHints(new String[]{"creditCardNumber"});
            } else if ("cc-csc".equals(str)) {
                cVar.setAutofillHints(new String[]{"creditCardSecurityCode"});
            } else if ("cc-exp".equals(str)) {
                cVar.setAutofillHints(new String[]{"creditCardExpirationDate"});
            } else if ("cc-exp-month".equals(str)) {
                cVar.setAutofillHints(new String[]{"creditCardExpirationMonth"});
            } else if ("cc-exp-year".equals(str)) {
                cVar.setAutofillHints(new String[]{"creditCardExpirationYear"});
            } else if ("off".equals(str)) {
                cVar.setImportantForAutofill(2);
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid autocomplete option: " + str);
            }
        }
    }

    @com.facebook.react.uimanager.a.a(a = "autoCorrect")
    public void setAutoCorrect(c cVar, Boolean bool) {
        updateStagedInputTypeFlag(cVar, 557056, bool != null ? bool.booleanValue() ? 32768 : PKIFailureInfo.signerNotTrusted : 0);
    }

    @com.facebook.react.uimanager.a.a(a = "multiline", f = false)
    public void setMultiline(c cVar, boolean z) {
        int i = 0;
        int i2 = z ? 0 : PKIFailureInfo.unsupportedVersion;
        if (z) {
            i = PKIFailureInfo.unsupportedVersion;
        }
        updateStagedInputTypeFlag(cVar, i2, i);
    }

    @com.facebook.react.uimanager.a.a(a = "secureTextEntry", f = false)
    public void setSecureTextEntry(c cVar, boolean z) {
        int i = 0;
        int i2 = z ? 0 : CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA;
        if (z) {
            i = 128;
        }
        updateStagedInputTypeFlag(cVar, i2, i);
        checkPasswordType(cVar);
    }

    @com.facebook.react.uimanager.a.a(a = "autoCapitalize")
    public void setAutoCapitalize(c cVar, int i) {
        updateStagedInputTypeFlag(cVar, 28672, i);
    }

    @com.facebook.react.uimanager.a.a(a = "keyboardType")
    public void setKeyboardType(c cVar, String str) {
        int i;
        if (KEYBOARD_TYPE_NUMERIC.equalsIgnoreCase(str)) {
            i = INPUT_TYPE_KEYBOARD_NUMBERED;
        } else if (KEYBOARD_TYPE_NUMBER_PAD.equalsIgnoreCase(str)) {
            i = 2;
        } else if (KEYBOARD_TYPE_DECIMAL_PAD.equalsIgnoreCase(str)) {
            i = INPUT_TYPE_KEYBOARD_DECIMAL_PAD;
        } else if (KEYBOARD_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(str)) {
            i = 33;
        } else if (KEYBOARD_TYPE_PHONE_PAD.equalsIgnoreCase(str)) {
            i = 3;
        } else {
            i = KEYBOARD_TYPE_VISIBLE_PASSWORD.equalsIgnoreCase(str) ? CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA : 1;
        }
        updateStagedInputTypeFlag(cVar, KEYBOARD_TYPE_FLAGS, i);
        checkPasswordType(cVar);
    }

    @com.facebook.react.uimanager.a.a(a = "returnKeyType")
    public void setReturnKeyType(c cVar, String str) {
        cVar.setReturnKeyType(str);
    }

    @com.facebook.react.uimanager.a.a(a = "disableFullscreenUI", f = false)
    public void setDisableFullscreenUI(c cVar, boolean z) {
        cVar.setDisableFullscreenUI(z);
    }

    @com.facebook.react.uimanager.a.a(a = "returnKeyLabel")
    public void setReturnKeyLabel(c cVar, String str) {
        cVar.setImeActionLabel(str, IME_ACTION_ID);
    }

    @com.facebook.react.uimanager.a.b(a = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"}, c = Float.NaN)
    public void setBorderRadius(c cVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        if (i == 0) {
            cVar.setBorderRadius(f);
        } else {
            cVar.a(f, i - 1);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "borderStyle")
    public void setBorderStyle(c cVar, String str) {
        cVar.setBorderStyle(str);
    }

    @com.facebook.react.uimanager.a.b(a = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"}, c = Float.NaN)
    public void setBorderWidth(c cVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        cVar.a(SPACING_TYPES[i], f);
    }

    @com.facebook.react.uimanager.a.b(a = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"}, b = "Color")
    public void setBorderColor(c cVar, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        cVar.a(SPACING_TYPES[i], intValue, f);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(c cVar) {
        super.onAfterUpdateTransaction((View) cVar);
        cVar.a();
    }

    private static void checkPasswordType(c cVar) {
        if ((cVar.getStagedInputType() & INPUT_TYPE_KEYBOARD_NUMBERED) != 0 && (cVar.getStagedInputType() & 128) != 0) {
            updateStagedInputTypeFlag(cVar, 128, 16);
        }
    }

    private static int parseNumericFontWeight(String str) {
        if (str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') {
            return -1;
        }
        return (str.charAt(0) - '0') * 100;
    }

    private static void updateStagedInputTypeFlag(c cVar, int i, int i2) {
        cVar.setStagedInputType(((~i) & cVar.getStagedInputType()) | i2);
    }

    /* access modifiers changed from: private */
    public class d implements TextWatcher {

        /* renamed from: b  reason: collision with root package name */
        private com.facebook.react.uimanager.events.d f3508b;

        /* renamed from: c  reason: collision with root package name */
        private c f3509c;

        /* renamed from: d  reason: collision with root package name */
        private String f3510d = null;

        public void afterTextChanged(Editable editable) {
        }

        public d(ReactContext reactContext, c cVar) {
            this.f3508b = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.f3509c = cVar;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f3510d = charSequence.toString();
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (i3 != 0 || i2 != 0) {
                com.facebook.i.a.a.a(this.f3510d);
                String substring = charSequence.toString().substring(i, i + i3);
                int i4 = i + i2;
                String substring2 = this.f3510d.substring(i, i4);
                if (i3 != i2 || !substring.equals(substring2)) {
                    this.f3508b.a(new e(this.f3509c.getId(), charSequence.toString(), this.f3509c.d()));
                    this.f3508b.a(new h(this.f3509c.getId(), substring, substring2, i, i4));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(final af afVar, final c cVar) {
        cVar.addTextChangedListener(new d(afVar, cVar));
        cVar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /* class com.facebook.react.views.textinput.ReactTextInputManager.AnonymousClass2 */

            public void onFocusChange(View view, boolean z) {
                com.facebook.react.uimanager.events.d eventDispatcher = ((UIManagerModule) afVar.getNativeModule(UIManagerModule.class)).getEventDispatcher();
                if (z) {
                    eventDispatcher.a(new i(cVar.getId()));
                    return;
                }
                eventDispatcher.a(new f(cVar.getId()));
                eventDispatcher.a(new g(cVar.getId(), cVar.getText().toString()));
            }
        });
        cVar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            /* class com.facebook.react.views.textinput.ReactTextInputManager.AnonymousClass3 */

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i & 255) > 0 || i == 0) {
                    boolean blurOnSubmit = cVar.getBlurOnSubmit();
                    boolean z = (cVar.getInputType() & PKIFailureInfo.unsupportedVersion) != 0;
                    ((UIManagerModule) afVar.getNativeModule(UIManagerModule.class)).getEventDispatcher().a(new n(cVar.getId(), cVar.getText().toString()));
                    if (blurOnSubmit) {
                        cVar.clearFocus();
                    }
                    if (blurOnSubmit || !z) {
                        return true;
                    }
                    return false;
                } else if (i == 5) {
                    return textView.focusSearch(2) != null && !textView.requestFocus(2);
                } else {
                    return true;
                }
            }
        });
    }

    private class a implements a {

        /* renamed from: b  reason: collision with root package name */
        private c f3496b;

        /* renamed from: c  reason: collision with root package name */
        private com.facebook.react.uimanager.events.d f3497c;

        /* renamed from: d  reason: collision with root package name */
        private int f3498d = 0;
        private int e = 0;

        public a(c cVar) {
            this.f3496b = cVar;
            this.f3497c = ((UIManagerModule) ((ReactContext) cVar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        @Override // com.facebook.react.views.textinput.a
        public void a() {
            int width = this.f3496b.getWidth();
            int height = this.f3496b.getHeight();
            if (this.f3496b.getLayout() != null) {
                width = this.f3496b.getCompoundPaddingLeft() + this.f3496b.getLayout().getWidth() + this.f3496b.getCompoundPaddingRight();
                height = this.f3496b.getCompoundPaddingTop() + this.f3496b.getLayout().getHeight() + this.f3496b.getCompoundPaddingBottom();
            }
            if (width != this.f3498d || height != this.e) {
                this.e = height;
                this.f3498d = width;
                this.f3497c.a(new b(this.f3496b.getId(), o.d((float) width), o.d((float) height)));
            }
        }
    }

    private class c implements p {

        /* renamed from: b  reason: collision with root package name */
        private c f3504b;

        /* renamed from: c  reason: collision with root package name */
        private com.facebook.react.uimanager.events.d f3505c;

        /* renamed from: d  reason: collision with root package name */
        private int f3506d;
        private int e;

        public c(c cVar) {
            this.f3504b = cVar;
            this.f3505c = ((UIManagerModule) ((ReactContext) cVar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        @Override // com.facebook.react.views.textinput.p
        public void a(int i, int i2) {
            if (this.f3506d != i || this.e != i2) {
                this.f3505c.a(new l(this.f3504b.getId(), i, i2));
                this.f3506d = i;
                this.e = i2;
            }
        }
    }

    private class b implements o {

        /* renamed from: b  reason: collision with root package name */
        private c f3500b;

        /* renamed from: c  reason: collision with root package name */
        private com.facebook.react.uimanager.events.d f3501c;

        /* renamed from: d  reason: collision with root package name */
        private int f3502d;
        private int e;

        public b(c cVar) {
            this.f3500b = cVar;
            this.f3501c = ((UIManagerModule) ((ReactContext) cVar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        @Override // com.facebook.react.views.textinput.o
        public void a(int i, int i2, int i3, int i4) {
            if (this.f3502d != i || this.e != i2) {
                this.f3501c.a(com.facebook.react.views.scroll.h.a(this.f3500b.getId(), i.SCROLL, i, i2, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0, 0, this.f3500b.getWidth(), this.f3500b.getHeight()));
                this.f3502d = i;
                this.e = i2;
            }
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedViewConstants() {
        return e.a("AutoCapitalizationType", e.a("none", 0, "characters", 4096, "words", Integer.valueOf((int) PKIFailureInfo.certRevoked), "sentences", 16384));
    }
}
