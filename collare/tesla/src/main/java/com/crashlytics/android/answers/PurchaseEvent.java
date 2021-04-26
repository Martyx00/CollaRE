package com.crashlytics.android.answers;

import java.math.BigDecimal;
import java.util.Currency;

public class PurchaseEvent extends PredefinedEvent<PurchaseEvent> {
    static final String CURRENCY_ATTRIBUTE = "currency";
    static final String ITEM_ID_ATTRIBUTE = "itemId";
    static final String ITEM_NAME_ATTRIBUTE = "itemName";
    static final String ITEM_PRICE_ATTRIBUTE = "itemPrice";
    static final String ITEM_TYPE_ATTRIBUTE = "itemType";
    static final BigDecimal MICRO_CONSTANT = BigDecimal.valueOf(1000000L);
    static final String SUCCESS_ATTRIBUTE = "success";
    static final String TYPE = "purchase";

    /* access modifiers changed from: package-private */
    @Override // com.crashlytics.android.answers.PredefinedEvent
    public String getPredefinedType() {
        return "purchase";
    }

    public PurchaseEvent putItemId(String str) {
        this.predefinedAttributes.put(ITEM_ID_ATTRIBUTE, str);
        return this;
    }

    public PurchaseEvent putItemName(String str) {
        this.predefinedAttributes.put(ITEM_NAME_ATTRIBUTE, str);
        return this;
    }

    public PurchaseEvent putItemType(String str) {
        this.predefinedAttributes.put(ITEM_TYPE_ATTRIBUTE, str);
        return this;
    }

    public PurchaseEvent putItemPrice(BigDecimal bigDecimal) {
        if (!this.validator.isNull(bigDecimal, ITEM_PRICE_ATTRIBUTE)) {
            this.predefinedAttributes.put(ITEM_PRICE_ATTRIBUTE, Long.valueOf(priceToMicros(bigDecimal)));
        }
        return this;
    }

    public PurchaseEvent putCurrency(Currency currency) {
        if (!this.validator.isNull(currency, "currency")) {
            this.predefinedAttributes.put("currency", currency.getCurrencyCode());
        }
        return this;
    }

    public PurchaseEvent putSuccess(boolean z) {
        this.predefinedAttributes.put("success", Boolean.toString(z));
        return this;
    }

    /* access modifiers changed from: package-private */
    public long priceToMicros(BigDecimal bigDecimal) {
        return MICRO_CONSTANT.multiply(bigDecimal).longValue();
    }
}
