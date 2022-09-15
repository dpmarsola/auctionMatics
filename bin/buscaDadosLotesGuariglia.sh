declare -a codLote
declare -a marcaVeiculo 
declare -a modeloVeiculo 
declare -a anoVeiculo
declare -a kmVeiculo
lightGreen='\033[1;32m'
resetColors='\033[0m'


limpaArquivosAntigos(){
    ls $WRK_DIR/leilao* 2> /dev/null > /dev/null
    
    if [ $? == 0 ]
    then
       rm -f $WRK_DIR/leilao* 2> /dev/null > /dev/null
    fi

    ls $WRK_DIR/tmp* 2> /dev/null > /dev/null
    
    if [ $? == 0 ]
    then
       rm -f $WRK_DIR/tmp* 2> /dev/null > /dev/null
    fi


    ls $WRK_DIR/busca.finalizada 2> /dev/null > /dev/null
    
    if [ $? == 0 ]
    then
       rm -f $WRK_DIR/busca.finalizada 2> /dev/null > /dev/null
    fi
}

obtemLotesNoSiteLeiloeiro(){
    while true 
    do
        wget -O "$WRK_DIR/leilao$leilao.pagina$pagina.html" "https://www.guariglialeiloes.com.br/leilao/$leilao/lotes?page=$pagina"
        pagina=$(expr $pagina + 1)
        fim=$(grep 'NENHUM LOTE ENCONTRADO NO MOMENTO' $WRK_DIR/leilao$leilao*)
        if [ ${#fim} -gt 1 ]
        then
            break        
        fi
    done

    cat $WRK_DIR/leilao* > $WRK_DIR/tmp.file
    totItems=$(grep 'Marca' $WRK_DIR/tmp.file | wc -l)
    
}

BKPisolaDadosVeiculo(){

    i=0;

    while read line
    do

       result=$(echo $line | grep 'Marca')

       if [ $? -eq 0 ] 
       then
            codLote[$i]=$(echo "GUA"$leilao$i)
            marcaVeiculo[$i]=$(echo $line | cut -d'<' -f3 | sed 's/\/b>//g' | cut -d'/' -f1 | sed 's/ //g')
            modeloVeiculo[$i]=$(echo $line | cut -d'<' -f3 | sed 's/\/b>//g' | cut -d'/' -f2)
       fi 
       
       result=$(echo $line | grep 'Ano/')

       if [ $? -eq 0 ] 
       then
            anoVeiculo[$i]=$(echo $line | cut -d'<' -f4 | sed 's/\/b> //g')
       fi 
       
       result=$(echo $line | grep 'KM:')

       if [ $? -eq 0 ] 
       then
            kmVeiculo[$i]=$(echo $line | cut -d'<' -f10 | sed 's/\/b> //g')
       fi 
       
       result=$(echo $line | grep 'Cor:')

       if [ $? -eq 0 ] 
       then
            corVeiculo[$i]=$(echo $line | cut -d'<' -f4 | cut -d'>' -f2 | sed 's/\/b> //g' | sed 's/ //g')
            combustivelVeiculo[$i]=$(echo $line | cut -d'<' -f7 | cut -d'>' -f2 | sed 's/\/b> //g' | sed 's/ //g')
            flagFinalizou=1
       fi 
       
       if [ "$flagFinalizou" == "1" ]
       then
            echo ${codLote[$i]}\,${marcaVeiculo[$i]}\,${modeloVeiculo[$i]}\,${anoVeiculo[$i]}\,${kmVeiculo[$i]}\,${corVeiculo[$i]}\,${combustivelVeiculo[$i]}
            echo -ne "${resetColors}    Adding data for item ${lightGreen}#$i${resetColors} of ${lightGreen}$totItems${resetColors} items.\r" > /proc/self/fd/2
            flagFinalizou=0
            i=$(expr $i + 1)
       fi


    done < $WRK_DIR/tmp.file
    

}

isolaDadosVeiculo(){

    i=0;

    while read line
    do

        date

    done < $WRK_DIR/tmp.file
    

}

################################################################################################################
#################################                MAIN                  #########################################
################################################################################################################

leilao=$1
pagina=1
BASE_DIR=$HOME/IdeaProjects/auctionMatics
WRK_DIR=$BASE_DIR/data

limpaArquivosAntigos
obtemLotesNoSiteLeiloeiro
isolaDadosVeiculo > $WRK_DIR/output.especializadoGuariglia
cat $WRK_DIR/output.especializadoGuariglia
touch $WRK_DIR/busca.finalizada

echo -e "#########################################################################"

s