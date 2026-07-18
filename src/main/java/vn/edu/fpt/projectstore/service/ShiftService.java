package vn.edu.fpt.projectstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.projectstore.repository.ShiftRepository;


@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;
}
