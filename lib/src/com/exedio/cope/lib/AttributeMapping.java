
package persistence;

public abstract class AttributeMapping
{
	public final Attribute sourceAttribute;
	private final String sqlMappingStart;
	private final String sqlMappingEnd;
	private final String functionName;

	public AttributeMapping(final Attribute sourceAttribute,
									final String sqlMappingStart,
									final String sqlMappingEnd,
									final String functionName)
	{
		this.sourceAttribute = sourceAttribute;
		this.sqlMappingStart = sqlMappingStart;
		this.sqlMappingEnd = sqlMappingEnd;
		this.functionName = functionName;
	}

	public abstract Object mapJava(Object sourceValue);
	

	public final String toString()
	{
		return functionName + '(' + sourceAttribute.getName() + ')';
	}
	
}
