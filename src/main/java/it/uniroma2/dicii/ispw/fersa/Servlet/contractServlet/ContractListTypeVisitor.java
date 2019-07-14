package it.uniroma2.dicii.ispw.fersa.Servlet.contractServlet;

import it.uniroma2.dicii.ispw.fersa.Bean.*;
import it.uniroma2.dicii.ispw.fersa.Controller.ContractUpdateController;
import it.uniroma2.dicii.ispw.fersa.Visitor.Visitor;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContractListTypeVisitor implements Visitor {

    private BidiMap<ContractBean,ApartmentBean> apartmentContractMap;
    private BidiMap<ContractBean,RoomBean> roomContractMap;
    private BidiMap<ContractBean,BedBean> bedContractMap;
    private ContractUpdateController contractUpdateController;

    public ContractListTypeVisitor(){
        apartmentContractMap = new DualHashBidiMap<>();
        roomContractMap = new DualHashBidiMap<>();
        bedContractMap = new DualHashBidiMap<>();
    }

    @Override
    public void visit(ApartmentBean apartmentBean) {
        ContractBean contractBean = null;
        contractUpdateController = new ContractUpdateController();
        try {
            contractBean = contractUpdateController.findContract(apartmentBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        apartmentContractMap.put(contractBean,apartmentBean);

    }

    @Override
    public void visit(RoomBean roomBean) {
        ContractBean contractBean = null;
        contractUpdateController = new ContractUpdateController();
        try {
            contractBean = contractUpdateController.findContract(roomBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        roomContractMap.put(contractBean,roomBean);
    }

    @Override
    public void visit(BedBean bedBean) {
        ContractBean contractBean = null;
        contractUpdateController = new ContractUpdateController();
        try {
            contractBean = contractUpdateController.findContract(bedBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bedContractMap.put(contractBean,bedBean);
    }

    @Override
    public void visit(RentableBean rentableBean) {

    }

    public BidiMap<ContractBean,ApartmentBean> getApartmentContractList() { return apartmentContractMap; }

    public BidiMap<ContractBean,RoomBean> getRoomContractList() { return roomContractMap; }

    public BidiMap<ContractBean,BedBean> getBedContractList() { return bedContractMap; }
}
