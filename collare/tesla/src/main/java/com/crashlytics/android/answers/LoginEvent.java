package com.crashlytics.android.answers;

public class LoginEvent extends PredefinedEvent<LoginEvent> {
    static final String METHOD_ATTRIBUTE = "method";
    static final String SUCCESS_ATTRIBUTE = "success";
    static final String TYPE = "login";

    /* access modifiers changed from: package-private */
    @Override // com.crashlytics.android.answers.PredefinedEvent
    public String getPredefinedType() {
        return "login";
    }

    public LoginEvent putMethod(String str) {
        this.predefinedAttributes.put("method", str);
        return this;
    }

    public LoginEvent putSuccess(boolean z) {
        this.predefinedAttributes.put("success", Boolean.toString(z));
        return this;
    }
}
