package br.pucminas.tccordemcompra.dominio;

public enum Status {

	PENDENTE(1),
	FINALIZADO(2),
	RECUSADO(3);
	
	private int id;
	
	private Status(int id) {
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static Status valueOfId(int id) {
		Status[] values = Status.values();
		Status encontrado = null;
		
		for (Status st : values) {
			if(st.getId() == id) {
				encontrado = st;
			}
		}
		
		return encontrado;
	}
	
	
}
