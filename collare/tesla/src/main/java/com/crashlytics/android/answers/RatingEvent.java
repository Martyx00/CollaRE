package com.crashlytics.android.answers;

public class RatingEvent extends PredefinedEvent<RatingEvent> {
    static final String CONTENT_ID_ATTRIBUTE = "contentId";
    static final String CONTENT_NAME_ATTRIBUTE = "contentName";
    static final String CONTENT_TYPE_ATTRIBUTE = "contentType";
    static final String RATING_ATTRIBUTE = "rating";
    static final String TYPE = "rating";

    /* access modifiers changed from: package-private */
    @Override // com.crashlytics.android.answers.PredefinedEvent
    public String getPredefinedType() {
        return "rating";
    }

    public RatingEvent putContentId(String str) {
        this.predefinedAttributes.put(CONTENT_ID_ATTRIBUTE, str);
        return this;
    }

    public RatingEvent putContentName(String str) {
        this.predefinedAttributes.put(CONTENT_NAME_ATTRIBUTE, str);
        return this;
    }

    public RatingEvent putContentType(String str) {
        this.predefinedAttributes.put(CONTENT_TYPE_ATTRIBUTE, str);
        return this;
    }

    public RatingEvent putRating(int i) {
        this.predefinedAttributes.put("rating", Integer.valueOf(i));
        return this;
    }
}
