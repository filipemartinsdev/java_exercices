package com.javaexercices.probabilidade;

import java.util.*;
import java.util.function.Predicate;

public class Amostra<T>{
    private int size;
    private Map<T, Integer> elementosMap;

    public Amostra(){
        this.elementosMap = new HashMap<>();
        this.size = 0;
    }

    public Amostra(List<T> amostra){
        this.elementosMap = new HashMap<>();
        this.size = 0;
        for(T E:amostra){
            elementosMap.merge(E, 1, Integer::sum);
            this.size++;
        }
    }

    public Amostra(Map<T, Integer> map){
        this.elementosMap = map;
        this.size = 0;
        for (Integer i:map.values()){
            this.size+=i;
        }
    }

    public void put(T E){
        elementosMap.merge(E,1, Integer::sum);
        this.size++;
    }

    public void putList(T[] list){
        for(T E:list){
            elementosMap.merge(E, 1, Integer::sum);
        }
    }

    public int getCount(T E){
        try {
            return this.elementosMap.get(E);
        } catch (NullPointerException e){
            return 0;
        }
    }

    public int size(){
        return this.size;
    }

    public List<T> getList(){
        List<T> out = new ArrayList<>();
        for (T E:this.elementosMap.keySet()){
            for(int i = getCount(E); i>0; i--){
                out.add(E);
            }
        }
        return out;
    }

    public void filter(Predicate<T> function){
        this.elementosMap.keySet().removeIf(e -> !function.test(e));
        this.size = 0;
        for (T E:this.elementosMap.keySet()){
            size+=elementosMap.get(E);
        }
    }
}
