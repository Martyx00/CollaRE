package com.facebook.react.devsupport;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.facebook.react.f;

public class DevSettingsActivity extends PreferenceActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getApplication().getResources().getString(f.b.catalyst_settings_title));
        addPreferencesFromResource(f.d.rn_dev_preferences);
    }
}
