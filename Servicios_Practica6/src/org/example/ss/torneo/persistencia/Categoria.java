package org.example.ss.torneo.persistencia;

import java.util.List;

public class Categoria {
	
	private String categoriaId;
	private List<Equipo> equipos;
	
	public Categoria(){}

	public String getid() {
		return categoriaId;
	}

	public void setid(String nombre) {
		this.categoriaId = nombre;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

}
