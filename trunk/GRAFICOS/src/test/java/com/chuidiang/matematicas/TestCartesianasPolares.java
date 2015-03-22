package com.chuidiang.matematicas;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chuidiang.matematicas.CartesianasPolares;

public class TestCartesianasPolares {

	@Test
	public void testDameAngulo() {
		assertEquals(Math.PI/4.0,CartesianasPolares.dameAngulo(1.0, 1.0),1e-6);
	}

	@Test
	public void testDameModulo() {
		assertEquals(Math.sqrt(2.0),CartesianasPolares.dameModulo(1.0, 1.0),1e-6);
	}

	@Test
	public void testDameX() {
		assertEquals(1.0,CartesianasPolares.dameX(Math.sqrt(2.0), Math.PI/4.0),1e-6);
	}

	@Test
	public void testDameY() {
		assertEquals(1.0,CartesianasPolares.dameY(Math.sqrt(2.0), Math.PI/4.0),1e-6);
	}

}
