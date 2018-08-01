package group12.TutorProfile;

import group12.email.IMailer;

public class MockMailer implements IMailer {
    @Override
    public void sendMail(String from, String to, String subject, String message) {
        System.out.println("Email Successfully Sent");
    }
}