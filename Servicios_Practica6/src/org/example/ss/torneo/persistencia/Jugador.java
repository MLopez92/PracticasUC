package org.example.ss.torneo.persistencia;

public class Jugador {
	
	private int dorsalJugador;
	private String nombre;
	private int goles;
	private int amarillas;
	private int rojas;
	
	public int getDorsalJugador() {
		return dorsalJugador;
	}

	public void setDorsalJugador(int dorsalJugador) {
		this.dorsalJugador = dorsalJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public int getAmarillas() {
		return amarillas;
	}

	public void setAmarillas(int amarillas) {
		this.amarillas = amarillas;
	}

	public int getRojas() {
		return rojas;
	}

	public void setRojas(int rojas) {
		this.rojas = rojas;
	}

	public Jugador(){}

}
