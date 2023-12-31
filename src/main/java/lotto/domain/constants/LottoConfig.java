package lotto.domain.constants;

public enum LottoConfig {
    LOTTO_NUMBER_MAX(45),
    LOTTO_NUMBER_MIN(1),
    LOTTO_SIZE(6),
    LOTTO_UNIT_PRICE(1000);
    private final int value;

    LottoConfig(final int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
