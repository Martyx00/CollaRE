package io.realm;

import io.realm.a;
import io.realm.annotations.RealmModule;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.c;
import io.realm.internal.n;
import io.realm.internal.o;
import io.realm.internal.p;
import io.realm.sync.Subscription;
import io.realm.sync.permissions.ClassPermissions;
import io.realm.sync.permissions.Permission;
import io.realm.sync.permissions.PermissionUser;
import io.realm.sync.permissions.RealmPermissions;
import io.realm.sync.permissions.Role;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RealmModule
class BaseModuleMediator extends o {

    /* renamed from: a  reason: collision with root package name */
    private static final Set<Class<? extends u>> f6103a;

    @Override // io.realm.internal.o
    public boolean c() {
        return true;
    }

    BaseModuleMediator() {
    }

    static {
        HashSet hashSet = new HashSet(6);
        hashSet.add(PermissionUser.class);
        hashSet.add(RealmPermissions.class);
        hashSet.add(ClassPermissions.class);
        hashSet.add(Permission.class);
        hashSet.add(Role.class);
        hashSet.add(Subscription.class);
        f6103a = Collections.unmodifiableSet(hashSet);
    }

    @Override // io.realm.internal.o
    public Map<Class<? extends u>, OsObjectSchemaInfo> a() {
        HashMap hashMap = new HashMap(6);
        hashMap.put(PermissionUser.class, io_realm_sync_permissions_PermissionUserRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(RealmPermissions.class, io_realm_sync_permissions_RealmPermissionsRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(ClassPermissions.class, io_realm_sync_permissions_ClassPermissionsRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Permission.class, io_realm_sync_permissions_PermissionRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Role.class, io_realm_sync_permissions_RoleRealmProxy.getExpectedObjectSchemaInfo());
        hashMap.put(Subscription.class, io_realm_sync_SubscriptionRealmProxy.getExpectedObjectSchemaInfo());
        return hashMap;
    }

    @Override // io.realm.internal.o
    public c a(Class<? extends u> cls, OsSchemaInfo osSchemaInfo) {
        c(cls);
        if (cls.equals(PermissionUser.class)) {
            return io_realm_sync_permissions_PermissionUserRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(RealmPermissions.class)) {
            return io_realm_sync_permissions_RealmPermissionsRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(ClassPermissions.class)) {
            return io_realm_sync_permissions_ClassPermissionsRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Permission.class)) {
            return io_realm_sync_permissions_PermissionRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Role.class)) {
            return io_realm_sync_permissions_RoleRealmProxy.createColumnInfo(osSchemaInfo);
        }
        if (cls.equals(Subscription.class)) {
            return io_realm_sync_SubscriptionRealmProxy.createColumnInfo(osSchemaInfo);
        }
        throw d(cls);
    }

    @Override // io.realm.internal.o
    public String a(Class<? extends u> cls) {
        c(cls);
        if (cls.equals(PermissionUser.class)) {
            return "__User";
        }
        if (cls.equals(RealmPermissions.class)) {
            return "__Realm";
        }
        if (cls.equals(ClassPermissions.class)) {
            return "__Class";
        }
        if (cls.equals(Permission.class)) {
            return "__Permission";
        }
        if (cls.equals(Role.class)) {
            return "__Role";
        }
        if (cls.equals(Subscription.class)) {
            return "__ResultSets";
        }
        throw d(cls);
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(Class<E> cls, Object obj, p pVar, c cVar, boolean z, List<String> list) {
        a.C0164a aVar = (a.C0164a) a.f.get();
        try {
            aVar.a((a) obj, pVar, cVar, z, list);
            c(cls);
            if (cls.equals(PermissionUser.class)) {
                return cls.cast(new io_realm_sync_permissions_PermissionUserRealmProxy());
            }
            if (cls.equals(RealmPermissions.class)) {
                E cast = cls.cast(new io_realm_sync_permissions_RealmPermissionsRealmProxy());
                aVar.f();
                return cast;
            } else if (cls.equals(ClassPermissions.class)) {
                E cast2 = cls.cast(new io_realm_sync_permissions_ClassPermissionsRealmProxy());
                aVar.f();
                return cast2;
            } else if (cls.equals(Permission.class)) {
                E cast3 = cls.cast(new io_realm_sync_permissions_PermissionRealmProxy());
                aVar.f();
                return cast3;
            } else if (cls.equals(Role.class)) {
                E cast4 = cls.cast(new io_realm_sync_permissions_RoleRealmProxy());
                aVar.f();
                return cast4;
            } else if (cls.equals(Subscription.class)) {
                E cast5 = cls.cast(new io_realm_sync_SubscriptionRealmProxy());
                aVar.f();
                return cast5;
            } else {
                throw d(cls);
            }
        } finally {
            aVar.f();
        }
    }

    @Override // io.realm.internal.o
    public Set<Class<? extends u>> b() {
        return f6103a;
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(o oVar, E e, boolean z, Map<u, n> map, Set<g> set) {
        Class<?> superclass = e instanceof n ? e.getClass().getSuperclass() : e.getClass();
        if (superclass.equals(PermissionUser.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_PermissionUserRealmProxy.copyOrUpdate(oVar, (ah) oVar.h().c(PermissionUser.class), (PermissionUser) e, z, map, set)));
        }
        if (superclass.equals(RealmPermissions.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_RealmPermissionsRealmProxy.copyOrUpdate(oVar, (ai) oVar.h().c(RealmPermissions.class), (RealmPermissions) e, z, map, set)));
        }
        if (superclass.equals(ClassPermissions.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_ClassPermissionsRealmProxy.copyOrUpdate(oVar, (af) oVar.h().c(ClassPermissions.class), (ClassPermissions) e, z, map, set)));
        }
        if (superclass.equals(Permission.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_PermissionRealmProxy.copyOrUpdate(oVar, (ag) oVar.h().c(Permission.class), (Permission) e, z, map, set)));
        }
        if (superclass.equals(Role.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_RoleRealmProxy.copyOrUpdate(oVar, (aj) oVar.h().c(Role.class), (Role) e, z, map, set)));
        }
        if (superclass.equals(Subscription.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_SubscriptionRealmProxy.copyOrUpdate(oVar, (ae) oVar.h().c(Subscription.class), (Subscription) e, z, map, set)));
        }
        throw d(superclass);
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(E e, int i, Map<u, n.a<u>> map) {
        Class<? super Object> superclass = e.getClass().getSuperclass();
        if (superclass.equals(PermissionUser.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_PermissionUserRealmProxy.createDetachedCopy((PermissionUser) e, 0, i, map)));
        }
        if (superclass.equals(RealmPermissions.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_RealmPermissionsRealmProxy.createDetachedCopy((RealmPermissions) e, 0, i, map)));
        }
        if (superclass.equals(ClassPermissions.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_ClassPermissionsRealmProxy.createDetachedCopy((ClassPermissions) e, 0, i, map)));
        }
        if (superclass.equals(Permission.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_PermissionRealmProxy.createDetachedCopy((Permission) e, 0, i, map)));
        }
        if (superclass.equals(Role.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_permissions_RoleRealmProxy.createDetachedCopy((Role) e, 0, i, map)));
        }
        if (superclass.equals(Subscription.class)) {
            return (E) ((u) superclass.cast(io_realm_sync_SubscriptionRealmProxy.createDetachedCopy((Subscription) e, 0, i, map)));
        }
        throw d(superclass);
    }
}
