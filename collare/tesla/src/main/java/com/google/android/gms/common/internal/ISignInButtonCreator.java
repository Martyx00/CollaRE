package com.google.android.gms.common.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface ISignInButtonCreator extends IInterface {
    IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i, int i2);

    IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig);
}
