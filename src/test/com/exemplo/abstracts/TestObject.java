package com.exemplo.abstracts;

import com.exemplo.models.CloneableClass;

public class TestObject implements CloneableClass {
    public String name;

    public TestObject(String name){
        this.name = name;
    }

    @Override
    public TestObject clone() {
        try {
            return (TestObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object obj){
        if(obj.getClass() != this.getClass()) return false;
        TestObject newObj = (TestObject) obj;

        return newObj.name.equals(this.name);
    }
}
