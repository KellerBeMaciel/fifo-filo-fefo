package com.exemplo.models;

import java.util.Date;

public interface ExpiredableCloneable extends CloneableClass {

    public Date getExpirationDate();
    public boolean isExpired();
}
