package com.example.demo1.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Activity {
    private long id;
    private long user_id;
    private String activity;
    private BigDecimal duration;
    private Timestamp publication_date;
    private boolean deletable;

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public Activity(String activity, BigDecimal duration) {
        this.activity = activity;
        this.duration = duration;
    }

    public Activity(long id, long user_id, String activity, BigDecimal duration, Timestamp publication_date, Boolean deletable) {
        this.id = id;
        this.user_id = user_id;
        this.activity = activity;
        this.duration = duration;
        this.publication_date = publication_date;
        this.deletable = deletable;
    }

    public Activity(long id, String activity, BigDecimal duration, Timestamp publication_date) {
        this.id = id;
        this.activity = activity;
        this.duration = duration;
        this.publication_date = publication_date;
    }

    public Activity(long id, String activity, BigDecimal duration) {
        this.id = id;
        this.activity = activity;
        this.duration = duration;
    }
    // TODO : что-то придумать с этими двумя конструкторами (Long, String, String ) / (String, Long, String)
    public Activity(String activity, long userId, BigDecimal duration) {
        this.user_id = userId;
        this.activity = activity;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public Timestamp getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Timestamp publication_date) {
        this.publication_date = publication_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity1 = (Activity) o;
        return id == activity1.id && user_id == activity1.user_id && Objects.equals(activity, activity1.activity) && Objects.equals(duration, activity1.duration) && Objects.equals(publication_date, activity1.publication_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, activity, duration, publication_date);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", activity='" + activity + '\'' +
                ", duration=" + duration +
                ", publication_date=" + publication_date +
                '}';
    }
}
