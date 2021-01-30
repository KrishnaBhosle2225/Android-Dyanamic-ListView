package com.krishna.dyanamiclist;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class model {

    String roleId;
    String fName;
    String lName;
    String branch;
    byte[] imageView;


    public model() {
        this.roleId = roleId;
        this.fName = fName;
        this.lName = lName;
        this.branch = branch;
        this.imageView = imageView;
    }

    public byte[] getImageView() {
        return imageView;
    }

    public void setImageView(byte[] imageView) {
        this.imageView = imageView;

    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
