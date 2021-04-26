package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tagmanager.ContainerHolder;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzv implements ContainerHolder {
    private Status zzact;
    private final Looper zzaxu;
    private Container zzaxv;
    private Container zzaxw;
    private zzx zzaxx;
    private zzw zzaxy;
    private boolean zzaxz;
    private TagManager zzaya;

    public zzv(Status status) {
        this.zzact = status;
        this.zzaxu = null;
    }

    public zzv(TagManager tagManager, Looper looper, Container container, zzw zzw) {
        this.zzaya = tagManager;
        this.zzaxu = looper == null ? Looper.getMainLooper() : looper;
        this.zzaxv = container;
        this.zzaxy = zzw;
        this.zzact = Status.RESULT_SUCCESS;
        tagManager.zza(this);
    }

    private final void zzmq() {
        zzx zzx = this.zzaxx;
        if (zzx != null) {
            zzx.sendMessage(zzx.obtainMessage(1, this.zzaxw.zzmn()));
        }
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized Container getContainer() {
        if (this.zzaxz) {
            zzdi.e("ContainerHolder is released.");
            return null;
        }
        if (this.zzaxw != null) {
            this.zzaxv = this.zzaxw;
            this.zzaxw = null;
        }
        return this.zzaxv;
    }

    /* access modifiers changed from: package-private */
    public final String getContainerId() {
        if (!this.zzaxz) {
            return this.zzaxv.getContainerId();
        }
        zzdi.e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzact;
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized void refresh() {
        if (this.zzaxz) {
            zzdi.e("Refreshing a released ContainerHolder.");
        } else {
            this.zzaxy.zzmr();
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        if (this.zzaxz) {
            zzdi.e("Releasing a released ContainerHolder.");
            return;
        }
        this.zzaxz = true;
        this.zzaya.zzb(this);
        this.zzaxv.release();
        this.zzaxv = null;
        this.zzaxw = null;
        this.zzaxy = null;
        this.zzaxx = null;
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public final synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener containerAvailableListener) {
        if (this.zzaxz) {
            zzdi.e("ContainerHolder is released.");
        } else if (containerAvailableListener == null) {
            this.zzaxx = null;
        } else {
            this.zzaxx = new zzx(this, containerAvailableListener, this.zzaxu);
            if (this.zzaxw != null) {
                zzmq();
            }
        }
    }

    public final synchronized void zza(Container container) {
        if (!this.zzaxz) {
            this.zzaxw = container;
            zzmq();
        }
    }

    public final synchronized void zzcr(String str) {
        if (!this.zzaxz) {
            this.zzaxv.zzcr(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzcs(String str) {
        if (this.zzaxz) {
            zzdi.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzaxy.zzcs(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzmp() {
        if (!this.zzaxz) {
            return this.zzaxy.zzmp();
        }
        zzdi.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }
}
