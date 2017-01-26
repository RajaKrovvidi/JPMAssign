package com.gbc.core;

/**
 * An exception class thrown when the Stock is not registered but a request to record trade is presented
 * The handler may reuse the trade for recording. 
 * @author Raja Krovvidi
 *
 */
public final class StockSymbolNotRegisteredException extends Exception {


	public StockSymbolNotRegisteredException(Trade record, String msg) 
	{
		super(msg);
		_tradeRecord = record;
	}

	public final Trade getTradeRequestedToRecord()
	{
		return _tradeRecord;
	}
	
	private final Trade _tradeRecord;
}
