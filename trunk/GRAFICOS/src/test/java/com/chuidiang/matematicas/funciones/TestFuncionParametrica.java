package com.chuidiang.matematicas.funciones;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestFuncionParametrica {

	@Test
	public void testGetX() {
		Seno sin = new Seno();
		
		FuncionParametrica fp = new FuncionParametrica(
				sin,
				new IfzFuncionMatematica() {
				
					public double getY(double x) {
						return 0;
					}
				
				});
		
		for (double t = -1.0; t < 1.0; t+=0.1)
		{
			assertEquals(sin.getY(t),fp.getX(t),1e-10 );
			assertEquals(0.0,fp.getY(t),1e-10 );
		}
	}

	@Test
	public void testGetY() {
		Seno sin = new Seno();
		
		FuncionParametrica fp = new FuncionParametrica(
				new IfzFuncionMatematica() {
				
					public double getY(double x) {
						return 0;
					}
				},
				sin
				);
		
		for (double t = -1.0; t < 1.0; t+=0.1)
		{
			assertEquals(0.0,fp.getX(t),1e-10 );
			assertEquals(sin.getY(t),fp.getY(t),1e-10 );
		}
	}

}
