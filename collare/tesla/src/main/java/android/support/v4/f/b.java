package android.support.v4.f;

import android.os.Build;
import android.support.v4.util.i;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.concurrent.Executor;

/* compiled from: PrecomputedTextCompat */
public class b implements Spannable {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f444a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static Executor f445b = null;

    /* renamed from: c  reason: collision with root package name */
    private final Spannable f446c;

    /* renamed from: d  reason: collision with root package name */
    private final a f447d;
    private final PrecomputedText e;

    /* compiled from: PrecomputedTextCompat */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        final PrecomputedText.Params f448a;

        /* renamed from: b  reason: collision with root package name */
        private final TextPaint f449b;

        /* renamed from: c  reason: collision with root package name */
        private final TextDirectionHeuristic f450c;

        /* renamed from: d  reason: collision with root package name */
        private final int f451d;
        private final int e;

        /* renamed from: android.support.v4.f.b$a$a  reason: collision with other inner class name */
        /* compiled from: PrecomputedTextCompat */
        public static class C0009a {

            /* renamed from: a  reason: collision with root package name */
            private final TextPaint f452a;

            /* renamed from: b  reason: collision with root package name */
            private TextDirectionHeuristic f453b;

            /* renamed from: c  reason: collision with root package name */
            private int f454c;

            /* renamed from: d  reason: collision with root package name */
            private int f455d;

            public C0009a(TextPaint textPaint) {
                this.f452a = textPaint;
                if (Build.VERSION.SDK_INT >= 23) {
                    this.f454c = 1;
                    this.f455d = 1;
                } else {
                    this.f455d = 0;
                    this.f454c = 0;
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    this.f453b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f453b = null;
                }
            }

            public C0009a a(int i) {
                this.f454c = i;
                return this;
            }

            public C0009a b(int i) {
                this.f455d = i;
                return this;
            }

            public C0009a a(TextDirectionHeuristic textDirectionHeuristic) {
                this.f453b = textDirectionHeuristic;
                return this;
            }

