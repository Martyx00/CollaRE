package com.crashlytics.android.answers;

import java.math.BigDecimal;
import java.util.Currency;

public class StartCheckoutEvent extends PredefinedEvent<StartCheckoutEvent> {
    static final String CURRENCY_ATTRIBUTE = "currency";
    static final String ITEM_COUNT_ATTRIBUTE = "itemCount";
    static final BigDecimal MICRO_CONSTANT = BigDecimal.valueOf(1000000L);
    static final String TOTAL_PRICE_ATTRIBUTE = "totalPrice";
    static final String TYPE = "startCheckout";

    /* access modifiers changed from: package-private */
    @Override // com.crashlytics.android.answers.PredefinedEvent
    public String getPredefinedType() {
        return TYPE;
    }

    public StartCheckoutEvent putItemCount(int i) {
        this.predefinedAttributes.put(ITEM_COUNT_ATTRIBUTE, Integer.valueOf(i));
        return this;
    }

    public StartCheckoutEvent putTotalPrice(BigDecimal bigDecimal) {
        if (!this.validator.isNull(bigDecimal, TOTAL_PRICE_ATTRIBUTE)) {
            this.predefinedAttributes.put(TOTAL_PRICE_ATTRIBUTE, Long.valueOf(priceToMicros(bigDecimal)));
        }
        return this;
    }

    public StartCheckoutEvent putCurrency(Currency currency) {
        if (!this.validator.isNull(currency, "currency")) {
            this.predefinedAttributes.put("currency", currency.getCurrencyCode());
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public long priceToMicros(BigDecimal bigDecimal) {
        return MICRO_CONSTANT.multiply(bigDecimal).longValue();
    }
}
