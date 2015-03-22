package com.chuidiang.matematicas.funciones;

import junit.framework.TestCase;

/**
 * @author Chuidiang
 *
 */
public class TestPolinomio extends TestCase {
	private static final double _1E_10 = 1e-10;

	public void testPolinomio1() {
		double coeficientes[] = { 1.0, 1.0, 1.0 };
		Polinomio p = new Polinomio(coeficientes);

		assertEquals(3.0, p.getY(1.0), _1E_10);
		assertEquals(1.0, p.getY(0.0), _1E_10);
		assertEquals(1.75, p.getY(0.5), _1E_10);
	}
	public void testPolinomio2() {
		double coeficientes[] = {};
		Polinomio p = new Polinomio(coeficientes);

		assertEquals(0.0, p.getY(1.0), _1E_10);
		assertEquals(0.0, p.getY(0.0), _1E_10);
		assertEquals(0.0, p.getY(0.5), _1E_10);
	}

	public void testPolinomio3()
	{
		double coeficientes[] = {0.0,0.0,1.0};
		Polinomio p = new Polinomio(coeficientes);
		
		assertEquals(0.0, p.getY(0.0),_1E_10);
		assertEquals(1.0, p.getY(1.0),_1E_10);
		assertEquals(4.0, p.getY(2.0),_1E_10);
	}
}
