package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.react.bridge.NativeModule;
import com.facebook.systrace.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@a
public class JavaModuleWrapper {
    private final ArrayList<MethodDescriptor> mDescs = new ArrayList<>();
    private final JSInstance mJSInstance;
    private final ArrayList<NativeModule.NativeMethod> mMethods = new ArrayList<>();
    private final ModuleHolder mModuleHolder;

    @a
    public class MethodDescriptor {
        @a
        Method method;
        @a
        String name;
        @a
        String signature;
        @a
        String type;

        public MethodDescriptor() {
        }
    }

    public JavaModuleWrapper(JSInstance jSInstance, ModuleHolder moduleHolder) {
        this.mJSInstance = jSInstance;
        this.mModuleHolder = moduleHolder;
    }

    @a
    public BaseJavaModule getModule() {
        return (BaseJavaModule) this.mModuleHolder.getModule();
    }

    @a
    public String getName() {
        return this.mModuleHolder.getName();
    }

    @a
    private void findMethods() {
        com.facebook.systrace.a.a(0, "findMethods");
        HashSet hashSet = new HashSet();
        Class<?> cls = this.mModuleHolder.getModule().getClass();
        Class<? super Object> superclass = cls.getSuperclass();
        if (ReactModuleWithSpec.class.isAssignableFrom(superclass)) {
            cls = superclass;
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            ReactMethod reactMethod = (ReactMethod) method.getAnnotation(ReactMethod.class);
            if (reactMethod != null) {
                String name = method.getName();
                if (!hashSet.contains(name)) {
                    MethodDescriptor methodDescriptor = new MethodDescriptor();
                    JavaMethodWrapper javaMethodWrapper = new JavaMethodWrapper(this, method, reactMethod.isBlockingSynchronousMethod());
                    methodDescriptor.name = name;
                    methodDescriptor.type = javaMethodWrapper.getType();
                    if (methodDescriptor.type == BaseJavaModule.METHOD_TYPE_SYNC) {
                        methodDescriptor.signature = javaMethodWrapper.getSignature();
                        methodDescriptor.method = method;
                    }
                    this.mMethods.add(javaMethodWrapper);
                    this.mDescs.add(methodDescriptor);
                } else {
                    throw new IllegalArgumentException("Java Module " + getName() + " method name already registered: " + name);
                }
            }
        }
        com.facebook.systrace.a.b(0);
    }

    @a
    public List<MethodDescriptor> getMethodDescriptors() {
        if (this.mDescs.isEmpty()) {
            findMethods();
        }
        return this.mDescs;
    }

    @a
    public NativeMap getConstants() {
        if (!this.mModuleHolder.getHasConstants()) {
            return null;
        }
        String name = getName();
        b.a(0, "JavaModuleWrapper.getConstants").a("moduleName", name).a();
        ReactMarker.logMarker(ReactMarkerConstants.GET_CONSTANTS_START, name);
        BaseJavaModule module = getModule();
        com.facebook.systrace.a.a(0, "module.getConstants");
        Map<String, Object> constants = module.getConstants();
        com.facebook.systrace.a.b(0);
        com.facebook.systrace.a.a(0, "create WritableNativeMap");
        ReactMarker.logMarker(ReactMarkerConstants.CONVERT_CONSTANTS_START, name);
        try {
            return Arguments.makeNativeMap(constants);
        } finally {
            ReactMarker.logMarker(ReactMarkerConstants.CONVERT_CONSTANTS_END, name);
            com.facebook.systrace.a.b(0);
            ReactMarker.logMarker(ReactMarkerConstants.GET_CONSTANTS_END, name);
            b.a(0).a();
        }
    }

    @a
    public void invoke(int i, ReadableNativeArray readableNativeArray) {
        ArrayList<NativeModule.NativeMethod> arrayList = this.mMethods;
        if (arrayList != null && i < arrayList.size()) {
            this.mMethods.get(i).invoke(this.mJSInstance, readableNativeArray);
        }
    }
}
