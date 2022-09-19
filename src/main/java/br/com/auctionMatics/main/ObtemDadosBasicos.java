package br.com.auctionMatics.main;

import java.util.List;

public class ObtemDadosBasicos extends Thread{
	
	Integer numleilao;
	
	public ObtemDadosBasicos(Integer numleilao) {
		this.numleilao = numleilao;
	}

    public void run() {

        boolean flContinuaBusca = true;
        Integer numPagina = 1;
        
        StatusConsultaCRUD sc = new StatusConsultaCRUD();
        sc.insereOuAtualiza(numleilao, "INI");

        while (flContinuaBusca){

            InsereDadosBasicos idb = new InsereDadosBasicos();
            if ( idb.obtemDadosWebsite(numleilao, numPagina) == 0){
                numPagina++;
            }else{
                flContinuaBusca = false;
            }

        }
        
        sc.insereOuAtualiza(numleilao, "FIM");

    }
    
}