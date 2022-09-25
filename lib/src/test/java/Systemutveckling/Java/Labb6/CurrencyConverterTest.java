
package Systemutveckling.Java.Labb6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

class CurrencyConverterTest {

	private CurrencyConverter converter = new CurrencyConverter();
	private static final CurrencyUnit usd = CurrencyUnit.of("USD");
	private static final CurrencyUnit eur = CurrencyUnit.of("EUR");
	private static final CurrencyUnit gbp = CurrencyUnit.of("GBP");
	private static Money amount1 = null;
	private static Money amount2 = null;
	private static Money amount3 = null;

	@Test
	void testConvertZero() {
		
		amount1 = Money.parse("USD 0");
		amount2 = Money.parse("EUR 0");
		amount3 = Money.parse("GBP 0");
		
		assertEquals(converter.convert(amount1, usd, gbp), Money.parse("GBP 0"));
		assertEquals(converter.convert(amount2, eur, usd), Money.parse("USD 0"));
		assertEquals(converter.convert(amount3, gbp, eur), Money.parse("EUR 0"));

	}

	@Test
	void testConvertSameCurrency() {

		amount1 = Money.parse("USD 100");
		amount2 = Money.parse("EUR 100");
		amount3 = Money.parse("GBP 100");


		Assertions.assertThrows(NullPointerException.class, () -> {
			converter.convert(amount1, usd, usd);
		});
		Assertions.assertThrows(NullPointerException.class, () -> {
			converter.convert(amount2, eur, eur);
		});
		Assertions.assertThrows(NullPointerException.class, () -> {
			converter.convert(amount3, gbp, gbp);
		});

	}

}
