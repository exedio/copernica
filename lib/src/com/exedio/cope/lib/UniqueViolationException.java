
package persistence;

/**
 * Is thrown, when a persistent modifivation violates a unique-constraint.
 */
public class UniqueViolationException extends ConstraintViolationException
{
	
	public UniqueViolationException()
	{
	}
	
}
