package com.reactnativecommunity.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.webrtc.MediaStreamTrack;

public class RNCWebViewModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final int PICKER = 1;
    private static final int PICKER_LEGACY = 3;
    final String DEFAULT_MIME_TYPES = "*/*";
    private a aPackage;
    private ValueCallback<Uri[]> filePathCallback;
    private ValueCallback<Uri> filePathCallbackLegacy;
    private Uri outputFileUri;
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCWebView";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    public RNCWebViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(this);
    }

    @ReactMethod
    public void isFileUploadSupported(Promise promise) {
        boolean z = false;
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            z = true;
        }
        if (i >= 16 && i <= 18) {
            z = true;
        }
        promise.resolve(z);
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (this.filePathCallback != null || this.filePathCallbackLegacy != null) {
            if (i != 1) {
                if (i == 3) {
                    this.filePathCallbackLegacy.onReceiveValue(i2 != -1 ? null : intent == null ? this.outputFileUri : intent.getData());
                }
            } else if (i2 != -1) {
                ValueCallback<Uri[]> valueCallback = this.filePathCallback;
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                }
            } else {
                Uri[] selectedFiles = getSelectedFiles(intent, i2);
                if (selectedFiles != null) {
                    this.filePathCallback.onReceiveValue(selectedFiles);
                } else {
                    this.filePathCallback.onReceiveValue(new Uri[]{this.outputFileUri});
                }
            }
            this.filePathCallback = null;
            this.filePathCallbackLegacy = null;
            this.outputFileUri = null;
        }
    }

    private Uri[] getSelectedFiles(Intent intent, int i) {
        if (intent == null) {
            return null;
        }
        if (intent.getData() != null) {
            if (i != -1 || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            return WebChromeClient.FileChooserParams.parseResult(i, intent);
        } else if (intent.getClipData() == null) {
            return null;
        } else {
            int itemCount = intent.getClipData().getItemCount();
            Uri[] uriArr = new Uri[itemCount];
            for (int i2 = 0; i2 < itemCount; i2++) {
                uriArr[i2] = intent.getClipData().getItemAt(i2).getUri();
            }
            return uriArr;
        }
    }

    public void startPhotoPickerIntent(ValueCallback<Uri> valueCallback, String str) {
        this.filePathCallbackLegacy = valueCallback;
        Intent createChooser = Intent.createChooser(getFileChooserIntent(str), "");
        ArrayList arrayList = new ArrayList();
        if (acceptsImages(str).booleanValue()) {
            arrayList.add(getPhotoIntent());
        }
        if (acceptsVideo(str).booleanValue()) {
            arrayList.add(getVideoIntent());
        }
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (createChooser.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(createChooser, 3);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
    }

    public boolean startPhotoPickerIntent(ValueCallback<Uri[]> valueCallback, Intent intent, String[] strArr, boolean z) {
        this.filePathCallback = valueCallback;
        ArrayList arrayList = new ArrayList();
        if (acceptsImages(strArr).booleanValue()) {
            arrayList.add(getPhotoIntent());
        }
        if (acceptsVideo(strArr).booleanValue()) {
            arrayList.add(getVideoIntent());
        }
        Intent fileChooserIntent = getFileChooserIntent(strArr, z);
        Intent intent2 = new Intent("android.intent.action.CHOOSER");
        intent2.putExtra("android.intent.extra.INTENT", fileChooserIntent);
        intent2.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (intent2.resolveActivity(getCurrentActivity().getPackageManager()) != null) {
            getCurrentActivity().startActivityForResult(intent2, 1);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
        return true;
    }

    public a getPackage() {
        return this.aPackage;
    }

    public void setPackage(a aVar) {
        this.aPackage = aVar;
    }

    private Intent getPhotoIntent() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        this.outputFileUri = getOutputUri("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", this.outputFileUri);
        return intent;
    }

    private Intent getVideoIntent() {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        intent.putExtra("output", getOutputUri("android.media.action.VIDEO_CAPTURE"));
        return intent;
    }

    private Intent getFileChooserIntent(String str) {
        if (str.isEmpty()) {
            str = "*/*";
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str);
        return intent;
    }

    private Intent getFileChooserIntent(String[] strArr, boolean z) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", getAcceptedMimeType(strArr));
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", z);
        return intent;
    }

    private Boolean acceptsImages(String str) {
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains("image"));
    }

    private Boolean acceptsImages(String[] strArr) {
        return Boolean.valueOf(isArrayEmpty(strArr).booleanValue() || arrayContainsString(strArr, "image").booleanValue());
    }

    private Boolean acceptsVideo(String str) {
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains(MediaStreamTrack.VIDEO_TRACK_KIND));
    }

    private Boolean acceptsVideo(String[] strArr) {
        return Boolean.valueOf(isArrayEmpty(strArr).booleanValue() || arrayContainsString(strArr, MediaStreamTrack.VIDEO_TRACK_KIND).booleanValue());
    }

    private Boolean arrayContainsString(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private String[] getAcceptedMimeType(String[] strArr) {
        return isArrayEmpty(strArr).booleanValue() ? new String[]{"*/*"} : strArr;
    }

    private Uri getOutputUri(String str) {
        File file;
        try {
            file = getCapturedFile(str);
        } catch (IOException e) {
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            file = null;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return Uri.fromFile(file);
        }
        String packageName = getReactApplicationContext().getPackageName();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        return FileProvider.a(reactApplicationContext, packageName + ".fileprovider", file);
    }

    private File getCapturedFile(String str) {
        String str2 = "";
        String str3 = "";
        String str4 = "";
        if (str.equals("android.media.action.IMAGE_CAPTURE")) {
            str2 = "image-";
            str3 = ".jpg";
            str4 = Environment.DIRECTORY_PICTURES;
        } else if (str.equals("android.media.action.VIDEO_CAPTURE")) {
            str2 = "video-";
            str3 = ".mp4";
            str4 = Environment.DIRECTORY_MOVIES;
        }
        String str5 = str2 + String.valueOf(System.currentTimeMillis()) + str3;
        if (Build.VERSION.SDK_INT < 23) {
            return new File(Environment.getExternalStoragePublicDirectory(str4), str5);
        }
        return File.createTempFile(str5, str3, getReactApplicationContext().getExternalFilesDir(null));
    }

    private Boolean isArrayEmpty(String[] strArr) {
        boolean z = false;
        if (strArr.length == 0 || (strArr.length == 1 && strArr[0].length() == 0)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
