package vn.edu.fpt.projectstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.projectstore.entity.Type;
import vn.edu.fpt.projectstore.repository.TypeRepositories;


import java.util.List;
import java.util.UUID;

@Service
public class TypeService {

    @Autowired
    private TypeRepositories typeRepositories;

    public Type findById(UUID id) {
        return typeRepositories.findByTypeId(id);
    }


    public Type findByTypeName(String name) {
        return  typeRepositories.findByTypeName(name);
    }

    public List<Type> getAllType(){
        return typeRepositories.findAll();
    }



}
