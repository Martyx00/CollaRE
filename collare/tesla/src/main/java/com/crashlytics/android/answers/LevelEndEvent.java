package com.crashlytics.android.answers;

public class LevelEndEvent extends PredefinedEvent<LevelEndEvent> {
    static final String LEVEL_NAME_ATTRIBUTE = "levelName";
    static final String SCORE_ATTRIBUTE = "score";
    static final String SUCCESS_ATTRIBUTE = "success";
    static final String TYPE = "levelEnd";

    /* access modifiers changed from: package-private */
    @Override // com.crashlytics.android.answers.PredefinedEvent
    public String getPredefinedType() {
        return TYPE;
    }

    public LevelEndEvent putLevelName(String str) {
        this.predefinedAttributes.put(LEVEL_NAME_ATTRIBUTE, str);
        return this;
    }

    public LevelEndEvent putScore(Number number) {
        this.predefinedAttributes.put("score", number);
        return this;
    }

    public LevelEndEvent putSuccess(boolean z) {
        this.predefinedAttributes.put("success", z ? "true" : "false");
        return this;
    }
}
