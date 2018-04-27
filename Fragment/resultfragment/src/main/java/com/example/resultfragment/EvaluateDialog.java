package com.example.resultfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * 用于创建弹出的对话框类
 */

public class EvaluateDialog extends DialogFragment {
	private String[] choices = {"极对", "对", "一般", "不对"};
	public static final String DIALOG_ARGMENT = "dialog_argument";


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("正确度评价").setItems(choices, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setResult(which);
			}
		});

		return builder.create();
	}

	protected void setResult(int which) {
		if (getTargetFragment() == null) {
			return;
		}
		Intent intent = new Intent();
		intent.putExtra(DIALOG_ARGMENT, choices[which]);
		getTargetFragment().onActivityResult(TitleFragment.REQUEST_CODE, TitleFragment
			   .RESULT_CODE, intent);

	}

}
