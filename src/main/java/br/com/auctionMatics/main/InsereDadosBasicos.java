package br.com.auctionMatics.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class InsereDadosBasicos {

    public Integer obtemDadosWebsite(Integer numLeilao, Integer pagina ){

        //Insere os dados obtidos via website
        String siteLeilao = "https://www.guariglialeiloes.com.br/leilao/"+ numLeilao + "/lotes?page=" + pagina;
        try {
            Document doc = Jsoup.connect(siteLeilao).get();
            Elements lotes = doc.select(".body-lote");
            String marca = null;
            String ano = null;
            String cor = null;
            String combustivel = null;
            String kilometragem = null;


            if ( lotes.toString().length() > 0 ) {

                DadosLoteCRUD dlote = new DadosLoteCRUD();
                Integer numeroLeilao = numLeilao;
                boolean flJumpToNext = false;

                Integer contadorLote = null;
                try {
                    contadorLote = dlote.buscaMaxLoteID();
                } catch (Exception e) {
                    System.out.println("Max ID nao encontrado");
                    contadorLote = 0;
                }
                contadorLote++;

                for (Element lote : lotes) {

                    flJumpToNext = false;
                    int i=1;

                    for (String dadoLote : lote.text().split(":")){

                        //Marca/Modelo
                        if ( i == 2){
                            marca = dadoLote.substring(0, (dadoLote.length()-6)).trim();
                        }

                        //Ano
                        if ( i == 4){
                            ano = dadoLote.substring(0, (dadoLote.length()-4)).trim();
                        }

                        //Cor
                        if ( i == 5){
                            try {
                                cor = dadoLote.substring(0, (dadoLote.length()-12)).trim();
                            } catch (Exception e) {
                                //Se isso ocorrer e pq o lote nao possui ddos corretos. Vamos pular este lote.
                                flJumpToNext = true;
                                continue;
                            }
                        }

                        //Combustivel
                        if ( i == 6){
                            combustivel = dadoLote.substring(0, (dadoLote.length()-3)).trim();
                        }

                        //KM
                        if ( i == 7){
                            String[] km = dadoLote.split(" ");
                            kilometragem = km[1];
                        }

                        i++;
                    }

                    if (flJumpToNext){
                        continue;
                    }

                    Elements lotesElem = lote.select("a");

                    for (Element loteElem : lotesElem) {

                        String lotefinal = loteElem.attributes().toString();
                        if (lotefinal.substring(7).startsWith("h")) {
                            String linkLote = lotefinal.substring(7, lotefinal.length() - 1);
                            dlote.insereOuAtualiza(contadorLote, numeroLeilao, marca, ano, kilometragem, cor, combustivel, linkLote);
                            contadorLote++;
                        }
                    }
                }
                return 0;
            }else{
                return 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){

//        InsereDadosBasicos od = new InsereDadosBasicos();
//        System.out.println(od.obtemDadosWebsite(381, 1));

        String siteLeilao = "https://www.guariglialeiloes.com.br/leilao/"+ 381 + "/lotes?page=" + 1;
        Document doc;
		try {
			doc = Jsoup.connect(siteLeilao).get();
			Elements lotes = doc.select(".lance-lote");
			for (Element lote : lotes) {
				Elements divs = lote.select("div");
				String[] valores = null;
				int i=0;
				for (Element div : divs) {
					if (div.hasClass("lance_atual") || div.hasClass("lance")) {
						System.out.println(div.text());
						valores[i]=div.text();
						i++;
					}
				}
				System.out.println("Esse e o valor" + valores[3]);

		        
				

		        
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            }        
        


}
