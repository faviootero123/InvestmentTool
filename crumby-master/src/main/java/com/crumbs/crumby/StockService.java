package com.crumbs.crumby;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.crumbs.crumby.Entities.StockHistoryEntity;
import com.crumbs.crumby.dto.StockDto;

import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Service
public class StockService {

	public List<StockDto> stockList = new ArrayList<>();
	public BigDecimal startingAmount;

	public StockDto getStockInfo(String stockName) throws Exception {
		Stock stock = YahooFinance.get(stockName.toUpperCase(), true);
		return new StockDto(stock.getName(), stock.getQuote().getPrice(), stock.getQuote().getChange(),
				stock.getCurrency(), stock.getQuote().getBid(), stock.getQuote().getChangeFromYearHighInPercent(),
				stockName.toUpperCase());
	}

	public List<StockHistoryEntity> getHistory(String stockName, int year, String searchType) throws Exception {
		List<StockHistoryEntity> histories = new ArrayList<>();
		Stock stock = null;
		List<HistoricalQuote> quotes = null;

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.MONTH, Integer.valueOf("-" + year));
		stock = YahooFinance.get(stockName);
		quotes = stock.getHistory(from, to, getInterval(searchType));

		for (var quote : quotes) {
			histories.add(new StockHistoryEntity(quote.getOpen(), quote.getSymbol(), quote.getDate(), quote.getHigh(),
					quote.getLow(), quote.getClose(), quote.getAdjClose(), quote.getVolume()));

		}
		return histories;
	}

	private String convertor(Calendar cal) {
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = format1.format(cal.getTime());
		return formatted;
	}

	private Interval getInterval(String searchType) {
		Interval interval = null;
		switch (searchType) {
			case "DAILY":
				interval = Interval.DAILY;
				break;
			case "WEEKLY":
				interval = Interval.WEEKLY;
				break;
			case "MONTHLY":
				interval = Interval.MONTHLY;
				break;

		}
		return interval;
	}

	public Stock getStock(String stockName) throws IOException {
		return YahooFinance.get(stockName.toUpperCase(), true);
	}
}