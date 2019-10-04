package it.uniroma2.dicii.ispw.fersa.Share;

import it.uniroma2.dicii.ispw.fersa.Controller.ContractUpdateController;
import it.uniroma2.dicii.ispw.fersa.Entity.Contract;

public class ThreadMailer implements Runnable {
    private Contract contract;
    private ContractUpdateController controller;

    public ThreadMailer(Contract contract, ContractUpdateController controller){
        this.contract = contract;
        this.controller = controller;
    }
    @Override
    public void run() {
        controller.sendEditoContractEmail(contract);
    }
}
