package okhttp3;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.List;
import okhttp3.internal.annotations.EverythingIsNonNull;

@EverythingIsNonNull
public final class JavaNetAuthenticator implements Authenticator {
    @Override // okhttp3.Authenticator
    public Request authenticate(Route route, Response response) {
        PasswordAuthentication passwordAuthentication;
        List<Challenge> challenges = response.challenges();
        Request request = response.request();
        HttpUrl url = request.url();
        boolean z = response.code() == 407;
        Proxy proxy = route.proxy();
        int size = challenges.size();
        for (int i = 0; i < size; i++) {
            Challenge challenge = challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.scheme())) {
                if (z) {
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy.address();
                    passwordAuthentication = Authenticator.requestPasswordAuthentication(inetSocketAddress.getHostName(), getConnectToInetAddress(proxy, url), inetSocketAddress.getPort(), url.scheme(), challenge.realm(), challenge.scheme(), url.url(), Authenticator.RequestorType.PROXY);
                } else {
                    passwordAuthentication = Authenticator.requestPasswordAuthentication(url.host(), getConnectToInetAddress(proxy, url), url.port(), url.scheme(), challenge.realm(), challenge.scheme(), url.url(), Authenticator.RequestorType.SERVER);
                }
                if (passwordAuthentication != null) {
                    return request.newBuilder().header(z ? "Proxy-Authorization" : "Authorization", Credentials.basic(passwordAuthentication.getUserName(), new String(passwordAuthentication.getPassword()), challenge.charset())).build();
                }
            }
        }
        return null;
    }

    private InetAddress getConnectToInetAddress(Proxy proxy, HttpUrl httpUrl) {
        if (proxy == null || proxy.type() == Proxy.Type.DIRECT) {
            return InetAddress.getByName(httpUrl.host());
        }
        return ((InetSocketAddress) proxy.address()).getAddress();
    }
}
