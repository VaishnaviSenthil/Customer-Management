package com.staxrt.tutorial.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity

@Table(name = "Feedback")
@EntityListeners(AuditingEntityListener.class)

public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


  
    @Column(name = "Customer_Name", nullable = false)
    private String Name;

    @Column(name = "email_address", nullable = false)
    private String email;
    
    @Column(name = "phone_number", nullable = false)
    private long phone;

    @Column(name = "Question1", nullable = false)
    private String question1;

    @Column(name = "Question2", nullable = false)
    private String  question2 ;

    @Column(name = "Question3", nullable = false)
    private String question3;

   

    @Column(name = "Message", nullable = false)
    private String message;


      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     @Override
    public String toString() {
        return "Feedback [ Name=" + Name + ", email=" + email + ", phone=" + phone + ", question1="
                + question1 + ", question2=" + question2 + ", question3=" + question3 + ", message=" + message + "]";
    }
}

   