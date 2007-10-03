import static org.junit.Assert.*;
import org.junit.*;

public class UnTest
{
	private boolean valor=false;

	@org.junit.Before
	public void inicializa()
	{
		valor=true;
	}

	@Test
	public void laPrueba()
	{
		assertEquals(valor,true);
	}

	public static void main (String [] args)
	{
		org.junit.runner.JUnitCore.main("UnTest");
	}
}
