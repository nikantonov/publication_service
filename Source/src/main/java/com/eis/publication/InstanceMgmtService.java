package com.eis.publication;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.Set;

public final class InstanceMgmtService {
    Set<Integer> instances = new HashSet<>();

    public InstanceMgmtService (){
        instances.add(5);
        instances.add(6);
    }

    public boolean check(int i){
        boolean b = false;
        for(int y: instances){
            if(y == i) b = true;
        }
        return b;
    }

    public void add(int i){
        instances.add(i);
    }
}

