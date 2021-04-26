package com.crashlytics.android.answers;

public class SearchEvent extends PredefinedEvent<SearchEvent> {
    static final String QUERY_ATTRIBUTE = "query";
    static final String TYPE = "search";

    /* access modifiers changed from: package-private */
    @Override // com.crashlytics.android.answers.PredefinedEvent
    public String getPredefinedType() {
        return "search";
    }

    public SearchEvent putQuery(String str) {
        this.predefinedAttributes.put("query", str);
        return this;
    }
}
