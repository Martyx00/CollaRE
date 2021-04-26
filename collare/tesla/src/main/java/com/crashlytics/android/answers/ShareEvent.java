package com.crashlytics.android.answers;

public class ShareEvent extends PredefinedEvent<ShareEvent> {
    static final String CONTENT_ID_ATTRIBUTE = "contentId";
    static final String CONTENT_NAME_ATTRIBUTE = "contentName";
    static final String CONTENT_TYPE_ATTRIBUTE = "contentType";
    static final String METHOD_ATTRIBUTE = "method";
    static final String TYPE = "share";

    /* access modifiers changed from: package-private */
    @Override // com.crashlytics.android.answers.PredefinedEvent
    public String getPredefinedType() {
        return "share";
    }

    public ShareEvent putMethod(String str) {
        this.predefinedAttributes.put("method", str);
        return this;
    }

    public ShareEvent putContentId(String str) {
        this.predefinedAttributes.put(CONTENT_ID_ATTRIBUTE, str);
        return this;
    }

    public ShareEvent putContentName(String str) {
        this.predefinedAttributes.put(CONTENT_NAME_ATTRIBUTE, str);
        return this;
    }

    public ShareEvent putContentType(String str) {
        this.predefinedAttributes.put(CONTENT_TYPE_ATTRIBUTE, str);
        return this;
    }
}
