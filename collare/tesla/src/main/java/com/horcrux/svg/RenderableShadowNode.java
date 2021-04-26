package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.a.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RenderableShadowNode extends am {
    private static final int CAP_BUTT = 0;
    private static final int CAP_ROUND = 1;
    private static final int CAP_SQUARE = 2;
    private static final int FILL_RULE_EVENODD = 0;
    private static final int FILL_RULE_NONZERO = 1;
    private static final int JOIN_BEVEL = 2;
    private static final int JOIN_MITER = 0;
    private static final int JOIN_ROUND = 1;
    protected ArrayList<String> mAttributeList;
    public ReadableArray mFill;
    public float mFillOpacity = 1.0f;
    public Path.FillType mFillRule = Path.FillType.WINDING;
    private ArrayList<String> mLastMergedList;
    private ArrayList<Object> mOriginProperties;
    protected ArrayList<String> mPropList;
    public ReadableArray mStroke;
    public String[] mStrokeDasharray;
    public float mStrokeDashoffset = BitmapDescriptorFactory.HUE_RED;
    public Paint.Cap mStrokeLinecap = Paint.Cap.ROUND;
    public Paint.Join mStrokeLinejoin = Paint.Join.ROUND;
    public float mStrokeMiterlimit = 4.0f;
    public float mStrokeOpacity = 1.0f;
    public String mStrokeWidth = "1";

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am
    public abstract Path getPath(Canvas canvas, Paint paint);

    @Override // com.horcrux.svg.am
    @a(a = "clipRule", e = 1)
    public /* bridge */ /* synthetic */ void clipRule(int i) {
        super.clipRule(i);
    }

    @Override // com.horcrux.svg.am
    public /* bridge */ /* synthetic */ RectF getClientRect() {
        return super.getClientRect();
    }

    @Override // com.horcrux.svg.am
    public /* bridge */ /* synthetic */ boolean isResponsible() {
        return super.isResponsible();
    }

    @Override // com.horcrux.svg.am, com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public /* bridge */ /* synthetic */ boolean isVirtual() {
        return super.isVirtual();
    }

    @Override // com.horcrux.svg.am, com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public /* bridge */ /* synthetic */ boolean isVirtualAnchor() {
        return super.isVirtualAnchor();
    }

    @Override // com.horcrux.svg.am, com.facebook.react.uimanager.x
    public /* bridge */ /* synthetic */ void markUpdated() {
        super.markUpdated();
    }

    @Override // com.horcrux.svg.am
    @a(a = "clipPath")
    public /* bridge */ /* synthetic */ void setClipPath(String str) {
        super.setClipPath(str);
    }

    @Override // com.horcrux.svg.am
    @a(a = "matrix")
    public /* bridge */ /* synthetic */ void setMatrix(ReadableArray readableArray) {
        super.setMatrix(readableArray);
    }

    @Override // com.horcrux.svg.am
    @a(a = "name")
    public /* bridge */ /* synthetic */ void setName(String str) {
        super.setName(str);
    }

    @Override // com.horcrux.svg.am
    @a(a = "opacity", d = 1.0f)
    public /* bridge */ /* synthetic */ void setOpacity(float f) {
        super.setOpacity(f);
    }

    @Override // com.horcrux.svg.am
    @a(a = "responsible")
    public /* bridge */ /* synthetic */ void setResponsible(boolean z) {
        super.setResponsible(z);
    }

    @a(a = "fill")
    public void setFill(ReadableArray readableArray) {
        this.mFill = readableArray;
        markUpdated();
    }

    @a(a = "fillOpacity", d = 1.0f)
    public void setFillOpacity(float f) {
        this.mFillOpacity = f;
        markUpdated();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @a(a = "fillRule", e = 1)
    public void setFillRule(int i) {
        switch (i) {
            case 0:
                this.mFillRule = Path.FillType.EVEN_ODD;
                break;
            case 1:
                break;
            default:
                throw new JSApplicationIllegalArgumentException("fillRule " + this.mFillRule + " unrecognized");
        }
        markUpdated();
    }

    @a(a = "stroke")
    public void setStroke(ReadableArray readableArray) {
        this.mStroke = readableArray;
        markUpdated();
    }

    @a(a = "strokeOpacity", d = 1.0f)
    public void setStrokeOpacity(float f) {
        this.mStrokeOpacity = f;
        markUpdated();
    }

    @a(a = "strokeDasharray")
    public void setStrokeDasharray(ReadableArray readableArray) {
        if (readableArray != null) {
            int size = readableArray.size();
            this.mStrokeDasharray = new String[size];
            for (int i = 0; i < size; i++) {
                this.mStrokeDasharray[i] = readableArray.getString(i);
            }
        } else {
            this.mStrokeDasharray = null;
        }
        markUpdated();
    }

    @a(a = "strokeDashoffset")
    public void setStrokeDashoffset(float f) {
        this.mStrokeDashoffset = f * this.mScale;
        markUpdated();
    }

    @a(a = "strokeWidth")
    public void setStrokeWidth(String str) {
        this.mStrokeWidth = str;
        markUpdated();
    }

    @a(a = "strokeMiterlimit", d = 4.0f)
    public void setStrokeMiterlimit(float f) {
        this.mStrokeMiterlimit = f;
        markUpdated();
    }

    @a(a = "strokeLinecap", e = 1)
    public void setStrokeLinecap(int i) {
        switch (i) {
            case 0:
                this.mStrokeLinecap = Paint.Cap.BUTT;
                break;
            case 1:
                this.mStrokeLinecap = Paint.Cap.ROUND;
                break;
            case 2:
                this.mStrokeLinecap = Paint.Cap.SQUARE;
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeLinecap " + this.mStrokeLinecap + " unrecognized");
        }
        markUpdated();
    }

    @a(a = "strokeLinejoin", e = 1)
    public void setStrokeLinejoin(int i) {
        switch (i) {
            case 0:
                this.mStrokeLinejoin = Paint.Join.MITER;
                break;
            case 1:
                this.mStrokeLinejoin = Paint.Join.ROUND;
                break;
            case 2:
                this.mStrokeLinejoin = Paint.Join.BEVEL;
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeLinejoin " + this.mStrokeLinejoin + " unrecognized");
        }
        markUpdated();
    }

    @a(a = "propList")
    public void setPropList(ReadableArray readableArray) {
        if (readableArray != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            this.mAttributeList = arrayList;
            this.mPropList = arrayList;
            for (int i = 0; i < readableArray.size(); i++) {
                this.mPropList.add(propertyNameToFieldName(readableArray.getString(i)));
            }
        }
        markUpdated();
    }

    @Override // com.horcrux.svg.am
    public void draw(Canvas canvas, Paint paint, float f) {
        float f2 = f * this.mOpacity;
        if (f2 > 0.01f) {
            if (this.mPath == null) {
                this.mPath = getPath(canvas, paint);
                this.mPath.setFillType(this.mFillRule);
            }
            RectF rectF = new RectF();
            this.mPath.computeBounds(rectF, true);
            new Matrix(canvas.getMatrix()).mapRect(rectF);
            setClientRect(rectF);
            clip(canvas, paint);
            if (setupFillPaint(paint, this.mFillOpacity * f2)) {
                canvas.drawPath(this.mPath, paint);
            }
            if (setupStrokePaint(paint, f2 * this.mStrokeOpacity)) {
                canvas.drawPath(this.mPath, paint);
            }
        }
    }

    private boolean setupFillPaint(Paint paint, float f) {
        ReadableArray readableArray = this.mFill;
        if (readableArray == null || readableArray.size() <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(385);
        paint.setStyle(Paint.Style.FILL);
        setupPaint(paint, f, this.mFill);
        return true;
    }

    private boolean setupStrokePaint(Paint paint, float f) {
        ReadableArray readableArray;
        paint.reset();
        double relativeOnOther = relativeOnOther(this.mStrokeWidth);
        if (relativeOnOther == 0.0d || (readableArray = this.mStroke) == null || readableArray.size() == 0) {
            return false;
        }
        paint.setFlags(385);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(this.mStrokeLinecap);
        paint.setStrokeJoin(this.mStrokeLinejoin);
        paint.setStrokeMiter(this.mStrokeMiterlimit * this.mScale);
        paint.setStrokeWidth((float) relativeOnOther);
        setupPaint(paint, f, this.mStroke);
        String[] strArr = this.mStrokeDasharray;
        if (strArr == null) {
            return true;
        }
        int length = strArr.length;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            fArr[i] = (float) relativeOnOther(this.mStrokeDasharray[i]);
        }
        paint.setPathEffect(new DashPathEffect(fArr, this.mStrokeDashoffset));
        return true;
    }

    private void setupPaint(Paint paint, float f, ReadableArray readableArray) {
        b definedBrush;
        int i = readableArray.getInt(0);
        if (i == 0) {
            paint.setARGB((int) (readableArray.size() > 4 ? readableArray.getDouble(4) * ((double) f) * 255.0d : (double) (f * 255.0f)), (int) (readableArray.getDouble(1) * 255.0d), (int) (readableArray.getDouble(2) * 255.0d), (int) (readableArray.getDouble(3) * 255.0d));
        } else if (i == 1 && (definedBrush = getSvgShadowNode().getDefinedBrush(readableArray.getString(1))) != null) {
            if (this.mBox == null) {
                this.mBox = new RectF();
                this.mPath.computeBounds(this.mBox, true);
            }
            definedBrush.a(paint, this.mBox, this.mScale, f);
        }
    }

    @Override // com.horcrux.svg.am
    public int hitTest(float[] fArr) {
        if (this.mPath == null || !this.mInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        if (this.mRegion == null && this.mPath != null) {
            this.mRegion = getRegion(this.mPath);
        }
        if (this.mRegion == null || !this.mRegion.contains(round, round2)) {
            return -1;
        }
        Path clipPath = getClipPath();
        if (clipPath != null) {
            if (this.mClipRegionPath != clipPath) {
                this.mClipRegionPath = clipPath;
                this.mClipRegion = getRegion(clipPath);
            }
            if (!this.mClipRegion.contains(round, round2)) {
                return -1;
            }
        }
        return getReactTag();
    }

    /* access modifiers changed from: package-private */
    public Region getRegion(Path path) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        Region region = new Region();
        region.setPath(path, new Region((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom)));
        return region;
    }

    private ArrayList<String> getAttributeList() {
        return this.mAttributeList;
    }

    /* access modifiers changed from: package-private */
    public void mergeProperties(RenderableShadowNode renderableShadowNode) {
        ArrayList<String> attributeList = renderableShadowNode.getAttributeList();
        if (!(attributeList == null || attributeList.size() == 0)) {
            this.mOriginProperties = new ArrayList<>();
            ArrayList<String> arrayList = this.mPropList;
            this.mAttributeList = arrayList == null ? new ArrayList<>() : new ArrayList<>(arrayList);
            int size = attributeList.size();
            for (int i = 0; i < size; i++) {
                try {
                    String str = attributeList.get(i);
                    Field field = getClass().getField(str);
                    Object obj = field.get(renderableShadowNode);
                    this.mOriginProperties.add(field.get(this));
                    if (!hasOwnProperty(str)) {
                        this.mAttributeList.add(str);
                        field.set(this, obj);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
            this.mLastMergedList = attributeList;
        }
    }

    /* access modifiers changed from: package-private */
    public void resetProperties() {
        ArrayList<String> arrayList = this.mLastMergedList;
        if (arrayList != null && this.mOriginProperties != null) {
            try {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    getClass().getField(this.mLastMergedList.get(size)).set(this, this.mOriginProperties.get(size));
                }
                this.mLastMergedList = null;
                this.mOriginProperties = null;
                this.mAttributeList = this.mPropList;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private String propertyNameToFieldName(String str) {
        Matcher matcher = Pattern.compile("^(\\w)").matcher(str);
        StringBuffer stringBuffer = new StringBuffer("m");
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private boolean hasOwnProperty(String str) {
        ArrayList<String> arrayList = this.mAttributeList;
        return arrayList != null && arrayList.contains(str);
    }
}
