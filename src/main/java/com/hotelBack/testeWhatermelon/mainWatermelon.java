package com.hotelBack.testeWhatermelon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class mainWatermelon {
    public static void main(String[] args) {
        int x = 0;
        List<Integer> lista = new ArrayList<>();
        List<Integer> ordenada = new ArrayList<>();
        Random aleatorio = new Random();
        for(int i = 0; i < 5; i++){
            lista.add(aleatorio.nextInt(100));
        }
        for (int i = 0; i < lista.size(); i++){
            for (int j = 0; j < lista.size(); j++){
                if (lista.get(i) < lista.get(j)) {
                    x = lista.get(i);
//                    lista.set(i) = lista.get(j);
//                    lista.get(j) = x;
                }
            }
            if (lista.get(i) > x){
                x = lista.get(i);
                ordenada.add(x);
            }

            System.out.println("itens da lista " + lista.get(i));
        }
        System.out.println("itens do x " + ordenada);
        System.out.println("Sem ordenação");
        System.out.println(lista);
        Collections.sort(lista);
        System.out.println("Ordenada");
        System.out.println(lista);
    }



}
