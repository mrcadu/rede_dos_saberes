package com.example.cadu.rededossaberes.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.cadu.rededossaberes.R;

public class ImageShowerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.activity_image_shower, null);
        Bitmap bmp = BitmapFactory.decodeByteArray(getArguments().getByteArray("imagem"), 0, getArguments().getByteArray("imagem").length);
        ImageView imageShower = view.findViewById(R.id.imageShower);
        imageShower.setImageBitmap(Bitmap.createScaledBitmap(bmp, 200,
                200, false));
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                // Negative Button
                .create();
    }
}
