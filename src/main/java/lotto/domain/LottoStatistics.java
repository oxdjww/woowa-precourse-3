package lotto.domain;


import static lotto.domain.constants.LottoConfig.LOTTO_UNIT_PRICE;
import static lotto.domain.constants.LottoStatisticsConstants.DOTTED_LINE;
import static lotto.domain.constants.LottoStatisticsConstants.INLINE;
import static lotto.domain.constants.LottoStatisticsConstants.PROFIT_RATE_NOTICE;
import static lotto.domain.constants.LottoStatisticsConstants.STATISTICS_NOTICE;
import static lotto.domain.constants.LottoStatisticsContent.SECOND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.constants.LottoStatisticsContent;

public class LottoStatistics {
    private final List<LottoStatisticsContent> lottoResult = new ArrayList<>();
    private final int purchaseCount;
    private int totalPrize = 0;

    private LottoStatistics(final Lottos lottoTicket, final Lotto winningLotto, final Bonus bonus, final Buyer buyer) {
        List<Integer> winningNumber = winningLotto.getNumbers();
        Integer bonusNumber = bonus.getBonusNumber();
        purchaseCount = buyer.getPurchaseCount();

        for (Lotto lotto : lottoTicket.getLottoTicket()) {
            boolean bonusMatch = false;
            long count = getLottoMatchCount(winningNumber, lotto);
            if (count == SECOND.getHitCount() && lotto.getNumbers().contains(bonusNumber)) {
                bonusMatch = true;
            }
            LottoStatisticsContent.matchHitCount(count, bonusMatch).increasePrizeCount();
            totalPrize += LottoStatisticsContent.matchHitCount(count, bonusMatch).getPrize();
        }
        lottoResult.addAll(Arrays.asList(LottoStatisticsContent.values()));
    }

    private long getLottoMatchCount(final List<Integer> winningNumber, final Lotto lotto) {
        return lotto.getNumbers().stream()
                .filter(winningNumber::contains)
                .count();
    }

    public static LottoStatistics from(final Lottos lottoTicket, final Lotto winningLotto, final Bonus bonus,
                                       final Buyer buyer) {
        return new LottoStatistics((lottoTicket), winningLotto, bonus, buyer);
    }

    public String generateLottoStatisticsForm() {
        // MISS 제거
        lottoResult.remove(0);

        String prefix = getStatisticsPrefix();
        String statistics = generateStatistics();
        String profitRate = generateProfitRate();

        return String.format(
                "%s%s%s",
                prefix, statistics, profitRate
        );
    }

    private String getStatisticsPrefix() {
        return STATISTICS_NOTICE.getValue() + INLINE.getValue() + DOTTED_LINE.getValue() + INLINE.getValue();
    }

    private String generateStatistics() {
        return lottoResult.stream()
                .map(e -> String.format(
                        "%s%d개", e.getMessage(), e.getPrizeCount())
                )
                .collect(Collectors.joining(INLINE.getValue()));
    }

    private String generateProfitRate() {
        return String.format(
                INLINE.getValue() + PROFIT_RATE_NOTICE.getValue() + INLINE.getValue(),
                ((double) totalPrize / (this.purchaseCount * LOTTO_UNIT_PRICE.getValue()) * 100)
        );
    }
}
