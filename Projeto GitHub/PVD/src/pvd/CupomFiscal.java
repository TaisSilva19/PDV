package pvd;

import java.util.Date;


public class CupomFiscal {
    private Date data;
    private String hora;
    private String sistema;
    private String metodoPagamento;

    public CupomFiscal(Date data, String hora, String produto, int quantidade, double valor, String sistema,
                       String metodoPagamento) {
        this.data = data;
        this.hora = hora;
        this.sistema = sistema;
        this.metodoPagamento = metodoPagamento;
    }

	public Date getData() {
        return data;
    }

   public void setData(Date data) {
       this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
} 