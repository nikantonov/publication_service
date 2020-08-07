package com.eis.publication;

import com.eis.publication.Publication;

import java.util.List;

public interface PRepository {
    public List<Publication> retrieveByTitel (String titel) throws Exception;
    public List<Publication> retrieveByUser(int userid) throws Exception;
    public Publication addPublication(Publication p) throws Exception;
    public void deletePublication(String titel) throws Exception;
}
