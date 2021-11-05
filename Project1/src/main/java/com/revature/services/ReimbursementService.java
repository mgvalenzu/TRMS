package com.revature.services;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementService {

    // Repository Methods
    public Reimbursement addReimbursement(Reimbursement r);
    public Reimbursement getReimbursement(int id);
//    public Reimbursement getReimbursement(String name); // get Reimbursement by name
    public List<Reimbursement> getAllReimbursements();
    public Reimbursement updateReimbursement(Reimbursement change);
    public Reimbursement deleteReimbursement(int id);

    // Other business logic methods here
    public List<Reimbursement> getAllReimbursementsByEmployeeId(int id);
//    public List<Reimbursement> getAllReimbursementsWhereSupervisor(int id);
}
