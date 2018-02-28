package com.example.cadu.rededossaberes.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.cadu.rededossaberes.R;
import com.example.cadu.rededossaberes.activity.CriarProjetos;

public class TextViewEditFragment extends DialogFragment {
/*
    @Override
    public void onCreate(Bundle savedInstanceState)
    {*/
/*        super.onCreate(savedInstanceState);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.activity_input_text, null);
        final EditText editableText = view.findViewById(R.id.currentText);
        editableText.setText(savedInstanceState.getString("currentName"));*/
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.activity_input_text, null);
        final EditText editableText = view.findViewById(R.id.currentText);
        editableText.setText(getArguments().getString("currentName"));
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                .setTitle("Escolha o nome do novo passo")
                // Set Dialog Title
                .setView(view)
                /*.setTitle("Escolha o nome do novo passo")*/
                // Set Dialog Message
                // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CriarProjetos.setParentText(editableText.getText().toString(),getArguments().getInt("currentParentIndex"));
                        LayoutInflater inflater = getLayoutInflater();
                        View view = inflater.inflate(R.layout.activity_criar_projetos,null);
                        Button botao = view.findViewById(R.id.botaoAtualizar);
                        botao.callOnClick();
                        dialog.dismiss();
                    }
                })
                // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
    }
}
