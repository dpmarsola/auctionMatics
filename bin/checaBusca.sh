#!/usr/bin/bash

BASE_DIR=$HOME/IdeaProjects/auctionMatics
WRK_DIR=$BASE_DIR/data

if [ -f $WRK_DIR/busca.finalizada ]
then
    echo "1"
else
    echo "0"
fi

