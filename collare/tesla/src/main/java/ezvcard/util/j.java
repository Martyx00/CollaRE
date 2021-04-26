package ezvcard.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/* compiled from: VCardFloatFormatter */
public class j extends DecimalFormat {
    public j() {
        this(6);
    }

    public j(int i) {
        setMaximumFractionDigits(i);
        if (i > 0) {
            setMinimumFractionDigits(1);
        }
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setMinusSign('-');
        setDecimalFormatSymbols(decimalFormatSymbols);
    }
}