            public a a() {
                return new a(this.f452a, this.f453b, this.f454c, this.f455d);
            }
        }

        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 28) {
                this.f448a = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f448a = null;
            }
            this.f449b = textPaint;
            this.f450c = textDirectionHeuristic;
            this.f451d = i;
            this.e = i2;
        }

        public a(PrecomputedText.Params params) {
            this.f449b = params.getTextPaint();
            this.f450c = params.getTextDirection();
            this.f451d = params.getBreakStrategy();
            this.e = params.getHyphenationFrequency();
            this.f448a = params;
        }

        public TextPaint a() {
            return this.f449b;
        }

        public TextDirectionHeuristic b() {
            return this.f450c;
        }

        public int c() {
            return this.f451d;
        }

        public int d() {
            return this.e;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            PrecomputedText.Params params = this.f448a;
            if (params != null) {
                return params.equals(aVar.f448a);
            }
            if (Build.VERSION.SDK_INT >= 23 && (this.f451d != aVar.c() || this.e != aVar.d())) {
                return false;
            }
            if ((Build.VERSION.SDK_INT >= 18 && this.f450c != aVar.b()) || this.f449b.getTextSize() != aVar.a().getTextSize() || this.f449b.getTextScaleX() != aVar.a().getTextScaleX() || this.f449b.getTextSkewX() != aVar.a().getTextSkewX()) {
                return false;
            }
            if ((Build.VERSION.SDK_INT >= 21 && (this.f449b.getLetterSpacing() != aVar.a().getLetterSpacing() || !TextUtils.equals(this.f449b.getFontFeatureSettings(), aVar.a().getFontFeatureSettings()))) || this.f449b.getFlags() != aVar.a().getFlags()) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                if (!this.f449b.getTextLocales().equals(aVar.a().getTextLocales())) {
                    return false;
                }
            } else if (Build.VERSION.SDK_INT >= 17 && !this.f449b.getTextLocale().equals(aVar.a().getTextLocale())) {
                return false;
            }
            if (this.f449b.getTypeface() == null) {
                if (aVar.a().getTypeface() != null) {
                    return false;
                }
            } else if (!this.f449b.getTypeface().equals(aVar.a().getTypeface())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (Build.VERSION.SDK_INT >= 24) {
                return i.a(Float.valueOf(this.f449b.getTextSize()), Float.valueOf(this.f449b.getTextScaleX()), Float.valueOf(this.f449b.getTextSkewX()), Float.valueOf(this.f449b.getLetterSpacing()), Integer.valueOf(this.f449b.getFlags()), this.f449b.getTextLocales(), this.f449b.getTypeface(), Boolean.valueOf(this.f449b.isElegantTextHeight()), this.f450c, Integer.valueOf(this.f451d), Integer.valueOf(this.e));
            } else if (Build.VERSION.SDK_INT >= 21) {
                return i.a(Float.valueOf(this.f449b.getTextSize()), Float.valueOf(this.f449b.getTextScaleX()), Float.valueOf(this.f449b.getTextSkewX()), Float.valueOf(this.f449b.getLetterSpacing()), Integer.valueOf(this.f449b.getFlags()), this.f449b.getTextLocale(), this.f449b.getTypeface(), Boolean.valueOf(this.f449b.isElegantTextHeight()), this.f450c, Integer.valueOf(this.f451d), Integer.valueOf(this.e));
            } else if (Build.VERSION.SDK_INT >= 18) {
                return i.a(Float.valueOf(this.f449b.getTextSize()), Float.valueOf(this.f449b.getTextScaleX()), Float.valueOf(this.f449b.getTextSkewX()), Integer.valueOf(this.f449b.getFlags()), this.f449b.getTextLocale(), this.f449b.getTypeface(), this.f450c, Integer.valueOf(this.f451d), Integer.valueOf(this.e));
            } else if (Build.VERSION.SDK_INT >= 17) {
                return i.a(Float.valueOf(this.f449b.getTextSize()), Float.valueOf(this.f449b.getTextScaleX()), Float.valueOf(this.f449b.getTextSkewX()), Integer.valueOf(this.f449b.getFlags()), this.f449b.getTextLocale(), this.f449b.getTypeface(), this.f450c, Integer.valueOf(this.f451d), Integer.valueOf(this.e));
            } else {
                return i.a(Float.valueOf(this.f449b.getTextSize()), Float.valueOf(this.f449b.getTextScaleX()), Float.valueOf(this.f449b.getTextSkewX()), Integer.valueOf(this.f449b.getFlags()), this.f449b.getTypeface(), this.f450c, Integer.valueOf(this.f451d), Integer.valueOf(this.e));
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f449b.getTextSize());
            sb.append(", textScaleX=" + this.f449b.getTextScaleX());
            sb.append(", textSkewX=" + this.f449b.getTextSkewX());
            if (Build.VERSION.SDK_INT >= 21) {
                sb.append(", letterSpacing=" + this.f449b.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f449b.isElegantTextHeight());
            }
            if (Build.VERSION.SDK_INT >= 24) {
                sb.append(", textLocale=" + this.f449b.getTextLocales());
            } else if (Build.VERSION.SDK_INT >= 17) {
                sb.append(", textLocale=" + this.f449b.getTextLocale());
            }
            sb.append(", typeface=" + this.f449b.getTypeface());
            if (Build.VERSION.SDK_INT >= 26) {
                sb.append(", variationSettings=" + this.f449b.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f450c);
            sb.append(", breakStrategy=" + this.f451d);
            sb.append(", hyphenationFrequency=" + this.e);
            sb.append("}");
            return sb.toString();
        }
    }

    public PrecomputedText a() {
        Spannable spannable = this.f446c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    public a b() {
        return this.f447d;
    }

    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.e.setSpan(obj, i, i2, i3);
        } else {
            this.f446c.setSpan(obj, i, i2, i3);
        }
    }

    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.e.removeSpan(obj);
        } else {
            this.f446c.removeSpan(obj);
        }
    }

    @Override // android.text.Spanned
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 28 ? (T[]) this.e.getSpans(i, i2, cls) : (T[]) this.f446c.getSpans(i, i2, cls);
    }

    public int getSpanStart(Object obj) {
        return this.f446c.getSpanStart(obj);
    }

    public int getSpanEnd(Object obj) {
        return this.f446c.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        return this.f446c.getSpanFlags(obj);
    }

    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f446c.nextSpanTransition(i, i2, cls);
    }

    public int length() {
        return this.f446c.length();
    }

    public char charAt(int i) {
        return this.f446c.charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return this.f446c.subSequence(i, i2);
    }

    public String toString() {
        return this.f446c.toString();
    }
}
