//package pl.coderslab.charity;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//public class JavaMailSender extends JavaMailSenderImpl {
//
//    @Bean
//    public JavaMailSenderImpl getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("TestMail2137420@gmail.com");
//        mailSender.setPassword("Raczydlo 22@");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }
//
//}