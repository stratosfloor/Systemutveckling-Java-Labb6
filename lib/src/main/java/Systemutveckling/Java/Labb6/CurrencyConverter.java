package Systemutveckling.Java.Labb6;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.*;

public class CurrencyConverter {

	private static final BigDecimal USDtoEUR = new BigDecimal("1.0250677");
	private static final BigDecimal USDtoGBP = new BigDecimal("0.90171468");

	private static final BigDecimal EURtoGBP = new BigDecimal("0.88150738");
	private static final BigDecimal EURtoUSD = new BigDecimal("0.97554528");

	private static final BigDecimal GBPtoUSD = new BigDecimal("1.1089982");
	private static final BigDecimal GBPtoEUR = new BigDecimal("1.1344205");

	private static final CurrencyUnit usd = CurrencyUnit.of("USD");
	private static final CurrencyUnit eur = CurrencyUnit.of("EUR");
	private static final CurrencyUnit gbp = CurrencyUnit.of("GBP");

	protected Money convert(Money amount, CurrencyUnit fromUnit, CurrencyUnit toUnit) {

		BigDecimal rate = null;


		try {

			if (fromUnit == usd) {
				if (toUnit == gbp) {
					rate = USDtoGBP;
				} else if (toUnit == eur) {
					rate = USDtoEUR;
				}
			} else if (fromUnit == eur) {
				if (toUnit == gbp) {
					rate = EURtoGBP;
				} else if (toUnit == usd) {
					rate = EURtoUSD;
				}

			} else if (fromUnit == gbp) {
				if (toUnit == usd) {
					rate = GBPtoUSD;
				} else if (toUnit == eur) {
					rate = GBPtoEUR;
				}
			} else {
				System.out.println("FEL");
			}

		} catch (NullPointerException e) {
			System.out.println("Cannot convert to the same currency");
		}
		return amount.convertedTo(toUnit, rate, RoundingMode.HALF_UP);

	}

}
