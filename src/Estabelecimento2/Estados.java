package Estabelecimento2;

public class Estados {

	private String estadosBr;
	
	public Estados(String estados){
		this.estadosBr = estados;
	}
	
	@Override
	public String toString(){
		return estadosBr;
	}

	public String getEstadosBr() {
		return estadosBr;
	}

	public void setEstadosBr(String estadosBr) {
		this.estadosBr = estadosBr;
	}
	
	
}
