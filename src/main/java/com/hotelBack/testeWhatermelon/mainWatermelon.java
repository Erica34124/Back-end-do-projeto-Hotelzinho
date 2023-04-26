package com.hotelBack.testeWhatermelon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class mainWatermelon {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        List<Integer> ordenada = new ArrayList<>();
        Random aleatorio = new Random();
        for(int i = 0; i < 5; i++){
            lista.add(aleatorio.nextInt(100));
        }
        System.out.println("Sem ordenação");
        System.out.println(lista);
        Collections.sort(lista);
        System.out.println("Ordenada");
        System.out.println(lista);
    }



}
