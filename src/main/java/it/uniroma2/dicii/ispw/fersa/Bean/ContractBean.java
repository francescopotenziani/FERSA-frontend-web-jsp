package it.uniroma2.dicii.ispw.fersa.Bean;

import com.itextpdf.kernel.pdf.PdfReader;
import it.uniroma2.dicii.ispw.fersa.Entity.StateEnum;

import java.time.LocalDate;

public class ContractBean {
    private UserBean lessor;
    private UserBean renter;
    private LocalDate start_date;
    private LocalDate end_date;
    private RentableBean rentable;
    private PdfReader pdfContract;
    private StateEnum state;

    public StateEnum getState() { return state; }

    public UserBean getLessor() { return lessor; }

    public UserBean getRenter() { return renter; }

    public LocalDate getStart_date() { return start_date; }

    public LocalDate getEnd_date() { return end_date; }

    public RentableBean getRentable() { return rentable; }

    public PdfReader getPdfContract() { return pdfContract; }

    public void setState(StateEnum state) { this.state = state; }

    public void setLessor(UserBean lessor) { this.lessor = lessor; }

    public void setRenter(UserBean renter) { this.renter = renter; }

    public void setStart_date(LocalDate start_date) { this.start_date = start_date; }

    public void setEnd_date(LocalDate end_date) { this.end_date = end_date; }

    public void setRentable(RentableBean rentable) { this.rentable = rentable; }

    public void setPdfContract(PdfReader pdfContract) { this.pdfContract = pdfContract; }


}
