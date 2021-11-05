package com.revature.services;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.repositories.EmployeeRepo;
import com.revature.repositories.ReimbursementRepo;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ReimbursementServiceImpl implements ReimbursementService{

    ReimbursementRepo rr;
    EmployeeServiceImpl es;

    public ReimbursementServiceImpl(ReimbursementRepo rr) {
        this.rr = rr;
    }

    // Basically going to pass the torch down to the repo layer for
    // these basic operations

    @Override
    public Reimbursement addReimbursement(Reimbursement r) {
        return rr.addReimbursement(r);
    }

    @Override
    public Reimbursement getReimbursement(int id) {
        return rr.getReimbursement(id);
    }
//
//    @Override
//    public Reimbursement getReimbursement(String name) {
//        return rr.getReimbursement(name);
//    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        return rr.getAllReimbursements();
    }

    @Override
    public Reimbursement updateReimbursement(Reimbursement change) {
        return rr.updateReimbursement(change);
    }

    @Override
    public Reimbursement deleteReimbursement(int id) {
        return rr.deleteReimbursement(id);
    }

    // Service layer methods

    //Gets all of the reimbursements for a particular at given employee id.
    @Override
    public List<Reimbursement> getAllReimbursementsByEmployeeId(int id){
        List<Reimbursement> allReimbursements =  null;
        allReimbursements = rr.getAllReimbursements();

        //Iterate through all reimbursements and add the ones where the employee id == id
        try {
            allReimbursements.removeIf(r -> r.getEmployeeID().getId() != id);
        }catch(ConcurrentModificationException e) {
            e.printStackTrace();
        }
        return allReimbursements;
    }
//
//    @Override
//    public List<Reimbursement> getAllReimbursementsWhereSupervisor(int supervisorID){
//        List<Employee> subordinates = null;
//        try{
//             subordinates = es.getSupervisorsSubordinates(supervisorID);
//        }catch(NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
//
//        for(Employee sub : subordinates){
//            //get all reimbursements for each subordinate
//            List<Reimbursement> subsReimbursements = getAllReimbursementsByEmployeeId(sub.getId());
//            System.out.println(subsReimbursements);
//        }
//
//        return reimbursements;
//    }

}