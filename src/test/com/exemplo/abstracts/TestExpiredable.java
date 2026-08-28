package com.exemplo.abstracts;

import com.exemplo.models.ExpiredableCloneable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestExpiredable implements ExpiredableCloneable {
    public String name;
    public Date expirationDate;

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    public TestExpiredable(String name, Date expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }

    @Override
    public Date getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public boolean isExpired() {
        return this.expirationDate.before(new Date());
    }

    @Override
    public boolean equals(Object obj){
        if(obj.getClass() != this.getClass()) return false;
        TestExpiredable newObj = (TestExpiredable) obj;

        return newObj.name.equals(this.name) && newObj.expirationDate.equals(this.expirationDate);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("TestExpiredable=(");
        sb.append("name=");
        sb.append(name);
        sb.append(", expirationDate=");
        sb.append(formatter.format(expirationDate));
        sb.append(")");

        return sb.toString();
    }

    @Override
    public Object clone() {
        return new TestExpiredable(this.name, (Date) this.expirationDate.clone());
    }
}
