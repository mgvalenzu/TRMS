package com.revature.repositories;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementRepo {

    public Reimbursement addReimbursement(Reimbursement r);
    public List<Reimbursement> getAllReimbursements();
    public Reimbursement getReimbursement(int id);
    public Reimbursement updateReimbursement(Reimbursement change);
    public Reimbursement deleteReimbursement(int id);
}
