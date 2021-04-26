package com.facebook.react.bridge;

import java.io.Closeable;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.Deque;

public class JsonWriter implements Closeable {
    private final Deque<Scope> mScopes = new ArrayDeque();
    private final Writer mWriter;

    /* access modifiers changed from: private */
    public enum Scope {
        EMPTY_OBJECT,
        OBJECT,
        EMPTY_ARRAY,
        ARRAY
    }

    public JsonWriter(Writer writer) {
        this.mWriter = writer;
    }

    public JsonWriter beginArray() {
        open(Scope.EMPTY_ARRAY, '[');
        return this;
    }

    public JsonWriter endArray() {
        close(']');
        return this;
    }

    public JsonWriter beginObject() {
        open(Scope.EMPTY_OBJECT, '{');
        return this;
    }

    public JsonWriter endObject() {
        close('}');
        return this;
    }

    public JsonWriter name(String str) {
        if (str != null) {
            beforeName();
            string(str);
            this.mWriter.write(58);
            return this;
        }
        throw new NullPointerException("name can not be null");
    }

    public JsonWriter value(String str) {
        if (str == null) {
            return nullValue();
        }
        beforeValue();
        string(str);
        return this;
    }

    public JsonWriter nullValue() {
        beforeValue();
        this.mWriter.write("null");
        return this;
    }

    public JsonWriter rawValue(String str) {
        beforeValue();
        this.mWriter.write(str);
        return this;
    }

    public JsonWriter value(boolean z) {
        beforeValue();
        this.mWriter.write(z ? "true" : "false");
        return this;
    }

    public JsonWriter value(double d2) {
        beforeValue();
        this.mWriter.append((CharSequence) Double.toString(d2));
        return this;
    }

    public JsonWriter value(long j) {
        beforeValue();
        this.mWriter.write(Long.toString(j));
        return this;
    }

    public JsonWriter value(Number number) {
        if (number == null) {
            return nullValue();
        }
        beforeValue();
        this.mWriter.append((CharSequence) number.toString());
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mWriter.close();
    }

    private void beforeValue() {
        Scope peek = this.mScopes.peek();
        switch (peek) {
            case EMPTY_ARRAY:
                replace(Scope.ARRAY);
                return;
            case EMPTY_OBJECT:
                throw new IllegalArgumentException(Scope.EMPTY_OBJECT.name());
            case ARRAY:
                this.mWriter.write(44);
                return;
            case OBJECT:
                return;
            default:
                throw new IllegalStateException("Unknown scope: " + peek);
        }
    }

    private void beforeName() {
        Scope peek = this.mScopes.peek();
        switch (peek) {
            case EMPTY_ARRAY:
            case ARRAY:
                throw new IllegalStateException("name not allowed in array");
            case EMPTY_OBJECT:
                replace(Scope.OBJECT);
                return;
            case OBJECT:
                this.mWriter.write(44);
                return;
            default:
                throw new IllegalStateException("Unknown scope: " + peek);
        }
    }

    private void open(Scope scope, char c2) {
        this.mScopes.push(scope);
        this.mWriter.write(c2);
    }

    private void close(char c2) {
        this.mScopes.pop();
        this.mWriter.write(c2);
    }

    private void string(String str) {
        this.mWriter.write(34);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\b':
                    this.mWriter.write("\\b");
                    break;
                case '\t':
                    this.mWriter.write("\\t");
                    break;
                case '\n':
                    this.mWriter.write("\\n");
                    break;
                case '\f':
                    this.mWriter.write("\\f");
                    break;
                case '\r':
                    this.mWriter.write("\\r");
                    break;
                case '\"':
                case '\\':
                    this.mWriter.write(92);
                    this.mWriter.write(charAt);
                    break;
                case 8232:
                case 8233:
                    this.mWriter.write(String.format("\\u%04x", Integer.valueOf(charAt)));
                    break;
                default:
                    if (charAt <= 31) {
                        this.mWriter.write(String.format("\\u%04x", Integer.valueOf(charAt)));
                        break;
                    } else {
                        this.mWriter.write(charAt);
                        break;
                    }
            }
        }
        this.mWriter.write(34);
    }

    private void replace(Scope scope) {
        this.mScopes.pop();
        this.mScopes.push(scope);
    }
}
