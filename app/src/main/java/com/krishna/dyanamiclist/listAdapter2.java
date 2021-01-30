package com.krishna.dyanamiclist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class listAdapter2 extends BaseAdapter {

    Context mContext;
    List<model> modelStudents;

    public listAdapter2(Context mContext, List<model> modelStudents) {
        this.mContext = mContext;
        this.modelStudents = modelStudents;
    }

    @Override
    public int getCount() {
        return modelStudents.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View studentListView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            studentListView = inflater.inflate(R.layout.list2, null);
        } else {
            studentListView = convertView;
        }

        CircleImageView img = studentListView.findViewById(R.id.imm1);
        TextView fName = studentListView.findViewById(R.id.txtViewFname);
        //TextView lName = studentListView.findViewById(R.id.txtViewLname);
        TextView rollNo = studentListView.findViewById(R.id.txtViewRoll);
        TextView branch = studentListView.findViewById(R.id.txtViewBranch);

        model student = modelStudents.get(position);

        byte[] getImage =student.getImageView();
        Bitmap bmp = BitmapFactory.decodeByteArray(getImage,0,getImage.length);
        img.setImageBitmap(bmp);
        fName.setText(student.getfName());
        //lName.setText(student.getlName());
        rollNo.setText(student.getRoleId());
        branch.setText(student.getBranch());

        return studentListView;
    }
}