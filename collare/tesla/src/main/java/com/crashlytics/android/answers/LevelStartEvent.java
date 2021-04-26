package com.crashlytics.android.answers;

public class LevelStartEvent extends PredefinedEvent<LevelStartEvent> {
    static final String LEVEL_NAME_ATTRIBUTE = "levelName";
    static final String TYPE = "levelStart";

    /* access modifiers changed from: package-private */
    @Override // com.crashlytics.android.answers.PredefinedEvent
    public String getPredefinedType() {
        return TYPE;
    }

    public LevelStartEvent putLevelName(String str) {
        this.predefinedAttributes.put(LEVEL_NAME_ATTRIBUTE, str);
        return this;
    }
}
