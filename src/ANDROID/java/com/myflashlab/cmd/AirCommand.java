package com.myflashlab.cmd;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.myflashlab.Conversions;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import nativeTestCMD.ExConsts;

/**
 * @author Myflashlabs Team
 */
public class AirCommand implements FREFunction
{
	private boolean isDialogCalled = false;
	private boolean isDialogClicked = false;

	private Activity _activity;

	private enum commands
	{
		isTestVersion,
		toExecute,
	}

	private void showTestVersionDialog()
	{
		// If we know at least one ANE is DEMO, we don't need to show demo dialog again. It's already shown once.
		if(com.myflashlab.dependency.overrideAir.MyExtension.hasAnyDemoAne()) return;

		// Check if this ANE is registered?
		if(com.myflashlab.dependency.overrideAir.MyExtension.isAneRegistered(ExConsts.ANE_NAME)) return;

		// Otherwise, show the demo dialog.

		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(_activity);
		dialogBuilder.setTitle("DEMO ANE!");
		dialogBuilder.setMessage("The library '"+ExConsts.ANE_NAME+"' is not registered!");
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int id)
			{
				dialog.dismiss();
				isDialogClicked = true;
			}
		});

		AlertDialog myAlert = dialogBuilder.create();
		myAlert.show();

		isDialogCalled = true;
	}

	public FREObject call(FREContext $context, FREObject[] $params)
	{
		String command = Conversions.AirToJava_String($params[0]);
		FREObject result = null;

		if (_activity == null)
		{
			_activity = $context.getActivity();
		}

		switch (commands.valueOf(command))
		{
			case isTestVersion:

				showTestVersionDialog();

				break;
			case toExecute:

				toExecute(
						Conversions.AirToJava_String($params[1]), // $command
						Conversions.AirToJava_Boolean($params[2]) // $isRoot
				);

				break;
		}

		return result;
	}

	private void toExecute(String $command, Boolean $isRoot)
	{
		StringBuilder returnString = new StringBuilder();
		try
		{
			String temp;
			toTrace("execute: " + $command);

			Process process;

			if($isRoot) process = Runtime.getRuntime().exec(new String[]{"su", "-c", $command});
			else 		process = Runtime.getRuntime().exec($command);

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((temp = reader.readLine()) != null)
			{
				returnString.append(temp);
			}
		}
		catch (Exception e)
		{
			toTrace(e.getMessage());
			MyExtension.toDispatch(ExConsts.OUTPUT, e.getMessage());
		}

		MyExtension.toDispatch(ExConsts.OUTPUT, returnString.toString());
	}

	private void toTrace(String $msg)
	{
		com.myflashlab.dependency.overrideAir.MyExtension.toTrace(
				ExConsts.ANE_NAME,
				this.getClass().getSimpleName(),
				$msg);
	}
}