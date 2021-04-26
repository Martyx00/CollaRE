package okhttp3.internal;

import java.net.URL;

public interface URLFilter {
    void checkURLPermitted(URL url);
}
