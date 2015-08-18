import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;


public class MainTest 
{

	@Test
	public void testMatrix()
	{
		Matrix matrix1 = new Matrix (3,2);
		Matrix matrix2 = new Matrix (3,2);
		Matrix matrix3 = new Matrix (1,2);
		assertEquals(matrix1, matrix1);
		assertEquals(matrix1.ReturnColumna(), matrix2.ReturnColumna());

	}

}
