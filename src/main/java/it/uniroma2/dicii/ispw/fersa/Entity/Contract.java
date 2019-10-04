package it.uniroma2.dicii.ispw.fersa.Entity;
import com.itextpdf.kernel.pdf.PdfReader;
import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import org.joda.time.LocalDateTime;

import java.time.LocalDate;

public class Contract {
    private User lessor;
    private User renter;
    private LocalDate start_date;
    private LocalDate end_date;
    private Rentable rentable;
    private PdfReader pdfContract;
    private StateEnum state;
    private LocalDateTime timeToExpire;

        public Contract(User lessor, Rentable rentable, User renter, LocalDate start_date, LocalDate end_date,
                    PdfReader pdfContract, StateEnum appr, LocalDateTime timeToExpire) {
        this.lessor = lessor;
        this.renter = renter;
        this.rentable = rentable;
        this.start_date = start_date;
        this.end_date = end_date;
        this.pdfContract = pdfContract;
        this.state = appr;
        this.timeToExpire = timeToExpire;
    }


    public User getLessor() { return lessor; }
    public User getRenter() { return renter; }
    public Rentable getRentable() { return rentable; }
    public LocalDate getStart_date() { return start_date; }
    public LocalDate getEnd_date() { return end_date; }
    public StateEnum getState() { return state; }
    public LocalDateTime getTimeToExpire() { return timeToExpire; }

    public void setRentable(Rentable rentable) {
        this.rentable = rentable;
    }

    public void setState(StateEnum state) { this.state = state; }

    public void setTimeToExpire(LocalDateTime timeToExpire) { this.timeToExpire = timeToExpire; }
}
