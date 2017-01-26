package com.gbc.core;

/*
 * An immutable class that wil be used across threads to represent Stock objects
 */
public final class Stock 
{
	/**
	 * A builder class to build either a common or a preferred Stock
	 * @author Raja Krovvidi
	 *
	 */
	public static class StockBuilder
	{
		public static Stock createCommonStock(String symbol, double parValue, double lastDividend) throws InvalidStockParameterException
		{
			return new Stock(symbol, true, parValue, lastDividend, 0.0);
		}
	
		public static Stock createPreferredStock(String symbol, double parValue, double lastDividend, double fixedDividend) throws InvalidStockParameterException
		{
			return new Stock(symbol, false, parValue, lastDividend, fixedDividend);
		}
	}
	
	/*
	 * By design we want to guarantee a well formed Stock instance.
	 * We do eager checking of parameters and throw exceptions rather than validate at the time of adding new stocks
	 * This guarantees a well constructed and immutable Stock instance
	 * Hide the constructor.. mandate using the builder
	 */
	private Stock(String symbol, boolean isCommonStock, double parValue, double lastDividend, double fixedDividend ) 
	throws InvalidStockParameterException
	{
		if (symbol != null && symbol.isEmpty()) throw new InvalidStockParameterException("Stock needs a valid symbol");
		if (parValue < 0.0) throw new InvalidStockParameterException("Par Value cannot be negative");
		if (lastDividend < 0.0) throw new InvalidStockParameterException("Last Dividend cannot be negative");
		if (!isCommonStock && fixedDividend <= 0.0) throw new InvalidStockParameterException("Fixed Dividend must be postive for Preferred Stock");
		// if (!isCommonStock && parValue == 0.0) throw new InvalidStockParameterException("Par Value must be postive for Preferred Stock");
		
		_symbol = symbol;
		_parValue = parValue;
		_lastDividend = lastDividend;
		_fixedDividend = fixedDividend;
		_isCommonStock = isCommonStock;
	}
	
	public final String getName()
	{
		return (_isCommonStock) ? "COMMON_STOCK" : "PREFERRED_STOCK";
	}
	
	public final double dividendYield(double price) throws IllegalArgumentException
	{
		if (price <= 0.0 ) throw new IllegalArgumentException("Price needs to be positive");

		double dYield = 0.0;
		if (_isCommonStock)
		{
			dYield = _lastDividend / price;
		}
		else
		{
			dYield = (_fixedDividend * _parValue) / price;
		}
		return dYield;
	}
	
	public final double peRatio(double price) throws IllegalArgumentException
	{
		if (price < 0.0) 
			throw new IllegalArgumentException("Price must be positive");
		
		double peRatio = 0.0;
		if (_isCommonStock)
		{
			if (_lastDividend <= 0.0)
				throw new IllegalArgumentException("Last Dividend must be postive for calculating the P/E Ratio");
				
			peRatio =  price / _lastDividend;
		}
		else
		{
			if (_fixedDividend <= 0.0)
				throw new IllegalArgumentException("For Preferred Stocks, Fixed Dividend must be postive for calculating the P/E Ratio");
			
			if (_parValue == 0.0)
				throw new IllegalArgumentException("For Preferred Stocks, Par Value must be postive for calculating the P/E Ratio");

			peRatio =  price / (_parValue * _fixedDividend);
		}
		return peRatio;
	}
	
	public final double vwap(long timeInMillis) throws IllegalArgumentException
	{
		if ( timeInMillis > System.currentTimeMillis()) 
			throw new IllegalArgumentException("Do not support for future vwap");
		
		// TODO:
		return 0.0;
	}
	
	public final String getSymbol() 
	{
		return _symbol;
	}
	
	public final double getParValue()
	{
		return _parValue;
	}
	
	public final double getLastDividend()
	{
		return _lastDividend;
	}
	
	public final double getFixedDividend()
	{
		return _fixedDividend;
	}
	
	public final boolean isCommonStock()
	{
		return _isCommonStock;
	}
	
	public final boolean isPreferredStock()
	{
		return (!_isCommonStock);
	}
	
	@Override
	public final String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(_symbol);
		sb.append(":");
		sb.append(_isCommonStock);
		sb.append(":");
		sb.append(_parValue);
		sb.append(":");
		sb.append("_lastDividend");
		sb.append(":");
		sb.append("_fixedDividend");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(_fixedDividend);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (_isCommonStock ? 1231 : 1237);
		temp = Double.doubleToLongBits(_lastDividend);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(_parValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((_symbol == null) ? 0 : _symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Stock other = (Stock) obj;
		if (Double.doubleToLongBits(_fixedDividend) != Double.doubleToLongBits(other._fixedDividend)) {
			return false;
		}
		if (_isCommonStock != other._isCommonStock) {
			return false;
		}
		if (Double.doubleToLongBits(_lastDividend) != Double.doubleToLongBits(other._lastDividend)) {
			return false;
		}
		if (Double.doubleToLongBits(_parValue) != Double.doubleToLongBits(other._parValue)) {
			return false;
		}
		if (_symbol == null) {
			if (other._symbol != null) {
				return false;
			}
		} else if (!_symbol.equals(other._symbol)) {
			return false;
		}
		return true;
	}

	private final String _symbol;
	private final double _parValue;
	private final double _lastDividend;
	private final double _fixedDividend;
	private final boolean _isCommonStock;
}
