package com.crashlytics.android.core;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.EventLogger;
import com.crashlytics.android.core.CrashPromptDialog;
import com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler;
import com.crashlytics.android.core.LogFileManager;
import com.crashlytics.android.core.ReportUploader;
import ezvcard.property.Kind;
import io.a.a.a.a.b.i;
import io.a.a.a.a.b.j;
import io.a.a.a.a.b.q;
import io.a.a.a.a.b.r;
import io.a.a.a.a.e.e;
import io.a.a.a.a.f.a;
import io.a.a.a.a.g.m;
import io.a.a.a.a.g.o;
import io.a.a.a.a.g.p;
import io.a.a.a.a.g.t;
import io.a.a.a.c;
import io.a.a.a.l;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class CrashlyticsController {
    private static final int ANALYZER_VERSION = 1;
    private static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    static final String FATAL_SESSION_DIR = "fatal-sessions";
    static final String FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS = "clx";
    static final String FIREBASE_APPLICATION_EXCEPTION = "_ae";
    static final String FIREBASE_CRASH_TYPE = "fatal";
    static final int FIREBASE_CRASH_TYPE_FATAL = 1;
    static final String FIREBASE_REALTIME = "_r";
    static final String FIREBASE_TIMESTAMP = "timestamp";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    private static final String[] INITIAL_SESSION_PART_TAGS = {SESSION_USER_TAG, SESSION_APP_TAG, SESSION_OS_TAG, SESSION_DEVICE_TAG};
    static final String INVALID_CLS_CACHE_DIR = "invalidClsFiles";
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new Comparator<File>() {
        /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass4 */

        public int compare(File file, File file2) {
            return file2.getName().compareTo(file.getName());
        }
    };
    static final int MAX_INVALID_SESSIONS = 4;
    private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
    static final int MAX_OPEN_SESSIONS = 8;
    static final int MAX_STACK_SIZE = 1024;
    static final String NONFATAL_SESSION_DIR = "nonfatal-sessions";
    static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    static final String SESSION_APP_TAG = "SessionApp";
    static final FilenameFilter SESSION_BEGIN_FILE_FILTER = new FileNameContainsFilter(SESSION_BEGIN_TAG) {
        /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass1 */

        @Override // com.crashlytics.android.core.CrashlyticsController.FileNameContainsFilter
        public boolean accept(File file, String str) {
            return super.accept(file, str) && str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    static final String SESSION_BEGIN_TAG = "BeginSession";
    static final String SESSION_DEVICE_TAG = "SessionDevice";
    static final FileFilter SESSION_DIRECTORY_FILTER = new FileFilter() {
        /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass3 */

        public boolean accept(File file) {
            return file.isDirectory() && file.getName().length() == 35;
        }
    };
    static final String SESSION_EVENT_MISSING_BINARY_IMGS_TAG = "SessionMissingBinaryImages";
    static final String SESSION_FATAL_TAG = "SessionCrash";
    static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter() {
        /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass2 */

        public boolean accept(File file, String str) {
            return str.length() == 39 && str.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    private static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final int SESSION_ID_LENGTH = 35;
    static final String SESSION_JSON_SUFFIX = ".json";
    static final String SESSION_NON_FATAL_TAG = "SessionEvent";
    static final String SESSION_OS_TAG = "SessionOS";
    static final String SESSION_USER_TAG = "SessionUser";
    private static final boolean SHOULD_PROMPT_BEFORE_SENDING_REPORTS_DEFAULT = false;
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new Comparator<File>() {
        /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass5 */

        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    };
    private final AppData appData;
    private final AppMeasurementEventListenerRegistrar appMeasurementEventListenerRegistrar;
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    private final CrashlyticsCore crashlyticsCore;
    private final DevicePowerStateListener devicePowerStateListener;
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final a fileStore;
    private final EventLogger firebaseAnalyticsLogger;
    private final ReportUploader.HandlingExceptionCheck handlingExceptionCheck;
    private final e httpRequestFactory;
    private final r idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    private final LogFileManager logFileManager;
    private final PreferenceManager preferenceManager;
    private final ReportUploader.ReportFilesProvider reportFilesProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    private final String unityVersion;

    /* access modifiers changed from: private */
    public interface CodedOutputStreamWriteAction {
        void writeTo(CodedOutputStream codedOutputStream);
    }

    /* access modifiers changed from: private */
    public interface FileOutputStreamWriteAction {
        void writeTo(FileOutputStream fileOutputStream);
    }

    /* access modifiers changed from: package-private */
    public static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String str) {
            this.string = str;
        }

        public boolean accept(File file, String str) {
            return str.contains(this.string) && !str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    }

    /* access modifiers changed from: package-private */
    public static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String str) {
            this.sessionId = str;
        }

        public boolean accept(File file, String str) {
            if (!str.equals(this.sessionId + ClsFileOutputStream.SESSION_FILE_EXTENSION) && str.contains(this.sessionId) && !str.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION)) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return !CrashlyticsController.SESSION_FILE_FILTER.accept(file, str) && CrashlyticsController.SESSION_FILE_PATTERN.matcher(str).matches();
        }
    }

    /* access modifiers changed from: package-private */
    public static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        public boolean accept(File file, String str) {
            return ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, str) || str.contains(CrashlyticsController.SESSION_EVENT_MISSING_BINARY_IMGS_TAG);
        }
    }

    CrashlyticsController(CrashlyticsCore crashlyticsCore2, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker, e eVar, r rVar, PreferenceManager preferenceManager2, a aVar, AppData appData2, UnityVersionProvider unityVersionProvider, AppMeasurementEventListenerRegistrar appMeasurementEventListenerRegistrar2, EventLogger eventLogger) {
        this.crashlyticsCore = crashlyticsCore2;
        this.backgroundWorker = crashlyticsBackgroundWorker;
        this.httpRequestFactory = eVar;
        this.idManager = rVar;
        this.preferenceManager = preferenceManager2;
        this.fileStore = aVar;
        this.appData = appData2;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        this.appMeasurementEventListenerRegistrar = appMeasurementEventListenerRegistrar2;
        this.firebaseAnalyticsLogger = eventLogger;
        Context context = crashlyticsCore2.getContext();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(aVar);
        this.logFileManager = new LogFileManager(context, this.logFileDirectoryProvider);
        this.reportFilesProvider = new ReportUploaderFilesProvider();
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck();
        this.devicePowerStateListener = new DevicePowerStateListener(context);
        this.stackTraceTrimmingStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
    }

    /* access modifiers changed from: package-private */
    public void enableExceptionHandling(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, boolean z) {
        openSession();
        this.crashHandler = new CrashlyticsUncaughtExceptionHandler(new CrashlyticsUncaughtExceptionHandler.CrashListener() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass6 */

            @Override // com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.CrashListener
            public void onUncaughtException(CrashlyticsUncaughtExceptionHandler.SettingsDataProvider settingsDataProvider, Thread thread, Throwable th, boolean z) {
                CrashlyticsController.this.handleUncaughtException(settingsDataProvider, thread, th, z);
            }
        }, new DefaultSettingsDataProvider(), z, uncaughtExceptionHandler);
        Thread.setDefaultUncaughtExceptionHandler(this.crashHandler);
    }

    /* access modifiers changed from: package-private */
    public synchronized void handleUncaughtException(final CrashlyticsUncaughtExceptionHandler.SettingsDataProvider settingsDataProvider, final Thread thread, final Throwable th, final boolean z) {
        l g = c.g();
        g.a(CrashlyticsCore.TAG, "Crashlytics is handling uncaught exception \"" + th + "\" from thread " + thread.getName());
        this.devicePowerStateListener.dispose();
        final Date date = new Date();
        this.backgroundWorker.submitAndWait(new Callable<Void>() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass7 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                m mVar;
                p pVar;
                CrashlyticsController.this.crashlyticsCore.createCrashMarker();
                CrashlyticsController.this.writeFatal(date, thread, th);
                t settingsData = settingsDataProvider.getSettingsData();
                if (settingsData != null) {
                    pVar = settingsData.f6070b;
                    mVar = settingsData.f6072d;
                } else {
                    pVar = null;
                    mVar = null;
                }
                boolean z = false;
                if ((mVar == null || mVar.e) || z) {
                    CrashlyticsController.this.recordFatalFirebaseEvent(date.getTime());
                }
                CrashlyticsController.this.doCloseSessions(pVar);
                CrashlyticsController.this.doOpenSession();
                if (pVar != null) {
                    CrashlyticsController.this.trimSessionFiles(pVar.g);
                }
                if (new q().c(CrashlyticsController.this.crashlyticsCore.getContext()) && !CrashlyticsController.this.shouldPromptUserBeforeSendingCrashReports(settingsData)) {
                    z = true;
                }
                if (z) {
                    CrashlyticsController.this.sendSessionReports(settingsData);
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void submitAllReports(float f, t tVar) {
        if (tVar == null) {
            c.g().d(CrashlyticsCore.TAG, "Could not send reports. Settings are not available.");
            return;
        }
        new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(tVar.f6069a.f6036d, tVar.f6069a.e), this.reportFilesProvider, this.handlingExceptionCheck).uploadReports(f, shouldPromptUserBeforeSendingCrashReports(tVar) ? new PrivacyDialogCheck(this.crashlyticsCore, this.preferenceManager, tVar.f6071c) : new ReportUploader.AlwaysSendCheck());
    }

    /* access modifiers changed from: package-private */
    public void writeToLog(final long j, final String str) {
        this.backgroundWorker.submit(new Callable<Void>() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass8 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                if (CrashlyticsController.this.isHandlingException()) {
                    return null;
                }
                CrashlyticsController.this.logFileManager.writeToLog(j, str);
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void writeNonFatalException(final Thread thread, final Throwable th) {
        final Date date = new Date();
        this.backgroundWorker.submit(new Runnable() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass9 */

            public void run() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    CrashlyticsController.this.doWriteNonFatal(date, thread, th);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void cacheUserData(final String str, final String str2, final String str3) {
        this.backgroundWorker.submit(new Callable<Void>() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass10 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(CrashlyticsController.this.getCurrentSessionId(), new UserMetaData(str, str2, str3));
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void cacheKeyData(final Map<String, String> map) {
        this.backgroundWorker.submit(new Callable<Void>() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass11 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(CrashlyticsController.this.getCurrentSessionId(), map);
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void openSession() {
        this.backgroundWorker.submit(new Callable<Void>() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass12 */

            @Override // java.util.concurrent.Callable
            public Void call() {
                CrashlyticsController.this.doOpenSession();
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getCurrentSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length > 0) {
            return getSessionIdFromSessionFile(listSortedSessionBeginFiles[0]);
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String getPreviousSessionId() {
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length > 1) {
            return getSessionIdFromSessionFile(listSortedSessionBeginFiles[1]);
        }
        return null;
    }

    static String getSessionIdFromSessionFile(File file) {
        return file.getName().substring(0, 35);
    }

    /* access modifiers changed from: package-private */
    public boolean hasOpenSession() {
        return listSessionBeginFiles().length > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean finalizeSessions(final p pVar) {
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass13 */

            @Override // java.util.concurrent.Callable
            public Boolean call() {
                if (CrashlyticsController.this.isHandlingException()) {
                    c.g().a(CrashlyticsCore.TAG, "Skipping session finalization because a crash has already occurred.");
                    return Boolean.FALSE;
                }
                c.g().a(CrashlyticsCore.TAG, "Finalizing previously open sessions.");
                CrashlyticsController.this.doCloseSessions(pVar, true);
                c.g().a(CrashlyticsCore.TAG, "Closed all previously open sessions");
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doOpenSession() {
        Date date = new Date();
        String clsuuid = new CLSUUID(this.idManager).toString();
        l g = c.g();
        g.a(CrashlyticsCore.TAG, "Opening a new session with ID " + clsuuid);
        writeBeginSession(clsuuid, date);
        writeSessionApp(clsuuid);
        writeSessionOS(clsuuid);
        writeSessionDevice(clsuuid);
        this.logFileManager.setCurrentSession(clsuuid);
    }

    /* access modifiers changed from: package-private */
    public void doCloseSessions(p pVar) {
        doCloseSessions(pVar, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doCloseSessions(p pVar, boolean z) {
        trimOpenSessions((z ? 1 : 0) + 8);
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        if (listSortedSessionBeginFiles.length <= z) {
            c.g().a(CrashlyticsCore.TAG, "No open sessions to be closed.");
            return;
        }
        writeSessionUser(getSessionIdFromSessionFile(listSortedSessionBeginFiles[z ? 1 : 0]));
        if (pVar == null) {
            c.g().a(CrashlyticsCore.TAG, "Unable to close session. Settings are not loaded.");
        } else {
            closeOpenSessions(listSortedSessionBeginFiles, z, pVar.f6058c);
        }
    }

    private void closeOpenSessions(File[] fileArr, int i, int i2) {
        c.g().a(CrashlyticsCore.TAG, "Closing open sessions.");
        while (i < fileArr.length) {
            File file = fileArr[i];
            String sessionIdFromSessionFile = getSessionIdFromSessionFile(file);
            l g = c.g();
            g.a(CrashlyticsCore.TAG, "Closing session: " + sessionIdFromSessionFile);
            writeSessionPartsToSessionFile(file, sessionIdFromSessionFile, i2);
            i++;
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream clsFileOutputStream) {
        if (clsFileOutputStream != null) {
            try {
                clsFileOutputStream.closeInProgressStream();
            } catch (IOException e) {
                c.g().e(CrashlyticsCore.TAG, "Error closing session file stream in the presence of an exception", e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void recursiveDelete(Set<File> set) {
        for (File file : set) {
            recursiveDelete(file);
        }
    }

    private void recursiveDelete(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                recursiveDelete(file2);
            }
        }
        file.delete();
    }

    private void deleteSessionPartFilesFor(String str) {
        for (File file : listSessionPartFilesFor(str)) {
            file.delete();
        }
    }

    private File[] listSessionPartFilesFor(String str) {
        return listFilesMatching(new SessionPartFileFilter(str));
    }

    /* access modifiers changed from: package-private */
    public File[] listCompleteSessionFiles() {
        LinkedList linkedList = new LinkedList();
        Collections.addAll(linkedList, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(linkedList, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) linkedList.toArray(new File[linkedList.size()]);
    }

    /* access modifiers changed from: package-private */
    public File[] listNativeSessionFileDirectories() {
        return listFilesMatching(SESSION_DIRECTORY_FILTER);
    }

    /* access modifiers changed from: package-private */
    public File[] listSessionBeginFiles() {
        return listFilesMatching(SESSION_BEGIN_FILE_FILTER);
    }

    private File[] listSortedSessionBeginFiles() {
        File[] listSessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(listSessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return listSessionBeginFiles;
    }

    private File[] listFilesMatching(FileFilter fileFilter) {
        return ensureFileArrayNotNull(getFilesDir().listFiles(fileFilter));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private File[] listFilesMatching(FilenameFilter filenameFilter) {
        return listFilesMatching(getFilesDir(), filenameFilter);
    }

    private File[] listFilesMatching(File file, FilenameFilter filenameFilter) {
        return ensureFileArrayNotNull(file.listFiles(filenameFilter));
    }

    private File[] listFiles(File file) {
        return ensureFileArrayNotNull(file.listFiles());
    }

    private File[] ensureFileArrayNotNull(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    private void trimSessionEventFiles(String str, int i) {
        File filesDir = getFilesDir();
        Utils.capFileCount(filesDir, new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG), i, SMALLEST_FILE_NAME_FIRST);
    }

    /* access modifiers changed from: package-private */
    public void trimSessionFiles(int i) {
        int capFileCount = i - Utils.capFileCount(getFatalSessionFilesDir(), i, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, capFileCount - Utils.capFileCount(getNonFatalSessionFilesDir(), capFileCount, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int i) {
        HashSet hashSet = new HashSet();
        File[] listSortedSessionBeginFiles = listSortedSessionBeginFiles();
        int min = Math.min(i, listSortedSessionBeginFiles.length);
        for (int i2 = 0; i2 < min; i2++) {
            hashSet.add(getSessionIdFromSessionFile(listSortedSessionBeginFiles[i2]));
        }
        this.logFileManager.discardOldLogFiles(hashSet);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), hashSet);
    }

    private void retainSessions(File[] fileArr, Set<String> set) {
        for (File file : fileArr) {
            String name = file.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(name);
            if (!matcher.matches()) {
                c.g().a(CrashlyticsCore.TAG, "Deleting unknown file: " + name);
                file.delete();
            } else if (!set.contains(matcher.group(1))) {
                c.g().a(CrashlyticsCore.TAG, "Trimming session file: " + name);
                file.delete();
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String str, File[] fileArr, int i) {
        if (fileArr.length <= i) {
            return fileArr;
        }
        c.g().a(CrashlyticsCore.TAG, String.format(Locale.US, "Trimming down to %d logged exceptions.", Integer.valueOf(i)));
        trimSessionEventFiles(str, i);
        return listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
    }

    /* access modifiers changed from: package-private */
    public void cleanInvalidTempFiles() {
        this.backgroundWorker.submit(new Runnable() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass14 */

            public void run() {
                CrashlyticsController crashlyticsController = CrashlyticsController.this;
                crashlyticsController.doCleanInvalidTempFiles(crashlyticsController.listFilesMatching((CrashlyticsController) new InvalidPartFileFilter()));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void doCleanInvalidTempFiles(File[] fileArr) {
        final HashSet hashSet = new HashSet();
        for (File file : fileArr) {
            c.g().a(CrashlyticsCore.TAG, "Found invalid session part file: " + file);
            hashSet.add(getSessionIdFromSessionFile(file));
        }
        if (!hashSet.isEmpty()) {
            File invalidFilesDir = getInvalidFilesDir();
            if (!invalidFilesDir.exists()) {
                invalidFilesDir.mkdir();
            }
            File[] listFilesMatching = listFilesMatching(new FilenameFilter() {
                /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass15 */

                public boolean accept(File file, String str) {
                    if (str.length() < 35) {
                        return false;
                    }
                    return hashSet.contains(str.substring(0, 35));
                }
            });
            for (File file2 : listFilesMatching) {
                c.g().a(CrashlyticsCore.TAG, "Moving session file: " + file2);
                if (!file2.renameTo(new File(invalidFilesDir, file2.getName()))) {
                    c.g().a(CrashlyticsCore.TAG, "Could not move session file. Deleting " + file2);
                    file2.delete();
                }
            }
            trimInvalidSessionFiles();
        }
    }

    private void trimInvalidSessionFiles() {
        File invalidFilesDir = getInvalidFilesDir();
        if (invalidFilesDir.exists()) {
            File[] listFilesMatching = listFilesMatching(invalidFilesDir, new InvalidPartFileFilter());
            Arrays.sort(listFilesMatching, Collections.reverseOrder());
            HashSet hashSet = new HashSet();
            for (int i = 0; i < listFilesMatching.length && hashSet.size() < 4; i++) {
                hashSet.add(getSessionIdFromSessionFile(listFilesMatching[i]));
            }
            retainSessions(listFiles(invalidFilesDir), hashSet);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void finalizeMostRecentNativeCrash(Context context, File file, String str) {
        byte[] minidumpFromDirectory = NativeFileUtils.minidumpFromDirectory(file);
        byte[] metadataJsonFromDirectory = NativeFileUtils.metadataJsonFromDirectory(file);
        byte[] binaryImagesJsonFromDirectory = NativeFileUtils.binaryImagesJsonFromDirectory(file, context);
        if (minidumpFromDirectory == null || minidumpFromDirectory.length == 0) {
            l g = c.g();
            g.d(CrashlyticsCore.TAG, "No minidump data found in directory " + file);
            return;
        }
        recordFatalExceptionAnswersEvent(str, "<native-crash: minidump>");
        byte[] readFile = readFile(str, "BeginSession.json");
        byte[] readFile2 = readFile(str, "SessionApp.json");
        byte[] readFile3 = readFile(str, "SessionDevice.json");
        byte[] readFile4 = readFile(str, "SessionOS.json");
        byte[] readFile5 = NativeFileUtils.readFile(new MetaDataStore(getFilesDir()).getUserDataFileForSession(str));
        LogFileManager logFileManager2 = new LogFileManager(this.crashlyticsCore.getContext(), this.logFileDirectoryProvider, str);
        byte[] bytesForLog = logFileManager2.getBytesForLog();
        logFileManager2.clearLog();
        byte[] readFile6 = NativeFileUtils.readFile(new MetaDataStore(getFilesDir()).getKeysFileForSession(str));
        File file2 = new File(this.fileStore.a(), str);
        if (!file2.mkdir()) {
            c.g().a(CrashlyticsCore.TAG, "Couldn't create native sessions directory");
            return;
        }
        gzipIfNotEmpty(minidumpFromDirectory, new File(file2, "minidump"));
        gzipIfNotEmpty(metadataJsonFromDirectory, new File(file2, "metadata"));
        gzipIfNotEmpty(binaryImagesJsonFromDirectory, new File(file2, "binaryImages"));
        gzipIfNotEmpty(readFile, new File(file2, "session"));
        gzipIfNotEmpty(readFile2, new File(file2, "app"));
        gzipIfNotEmpty(readFile3, new File(file2, Kind.DEVICE));
        gzipIfNotEmpty(readFile4, new File(file2, "os"));
        gzipIfNotEmpty(readFile5, new File(file2, "user"));
        gzipIfNotEmpty(bytesForLog, new File(file2, "logs"));
        gzipIfNotEmpty(readFile6, new File(file2, "keys"));
    }

    /* access modifiers changed from: package-private */
    public boolean finalizeNativeReport(final CrashlyticsNdkData crashlyticsNdkData) {
        if (crashlyticsNdkData == null) {
            return true;
        }
        return ((Boolean) this.backgroundWorker.submitAndWait(new Callable<Boolean>() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass16 */

            @Override // java.util.concurrent.Callable
            public Boolean call() {
                File first;
                TreeSet<File> treeSet = crashlyticsNdkData.timestampedDirectories;
                String previousSessionId = CrashlyticsController.this.getPreviousSessionId();
                if (!(previousSessionId == null || treeSet.isEmpty() || (first = treeSet.first()) == null)) {
                    CrashlyticsController crashlyticsController = CrashlyticsController.this;
                    crashlyticsController.finalizeMostRecentNativeCrash(crashlyticsController.crashlyticsCore.getContext(), first, previousSessionId);
                }
                CrashlyticsController.this.recursiveDelete((CrashlyticsController) treeSet);
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    private void gzipIfNotEmpty(byte[] bArr, File file) {
        if (bArr != null && bArr.length > 0) {
            gzip(bArr, file);
        }
    }

    private void gzip(byte[] bArr, File file) {
        Throwable th;
        GZIPOutputStream gZIPOutputStream = null;
        try {
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(new FileOutputStream(file));
            try {
                gZIPOutputStream2.write(bArr);
                gZIPOutputStream2.finish();
                i.a(gZIPOutputStream2);
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = gZIPOutputStream2;
                i.a(gZIPOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            i.a(gZIPOutputStream);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void writeFatal(Date date, Thread thread, Throwable th) {
        Throwable th2;
        ClsFileOutputStream clsFileOutputStream;
        Exception e;
        CodedOutputStream codedOutputStream = null;
        try {
            String currentSessionId = getCurrentSessionId();
            if (currentSessionId == null) {
                c.g().e(CrashlyticsCore.TAG, "Tried to write a fatal exception while no session was open.", null);
                i.a((Flushable) null, "Failed to flush to session begin file.");
                i.a((Closeable) null, "Failed to close fatal exception file output stream.");
                return;
            }
            recordFatalExceptionAnswersEvent(currentSessionId, th.getClass().getName());
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), currentSessionId + SESSION_FATAL_TAG);
            try {
                codedOutputStream = CodedOutputStream.newInstance(clsFileOutputStream);
                writeSessionEvent(codedOutputStream, date, thread, th, "crash", true);
            } catch (Exception e2) {
                e = e2;
            }
            i.a(codedOutputStream, "Failed to flush to session begin file.");
            i.a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            try {
                c.g().e(CrashlyticsCore.TAG, "An error occurred in the fatal exception logger", e);
                i.a(codedOutputStream, "Failed to flush to session begin file.");
                i.a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
            } catch (Throwable th3) {
                th2 = th3;
                i.a(codedOutputStream, "Failed to flush to session begin file.");
                i.a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
                throw th2;
            }
        } catch (Throwable th4) {
            th2 = th4;
            clsFileOutputStream = null;
            i.a(codedOutputStream, "Failed to flush to session begin file.");
            i.a((Closeable) clsFileOutputStream, "Failed to close fatal exception file output stream.");
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doWriteNonFatal(Date date, Thread thread, Throwable th) {
        Throwable th2;
        ClsFileOutputStream clsFileOutputStream;
        Exception e;
        String currentSessionId = getCurrentSessionId();
        CodedOutputStream codedOutputStream = null;
        if (currentSessionId == null) {
            c.g().e(CrashlyticsCore.TAG, "Tried to write a non-fatal exception while no session was open.", null);
            return;
        }
        recordLoggedExceptionAnswersEvent(currentSessionId, th.getClass().getName());
        try {
            c.g().a(CrashlyticsCore.TAG, "Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), currentSessionId + SESSION_NON_FATAL_TAG + i.a(this.eventCounter.getAndIncrement()));
            try {
                codedOutputStream = CodedOutputStream.newInstance(clsFileOutputStream);
                writeSessionEvent(codedOutputStream, date, thread, th, EVENT_TYPE_LOGGED, false);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            try {
                c.g().e(CrashlyticsCore.TAG, "An error occurred in the non-fatal exception logger", e);
                i.a(codedOutputStream, "Failed to flush to non-fatal file.");
                i.a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
                trimSessionEventFiles(currentSessionId, 64);
            } catch (Throwable th3) {
                th2 = th3;
                i.a(codedOutputStream, "Failed to flush to non-fatal file.");
                i.a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
                throw th2;
            }
        } catch (Throwable th4) {
            th2 = th4;
            clsFileOutputStream = null;
            i.a(codedOutputStream, "Failed to flush to non-fatal file.");
            i.a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
            throw th2;
        }
        i.a(codedOutputStream, "Failed to flush to non-fatal file.");
        i.a((Closeable) clsFileOutputStream, "Failed to close non-fatal file output stream.");
        try {
            trimSessionEventFiles(currentSessionId, 64);
        } catch (Exception e4) {
            c.g().e(CrashlyticsCore.TAG, "An error occurred when trimming non-fatal files.", e4);
        }
    }

    private void writeSessionPartFile(String str, String str2, CodedOutputStreamWriteAction codedOutputStreamWriteAction) {
        Throwable th;
        ClsFileOutputStream clsFileOutputStream;
        CodedOutputStream codedOutputStream = null;
        try {
            clsFileOutputStream = new ClsFileOutputStream(getFilesDir(), str + str2);
            try {
                codedOutputStream = CodedOutputStream.newInstance(clsFileOutputStream);
                codedOutputStreamWriteAction.writeTo(codedOutputStream);
                i.a(codedOutputStream, "Failed to flush to session " + str2 + " file.");
                i.a((Closeable) clsFileOutputStream, "Failed to close session " + str2 + " file.");
            } catch (Throwable th2) {
                th = th2;
                i.a(codedOutputStream, "Failed to flush to session " + str2 + " file.");
                i.a((Closeable) clsFileOutputStream, "Failed to close session " + str2 + " file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            clsFileOutputStream = null;
            i.a(codedOutputStream, "Failed to flush to session " + str2 + " file.");
            i.a((Closeable) clsFileOutputStream, "Failed to close session " + str2 + " file.");
            throw th;
        }
    }

    private void writeFile(String str, String str2, FileOutputStreamWriteAction fileOutputStreamWriteAction) {
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            File filesDir = getFilesDir();
            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(filesDir, str + str2));
            try {
                fileOutputStreamWriteAction.writeTo(fileOutputStream2);
                i.a((Closeable) fileOutputStream2, "Failed to close " + str2 + " file.");
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                i.a((Closeable) fileOutputStream, "Failed to close " + str2 + " file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            i.a((Closeable) fileOutputStream, "Failed to close " + str2 + " file.");
            throw th;
        }
    }

    private byte[] readFile(String str, String str2) {
        File filesDir = getFilesDir();
        return NativeFileUtils.readFile(new File(filesDir, str + str2));
    }

    private void writeBeginSession(final String str, Date date) {
        final String format = String.format(Locale.US, GENERATOR_FORMAT, this.crashlyticsCore.getVersion());
        final long time = date.getTime() / 1000;
        writeSessionPartFile(str, SESSION_BEGIN_TAG, new CodedOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass17 */

            @Override // com.crashlytics.android.core.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeBeginSession(codedOutputStream, str, format, time);
            }
        });
        writeFile(str, "BeginSession.json", new FileOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass18 */

            @Override // com.crashlytics.android.core.CrashlyticsController.FileOutputStreamWriteAction
            public void writeTo(FileOutputStream fileOutputStream) {
                fileOutputStream.write(new JSONObject(new HashMap<String, Object>() {
                    /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass18.AnonymousClass1 */

                    {
                        put("session_id", str);
                        put("generator", format);
                        put("started_at_seconds", Long.valueOf(time));
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionApp(String str) {
        final String c2 = this.idManager.c();
        final String str2 = this.appData.versionCode;
        final String str3 = this.appData.versionName;
        final String b2 = this.idManager.b();
        final int a2 = io.a.a.a.a.b.l.a(this.appData.installerPackageName).a();
        writeSessionPartFile(str, SESSION_APP_TAG, new CodedOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass19 */

            @Override // com.crashlytics.android.core.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeSessionApp(codedOutputStream, c2, CrashlyticsController.this.appData.apiKey, str2, str3, b2, a2, CrashlyticsController.this.unityVersion);
            }
        });
        writeFile(str, "SessionApp.json", new FileOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass20 */

            @Override // com.crashlytics.android.core.CrashlyticsController.FileOutputStreamWriteAction
            public void writeTo(FileOutputStream fileOutputStream) {
                fileOutputStream.write(new JSONObject(new HashMap<String, Object>() {
                    /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass20.AnonymousClass1 */

                    {
                        put("app_identifier", c2);
                        put("api_key", CrashlyticsController.this.appData.apiKey);
                        put("version_code", str2);
                        put("version_name", str3);
                        put("install_uuid", b2);
                        put("delivery_mechanism", Integer.valueOf(a2));
                        put("unity_version", TextUtils.isEmpty(CrashlyticsController.this.unityVersion) ? "" : CrashlyticsController.this.unityVersion);
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionOS(String str) {
        final boolean g = i.g(this.crashlyticsCore.getContext());
        writeSessionPartFile(str, SESSION_OS_TAG, new CodedOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass21 */

            @Override // com.crashlytics.android.core.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeSessionOS(codedOutputStream, Build.VERSION.RELEASE, Build.VERSION.CODENAME, g);
            }
        });
        writeFile(str, "SessionOS.json", new FileOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass22 */

            @Override // com.crashlytics.android.core.CrashlyticsController.FileOutputStreamWriteAction
            public void writeTo(FileOutputStream fileOutputStream) {
                fileOutputStream.write(new JSONObject(new HashMap<String, Object>() {
                    /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass22.AnonymousClass1 */

                    {
                        put("version", Build.VERSION.RELEASE);
                        put("build_version", Build.VERSION.CODENAME);
                        put("is_rooted", Boolean.valueOf(g));
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionDevice(String str) {
        Context context = this.crashlyticsCore.getContext();
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        final int a2 = i.a();
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final long b2 = i.b();
        final long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        final boolean f = i.f(context);
        final Map<r.a, String> h = this.idManager.h();
        final int h2 = i.h(context);
        writeSessionPartFile(str, SESSION_DEVICE_TAG, new CodedOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass23 */

            @Override // com.crashlytics.android.core.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeSessionDevice(codedOutputStream, a2, Build.MODEL, availableProcessors, b2, blockCount, f, h, h2, Build.MANUFACTURER, Build.PRODUCT);
            }
        });
        writeFile(str, "SessionDevice.json", new FileOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass24 */

            @Override // com.crashlytics.android.core.CrashlyticsController.FileOutputStreamWriteAction
            public void writeTo(FileOutputStream fileOutputStream) {
                fileOutputStream.write(new JSONObject(new HashMap<String, Object>() {
                    /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass24.AnonymousClass1 */

                    {
                        put("arch", Integer.valueOf(a2));
                        put("build_model", Build.MODEL);
                        put("available_processors", Integer.valueOf(availableProcessors));
                        put("total_ram", Long.valueOf(b2));
                        put("disk_space", Long.valueOf(blockCount));
                        put("is_emulator", Boolean.valueOf(f));
                        put("ids", h);
                        put("state", Integer.valueOf(h2));
                        put("build_manufacturer", Build.MANUFACTURER);
                        put("build_product", Build.PRODUCT);
                    }
                }).toString().getBytes());
            }
        });
    }

    private void writeSessionUser(String str) {
        final UserMetaData userMetaData = getUserMetaData(str);
        writeSessionPartFile(str, SESSION_USER_TAG, new CodedOutputStreamWriteAction() {
            /* class com.crashlytics.android.core.CrashlyticsController.AnonymousClass25 */

            @Override // com.crashlytics.android.core.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream codedOutputStream) {
                SessionProtobufHelper.writeSessionUser(codedOutputStream, userMetaData.id, userMetaData.name, userMetaData.email);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r6v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeSessionEvent(com.crashlytics.android.core.CodedOutputStream r25, java.util.Date r26, java.lang.Thread r27, java.lang.Throwable r28, java.lang.String r29, boolean r30) {
        /*
        // Method dump skipped, instructions count: 214
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.core.CrashlyticsController.writeSessionEvent(com.crashlytics.android.core.CodedOutputStream, java.util.Date, java.lang.Thread, java.lang.Throwable, java.lang.String, boolean):void");
    }

    private void writeSessionPartsToSessionFile(File file, String str, int i) {
        l g = c.g();
        g.a(CrashlyticsCore.TAG, "Collecting session parts for ID " + str);
        File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(str + SESSION_FATAL_TAG));
        boolean z = listFilesMatching != null && listFilesMatching.length > 0;
        c.g().a(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has fatal exception: %s", str, Boolean.valueOf(z)));
        File[] listFilesMatching2 = listFilesMatching(new FileNameContainsFilter(str + SESSION_NON_FATAL_TAG));
        boolean z2 = listFilesMatching2 != null && listFilesMatching2.length > 0;
        c.g().a(CrashlyticsCore.TAG, String.format(Locale.US, "Session %s has non-fatal exceptions: %s", str, Boolean.valueOf(z2)));
        if (z || z2) {
            synthesizeSessionFile(file, str, getTrimmedNonFatalFiles(str, listFilesMatching2, i), z ? listFilesMatching[0] : null);
        } else {
            l g2 = c.g();
            g2.a(CrashlyticsCore.TAG, "No events present for session ID " + str);
        }
        l g3 = c.g();
        g3.a(CrashlyticsCore.TAG, "Removing session part files for ID " + str);
        deleteSessionPartFilesFor(str);
    }

    private void synthesizeSessionFile(File file, String str, File[] fileArr, File file2) {
        Throwable th;
        ClsFileOutputStream clsFileOutputStream;
        Exception e;
        boolean z = file2 != null;
        File fatalSessionFilesDir = z ? getFatalSessionFilesDir() : getNonFatalSessionFilesDir();
        if (!fatalSessionFilesDir.exists()) {
            fatalSessionFilesDir.mkdirs();
        }
        try {
            clsFileOutputStream = new ClsFileOutputStream(fatalSessionFilesDir, str);
            try {
                CodedOutputStream newInstance = CodedOutputStream.newInstance(clsFileOutputStream);
                c.g().a(CrashlyticsCore.TAG, "Collecting SessionStart data for session ID " + str);
                writeToCosFromFile(newInstance, file);
                newInstance.writeUInt64(4, new Date().getTime() / 1000);
                newInstance.writeBool(5, z);
                newInstance.writeUInt32(11, 1);
                newInstance.writeEnum(12, 3);
                writeInitialPartsTo(newInstance, str);
                writeNonFatalEventsTo(newInstance, fileArr, str);
                if (z) {
                    writeToCosFromFile(newInstance, file2);
                }
                i.a(newInstance, "Error flushing session file stream");
                i.a((Closeable) clsFileOutputStream, "Failed to close CLS file");
            } catch (Exception e2) {
                e = e2;
                try {
                    c.g().e(CrashlyticsCore.TAG, "Failed to write session file for session ID: " + str, e);
                    i.a((Flushable) null, "Error flushing session file stream");
                    closeWithoutRenamingOrLog(clsFileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    i.a((Flushable) null, "Error flushing session file stream");
                    i.a((Closeable) clsFileOutputStream, "Failed to close CLS file");
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            clsFileOutputStream = null;
            c.g().e(CrashlyticsCore.TAG, "Failed to write session file for session ID: " + str, e);
            i.a((Flushable) null, "Error flushing session file stream");
            closeWithoutRenamingOrLog(clsFileOutputStream);
        } catch (Throwable th3) {
            th = th3;
            clsFileOutputStream = null;
            i.a((Flushable) null, "Error flushing session file stream");
            i.a((Closeable) clsFileOutputStream, "Failed to close CLS file");
            throw th;
        }
    }

    private static void writeNonFatalEventsTo(CodedOutputStream codedOutputStream, File[] fileArr, String str) {
        Arrays.sort(fileArr, i.f5889a);
        for (File file : fileArr) {
            try {
                c.g().a(CrashlyticsCore.TAG, String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", str, file.getName()));
                writeToCosFromFile(codedOutputStream, file);
            } catch (Exception e) {
                c.g().e(CrashlyticsCore.TAG, "Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream codedOutputStream, String str) {
        String[] strArr = INITIAL_SESSION_PART_TAGS;
        for (String str2 : strArr) {
            File[] listFilesMatching = listFilesMatching(new FileNameContainsFilter(str + str2 + ClsFileOutputStream.SESSION_FILE_EXTENSION));
            if (listFilesMatching.length == 0) {
                c.g().e(CrashlyticsCore.TAG, "Can't find " + str2 + " data for session ID " + str, null);
            } else {
                c.g().a(CrashlyticsCore.TAG, "Collecting " + str2 + " data for session ID " + str);
                writeToCosFromFile(codedOutputStream, listFilesMatching[0]);
            }
        }
    }

    private static void writeToCosFromFile(CodedOutputStream codedOutputStream, File file) {
        Throwable th;
        FileInputStream fileInputStream;
        if (!file.exists()) {
            c.g().e(CrashlyticsCore.TAG, "Tried to include a file that doesn't exist: " + file.getName(), null);
            return;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                copyToCodedOutputStream(fileInputStream, codedOutputStream, (int) file.length());
                i.a((Closeable) fileInputStream, "Failed to close file input stream.");
            } catch (Throwable th2) {
                th = th2;
                i.a((Closeable) fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            i.a((Closeable) fileInputStream, "Failed to close file input stream.");
            throw th;
        }
    }

    private static void copyToCodedOutputStream(InputStream inputStream, CodedOutputStream codedOutputStream, int i) {
        int read;
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < bArr.length && (read = inputStream.read(bArr, i2, bArr.length - i2)) >= 0) {
            i2 += read;
        }
        codedOutputStream.writeRawBytes(bArr);
    }

    private UserMetaData getUserMetaData(String str) {
        if (isHandlingException()) {
            return new UserMetaData(this.crashlyticsCore.getUserIdentifier(), this.crashlyticsCore.getUserName(), this.crashlyticsCore.getUserEmail());
        }
        return new MetaDataStore(getFilesDir()).readUserData(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isHandlingException() {
        CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler = this.crashHandler;
        return crashlyticsUncaughtExceptionHandler != null && crashlyticsUncaughtExceptionHandler.isHandlingException();
    }

    /* access modifiers changed from: package-private */
    public File getFilesDir() {
        return this.fileStore.a();
    }

    /* access modifiers changed from: package-private */
    public File getFatalSessionFilesDir() {
        return new File(getFilesDir(), FATAL_SESSION_DIR);
    }

    /* access modifiers changed from: package-private */
    public File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), NONFATAL_SESSION_DIR);
    }

    /* access modifiers changed from: package-private */
    public File getInvalidFilesDir() {
        return new File(getFilesDir(), INVALID_CLS_CACHE_DIR);
    }

    /* access modifiers changed from: package-private */
    public void registerAnalyticsEventListener(t tVar) {
        if (tVar.f6072d.e && this.appMeasurementEventListenerRegistrar.register()) {
            c.g().a(CrashlyticsCore.TAG, "Registered Firebase Analytics event listener");
        }
    }

    /* access modifiers changed from: package-private */
    public void registerDevicePowerStateListener() {
        this.devicePowerStateListener.initialize();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean shouldPromptUserBeforeSendingCrashReports(t tVar) {
        if (tVar != null && tVar.f6072d.f6044a && !this.preferenceManager.shouldAlwaysSendReports()) {
            return true;
        }
        return false;
    }

    private CreateReportSpiCall getCreateReportSpiCall(String str, String str2) {
        String b2 = i.b(this.crashlyticsCore.getContext(), CRASHLYTICS_API_ENDPOINT);
        return new CompositeCreateReportSpiCall(new DefaultCreateReportSpiCall(this.crashlyticsCore, b2, str, this.httpRequestFactory), new NativeCreateReportSpiCall(this.crashlyticsCore, b2, str2, this.httpRequestFactory));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendSessionReports(t tVar) {
        if (tVar == null) {
            c.g().d(CrashlyticsCore.TAG, "Cannot send reports. Settings are unavailable.");
            return;
        }
        Context context = this.crashlyticsCore.getContext();
        ReportUploader reportUploader = new ReportUploader(this.appData.apiKey, getCreateReportSpiCall(tVar.f6069a.f6036d, tVar.f6069a.e), this.reportFilesProvider, this.handlingExceptionCheck);
        for (File file : listCompleteSessionFiles()) {
            this.backgroundWorker.submit(new SendReportRunnable(context, new SessionReport(file, SEND_AT_CRASHTIME_HEADER), reportUploader));
        }
    }

    private static void recordLoggedExceptionAnswersEvent(String str, String str2) {
        Answers answers = (Answers) c.a(Answers.class);
        if (answers == null) {
            c.g().a(CrashlyticsCore.TAG, "Answers is not available");
        } else {
            answers.onException(new j.b(str, str2));
        }
    }

    private static void recordFatalExceptionAnswersEvent(String str, String str2) {
        Answers answers = (Answers) c.a(Answers.class);
        if (answers == null) {
            c.g().a(CrashlyticsCore.TAG, "Answers is not available");
        } else {
            answers.onException(new j.a(str, str2));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void recordFatalFirebaseEvent(long j) {
        if (firebaseCrashExists()) {
            c.g().a(CrashlyticsCore.TAG, "Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
        } else if (this.firebaseAnalyticsLogger != null) {
            c.g().a(CrashlyticsCore.TAG, "Logging Crashlytics event to Firebase");
            Bundle bundle = new Bundle();
            bundle.putInt(FIREBASE_REALTIME, 1);
            bundle.putInt("fatal", 1);
            bundle.putLong("timestamp", j);
            this.firebaseAnalyticsLogger.logEvent(FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS, "_ae", bundle);
        } else {
            c.g().a(CrashlyticsCore.TAG, "Skipping logging Crashlytics event to Firebase, no Firebase Analytics");
        }
    }

    private boolean firebaseCrashExists() {
        try {
            Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private final class ReportUploaderHandlingExceptionCheck implements ReportUploader.HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        @Override // com.crashlytics.android.core.ReportUploader.HandlingExceptionCheck
        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    private final class ReportUploaderFilesProvider implements ReportUploader.ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        @Override // com.crashlytics.android.core.ReportUploader.ReportFilesProvider
        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        @Override // com.crashlytics.android.core.ReportUploader.ReportFilesProvider
        public File[] getInvalidSessionFiles() {
            return CrashlyticsController.this.getInvalidFilesDir().listFiles();
        }

        @Override // com.crashlytics.android.core.ReportUploader.ReportFilesProvider
        public File[] getNativeReportFiles() {
            return CrashlyticsController.this.listNativeSessionFileDirectories();
        }
    }

    /* access modifiers changed from: private */
    public static final class PrivacyDialogCheck implements ReportUploader.SendCheck {
        private final io.a.a.a.i kit;
        private final PreferenceManager preferenceManager;
        private final o promptData;

        public PrivacyDialogCheck(io.a.a.a.i iVar, PreferenceManager preferenceManager2, o oVar) {
            this.kit = iVar;
            this.preferenceManager = preferenceManager2;
            this.promptData = oVar;
        }

        @Override // com.crashlytics.android.core.ReportUploader.SendCheck
        public boolean canSendReports() {
            Activity b2 = this.kit.getFabric().b();
            if (b2 == null || b2.isFinishing()) {
                return true;
            }
            final CrashPromptDialog create = CrashPromptDialog.create(b2, this.promptData, new CrashPromptDialog.AlwaysSendCallback() {
                /* class com.crashlytics.android.core.CrashlyticsController.PrivacyDialogCheck.AnonymousClass1 */

                @Override // com.crashlytics.android.core.CrashPromptDialog.AlwaysSendCallback
                public void sendUserReportsWithoutPrompting(boolean z) {
                    PrivacyDialogCheck.this.preferenceManager.setShouldAlwaysSendReports(z);
                }
            });
            b2.runOnUiThread(new Runnable() {
                /* class com.crashlytics.android.core.CrashlyticsController.PrivacyDialogCheck.AnonymousClass2 */

                public void run() {
                    create.show();
                }
            });
            c.g().a(CrashlyticsCore.TAG, "Waiting for user opt-in.");
            create.await();
            return create.getOptIn();
        }
    }

    /* access modifiers changed from: private */
    public static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context2, Report report2, ReportUploader reportUploader2) {
            this.context = context2;
            this.report = report2;
            this.reportUploader = reportUploader2;
        }

        public void run() {
            if (i.n(this.context)) {
                c.g().a(CrashlyticsCore.TAG, "Attempting to send crash report at time of crash...");
                this.reportUploader.forceUpload(this.report);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class LogFileDirectoryProvider implements LogFileManager.DirectoryProvider {
        private static final String LOG_FILES_DIR = "log-files";
        private final a rootFileStore;

        public LogFileDirectoryProvider(a aVar) {
            this.rootFileStore = aVar;
        }

        @Override // com.crashlytics.android.core.LogFileManager.DirectoryProvider
        public File getLogFileDir() {
            File file = new File(this.rootFileStore.a(), LOG_FILES_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
    }

    /* access modifiers changed from: private */
    public static final class DefaultSettingsDataProvider implements CrashlyticsUncaughtExceptionHandler.SettingsDataProvider {
        private DefaultSettingsDataProvider() {
        }

        @Override // com.crashlytics.android.core.CrashlyticsUncaughtExceptionHandler.SettingsDataProvider
        public t getSettingsData() {
            return io.a.a.a.a.g.q.a().b();
        }
    }
}
