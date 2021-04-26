package com.facebook.react.bridge;

import java.util.List;

public interface JSIModulePackage {
    List<JSIModuleSpec> getJSIModules(ReactApplicationContext reactApplicationContext, JavaScriptContextHolder javaScriptContextHolder);
}
