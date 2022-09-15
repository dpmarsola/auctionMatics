package br.com.auctionMatics.main;

public class ObtemDadosBasicos {

    public void buscaInsereDadosLeilao(Integer numLeilao) {

        boolean flContinuaBusca = true;
        Integer numPagina = 1;

        while (flContinuaBusca){

            InsereDadodBasicos idb = new InsereDadodBasicos();
            if ( idb.obtemDadosWebsite(numLeilao, numPagina) == 0){
                numPagina++;
            }else{
                flContinuaBusca = false;
            }

        }

    }
    public static void main(String[] args) {

        ObtemDadosBasicos lag = new ObtemDadosBasicos();
        lag.buscaInsereDadosLeilao(380);
    }



}