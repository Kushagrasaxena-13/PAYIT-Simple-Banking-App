package com.example.payit;

public class histroy {
    public String amount;
    public String done;
    public String from;
    public String time;
    public String to;

    public histroy() {
    }

    public histroy(String amount, String done, String from, String time, String to) {
        this.amount = amount;
        this.done = done;
        this.from = from;
        this.time = time;
        this.to = to;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
