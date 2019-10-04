package it.uniroma2.dicii.ispw.fersa.Share;

import it.uniroma2.dicii.ispw.fersa.Bean.UserBean;
import it.uniroma2.dicii.ispw.fersa.Entity.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Mailer {
    private static Mailer mailer_instance;
    private Session session;
    private Mailer() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "2525");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("43261e78f332df", "e2f523efc8a3b1");
            }
        });
    }

    public static Mailer getInstance() {
        if (mailer_instance == null) {
            mailer_instance = new Mailer();
        }
        return mailer_instance;
    }

    private void sendMessage(Message message, String msg) throws MessagingException {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    public boolean sendEditContractEmail(Contract contract)
    {
        String msg;
        if (contract.getRentable() instanceof Apartment) {
            Apartment apartment = (Apartment) contract.getRentable();
            String mailText = "Il locatore %s (email: %s) ha modificato il contratto per l'appartamento " +
                    "con indirizzo %s\nData inizio e fine contrato: %s %s\nPrezzo dell'affitto: %s ";
            msg = String.format(mailText,contract.getLessor().getUsername(), contract.getLessor().getEmail(), apartment.getAddress(),
                    contract.getStart_date().toString(), contract.getEnd_date().toString(),contract.getRentable().getFee());

        }
        else if (contract.getRentable() instanceof Room) {
            Room room = (Room) contract.getRentable();
            String mailText = "Il locatore %s (email: %s) ha modificato il contratto per la stanza numero: %s dell'appartamento " +
                    "con indirizzo %s\nData inizio e fine contrato: %s %s\nPrezzo dell'affitto: %s ";
            msg = String.format(mailText,contract.getLessor().getUsername(), contract.getLessor().getEmail(),room.getRoomNumber(), room.getApt().getAddress(),
                    contract.getStart_date().toString(), contract.getEnd_date().toString(),contract.getRentable().getFee());
        }
        else{
            Bed bed = (Bed) contract.getRentable();
            String mailText = "Il locatore %s (email: %s) ha modificato il contratto per il letto numero: %s della" +
                    " stanza numero: %s dell'appartamento " +
                    "con indirizzo %s\nData inizio e fine contrato: %s %s\nPrezzo dell'affitto: %s ";
            msg = String.format(mailText,contract.getLessor().getUsername(), contract.getLessor().getEmail(),bed.getBedNumber(),bed.getRoom().getRoomNumber(), bed.getRoom().getApt().getAddress(),
                    contract.getStart_date().toString(), contract.getEnd_date().toString(),contract.getRentable().getFee());
        }
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@fersa.it"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(contract.getRenter().getEmail()));
            message.setSubject("Avviso modifica visita");
            sendMessage(message, msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
