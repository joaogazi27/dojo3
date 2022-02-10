package Interfaces;

import java.util.Date;

public interface TransacaoEmConta
{
    public float valor();
    public String descricao();
    public String tipoOperacao();
    public Date data();
}
