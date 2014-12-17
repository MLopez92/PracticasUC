package org.example.ss.torneo.persistencia;

import java.util.List;

public class Equipo {
	
	private String nombreEquipo;
	private List<Jugador> jugadores;
	//podríamos poner tambien un atributo apuntando a la categoria

	public Equipo (){}

	public String getNombre() {
		return nombreEquipo;
	}

	public void setNombre(String nombre) {
		this.nombreEquipo = nombre;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

}
