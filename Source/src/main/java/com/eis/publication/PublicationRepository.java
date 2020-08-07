package com.eis.publication;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Exception;

public class PublicationRepository implements  PRepository{
    private List<Publication> publications;
    private static PublicationRepository instance = null;

    PublicationRepository(){
        this.publications = new ArrayList<>();

        Publication p1 = new Publication(5, "Mathematics", "Mann", 2017, "Spring" );
        Publication p2 = new Publication(6, "IT", "Thomas", 2017, "Spring" );
        Publication p3 = new Publication(5, "German", "Harald", 2017, "Wien" );

        this.publications.addAll(Arrays.asList(p1,p2,p3));
    }

    @Override
    public List<Publication> retrieveByTitel(String titel) throws Exception{
        List<Publication> listPublikation = new ArrayList<>();
        for(Publication p : publications){
            if(p.getTitel().equals(titel)){
                listPublikation.add(p);
            }
            System.out.println(titel);
            System.out.println(listPublikation.size());
        }
        if(listPublikation.size() == 0){
            throw new Exception("We don't have publications with this titel");
        }
        return listPublikation;
    }


    @Override
    public List<Publication> retrieveByUser(int userid) throws Exception{
        List<Publication> listPublikation = new ArrayList<>();
        for(Publication p : publications){
            if(p.getUserId() == userid){
                listPublikation.add(p);
            }
        }
        if(listPublikation.size() == 0){
            throw new Exception("User didn't save publications yet");
        }
        return listPublikation;
    }

    @Override
    public Publication addPublication(Publication p) throws Exception
    {
        this.publications.add(p);
        return p;
    }

    @Override
    public void deletePublication(String titel) throws Exception
    {
        System.out.println("Hi");
        Publication pub = this.publications.stream()
                .filter(u -> u.getTitel().equals(titel))
                .findFirst().orElse(null);

        if(pub != null){
            publications.remove(pub);
        }else{
            throw new Exception("Publikation doens't exist");
        }
    }



    public static PublicationRepository getRepository() {
        if(instance == null) {
            instance = new PublicationRepository();
        }
         return instance;
    }
}
