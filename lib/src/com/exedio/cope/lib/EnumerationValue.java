
package persistence;

public class EnumerationValue
{
	public final int number;
	public final String code;
	
	public EnumerationValue(final int number, final String code)
	{
		this.number = number;
		this.code = code;
	}
	
	public final String toString()
	{
		return code + '(' + number + ')';
	}
	
}
